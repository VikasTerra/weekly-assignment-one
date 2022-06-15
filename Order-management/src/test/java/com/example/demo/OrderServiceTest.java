package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.terra.assignment.dao.OrderRepository;
import com.terra.assignment.entity.Address;
import com.terra.assignment.entity.ErrorMessages;
import com.terra.assignment.entity.Item;
import com.terra.assignment.entity.Order;
import com.terra.assignment.entity.OrderCollectionsResult;
import com.terra.assignment.entity.OrderLine;
import com.terra.assignment.entity.OrderLineStatus;
import com.terra.assignment.entity.OrderResult;
import com.terra.assignment.entity.OrderStatus;
import com.terra.assignment.service.OrderService;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
	
	@InjectMocks
	OrderService orderService;
	
	@Mock
	OrderRepository repository;
	
	@Test
	void testSaveOrder() {
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
		when(repository.save(order)).thenReturn(order);
		int result = orderService.saveOrder(order);
		assertEquals(1, result);
	}
	@Test
	void testGetOrderByPinCode() {
		List<Order> listOfOrders = new ArrayList<>();
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
		listOfOrders.add(order);
		OrderCollectionsResult result = new OrderCollectionsResult();
		result.setErrorCode(200);
		result.setErrorMessage(ErrorMessages.RETRIEVE_SUCCESSFUL);
		result.setListOfOrders(listOfOrders);
		when(repository.findByPinCode(23456)).thenReturn(listOfOrders);
		result = orderService.getOrdersByPincode(23456);
		assertEquals(result,result);
	}
	@Test
	void tesOrderByPinCodeNotFound() {
		List<Order> listOfOrders = new ArrayList<>();
		OrderCollectionsResult result = new OrderCollectionsResult();
		result.setErrorCode(404);
		result.setErrorMessage(ErrorMessages.NOT_FOUND);
		result.setListOfOrders(listOfOrders);
		when(repository.findByPinCode(12345)).thenReturn(listOfOrders);
		result = orderService.getOrdersByPincode(12345);
		assertEquals(result,result);
	}
	/*
	 * @Test void testGetOrderById() { OrderResult orderResult = new OrderResult();
	 * orderResult.setErrorCode(404);
	 * orderResult.setErrorMessage(ErrorMessages.NOT_FOUND);
	 * orderResult.setOrder(null);
	 * when(repository.findById("1")).thenReturn(result); OrderResult result1 =
	 * orderService.getOrdersById("1"); assertEquals(null, result1); }
	 */	 
}
