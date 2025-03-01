package com.example.ExternalApi.client;

import com.example.ExternalApi.client.domain.ProductResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ProductClient {

    private final static String PROD_API = "https://dummyjson.com/products";

    public ProductResponse fetchProducts() {
        WebClient webClient = WebClient.create();
        return webClient.get()
                .uri(PROD_API)
                .retrieve()
                .bodyToMono(ProductResponse.class)
                .block();
    }
}
