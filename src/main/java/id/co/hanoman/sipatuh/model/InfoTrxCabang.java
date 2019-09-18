package id.co.hanoman.sipatuh.model;

import javax.validation.constraints.NotNull;

public class InfoTrxCabang {

	@NotNull
	String nomor_registrasi;
	String nomor_pasti_umrah;
	String bank_id;
	String nama;
	String ppiu;
	String asuransi;
	String paket;
	String nominal_asuransi;
	String nominal_ppiu;
	String nominal_total;
	String total_pembayaran;
	String tgl_bayar;
	String nomor_referensi;
	String kd_cabang;
	
	
	public String getNomor_registrasi() {
		return nomor_registrasi;
	}
	public void setNomor_registrasi(String nomor_registrasi) {
		this.nomor_registrasi = nomor_registrasi;
	}
	public String getNomor_pasti_umrah() {
		return nomor_pasti_umrah;
	}
	public void setNomor_pasti_umrah(String nomor_pasti_umrah) {
		this.nomor_pasti_umrah = nomor_pasti_umrah;
	}
	public String getBank_id() {
		return bank_id;
	}
	public void setBank_id(String bank_id) {
		this.bank_id = bank_id;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getPpiu() {
		return ppiu;
	}
	public void setPpiu(String ppiu) {
		this.ppiu = ppiu;
	}
	public String getAsuransi() {
		return asuransi;
	}
	public void setAsuransi(String asuransi) {
		this.asuransi = asuransi;
	}
	public String getPaket() {
		return paket;
	}
	public void setPaket(String paket) {
		this.paket = paket;
	}
	public String getNominal_asuransi() {
		return nominal_asuransi;
	}
	public void setNominal_asuransi(String nominal_asuransi) {
		this.nominal_asuransi = nominal_asuransi;
	}
	public String getNominal_ppiu() {
		return nominal_ppiu;
	}
	public void setNominal_ppiu(String nominal_ppiu) {
		this.nominal_ppiu = nominal_ppiu;
	}
	public String getTotal_pembayaran() {
		return total_pembayaran;
	}
	public void setTotal_pembayaran(String total_pembayaran) {
		this.total_pembayaran = total_pembayaran;
	}
	public String getTgl_bayar() {
		return tgl_bayar;
	}
	public void setTgl_bayar(String tgl_bayar) {
		this.tgl_bayar = tgl_bayar;
	}
	public String getNomor_referensi() {
		return nomor_referensi;
	}
	public void setNomor_referensi(String nomor_referensi) {
		this.nomor_referensi = nomor_referensi;
	}
	public String getKd_cabang() {
		return kd_cabang;
	}
	public void setKd_cabang(String kd_cabang) {
		this.kd_cabang = kd_cabang;
	}
	public String getNominal_total() {
		return nominal_total;
	}
	public void setNominal_total(String nominal_total) {
		this.nominal_total = nominal_total;
	}
}
