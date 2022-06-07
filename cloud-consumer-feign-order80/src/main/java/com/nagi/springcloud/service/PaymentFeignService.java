package com.nagi.springcloud.service;

import com.nagi.springcloud.entities.CommonResult;
import com.nagi.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 通过接口绑定服务提供方服务（RPC：客户端维护服务端方法表）
 */
@Component
@FeignClient("CLOUD-PAYMENT-SERVICE") //定义服务名
public interface PaymentFeignService {
    @GetMapping("/payment/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    @GetMapping(value = "/payment/feign/timeout")
    String paymentFeignTimeout();
}
