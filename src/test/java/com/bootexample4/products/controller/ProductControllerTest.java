package com.bootexample4.products.controller;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.springframework.http.ResponseEntity;
import com.bootexample4.products.model.Product;
import com.bootexample4.products.repository.ProductRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@Tag("integration")
public class ProductControllerTest {

	// Merged class fields
	private ProductRepository productRepository;

	private ProductController productController;

	Product mockProduct = new Product();

	Long productId = 1L;

	Product updatedProductMock = new Product();

	ResponseEntity<Product> response = productController.updateProduct(productId, updatedProductMock);

	Long invalidProductId = 999L;

	// Merged constructors
	public ProductControllerGetProductByIdTest() {
    MockitoAnnotations.openMocks(this);
}

	// Merged test methods

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("valid")
	public void getProductByIdWhenProductExists() {

		Long validId = 1L;
		Product mockProduct = new Product();
		mockProduct.setId(validId);
		mockProduct.setName("ProductName");
		mockProduct.setDescription("ProductDescription");
		mockProduct.setPrice(100.0);
		when(productRepository.findById(validId)).thenReturn(Optional.of(mockProduct));
		ResponseEntity<Product> response = productController.getProductById(validId);
		assertEquals(ResponseEntity.ok(mockProduct), response);
		verify(productRepository, times(1)).findById(validId);
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("invalid")
	public void getProductByIdWhenProductDoesNotExist() {

		Long invalidId = 999L;
		when(productRepository.findById(invalidId)).thenReturn(Optional.empty());
		ResponseEntity<Product> response = productController.getProductById(invalidId);
		assertEquals(ResponseEntity.notFound().build(), response);
		verify(productRepository, times(1)).findById(invalidId);
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("invalid")
	public void getProductByIdWhenIdIsNull() {
		Long nullId = null;
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> productController.getProductById(nullId));

		assertEquals("Invalid ID", exception.getMessage());
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("boundary")
	public void getProductByIdWhenIdIsNegative() {
		Long negativeId = -1L;
		when(productRepository.findById(negativeId)).thenReturn(Optional.empty());
		ResponseEntity<Product> response = productController.getProductById(negativeId);
		assertEquals(ResponseEntity.notFound().build(), response);
		verify(productRepository, times(1)).findById(negativeId);
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("boundary")
	public void getProductByIdAfterProductDeletion() {

		Long productId = 2L;
		Product mockProduct = new Product();
		mockProduct.setId(productId);
		mockProduct.setName("ProductName");
		mockProduct.setDescription("ProductDescription");
		mockProduct.setPrice(200.0);
		when(productRepository.findById(productId)).thenReturn(Optional.of(mockProduct)).thenReturn(Optional.empty());
		doNothing().when(productRepository).delete(mockProduct);

		productRepository.delete(mockProduct);
		ResponseEntity<Product> response = productController.getProductById(productId);
		assertEquals(ResponseEntity.notFound().build(), response);

		verify(productRepository, times(2)).findById(productId);
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("boundary")
	public void getProductByIdWhenIdIsOutOfRange() {

		Long outOfRangeId = Long.MAX_VALUE;
		when(productRepository.findById(outOfRangeId)).thenReturn(Optional.empty());
		ResponseEntity<Product> response = productController.getProductById(outOfRangeId);
		assertEquals(ResponseEntity.notFound().build(), response);
		verify(productRepository, times(1)).findById(outOfRangeId);
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("integration")
	public void getProductByIdCallsRepositoryCorrectly() {

		Long validId = 3L;
		Product mockProduct = new Product();
		mockProduct.setId(validId);
		mockProduct.setName("ProductName");
		mockProduct.setDescription("ProductDescription");
		mockProduct.setPrice(300.0);
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
	@Tag("integration")
	public void getProductByIdWhenDatabaseUnavailable() {

		Long validId = 4L;
		when(productRepository.findById(validId)).thenThrow(new RuntimeException("Database unavailable"));
		RuntimeException exception = assertThrows(RuntimeException.class,
				() -> productController.getProductById(validId));
		assertEquals("Database unavailable", exception.getMessage());
		verify(productRepository, times(1)).findById(validId);
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("valid")
	public void getProductByIdWhenMultipleProductsExist() {

		Long validId = 5L;
		Product mockProduct = new Product();
		mockProduct.setId(validId);
		mockProduct.setName("ProductName");
		mockProduct.setDescription("ProductDescription");
		mockProduct.setPrice(400.0);
		when(productRepository.findById(validId)).thenReturn(Optional.of(mockProduct));
		ResponseEntity<Product> response = productController.getProductById(validId);
		assertEquals(ResponseEntity.ok(mockProduct), response);
		verify(productRepository, times(1)).findById(validId);
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Test
	@Tag("valid")
	public void updateProductSuccessfully() {

		Long productId = 1L;
		Product mockExistingProduct = new Product();
		mockExistingProduct.setName("Old Product Name");
		mockExistingProduct.setDescription("Old Description");

		mockExistingProduct.setPrice(100.0);
		Product updatedProductMock = new Product();
		updatedProductMock.setName("Updated Product Name");
		updatedProductMock.setDescription("Updated Description");

		updatedProductMock.setPrice(150.0);
		when(productRepository.findById(productId)).thenReturn(java.util.Optional.of(mockExistingProduct));
		when(productRepository.save(mockExistingProduct)).thenReturn(updatedProductMock);

		ResponseEntity<Product> response = productController.updateProduct(productId, updatedProductMock);

		assertThat(response).isNotNull();
		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().getName()).isEqualTo((String) updatedProductMock.getName());
		assertThat(response.getBody().getDescription()).isEqualTo((String) updatedProductMock.getDescription());
		assertThat(response.getBody().getPrice()).isEqualTo((Double) updatedProductMock.getPrice());
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Test
	@Tag("invalid")
	public void updateProductWithNonExistentIDShouldReturnNotFound() {

		Long invalidProductId = 999L;
		Product updatedProductMock = new Product();
		updatedProductMock.setName("Updated Product Name");
		updatedProductMock.setDescription("Updated Description");

		updatedProductMock.setPrice(200.0);
		when(productRepository.findById(invalidProductId)).thenReturn(java.util.Optional.empty());

		ResponseEntity<Product> response = productController.updateProduct(invalidProductId, updatedProductMock);

		assertThat(response).isNotNull();
		assertThat(response.getStatusCodeValue()).isEqualTo(404);
		assertThat(response.getBody()).isNull();
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Test
	@Tag("boundary")
	public void updateProductWithNullValuesShouldUpdateFields() {

		Long productId = 1L;
		Product mockExistingProduct = new Product();
		mockExistingProduct.setName("Old Product Name");
		mockExistingProduct.setDescription("Old Description");

		mockExistingProduct.setPrice(100.0);
		Product updatedProductMock = new Product();

		updatedProductMock.setName(null);

		updatedProductMock.setDescription(null);

		updatedProductMock.setPrice(150.0);
		when(productRepository.findById(productId)).thenReturn(java.util.Optional.of(mockExistingProduct));
		when(productRepository.save(mockExistingProduct)).thenReturn(mockExistingProduct);

		ResponseEntity<Product> response = productController.updateProduct(productId, updatedProductMock);

		assertThat(response).isNotNull();
		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		assertThat(response.getBody()).isNotNull();

		assertThat(response.getBody().getName()).isEqualTo((String) null);

		assertThat(response.getBody().getDescription()).isEqualTo((String) null);

		assertThat(response.getBody().getPrice()).isEqualTo((Double) mockExistingProduct.getPrice());
	}

}