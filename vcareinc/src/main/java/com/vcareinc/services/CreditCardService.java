package com.vcareinc.services;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.webflow.execution.RequestContext;

import com.paypal.api.payments.Address;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.AmountDetails;
import com.paypal.api.payments.CreditCard;
import com.paypal.api.payments.FundingInstrument;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.Transaction;
import com.paypal.core.rest.OAuthTokenCredential;
import com.paypal.core.rest.PayPalRESTException;
import com.vcareinc.constants.StatusType;
import com.vcareinc.exceptions.CommonException;
import com.vcareinc.exceptions.DBException;
import com.vcareinc.exceptions.ValidationException;
import com.vcareinc.models.BillingOrder;
import com.vcareinc.models.CreditCardModel;
import com.vcareinc.utils.DateUtils;
import com.vcareinc.vo.User;

@Controller
public class CreditCardService extends BaseService<CreditCardModel> {

	@Autowired
	private OAuthTokenCredential oAuthCreditCard;

	@Autowired
	private UserService userService;

	@Autowired
	private ListingService listingService;

	@Autowired
	private EventService eventService;

	@Autowired
	private ClassifiedService classifiedService;

	@Autowired
	private ArticleService articleService;

	public OAuthTokenCredential getoAuthCreditCard() {
		return oAuthCreditCard;
	}

	public void setoAuthCreditCard(OAuthTokenCredential oAuthCreditCard) {
		this.oAuthCreditCard = oAuthCreditCard;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * @return the listingService
	 */
	public ListingService getListingService() {
		return listingService;
	}

	/**
	 * @param listingService the listingService to set
	 */
	public void setListingService(ListingService listingService) {
		this.listingService = listingService;
	}

	/**
	 * @return the eventService
	 */
	public EventService getEventService() {
		return eventService;
	}

	/**
	 * @param eventService the eventService to set
	 */
	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

	/**
	 * @return the classifiedService
	 */
	public ClassifiedService getClassifiedService() {
		return classifiedService;
	}

	/**
	 * @param classifiedService the classifiedService to set
	 */
	public void setClassifiedService(ClassifiedService classifiedService) {
		this.classifiedService = classifiedService;
	}

	/**
	 * @return the articleService
	 */
	public ArticleService getArticleService() {
		return articleService;
	}

	/**
	 * @param articleService the articleService to set
	 */
	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}

	private String getAccessToken() throws CommonException {
		String accessToken = null;
		try {
			accessToken = oAuthCreditCard.getAccessToken();
		} catch (PayPalRESTException e) {
			throw new CommonException(e);
		}
		return accessToken;
	}

	@SuppressWarnings("unchecked")
	@Transactional(rollbackFor=DBException.class)
	public void saveCreditCard(RequestContext context, CreditCardModel creditCardModel) throws CommonException {
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getNativeRequest();
		User user = userService.getUserProfile(request);

		Float totalAmount = (Float) context.getFlowScope().get("totalAmount");
		BillingOrder billingOrder = (BillingOrder) context.getFlowScope().get("billingOrder");
		try {
			validate(creditCardModel);

			Address billingAddress = new Address();
			billingAddress.setLine1(creditCardModel.getAddress());
			billingAddress.setCity(creditCardModel.getCity());
			billingAddress.setState(creditCardModel.getState());
			billingAddress.setCountryCode(creditCardModel.getCountry());
			billingAddress.setPostalCode(creditCardModel.getZipCode());
			billingAddress.setPhone(creditCardModel.getPhone());

			CreditCard creditCard = new CreditCard();
			creditCard.setNumber(creditCardModel.getCardNumber());
			String creditCardType = getCreditCardType(creditCardModel.getCardNumber());
			if(creditCardType == null)
				throw new ValidationException("Invalid Credit Card");
			else
				creditCard.setType(creditCardType);
			creditCard.setExpireMonth(creditCardModel.getExpireDate().substring(0, 2));
			creditCard.setExpireYear(creditCardModel.getExpireDate().substring(3, creditCardModel.getExpireDate().length()));
			creditCard.setCvv2(creditCardModel.getCardCode());
			creditCard.setFirstName(creditCardModel.getFirstName());
			creditCard.setLastName(creditCardModel.getLastName());
			creditCard.setBillingAddress(billingAddress);

			AmountDetails amountDetails = new AmountDetails();
			amountDetails.setSubtotal(totalAmount.toString());

			Amount amount = new Amount();
			amount.setTotal(totalAmount.toString());
			amount.setCurrency("USD");
			amount.setDetails(amountDetails);

			Transaction transaction = new Transaction();
			transaction.setAmount(amount);
			transaction.setDescription("This is payment for BeautyPro411.com");

			List<Transaction> transactionLst = new ArrayList<Transaction>();
			transactionLst.add(transaction);

			FundingInstrument fundingInstrument =  new FundingInstrument();
			fundingInstrument.setCreditCard(creditCard);

			List<FundingInstrument> fundingInstrumentLst = new ArrayList<FundingInstrument>();
			fundingInstrumentLst.add(fundingInstrument);

			Payer payer = new Payer();
			payer.setFundingInstruments(fundingInstrumentLst);
			payer.setPaymentMethod("credit_card");

			Payment payment = new Payment();
			payment.setIntent("sale");
			payment.setPayer(payer);
			payment.setTransactions(transactionLst);

			Payment createdPayment = payment.create(getAccessToken());

			com.vcareinc.vo.CreditCard creditCardVo = new com.vcareinc.vo.CreditCard();
			creditCardVo.setPayerId(createdPayment.getId());
			creditCardVo.setUser(user);
			creditCardVo.setStatus(StatusType.ACTIVE);
			creditCardVo.setCreatedDate(DateUtils.getTimestamp(createdPayment.getCreateTime()));

			em.persist(creditCardVo);

			listingService.changeActiveStatusById(billingOrder.getListingCheck());
			eventService.changeActiveStatusById(billingOrder.getEventCheck());
			classifiedService.changeActiveStatusById(billingOrder.getClassifiedCheck());
			articleService.changeActiveStatusById(billingOrder.getArticleCheck());

			clearObject(creditCardModel);

		} catch (ValidationException | PayPalRESTException | ParseException e) {
			e.printStackTrace();
			creditCardModel.setErrorMsg(e.getMessage());
			throw new CommonException(e.getMessage());
		}
	}

	private String getCreditCardType(String creditCardNumber) {
		String type = null;
		if(creditCardNumber.substring(0,1).equals("3"))
			type = "amex";
		else if(creditCardNumber.substring(0,1).equals("4"))
			type = "visa";
		else if(creditCardNumber.substring(0,1).equals("5"))
			type = "mastercard";
		else if(creditCardNumber.substring(0,4).equals("6011"))
			type = "discover";
		return type;
	}

	@SuppressWarnings("unchecked")
	public List<com.vcareinc.vo.CreditCard> getCreditCardByUser(User user) {
		return em.createQuery("SELECT cc FROM CreditCard cc WHERE cc.user = :user")
				.setParameter("user", user)
				.getResultList();
	}

}
