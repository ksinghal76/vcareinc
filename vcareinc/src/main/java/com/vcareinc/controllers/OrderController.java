package com.vcareinc.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vcareinc.services.OrderService;
import com.vcareinc.vo.Price;

@Controller
public class OrderController extends BaseMultiActionController {

	@Autowired
	private OrderService orderService;

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	@RequestMapping("/advertiseController")
	public String initializeOrder(ModelMap modelMap) {
		Map<String, List<Price>> priceMap = orderService.getAllPriceByType();
		modelMap.addAttribute("priceMap", priceMap);
		return "advertise";
	}
}
