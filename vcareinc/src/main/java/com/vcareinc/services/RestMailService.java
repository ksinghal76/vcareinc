package com.vcareinc.services;

import java.net.URLEncoder;
import java.util.Map;

import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.vcareinc.models.BaseModel;
import com.vcareinc.models.ResultEmailJson;

@Controller
public class RestMailService<T extends BaseModel> extends BaseService {

	@Autowired
	private RestTemplate restTemplate;
//	
//	@Autowired
//	private HttpClientBuilder httpClient;
	
	@Autowired
	private UsernamePasswordCredentials credentials;
	
	@Autowired
	private VelocityEngine velocityEngine;
	
	private Map<String, Object> vmModel;
	private String vmFilename;
	private String url;
	private MultiValueMap<String, String> model;
	private Object resultType;
	
	public RestMailService() {
//		CredentialsProvider provider = new BasicCredentialsProvider();
//		
//		AuthScope scope = new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT, AuthScope.ANY_REALM);
//		
//		provider.setCredentials(scope, credentials);
//		
//		HttpClient httpClient2 = httpClient.setDefaultCredentialsProvider(provider).build();
//
//		ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient2);
		
//		restTemplate.setRequestFactory(requestFactory);
	}
	
	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

//	public HttpClientBuilder getHttpClient() {
//		return httpClient;
//	}
//
//	public void setHttpClient(HttpClientBuilder httpClient) {
//		this.httpClient = httpClient;
//	}

	public UsernamePasswordCredentials getCredentials() {
		return credentials;
	}

	public void setCredentials(UsernamePasswordCredentials credentials) {
		this.credentials = credentials;
	}

	public VelocityEngine getVelocityEngine() {
		return velocityEngine;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}
	
	public Map<String, Object> getVmModel() {
		return vmModel;
	}

	public void setVmModel(Map<String, Object> vmModel) {
		this.vmModel = vmModel;
	}

	public String getVmFilename() {
		return vmFilename;
	}

	public void setVmFilename(String vmFilename) {
		this.vmFilename = vmFilename;
	}

	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public MultiValueMap<String, String> getModel() {
		return model;
	}

	public void setModel(MultiValueMap<String, String> model) {
		this.model = model;
	}

	public Object getResultType() {
		return resultType;
	}

	public void setResultType(Object resultType) {
		this.resultType = resultType;
	}

	public ResultEmailJson sendMessageTemplate(String textField, String apiUsername, String apiPassword) {
		ResultEmailJson result = null;
		try {
			String text = URLEncoder.encode(VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, vmFilename, "utf-8", vmModel), "UTF-8");
			model.add(textField, text);
			model.add(apiUsername, credentials.getUserName());
			model.add(apiPassword, credentials.getPassword());
			result = restTemplate.postForObject(url, model, ResultEmailJson.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
