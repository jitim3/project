package project.dao;

import project.dto.CreatePaymentDto;

public interface PaymentDao {
	Long createPayment(CreatePaymentDto createPaymentDto);
}
