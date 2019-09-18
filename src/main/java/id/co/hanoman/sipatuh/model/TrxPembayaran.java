package id.co.hanoman.sipatuh.model;

import javax.validation.constraints.NotNull;

public class TrxPembayaran {
	@NotNull
	String nomor_registrasi;

	@NotNull
	String kd_cabang;
	
	@NotNull
	String tgl_bayar;

	@NotNull
	String nominal_ppiu;

	@NotNull
	String nominal_asuransi;
	
	@NotNull
	String nominal_rek_ppiu;
	
	@NotNull
	String nominal_rek_asuransi;

	@NotNull
	private
	String nama_rek_ppiu;
	
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

	public String getNominal_rek_asuransi() {
		return nominal_rek_asuransi;
	}

	public void setNominal_rek_asuransi(String nominal_rek_asuransi) {
		this.nominal_rek_asuransi = nominal_rek_asuransi;
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

	public String getNominal_ppiu() {
		return nominal_ppiu;
	}

	public void setNominal_ppiu(String nominal_ppiu) {
		this.nominal_ppiu = nominal_ppiu;
	}

	public String getNominal_rek_ppiu() {
		return nominal_rek_ppiu;
	}

	public void setNominal_rek_ppiu(String nominal_rek_ppiu) {
		this.nominal_rek_ppiu = nominal_rek_ppiu;
	}

	public String getNama_rek_ppiu() {
		return nama_rek_ppiu;
	}

	public void setNama_rek_ppiu(String nama_rek_ppiu) {
		this.nama_rek_ppiu = nama_rek_ppiu;
	}


}
