package com.example.demo.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface TestService {
    Map<String, Object> insertMysql(String name, Integer age);

    Map<String, Object> insertOracle(String name, Integer age);

    Map<String, Object> insertMysqlAndOrcl(String name, Integer age);

    Map<String, Object> testQuery(HttpServletRequest request, HttpServletResponse response);

}
