//package com.fancode.config;
//
//public class Config {
//    public static final String BASE_URI = "http://jsonplaceholder.typicode.com";
//
//}

package com.fancode.config;

import java.util.Optional;

public class Config {
    private static final String DEFAULT_BASE_URI = "http://jsonplaceholder.typicode.com";
    private static final String BASE_URI_ENV = "BASE_URI";
    public static final String REPORT_DIRECTORY = "Reports";

    
    public static String getBaseUri() {
        return Optional.ofNullable(System.getenv(BASE_URI_ENV))
                       .orElse(DEFAULT_BASE_URI);
    }
}