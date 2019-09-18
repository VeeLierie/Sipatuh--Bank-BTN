package id.co.hanoman.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("sipatuh")

public class YAMLConfig {
  
	private String xkey;
	
	private String userid;
	private String password;

	private String urlRest;
	private String urlToken;

	private int timeout;
	private String baseUrl;

	public String getXkey() {
		return xkey;
	}

	public void setXkey(String xkey) {
		this.xkey = xkey;
	}


	public String getUrlRest() {
		return urlRest;
	}

	public void setUrlRest(String urlRest) {
		this.urlRest = urlRest;
	}

	public String getUrlToken() {
		return urlToken;
	}

	public void setUrlToken(String urlToken) {
		this.urlToken = urlToken;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
 
	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}