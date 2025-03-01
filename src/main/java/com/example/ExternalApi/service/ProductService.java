package com.example.ExternalApi.service;

import com.example.ExternalApi.client.ProductClient;
import com.example.ExternalApi.client.RecipeClient;
import com.example.ExternalApi.client.domain.Product;
import com.example.ExternalApi.client.domain.ProductResponse;
import com.example.ExternalApi.client.domain.Recipe;
import com.example.ExternalApi.client.domain.RecipeResponse;
import com.example.ExternalApi.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductClient productClient;

    @Autowired
    private RecipeClient recipeClient;

    public List<ProductDto> getProducts() {
        ProductResponse productResponseMono = productClient.fetchProducts();
        List<Product> products = productResponseMono.getProducts();
        Map<Integer, String> recipes = getRecipe().stream().collect(Collectors.toMap(Recipe::getProductId,Recipe::getCuisine));
        return products.stream()
                .map(p -> new ProductDto(p.getId(), p.getTitle(), p.getPrice(),
                        recipes.get(p.getId())))
                .collect(Collectors.toList());
    }

    private List<Recipe> getRecipe(){
        RecipeResponse recipeResponse = recipeClient.fetchRecipe();
        return recipeResponse.getRecipes();
    }
}
