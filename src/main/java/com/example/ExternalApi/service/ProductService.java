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
        List<Product> products = getProduct();
        Map<Integer, String> recipes = getRecipe().stream().collect(Collectors.toMap(Recipe::getProductId, Recipe::getCuisine));
        return combineProductDetails(products, recipes);
    }

    public List<ProductDto> getProductsByCuisine(String cusine) {
        List<Recipe> recipes = getRecipe().stream()
                .filter(r -> r.getCuisine().equals(cusine))
                .collect(Collectors.toList());
        return recipes.stream()
                .map(r ->
                {
                    Product p = findProductById(r.getProductId());
                    return new ProductDto(p.getId(), p.getTitle(), p.getPrice(), r.getCuisine());
                })
                .collect(Collectors.toList());
    }

    private List<Recipe> getRecipe() {
        RecipeResponse recipeResponse = recipeClient.fetchRecipe();
        return recipeResponse.getRecipes();
    }

    private List<Product> getProduct() {
        ProductResponse productResponseMono = productClient.fetchProducts();
        return productResponseMono.getProducts();
    }

    private Product findProductById(int id) {
        return getProduct()
                .stream()
                .filter(p -> p.getId() == id)
                .findAny()
                .orElse(null);
    }

    private static List<ProductDto> combineProductDetails(List<Product> products, Map<Integer, String> recipes) {
        return products.stream()
                .map(p -> new ProductDto(p.getId(), p.getTitle(), p.getPrice(),
                        recipes.get(p.getId())))
                .collect(Collectors.toList());
    }
}
