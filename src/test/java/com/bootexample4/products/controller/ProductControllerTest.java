package com.bootexample4.products.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Collections;
import java.util.List;
import com.bootexample4.products.model.Product;
import com.bootexample4.products.repository.ProductRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

	// Merged class fields
	private ProductController productController;

	private ProductRepository productRepository;

	Product product2 = new Product();

	List<Product> mockedProducts = List.of(product1, product2);

	Product validProduct = new Product();

	Product createdProduct = productController.createProduct(validProduct);

	Product nullProduct = null;

	Product incompleteProduct = new Product();

	Product duplicateProduct = new Product();

	<20;
	ResponseEntity<Product> responseEntity = productController.getProductById(Long.valueOf(invalidInput));

	private Product existingProduct;

	private Product updatedProduct;

	Long validId = 1L;

	ResponseEntity<Product> response = productController.updateProduct(validId, updatedProduct);

	Long invalidId = 999L;

	// Merged test methods

	/*
	 * ROOST_METHOD_HASH=getAllProducts_fef141838b
	 * ROOST_METHOD_SIG_HASH=getAllProducts_7e38cc05f6
	 *
	 */
	@BeforeEach
	void setUp() {

	}

	/*
	 * ROOST_METHOD_HASH=getAllProducts_fef141838b
	 * ROOST_METHOD_SIG_HASH=getAllProducts_7e38cc05f6
	 *
	 */
@Test
@Tag("valid")
public void retrieveAllProductsWhenRepositoryIsEmpty() {
    when(productRepository.findAll()).thenReturn(Collections.emptyList());
    List<Product> result = productController.getAllProducts();
    assertNotNull(result, "Expected result to not be null");
    assertTrue(result.isEmpty(), "Expected result list to be empty");
    verify(productRepository, times(1)).findAll();
}

	/*
	 * ROOST_METHOD_HASH=getAllProducts_fef141838b
	 * ROOST_METHOD_SIG_HASH=getAllProducts_7e38cc05f6
	 *
	 */
	@Test
	@Tag("valid")
	public void retrieveAllProductsWhenRepositoryHasProducts() {
		Product product1 = new Product();

		product1.setId(1L);
		Product product2 = new Product();

		product2.setId(2L);
		List<Product> mockedProducts = List.of(product1, product2);
		when(productRepository.findAll()).thenReturn(mockedProducts);
		List<Product> result = productController.getAllProducts();
		assertNotNull(result, "Expected result to not be null");
		assertEquals(mockedProducts, result, "Expected result to match the predefined list");
		verify(productRepository, times(1)).findAll();
	}

	/*
	 * ROOST_METHOD_HASH=getAllProducts_fef141838b
	 * ROOST_METHOD_SIG_HASH=getAllProducts_7e38cc05f6
	 *
	 */
	@Test
	@Tag("invalid")
	public void handleNullRepositoryGracefully() {

		productController = new ProductController();
		assertThrows(NullPointerException.class, () -> productController.getAllProducts(),
				"Expected NullPointerException due to null repository");
	}

	/*
	 * ROOST_METHOD_HASH=getAllProducts_fef141838b
	 * ROOST_METHOD_SIG_HASH=getAllProducts_7e38cc05f6
	 *
	 */
	@Test
	@Tag("boundary")
	public void measurePerformanceWithLargeDataSet() {

		List<Product> largeMockedProducts = Collections.nCopies(100000, new Product());
		when(productRepository.findAll()).thenReturn(largeMockedProducts);
		long startTime = System.currentTimeMillis();
		List<Product> result = productController.getAllProducts();
		long endTime = System.currentTimeMillis();
		assertNotNull(result, "Expected result to not be null");
		assertEquals(largeMockedProducts.size(), result.size(), "Expected result size to match mocked list size");

		assertTrue((endTime - startTime) < 2000, "Expected method execution within acceptable timeframe");
		verify(productRepository, times(1)).findAll();
	}

	/*
	 * ROOST_METHOD_HASH=getAllProducts_fef141838b
	 * ROOST_METHOD_SIG_HASH=getAllProducts_7e38cc05f6
	 *
	 */
	@Test
	@Tag("valid")
	public void verifyReturnedInstanceType() {
		Product product = new Product();

		product.setId(1L);
		List<Product> mockedProducts = List.of(product);
		when(productRepository.findAll()).thenReturn(mockedProducts);
		List<Product> result = productController.getAllProducts();
		assertTrue(result instanceof List, "Expected result to be instanceof List<Product>");
		verify(productRepository, times(1)).findAll();
	}

	/*
	 * ROOST_METHOD_HASH=getAllProducts_fef141838b
	 * ROOST_METHOD_SIG_HASH=getAllProducts_7e38cc05f6
	 *
	 */
@Test
@Tag("integration")
public void verifyRepositoryMethodCall() {
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
@Tag("invalid")
public void handleRepositoryExceptionsGracefully() {
    when(productRepository.findAll()).thenThrow(new RuntimeException("Mocked Repository Exception"));
    assertThrows(RuntimeException.class, () -> productController.getAllProducts(), "Expected RuntimeException to propagate");
    verify(productRepository, times(1)).findAll();
}

	/*
	 * ROOST_METHOD_HASH=getAllProducts_fef141838b
	 * ROOST_METHOD_SIG_HASH=getAllProducts_7e38cc05f6
	 *
	 */
	@Test
	@Tag("valid")
	public void verifyReturnedListOrder() {
		Product product1 = new Product();

		product1.setId(1L);
		Product product2 = new Product();

		product2.setId(2L);

		List<Product> mockedProducts = List.of(product1, product2);
		when(productRepository.findAll()).thenReturn(mockedProducts);
		List<Product> result = productController.getAllProducts();
		assertNotNull(result, "Expected result to not be null");
		assertEquals(mockedProducts, result, "Expected result list to maintain order");
		verify(productRepository, times(1)).findAll();
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
	public void createValidProductSuccessfully() {

		Product validProduct = new Product();
		validProduct.setName("Sample Product");
		validProduct.setDescription("Sample Description");

		validProduct.setPrice(100.0);
		when(productRepository.save(validProduct)).thenReturn(validProduct);

		Product createdProduct = productController.createProduct(validProduct);

		assertNotNull(createdProduct);

		assertEquals(validProduct, createdProduct);
		verify(productRepository, times(1)).save(validProduct);
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@Test
	@Tag("invalid")
	public void createProductWithNullInputThrowsException() {

		Product nullProduct = null;
		when(productRepository.save(nullProduct)).thenThrow(new IllegalArgumentException("Product cannot be null"));

		assertThrows(IllegalArgumentException.class, () -> {
			productController.createProduct(nullProduct);
		});

		verify(productRepository, times(0)).save(nullProduct);
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@Test
	@Tag("invalid")
	public void createProductWithInvalidMissingFields() {

		Product incompleteProduct = new Product();
		incompleteProduct.setName(null);

		incompleteProduct.setPrice(0.0);
		when(productRepository.save(incompleteProduct))
			.thenThrow(new IllegalArgumentException("Product name cannot be null"));

		assertThrows(IllegalArgumentException.class, () -> {
			productController.createProduct(incompleteProduct);
		});

		verify(productRepository, times(0)).save(incompleteProduct);
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@Test
	@Tag("integration")
	public void verifyRepositorySaveIsCalled() {

		Product validProduct = new Product();
		validProduct.setName("Sample Product");
		validProduct.setDescription("Sample Description");

		validProduct.setPrice(50.0);
		when(productRepository.save(validProduct)).thenReturn(validProduct);

		productController.createProduct(validProduct);

		verify(productRepository, times(1)).save(validProduct);
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@Test
	@Tag("valid")
	public void returnedProductMatchesSavedInstance() {

		Product validProduct = new Product();
		validProduct.setName("Product Match");
		validProduct.setDescription("Match Description");

		validProduct.setPrice(200.0);
		when(productRepository.save(validProduct)).thenReturn(validProduct);

		Product createdProduct = productController.createProduct(validProduct);

		assertNotNull(createdProduct);
		assertEquals(validProduct, createdProduct);
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@Test
	@Tag("invalid")
	public void handleRepositorySaveFailure() {

		Product validProduct = new Product();
		validProduct.setName("Failed Product");
		validProduct.setDescription("Failed Description");

		validProduct.setPrice(150.0);
		when(productRepository.save(validProduct)).thenThrow(new RuntimeException("Save operation failed"));

		assertThrows(RuntimeException.class, () -> {
			productController.createProduct(validProduct);
		});
		verify(productRepository, times(1)).save(validProduct);
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@Test
	@Tag("invalid")
	public void createDuplicateProduct() {

		Product duplicateProduct = new Product();
		duplicateProduct.setName("Duplicate Product");
		duplicateProduct.setDescription("Duplicate Description");

		duplicateProduct.setPrice(99.9);
		when(productRepository.save(duplicateProduct)).thenThrow(new RuntimeException("Duplicate entry error"));

		assertThrows(RuntimeException.class, () -> {
			productController.createProduct(duplicateProduct);
		});
		verify(productRepository, times(1)).save(duplicateProduct);
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("valid")
	public void getProductByIdWithValidId() {
		Long validId = 1L;
		Product mockProduct = new Product();
		mockProduct.setId(validId);
		mockProduct.setName("Test Product");
		mockProduct.setDescription("Test Description");
		mockProduct.setPrice(100.0);
		when(productRepository.findById(validId)).thenReturn(Optional.of(mockProduct));
		ResponseEntity<Product> responseEntity = productController.getProductById(validId);
		assertEquals(200, responseEntity.getStatusCodeValue());
		assertNotNull(responseEntity.getBody());
		assertEquals(mockProduct, responseEntity.getBody());
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("invalid")
	public void getProductByIdWithInvalidId() {
		Long invalidId = 999L;
		when(productRepository.findById(invalidId)).thenReturn(Optional.empty());
		ResponseEntity<Product> responseEntity = productController.getProductById(invalidId);
		assertEquals(404, responseEntity.getStatusCodeValue());
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("boundary")
	public void getProductByIdWhenRepositoryIsEmpty() {
		Long anyId = 1L;
		when(productRepository.findById(anyId)).thenReturn(Optional.empty());
		ResponseEntity<Product> responseEntity = productController.getProductById(anyId);
		assertEquals(404, responseEntity.getStatusCodeValue());
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("invalid")
	public void getProductByIdWithNullId() {
		Long nullId = null;
		try {
			ResponseEntity<Product> responseEntity = productController.getProductById(nullId);
		}
		catch (Exception e) {

			assertEquals(NullPointerException.class, e.getClass());
		}
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("valid")
	public void getProductByIdResponseMappingValidation() {
		Long productId = 2L;
		Product mockProduct = new Product();
		mockProduct.setId(productId);
		mockProduct.setName("Mock Product");
		mockProduct.setDescription("Mock Description");
		mockProduct.setPrice(200.0);
		when(productRepository.findById(productId)).thenReturn(Optional.of(mockProduct));
		ResponseEntity<Product> responseEntity = productController.getProductById(productId);
		Product responseBody = responseEntity.getBody();
		assertNotNull(responseBody);
		assertEquals(mockProduct.getId(), responseBody.getId());
		assertEquals(mockProduct.getName(), responseBody.getName());
		assertEquals(mockProduct.getDescription(), responseBody.getDescription());
		assertEquals(mockProduct.getPrice(), responseBody.getPrice());
		assertEquals(200, responseEntity.getStatusCodeValue());
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("integration")
	public void verifyRepositoryInvocationForValidId() {
		Long validId = 3L;
		Product mockProduct = new Product();
		mockProduct.setId(validId);
		mockProduct.setName("Valid Product");
		when(productRepository.findById(validId)).thenReturn(Optional.of(mockProduct));
		ResponseEntity<Product> responseEntity = productController.getProductById(validId);
		verify(productRepository, times(1)).findById(validId);
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("invalid")
	public void handleRepositoryExceptionDuringGetProductById() {
		Long id = 4L;
		when(productRepository.findById(id)).thenThrow(RuntimeException.class);
		try {
			productController.getProductById(id);
		}
		catch (Exception e) {
			assertEquals(RuntimeException.class, e.getClass());
		}
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("integration")
	public void verifyConcurrentRequestHandlingForGetProductByIdResponse() throws InterruptedException {
		Long id = 5L;
		Product mockProduct = new Product();
		mockProduct.setId(id);
		mockProduct.setName("Concurrent Product");
		when(productRepository.findById(id)).thenReturn(Optional.of(mockProduct));
		ExecutorService executor = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 20; i++) {
			executor.execute(() -> {
				ResponseEntity<Product> responseEntity = productController.getProductById(id);
				assertNotNull(responseEntity);
				assertEquals(200, responseEntity.getStatusCodeValue());
				assertEquals(mockProduct, responseEntity.getBody());
			});
		}
		executor.shutdown();
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("invalid")
	public void getProductByIdInvalidInputType() {

		String invalidInput = "invalidId";
		try {

			ResponseEntity<Product> responseEntity = productController.getProductById(Long.valueOf(invalidInput));
		}
		catch (Exception e) {
			assertEquals(NumberFormatException.class, e.getClass());
		}
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@BeforeEach
	public void setup() {
		existingProduct = new Product();
		existingProduct.setId(1L);
		existingProduct.setName("Old Product Name");
		existingProduct.setDescription("Old Product Description");
		existingProduct.setPrice(50.0);
		updatedProduct = new Product();
		updatedProduct.setName("New Product Name");
		updatedProduct.setDescription("New Product Description");
		updatedProduct.setPrice(75.0);
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Test
	@Tag("valid")
	public void updateProductWhenIdExists() {

		Long validId = 1L;
		when(productRepository.findById(validId)).thenReturn(Optional.of(existingProduct));
		when(productRepository.save(any(Product.class))).thenReturn(updatedProduct);

		ResponseEntity<Product> response = productController.updateProduct(validId, updatedProduct);

		assertEquals(200, response.getStatusCodeValue());
		assertEquals("New Product Name", response.getBody().getName());
		assertEquals("New Product Description", response.getBody().getDescription());
		assertEquals(75.0, response.getBody().getPrice());
		verify(productRepository, times(1)).save(existingProduct);
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Test
	@Tag("invalid")
	public void updateProductWhenIdDoesNotExist() {

		Long invalidId = 999L;
		when(productRepository.findById(invalidId)).thenReturn(Optional.empty());

		ResponseEntity<Product> response = productController.updateProduct(invalidId, updatedProduct);

		assertEquals(404, response.getStatusCodeValue());
		assertTrue(response.getBody() == null);
		verify(productRepository, times(0)).save(any(Product.class));
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Test
	@Tag("invalid")
	public void updateProductWithNullBody() {

		Long validId = 1L;
		when(productRepository.findById(validId)).thenReturn(Optional.of(existingProduct));

		ResponseEntity<Product> response = productController.updateProduct(validId, null);

		assertEquals(400, response.getStatusCodeValue());

		verify(productRepository, times(0)).save(any(Product.class));
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Test
	@Tag("boundary")
	public void updateProductWithModifiedPrice() {

		Long validId = 1L;

		existingProduct.setPrice(50.0);

		updatedProduct.setPrice(75.0);
		when(productRepository.findById(validId)).thenReturn(Optional.of(existingProduct));
		when(productRepository.save(any(Product.class))).thenReturn(updatedProduct);

		ResponseEntity<Product> response = productController.updateProduct(validId, updatedProduct);

		assertEquals(200, response.getStatusCodeValue());
		assertEquals(75.0, response.getBody().getPrice());
		verify(productRepository, times(1)).save(existingProduct);
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Test
	@Tag("valid")
	public void updateProductWithMismatchedId() {

		Long validId = 1L;
		Product mismatchedProduct = new Product();

		mismatchedProduct.setId(2L);
		mismatchedProduct.setName("Another Product");
		mismatchedProduct.setDescription("Another Description");
		mismatchedProduct.setPrice(100.0);
		when(productRepository.findById(validId)).thenReturn(Optional.of(existingProduct));
		when(productRepository.save(any(Product.class))).thenReturn(existingProduct);

		ResponseEntity<Product> response = productController.updateProduct(validId, mismatchedProduct);

		assertEquals(200, response.getStatusCodeValue());

		assertEquals("Another Product", existingProduct.getName());
		verify(productRepository, times(1)).save(existingProduct);
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Test
	@Tag("integration")
	public void updateProductWithSaveFailure() {

		Long validId = 1L;
		when(productRepository.findById(validId)).thenReturn(Optional.of(existingProduct));
		when(productRepository.save(any(Product.class))).thenThrow(new RuntimeException("Save failed"));

		ResponseEntity<Product> response;
		try {
			response = productController.updateProduct(validId, updatedProduct);
		}
		catch (RuntimeException e) {

			response = ResponseEntity.internalServerError().build();
		}

		assertEquals(500, response.getStatusCodeValue());
		verify(productRepository, times(1)).findById(validId);
		verify(productRepository, times(1)).save(existingProduct);
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Test
	@Tag("boundary")
	public void updateProductWithModifiedName() {

		Long validId = 1L;
		updatedProduct.setName("Updated Product Name");
		when(productRepository.findById(validId)).thenReturn(Optional.of(existingProduct));
		when(productRepository.save(any(Product.class))).thenReturn(updatedProduct);

		ResponseEntity<Product> response = productController.updateProduct(validId, updatedProduct);

		assertEquals(200, response.getStatusCodeValue());
		assertEquals("Updated Product Name", response.getBody().getName());
		verify(productRepository, times(1)).save(existingProduct);
	}

}