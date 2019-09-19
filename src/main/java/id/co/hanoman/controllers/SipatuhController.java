package id.co.hanoman.controllers;

import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import id.co.hanoman.sipatuh.model.ErrorResponse;
import id.co.hanoman.sipatuh.model.Response;
import id.co.hanoman.sipatuh.model.LoginSipatuh;
import id.co.hanoman.sipatuh.model.InquiryPembayaran;
import id.co.hanoman.sipatuh.model.TrxPembayaran;
import id.co.hanoman.sipatuh.model.CetakBuktiTransaksi;
import id.co.hanoman.sipatuh.model.InfoTrxCabang;
import id.co.hanoman.sipatuh.model.InfoTrxNoRegis;
import id.co.hanoman.sipatuh.model.InfoTrxPeriode;
import id.co.hanoman.sipatuh.util.NetClientSipatuh;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Api(value="sipatuh", description="Gateway for sipatuh api")
@RestController
@RequestMapping("/sipatuh")

public class SipatuhController {

	@Autowired
	NetClientSipatuh netclientsipatuh;
	
	static Logger log = LoggerFactory.getLogger(NetClientSipatuh.class);	 
	@ApiOperation(value = "Pembayaran Sipatuh",response = Iterable.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
			)	

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> login(@RequestBody LoginSipatuh req){
		Object res = null;
		try {
			log.info("request login : "+getJson(req));
			res = netclientsipatuh.login(req);
			log.info("response login : "+getJson(res));
		} catch (Exception e) {
			log.error("login",e);
		}		
		return ResponseEntity.ok(res);
	}	
	
	
	@RequestMapping(value = "/pembayaran", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> pembayaran(@RequestBody TrxPembayaran req){
		Object res = null;
		try {
			log.info("request Pembayaran : "+getJson(req));
			res = netclientsipatuh.pembayaran(req);
			log.info("response Pembayaran : "+getJson(res));
		} catch (Exception e) {
			log.error("pembayaran",e);
		}		
		return ResponseEntity.ok(res);
	}
	
	
	public JsonNode getJson(Object obj){
		ObjectMapper mapper = new ObjectMapper();
		JsonNode reqJson = mapper.valueToTree(obj);
		return reqJson;
	}
	
}
