package com.example.demo.mysqlMapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MysqlMapper {
    int insertMysql(@Param("name") String name, @Param("age") Integer age);

    List<Map<String, Object>> testQuery();
}
