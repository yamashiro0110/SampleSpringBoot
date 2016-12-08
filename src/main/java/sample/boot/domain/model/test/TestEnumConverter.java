package sample.boot.domain.model.test;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class TestEnumConverter implements AttributeConverter<TestEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(final TestEnum attribute) {
        return Long.valueOf(attribute.ordinal());
    }

    @Override
    public TestEnum convertToEntityAttribute(final Long dbData) {
        return (dbData == 0) ? TestEnum.ADMIN : TestEnum.NORMAL;
    }
}
