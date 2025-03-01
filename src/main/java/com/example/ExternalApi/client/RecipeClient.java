package com.example.ExternalApi.client;

import com.example.ExternalApi.client.domain.ProductResponse;
import com.example.ExternalApi.client.domain.RecipeResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class RecipeClient {

    private final static String RECIPE_API = "https://dummyjson.com/recipes";


    public RecipeResponse fetchRecipe() {
        WebClient webClient = WebClient.create();
        return webClient.get()
                .uri(RECIPE_API)
                .retrieve()
                .bodyToMono(RecipeResponse.class)
                .block();
    }
}
