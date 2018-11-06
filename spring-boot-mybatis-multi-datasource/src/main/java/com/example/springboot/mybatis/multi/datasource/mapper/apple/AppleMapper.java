package com.example.springboot.mybatis.multi.datasource.mapper.apple;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AppleMapper {

    @Select("select name from datasource")
    List<String> findAll();

}
