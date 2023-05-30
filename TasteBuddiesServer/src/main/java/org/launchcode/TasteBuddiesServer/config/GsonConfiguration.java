package org.launchcode.TasteBuddiesServer.config;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
@Configuration
public class GsonConfiguration {
    @Bean
    public GsonHttpMessageConverter gsonHttpMessageConverter(){
        Gson gson = new Gson();
        GsonHttpMessageConverter converter = new GsonHttpMessageConverter(gson);
        return converter;
    }
}
