/**  Copyright (C) 2015 Baidu, Inc. All Rights Reserved  */
package com.baidu.fbu.asset.mvc;

import java.io.IOException;
import org.springframework.web.util.HtmlUtils;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

/** 特殊字符转换器 */
public class EscapeObjectMapper extends ObjectMapper {
    public EscapeObjectMapper() {
        SimpleModule module = new SimpleModule( "HTML XSS Serializer",
                new Version(1, 0, 0, "FINAL", "com.baidu.fbu", "ep-jsonmodule"));
        module.addSerializer(new JsonHtmlXssSerializer(String.class));
        this.registerModule(module);
    }

    /**   内部类特殊字符处理逻辑     */
    class JsonHtmlXssSerializer extends JsonSerializer<String> {
        public JsonHtmlXssSerializer(Class<String> string) {
            super();
        }

        /**  处理String类型    */
        public Class<String> handledType() {
            return String.class;
        }

        /** 处理逻辑      */
        public void serialize(String value, JsonGenerator jsonGenerator,
                SerializerProvider serializerProvider) throws IOException,
                JsonProcessingException {
            if (value != null) {
                String encodedValue = HtmlUtils.htmlEscape(value.toString());
                jsonGenerator.writeString(encodedValue);
            }
        }
    }
    
}