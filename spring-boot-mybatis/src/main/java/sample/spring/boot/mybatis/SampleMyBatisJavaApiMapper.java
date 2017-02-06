package sample.spring.boot.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Created by yamashiro-r on 2017/02/06.
 */
@Mapper
interface SampleMyBatisJavaApiMapper {

    @Select("select 1 from dual")
    Long countBy();

}
