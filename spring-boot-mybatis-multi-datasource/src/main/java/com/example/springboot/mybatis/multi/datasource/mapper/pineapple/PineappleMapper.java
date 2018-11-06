package com.example.springboot.mybatis.multi.datasource.mapper.pineapple;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PineappleMapper {

    @Select("select name from datasource")
    List<String> findAll();

}
