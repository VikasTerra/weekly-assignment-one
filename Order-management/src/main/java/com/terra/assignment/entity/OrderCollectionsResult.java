package com.terra.assignment.entity;

import java.util.List;


public class OrderCollectionsResult extends Result {
	private List<Order> listOfOrders;

	public List<Order> getListOfOrders() {
		return listOfOrders;
	}

	public void setListOfOrders(List<Order> listOfOrders) {
		this.listOfOrders = listOfOrders;
	}
}
