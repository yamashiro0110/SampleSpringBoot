package sample.spring.boot;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yamashiro-r on 2017/02/02.
 */
@Mapper
interface SampleTableMapper {
    List<SampleTable> findAll();

    List<SampleTable> findByQuery(@Param("query") String query);

    void save(@Param("s") SampleTable sampleTable);
}
