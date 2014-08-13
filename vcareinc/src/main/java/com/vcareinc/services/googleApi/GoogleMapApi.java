package com.vcareinc.services.googleApi;

import java.io.IOException;
import java.security.InvalidKeyException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;

@Component
public class GoogleMapApi {

	@Value("#{googleApi['google.api.client.id']}")
	private String googleClientId;
	
	@Value("#{googleApi['google.api.client.key']}")
	private String googleClientKey;

	public GeocodeResponse getGoogleResponse(String address) {
		GeocodeResponse response = null;
		try {
			final Geocoder geocoder = new Geocoder(googleClientId, googleClientKey);
			GeocoderRequest request = new GeocoderRequestBuilder().setAddress(address).setLanguage("en").getGeocoderRequest();
			response = geocoder.geocode(request);
		} catch (InvalidKeyException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	public String getGoogleClientId() {
		return googleClientId;
	}

	public void setGoogleClientId(String googleClientId) {
		this.googleClientId = googleClientId;
	}

	/**
	 * @return the googleClientKey
	 */
	public String getGoogleClientKey() {
		return googleClientKey;
	}

	/**
	 * @param googleClientKey the googleKey to set
	 */
	public void setGoogleClientKey(String googleClientKey) {
		this.googleClientKey = googleClientKey;
	}
}
