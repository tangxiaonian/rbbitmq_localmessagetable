package com.tang.stock.controller;

import com.tang.stock.service.StockService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Classname StockController
 * @Description [ TODO ]
 * @Author Tang
 * @Date 2020/4/11 12:26
 * @Created by ASUS
 */
@RequestMapping("/stock")
@RestController
public class StockController {

    @Resource
    private StockService stockService;

    @GetMapping("/reduce")
    public Boolean reduceNumber() {
        return stockService.reduceNumber();
    }

}