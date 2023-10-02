package com.nt.runners;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.nt.model.prod.Product;
import com.nt.model.promotions.Offers;
import com.nt.repo.prod.IProductRepo;
import com.nt.repo.promotions.IOffersRepo;

@Component
public class MultiDataSourceRunner implements CommandLineRunner {

	@Autowired
	private IProductRepo prodRepo;
	@Autowired
	private IOffersRepo offersRepo;
	
	@Override
	public void run(String... args) throws Exception {
		prodRepo.saveAll(Arrays.asList(new Product("table", 10000.0, 800.0),
										new Product("chair", 30049.0, 500.0),
										new Product("sofa", 56749.0, 787.0)										
							));
		offersRepo.saveAll(Arrays.asList(new Offers("diwaliOffer","D2WAli",40.0, LocalDateTime.of(2023,10,20,11,10)),
										new Offers("dusseraOffer","DSR",40.0, LocalDateTime.of(2023,10,20,11,10)),
										new Offers("ChatthOffer","D2Chatt",40.0, LocalDateTime.of(2023,10,20,11,10))
				
					));
		
		System.out.println("Product Saved");
		System.out.println("=========================================================");
		System.out.println("Offers Saved");
		System.out.println("=========================================================");
	
		System.out.println("===========================Display Products==============================");
		prodRepo.findAll().forEach(System.out::println);
		System.out.println("===========================Display Offers==============================");
		offersRepo.findAll().forEach(System.out::println);
	}

}
