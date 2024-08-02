
package com.bootexample4.products.controller;

import com.bootexample4.products.model.Product;
import com.bootexample4.products.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
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
	public void testGetAllProductsReturnsCorrectList() {
		// Arrange
		Product product1 = new Product();
		Product product2 = new Product();
		List<Product> expectedProducts = Arrays.asList(product1, product2);
		when(productRepository.findAll()).thenReturn(expectedProducts);
		// Act
		List<Product> actualProducts = productController.getAllProducts();
		// Assert
		assertEquals(expectedProducts, actualProducts);
	}

	@Test
    public void testGetAllProductsReturnsEmptyListWhenNoProducts() {
        // Arrange
        when(productRepository.findAll()).thenReturn(Collections.emptyList());
        // Act
        List<Product> actualProducts = productController.getAllProducts();
        // Assert
        assertTrue(actualProducts.isEmpty());
    }

	@Test
    public void testGetAllProductsHandlesExceptions() {
        // Arrange
        when(productRepository.findAll()).thenThrow(DataAccessException.class);
        // Act and Assert
        assertThrows(DataAccessException.class, () -> productController.getAllProducts());
    }

}