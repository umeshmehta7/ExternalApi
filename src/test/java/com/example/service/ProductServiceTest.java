package com.example.service;

import com.example.ExternalApi.client.RecipeClient;
import com.example.ExternalApi.client.domain.Recipe;
import com.example.ExternalApi.client.domain.RecipeResponse;
import com.example.ExternalApi.service.ProductService;
import com.example.ExternalApi.client.ProductClient;
import com.example.ExternalApi.client.domain.Product;
import com.example.ExternalApi.client.domain.ProductResponse;
import com.example.ExternalApi.dto.ProductDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    ProductClient productClient;

    @Mock
    RecipeClient recipeClient;

    @InjectMocks
    ProductService productService;

    @Test
    void shouldReturnEmptyProducts() {
        ProductResponse mock = mock(ProductResponse.class);
        RecipeResponse recipeResponse = mock(RecipeResponse.class);

        when(productClient.fetchProducts()).thenReturn(mock);
        when(recipeClient.fetchRecipe()).thenReturn(recipeResponse);

        List<ProductDto> products = productService.getProducts();
        assertEquals(products.size(), 0);
    }

    @Test
    void shouldReturnProducts() {
        when(productClient.fetchProducts()).thenReturn(getProductResponse());
        when(recipeClient.fetchRecipe()).thenReturn(getRecipeResponse());
        List<ProductDto> products = productService.getProducts();
        ProductDto productDto = products.get(0);
        assertEquals(products.size(), 1);
        assertEquals(productDto.id(), 1);
        assertEquals(productDto.title(), "Javva");
        assertEquals(productDto.price(), 99.9);
        assertEquals(productDto.cuisine(), "Indian");
    }

    private ProductResponse getProductResponse() {
        ProductResponse productResponse = new ProductResponse();
        Product product = new Product();
        product.setId(1);
        product.setTitle("Javva");
        product.setPrice(99.9);
        productResponse.setProducts(Arrays.asList(product));
        return productResponse;
    }

    private RecipeResponse getRecipeResponse(){
        RecipeResponse recipeResponse = new RecipeResponse();
        Recipe recipe = new Recipe();
        recipe.setProductId(1);
        recipe.setCuisine("Indian");
        recipeResponse.setRecipes(Arrays.asList(recipe));
        return recipeResponse;
    }
}