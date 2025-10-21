package com.bootexample4.products.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.bootexample4.products.model.Product;
import com.bootexample4.products.repository.ProductRepository;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.Optional;
import com.bootexample4.products.controller.ProductController;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

	// Merged class fields
	private ProductRepository productRepository;

	private ProductController productController;

	Product inputProduct = new Product();

	Product savedProduct = new Product();

	Product result = productController.createProduct(inputProduct);

	Long validId = 1L;

	ResponseEntity<Product> response = productController.getProductById(validId);

	Long nonexistentId = 2L;

	Long arbitraryId = 3L;

	Long largeId = Long.MAX_VALUE;

	Long negativeId = -1L;

	Long zeroId = 0L;

	Long duplicateId = 6L;

	Product product2 = new Product();

	Product updatedProduct = new Product();

	private Product existingProduct;

	Long productId = 1L;

	// Merged test methodspublic void the_client_sends_a_post_request_to(String string) {

	savedProduct=productController.createProduct(newProduct);
}

	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	public void createProductWithValidInput() {
		// Arrange
		Product inputProduct = new Product();
		inputProduct.setName("Test Product"); // TODO: Change test data if needed
		inputProduct.setDescription("Test Description");
		inputProduct.setPrice(100.0); // TODO: Adjust price value if needed
		Product savedProduct = new Product();
		savedProduct.setName("Test Product");
		savedProduct.setDescription("Test Description");
		savedProduct.setPrice(100.0);
		when(productRepository.save(inputProduct)).thenReturn(savedProduct);
		// Act
		Product result = productController.createProduct(inputProduct);
		// Assert
		assertNotNull(result, "Resulting product should not be null");
		assertEquals(savedProduct, result, "The returned product should match the saved product");
		verify(productRepository, times(1)).save(inputProduct);
	}

	public void the_client_sends_a_GET_request_to_get_a_product_by_its_id(String string) {

		Long id = getProductIDfromAPI(string);
		getProductByIdResponse = productController.getProductById(id);
		responseStatusCode = getProductByIdResponse.getStatusCode();
	}

	public void the_product_with_ID_should_be_updated_with_the_provided_details(Long id) {

		Product updatedProduct = productController.getProductById(id).getBody();
		assertEquals(newProduct.getDescription(), updatedProduct.getDescription());
		assertEquals(newProduct.getName(), updatedProduct.getName());
		assertEquals(newProduct.getPrice(), updatedProduct.getPrice());
	}

	public void the_product_with_id_should_no_longer_exist(Long id) {

		getProductByIdResponse = productController.getProductById(id);
		assertEquals(HttpStatus.NOT_FOUND, getProductByIdResponse.getStatusCode());
	}

	void setUp_2() {
		MockitoAnnotations.openMocks(this);
	}

	public void getProductByIdValidIdReturnsProduct() {
		// Arrange
		Long validId = 1L;
		Product product = new Product(); // Assuming Product is a plain old Java object or
											// an entity
		product.setId(validId); // TODO: Ensure that you add a setter for 'id' in the
								// Product class
		product.setName("Test Product");
		product.setDescription("Test Description");
		product.setPrice(100.0); // TODO: Update this value if necessary
		when(productRepository.findById(validId)).thenReturn(Optional.of(product));
		// Act
		ResponseEntity<Product> response = productController.getProductById(validId);
		// Assert
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo(product);
		verify(productRepository, times(1)).findById(validId);
	}

	public void getProductByIdNonexistentIdReturnsNotFound() {
		// Arrange
		Long nonexistentId = 2L;
		when(productRepository.findById(nonexistentId)).thenReturn(Optional.empty());
		// Act
		ResponseEntity<Product> response = productController.getProductById(nonexistentId);
		// Assert
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(response.getBody()).isNull();
		verify(productRepository, times(1)).findById(nonexistentId);
	}

	public void getProductByIdNullIdThrowsException() {
		// Act & Assert
		assertThrows(IllegalArgumentException.class, () -> {
			productController.getProductById(null);
		});
	}

	public void getProductByIdEmptyRepositoryReturnsNotFound() {
		// Arrange
		Long arbitraryId = 3L;
		when(productRepository.findById(arbitraryId)).thenReturn(Optional.empty());
		// Act
		ResponseEntity<Product> response = productController.getProductById(arbitraryId);
		// Assert
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(response.getBody()).isNull();
		verify(productRepository, times(1)).findById(arbitraryId);
	}

	public void getProductByIdLargeIdReturnsNotFound() {
		// Arrange
		Long largeId = Long.MAX_VALUE;
		when(productRepository.findById(largeId)).thenReturn(Optional.empty());
		// Act
		ResponseEntity<Product> response = productController.getProductById(largeId);
		// Assert
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(response.getBody()).isNull();
		verify(productRepository, times(1)).findById(largeId);
	}

	public void getProductByIdNegativeIdReturnsNotFound() {
		// Arrange
		Long negativeId = -1L;
		when(productRepository.findById(negativeId)).thenReturn(Optional.empty());
		// Act
		ResponseEntity<Product> response = productController.getProductById(negativeId);
		// Assert
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(response.getBody()).isNull();
		verify(productRepository, times(1)).findById(negativeId);
	}

	public void getProductByIdZeroIdReturnsNotFound() {
		// Arrange
		Long zeroId = 0L;
		when(productRepository.findById(zeroId)).thenReturn(Optional.empty());
		// Act
		ResponseEntity<Product> response = productController.getProductById(zeroId);
		// Assert
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(response.getBody()).isNull();
		verify(productRepository, times(1)).findById(zeroId);
	}

	public void getProductByIdResponseContainsHeaders() {
		// Arrange
		Long validId = 5L;
		Product product = new Product();
		product.setId(validId); // TODO: Ensure that you add a setter for 'id' in the
								// Product class
		product.setName("Another Product");
		product.setDescription("Description for another product");
		product.setPrice(200.0); // TODO: Update this value if necessary
		when(productRepository.findById(validId)).thenReturn(Optional.of(product));
		// Act
		ResponseEntity<Product> response = productController.getProductById(validId);
		// Assert
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getHeaders()).isNotNull();
		assertThat(response.getBody()).isEqualTo(product);
		verify(productRepository, times(1)).findById(validId);
	}

	public void getProductByIdRepositoryFailureThrowsException() {
		// Arrange
		Long validId = 1L;
		when(productRepository.findById(validId)).thenThrow(new RuntimeException("Database connection failure"));
		// Act & Assert
		assertThrows(RuntimeException.class, () -> {
			productController.getProductById(validId);
		});
		verify(productRepository, times(1)).findById(validId);
	}

	public void getProductByIdDuplicateIdReturnsFirstResult() {
		// Arrange
		Long duplicateId = 6L;
		Product product1 = new Product();
		product1.setId(duplicateId); // TODO: Ensure that you add a setter for 'id' in the
										// Product class
		product1.setName("First Product");
		product1.setDescription("First Product Description");
		product1.setPrice(150.0); // TODO: Update this value if necessary
		Product product2 = new Product();
		product2.setId(duplicateId); // TODO: Ensure that you add a setter for 'id' in the
										// Product class
		product2.setName("Second Product");
		product2.setDescription("Second Product Description");
		product2.setPrice(200.0); // TODO: Update this value if necessary
		when(productRepository.findById(duplicateId)).thenReturn(Optional.of(product1));
		// Act
		ResponseEntity<Product> response = productController.getProductById(duplicateId);
		// Assert
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo(product1);
		verify(productRepository, times(1)).findById(duplicateId);
	}

	public void the_client_sends_a_put_request_to(String string) {

		updateProductResponse = productController.updateProduct(getProductIDfromAPI(string), newProduct);
		responseStatusCode = updateProductResponse.getStatusCode();
	}

	void setup() {
		MockitoAnnotations.openMocks(this);
	}

	public void verifySuccessfulProductUpdateWithValidDetails() {
		Long id = 1L;
		Product existingProduct = new Product();
		existingProduct.setName("Old Name");
		existingProduct.setDescription("Old Description");
		existingProduct.setPrice(100.0);
		Product updatedProductDetails = new Product();
		updatedProductDetails.setName("New Name");
		updatedProductDetails.setDescription("New Description");
		updatedProductDetails.setPrice(200.0);
		Product updatedProduct = new Product();
		updatedProduct.setName("New Name");
		updatedProduct.setDescription("New Description");
		updatedProduct.setPrice(200.0);
		when(productRepository.findById(id)).thenReturn(Optional.of(existingProduct));
		when(productRepository.save(existingProduct)).thenReturn(updatedProduct);
		ResponseEntity<Product> response = productController.updateProduct(id, updatedProductDetails);
		verify(productRepository).findById(id);
		verify(productRepository).save(existingProduct);
		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().getName()).isEqualTo("New Name");
		assertThat(response.getBody().getDescription()).isEqualTo("New Description");
		assertThat(response.getBody().getPrice()).isEqualTo((Double) 200.0);
	}

	public void verifyNotFoundResponseWhenIdDoesNotExist() {
		Long nonExistentId = 2L;
		Product productDetails = new Product();
		productDetails.setName("New Name");
		productDetails.setDescription("New Description");
		productDetails.setPrice(200.0);
		when(productRepository.findById(nonExistentId)).thenReturn(Optional.empty());
		ResponseEntity<Product> response = productController.updateProduct(nonExistentId, productDetails);
		verify(productRepository).findById(nonExistentId);
		assertThat(response.getStatusCodeValue()).isEqualTo(404);
		assertThat(response.getBody()).isNull();
	}

	public void verifyErrorWhenProductDetailsAreNull() {
		Long id = 1L;
		when(productRepository.findById(id)).thenReturn(Optional.of(new Product()));
		try {
			productController.updateProduct(id, null);
		}
		catch (NullPointerException ex) {
			assertThat(ex).isInstanceOf(NullPointerException.class);
		}
		verify(productRepository, never()).save(any(Product.class));
	}

	public void verifyPartialFieldUpdate() {
		Long id = 1L;
		Product existingProduct = new Product();
		existingProduct.setName("Old Name");
		existingProduct.setDescription("Old Description");
		existingProduct.setPrice(100.0);
		Product updatedProductDetails = new Product();
		updatedProductDetails.setName("Updated Name");
		// Only the name is changed, description and price remain the same
		Product updatedProduct = new Product();
		updatedProduct.setName("Updated Name");
		updatedProduct.setDescription("Old Description");
		updatedProduct.setPrice(100.0);
		when(productRepository.findById(id)).thenReturn(Optional.of(existingProduct));
		when(productRepository.save(existingProduct)).thenReturn(updatedProduct);
		ResponseEntity<Product> response = productController.updateProduct(id, updatedProductDetails);
		verify(productRepository).findById(id);
		verify(productRepository).save(existingProduct);
		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().getName()).isEqualTo("Updated Name");
		assertThat(response.getBody().getDescription()).isEqualTo("Old Description");
		assertThat(response.getBody().getPrice()).isEqualTo((Double) 100.0);
	}

	public void verifyErrorWhenPriceIsNegative() {
		Long id = 1L;
		Product existingProduct = new Product();
		existingProduct.setName("Old Name");
		existingProduct.setDescription("Old Description");
		existingProduct.setPrice(100.0);
		Product updatedProductDetails = new Product();
		updatedProductDetails.setName("New Name");
		updatedProductDetails.setDescription("New Description");
		updatedProductDetails.setPrice(-50.0); // Invalid price
		when(productRepository.findById(id)).thenReturn(Optional.of(existingProduct));
		try {
			productController.updateProduct(id, updatedProductDetails);
		}
		catch (IllegalArgumentException ex) {
			assertThat(ex).isInstanceOf(IllegalArgumentException.class);
			// TODO: Ensure the ProductController handles validation for negative prices
		}
		verify(productRepository, never()).save(any(Product.class));
	}

	public void verifyErrorWhenNameIsEmpty() {
		Long id = 1L;
		Product existingProduct = new Product();
		existingProduct.setName("Old Name");
		existingProduct.setDescription("Old Description");
		existingProduct.setPrice(100.0);
		Product updatedProductDetails = new Product();
		updatedProductDetails.setName(""); // Empty name
		updatedProductDetails.setDescription("New Description");
		updatedProductDetails.setPrice(50.0);
		when(productRepository.findById(id)).thenReturn(Optional.of(existingProduct));
		try {
			productController.updateProduct(id, updatedProductDetails);
		}
		catch (IllegalArgumentException ex) {
			assertThat(ex).isInstanceOf(IllegalArgumentException.class);
			// TODO: Ensure the ProductController handles validation for empty names
		}
		verify(productRepository, never()).save(any(Product.class));
	}

	public void verifyErrorWhenIdIsNull() {
		Long nullId = null;
		Product productDetails = new Product();
		productDetails.setName("New Name");
		productDetails.setDescription("New Description");
		productDetails.setPrice(50.0);
		try {
			productController.updateProduct(nullId, productDetails);
		}
		catch (IllegalArgumentException ex) {
			assertThat(ex).isInstanceOf(IllegalArgumentException.class);
			// TODO: Ensure ProductController validates null IDs properly
		}
		verify(productRepository, never()).findById(nullId);
		verify(productRepository, never()).save(any(Product.class));
	}

	public void the_client_sends_a_delete_request_to(String string) {

		Long id = getProductIDfromAPI(string);
		deleteProductResponse = productController.deleteProduct(id);
		responseStatusCode = deleteProductResponse.getStatusCode();
	}

	public void setup_2() {
		existingProduct = new Product();
		existingProduct.setId(1L); // TODO: Change product id for different scenarios if
									// required
		existingProduct.setName("Product A"); // TODO: Change name for different scenarios
												// if required
		existingProduct.setDescription("A sample product"); // TODO: Change description
															// for different scenarios if
															// required
		existingProduct.setPrice(100.00); // TODO: Change price for different scenarios if
											// required
	}

	public void deleteExistingProductById() {
		// Arrange
		Long productId = 1L; // TODO: Change product ID for different scenarios if
								// required
		when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
		// Act
		ResponseEntity<Object> response = productController.deleteProduct(productId);
		// Assert
		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		verify(productRepository, times(1)).delete(existingProduct);
		verify(productRepository, times(1)).findById(productId);
	}

	public void deleteNonExistingProductById() {
		// Arrange
		Long productId = 2L; // TODO: Change to an invalid ID depending on the test
								// scenario
		when(productRepository.findById(productId)).thenReturn(Optional.empty());
		// Act
		ResponseEntity<Object> response = productController.deleteProduct(productId);
		// Assert
		assertThat(response.getStatusCodeValue()).isEqualTo(404);
		verify(productRepository, times(0)).delete(any(Product.class));
		verify(productRepository, times(1)).findById(productId);
	}

	public void deleteProductWithNullId() {
		// Arrange
		Long productId = null;
		// Act
		ResponseEntity<Object> response = productController.deleteProduct(productId);
		// Assert
		assertThat(response.getStatusCodeValue()).isEqualTo(404);
		verify(productRepository, times(0)).delete(any(Product.class));
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@BeforeEach
	void setUp() {
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

		Product inputProduct = new Product();

		inputProduct.setName("Test Product");
		inputProduct.setDescription("Test Description");

		inputProduct.setPrice(100.0);
		Product savedProduct = new Product();
		savedProduct.setName("Test Product");
		savedProduct.setDescription("Test Description");
		savedProduct.setPrice(100.0);
		when(productRepository.save(inputProduct)).thenReturn(savedProduct);

		Product result = productController.createProduct(inputProduct);

		assertNotNull(result, "Resulting product should not be null");
		assertEquals(savedProduct, result, "The returned product should match the saved product");
		verify(productRepository, times(1)).save(inputProduct);
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
	public void getProductByIdValidIdReturnsProduct() {

		Long validId = 1L;

		Product product = new Product();

		product.setId(validId);
		product.setName("Test Product");
		product.setDescription("Test Description");

		product.setPrice(100.0);
		when(productRepository.findById(validId)).thenReturn(Optional.of(product));

		ResponseEntity<Product> response = productController.getProductById(validId);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo(product);
		verify(productRepository, times(1)).findById(validId);
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("invalid")
	public void getProductByIdNonexistentIdReturnsNotFound() {

		Long nonexistentId = 2L;
		when(productRepository.findById(nonexistentId)).thenReturn(Optional.empty());

		ResponseEntity<Product> response = productController.getProductById(nonexistentId);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(response.getBody()).isNull();
		verify(productRepository, times(1)).findById(nonexistentId);
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("invalid")
	public void getProductByIdNullIdThrowsException() {

		assertThrows(IllegalArgumentException.class, () -> {
			productController.getProductById(null);
		});
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("invalid")
	public void getProductByIdEmptyRepositoryReturnsNotFound() {

		Long arbitraryId = 3L;
		when(productRepository.findById(arbitraryId)).thenReturn(Optional.empty());

		ResponseEntity<Product> response = productController.getProductById(arbitraryId);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(response.getBody()).isNull();
		verify(productRepository, times(1)).findById(arbitraryId);
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("boundary")
	public void getProductByIdLargeIdReturnsNotFound() {

		Long largeId = Long.MAX_VALUE;
		when(productRepository.findById(largeId)).thenReturn(Optional.empty());

		ResponseEntity<Product> response = productController.getProductById(largeId);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
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
	public void getProductByIdNegativeIdReturnsNotFound() {

		Long negativeId = -1L;
		when(productRepository.findById(negativeId)).thenReturn(Optional.empty());

		ResponseEntity<Product> response = productController.getProductById(negativeId);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(response.getBody()).isNull();
		verify(productRepository, times(1)).findById(negativeId);
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("boundary")
	public void getProductByIdZeroIdReturnsNotFound() {

		Long zeroId = 0L;
		when(productRepository.findById(zeroId)).thenReturn(Optional.empty());

		ResponseEntity<Product> response = productController.getProductById(zeroId);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(response.getBody()).isNull();
		verify(productRepository, times(1)).findById(zeroId);
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("integration")
	public void getProductByIdResponseContainsHeaders() {

		Long validId = 5L;
		Product product = new Product();

		product.setId(validId);
		product.setName("Another Product");
		product.setDescription("Description for another product");

		product.setPrice(200.0);
		when(productRepository.findById(validId)).thenReturn(Optional.of(product));

		ResponseEntity<Product> response = productController.getProductById(validId);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getHeaders()).isNotNull();
		assertThat(response.getBody()).isEqualTo(product);
		verify(productRepository, times(1)).findById(validId);
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("integration")
	public void getProductByIdRepositoryFailureThrowsException() {

		Long validId = 1L;
		when(productRepository.findById(validId)).thenThrow(new RuntimeException("Database connection failure"));

		assertThrows(RuntimeException.class, () -> {
			productController.getProductById(validId);
		});
		verify(productRepository, times(1)).findById(validId);
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("boundary")
	public void getProductByIdDuplicateIdReturnsFirstResult() {

		Long duplicateId = 6L;
		Product product1 = new Product();

		product1.setId(duplicateId);
		product1.setName("First Product");
		product1.setDescription("First Product Description");

		product1.setPrice(150.0);
		Product product2 = new Product();

		product2.setId(duplicateId);
		product2.setName("Second Product");
		product2.setDescription("Second Product Description");

		product2.setPrice(200.0);
		when(productRepository.findById(duplicateId)).thenReturn(Optional.of(product1));

		ResponseEntity<Product> response = productController.getProductById(duplicateId);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo(product1);
		verify(productRepository, times(1)).findById(duplicateId);
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Test
	@Tag("valid")
	public void verifySuccessfulProductUpdateWithValidDetails() {
		Long id = 1L;
		Product existingProduct = new Product();
		existingProduct.setName("Old Name");
		existingProduct.setDescription("Old Description");
		existingProduct.setPrice(100.0);
		Product updatedProductDetails = new Product();
		updatedProductDetails.setName("New Name");
		updatedProductDetails.setDescription("New Description");
		updatedProductDetails.setPrice(200.0);
		Product updatedProduct = new Product();
		updatedProduct.setName("New Name");
		updatedProduct.setDescription("New Description");
		updatedProduct.setPrice(200.0);
		when(productRepository.findById(id)).thenReturn(Optional.of(existingProduct));
		when(productRepository.save(existingProduct)).thenReturn(updatedProduct);
		ResponseEntity<Product> response = productController.updateProduct(id, updatedProductDetails);
		verify(productRepository).findById(id);
		verify(productRepository).save(existingProduct);
		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().getName()).isEqualTo("New Name");
		assertThat(response.getBody().getDescription()).isEqualTo("New Description");
		assertThat(response.getBody().getPrice()).isEqualTo((Double) 200.0);
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Test
	@Tag("invalid")
	public void verifyNotFoundResponseWhenIdDoesNotExist() {
		Long nonExistentId = 2L;
		Product productDetails = new Product();
		productDetails.setName("New Name");
		productDetails.setDescription("New Description");
		productDetails.setPrice(200.0);
		when(productRepository.findById(nonExistentId)).thenReturn(Optional.empty());
		ResponseEntity<Product> response = productController.updateProduct(nonExistentId, productDetails);
		verify(productRepository).findById(nonExistentId);
		assertThat(response.getStatusCodeValue()).isEqualTo(404);
		assertThat(response.getBody()).isNull();
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Test
	@Tag("invalid")
	public void verifyErrorWhenProductDetailsAreNull() {
		Long id = 1L;
		when(productRepository.findById(id)).thenReturn(Optional.of(new Product()));
		try {
			productController.updateProduct(id, null);
		}
		catch (NullPointerException ex) {
			assertThat(ex).isInstanceOf(NullPointerException.class);
		}
		verify(productRepository, never()).save(any(Product.class));
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Test
	@Tag("boundary")
	public void verifyPartialFieldUpdate() {
		Long id = 1L;
		Product existingProduct = new Product();
		existingProduct.setName("Old Name");
		existingProduct.setDescription("Old Description");
		existingProduct.setPrice(100.0);
		Product updatedProductDetails = new Product();
		updatedProductDetails.setName("Updated Name");

		Product updatedProduct = new Product();
		updatedProduct.setName("Updated Name");
		updatedProduct.setDescription("Old Description");
		updatedProduct.setPrice(100.0);
		when(productRepository.findById(id)).thenReturn(Optional.of(existingProduct));
		when(productRepository.save(existingProduct)).thenReturn(updatedProduct);
		ResponseEntity<Product> response = productController.updateProduct(id, updatedProductDetails);
		verify(productRepository).findById(id);
		verify(productRepository).save(existingProduct);
		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().getName()).isEqualTo("Updated Name");
		assertThat(response.getBody().getDescription()).isEqualTo("Old Description");
		assertThat(response.getBody().getPrice()).isEqualTo((Double) 100.0);
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Test
	@Tag("invalid")
	public void verifyErrorWhenPriceIsNegative() {
		Long id = 1L;
		Product existingProduct = new Product();
		existingProduct.setName("Old Name");
		existingProduct.setDescription("Old Description");
		existingProduct.setPrice(100.0);
		Product updatedProductDetails = new Product();
		updatedProductDetails.setName("New Name");
		updatedProductDetails.setDescription("New Description");

		updatedProductDetails.setPrice(-50.0);
		when(productRepository.findById(id)).thenReturn(Optional.of(existingProduct));
		try {
			productController.updateProduct(id, updatedProductDetails);
		}
		catch (IllegalArgumentException ex) {
			assertThat(ex).isInstanceOf(IllegalArgumentException.class);

		}
		verify(productRepository, never()).save(any(Product.class));
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Test
	@Tag("invalid")
	public void verifyErrorWhenNameIsEmpty() {
		Long id = 1L;
		Product existingProduct = new Product();
		existingProduct.setName("Old Name");
		existingProduct.setDescription("Old Description");
		existingProduct.setPrice(100.0);
		Product updatedProductDetails = new Product();

		updatedProductDetails.setName("");
		updatedProductDetails.setDescription("New Description");
		updatedProductDetails.setPrice(50.0);
		when(productRepository.findById(id)).thenReturn(Optional.of(existingProduct));
		try {
			productController.updateProduct(id, updatedProductDetails);
		}
		catch (IllegalArgumentException ex) {
			assertThat(ex).isInstanceOf(IllegalArgumentException.class);

		}
		verify(productRepository, never()).save(any(Product.class));
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Test
	@Tag("invalid")
	public void verifyErrorWhenIdIsNull() {
		Long nullId = null;
		Product productDetails = new Product();
		productDetails.setName("New Name");
		productDetails.setDescription("New Description");
		productDetails.setPrice(50.0);
		try {
			productController.updateProduct(nullId, productDetails);
		}
		catch (IllegalArgumentException ex) {
			assertThat(ex).isInstanceOf(IllegalArgumentException.class);

		}
		verify(productRepository, never()).findById(nullId);
		verify(productRepository, never()).save(any(Product.class));
	}

	/*
	 * ROOST_METHOD_HASH=deleteProduct_5ea3a876a4
	 * ROOST_METHOD_SIG_HASH=deleteProduct_dcaff736d4
	 *
	 */
	@BeforeEach
	public void setup() {
		existingProduct = new Product();

		existingProduct.setId(1L);

		existingProduct.setName("Product A");

		existingProduct.setDescription("A sample product");

		existingProduct.setPrice(100.00);
	}

	/*
	 * ROOST_METHOD_HASH=deleteProduct_5ea3a876a4
	 * ROOST_METHOD_SIG_HASH=deleteProduct_dcaff736d4
	 *
	 */
	@Test
	@Tag("valid")
	public void deleteExistingProductById() {

		Long productId = 1L;
		when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));

		ResponseEntity<Object> response = productController.deleteProduct(productId);

		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		verify(productRepository, times(1)).delete(existingProduct);
		verify(productRepository, times(1)).findById(productId);
	}

	/*
	 * ROOST_METHOD_HASH=deleteProduct_5ea3a876a4
	 * ROOST_METHOD_SIG_HASH=deleteProduct_dcaff736d4
	 *
	 */
	@Test
	@Tag("invalid")
	public void deleteNonExistingProductById() {

		Long productId = 2L;
		when(productRepository.findById(productId)).thenReturn(Optional.empty());

		ResponseEntity<Object> response = productController.deleteProduct(productId);

		assertThat(response.getStatusCodeValue()).isEqualTo(404);
		verify(productRepository, times(0)).delete(any(Product.class));
		verify(productRepository, times(1)).findById(productId);
	}

	/*
	 * ROOST_METHOD_HASH=deleteProduct_5ea3a876a4
	 * ROOST_METHOD_SIG_HASH=deleteProduct_dcaff736d4
	 *
	 */
	@Test
	@Tag("boundary")
	public void deleteProductWithNullId() {

		Long productId = null;

		ResponseEntity<Object> response = productController.deleteProduct(productId);

		assertThat(response.getStatusCodeValue()).isEqualTo(404);
		verify(productRepository, times(0)).delete(any(Product.class));
	}

}