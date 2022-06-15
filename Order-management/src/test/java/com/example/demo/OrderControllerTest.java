package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.terra.assignment.controller.OrderController;
import com.terra.assignment.entity.Address;
import com.terra.assignment.entity.ErrorMessages;
import com.terra.assignment.entity.Item;
import com.terra.assignment.entity.Order;
import com.terra.assignment.entity.OrderCollectionsResult;
import com.terra.assignment.entity.OrderLine;
import com.terra.assignment.entity.OrderLineStatus;
import com.terra.assignment.entity.OrderResult;
import com.terra.assignment.entity.OrderStatus;
import com.terra.assignment.entity.Result;
import com.terra.assignment.service.OrderService;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {
	
	@InjectMocks
	OrderController controller;
	
	@Mock
	OrderService orderService;
	
	@Test
	void testCreateOrder() {
		List<Item> itmList = new ArrayList<>();
		List<Address> addList = new ArrayList<Address>();
		Item item = new Item();
		item.setItemId("1");
		item.setItemName("iphone");
		item.setPrice(150);
		item.setQuantity("7");
		itmList.add(item);
		Address address = new Address();
		address.setCity("Banglore");
		address.setCountry("India");
		address.setPinCode(23456);
		addList.add(address);
		OrderLine orderLine = new OrderLine();
		orderLine.setEta(new Date());
		orderLine.setOrderLineStatus(OrderLineStatus.OPEN);
		orderLine.setListOfAddresses(addList);
		orderLine.setListOfItems(itmList);
		Order order = new Order();
		order.setTotalAmount(150);
		order.setOrderStatus(OrderStatus.OPEN);
		order.setOrderDate(new Date());
		when(orderService.saveOrder(order)).thenReturn(1);
		int result = controller.createStore(order);
		assertEquals(1, result);
	}
	
	@Test
	void testGetOrderByPinCode() {
		OrderCollectionsResult result = new OrderCollectionsResult();
		result.setErrorCode(200);
		result.setErrorMessage(ErrorMessages.RETRIEVE_SUCCESSFUL);
		when(orderService.getOrdersByPincode(23456)).thenReturn(result);
		OrderCollectionsResult result1 = controller.getOrdersByPincode(23456);
		assertEquals(result, result1);
	}
	@Test
	void testGetOrderById() {
		OrderResult orderResult = new OrderResult();
		orderResult.setErrorCode(200);
		orderResult.setErrorMessage(ErrorMessages.RETRIEVE_SUCCESSFUL);
		when(orderService.getOrdersById("1")).thenReturn(orderResult);
		OrderResult result1 = controller.getOrdersById("1");
		assertEquals(orderResult, result1);
	}
	@Test
	public void testgetOrdersByPincodeNotFound() {
		when(orderService.getOrdersByPincode(23456)).thenReturn(new OrderCollectionsResult());
		OrderCollectionsResult result = controller.getOrdersByPincode(23456);
		verify(orderService).getOrdersByPincode(23456);
		assertNotNull(result);
	}

	@Test
	public void getOrdersByIdTestNotNullCheck() {
		String id = "1";
		when(orderService.getOrdersById(id)).thenReturn(new OrderResult());
		OrderResult result = controller.getOrdersById(id);
		verify(orderService).getOrdersById(id);
		assertNotNull(result);
	}

}
