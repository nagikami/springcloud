package com.nagi.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.nagi.springcloud.entities.CommonResult;

public class CustomBlockHandler {
    public static CommonResult handlerException(BlockException exception) {
        return new CommonResult(4444, "按客戶自定义,global handlerException----1");
    }

    public static CommonResult handlerException2(BlockException exception) {
        return new CommonResult(4444, "按客戶自定义,global handlerException----2");
    }
}
