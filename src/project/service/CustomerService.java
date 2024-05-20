package project.service;

import java.util.List;
import java.util.Optional;

import project.dto.CreateCustomerDto;
import project.dto.CustomerDto;
import project.dto.UpdateCustomerDto;

public interface CustomerService {
	boolean isCustomerExists(long id);
	
	Optional<CustomerDto> getCustomerById(Long id);

	Optional<CustomerDto> getCustomerByUsername(String username);
	
	List<CustomerDto> getCustomers();

	CustomerDto createCustomer(CreateCustomerDto createCustomerDto);

	CustomerDto updateCustomer(UpdateCustomerDto updateCustomerDto);
}
