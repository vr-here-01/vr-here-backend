package com.vr.here.api.gateway.serviceproxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.vr.here.api.gateway.exception.ClientSideException;
import com.vr.here.api.gateway.exception.ServerSideException;
import com.vr.here.api.gateway.response.CountryCodeResponse;

import reactor.core.publisher.Mono;

@Service
public class UserServiceProxy {
	@Autowired
	@Qualifier("userClient")
	private WebClient.Builder webClientBuilder;

	public void getCountryCodeOnLocation(final String latitude, final String logitude) {
		webClientBuilder.build().get().uri("/location").accept(MediaType.APPLICATION_JSON).attribute("lat", latitude)
				.attribute("lng", logitude).retrieve()
				.onStatus(status -> status.is4xxClientError(),
						response -> Mono.error(() -> new ClientSideException("Oops, something wrong from your end")))
				.onStatus(status -> status.is5xxServerError(),
						response -> Mono.error(() -> new ServerSideException(
								"Oops, something wrong with my co-worker \"User-Service\", check with him")))
				.bodyToMono(CountryCodeResponse.class);
	}

}
