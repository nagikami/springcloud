package com.nagi.springcloud.service.impl;

import com.nagi.springcloud.entities.Payment;
import org.springframework.stereotype.Service;
import com.nagi.springcloud.dao.PaymentDao;
import com.nagi.springcloud.service.PaymentService;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
