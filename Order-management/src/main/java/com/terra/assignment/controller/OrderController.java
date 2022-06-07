package com.terra.assignment.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.terra.assignment.entity.Order;
import com.terra.assignment.entity.OrderCollectionsResult;
import com.terra.assignment.entity.OrderResult;
import com.terra.assignment.entity.Result;
import com.terra.assignment.service.OrderService;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
	@Autowired
	OrderService orderService;

	@PostMapping
	public Result createStore(@Valid @RequestBody Order order) {
		return orderService.saveOrder(order);
	}

	@GetMapping("pincode/{pinCode}")
	public OrderCollectionsResult getOrdersByPincode(@PathVariable int pinCode) {
		return orderService.getOrdersByPincode(pinCode);
	}

	@GetMapping("/{id}")
	public OrderResult getOrdersByPincode(@PathVariable String id) {
		return orderService.getOrdersById(id);
	}
}