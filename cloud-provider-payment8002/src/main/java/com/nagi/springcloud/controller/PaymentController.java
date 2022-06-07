package com.nagi.springcloud.controller;

import com.nagi.springcloud.entities.CommonResult;
import com.nagi.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import com.nagi.springcloud.service.PaymentService;
import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String port;

    @PostMapping("/payment/create")
    public CommonResult<Integer> create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("create result: " + result);
        if (result > 0) {
            return new CommonResult<>(200, "insert successfully " + port, result);
        } else {
            return new CommonResult<>(999, "insert failed");
        }
    }

    @GetMapping("/payment/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("get result: " + payment);
        if (Objects.nonNull(payment)) {
            return new CommonResult<>(200, "get successfully " + port, payment);
        } else {
            return new CommonResult<>(999, "get failed");
        }
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB()
    {
        return port;
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout()
    {
        // 业务逻辑处理正确，但是需要耗费3秒钟
        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
        return port;
    }
}
