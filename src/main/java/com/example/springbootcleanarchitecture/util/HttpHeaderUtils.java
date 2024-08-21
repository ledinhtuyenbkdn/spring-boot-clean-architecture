package com.example.springbootcleanarchitecture.util;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;

public class HttpHeaderUtils {

    private HttpHeaderUtils() {

    }

    public static HttpHeaders generatePaginationHttpHeaders(Page<?> page) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Total-Count", String.valueOf(page.getTotalElements()));
        httpHeaders.add("X-Total-Page", String.valueOf(page.getTotalPages()));
        return httpHeaders;
    }
}
