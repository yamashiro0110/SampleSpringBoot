package sample.spring.boot.mybatis;

import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by yamashiro-r on 2017/02/06.
 */
@Repository
public class SampleRepository {
    @Resource
    private SampleMyBatisJavaApiMapper sampleMyBatisJavaApiMapper;
    @Resource
    private SampleMyBatisXmlMapper sampleMyBatisXmlMapper;

    public Long countByJava() {
        return this.sampleMyBatisJavaApiMapper.countBy();
    }

    public Long countByXml() {
        return this.sampleMyBatisXmlMapper.countBy();
    }
}
