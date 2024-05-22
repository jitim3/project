package project.service;

import project.dto.CreatePaymentDto;

public interface PaymentService {
	Long createPayment(CreatePaymentDto createPaymentDto);
}
