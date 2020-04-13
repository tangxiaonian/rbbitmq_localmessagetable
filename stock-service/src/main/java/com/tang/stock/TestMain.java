package com.tang.stock;

import com.tang.stock.utils.MapperUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname TestMain
 * @Description [ TODO ]
 * @Author Tang
 * @Date 2020/4/11 16:49
 * @Created by ASUS
 */
public class TestMain {

    public static void main(String[] args) throws IOException {

        Map<String, String> map = new HashMap<>();

        map.put("orderId", "efd8e38168614076a4b21f6b591df70f");

        System.out.println(MapperUtils.ObjectToJson(map));

    }

}