package com.bootexample4.products.controller;

import com.bootexample4.products.model.Product;
import com.bootexample4.products.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

	// Merged class fields
	private ProductRepository productRepository;

	private ProductController productController;

	private Product sampleProduct;

	Long validProductId = 1L;

	ResponseEntity<Product> response = productController.getProductById(validProductId);

	Long invalidProductId = 999L;

	Long validId = 1L;

	Long nonExistentId = 2L;

	Long invalidId = -1L;

	Product mockProduct = new Product();

	ResponseEntity<Object> responseEntity = productController.deleteProduct(validId);

	// Merged test methods

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@BeforeEach
	public void setUp() {
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

		Product inputProduct = new Product();
		inputProduct.setName("Valid Name");
		inputProduct.setDescription("Valid Description");
		inputProduct.setPrice(100.0);
		Product savedProduct = new Product();
		savedProduct.setName("Valid Name");
		savedProduct.setDescription("Valid Description");
		savedProduct.setPrice(100.0);
		when(productRepository.save(inputProduct)).thenReturn(savedProduct);
		Product result = productController.createProduct(inputProduct);
		assertThat(result).isEqualTo(savedProduct);
		verify(productRepository, times(1)).save(inputProduct);
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@Test
	@Tag("invalid")
	public void createProductWithNullFields() {

		Product inputProduct = new Product();
		assertThrows(IllegalArgumentException.class, () -> productController.createProduct(inputProduct),
				"Expected IllegalArgumentException for null fields");
		verify(productRepository, never()).save(any());
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@Test
	@Tag("valid")
	public void verifyProductRepositoryIsCalled() {
		Product inputProduct = new Product();
		inputProduct.setName("Unique Name");
		inputProduct.setDescription("Some Description");
		inputProduct.setPrice(200.0);
		when(productRepository.save(inputProduct)).thenAnswer(i -> i.getArgument(0));
		productController.createProduct(inputProduct);
		verify(productRepository, times(1)).save(inputProduct);
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@Test
	@Tag("invalid")
	public void handleExceptionFromRepositoryWhileSaving() {

		Product inputProduct = new Product();
		inputProduct.setName("Exception Test Product");
		inputProduct.setDescription("Test Description");
		inputProduct.setPrice(250.0);
		doThrow(new RuntimeException("Database Error")).when(productRepository).save(inputProduct);
		assertThrows(RuntimeException.class, () -> productController.createProduct(inputProduct),
				"Expected RuntimeException for database error");
		verify(productRepository, times(1)).save(inputProduct);
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
		duplicateProduct.setName("Existing Product");
		duplicateProduct.setDescription("Duplicate Description");
		duplicateProduct.setPrice(300.0);
		doThrow(new IllegalArgumentException("Duplicate Product")).when(productRepository).save(duplicateProduct);
		assertThrows(IllegalArgumentException.class, () -> productController.createProduct(duplicateProduct),
				"Expected IllegalArgumentException for duplicate product");
		verify(productRepository, times(1)).save(duplicateProduct);
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@Test
	@Tag("valid")
	public void validateReturnedProductData() {
		Product inputProduct = new Product();
		inputProduct.setName("Valid Name");
		inputProduct.setDescription("Valid Description");
		inputProduct.setPrice(400.0);
		when(productRepository.save(inputProduct)).thenReturn(inputProduct);
		Product result = productController.createProduct(inputProduct);
		assertThat(result).isEqualTo(inputProduct);
		verify(productRepository, times(1)).save(inputProduct);
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@Test
	@Tag("invalid")
	public void createNullProduct() {
		assertThrows(IllegalArgumentException.class, () -> productController.createProduct(null),
				"Expected IllegalArgumentException for null product");
		verify(productRepository, never()).save(any());
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@Test
	@Tag("integration")
	public void handleRepositoryUnavailability() {
		Product inputProduct = new Product();
		inputProduct.setName("Test Name");
		inputProduct.setDescription("Test Description");
		inputProduct.setPrice(500.0);
		doThrow(new RuntimeException("Repository Unavailable")).when(productRepository).save(inputProduct);
		assertThrows(RuntimeException.class, () -> productController.createProduct(inputProduct),
				"Expected RuntimeException for repository unavailability");
		verify(productRepository, times(1)).save(inputProduct);
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@Test
	@Tag("invalid")
	public void createProductWithInvalidFieldTypes() {
		Product invalidProduct = new Product();

		invalidProduct.setName("Invalid");

		invalidProduct.setDescription("Invalid");

		invalidProduct.setPrice(Double.parseDouble("NaN"));
		assertThrows(NumberFormatException.class, () -> productController.createProduct(invalidProduct),
				"Expected NumberFormatException for invalid field types");
		verify(productRepository, never()).save(any());
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@Test
	@Tag("integration")
	public void createProductHandlesHttpRequestBodyCorrectly() {

		Product httpRequestProduct = new Product();
		httpRequestProduct.setName("Requested Product");
		httpRequestProduct.setDescription("Requested Description");
		httpRequestProduct.setPrice(600.0);
		when(productRepository.save(httpRequestProduct)).thenReturn(httpRequestProduct);
		Product result = productController.createProduct(httpRequestProduct);
		assertThat(result.getName()).isEqualTo(httpRequestProduct.getName());
		assertThat(result.getDescription()).isEqualTo(httpRequestProduct.getDescription());
		assertThat(result.getPrice()).isEqualTo(httpRequestProduct.getPrice());
		verify(productRepository, times(1)).save(httpRequestProduct);
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);

		sampleProduct = new Product();

		sampleProduct.setId(1L);
		sampleProduct.setName("Test Product");
		sampleProduct.setDescription("Sample product description");
		sampleProduct.setPrice(29.99);
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("valid")
	public void validProductIdReturnsProductDetails() {

		Long validProductId = 1L;
		when(productRepository.findById(validProductId)).thenReturn(Optional.of(sampleProduct));

		ResponseEntity<Product> response = productController.getProductById(validProductId);

		assertEquals(HttpStatus.OK, response.getStatusCode(), "Expected HTTP status code 200");
		assertEquals(sampleProduct, response.getBody(), "Returned product should match the sample product");
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("invalid")
	public void invalidProductIdReturnsNotFound() {

		Long invalidProductId = 999L;
		when(productRepository.findById(invalidProductId)).thenReturn(Optional.empty());

		ResponseEntity<Product> response = productController.getProductById(invalidProductId);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), "Expected HTTP status code 404");
		assertEquals(null, response.getBody(), "Response body should be null for an invalid product ID");
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Test
	@Tag("valid")
	public void updateProductWithValidData() {

		Long validId = 1L;
		Product existingProduct = new Product();
		existingProduct.setId(validId);
		existingProduct.setName("Old Name");
		existingProduct.setDescription("Old Description");
		existingProduct.setPrice(100.0);
		Product updatedProductRequest = new Product();
		updatedProductRequest.setId(validId);
		updatedProductRequest.setName("New Name");
		updatedProductRequest.setDescription("New Description");
		updatedProductRequest.setPrice(150.0);
		when(productRepository.findById(validId)).thenReturn(Optional.of(existingProduct));
		when(productRepository.save(existingProduct)).thenReturn(updatedProductRequest);

		ResponseEntity<Product> response = productController.updateProduct(validId, updatedProductRequest);

		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		assertThat(response.getBody().getName()).isEqualTo(updatedProductRequest.getName());
		assertThat(response.getBody().getDescription()).isEqualTo(updatedProductRequest.getDescription());
		assertThat(response.getBody().getPrice()).isEqualTo((double) updatedProductRequest.getPrice());
		verify(productRepository).findById(validId);
		verify(productRepository).save(existingProduct);
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Test
	@Tag("invalid")
	public void updateNonExistentProduct() {

		Long nonExistentId = 2L;
		Product product = new Product();
		product.setId(nonExistentId);
		product.setName("Name");
		product.setDescription("Description");
		product.setPrice(200.0);
		when(productRepository.findById(nonExistentId)).thenReturn(Optional.empty());

		ResponseEntity<Product> response = productController.updateProduct(nonExistentId, product);

		assertThat(response.getStatusCodeValue()).isEqualTo(404);
		verify(productRepository).findById(nonExistentId);
		verifyNoMoreInteractions(productRepository);
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Test
	@Tag("valid")
	public void updateProductWithNullValues() {

	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Test
	@Tag("boundary")
	public void updateProductWithInvalidIdFormat() {

		Long invalidId = -1L;
		Product product = new Product();
		product.setId(invalidId);
		product.setName("Name");
		product.setDescription("Description");
		product.setPrice(200.0);
		when(productRepository.findById(invalidId)).thenReturn(Optional.empty());

		ResponseEntity<Product> response = productController.updateProduct(invalidId, product);

		assertThat(response.getStatusCodeValue()).isEqualTo(404);
		verify(productRepository).findById(invalidId);
		verifyNoMoreInteractions(productRepository);
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Test
	@Tag("valid")
	public void updateProductWithEmptyFields() {

		Long validId = 1L;
		Product existingProduct = new Product();
		existingProduct.setId(validId);
		existingProduct.setName("Old Name");
		existingProduct.setDescription("Old Description");
		existingProduct.setPrice(100.0);
		Product updatedProductRequest = new Product();
		updatedProductRequest.setId(validId);
		updatedProductRequest.setName("");
		updatedProductRequest.setDescription("");
		updatedProductRequest.setPrice(100.0);
		when(productRepository.findById(validId)).thenReturn(Optional.of(existingProduct));
		when(productRepository.save(existingProduct)).thenReturn(updatedProductRequest);

		ResponseEntity<Product> response = productController.updateProduct(validId, updatedProductRequest);

		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		assertThat(response.getBody().getName()).isEqualTo(updatedProductRequest.getName());
		assertThat(response.getBody().getDescription()).isEqualTo(updatedProductRequest.getDescription());
		assertThat(response.getBody().getPrice()).isEqualTo((double) updatedProductRequest.getPrice());
		verify(productRepository).findById(validId);
		verify(productRepository).save(existingProduct);
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Test
	@Tag("invalid")
	public void updateProductWhenRepositoryThrowsException() {

		Long validId = 1L;
		Product updatedProductRequest = new Product();
		updatedProductRequest.setId(validId);
		updatedProductRequest.setName("New Name");
		updatedProductRequest.setDescription("New Description");
		updatedProductRequest.setPrice(150.0);
		when(productRepository.findById(validId)).thenThrow(new RuntimeException("Database error!"));

		try {
			productController.updateProduct(validId, updatedProductRequest);
		}
		catch (Exception e) {
			assertThat(e).isInstanceOf(RuntimeException.class).hasMessage("Database error!");
		}
		verify(productRepository).findById(validId);
		verifyNoMoreInteractions(productRepository);
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
	public void deleteExistingProductById() {

		Long validId = 1L;

		Product mockProduct = new Product();
		when(productRepository.findById(validId)).thenReturn(Optional.of(mockProduct));
		doNothing().when(productRepository).delete(mockProduct);

		ResponseEntity<Object> responseEntity = productController.deleteProduct(validId);

		assertEquals(200, responseEntity.getStatusCodeValue());
		verify(productRepository, times(1)).delete(mockProduct);
	}

	/*
	 * ROOST_METHOD_HASH=deleteProduct_5ea3a876a4
	 * ROOST_METHOD_SIG_HASH=deleteProduct_dcaff736d4
	 *
	 */
	@Test
	@Tag("invalid")
	public void deleteProductByNonExistingId() {

		Long invalidId = 99L;
		when(productRepository.findById(invalidId)).thenReturn(Optional.empty());

		ResponseEntity<Object> responseEntity = productController.deleteProduct(invalidId);

		assertEquals(404, responseEntity.getStatusCodeValue());
		verify(productRepository, never()).delete(any(Product.class));
	}

}