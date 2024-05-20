package project.service.impl;

import java.util.List;
import java.util.Optional;

import project.dao.CustomerDao;
import project.dao.entity.Customer;
import project.dao.impl.DefaultCustomerDao;
import project.dto.CreateCustomerDto;
import project.dto.CustomerDto;
import project.dto.UpdateCustomerDto;
import project.service.CustomerService;

public class DefaultCustomerService implements CustomerService {
	private final CustomerDao customerDao;
	
	public DefaultCustomerService() {
		this.customerDao = new DefaultCustomerDao();
	}
	
	@Override
	public boolean isCustomerExists(long id) {
		return this.customerDao.isCustomerExists(id);
	}

	@Override
	public Optional<CustomerDto> getCustomerById(Long id) {
		return this.customerDao.getCustomerById(id)
				.map(this::mapToCustomerDto);
	}

	@Override
	public Optional<CustomerDto> getCustomerByUsername(String username) {
		return this.customerDao.getCustomerByUsername(username)
				.map(this::mapToCustomerDto);
	}

	@Override
	public List<CustomerDto> getCustomers() {
		return this.customerDao.getCustomers().stream()
				.map(this::mapToCustomerDto)
				.toList();
	}

	@Override
	public CustomerDto createCustomer(CreateCustomerDto createCustomerDto) {
		Customer createdCustomer = this.customerDao.createCustomer(createCustomerDto);
		
		return this.mapToCustomerDto(createdCustomer);
	}

	@Override
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
