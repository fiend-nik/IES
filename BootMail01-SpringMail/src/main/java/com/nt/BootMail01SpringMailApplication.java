package com.nt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import com.nt.service.IPurchaseOrder;

@SpringBootApplication
public class BootMail01SpringMailApplication {

	public static void main(String[] args) {
		ApplicationContext ctx =  SpringApplication.run(BootMail01SpringMailApplication.class, args);
		
		IPurchaseOrder order = ctx.getBean("purchaseOrder",IPurchaseOrder.class);
		
		try {
			String msg = order.purchase(new String[] {"shirt","trouser","watch"},
										new double[] {5904,8989,2332},
										new String[] {"vishwakarmam04@gmail.com","niksharma072@gmail.com"});
		
			System.out.println(msg);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		((ConfigurableApplicationContext)ctx).close();
	}

}
