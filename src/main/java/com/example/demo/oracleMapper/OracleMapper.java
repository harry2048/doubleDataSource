package com.example.demo.oracleMapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OracleMapper {
    int insertOracle(@Param("name") String name, @Param("age") Integer age);
}
