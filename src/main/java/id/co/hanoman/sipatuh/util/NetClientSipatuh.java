package id.co.hanoman.sipatuh.util;

import java.io.BufferedReader;
//import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.xml.bind.DatatypeConverter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.RequestDataValueProcessor;
import org.thymeleaf.spring5.expression.RequestDataValues;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import id.co.hanoman.config.YAMLConfig;
import id.co.hanoman.domain.Token;
import id.co.hanoman.sipatuh.model.ErrorResponse;
import id.co.hanoman.sipatuh.model.Response;
import id.co.hanoman.sipatuh.model.LoginSipatuh;
import id.co.hanoman.sipatuh.model.InquiryPembayaran;
import id.co.hanoman.sipatuh.model.TrxPembayaran;
import id.co.hanoman.sipatuh.model.CetakBuktiTransaksi;
import id.co.hanoman.sipatuh.model.InfoTrxCabang;
import id.co.hanoman.sipatuh.model.InfoTrxNoRegis;
import id.co.hanoman.sipatuh.model.InfoTrxPeriode;


@Component
public class NetClientSipatuh {
	static Logger log = LoggerFactory.getLogger(NetClientSipatuh.class);	 

	@Autowired	
	YAMLConfig config ;
	
	public   Object login(LoginSipatuh req) throws Exception{
		Object res = null;
		try {
			
			String bodyrequest = "{\"userid\": \""+config.getUserid()+"\",\"password\": \""+config.getPassword()+"\"}";
			bodyrequest = bodyrequest.replace(" ", "");
			bodyrequest = bodyrequest.replace("\t", "");
			
			Object resCall = callUrl(bodyrequest,"login");
		    Response resp = new Response();
		    resp.setResponse(resCall);
		    res = resp;
			
		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		}
		return res;
	}

	
	public   Object pembayaran(TrxPembayaran req) throws Exception{
		Object res = null;
		try {
			
			String no_regis = req.getNomor_registrasi();
			String kd_cabang = req.getKd_cabang();
			String tgl_bayar = req.getTgl_bayar();
			String nom_ppiu = req.getNominal_ppiu();
			String nom_asu = req.getNominal_asuransi();
			String no_rek_ppiu = req.getNominal_rek_ppiu();
			String no_rek_asu = req.getNominal_rek_asuransi();
			String nama_rek_ppiu = req.getNama_rek_ppiu();
			String nama_rek_asu = req.getNama_rek_asuransi();
			String kd_channel = req.getKd_channel();
			String no_reff = req.getNomor_referensi();
			
			String bodyrequest = "{\"nomor_registrasi\": \""+no_regis+"\",\"kd_cabang\": \""+kd_cabang+"\",\"tgl_bayar\": \""+tgl_bayar+"\",\"nominal_ppiu\": \""+nom_ppiu+"\",\"nominal_asuransi\": \""+nom_asu+"\",\"nomor_rek_ppiu\": \""+no_rek_ppiu+"\",\"nomor_rek_asuransi\": \""+no_rek_asu+"\",\"nama_rek_ppiu\": \""+nama_rek_ppiu+"\",\"nama_rek_asuransi\": \""+nama_rek_asu+"\",\"kd_channel\": \""+kd_channel+"\",\"nomor_referensi\": \""+no_reff+"\"}";
			bodyrequest = bodyrequest.replace(" ", "");
			bodyrequest = bodyrequest.replace("\n", "");
			
			Object resCall = callUrl(bodyrequest,"pemabayaran");
		    Response resp = new Response();
		    resp.setResponse(resCall);
		    res = resp;
			
		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();

		}
		return res;
	}
	

	private  Object callUrl(String data1,String subPath) throws Exception{
		log.info("request "+subPath+": "+ data1);
	
		URL url = new URL(config.getBaseUrl()+subPath);
		try {
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(config.getTimeout());
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("x-key", config.getXkey());
			conn.setRequestProperty("x-access-key", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiIyMDAiLCJjYXRlZ29yeSI6IkIiLCJpYXQiOjE1Njg3OTUxODEsImV4cCI6MTU2OTM5OTk4MX0.Hl1GmdTtIY2ueePmA_F2F-bIsh4fZhxgiyK1nZfLF7E");
			
			OutputStream os = conn.getOutputStream();
			os.write(data1.getBytes());
			os.flush();
			
			if (conn.getResponseCode() != 200) {
				ErrorResponse errRes = new ErrorResponse();
				errRes.setCode(String.valueOf(conn.getResponseCode()));
				BufferedReader br = new BufferedReader(new InputStreamReader(
						(conn.getErrorStream())));
				ObjectMapper mapper = new ObjectMapper();
				JsonNode root = mapper.readTree(br);
				String faultCode = root.get("fault").get("code").toString();
				if(faultCode.equals("900901")){
					log.info("Error :"+ faultCode +"callToken");
//					token = null;
				
				}
				errRes.setError(root);

				return errRes;

			}else {
				BufferedReader br = new BufferedReader(new InputStreamReader(
						(conn.getInputStream())));
				ObjectMapper mapper = new ObjectMapper();
				JsonNode root = mapper.readTree(br);

				return root;
			}
		} catch (Exception e) {
			log.info("disini1:"+e.getMessage());
			String strErrMsg = e.toString();
			ErrorResponse errRes = new ErrorResponse();
			errRes.setCode("400");
			if(strErrMsg.equals("java.net.ConnectException: Connection refused: connect")){
				ObjectMapper mapper = new ObjectMapper();
				JsonNode root = mapper.createObjectNode();
				ObjectNode faultNode = mapper.createObjectNode();
				faultNode.put("code", "G02");
				faultNode.put("message", "Failed to connect");
				((ObjectNode) root).set("fault", faultNode);
				errRes.setError(root);
			} else if(strErrMsg.equals("java.net.SocketTimeoutException: Read timed out")){
				ObjectMapper mapper = new ObjectMapper();
				JsonNode root = mapper.createObjectNode();
				ObjectNode faultNode = mapper.createObjectNode();
				faultNode.put("code", "G01");
				faultNode.put("message", "Timeout from giropos");
				((ObjectNode) root).set("fault", faultNode);
				errRes.setError(root);
			}else {
				ObjectMapper mapper = new ObjectMapper();
				JsonNode root = mapper.createObjectNode();
				ObjectNode faultNode = mapper.createObjectNode();
				faultNode.put("code", "G99");
				faultNode.put("message", "General Error");
				((ObjectNode) root).set("fault", faultNode);
				errRes.setError(root);

			}
			
			return errRes;
		}

	}
		
}

