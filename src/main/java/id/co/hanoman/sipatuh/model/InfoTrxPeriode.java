package id.co.hanoman.sipatuh.model;

import javax.validation.constraints.NotNull;

public class InfoTrxPeriode {

	@NotNull
	String tgl_awal;
	
	@NotNull
	String tgl_akhir;
	
	@NotNull
	String page;

	public String getTgl_awal() {
		return tgl_awal;
	}

	public void setTgl_awal(String tgl_awal) {
		this.tgl_awal = tgl_awal;
	}

	public String getTgl_akhir() {
		return tgl_akhir;
	}

	public void setTgl_akhir(String tgl_akhir) {
		this.tgl_akhir = tgl_akhir;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}
	
}
