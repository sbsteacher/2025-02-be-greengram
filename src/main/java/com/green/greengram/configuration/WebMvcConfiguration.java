package com.green.greengram.configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //빈등록
public class WebMvcConfiguration implements WebMvcConfigurer {
    public final String fileUploadPath;

    public WebMvcConfiguration(@Value("${constants.file.directory}") String fileUploadPath) {
        this.fileUploadPath = fileUploadPath;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/pic/**")
                .addResourceLocations("file:" + fileUploadPath);
    }

}
