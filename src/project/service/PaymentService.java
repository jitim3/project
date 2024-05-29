package project.service;

import project.dao.PaymentDao;
import project.dto.CreatePaymentDto;

public class PaymentService {
    private final PaymentDao paymentDao;

    public PaymentService() {
        this.paymentDao = new PaymentDao();
    }

    public Long createPayment(CreatePaymentDto createPaymentDto) {
        return this.paymentDao.createPayment(createPaymentDto);
    }

}
