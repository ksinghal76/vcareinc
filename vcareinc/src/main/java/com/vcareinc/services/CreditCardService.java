package com.vcareinc.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.paypal.core.rest.OAuthTokenCredential;
import com.paypal.core.rest.PayPalRESTException;
import com.vcareinc.exceptions.CommonException;

public class CreditCardService extends BaseService {
	
	@Autowired
	private OAuthTokenCredential oAuthCreditCardTest;
	
	@Autowired
	private OAuthTokenCredential oAuthCreditCard;

	public OAuthTokenCredential getoAuthCreditCardTest() {
		return oAuthCreditCardTest;
	}

	public void setoAuthCreditCardTest(OAuthTokenCredential oAuthCreditCardTest) {
		this.oAuthCreditCardTest = oAuthCreditCardTest;
	}

	public OAuthTokenCredential getoAuthCreditCard() {
		return oAuthCreditCard;
	}

	public void setoAuthCreditCard(OAuthTokenCredential oAuthCreditCard) {
		this.oAuthCreditCard = oAuthCreditCard;
	}
	
	private String getAccessToken() throws CommonException {
		String accessToken = null;
		try {
			accessToken = oAuthCreditCardTest.getAccessToken();
		} catch (PayPalRESTException e) {
			throw new CommonException(e);
		}
		return accessToken;
	}
	
	
}
