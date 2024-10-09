
package com.bootexample4.products.controller;

import com.bootexample4.products.model.Product;
import com.bootexample4.products.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ExtendWith(MockitoExtension.class)
public class ProductControllerGetAllProductsTest {

	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private ProductController productController;

	@Test
    @Tag('valid')
    void testGetAllProductsReturnsAllProducts() {
        Product product1 = new Product();
        Product product2 = new Product();
        List<Product> products = Arrays.asList(product1, product2);
        when(productRepository.findAll()).thenReturn(products);
        List<Product> result = productController.getAllProducts();
        assertEquals(products, result);
        verify(productRepository, times(1)).findAll();
    }

	@Test
    @Tag('valid')
    void testGetAllProductsReturnsEmptyListWhenNoProducts() {
        when(productRepository.findAll()).thenReturn(Collections.emptyList());
        List<Product> result = productController.getAllProducts();
        assertTrue(result.isEmpty());
        verify(productRepository, times(1)).findAll();
    }

	@Test
    @Tag('invalid')
    void testGetAllProductsHandlesRepositoryExceptions() {
        when(productRepository.findAll()).thenThrow(new RuntimeException());
        assertThrows(ResponseStatusException.class, () -> productController.getAllProducts());
        verify(productRepository, times(1)).findAll();
    }

}