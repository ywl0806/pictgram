package com.example.pictgram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class PictgramApplication {

	public static void main(String[] args) {
		SpringApplication.run(PictgramApplication.class, args);
	}

	@Bean
	public WebClient rest(ClientRegistrationRepository clients, OAuth2AuthorizedClientRepository authz) {
		ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2 = new ServletOAuth2AuthorizedClientExchangeFilterFunction(
				clients, authz);
		return WebClient.builder().filter(oauth2).build();
	}
}
