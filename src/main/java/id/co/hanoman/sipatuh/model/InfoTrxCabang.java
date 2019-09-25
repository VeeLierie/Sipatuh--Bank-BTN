package id.co.hanoman.sipatuh.model;

import javax.validation.constraints.NotNull;

public class InfoTrxCabang {

	@NotNull
	String kd_cabang;
	
	@NotNull
	String tgl_awal	;

	@NotNull
	String tgl_akhir;
	
	@NotNull
	String page;

	public String getKd_cabang() {
		return kd_cabang;
	}

	public void setKd_cabang(String kd_cabang) {
		this.kd_cabang = kd_cabang;
	}

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
