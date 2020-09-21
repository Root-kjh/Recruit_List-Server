package com.DrK.config;

public class UrlConfig {
    public static class User {
        public final static String baseUrl = "/user";
        public final static String signin = baseUrl+"/signin";
        public final static String signup = baseUrl+"/signup";
        public final static String getLikeCompany = baseUrl+"/get_like_company";
        public final static String addLikeCompany = baseUrl+"/add_like_company";
        public final static String deleteLikeCompany = baseUrl+"/delete_like_company";
    }

    public static class Company {
        public final static String baseeUrl = "/company";
        public final static String show = baseeUrl+"/show/{page}";
        public final static String searchCompany = baseeUrl+"/search/{companyName}/{page}";
        public final static String filterCompany = baseeUrl+"/filter"; 
    }
}