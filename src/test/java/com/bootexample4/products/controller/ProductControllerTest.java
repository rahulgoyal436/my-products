package com.bootexample4.products.controller;

import com.bootexample4.products.model.Product;
import com.bootexample4.products.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.util.Optional;
import org.junit.jupiter.api.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.mockito.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

	// Merged class fields
	private ProductController productController;

	private ProductRepository productRepository;

	Long validProductId = 1L;

	Product mockProduct = new Product();

	ResponseEntity<Product> response = productController.getProductById(validProductId);

	Long invalidProductId = -1L;

	Long boundaryProductId = Long.MAX_VALUE;

	Product existingProduct = new Product();

	Product productDetails = new Product();

	private Long VALID_PRODUCT_ID = 1L;

	private Long INVALID_PRODUCT_ID = 999L;

	private Product mockedProduct;

	// Merged test methods

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("valid")
	public void retrieveProductByValidId() {

		Long validProductId = 1L;
		Product mockProduct = new Product();
		mockProduct.setId(validProductId);
		mockProduct.setName("Test Name");
		mockProduct.setDescription("Test Description");
		mockProduct.setPrice(100.0);
		when(productRepository.findById(validProductId)).thenReturn(Optional.of(mockProduct));

		ResponseEntity<Product> response = productController.getProductById(validProductId);

		assertNotNull(response);
		assertEquals(ResponseEntity.ok(mockProduct), response);
		assertEquals(mockProduct, response.getBody());
		assertEquals(200, response.getStatusCodeValue());
		verify(productRepository, times(1)).findById(validProductId);
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("invalid")
	public void retrieveProductByInvalidId() {

		Long invalidProductId = -1L;
		when(productRepository.findById(invalidProductId)).thenReturn(Optional.empty());

		ResponseEntity<Product> response = productController.getProductById(invalidProductId);

		assertEquals(ResponseEntity.notFound().build(), response);
		assertEquals(404, response.getStatusCodeValue());
		verify(productRepository, times(1)).findById(invalidProductId);
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("boundary")
	public void retrieveProductWithBoundaryId() {

		Long boundaryProductId = Long.MAX_VALUE;
		when(productRepository.findById(boundaryProductId)).thenReturn(Optional.empty());

		ResponseEntity<Product> response = productController.getProductById(boundaryProductId);

		assertEquals(ResponseEntity.notFound().build(), response);
		assertEquals(404, response.getStatusCodeValue());
		verify(productRepository, times(1)).findById(boundaryProductId);
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@BeforeAll
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
	void updateProductWithValidDetails() {

		Product existingProduct = new Product();
		existingProduct.setId(1L);
		existingProduct.setName("Product A");
		existingProduct.setDescription("Description A");
		existingProduct.setPrice(100.0);
		Product updatedProductDetails = new Product();
		updatedProductDetails.setName("Updated Product");
		updatedProductDetails.setDescription("Updated Description");
		updatedProductDetails.setPrice(150.0);
		when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
		when(productRepository.save(existingProduct)).thenReturn(existingProduct);

		ResponseEntity<Product> response = productController.updateProduct(1L, updatedProductDetails);

		verify(productRepository).findById(1L);
		verify(productRepository).save(existingProduct);
		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		assertThat(response.getBody().getName()).isEqualTo("Updated Product");
		assertThat(response.getBody().getDescription()).isEqualTo("Updated Description");
		assertThat(response.getBody().getPrice()).isEqualTo(150.0);
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
@Test
@Tag("invalid")
void updateProductForNonExistentProduct() {

    when(productRepository.findById(1L)).thenReturn(Optional.empty());
    Product productDetails = new Product();
    productDetails.setName("New Product");
    productDetails.setDescription("New Description");
    productDetails.setPrice(200.0);

    ResponseEntity<Product> response = productController.updateProduct(1L, productDetails);

    verify(productRepository).findById(1L);
    assertThat(response.getStatusCodeValue()).isEqualTo(404);
    assertThat(response.getBody()).isNull();
}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Test
	@Tag("valid")
	void updateProductWithNullName() {

		Product existingProduct = new Product();
		existingProduct.setId(1L);
		existingProduct.setName("Product A");
		existingProduct.setDescription("Description A");
		existingProduct.setPrice(100.0);
		Product updatedProductDetails = new Product();
		updatedProductDetails.setName(null);
		updatedProductDetails.setDescription("Updated Description");
		updatedProductDetails.setPrice(150.0);
		when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
		when(productRepository.save(existingProduct)).thenReturn(existingProduct);

		ResponseEntity<Product> response = productController.updateProduct(1L, updatedProductDetails);

		verify(productRepository).findById(1L);
		verify(productRepository).save(existingProduct);
		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		assertThat(response.getBody().getName()).isNull();
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Test
	@Tag("valid")
	void updateProductWithNegativePrice() {

		Product existingProduct = new Product();
		existingProduct.setId(1L);
		existingProduct.setName("Product A");
		existingProduct.setDescription("Description A");
		existingProduct.setPrice(100.0);
		Product updatedProductDetails = new Product();
		updatedProductDetails.setName("Updated Product");
		updatedProductDetails.setDescription("Updated Description");
		updatedProductDetails.setPrice(-50.0);
		when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
		when(productRepository.save(existingProduct)).thenReturn(existingProduct);

		ResponseEntity<Product> response = productController.updateProduct(1L, updatedProductDetails);

		verify(productRepository).findById(1L);
		verify(productRepository).save(existingProduct);
		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		assertThat(response.getBody().getPrice()).isEqualTo(-50.0);
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Test
	@Tag("valid")
	void updateProductWithEmptyDescription() {

		Product existingProduct = new Product();
		existingProduct.setId(1L);
		existingProduct.setName("Product A");
		existingProduct.setDescription("Description A");
		existingProduct.setPrice(100.0);
		Product updatedProductDetails = new Product();
		updatedProductDetails.setName("Updated Product");
		updatedProductDetails.setDescription("");
		updatedProductDetails.setPrice(150.0);
		when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
		when(productRepository.save(existingProduct)).thenReturn(existingProduct);

		ResponseEntity<Product> response = productController.updateProduct(1L, updatedProductDetails);

		verify(productRepository).findById(1L);
		verify(productRepository).save(existingProduct);
		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		assertThat(response.getBody().getDescription()).isEmpty();
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Test
	@Tag("invalid")
	void updateProductWithNullProductDetails() {

		Product existingProduct = new Product();
		existingProduct.setId(1L);
		existingProduct.setName("Product A");
		existingProduct.setDescription("Description A");
		existingProduct.setPrice(100.0);
		when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));

		ResponseEntity<Product> response = productController.updateProduct(1L, null);

		verify(productRepository).findById(1L);

		assertThat(response.getStatusCodeValue()).isEqualTo(400);
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Test
	@Tag("invalid")
	void updateProductWhenSaveFails() {

		Product existingProduct = new Product();
		existingProduct.setId(1L);
		existingProduct.setName("Product A");
		existingProduct.setDescription("Description A");
		existingProduct.setPrice(100.0);
		Product updatedProductDetails = new Product();
		updatedProductDetails.setName("Updated Product");
		updatedProductDetails.setDescription("Updated Description");
		updatedProductDetails.setPrice(150.0);
		when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
		when(productRepository.save(existingProduct)).thenThrow(new RuntimeException("Database Save Error"));

		Assertions.assertThrows(RuntimeException.class, () -> {
			productController.updateProduct(1L, updatedProductDetails);
		});
		verify(productRepository).findById(1L);
		verify(productRepository).save(existingProduct);
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Test
	@Tag("invalid")
	void updateProductWithInvalidIdFormat() {

		Product productDetails = new Product();
		productDetails.setName("Updated Product");
		productDetails.setDescription("Updated Description");
		productDetails.setPrice(150.0);

		ResponseEntity<Product> response = productController.updateProduct(-1L, productDetails);

		assertThat(response.getStatusCodeValue()).isEqualTo(400);
	}

	/*
	 * ROOST_METHOD_HASH=deleteProduct_5ea3a876a4
	 * ROOST_METHOD_SIG_HASH=deleteProduct_dcaff736d4
	 *
	 */
	@BeforeEach
	void setUp() {

		mockedProduct = new Product();

		mockedProduct.setName("Test Product");
		mockedProduct.setDescription("Sample description");
		mockedProduct.setPrice(100.0);
	}

	/*
	 * ROOST_METHOD_HASH=deleteProduct_5ea3a876a4
	 * ROOST_METHOD_SIG_HASH=deleteProduct_dcaff736d4
	 *
	 */
@Test
@Tag("valid")
public void deleteProductWhenProductExists() {

    when(productRepository.findById(VALID_PRODUCT_ID)).thenReturn(Optional.of(mockedProduct));
    doNothing().when(productRepository).delete(mockedProduct);

    ResponseEntity<Object> response = productController.deleteProduct(VALID_PRODUCT_ID);

    verify(productRepository, times(1)).delete(mockedProduct);
    assertEquals(ResponseEntity.ok().build(), response);
}

	/*
	 * ROOST_METHOD_HASH=deleteProduct_5ea3a876a4
	 * ROOST_METHOD_SIG_HASH=deleteProduct_dcaff736d4
	 *
	 */
@Test
@Tag("invalid")
public void deleteProductWhenProductDoesNotExist() {

    when(productRepository.findById(INVALID_PRODUCT_ID)).thenReturn(Optional.empty());

    ResponseEntity<Object> response = productController.deleteProduct(INVALID_PRODUCT_ID);

    verify(productRepository, never()).delete(any());
    assertEquals(ResponseEntity.notFound().build(), response);
}

}