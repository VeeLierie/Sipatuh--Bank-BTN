package id.co.hanoman.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import id.co.hanoman.sipatuh.model.ChangePass;
import id.co.hanoman.sipatuh.model.TrxPembayaran;
import id.co.hanoman.sipatuh.model.TrxPembayaran2;
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
	
	@ApiOperation(value = "Ganti Password",response = Iterable.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
			)	
	@RequestMapping(value = "/password", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	
	public ResponseEntity<Object> changepass(@RequestBody ChangePass req){
		Object res = null;
		try {
			log.info("request ganti password : "+getJson(req));
			res = netclientsipatuh.changepass(req);
			log.info("response ganti password : "+getJson(res));
		} catch (Exception e) {
			log.error("ganti password",e);
		}		
		return ResponseEntity.ok(res);
	}
	
	@ApiOperation(value = "Pembayaran",response = Iterable.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
			)	
	@RequestMapping(value = "/pembayaran", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	
	public ResponseEntity<Object> pembayaran(@RequestBody TrxPembayaran req){
		Object res = null;
		try {
			log.info("request pembayaran : "+getJson(req));
			res = netclientsipatuh.pembayaran(req);
			log.info("response Pembayaran : "+getJson(res));
		} catch (Exception e) {
			log.error("pembayaran",e);
		}		
		return ResponseEntity.ok(res);
	}
	
	@ApiOperation(value = "Pembayaran Asuransi",response = Iterable.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
			)	
	@RequestMapping(value = "/pembayaran/asuransi", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	
	public ResponseEntity<Object> pembayaran_asuransi(@RequestBody TrxPembayaran2 req){
		Object res = null;
		try {
			log.info("request pembayaran_asuransi : "+getJson(req));
			res = netclientsipatuh.pembayaran_asuransi(req);
			log.info("response Pembayaran_asuransi : "+getJson(res));
		} catch (Exception e) {
			log.error("pembayaran_asuransi",e);
		}		
		return ResponseEntity.ok(res);
	}
	
	@ApiOperation(value = "Inquiry",response = Iterable.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
			)		
	@RequestMapping(value = "/inquiry", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    
	public ResponseEntity<Object> inquiry(@RequestParam(value="nomor_registrasi") String nomor) {
		Object res = null;
		try {
			log.info("request inq : "+getJson(nomor));
			res = netclientsipatuh.inquiry(nomor);
			log.info("response inq: "+getJson(res));
		} catch (Exception e) {
			log.error("inq",e);
		}		
		return ResponseEntity.ok(res);
	}      
	
	@ApiOperation(value = "Cetak Bukti Transaksi",response = Iterable.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
			)		
	@RequestMapping(value = "/cetak", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    
	public ResponseEntity<Object> cetak(
			@RequestParam(value="nomor_registrasi") String nomor
		    ,@RequestParam(value="nomor_referensi") String noref) {
		Object res = null;
		try {
			log.info("request cetak nomor : "+getJson(nomor));
			log.info("request cetak noref : "+getJson(noref));
			res = netclientsipatuh.cetak(nomor, noref);
			log.info("response cetak: "+getJson(res));
		} catch (Exception e) {
			log.error("cetak",e);
		}		
		return ResponseEntity.ok(res);
	}
    
	@ApiOperation(value = "Informasi Transaksi Nomor Registrasi",response = Iterable.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
			)		
	@RequestMapping(value = "/infonoreg", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    
	public ResponseEntity<Object> infonoreg(@RequestParam(value="nomor_registrasi") String nomor) {
		Object res = null;
		try {
			log.info("request infonoreg : "+getJson(nomor));
			res = netclientsipatuh.infonoreg(nomor);
			log.info("response infonoreg : "+getJson(res));
		} catch (Exception e) {
			log.error("infonoreg",e);
		}		
		return ResponseEntity.ok(res);
	}     
	
	@ApiOperation(value = "Informasi Transaksi Berdasarkan Cabang",response = Iterable.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
			)		
	@RequestMapping(value = "/infocabang", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    
	public ResponseEntity<Object> infocabang(
			@RequestParam(value="kd_cabang") String kode,
		    @RequestParam(value="tgl_awal") String tgl1,
		    @RequestParam(value="tgl_akhir") String tgl2,
		    @RequestParam(value="page") String page){
		Object res = null;
		try {
			log.info("request kode : "+getJson(kode));
			log.info("request tgl awal : "+getJson(tgl1));
			log.info("request tgl akhir : "+getJson(tgl2));
			log.info("request page : "+getJson(page));
			res = netclientsipatuh.infocabang(kode, tgl1, tgl2, page);
			log.info("response infocabang: "+getJson(res));
		} catch (Exception e) {
			log.error("infocabang",e);
		}		
		return ResponseEntity.ok(res);
	}
	
	@ApiOperation(value = "Informasi Transaksi Berdasarkan Periode",response = Iterable.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
			)		
	@RequestMapping(value = "/infoperiode", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    
	public ResponseEntity<Object> infoperiode(
		    @RequestParam(value="tgl_awal") String tgl1,
		    @RequestParam(value="tgl_akhir") String tgl2,
		    @RequestParam(value="page") String page){
		Object res = null;
		try {
			log.info("request tanggal awal : "+getJson(tgl1));
			log.info("request tanggal akhir : "+getJson(tgl2));
			log.info("request page : "+getJson(page));
			res = netclientsipatuh.infoperiode(tgl1, tgl2, page);
			log.info("response infoperiode : "+getJson(res));
		} catch (Exception e) {
			log.error("infoperiode",e);
		}		
		return ResponseEntity.ok(res);
	}
	
	
	public JsonNode getJson(Object obj){
		ObjectMapper mapper = new ObjectMapper();
		JsonNode reqJson = mapper.valueToTree(obj);
		return reqJson;
	}
	
}
