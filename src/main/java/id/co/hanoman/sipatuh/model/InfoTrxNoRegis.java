package id.co.hanoman.sipatuh.model;

import javax.validation.constraints.NotNull;

public class InfoTrxNoRegis {

	@NotNull
	private
	String nomor_registrasi;

	public String getNomor_registrasi() {
		return nomor_registrasi;
	}

	public void setNomor_registrasi(String nomor_registrasi) {
		this.nomor_registrasi = nomor_registrasi;
	}
	
}
