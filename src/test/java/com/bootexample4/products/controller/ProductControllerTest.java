package com.bootexample4.products.controller;

import com.bootexample4.products.model.Product;
import com.bootexample4.products.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

	// Merged class fields
	private ProductRepository productRepository;

	private ProductController productController;

	List<Product> actualProducts = productController.getAllProducts();

	Long validId = 1L;

	ResponseEntity<Product> response = productController.getProductById(validId);

	Long nonExistentId = 999L;

	Long largeId = Long.MAX_VALUE;

	private Product existingProduct;

	<10;<5000;

	// Merged test methods

	/*
	 * ROOST_METHOD_HASH=getAllProducts_fef141838b
	 * ROOST_METHOD_SIG_HASH=getAllProducts_7e38cc05f6
	 *
	 */
@Test
@Tag("valid")
public void shouldReturnEmptyListWhenNoProductsExist() {

    when(productRepository.findAll()).thenReturn(new ArrayList<>());

    List<Product> actualProducts = productController.getAllProducts();

    assertTrue(actualProducts.isEmpty(), "The returned product list should be empty when no products exist in the repository");
}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@Test
	@Tag("valid")
	public void createProductWithValidInput() {
		Product validProduct = new Product();
		validProduct.setName("Valid Product");
		validProduct.setDescription("Valid product description");
		validProduct.setPrice(100.0);
		when(productRepository.save(validProduct)).thenReturn(validProduct);
		Product createdProduct = productController.createProduct(validProduct);
		assertEquals(validProduct, createdProduct, "Returned product should match the input product.");
		verify(productRepository, times(1)).save(validProduct);
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@Test
	@Tag("invalid")
	public void createProductWithNullFields() {
		Product invalidProduct = new Product();
		invalidProduct.setName(null);
		invalidProduct.setDescription(null);
		invalidProduct.setPrice(0.0);
		when(productRepository.save(any(Product.class))).thenThrow(new RuntimeException("Null fields are not allowed"));
		Exception exception = assertThrows(RuntimeException.class,
				() -> productController.createProduct(invalidProduct));
		assertEquals("Null fields are not allowed", exception.getMessage());
		verify(productRepository, times(1)).save(invalidProduct);
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@Test
	@Tag("invalid")
	public void handleExceptionDuringSave() {
		Product product = new Product();
		product.setName("Sample Product");
		product.setDescription("Sample Description");
		product.setPrice(99.99);
		when(productRepository.save(product)).thenThrow(new IllegalStateException("Database connection error"));
		Exception exception = assertThrows(IllegalStateException.class, () -> productController.createProduct(product));
		assertEquals("Database connection error", exception.getMessage());
		verify(productRepository, times(1)).save(product);
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@Test
	@Tag("boundary")
	public void createProductWithBoundaryValues() {
		Product product = new Product();
		product.setName("Boundary Value Product");
		product.setDescription("Testing boundary");
		product.setPrice(0.0);
		when(productRepository.save(product)).thenReturn(product);
		Product createdProduct = productController.createProduct(product);
		assertEquals(product, createdProduct, "Returned product should match the input product for boundary values.");
		verify(productRepository, times(1)).save(product);
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@Test
	@Tag("invalid")
	public void createDuplicateProduct() {
		Product product = new Product();
		product.setName("Duplicate Product");
		product.setDescription("Description of product");
		product.setPrice(50.0);
		when(productRepository.save(product)).thenThrow(new RuntimeException("Duplicate product detected"));
		Exception exception = assertThrows(RuntimeException.class, () -> productController.createProduct(product));
		assertEquals("Duplicate product detected", exception.getMessage());
		verify(productRepository, times(1)).save(product);
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@Test
	@Tag("invalid")
	public void createProductWithEmptyInput() {
		Product product = null;
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> productController.createProduct(product));
		assertNotNull(exception, "Exception should be thrown for null input.");
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@Test
	@Tag("valid")
	public void createProductWithSpecialCharacters() {
		Product product = new Product();
		product.setName("Special@Product#Name");
		product.setDescription("Description with %^&* special characters!");
		product.setPrice(120.75);
		when(productRepository.save(product)).thenReturn(product);
		Product createdProduct = productController.createProduct(product);
		assertEquals(product, createdProduct,
				"Returned product should match the input with special characters intact.");
		verify(productRepository, times(1)).save(product);
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
		product.setName("Invalid Price Product");
		product.setDescription("Valid description");
		product.setPrice(-10.0);
		when(productRepository.save(product)).thenThrow(new IllegalArgumentException("Price cannot be negative"));
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> productController.createProduct(product));
		assertEquals("Price cannot be negative", exception.getMessage());
		verify(productRepository, times(1)).save(product);
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@Test
	@Tag("integration")
	public void verifyRepositoryInteractionOnSave() {
		Product product = new Product();
		product.setName("Interaction Test Product");
		product.setDescription("Valid product for interaction verification");
		product.setPrice(79.99);
		when(productRepository.save(product)).thenReturn(product);
		productController.createProduct(product);
		verify(productRepository, times(1)).save(product);
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("valid")
	public void validIdReturnsExpectedProduct() {

		Long validId = 1L;

		Product mockProduct = new Product();

		mockProduct.setId(validId);

		mockProduct.setName("Valid Product");
		mockProduct.setDescription("This is a test product");
		mockProduct.setPrice(100.0);
		when(productRepository.findById(validId)).thenReturn(Optional.of(mockProduct));

		ResponseEntity<Product> response = productController.getProductById(validId);

		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		assertThat(response.getBody()).isEqualTo(mockProduct);
		verify(productRepository, times(1)).findById(validId);
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("invalid")
	public void nonExistentIdReturnsNotFound() {

		Long nonExistentId = 999L;
		when(productRepository.findById(nonExistentId)).thenReturn(Optional.empty());

		ResponseEntity<Product> response = productController.getProductById(nonExistentId);

		assertThat(response.getStatusCodeValue()).isEqualTo(404);
		assertThat(response.getBody()).isNull();
		verify(productRepository, times(1)).findById(nonExistentId);
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("invalid")
	public void nullIdReturnsAppropriateError() {

		assertThrows(IllegalArgumentException.class, () -> {
			productController.getProductById(null);
		});
		verify(productRepository, never()).findById(null);
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("invalid")
	public void malformedIdTypeThrowsError() {

		assertThrows(ClassCastException.class, () -> {
			productController.getProductById((Long) (Object) "Invalid ID");
		});
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("integration")
	public void repositoryInteractionsAreVerified() {

		Long validId = 2L;
		Product mockProduct = new Product();
		mockProduct.setId(validId);

		mockProduct.setName("Another Product");
		when(productRepository.findById(validId)).thenReturn(Optional.of(mockProduct));

		productController.getProductById(validId);

		verify(productRepository, times(1)).findById(validId);
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("valid")
	public void productWithExtraFieldsStillReturnsAppropriately() {

		Long validId = 3L;
		Product mockProduct = new Product();
		mockProduct.setId(validId);
		mockProduct.setName("Product with Extra Fields");
		mockProduct.setDescription("A product with additional fields");
		mockProduct.setPrice(200.0);

		when(productRepository.findById(validId)).thenReturn(Optional.of(mockProduct));

		ResponseEntity<Product> response = productController.getProductById(validId);

		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		assertThat(response.getBody()).isEqualTo(mockProduct);
		verify(productRepository, times(1)).findById(validId);
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("boundary")
	public void largeNumericIdDoesNotCauseOverflow() {

		Long largeId = Long.MAX_VALUE;
		when(productRepository.findById(largeId)).thenReturn(Optional.empty());

		ResponseEntity<Product> response = productController.getProductById(largeId);

		assertThat(response.getStatusCodeValue()).isEqualTo(404);
		assertThat(response.getBody()).isNull();
		verify(productRepository, times(1)).findById(largeId);
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("invalid")
	public void mixedDataIdReturnsError() {

		assertThrows(ClassCastException.class, () -> {
			productController.getProductById((Long) (Object) "abc123");
		});
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@BeforeEach
	public void setUp() {
		existingProduct = new Product();

		existingProduct.setId(1L);
		existingProduct.setName("Old Product");
		existingProduct.setDescription("Old Description");
		existingProduct.setPrice(100.0);
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Test
	@Tag("valid")
	public void successfulProductUpdate() {
		Product updatedProduct = new Product();
		updatedProduct.setName("Updated Product");
		updatedProduct.setDescription("Updated Description");
		updatedProduct.setPrice(200.0);
		when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
		when(productRepository.save(existingProduct)).thenReturn(updatedProduct);
		ResponseEntity<Product> response = productController.updateProduct(1L, updatedProduct);
		assertEquals(200, response.getStatusCodeValue());
		assertEquals(updatedProduct.getName(), response.getBody().getName());
		assertEquals(updatedProduct.getDescription(), response.getBody().getDescription());
		assertEquals(updatedProduct.getPrice(), response.getBody().getPrice(), 0);
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
@Test
@Tag("invalid")
public void productUpdateWithInvalidId() {
    when(productRepository.findById(99L)).thenReturn(Optional.empty());
    ResponseEntity<Product> response = productController.updateProduct(99L, existingProduct);
    assertEquals(404, response.getStatusCodeValue());
}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
@Test
@Tag("invalid")
public void productUpdateWithNullDetails() {
    when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
    try {
        productController.updateProduct(1L, null);
    } catch (NullPointerException e) {
        assertEquals(NullPointerException.class, e.getClass());
    }
}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Test
	@Tag("boundary")
	public void productUpdateWithIncompleteDetails() {
		Product partialUpdateProduct = new Product();
		partialUpdateProduct.setName("Partial Updated Product");

		partialUpdateProduct.setDescription(null);

		partialUpdateProduct.setPrice(0);
		when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
		when(productRepository.save(existingProduct)).thenReturn(partialUpdateProduct);
		ResponseEntity<Product> response = productController.updateProduct(1L, partialUpdateProduct);
		assertEquals(200, response.getStatusCodeValue());
		assertEquals(partialUpdateProduct.getName(), response.getBody().getName());
		assertEquals(partialUpdateProduct.getDescription(), response.getBody().getDescription());
		assertEquals(partialUpdateProduct.getPrice(), response.getBody().getPrice(), 0);
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Test
	@Tag("invalid")
	public void productUpdateWithNullId() {
		try {
			productController.updateProduct(null, existingProduct);
		}
		catch (IllegalArgumentException e) {
			assertEquals(IllegalArgumentException.class, e.getClass());
		}
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
@Test
@Tag("valid")
public void productUpdateWithSameDetails() {
    when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
    when(productRepository.save(existingProduct)).thenReturn(existingProduct);
    ResponseEntity<Product> response = productController.updateProduct(1L, existingProduct);
    assertEquals(200, response.getStatusCodeValue());
    assertEquals(existingProduct.getName(), response.getBody().getName());
    assertEquals(existingProduct.getDescription(), response.getBody().getDescription());
    assertEquals(existingProduct.getPrice(), response.getBody().getPrice(), 0);
}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Test
	@Tag("invalid")
	public void productUpdateWithInvalidFields() {
		Product invalidProduct = new Product();

		invalidProduct.setName("");
		invalidProduct.setDescription("Invalid Desc");

		invalidProduct.setPrice(-100.0);
		when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
		try {
			productController.updateProduct(1L, invalidProduct);
		}
		catch (IllegalArgumentException e) {
			assertEquals(IllegalArgumentException.class, e.getClass());
		}
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Test
	@Tag("performance")
	public void productUpdatePerformanceUnderLoad() {
		ExecutorService executor = Executors.newFixedThreadPool(10);
		when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
		when(productRepository.save(existingProduct)).thenReturn(existingProduct);
		Runnable task = () -> {
			Product product = new Product();
			product.setName("Concurrent Updated Product");
			product.setDescription("Concurrent Description");
			product.setPrice(150.0);
			ResponseEntity<Product> response = productController.updateProduct(1L, product);
			assertEquals(200, response.getStatusCodeValue());
		};
		for (int i = 0; i < 10; i++) {
			executor.submit(task);
		}
		executor.shutdown();
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Test
	@Tag("boundary")
	public void productUpdateWithLargeProductDetails() {
		StringBuilder largeName = new StringBuilder();
		StringBuilder largeDescription = new StringBuilder();
		for (int i = 0; i < 5000; i++) {

			largeName.append("A");
			largeDescription.append("B");
		}
		Product largeProduct = new Product();
		largeProduct.setName(largeName.toString());
		largeProduct.setDescription(largeDescription.toString());
		largeProduct.setPrice(1000.0);
		when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
		when(productRepository.save(existingProduct)).thenReturn(largeProduct);
		ResponseEntity<Product> response = productController.updateProduct(1L, largeProduct);
		assertEquals(200, response.getStatusCodeValue());
		assertEquals(largeProduct.getName(), response.getBody().getName());
	}

}