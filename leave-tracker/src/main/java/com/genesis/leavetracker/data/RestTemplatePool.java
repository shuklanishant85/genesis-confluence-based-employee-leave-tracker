package com.genesis.leavetracker.data;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RestTemplatePool {

	@Bean
	@Scope("prototype")
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter = new MappingJackson2HttpMessageConverter();
		mappingJacksonHttpMessageConverter.setObjectMapper(new ObjectMapper());
		restTemplate.getMessageConverters().add(mappingJacksonHttpMessageConverter);
		return restTemplate;
	}


}
