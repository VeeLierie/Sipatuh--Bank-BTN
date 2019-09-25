package id.co.hanoman.sipatuh.model;

import javax.validation.constraints.NotNull;

public class InquiryPembayaran {
	@NotNull

	String noregistrasi ;

	public String getNoregistrasi() {
		return noregistrasi;
	}

	void setNoregistrasi(String noregistrasi) {
		this.noregistrasi = noregistrasi;
	}
	
}
