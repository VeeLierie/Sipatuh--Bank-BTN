package id.co.hanoman.sipatuh.model;

import javax.validation.constraints.NotNull;

public class InquiryPembayaran {
	@NotNull
	private
	String noregistrasi ;

	String getNoregistrasi() {
		return noregistrasi;
	}

	void setNoregistrasi(String noregistrasi) {
		this.noregistrasi = noregistrasi;
	}



	
}
