package org.mql.spring.billingservice;

import org.mql.spring.billingservice.dao.BillProjection;
import org.mql.spring.billingservice.dao.BillRepository;
import org.mql.spring.billingservice.dao.ProductItemRepository;
import org.mql.spring.billingservice.entities.Bill;
import org.mql.spring.billingservice.entities.Customer;
import org.mql.spring.billingservice.entities.Product;
import org.mql.spring.billingservice.entities.ProductItem;
import org.mql.spring.billingservice.service.CustomerService;
import org.mql.spring.billingservice.service.InventaryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.hateoas.PagedModel;

import java.util.Date;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner start(BillRepository billRepository,
								   RepositoryRestConfiguration restConfiguration,
								   ProductItemRepository productItemRepository,
								   CustomerService customerService,
								   InventaryService inventaryService){
		return args -> {
			restConfiguration.exposeIdsFor(Bill.class);
			Customer customer = customerService.findCustomerById(1L);
			System.out.println("***************");
			System.out.println(customer.getEmail());
			System.out.println(customer.getCustomerId());
			System.out.println(customer.getName());
			System.out.println("***************");
			restConfiguration.getProjectionConfiguration().addProjection(BillProjection.class);
			Bill bill1 = billRepository.save(new Bill( customer.getCustomerId(), new Date()));
			PagedModel<Product> productPagedModel = inventaryService.findAllProduct();
			productPagedModel.getContent().forEach(product -> {
				productItemRepository.save(new ProductItem(product.getProductId(),6,product.getPrice(),bill1));
				System.out.println(product.getName());
			});
			//Bill bill2 = billRepository.save(new Bill(null, 2L, new Date(1996, 07, 10), null));
			//Bill bill3 = billRepository.save(new Bill(null, 3L, new Date(), null));
//			Product product = inventaryService.findProductById(1L);
//			Product product1 = inventaryService.findProductById(2L);
//			Product product2 = inventaryService.findProductById(3L);
//			System.out.println("***************");
//			System.out.println(product.getProductId());
//			System.out.println(product.getName());
//			System.out.println(product.getPrice());
//			System.out.println("***************");
//			productItemRepository.save(new ProductItem(null,product.getProductId(),3,product.getPrice(),bill1));
//			productItemRepository.save(new ProductItem(null,product1.getProductId(),2,product1.getPrice(),bill1));
//			productItemRepository.save(new ProductItem(null,product2.getProductId(),6,product2.getPrice(),bill1));

		};

	}

}
