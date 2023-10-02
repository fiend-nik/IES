package com.nt.service;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;


@Service("purchaseOrder")
public class PurchaseOrderImpl implements IPurchaseOrder{

	@Autowired
	private JavaMailSender sender;
	
	@Override
	public String purchase(String[] items, double[] price, String[] emails) throws MessagingException {

		double billAmt = 0.0;
		for(double p:price)
			billAmt += p;
		
		String msg = Arrays.toString(items)+" with prices "+Arrays.toString(price)+" with total bill Amount is "+billAmt;
		
		String status = sendMail(msg, emails);
		
		
		return msg+" "+status;
	}
	
	public String sendMail(String msg, String[] toEmails) throws MessagingException {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message,true);
		helper.setFrom("vishwakarmam750@gmail.com");
		helper.setCc(toEmails);
		helper.setSentDate(new Date());
		helper.setSubject("Srping mail test (application generated mail)");
		helper.setText(msg);
		helper.addAttachment("img.png", new ClassPathResource("img.png"));
		sender.send(message);
		return "";
	}
	

}
