package com.green.greengram.configuration;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.module.SimpleSerializers;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.boot.jackson.autoconfigure.JsonMapperBuilderCustomizer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

public class JacksonFormatConfiguration {

    public JsonMapperBuilderCustomizer jsonMapperBuilderCustomizer() {
        return builder -> {

        };
    }
}
