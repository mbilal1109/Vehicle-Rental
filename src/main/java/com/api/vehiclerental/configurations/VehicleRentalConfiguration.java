package com.api.vehiclerental.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VehicleRentalConfiguration {

    @Bean
    public ModelMapper mapper() {
        return new ModelMapper();
    }
}
