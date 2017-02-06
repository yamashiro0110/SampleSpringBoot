package sample.spring.boot.mybatis;

import org.apache.ibatis.annotations.Mapper;

/**
 * Created by yamashiro-r on 2017/02/06.
 */
@Mapper
interface SampleMyBatisXmlMapper {
    Long countBy();
}
