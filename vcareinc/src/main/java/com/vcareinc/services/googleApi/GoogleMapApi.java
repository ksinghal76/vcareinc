package com.vcareinc.services.googleApi;

import java.io.IOException;
import java.security.InvalidKeyException;

import org.springframework.beans.factory.annotation.Value;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;

public class GoogleMapApi {

	@Value("${google.api.key}")
	private String googleKey;

	@Value("${google.geocode.url}")
	private String geoCodeUrl;

	@Value("${google.output.type}")
	private String outputType;

	public GeocodeResponse getGoogleCode(String address) {
		GeocodeResponse response = null;
		try {
			final Geocoder geocoder = new Geocoder("clientId", googleKey);
			GeocoderRequest request = new GeocoderRequestBuilder().setAddress(address).setLanguage("en").getGeocoderRequest();
			response = geocoder.geocode(request);
		} catch (InvalidKeyException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	/**
	 * @return the googleKey
	 */
	public String getGoogleKey() {
		return googleKey;
	}

	/**
	 * @param googleKey the googleKey to set
	 */
	public void setGoogleKey(String googleKey) {
		this.googleKey = googleKey;
	}

	/**
	 * @return the geoCodeUrl
	 */
	public String getGeoCodeUrl() {
		return geoCodeUrl;
	}

	/**
	 * @param geoCodeUrl the geoCodeUrl to set
	 */
	public void setGeoCodeUrl(String geoCodeUrl) {
		this.geoCodeUrl = geoCodeUrl;
	}

	/**
	 * @return the outputType
	 */
	public String getOutputType() {
		return outputType;
	}

	/**
	 * @param outputType the outputType to set
	 */
	public void setOutputType(String outputType) {
		this.outputType = outputType;
	}
}
