//package com.demnca.java.basic.tools.rest;
//
//
//import com.alibaba.fastjson.TypeReference;
//
//import java.util.Map;
//
///**
// * @author caoan
// * @Description:
// * @create 2022/8/16 17:10
// **/
//public class BaseResultRestRequest<T> {
//
//    private Map<String,String> params;
//
//    private String url;
//
//    private BaseResultRestRequest(Builder builder){
//        this.url = builder.url;
//    }
//
//    private static class Builder<T>{
//
//        private String url;
//
//        private TypeReference<T> typeReference;
//
//
//        public BaseResultRestRequest<T> build(){
//            return new BaseResultRestRequest<T>();
//        }
//    }
//}
