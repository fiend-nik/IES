package com.nt.service;

import jakarta.mail.MessagingException;

public interface IPurchaseOrder {

	public String purchase(String[] items,double[] price, String[] emails) throws MessagingException;
}
