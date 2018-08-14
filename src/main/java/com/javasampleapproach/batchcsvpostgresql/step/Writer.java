package com.javasampleapproach.batchcsvpostgresql.step;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import com.javasampleapproach.batchcsvpostgresql.dao.CustomerDao;
import com.javasampleapproach.batchcsvpostgresql.model.Customer;

public class Writer implements ItemWriter<Customer> {

	private static final Logger log = LoggerFactory.getLogger(Writer.class);
	private final CustomerDao customerDao;

	public Writer(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	public void write(List<? extends Customer> customers) throws Exception {
		List<Customer> existingcustomers = customerDao.loadAllCustomers();
		log.info("existingcustomers size "+existingcustomers.size());
		if(existingcustomers.isEmpty())
		{
			
			customerDao.insert(customers);
		}else {
			List<Customer> insertList = new ArrayList<>();
			List<Customer> updateList = new ArrayList<>();
			
			for (Customer customer : customers) {
				boolean found = false;
				for (Customer existingcustomer : existingcustomers) {
					if(customer.getProj_id().intValue()==existingcustomer.getProj_id().intValue())
					{
						found= true;
					}
				}
				if(found)
				{
					updateList.add(customer);
				}else{
					insertList.add(customer);
				}
			}
			log.info("updateList size "+updateList.size());
			log.info("insertList size "+insertList.size());
			if(!insertList.isEmpty()){
				customerDao.insert(insertList);
			}
				if(!updateList.isEmpty()){
					customerDao.update(updateList);
			}
		}
	}

}
