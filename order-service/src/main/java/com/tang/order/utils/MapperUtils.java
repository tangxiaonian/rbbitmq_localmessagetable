package com.tang.order.utils;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;

/**
 * @Classname MapperUtils
 * @Description [ TODO ]
 * @Author Tang
 * @Date 2020/4/11 16:22
 * @Created by ASUS
 */
public class MapperUtils {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    public static ObjectMapper getInstance() {
        return objectMapper;
    }

    /**
     * 对象转换为json字符串
     * @param entity
     * @return
     */
    public static String ObjectToJson(Object entity) {

        try {
            return objectMapper.writeValueAsString(entity);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";

    }

    /**
     * 对象序列化后 输出 到指定输出流
     * @param printStream
     * @param entity
     * @throws Exception
     */
    public static void ObjectToJson2(PrintStream printStream, Object entity) throws Exception {
        JsonGenerator generator = objectMapper.getFactory()
                .createGenerator(printStream, JsonEncoding.UTF8);
        generator.writeObject(entity);
    }

    /**
     * json 转  java 实体类
     * @param json
     * @param entity
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T JsonToObject(String json,Class<T> entity) throws IOException {
        return objectMapper.readValue(json, entity);
    }

    /**
     * json 转换 Map
     * @param json
     * @return
     */
    public static Map<String, Object> JsonToMap(String json) {

        try {
            return objectMapper.readValue(json, new TypeReference<Map<String, Object>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * json 转 List
     * @param json
     * @return
     */
    public static List<Object> JsonToList(String json) {

        try {
            return objectMapper.readValue(json, new TypeReference<List<Object>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * json 转 数组
     * @param json
     * @return
     */
    public static Object[] JsonToArray(String json) {

        try {
            return objectMapper.readValue(json, Object[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @MethodName ConvertNodeToPoJo
     * @Description [ 解析指定节点的数据，返回PoJo ]
     * @Date 2019/9/18 9:10
     * @Param [tree, node, tClass]
     * @return
     **/
    public static <T> T ConvertNodeToPoJo(String tree,String node, Class<T> tClass) {

        try {
            JsonNode treeNode = objectMapper.readTree(tree);

            JsonNode jsonNode = treeNode.get(node);

            return JsonToObject(jsonNode.toString(), tClass);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取指定节点的数据
     * @param tree
     * @param node
     * @return
     */
    public static String ConvertNodeToString(String tree,String node) {

        try {
            JsonNode treeNode = objectMapper.readTree(tree);

            JsonNode jsonNode = treeNode.get(node);

            return jsonNode.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}