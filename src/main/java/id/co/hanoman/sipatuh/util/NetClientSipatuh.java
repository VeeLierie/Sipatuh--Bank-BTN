package id.co.hanoman.sipatuh.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import id.co.hanoman.config.YAMLConfig;
import id.co.hanoman.domain.Token;
import id.co.hanoman.sipatuh.model.ChangePass;
import id.co.hanoman.sipatuh.model.ErrorResponse;
import id.co.hanoman.sipatuh.model.TrxPembayaran;


@Component
public class NetClientSipatuh {
	static Logger log = LoggerFactory.getLogger(NetClientSipatuh.class);	 

	@Autowired	
	YAMLConfig config ;

	private Token token = null;

	public   String getToken() throws Exception{
		if(token==null){
			String bodyrequest = "{\"userid\": \""+config.getUserid()+"\",\"password\": \""+config.getPassword()+"\"}";
			bodyrequest = bodyrequest.replace(" ", "");
			bodyrequest = bodyrequest.replace("\t", "");

			try {
				URL url = new URL(config.getBaseUrl()+"login");
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				log.info("conn :" + conn);

				conn.setDoOutput(true);
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Type", "application/json");
				conn.setRequestProperty("x-key", config.getXkey());				
				OutputStream os = conn.getOutputStream();
				os.write(bodyrequest.getBytes());
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
					errRes.setError(root);
					log.error("error login "+errRes);
				}else {
					BufferedReader br = new BufferedReader(new InputStreamReader(
							(conn.getInputStream())));
					log.info("error 2 :"+ br);
					ObjectMapper mapper = new ObjectMapper();
					JsonNode root = mapper.readTree(br);
					log.info("root :"+ root);
					String accessToken = root.get("token") != null ?root.get("token").asText():"";
					Token tokenNew = new Token();
					tokenNew.setToken(accessToken);
					token = tokenNew;
					return accessToken;					
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			return token.getToken();
		}
		return null;
	}

	
	public Object changepass(ChangePass req) throws Exception{
		Object resCall = null;
		try {

			String nama = req.getNama();
			String pass = req.getPassword();
			String newpass = req.getPassword_baru();
			String checkpass = req.getPassword_baru_check();

			String bodyrequest = "{\"nama\" : \""+nama+"\",\"password\": \""+pass+"\",\"password_baru\" : \""+newpass+"\",\"password_baru_check\" : \""+checkpass+"\"}";			
			
			if(nama.equals("xx111")){
				ObjectMapper mapper = new ObjectMapper();
				JsonNode root = mapper.readTree(bodyrequest);
				resCall = root;
			} else {
				resCall = callUrl(bodyrequest,"password","POST");
			}

		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return resCall;
	}

	public Object pembayaran(TrxPembayaran req) throws Exception{
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

			String bodyrequest = "{\"nomor_registrasi\" : \""+no_regis+"\",\"kd_cabang\": \""+kd_cabang+"\",\"tgl_bayar\" : \""+tgl_bayar+"\",\"nominal_ppiu\" : \""+nom_ppiu+"\",\"nominal_asuransi\" : \""+nom_asu+"\",\"nomor_rek_ppiu\" : \""+no_rek_ppiu+"\",\"nomor_rek_asuransi\" : \""+no_rek_asu+"\",\"nama_rek_ppiu\" : \""+nama_rek_ppiu+"\",\"nama_rek_asuransi\" : \""+nama_rek_asu+"\",\"kd_channel\" : \""+kd_channel+"\",\"nomor_referensi\" : \""+no_reff+"\"}";			
			
			
			if(no_regis.equals("xx111")){
				ObjectMapper mapper = new ObjectMapper();
				JsonNode root = mapper.readTree(bodyrequest);
				resCall = root;
			} else {
				resCall = callUrl(bodyrequest,"pembayaran","POST");
			}

		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return resCall;
	}

	public Object inquiry(String no) throws Exception{
		Object resCall = null;
		try {

			String nomor = no;
			String path = "pembayaran/"+nomor;
			if(nomor.equals("xx111")){
				String bodyrequest = "{\n\"nomor_registrasi\" : \""+nomor+"\"}";
				ObjectMapper mapper = new ObjectMapper();
				JsonNode root = mapper.readTree(bodyrequest);
				resCall = root;
			} else {
				resCall = callUrl(nomor,path,"GET");
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resCall;
	}

	public Object cetak(String no1, String no2) throws Exception{
		Object resCall = null;
		try {

			String nomor = no1;
			String reff = no2;
			
			String path = "cetak/"+nomor+"/"+reff;
			if(nomor.equals("xx111")){
				String bodyrequest = "{\n\"nomor_registrasi\" : \""+nomor+"\",\n\"nomor_referensi\" : \""+reff+"\"}";
				ObjectMapper mapper = new ObjectMapper();
				JsonNode root = mapper.readTree(bodyrequest);
				resCall = root;
			} else {
				resCall = callUrl(nomor,path,"GET");
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resCall;
	}

	public Object infonoreg(String no) throws Exception{
		Object resCall = null;
		try {

			String nomor = no;
			
			String path = "transaksi/jamaah/"+nomor;
			if(nomor.equals("xx111")){
				String bodyrequest = "{\n\"nomor_registrasi\" : \""+nomor+"\"}";
				ObjectMapper mapper = new ObjectMapper();
				JsonNode root = mapper.readTree(bodyrequest);
				resCall = root;
			} else {
				resCall = callUrl(nomor,path,"GET");
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resCall;
	}

	public Object infocabang(String kd, String tg1, String tg2, String pg) throws Exception{
		Object resCall = null;
		try {

			String kode = kd;
			String tgl1 = tg1;
			String tgl2 = tg2;
			String page = pg;
			
			String path = "transaksi/cabang/"+kode+"/"+tgl1+"/"+tgl2+"/"+pg;
			if(kode.equals("xx111")){
				String bodyrequest = "{\n\"kd_cabang\" : \""+kode+"\",\n\"tgl_awal\" : \""+tgl1+"\",\n\"tgl_akhir\" : \""+tgl2+"\",\n\"page\" : \""+page+"\"}";
				ObjectMapper mapper = new ObjectMapper();
				JsonNode root = mapper.readTree(bodyrequest);
				resCall = root;
			} else {
				resCall = callUrl(kode,path,"GET");
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resCall;
	}
	
	public Object infoperiode(String tg1, String tg2, String pg) throws Exception{
		Object resCall = null;
		try {

			String tgl1 = tg1;
			String tgl2 = tg2;
			String page = pg;
			
			String path = "transaksi/"+tgl1+"/"+tgl2+"/"+pg;
			if(tgl1.equals("xx111")){
				String bodyrequest = "{\n\"tgl_awal\" : \""+tgl1+"\",\n\"tgl_akhir\" : \""+tgl2+"\",\n\"page\" : \""+page+"\"}";
				ObjectMapper mapper = new ObjectMapper();
				JsonNode root = mapper.readTree(bodyrequest);
				resCall = root;
			} else {
				resCall = callUrl(tgl1,path,"GET");
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resCall;
	}
	
	
	private  Object callUrl(String data1,String subPath,String type) throws Exception{
		log.info("callUrl request "+subPath+": "+ data1);
		String access_token  = getToken();
		log.info("callUrl access_token :"+access_token);
		URL url = new URL(config.getBaseUrl()+subPath);
		log.info("callUrl url 1 :"+url);
		try {
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			log.info("conn :" + conn);
			conn.setRequestProperty("x-access-key", access_token);				


			if(type.equals("POST")) {
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
				conn.setRequestProperty("Content-Type", "application/json");

				if(data1!=null){
					OutputStream os = conn.getOutputStream();
					os.write(data1.getBytes());
					os.flush();
				}
				
			}
			if (conn.getResponseCode() != 200) {
				ErrorResponse errRes = new ErrorResponse();
				String responseCode = String.valueOf(conn.getResponseCode());
				errRes.setCode(responseCode);
				log.info("error responseCode :"+ responseCode);
				BufferedReader br = new BufferedReader(new InputStreamReader(
						(conn.getErrorStream())));
				ObjectMapper mapper = new ObjectMapper();
				JsonNode root = mapper.readTree(br);
				errRes.setError(root);

				return errRes;

			}else {
				BufferedReader br = new BufferedReader(new InputStreamReader(
						(conn.getInputStream())));
				log.info("resp 200 :"+ br);
				ObjectMapper mapper = new ObjectMapper();
				JsonNode root = mapper.readTree(br);

				return root;
			}
		} catch (Exception e) {
			String strErrMsg = e.toString();
			log.info("error strErrMsg :"+ strErrMsg);
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

