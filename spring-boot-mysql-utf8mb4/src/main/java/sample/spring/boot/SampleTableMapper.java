package sample.spring.boot;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by yamashiro-r on 2017/02/02.
 */
@Mapper
public interface SampleTableMapper {
    @Insert("insert into sample_table(sample_id, sample_name, created) values (#{s.id}, #{s.name}, now())")
    void save(@Param("s") SampleTable sampleTable);
}
