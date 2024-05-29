package project.service;

import project.dao.CustomerDao;
import project.dao.entity.Customer;
import project.dto.CreateCustomerDto;
import project.dto.CustomerDto;
import project.dto.UpdateCustomerDto;

import java.util.List;
import java.util.Optional;

public class CustomerService {
	private final CustomerDao customerDao;
	
	public CustomerService() {
		this.customerDao = new CustomerDao();
	}

	public boolean isCustomerExists(long id) {
		return this.customerDao.isCustomerExists(id);
	}

	public Optional<CustomerDto> getCustomerById(Long id) {
		return this.customerDao.getCustomerById(id)
				.map(this::mapToCustomerDto);
	}

	public Optional<CustomerDto> getCustomerByUsername(String username) {
		return this.customerDao.getCustomerByUsername(username)
				.map(this::mapToCustomerDto);
	}

	public List<CustomerDto> getCustomers() {
		return this.customerDao.getCustomers().stream()
				.map(this::mapToCustomerDto)
				.toList();
	}

	public CustomerDto createCustomer(CreateCustomerDto createCustomerDto) {
		Customer createdCustomer = this.customerDao.createCustomer(createCustomerDto);
		
		return this.mapToCustomerDto(createdCustomer);
	}

	public CustomerDto updateCustomer(UpdateCustomerDto updateCustomerDto) {
		Customer updatedCustomer = this.customerDao.updateCustomer(updateCustomerDto);
		
		return this.mapToCustomerDto(updatedCustomer);
	}

	private CustomerDto mapToCustomerDto(Customer customer) {
		return new CustomerDto(
				customer.id(), 
				customer.username(), 
				customer.firstName(), 
				customer.lastName(), 
				customer.contactNumber(), 
				customer.emailAddress(), 
				customer.createdAt(), 
				customer.updatedAt()
			);
	}
}
