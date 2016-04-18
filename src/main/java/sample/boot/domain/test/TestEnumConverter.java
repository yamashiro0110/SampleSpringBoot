package sample.boot.domain.test;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class TestEnumConverter implements AttributeConverter<TestEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(TestEnum attribute) {
        return Long.valueOf(attribute.ordinal());
    }

    @Override
    public TestEnum convertToEntityAttribute(Long dbData) {
        return (dbData == 0) ? TestEnum.ADMIN : TestEnum.NORMAL;
    }
}
