package id.co.hanoman.sipatuh.model;

import javax.validation.constraints.NotNull;

public class TrxPembayaran2 {
	@NotNull
	String nomor_registrasi;

	@NotNull
	String kd_cabang;
	
	@NotNull
	String tgl_bayar;

	@NotNull
	String nominal_asuransi;
	
	@NotNull
	String nomor_rek_asuransi;
	
	@NotNull
	String nama_rek_asuransi;
	
	@NotNull
	String kd_channel;

	@NotNull
	String nomor_referensi;


	public String getNomor_registrasi() {
		return nomor_registrasi;
	}

	public void setNomor_registrasi(String nomor_registrasi) {
		this.nomor_registrasi = nomor_registrasi;
	}

	public String getKd_cabang() {
		return kd_cabang;
	}

	public void setKd_cabang(String kd_cabang) {
		this.kd_cabang = kd_cabang;
	}

	public String getTgl_bayar() {
		return tgl_bayar;
	}

	public void setTgl_bayar(String tgl_bayar) {
		this.tgl_bayar = tgl_bayar;
	}

	public String getNominal_asuransi() {
		return nominal_asuransi;
	}

	public void setNominal_asuransi(String nominal_asuransi) {
		this.nominal_asuransi = nominal_asuransi;
	}

	public String getNomor_rek_asuransi() {
		return nomor_rek_asuransi;
	}

	public void setNomor_rek_asuransi(String nomor_rek_asuransi) {
		this.nomor_rek_asuransi = nomor_rek_asuransi;
	}

	public String getNama_rek_asuransi() {
		return nama_rek_asuransi;
	}

	public void setNama_rek_asuransi(String nama_rek_asuransi) {
		this.nama_rek_asuransi = nama_rek_asuransi;
	}

	public String getKd_channel() {
		return kd_channel;
	}

	public void setKd_channel(String kd_channel) {
		this.kd_channel = kd_channel;
	}

	public String getNomor_referensi() {
		return nomor_referensi;
	}

	public void setNomor_referensi(String nomor_referensi) {
		this.nomor_referensi = nomor_referensi;
	}

	
}
