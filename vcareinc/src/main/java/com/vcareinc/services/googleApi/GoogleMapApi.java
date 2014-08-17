package com.vcareinc.services.googleApi;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.vcareinc.exceptions.ValidationException;

@Component
public class GoogleMapApi {
//
//	@Value("644905138569-tep6pc4jam29km3a79062d80ie5upql6.apps.googleusercontent.com")
//	private String googleClientId;
//	
//	@Value("c6a29e6b58f326cee0ed3126d8bac5c9f2d0ed26")
//	private String googleClientKey;

	public GeocodeResponse getGoogleResponse(String address) throws ValidationException {
		GeocodeResponse response = null;
		try {
			final Geocoder geocoder = new Geocoder();
			GeocoderRequest request = new GeocoderRequestBuilder().setAddress(address).setLanguage("en").getGeocoderRequest();
			response = geocoder.geocode(request);
		} catch (IOException e) {
			throw new ValidationException(e);
		}
		return response;
	}

//	public String getGoogleClientId() {
//		return googleClientId;
//	}
//
//	public void setGoogleClientId(String googleClientId) {
//		this.googleClientId = googleClientId;
//	}
//
//	/**
//	 * @return the googleClientKey
//	 */
//	public String getGoogleClientKey() {
//		return googleClientKey;
//	}
//
//	/**
//	 * @param googleClientKey the googleKey to set
//	 */
//	public void setGoogleClientKey(String googleClientKey) {
//		this.googleClientKey = googleClientKey;
//	}
}
