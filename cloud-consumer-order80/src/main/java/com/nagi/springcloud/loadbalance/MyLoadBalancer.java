package com.nagi.springcloud.loadbalance;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLoadBalancer implements LoadBalancer {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    // CAS加自旋
    public final int getAndIncrement() {
        int current;
        int next;
        for(;;) {
            current = this.atomicInteger.get();
            next = current >= Integer.MAX_VALUE ? 0 : current + 1;
            if (atomicInteger.compareAndSet(current, next)) {
                return next;
            }
        }
    }

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        // 访问次数对实例数取模，实现轮询
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
