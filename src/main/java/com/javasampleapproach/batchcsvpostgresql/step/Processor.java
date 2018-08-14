package com.javasampleapproach.batchcsvpostgresql.step;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.javasampleapproach.batchcsvpostgresql.model.Customer;

public class Processor implements ItemProcessor<Customer, Customer> {

	private static final Logger log = LoggerFactory.getLogger(Processor.class);

	@Override
	public Customer process(Customer customer) throws Exception {
		Random r = new Random();
		
		final Integer proj_id = customer.getProj_id();
		final String oppName = customer.getOpprtunity_name().toUpperCase();
		final String asso_proj = customer.getAsso_proj().toUpperCase();
		final String description = customer.getDescri().toUpperCase();
		final String owner = customer.getOwner().toUpperCase();

		final Customer fixedCustomer = new Customer(proj_id, oppName, asso_proj, description, owner);

		log.info("Converting (" + customer + ") into (" + fixedCustomer + ")");

		return fixedCustomer;
	}
}
