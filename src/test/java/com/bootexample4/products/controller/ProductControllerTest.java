package com.bootexample4.products.controller;

import com.bootexample4.products.model.Product;
import com.bootexample4.products.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

	// Merged class fields
	private ProductRepository productRepository;

	private ProductController productController;

	Product product1 = new Product();

	Product product2 = new Product();

	List<Product> mockProducts = Arrays.asList(product1, product2);

	List<Product> products = productController.getAllProducts();

	Product product = new Product();

	List<Product> largeProductList = new ArrayList<>();

	<10000;
	Product result = productController.createProduct(product);

	RuntimeException exception = assertThrows(RuntimeException.class, () -> productController.createProduct(product));

	Long validId = 1L;

	Product mockProduct = new Product();

	ResponseEntity<Product> response = productController.getProductById(validId);

	Long invalidId = 999L;

	Long productId = 1L;

	Long nonExistentId = 999L;

	ResponseEntity<Object> responseEntity = productController.deleteProduct(productId);

	// Merged test methods
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	public void returnsAllProductsSuccessfully() {
		// Arrange
		Product product1 = new Product(); // TODO: Set fields if available
		Product product2 = new Product(); // TODO: Set fields if available
		List<Product> mockProducts = Arrays.asList(product1, product2);
		when(productRepository.findAll()).thenReturn(mockProducts);
		// Act
		List<Product> products = productController.getAllProducts();
		// Assert
		assertEquals(mockProducts, products, "The returned list should match the mocked product list.");
		verify(productRepository, times(1)).findAll();
	}

	public void returnsEmptyListWhenNoProducts() {
        // Arrange
        when(productRepository.findAll()).thenReturn(Collections.emptyList());
        // Act
        List<Product> products = productController.getAllProducts();
        // Assert
        assertTrue(products.isEmpty(), "The returned list should be empty.");
        verify(productRepository, times(1)).findAll();
    }

	public void handlesNullResponseGracefully() {
        // Arrange
        when(productRepository.findAll()).thenReturn(null);
        // Act
        List<Product> products = productController.getAllProducts();
        // Assert
        assertNotNull(products, "The returned list should not be null.");
        assertTrue(products.isEmpty(), "The returned list should be empty for a null response.");
        verify(productRepository, times(1)).findAll();
    }

	public void handlesDuplicateProductsCorrectly() {
		// Arrange
		Product product = new Product(); // TODO: Set fields if available
		List<Product> mockProducts = Arrays.asList(product, product);
		when(productRepository.findAll()).thenReturn(mockProducts);
		// Act
		List<Product> products = productController.getAllProducts();
		// Assert
		assertEquals(mockProducts, products, "The returned list should match the mocked list with duplicates.");
		assertEquals(2, products.size(), "The list should preserve duplicate entries.");
		verify(productRepository, times(1)).findAll();
	}

	public void handlesRepositoryExceptionGracefully() {
        // Arrange
        when(productRepository.findAll()).thenThrow(new RuntimeException("Database error"));
        // Act & Assert
        assertThrows(RuntimeException.class, productController::getAllProducts, "The method should propagate the exception.");
        verify(productRepository, times(1)).findAll();
    }

	public void callsRepositoryFindAllOnce() {
        // Arrange
        when(productRepository.findAll()).thenReturn(Collections.emptyList());
        // Act
        productController.getAllProducts();
        // Assert
        verify(productRepository, times(1)).findAll();
    }

	public void handlesLargeProductListsSuccessfully() {
		// Arrange
		List<Product> largeProductList = new ArrayList<>();
		for (int i = 0; i < 10000; i++) {
			largeProductList.add(new Product()); // TODO: Set fields if available
		}
		when(productRepository.findAll()).thenReturn(largeProductList);
		// Act
		List<Product> products = productController.getAllProducts();
		// Assert
		assertEquals(largeProductList, products, "The returned list should match the mocked large product list.");
		assertEquals(10000, products.size(), "The list size should match the large dataset.");
		verify(productRepository, times(1)).findAll();
	}

	public void createProductSuccess() {
		// Arrange
		Product product = new Product();
		product.setName("Test Product");
		product.setDescription("This is a test product");
		product.setPrice(100.00);
		when(productRepository.save(any(Product.class))).thenReturn(product);
		// Act
		Product result = productController.createProduct(product);
		// Assert
		assertNotNull(result);
		assertEquals("Test Product", result.getName());
		assertEquals("This is a test product", result.getDescription());
		assertEquals(100.00, result.getPrice());
		verify(productRepository, times(1)).save(any(Product.class));
	}

	public void createProductFailsWhenNullProvided() {
		// Arrange, Act & Assert
		assertThrows(NullPointerException.class, () -> productController.createProduct(null));
	}

	public void createProductWithMissingName() {
		// Arrange
		Product product = new Product();
		product.setDescription("Description without name");
		product.setPrice(50.00);
		when(productRepository.save(any(Product.class))).thenReturn(product);
		// Act
		Product result = productController.createProduct(product);
		// Assert
		assertNotNull(result);
		assertNull(result.getName());
		assertEquals("Description without name", result.getDescription());
		assertEquals(50.00, result.getPrice());
		verify(productRepository, times(1)).save(any(Product.class));
	}

	public void createProductWithNegativePrice() {
		// Arrange
		Product product = new Product();
		product.setName("Invalid Product");
		product.setDescription("This product has a negative price");
		product.setPrice(-100.00);
		when(productRepository.save(any(Product.class))).thenReturn(product);
		// Act
		Product result = productController.createProduct(product);
		// Assert
		assertNotNull(result);
		assertEquals("Invalid Product", result.getName());
		assertEquals("This product has a negative price", result.getDescription());
		assertEquals(-100.00, result.getPrice());
		verify(productRepository, times(1)).save(any(Product.class));
	}

	public void createProductFailsRepositoryError() {
		// Arrange
		Product product = new Product();
		product.setName("Unexpected Failure");
		product.setDescription("This test simulates repo failure");
		product.setPrice(150.00);
		when(productRepository.save(any(Product.class))).thenThrow(new RuntimeException("Database error"));
		// Act & Assert
		RuntimeException exception = assertThrows(RuntimeException.class,
				() -> productController.createProduct(product));
		assertEquals("Database error", exception.getMessage());
		verify(productRepository, times(1)).save(any(Product.class));
	}

	public void createProductWithNullFieldsForOptionalAttributes() {
		// Arrange
		Product product = new Product();
		product.setName("Partial Product");
		product.setDescription("This product has optional fields as null");
		product.setPrice(200.00);
		when(productRepository.save(any(Product.class))).thenReturn(product);
		// Act
		Product result = productController.createProduct(product);
		// Assert
		assertNotNull(result);
		assertEquals("Partial Product", result.getName());
		assertEquals("This product has optional fields as null", result.getDescription());
		assertEquals(200.00, result.getPrice());
		verify(productRepository, times(1)).save(any(Product.class));
	}

	public void validateRepositorySaveInteraction() {
		// Arrange
		Product product = new Product();
		product.setName("Test Interaction");
		product.setDescription("Interaction with repository");
		product.setPrice(300.00);
		when(productRepository.save(any(Product.class))).thenReturn(product);
		// Act
		productController.createProduct(product);
		// Assert
		verify(productRepository, times(1)).save(any(Product.class));
	}

	void setUp_2() {
		MockitoAnnotations.openMocks(this);
	}

	public void retrieveProductWithValidId() {
		// Arrange
		Long validId = 1L; // TODO Replace with actual product ID if needed
		Product mockProduct = new Product(); // Replace below with actual fields in
												// Product
		mockProduct.setId(validId);
		mockProduct.setName("Sample Product");
		mockProduct.setPrice(100.0);
		mockProduct.setDescription("This is a sample product description.");

		when(productRepository.findById(validId)).thenReturn(Optional.of(mockProduct));

		// Act
		ResponseEntity<Product> response = productController.getProductById(validId);

		// Assert
		assertEquals(200, response.getStatusCodeValue());
		assertEquals(mockProduct, response.getBody());
	}

	public void retrieveProductWithInvalidId() {
		// Arrange
		Long invalidId = 999L; // Invalid/Non-existing ID
		when(productRepository.findById(invalidId)).thenReturn(Optional.empty());

		// Act
		ResponseEntity<Product> response = productController.getProductById(invalidId);

		// Assert
		assertEquals(404, response.getStatusCodeValue());
		assertEquals(null, response.getBody());
	}

	public void setUp_3() {
		MockitoAnnotations.openMocks(this);
	}

	public void updateExistingProductSuccessfully() {
		// Arrange
		Long productId = 1L;
		Product existingProduct = new Product();
		existingProduct.setName("Old Name");
		existingProduct.setDescription("Old Description");
		existingProduct.setPrice(10.0);
		Product updatedProduct = new Product();
		updatedProduct.setName("Updated Name");
		updatedProduct.setDescription("Updated Description");
		updatedProduct.setPrice(15.0);
		when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
		when(productRepository.save(existingProduct)).thenReturn(updatedProduct);
		// Act
		var response = productController.updateProduct(productId, updatedProduct);
		// Assert
		assertEquals(200, response.getStatusCodeValue());
		assertThat(response.getBody()).isNotNull();
		assertEquals("Updated Name", response.getBody().getName());
		assertEquals("Updated Description", response.getBody().getDescription());
		assertEquals(15.0, response.getBody().getPrice(), 0.001); // Added delta for
																	// compatibility
	}

	public void updateProductForNonExistentId() {
		// Arrange
		Long nonExistentId = 999L;
		Product productUpdate = new Product();
		productUpdate.setName("Name");
		productUpdate.setDescription("Description");
		productUpdate.setPrice(20.0);
		when(productRepository.findById(nonExistentId)).thenReturn(Optional.empty());
		// Act
		var response = productController.updateProduct(nonExistentId, productUpdate);
		// Assert
		assertEquals(404, response.getStatusCodeValue());
		assertThat(response.getBody()).isNull();
	}

	public void updateProductWithNullFields() {
		// Arrange
		Long productId = 1L;
		Product existingProduct = new Product();
		existingProduct.setName("Old Name");
		existingProduct.setDescription("Old Description");
		existingProduct.setPrice(10.0);
		Product updatedProduct = new Product();
		updatedProduct.setName(null);
		updatedProduct.setDescription(null);
		// For null price, avoid directly setting "null" since the type is double.
		// Instead, use a flag or maintain any defaults in the business logic.
		updatedProduct.setPrice(0.0);
		when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
		when(productRepository.save(existingProduct)).thenReturn(updatedProduct);
		// Act
		var response = productController.updateProduct(productId, updatedProduct);
		// Assert
		assertEquals(200, response.getStatusCodeValue());
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().getName()).isNull();
		assertThat(response.getBody().getDescription()).isNull();
		assertEquals(0.0, response.getBody().getPrice(), 0.001); // Modified test for
																	// compilable code
	}

	public void updateProductWithInvalidPrice() {
		// Improvement Suggestion:
		// The business logic should validate negative price inputs since prices cannot
		// logically be negative.
		// The provided logic does not handle invalid inputs, which needs enhancement.
		// Improved code for handling or suggesting valid business logic within the tests.
		Long productId = 1L;
		Product existingProduct = new Product();
		existingProduct.setName("Old Name");
		existingProduct.setDescription("Old Description");
		existingProduct.setPrice(10.0);
		Product updatedProduct = new Product();
		updatedProduct.setName("Updated Name");
		updatedProduct.setDescription("Updated Description");
		updatedProduct.setPrice(-5.0); // Invalid price, needs validation in the business
										// logic.
		when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
		// Act
		var response = productController.updateProduct(productId, updatedProduct);
		// Assert
		assertEquals(200, response.getStatusCodeValue());
		assertThat(response.getBody()).isNotNull();
		assertEquals("Updated Name", response.getBody().getName());
		assertEquals("Updated Description", response.getBody().getDescription());
		assertEquals(-5.0, response.getBody().getPrice(), 0.001); // Added delta for
																	// compatibility
		// Comment suggesting enhancement:
		// Either throw an exception or reject updates with negative prices in business
		// logic.
	}

	public void verifySaveMethodCalledOnUpdate() {
		// Arrange
		Long productId = 1L;
		Product existingProduct = new Product();
		existingProduct.setName("Old Name");
		existingProduct.setDescription("Old Description");
		existingProduct.setPrice(10.0);
		Product updatedProduct = new Product();
		updatedProduct.setName("Updated Name");
		updatedProduct.setDescription("Updated Description");
		updatedProduct.setPrice(15.0);
		when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
		when(productRepository.save(existingProduct)).thenReturn(updatedProduct);
		// Act
		productController.updateProduct(productId, updatedProduct);
		// Assert
		verify(productRepository, times(1)).save(existingProduct);
	}

	public void updateProductWithEmptyObject() {
		// Arrange
		Long productId = 1L;
		Product existingProduct = new Product();
		existingProduct.setName("Old Name");
		existingProduct.setDescription("Old Description");
		existingProduct.setPrice(10.0);
		Product updatedProduct = new Product(); // Empty Product
		when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
		when(productRepository.save(existingProduct)).thenReturn(existingProduct);
		// Act
		var response = productController.updateProduct(productId, updatedProduct);
		// Assert
		assertEquals(200, response.getStatusCodeValue());
		assertThat(response.getBody()).isNotNull();
		assertEquals("Old Name", response.getBody().getName());
		assertEquals("Old Description", response.getBody().getDescription());
		assertEquals(10.0, response.getBody().getPrice(), 0.001); // Added delta for
																	// compatibility
	}

	public void updateProductWithRepositoryFailure() {
		// Arrange
		Long productId = 1L;
		Product productUpdate = new Product();
		productUpdate.setName("Name");
		productUpdate.setDescription("Description");
		productUpdate.setPrice(10.0);
		when(productRepository.findById(productId)).thenThrow(new RuntimeException("Database error"));
		// Act & Assert
		try {
			productController.updateProduct(productId, productUpdate);
		}
		catch (RuntimeException e) {
			assertEquals("Database error", e.getMessage());
		}
	}

	void setUp_4() {
		MockitoAnnotations.openMocks(this);
	}

	public void deleteProductSuccessfully() {
		// Arrange
		Long productId = 1L; // Sample valid ID
		Product product = new Product(); // Dummy product instance
		product.setId(productId); // TODO: Add properties as required by the Product
									// entity
		when(productRepository.findById(productId)).thenReturn(Optional.of(product));
		// Act
		ResponseEntity<Object> responseEntity = productController.deleteProduct(productId);
		// Assert
		verify(productRepository, times(1)).delete(product);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	}

	public void deleteProductNotFound() {
		// Arrange
		Long productId = 2L; // Sample non-existing ID
		when(productRepository.findById(productId)).thenReturn(Optional.empty());
		// Act
		ResponseEntity<Object> responseEntity = productController.deleteProduct(productId);
		// Assert
		verify(productRepository, times(0)).delete(any(Product.class));
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(404);
	}

	/*
	 * ROOST_METHOD_HASH=getAllProducts_fef141838b
	 * ROOST_METHOD_SIG_HASH=getAllProducts_7e38cc05f6
	 *
	 */
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	/*
	 * ROOST_METHOD_HASH=getAllProducts_fef141838b
	 * ROOST_METHOD_SIG_HASH=getAllProducts_7e38cc05f6
	 *
	 */
	@Test
	@Tag("valid")
	public void returnsAllProductsSuccessfully() {

		Product product1 = new Product();

		Product product2 = new Product();
		List<Product> mockProducts = Arrays.asList(product1, product2);
		when(productRepository.findAll()).thenReturn(mockProducts);

		List<Product> products = productController.getAllProducts();

		assertEquals(mockProducts, products, "The returned list should match the mocked product list.");
		verify(productRepository, times(1)).findAll();
	}

	/*
	 * ROOST_METHOD_HASH=getAllProducts_fef141838b
	 * ROOST_METHOD_SIG_HASH=getAllProducts_7e38cc05f6
	 *
	 */
@Test
@Tag("valid")
public void returnsEmptyListWhenNoProducts() {

    when(productRepository.findAll()).thenReturn(Collections.emptyList());

    List<Product> products = productController.getAllProducts();

    assertTrue(products.isEmpty(), "The returned list should be empty.");
    verify(productRepository, times(1)).findAll();
}

	/*
	 * ROOST_METHOD_HASH=getAllProducts_fef141838b
	 * ROOST_METHOD_SIG_HASH=getAllProducts_7e38cc05f6
	 *
	 */
@Test
@Tag("valid")
public void handlesNullResponseGracefully() {

    when(productRepository.findAll()).thenReturn(null);

    List<Product> products = productController.getAllProducts();

    assertNotNull(products, "The returned list should not be null.");
    assertTrue(products.isEmpty(), "The returned list should be empty for a null response.");
    verify(productRepository, times(1)).findAll();
}

	/*
	 * ROOST_METHOD_HASH=getAllProducts_fef141838b
	 * ROOST_METHOD_SIG_HASH=getAllProducts_7e38cc05f6
	 *
	 */
	@Test
	@Tag("valid")
	public void handlesDuplicateProductsCorrectly() {

		Product product = new Product();
		List<Product> mockProducts = Arrays.asList(product, product);
		when(productRepository.findAll()).thenReturn(mockProducts);

		List<Product> products = productController.getAllProducts();

		assertEquals(mockProducts, products, "The returned list should match the mocked list with duplicates.");
		assertEquals(2, products.size(), "The list should preserve duplicate entries.");
		verify(productRepository, times(1)).findAll();
	}

	/*
	 * ROOST_METHOD_HASH=getAllProducts_fef141838b
	 * ROOST_METHOD_SIG_HASH=getAllProducts_7e38cc05f6
	 *
	 */
@Test
@Tag("invalid")
public void handlesRepositoryExceptionGracefully() {

    when(productRepository.findAll()).thenThrow(new RuntimeException("Database error"));

    assertThrows(RuntimeException.class, productController::getAllProducts, "The method should propagate the exception.");
    verify(productRepository, times(1)).findAll();
}

	/*
	 * ROOST_METHOD_HASH=getAllProducts_fef141838b
	 * ROOST_METHOD_SIG_HASH=getAllProducts_7e38cc05f6
	 *
	 */
@Test
@Tag("integration")
public void callsRepositoryFindAllOnce() {

    when(productRepository.findAll()).thenReturn(Collections.emptyList());

    productController.getAllProducts();

    verify(productRepository, times(1)).findAll();
}

	/*
	 * ROOST_METHOD_HASH=getAllProducts_fef141838b
	 * ROOST_METHOD_SIG_HASH=getAllProducts_7e38cc05f6
	 *
	 */
	@Test
	@Tag("boundary")
	public void handlesLargeProductListsSuccessfully() {

		List<Product> largeProductList = new ArrayList<>();
		for (int i = 0; i < 10000; i++) {

			largeProductList.add(new Product());
		}
		when(productRepository.findAll()).thenReturn(largeProductList);

		List<Product> products = productController.getAllProducts();

		assertEquals(largeProductList, products, "The returned list should match the mocked large product list.");
		assertEquals(10000, products.size(), "The list size should match the large dataset.");
		verify(productRepository, times(1)).findAll();
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@Test
	@Tag("valid")
	public void createProductSuccess() {

		Product product = new Product();
		product.setName("Test Product");
		product.setDescription("This is a test product");
		product.setPrice(100.00);
		when(productRepository.save(any(Product.class))).thenReturn(product);

		Product result = productController.createProduct(product);

		assertNotNull(result);
		assertEquals("Test Product", result.getName());
		assertEquals("This is a test product", result.getDescription());
		assertEquals(100.00, result.getPrice());
		verify(productRepository, times(1)).save(any(Product.class));
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@Test
	@Tag("invalid")
	public void createProductFailsWhenNullProvided() {

		assertThrows(NullPointerException.class, () -> productController.createProduct(null));
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@Test
	@Tag("invalid")
	public void createProductWithMissingName() {

		Product product = new Product();
		product.setDescription("Description without name");
		product.setPrice(50.00);
		when(productRepository.save(any(Product.class))).thenReturn(product);

		Product result = productController.createProduct(product);

		assertNotNull(result);
		assertNull(result.getName());
		assertEquals("Description without name", result.getDescription());
		assertEquals(50.00, result.getPrice());
		verify(productRepository, times(1)).save(any(Product.class));
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@Test
	@Tag("invalid")
	public void createProductWithNegativePrice() {

		Product product = new Product();
		product.setName("Invalid Product");
		product.setDescription("This product has a negative price");
		product.setPrice(-100.00);
		when(productRepository.save(any(Product.class))).thenReturn(product);

		Product result = productController.createProduct(product);

		assertNotNull(result);
		assertEquals("Invalid Product", result.getName());
		assertEquals("This product has a negative price", result.getDescription());
		assertEquals(-100.00, result.getPrice());
		verify(productRepository, times(1)).save(any(Product.class));
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@Test
	@Tag("invalid")
	public void createProductFailsRepositoryError() {

		Product product = new Product();
		product.setName("Unexpected Failure");
		product.setDescription("This test simulates repo failure");
		product.setPrice(150.00);
		when(productRepository.save(any(Product.class))).thenThrow(new RuntimeException("Database error"));

		RuntimeException exception = assertThrows(RuntimeException.class,
				() -> productController.createProduct(product));
		assertEquals("Database error", exception.getMessage());
		verify(productRepository, times(1)).save(any(Product.class));
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@Test
	@Tag("valid")
	public void createProductWithNullFieldsForOptionalAttributes() {

		Product product = new Product();
		product.setName("Partial Product");
		product.setDescription("This product has optional fields as null");
		product.setPrice(200.00);
		when(productRepository.save(any(Product.class))).thenReturn(product);

		Product result = productController.createProduct(product);

		assertNotNull(result);
		assertEquals("Partial Product", result.getName());
		assertEquals("This product has optional fields as null", result.getDescription());
		assertEquals(200.00, result.getPrice());
		verify(productRepository, times(1)).save(any(Product.class));
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@Test
	@Tag("integration")
	public void validateRepositorySaveInteraction() {

		Product product = new Product();
		product.setName("Test Interaction");
		product.setDescription("Interaction with repository");
		product.setPrice(300.00);
		when(productRepository.save(any(Product.class))).thenReturn(product);

		productController.createProduct(product);

		verify(productRepository, times(1)).save(any(Product.class));
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("valid")
	public void retrieveProductWithValidId() {

		Long validId = 1L;

		Product mockProduct = new Product();
		mockProduct.setId(validId);
		mockProduct.setName("Sample Product");
		mockProduct.setPrice(100.0);
		mockProduct.setDescription("This is a sample product description.");
		when(productRepository.findById(validId)).thenReturn(Optional.of(mockProduct));

		ResponseEntity<Product> response = productController.getProductById(validId);

		assertEquals(200, response.getStatusCodeValue());
		assertEquals(mockProduct, response.getBody());
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("invalid")
	public void retrieveProductWithInvalidId() {

		Long invalidId = 999L;
		when(productRepository.findById(invalidId)).thenReturn(Optional.empty());

		ResponseEntity<Product> response = productController.getProductById(invalidId);

		assertEquals(404, response.getStatusCodeValue());
		assertEquals(null, response.getBody());
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Test
	@Tag("valid")
	public void updateExistingProductSuccessfully() {

		Long productId = 1L;
		Product existingProduct = new Product();
		existingProduct.setName("Old Name");
		existingProduct.setDescription("Old Description");
		existingProduct.setPrice(10.0);
		Product updatedProduct = new Product();
		updatedProduct.setName("Updated Name");
		updatedProduct.setDescription("Updated Description");
		updatedProduct.setPrice(15.0);
		when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
		when(productRepository.save(existingProduct)).thenReturn(updatedProduct);

		var response = productController.updateProduct(productId, updatedProduct);

		assertEquals(200, response.getStatusCodeValue());
		assertThat(response.getBody()).isNotNull();
		assertEquals("Updated Name", response.getBody().getName());
		assertEquals("Updated Description", response.getBody().getDescription());

		assertEquals(15.0, response.getBody().getPrice(), 0.001);
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Test
	@Tag("invalid")
	public void updateProductForNonExistentId() {

		Long nonExistentId = 999L;
		Product productUpdate = new Product();
		productUpdate.setName("Name");
		productUpdate.setDescription("Description");
		productUpdate.setPrice(20.0);
		when(productRepository.findById(nonExistentId)).thenReturn(Optional.empty());

		var response = productController.updateProduct(nonExistentId, productUpdate);

		assertEquals(404, response.getStatusCodeValue());
		assertThat(response.getBody()).isNull();
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Test
	@Tag("boundary")
	public void updateProductWithNullFields() {

		Long productId = 1L;
		Product existingProduct = new Product();
		existingProduct.setName("Old Name");
		existingProduct.setDescription("Old Description");
		existingProduct.setPrice(10.0);
		Product updatedProduct = new Product();
		updatedProduct.setName(null);
		updatedProduct.setDescription(null);

		updatedProduct.setPrice(0.0);
		when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
		when(productRepository.save(existingProduct)).thenReturn(updatedProduct);

		var response = productController.updateProduct(productId, updatedProduct);

		assertEquals(200, response.getStatusCodeValue());
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().getName()).isNull();
		assertThat(response.getBody().getDescription()).isNull();

		assertEquals(0.0, response.getBody().getPrice(), 0.001);
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Test
	@Tag("invalid")
	public void updateProductWithInvalidPrice() {

		Long productId = 1L;
		Product existingProduct = new Product();
		existingProduct.setName("Old Name");
		existingProduct.setDescription("Old Description");
		existingProduct.setPrice(10.0);
		Product updatedProduct = new Product();
		updatedProduct.setName("Updated Name");
		updatedProduct.setDescription("Updated Description");

		updatedProduct.setPrice(-5.0);
		when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));

		var response = productController.updateProduct(productId, updatedProduct);

		assertEquals(200, response.getStatusCodeValue());
		assertThat(response.getBody()).isNotNull();
		assertEquals("Updated Name", response.getBody().getName());
		assertEquals("Updated Description", response.getBody().getDescription());

		assertEquals(-5.0, response.getBody().getPrice(), 0.001);

	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Test
	@Tag("integration")
	public void verifySaveMethodCalledOnUpdate() {

		Long productId = 1L;
		Product existingProduct = new Product();
		existingProduct.setName("Old Name");
		existingProduct.setDescription("Old Description");
		existingProduct.setPrice(10.0);
		Product updatedProduct = new Product();
		updatedProduct.setName("Updated Name");
		updatedProduct.setDescription("Updated Description");
		updatedProduct.setPrice(15.0);
		when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
		when(productRepository.save(existingProduct)).thenReturn(updatedProduct);

		productController.updateProduct(productId, updatedProduct);

		verify(productRepository, times(1)).save(existingProduct);
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Test
	@Tag("boundary")
	public void updateProductWithEmptyObject() {

		Long productId = 1L;
		Product existingProduct = new Product();
		existingProduct.setName("Old Name");
		existingProduct.setDescription("Old Description");
		existingProduct.setPrice(10.0);

		Product updatedProduct = new Product();
		when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
		when(productRepository.save(existingProduct)).thenReturn(existingProduct);

		var response = productController.updateProduct(productId, updatedProduct);

		assertEquals(200, response.getStatusCodeValue());
		assertThat(response.getBody()).isNotNull();
		assertEquals("Old Name", response.getBody().getName());
		assertEquals("Old Description", response.getBody().getDescription());

		assertEquals(10.0, response.getBody().getPrice(), 0.001);
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Test
	@Tag("integration")
	public void updateProductWithRepositoryFailure() {

		Long productId = 1L;
		Product productUpdate = new Product();
		productUpdate.setName("Name");
		productUpdate.setDescription("Description");
		productUpdate.setPrice(10.0);
		when(productRepository.findById(productId)).thenThrow(new RuntimeException("Database error"));

		try {
			productController.updateProduct(productId, productUpdate);
		}
		catch (RuntimeException e) {
			assertEquals("Database error", e.getMessage());
		}
	}

	/*
	 * ROOST_METHOD_HASH=deleteProduct_5ea3a876a4
	 * ROOST_METHOD_SIG_HASH=deleteProduct_dcaff736d4
	 *
	 */
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	/*
	 * ROOST_METHOD_HASH=deleteProduct_5ea3a876a4
	 * ROOST_METHOD_SIG_HASH=deleteProduct_dcaff736d4
	 *
	 */
	@Test
	@Tag("valid")
	public void deleteProductSuccessfully() {

		Long productId = 1L;

		Product product = new Product();

		product.setId(productId);
		when(productRepository.findById(productId)).thenReturn(Optional.of(product));

		ResponseEntity<Object> responseEntity = productController.deleteProduct(productId);

		verify(productRepository, times(1)).delete(product);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	}

	/*
	 * ROOST_METHOD_HASH=deleteProduct_5ea3a876a4
	 * ROOST_METHOD_SIG_HASH=deleteProduct_dcaff736d4
	 *
	 */
	@Test
	@Tag("invalid")
	public void deleteProductNotFound() {

		Long productId = 2L;
		when(productRepository.findById(productId)).thenReturn(Optional.empty());

		ResponseEntity<Object> responseEntity = productController.deleteProduct(productId);

		verify(productRepository, times(0)).delete(any(Product.class));
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(404);
	}

}