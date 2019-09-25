package id.co.hanoman.sipatuh.util;

import java.io.BufferedReader;
//import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.lang.annotation.Annotation;
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

import org.jboss.logging.LogMessage;
import org.jboss.logging.Logger.Level;
import org.jose4j.http.Get;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
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


	
	public Object login(LoginSipatuh req) throws Exception{
		Object resCall = null;
		try {
			
			String bodyrequest = "{\"userid\": \""+config.getUserid()+"\",\"password\": \""+config.getPassword()+"\"}";
			bodyrequest = bodyrequest.replace(" ", "");
			bodyrequest = bodyrequest.replace("\t", "");
			
			resCall = callUrl(bodyrequest,"login");
			
		    
		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		}
		return resCall;
	}
	
	
	public   Object pembayaran(TrxPembayaran req) throws Exception{
		Object resCall = null;
		try {
			
			String no_regis = req.getNomor_registrasi();
			String kd_cabang = req.getKd_cabang();
			String tgl_bayar = req.getTgl_bayar();
			String nom_ppiu = req.getNominal_ppiu();
			String nom_asu = req.getNominal_asuransi();
			String no_rek_ppiu = req.getNomor_rek_ppiu();
			String no_rek_asu = req.getNomor_rek_asuransi();
			String nama_rek_ppiu = req.getNama_rek_ppiu();
			String nama_rek_asu = req.getNama_rek_asuransi();
			String kd_channel = req.getKd_channel();
			String no_reff = req.getNomor_referensi();
			
			String bodyrequest = "{\n\"nomor_registrasi\" : \""+no_regis+"\",\n\"kd_cabang\": \""+kd_cabang+"\",\n\"tgl_bayar\" : \""+tgl_bayar+"\",\n\"nominal_ppiu\" : \""+nom_ppiu+"\",\n\"nominal_asuransi\" : \""+nom_asu+"\",\n\"nomor_rek_ppiu\" : \""+no_rek_ppiu+"\",\n\"nomor_rek_asuransi\" : \""+no_rek_asu+"\",\n\"nama_rek_ppiu\" : \""+nama_rek_ppiu+"\",\n\"nama_rek_asuransi\" : \""+nama_rek_asu+"\",\n\"kd_channel\" : \""+kd_channel+"\",\n\"nomor_referensi\" : \""+no_reff+"\"}";
			
			resCall = callUrl(bodyrequest,"pembayaran");
			
		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return resCall;
	}
	
	public Object inquiry(InquiryPembayaran no) throws Exception{
		Object res = null;
		try {
			
			String nomor = no.getNoregistrasi();
			
			Object resCall = callUrlinq(nomor,"inquiry");
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
	


	private  Object callUrlinq(String data1, String subPath) throws Exception{
		log.info("request : "+ data1);
	
		String noreg = "40016920825392";
		
		URL url = new URL(config.getBaseUrl()+"pembayaran/"+noreg);
		log.info("request : "+ data1);
		
		try {
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(config.getTimeout());
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("x-key", config.getXkey());
			conn.setRequestProperty("x-access-key", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiIyMDAiLCJjYXRlZ29yeSI6IkIiLCJpYXQiOjE1NjkzMTMzMTIsImV4cCI6MTU2OTkxODExMn0.lbzV0aijuxy-8I-OSFLlOjeuFlAh4jbCLM7tHs65DOo");
			
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
		
	
	private  Object callUrl(String data1,String subPath) throws Exception{
		log.info("request "+subPath+": "+ data1);
	
		URL url = new URL(config.getBaseUrl()+subPath);
		log.info("url 1 :"+url);
		try {
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			log.info("conn :" + conn);
			
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("x-key", config.getXkey());
			conn.setRequestProperty("x-access-key", config.getTokenIsi());
			
			OutputStream os = conn.getOutputStream();
			os.write(data1.getBytes());
			os.flush();
			
			if (conn.getResponseCode() != 200) {
				ErrorResponse errRes = new ErrorResponse();
				errRes.setCode(String.valueOf(conn.getResponseCode()));
				log.info("error 1 :"+ errRes);
				BufferedReader br = new BufferedReader(new InputStreamReader(
						(conn.getErrorStream())));
				log.info("error 2 :"+ br);
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
				log.info("error 2 :"+ br);
				ObjectMapper mapper = new ObjectMapper();
				JsonNode root = mapper.readTree(br);

				return root;
			}
		} catch (Exception e) {
			String strErrMsg = e.toString();
			ErrorResponse errRes = new ErrorResponse();
			errRes.setCode("400");
			if(strErrMsg.equals("java.net.ConnectException: Connection refused: connect")){
				errRes.setFaultCode("G02");
				errRes.setFaultMessage("Failed to Connect");
				
			} else if(strErrMsg.equals("java.net.SocketTimeoutException: Read timed out")){
				errRes.setFaultCode("G01");
				errRes.setFaultMessage("Timeout from Server");
				
			}else {
				errRes.setFaultCode("G99");
				errRes.setFaultMessage("General Error");
				log.info("error 2 :"+ errRes);

			}
			
			return errRes;
		}

	}


}

