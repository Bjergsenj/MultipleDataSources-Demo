package com.example.demo.util;

import com.example.demo.annotation.TestUrlStringSer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.Objects;

/**
 * description:TestSerializeUtil
 * create: 2020/12/25 21:53
 *
 * @author MingXin.Nie
 * @version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
public class TestSerializeUtil extends JsonSerializer<String> implements
        ContextualSerializer {

    private String w;
    private String h;
    private String q;
    private String a;


    @Override
    public void serialize(final String s, final JsonGenerator jsonGenerator,
                          final SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(s + "?" + w + h + q + a);

    }

    @Override
    public JsonSerializer<?> createContextual(final SerializerProvider serializerProvider,
                                              final BeanProperty beanProperty) throws JsonMappingException {
        // 为空直接跳过
        if (beanProperty != null) {
            // 非 String 类直接跳过
            if (Objects.equals(beanProperty.getType().getRawClass(), String.class)) {
                TestUrlStringSer annotation = beanProperty.getAnnotation(TestUrlStringSer.class);
                if (annotation == null) {
                    annotation = beanProperty.getContextAnnotation(TestUrlStringSer.class);
                }
                // 如果能得到注解，就将注解的 value 传入 TestSerializeTest
                if (annotation != null) {
                    return new TestSerializeUtil(annotation.w(), annotation.h(), annotation.q(), annotation.a());
                }
            }
            return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
        }
        return serializerProvider.findNullValueSerializer(null);
    }
}

