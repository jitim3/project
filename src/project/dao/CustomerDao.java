package project.dao;

import java.util.List;
import java.util.Optional;

import project.dao.entity.Customer;
import project.dto.CreateCustomerDto;
import project.dto.UpdateCustomerDto;

public interface CustomerDao {
	boolean isCustomerExists(long id);
	
	Optional<Customer> getCustomerById(Long id);

	Optional<Customer> getCustomerByUsername(String username);
	
	List<Customer> getCustomers();

	Customer createCustomer(CreateCustomerDto createCustomerDto);

	Customer updateCustomer(UpdateCustomerDto updateCustomerDto);
}
