package com.tang.stock.service;

import com.tang.stock.domain.Stock;
import com.tang.stock.mapper.StockMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Classname StockService
 * @Description [ TODO ]
 * @Author Tang
 * @Date 2020/4/11 14:23
 * @Created by ASUS
 */
@Service
public class StockService {

    @Resource
    private StockMapper stockMapper;

    public Boolean reduceNumber() {

        Stock stock = stockMapper.selectByPrimaryKey(1);

        stock.setStock(stock.getStock() - 1);

        stockMapper.updateByPrimaryKey(stock);

        return true;
    }

}