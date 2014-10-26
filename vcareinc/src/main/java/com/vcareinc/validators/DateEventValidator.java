package com.vcareinc.validators;

import java.lang.reflect.InvocationTargetException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.vcareinc.constants.DateRange;
import com.vcareinc.constants.MonthOfYear;
import com.vcareinc.controllers.ConversionManagedBean;
import com.vcareinc.utils.DateUtils;


public class DateEventValidator implements ConstraintValidator<DateEvent, Object> {

	String message;
	String startDate;
	String endDate;
	String recurring;
	String period;
	String precision;
	String day;
	String month;
	String[] dayOfWeekcb;
	String[] weekOfMonth;
	String month2;
	String eventPeriod;
	String untilDate;

	@Autowired
	private ConversionManagedBean conversionManagedBean;

	@Override
	public void initialize(DateEvent params) {
		message = params.message();
		startDate = params.startDate();
		endDate = params.endDate();
		recurring = params.recurring();
		period = params.period();
		precision = params.precision();
		day = params.day();
		month = params.month();
		month2 = params.month2();
		dayOfWeekcb = params.dayOfWeekcb();
		weekOfMonth = params.weekOfMonth();
		eventPeriod = params.eventPeriod();
		untilDate = params.untilDate();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext  ctx) {
		try {
			String startDt = BeanUtils.getProperty(value, startDate);
			String endDt = BeanUtils.getProperty(value, endDate);
			String prd = BeanUtils.getProperty(value, period);
			String rec = BeanUtils.getProperty(value, recurring);
			String prc = BeanUtils.getProperty(value, precision);
			String dy = BeanUtils.getProperty(value, day);
			String mth = BeanUtils.getProperty(value, month);
			String mth2 = BeanUtils.getProperty(value, month2);

			if(startDt == null || startDt.trim().length() <= 0) {
				ctx.buildConstraintViolationWithTemplate("Please enter valid start date").addConstraintViolation();
			} else if(!DateUtils.isValidDateString(startDt)) {
				ctx.buildConstraintViolationWithTemplate("Start Date format should be MM\\dd\\yyyy").addConstraintViolation();
			}

			Integer cntDyofWk = 0;
			for(String dyOfWk: dayOfWeekcb) {
				String dow = BeanUtils.getProperty(value, dyOfWk);
				if(dow != null && dow.length() > 0)
					cntDyofWk++;
			}

			Integer cntWkOfMth = 0;
			for(String wkOfMth: weekOfMonth) {
				String wom = BeanUtils.getProperty(value, wkOfMth);
				if(wom != null && wom.length() > 0)
					cntWkOfMth++;
			}
			String evtPrd = BeanUtils.getProperty(value, eventPeriod);
			String unDt = BeanUtils.getProperty(value, untilDate);

			if(rec != null && Boolean.valueOf(rec)) {
				if(eventPeriod != null && eventPeriod.trim().length() > 0) {
					if(eventPeriod.trim().equalsIgnoreCase("until")) {
						if(untilDate == null || untilDate.trim().length() <= 0)
							ctx.buildConstraintViolationWithTemplate("Until Date is required").addConstraintViolation();
						else if(!DateUtils.isValidDateString(untilDate))
							ctx.buildConstraintViolationWithTemplate("Invalid Until Date").addConstraintViolation();
					}
				}

				if(prd != null && prd.trim().length() > 0) {
					if(prd.equalsIgnoreCase(DateRange.MONTHLY.toString()) || prd.equalsIgnoreCase(DateRange.WEEKLY.toString()) || prd.equalsIgnoreCase(DateRange.YEARLY.toString())) {
						if(prc == null || prc.trim().length() <= 0) {
							ctx.buildConstraintViolationWithTemplate("Please select precision").addConstraintViolation();
						} else if(prc.equals("weekday")) {
							if(cntDyofWk <= 0)
								ctx.buildConstraintViolationWithTemplate("Day of the Week Required").addConstraintViolation();
						}

					}

					if(prd.equalsIgnoreCase(DateRange.MONTHLY.toString()) || prd.equalsIgnoreCase(DateRange.YEARLY.toString())) {
						if(prc != null && prc.equals("weekday")) {
							if(cntWkOfMth <=0) {
								ctx.buildConstraintViolationWithTemplate("Week of the Month Required").addConstraintViolation();
							}
						} else if(prc.equals("day")) {
							if(dy == null || dy.trim().length() <= 0) {
								ctx.buildConstraintViolationWithTemplate("Day required").addConstraintViolation();
							} else if(!dy.matches("^[1-7]")) {
								ctx.buildConstraintViolationWithTemplate("Day must be between 1 and 7").addConstraintViolation();
							}
						}
					}

					if(prd.equalsIgnoreCase(DateRange.YEARLY.toString())) {
						if((mth == null || mth.trim().length() <= 0) && (mth2 == null || mth2.trim().length() <=0)) {
							ctx.buildConstraintViolationWithTemplate("Month is required").addConstraintViolation();
						}

						if(MonthOfYear.valueOf(mth) == null && MonthOfYear.valueOf(mth2) == null) {
							ctx.buildConstraintViolationWithTemplate("Month is required").addConstraintViolation();
						}
					}

					if(evtPrd == null || evtPrd.trim().length() <= 0) {
						ctx.buildConstraintViolationWithTemplate("Please select end on").addConstraintViolation();
					} else if(evtPrd.trim().equalsIgnoreCase("until")) {
						if(unDt == null || unDt.trim().length() <= 0) {
							ctx.buildConstraintViolationWithTemplate("Please select until Date").addConstraintViolation();
						} else if(!DateUtils.isValidDateString(unDt)) {
							ctx.buildConstraintViolationWithTemplate("Until Date format should be MM\\dd\\yyyy").addConstraintViolation();
						}
					}
				}
			} else {
				if(endDt == null || endDt.trim().length() <= 0) {
					ctx.buildConstraintViolationWithTemplate("Please enter valid end date").addConstraintViolation();
				} else if(!DateUtils.isValidDateString(endDt)) {
					ctx.buildConstraintViolationWithTemplate("End Date format should be MM\\dd\\yyyy").addConstraintViolation();
				}
			}
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
