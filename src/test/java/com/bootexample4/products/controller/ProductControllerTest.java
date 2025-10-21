package com.bootexample4.products.controller;

import com.bootexample4.products.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.junit.jupiter.api.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bootexample4.products.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

	// Merged class fields
	private ProductRepository productRepository;

	private ProductController productController;

	Product validProduct = new Product();

	Product createdProduct = productController.createProduct(validProduct);

	Product nullProduct = null;

	Product productWithBoundaryValues = new Product();

	Product duplicateProduct1 = new Product();

	Product firstCreatedProduct = productController.createProduct(duplicateProduct1);

	Product productWithBlankFields = new Product();

	Product newProduct = new Product();

	Product validPriceProduct = new Product();

	Product longDescriptionProduct = new Product();

	Product missingFieldsProduct = new Product();

	Long id = 1L;

	Product mockProduct = new Product();

	ResponseEntity<Product> response = productController.getProductById(id);

	Long productId = 1L;

	Product existingProduct = new Product();

	Product updatedProductData = new Product();

	// Merged test methodspublic void the_client_sends_a_post_request_to(String string) {

	savedProduct=productController.createProduct(newProduct);
}

	public void productCreationWithValidInput() {
		// Arrange
		Product validProduct = new Product();
		validProduct.setId(1L);
		validProduct.setName("Product Name");
		validProduct.setDescription("Product Description");
		validProduct.setPrice(99.99);
		Mockito.when(productRepository.save(validProduct)).thenReturn(validProduct);
		// Act
		Product createdProduct = productController.createProduct(validProduct);
		// Assert
		assertThat(createdProduct).isEqualTo(validProduct);
	}

	public void productCreationWithNullInput() {
		// Arrange
		Product nullProduct = null;
		// Act & Assert
		assertThrows(NullPointerException.class, () -> {
			productController.createProduct(nullProduct);
		});
	}

	public void productCreationWhenRepositoryUnavailable() {
		// Arrange
		Product validProduct = new Product();
		validProduct.setId(1L);
		validProduct.setName("Product Name");
		validProduct.setDescription("Product Description");
		validProduct.setPrice(99.99);
		Mockito.when(productRepository.save(validProduct))
			.thenThrow(new IllegalStateException("Repository unavailable"));
		// Act & Assert
		assertThrows(IllegalStateException.class, () -> {
			productController.createProduct(validProduct);
		});
	}

	public void productCreationWithBoundaryValues() {
		// Arrange
		Product productWithBoundaryValues = new Product();
		productWithBoundaryValues.setId(2L);
		productWithBoundaryValues.setName(""); // Minimum boundary
		productWithBoundaryValues.setDescription(""); // Minimum boundary
		productWithBoundaryValues.setPrice(0.01); // Minimum valid price
		Mockito.when(productRepository.save(productWithBoundaryValues)).thenReturn(productWithBoundaryValues);
		// Act
		Product createdProduct = productController.createProduct(productWithBoundaryValues);
		// Assert
		assertThat(createdProduct.getName()).isEqualTo(productWithBoundaryValues.getName());
		assertThat(createdProduct.getDescription()).isEqualTo(productWithBoundaryValues.getDescription());
		assertThat(createdProduct.getPrice()).isEqualTo((double) productWithBoundaryValues.getPrice());
	}

	public void productCreationWhenDuplicateProductsExist() {
		// Arrange
		Product duplicateProduct1 = new Product();
		duplicateProduct1.setId(3L);
		duplicateProduct1.setName("Duplicate Product");
		duplicateProduct1.setDescription("Duplicate Description");
		duplicateProduct1.setPrice(99.99);
		Product duplicateProduct2 = new Product();
		duplicateProduct2.setId(3L); // Same ID
		duplicateProduct2.setName("Duplicate Product");
		duplicateProduct2.setDescription("Duplicate Description");
		duplicateProduct2.setPrice(99.99);
		Mockito.when(productRepository.save(duplicateProduct1)).thenReturn(duplicateProduct1);
		Mockito.when(productRepository.save(duplicateProduct2)).thenReturn(duplicateProduct2);
		// Act
		Product firstCreatedProduct = productController.createProduct(duplicateProduct1);
		Product secondCreatedProduct = productController.createProduct(duplicateProduct2);
		// Assert
		assertThat(firstCreatedProduct).isEqualTo(duplicateProduct1);
		assertThat(secondCreatedProduct).isEqualTo(duplicateProduct2); // Assuming
																		// duplicates are
																		// allowed by
																		// Repository
	}

	public void productCreationWithBlankFields() {
		// Arrange
		Product productWithBlankFields = new Product();
		productWithBlankFields.setId(4L);
		productWithBlankFields.setName("   "); // Blank name
		productWithBlankFields.setDescription("   "); // Blank description
		productWithBlankFields.setPrice(10.00);
		Mockito.when(productRepository.save(productWithBlankFields)).thenReturn(productWithBlankFields);
		// Act
		Product createdProduct = productController.createProduct(productWithBlankFields);
		// Assert
		assertThat(createdProduct.getName()).isEqualTo(productWithBlankFields.getName());
		assertThat(createdProduct.getDescription()).isEqualTo(productWithBlankFields.getDescription());
		assertThat(createdProduct.getPrice()).isEqualTo((double) productWithBlankFields.getPrice());
	}

	public void productCreationOnLargeRepositoryData() {
		// Arrange
		Product newProduct = new Product();
		newProduct.setId(5L);
		newProduct.setName("Large Repo Product");
		newProduct.setDescription("Product in a large repository dataset.");
		newProduct.setPrice(50.00);
		Mockito.when(productRepository.save(newProduct)).thenReturn(newProduct);
		// Act
		Product createdProduct = productController.createProduct(newProduct);
		// Assert
		assertThat(createdProduct).isEqualTo(newProduct);
	}

	public void productCreationWithValidPriceOnly() {
		// Arrange
		Product validPriceProduct = new Product();
		validPriceProduct.setId(6L);
		validPriceProduct.setName("");
		validPriceProduct.setDescription("");
		validPriceProduct.setPrice(100.00);
		Mockito.when(productRepository.save(validPriceProduct)).thenReturn(validPriceProduct);
		// Act
		Product createdProduct = productController.createProduct(validPriceProduct);
		// Assert
		assertThat(createdProduct.getPrice()).isEqualTo((double) validPriceProduct.getPrice());
	}

	public void productCreationWithLongDescription() {
		// Arrange
		Product longDescriptionProduct = new Product();
		longDescriptionProduct.setId(7L);
		longDescriptionProduct.setName("Valid Name");
		longDescriptionProduct.setDescription("L" + "o".repeat(1000) + "ng Description");
		longDescriptionProduct.setPrice(75.00);
		Mockito.when(productRepository.save(longDescriptionProduct)).thenReturn(longDescriptionProduct);
		// Act
		Product createdProduct = productController.createProduct(longDescriptionProduct);
		// Assert
		assertThat(createdProduct.getDescription()).isEqualTo(longDescriptionProduct.getDescription());
	}

	public void productCreationWithMissingFields() {
		// Arrange
		Product missingFieldsProduct = new Product();
		missingFieldsProduct.setId(null);
		missingFieldsProduct.setName(null);
		missingFieldsProduct.setDescription(null);
		missingFieldsProduct.setPrice(0.00); // Using 0.00 as a valid substitute for null
												// due to type constraints, replace in
												// business logic if necessary
		// Act & Assert
		assertThrows(NullPointerException.class, () -> {
			productController.createProduct(missingFieldsProduct);
		});
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

	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	public void getExistingProductById() {
		// Arrange
		Long id = 1L; // TODO: Use appropriate product ID for test scenarios
		Product mockProduct = new Product();
		mockProduct.setId(id);
		mockProduct.setName("Test Product");
		mockProduct.setDescription("Test Description");
		mockProduct.setPrice(10.99); // TODO: Change price value for different edge cases
		when(productRepository.findById(id)).thenReturn(Optional.of(mockProduct));
		// Act
		ResponseEntity<Product> response = productController.getProductById(id);
		// Assert
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().getId()).isEqualTo(id);
		assertThat(response.getBody().getName()).isEqualTo("Test Product");
		assertThat(response.getBody().getDescription()).isEqualTo("Test Description");
		assertThat(response.getBody().getPrice()).isEqualTo((Double) 10.99);
	}

	public void getNonExistingProductById() {
		// Arrange
		Long id = -1L; // Non-existent product ID for failure case
		when(productRepository.findById(id)).thenReturn(Optional.empty());
		// Act
		ResponseEntity<Product> response = productController.getProductById(id);
		// Assert
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(response.getBody()).isNull();
	}

	public void getProductByIdWithNullValue() {
		// Arrange
		Long id = null;
		// Act
		ResponseEntity<Product> response = productController.getProductById(id);
		// Assert
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(response.getBody()).isNull();
	}

	public void the_client_sends_a_put_request_to(String string) {

		updateProductResponse = productController.updateProduct(getProductIDfromAPI(string), newProduct);
		responseStatusCode = updateProductResponse.getStatusCode();
	}

	public void updateValidProduct() {
		// Arrange
		Long productId = 1L; // TODO: Replace with desired product ID
		Product existingProduct = new Product();
		existingProduct.setName("Old Name");
		existingProduct.setDescription("Old Description");
		existingProduct.setPrice(10.0);
		Product updatedProductData = new Product();
		updatedProductData.setName("New Name");
		updatedProductData.setDescription("New Description");
		updatedProductData.setPrice(20.0);
		when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
		when(productRepository.save(existingProduct)).thenReturn(updatedProductData);
		// Act
		ResponseEntity<Product> response = productController.updateProduct(productId, updatedProductData);
		// Assert
		verify(productRepository, times(1)).findById(productId);
		verify(productRepository, times(1)).save(existingProduct);
		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().getName()).isEqualTo("New Name");
		assertThat(response.getBody().getDescription()).isEqualTo("New Description");
		assertThat(response.getBody().getPrice()).isEqualTo(20.0);
	}

	public void updateNonexistentProduct() {
		// Arrange
		Long productId = 99L; // TODO: Replace with desired non-existent product ID
		Product updatedProductData = new Product();
		updatedProductData.setName("New Name");
		updatedProductData.setDescription("New Description");
		updatedProductData.setPrice(20.0);
		when(productRepository.findById(productId)).thenReturn(Optional.empty());
		// Act
		ResponseEntity<Product> response = productController.updateProduct(productId, updatedProductData);
		// Assert
		verify(productRepository, times(1)).findById(productId);
		verify(productRepository, never()).save(any(Product.class));
		assertThat(response.getStatusCodeValue()).isEqualTo(404);
		assertThat(response.getBody()).isNull();
	}

	public void updateValidProductWithEdgeCaseValues() {
		// Arrange
		Long productId = 2L; // TODO: Replace with desired product ID
		Product existingProduct = new Product();
		existingProduct.setName("Boundary Name");
		existingProduct.setDescription("Boundary Desc");
		existingProduct.setPrice(0.01);
		Product updatedProductData = new Product();
		updatedProductData.setName(""); // Edge case: empty name
		updatedProductData.setDescription(""); // Edge case: empty description
		updatedProductData.setPrice(0.0); // Edge case: minimum price
		when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
		when(productRepository.save(existingProduct)).thenReturn(updatedProductData);
		// Act
		ResponseEntity<Product> response = productController.updateProduct(productId, updatedProductData);
		// Assert
		verify(productRepository, times(1)).findById(productId);
		verify(productRepository, times(1)).save(existingProduct);
		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().getName()).isEqualTo("");
		assertThat(response.getBody().getDescription()).isEqualTo("");
		assertThat(response.getBody().getPrice()).isEqualTo(0.0);
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@Test
	@Tag("valid")
	public void productCreationWithValidInput() {

		Product validProduct = new Product();
		validProduct.setId(1L);
		validProduct.setName("Product Name");
		validProduct.setDescription("Product Description");
		validProduct.setPrice(99.99);
		Mockito.when(productRepository.save(validProduct)).thenReturn(validProduct);

		Product createdProduct = productController.createProduct(validProduct);

		assertThat(createdProduct).isEqualTo(validProduct);
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@Test
	@Tag("invalid")
	public void productCreationWithNullInput() {

		Product nullProduct = null;

		assertThrows(NullPointerException.class, () -> {
			productController.createProduct(nullProduct);
		});
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@Test
	@Tag("integration")
	public void productCreationWhenRepositoryUnavailable() {

		Product validProduct = new Product();
		validProduct.setId(1L);
		validProduct.setName("Product Name");
		validProduct.setDescription("Product Description");
		validProduct.setPrice(99.99);
		Mockito.when(productRepository.save(validProduct))
			.thenThrow(new IllegalStateException("Repository unavailable"));

		assertThrows(IllegalStateException.class, () -> {
			productController.createProduct(validProduct);
		});
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@Test
	@Tag("boundary")
	public void productCreationWithBoundaryValues() {

		Product productWithBoundaryValues = new Product();
		productWithBoundaryValues.setId(2L);

		productWithBoundaryValues.setName("");

		productWithBoundaryValues.setDescription("");

		productWithBoundaryValues.setPrice(0.01);
		Mockito.when(productRepository.save(productWithBoundaryValues)).thenReturn(productWithBoundaryValues);

		Product createdProduct = productController.createProduct(productWithBoundaryValues);

		assertThat(createdProduct.getName()).isEqualTo(productWithBoundaryValues.getName());
		assertThat(createdProduct.getDescription()).isEqualTo(productWithBoundaryValues.getDescription());
		assertThat(createdProduct.getPrice()).isEqualTo((double) productWithBoundaryValues.getPrice());
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@Test
	@Tag("invalid")
	public void productCreationWhenDuplicateProductsExist() {

		Product duplicateProduct1 = new Product();
		duplicateProduct1.setId(3L);
		duplicateProduct1.setName("Duplicate Product");
		duplicateProduct1.setDescription("Duplicate Description");
		duplicateProduct1.setPrice(99.99);
		Product duplicateProduct2 = new Product();

		duplicateProduct2.setId(3L);
		duplicateProduct2.setName("Duplicate Product");
		duplicateProduct2.setDescription("Duplicate Description");
		duplicateProduct2.setPrice(99.99);
		Mockito.when(productRepository.save(duplicateProduct1)).thenReturn(duplicateProduct1);
		Mockito.when(productRepository.save(duplicateProduct2)).thenReturn(duplicateProduct2);

		Product firstCreatedProduct = productController.createProduct(duplicateProduct1);
		Product secondCreatedProduct = productController.createProduct(duplicateProduct2);

		assertThat(firstCreatedProduct).isEqualTo(duplicateProduct1);

		assertThat(secondCreatedProduct).isEqualTo(duplicateProduct2);
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@Test
	@Tag("invalid")
	public void productCreationWithBlankFields() {

		Product productWithBlankFields = new Product();
		productWithBlankFields.setId(4L);

		productWithBlankFields.setName("   ");

		productWithBlankFields.setDescription("   ");
		productWithBlankFields.setPrice(10.00);
		Mockito.when(productRepository.save(productWithBlankFields)).thenReturn(productWithBlankFields);

		Product createdProduct = productController.createProduct(productWithBlankFields);

		assertThat(createdProduct.getName()).isEqualTo(productWithBlankFields.getName());
		assertThat(createdProduct.getDescription()).isEqualTo(productWithBlankFields.getDescription());
		assertThat(createdProduct.getPrice()).isEqualTo((double) productWithBlankFields.getPrice());
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@Test
	@Tag("integration")
	public void productCreationOnLargeRepositoryData() {

		Product newProduct = new Product();
		newProduct.setId(5L);
		newProduct.setName("Large Repo Product");
		newProduct.setDescription("Product in a large repository dataset.");
		newProduct.setPrice(50.00);
		Mockito.when(productRepository.save(newProduct)).thenReturn(newProduct);

		Product createdProduct = productController.createProduct(newProduct);

		assertThat(createdProduct).isEqualTo(newProduct);
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@Test
	@Tag("valid")
	public void productCreationWithValidPriceOnly() {

		Product validPriceProduct = new Product();
		validPriceProduct.setId(6L);
		validPriceProduct.setName("");
		validPriceProduct.setDescription("");
		validPriceProduct.setPrice(100.00);
		Mockito.when(productRepository.save(validPriceProduct)).thenReturn(validPriceProduct);

		Product createdProduct = productController.createProduct(validPriceProduct);

		assertThat(createdProduct.getPrice()).isEqualTo((double) validPriceProduct.getPrice());
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@Test
	@Tag("boundary")
	public void productCreationWithLongDescription() {

		Product longDescriptionProduct = new Product();
		longDescriptionProduct.setId(7L);
		longDescriptionProduct.setName("Valid Name");
		longDescriptionProduct.setDescription("L" + "o".repeat(1000) + "ng Description");
		longDescriptionProduct.setPrice(75.00);
		Mockito.when(productRepository.save(longDescriptionProduct)).thenReturn(longDescriptionProduct);

		Product createdProduct = productController.createProduct(longDescriptionProduct);

		assertThat(createdProduct.getDescription()).isEqualTo(longDescriptionProduct.getDescription());
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@Test
	@Tag("invalid")
	public void productCreationWithMissingFields() {

		Product missingFieldsProduct = new Product();
		missingFieldsProduct.setId(null);
		missingFieldsProduct.setName(null);
		missingFieldsProduct.setDescription(null);

		missingFieldsProduct.setPrice(0.00);

		assertThrows(NullPointerException.class, () -> {
			productController.createProduct(missingFieldsProduct);
		});
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
	public void getExistingProductById() {

		Long id = 1L;
		Product mockProduct = new Product();
		mockProduct.setId(id);
		mockProduct.setName("Test Product");
		mockProduct.setDescription("Test Description");

		mockProduct.setPrice(10.99);
		when(productRepository.findById(id)).thenReturn(Optional.of(mockProduct));

		ResponseEntity<Product> response = productController.getProductById(id);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().getId()).isEqualTo(id);
		assertThat(response.getBody().getName()).isEqualTo("Test Product");
		assertThat(response.getBody().getDescription()).isEqualTo("Test Description");
		assertThat(response.getBody().getPrice()).isEqualTo((Double) 10.99);
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("invalid")
	public void getNonExistingProductById() {

		Long id = -1L;
		when(productRepository.findById(id)).thenReturn(Optional.empty());

		ResponseEntity<Product> response = productController.getProductById(id);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(response.getBody()).isNull();
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("boundary")
	public void getProductByIdWithNullValue() {

		Long id = null;

		ResponseEntity<Product> response = productController.getProductById(id);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(response.getBody()).isNull();
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Test
	@Tag("valid")
	public void updateValidProduct() {

		Long productId = 1L;
		Product existingProduct = new Product();
		existingProduct.setName("Old Name");
		existingProduct.setDescription("Old Description");
		existingProduct.setPrice(10.0);
		Product updatedProductData = new Product();
		updatedProductData.setName("New Name");
		updatedProductData.setDescription("New Description");
		updatedProductData.setPrice(20.0);
		when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
		when(productRepository.save(existingProduct)).thenReturn(updatedProductData);

		ResponseEntity<Product> response = productController.updateProduct(productId, updatedProductData);

		verify(productRepository, times(1)).findById(productId);
		verify(productRepository, times(1)).save(existingProduct);
		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().getName()).isEqualTo("New Name");
		assertThat(response.getBody().getDescription()).isEqualTo("New Description");
		assertThat(response.getBody().getPrice()).isEqualTo(20.0);
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Test
	@Tag("invalid")
	public void updateNonexistentProduct() {

		Long productId = 99L;
		Product updatedProductData = new Product();
		updatedProductData.setName("New Name");
		updatedProductData.setDescription("New Description");
		updatedProductData.setPrice(20.0);
		when(productRepository.findById(productId)).thenReturn(Optional.empty());

		ResponseEntity<Product> response = productController.updateProduct(productId, updatedProductData);

		verify(productRepository, times(1)).findById(productId);
		verify(productRepository, never()).save(any(Product.class));
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
	public void updateValidProductWithEdgeCaseValues() {

		Long productId = 2L;
		Product existingProduct = new Product();
		existingProduct.setName("Boundary Name");
		existingProduct.setDescription("Boundary Desc");
		existingProduct.setPrice(0.01);
		Product updatedProductData = new Product();

		updatedProductData.setName("");

		updatedProductData.setDescription("");

		updatedProductData.setPrice(0.0);
		when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
		when(productRepository.save(existingProduct)).thenReturn(updatedProductData);

		ResponseEntity<Product> response = productController.updateProduct(productId, updatedProductData);

		verify(productRepository, times(1)).findById(productId);
		verify(productRepository, times(1)).save(existingProduct);
		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().getName()).isEqualTo("");
		assertThat(response.getBody().getDescription()).isEqualTo("");
		assertThat(response.getBody().getPrice()).isEqualTo(0.0);
	}

}