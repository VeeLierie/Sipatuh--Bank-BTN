package id.co.hanoman.sipatuh.model;

import javax.validation.constraints.NotNull;

public class CetakBuktiTransaksi{
	
	@NotNull
	String nomor_registrasi;
	
	@NotNull
	String nomor_referensi;

	public String getNomor_registrasi() {
		return nomor_registrasi;
	}

	public void setNomor_registrasi(String nomor_registrasi) {
		this.nomor_registrasi = nomor_registrasi;
	}

	public String getNomor_referensi() {
		return nomor_referensi;
	}

	public void setNomor_referensi(String nomor_referensi) {
		this.nomor_referensi = nomor_referensi;
	}
	
}
