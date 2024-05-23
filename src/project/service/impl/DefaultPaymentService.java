package project.service.impl;

import project.dao.PaymentDao;
import project.dao.impl.DefaultPaymentDao;
import project.dto.CreatePaymentDto;
import project.service.PaymentService;

public class DefaultPaymentService implements PaymentService {
	private final PaymentDao paymentDao;

	public DefaultPaymentService() {
		this.paymentDao = new DefaultPaymentDao();
	}

	@Override
	public Long createPayment(CreatePaymentDto createPaymentDto) {
		return this.paymentDao.createPayment(createPaymentDto);
	}

}
