package com.bootexample4.products.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.bootexample4.products.model.Product;
import com.bootexample4.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.junit.jupiter.api.*;
import java.util.Optional;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

	// Merged class fields
	private ProductRepository productRepository;

	private ProductController productController;

	Product product1 = new Product();

	List<Product> actualProducts = productController.getAllProducts();

	List<Product> mockProducts = new ArrayList<>();

	Product product = new Product();

	Product createdProduct = productController.createProduct(product);

	Long validId = 1L;

	Product mockProduct = new Product();

	ResponseEntity<Product> response = productController.getProductById(validId);

	Long invalidId = 999L;

	Long boundaryId = Long.MAX_VALUE;

	Long id = 5L;

	Product existingProduct = new Product();

	Long productId = 1L;

	// Merged test methods

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
	@Tag("valid")
	@Test
	public void fetchAllProductsFromRepositoryWithMultipleEntries() {

		Product product1 = new Product();
		product1.setId(1L);
		product1.setName("Product1");
		product1.setDescription("Description1");
		product1.setPrice(100.0);
		Product product2 = new Product();
		product2.setId(2L);
		product2.setName("Product2");
		product2.setDescription("Description2");
		product2.setPrice(200.0);
		List<Product> mockProducts = Arrays.asList(product1, product2);
		when(productRepository.findAll()).thenReturn(mockProducts);

		List<Product> actualProducts = productController.getAllProducts();

		assertThat((List<Product>) actualProducts).isEqualTo(mockProducts);
		verify(productRepository, times(1)).findAll();
	}

	/*
	 * ROOST_METHOD_HASH=getAllProducts_fef141838b
	 * ROOST_METHOD_SIG_HASH=getAllProducts_7e38cc05f6
	 *
	 */
	@Tag("boundary")
	@Test
	public void fetchAllProductsFromEmptyRepository() {

		List<Product> mockProducts = new ArrayList<>();
		when(productRepository.findAll()).thenReturn(mockProducts);

		List<Product> actualProducts = productController.getAllProducts();

		assertThat((List<Product>) actualProducts).isEmpty();
		verify(productRepository, times(1)).findAll();
	}

	/*
	 * ROOST_METHOD_HASH=getAllProducts_fef141838b
	 * ROOST_METHOD_SIG_HASH=getAllProducts_7e38cc05f6
	 *
	 */
@Tag("integration")
@Test
public void ensureFindAllIsInvokedOnRepository() {

    when(productRepository.findAll()).thenReturn(new ArrayList<>());

    productController.getAllProducts();

    verify(productRepository, times(1)).findAll();
}

	/*
	 * ROOST_METHOD_HASH=getAllProducts_fef141838b
	 * ROOST_METHOD_SIG_HASH=getAllProducts_7e38cc05f6
	 *
	 */
@Tag("invalid")
@Test
public void handleNullRepositoryResponse() {

    when(productRepository.findAll()).thenReturn(null);

    List<Product> actualProducts = productController.getAllProducts();

    assertThat((List<Product>) actualProducts).isNotNull();
    assertThat((List<Product>) actualProducts).isEmpty();
    verify(productRepository, times(1)).findAll();
}

	/*
	 * ROOST_METHOD_HASH=getAllProducts_fef141838b
	 * ROOST_METHOD_SIG_HASH=getAllProducts_7e38cc05f6
	 *
	 */
@Tag("invalid")
@Test
public void verifyExceptionHandlingWithRepositoryChaos() {

    when(productRepository.findAll()).thenThrow(new RuntimeException("Repository exception"));

    assertThatThrownBy(() -> productController.getAllProducts()).isInstanceOf(RuntimeException.class).hasMessageContaining("Repository exception");
    verify(productRepository, times(1)).findAll();
}

	/*
	 * ROOST_METHOD_HASH=getAllProducts_fef141838b
	 * ROOST_METHOD_SIG_HASH=getAllProducts_7e38cc05f6
	 *
	 */
	@Tag("boundary")
	@Test
	public void handleDuplicateEntriesFromRepository() {

		Product product1 = new Product();
		product1.setId(1L);
		product1.setName("DuplicateProduct");
		product1.setDescription("Description");
		product1.setPrice(50.0);
		Product product2 = new Product();
		product2.setId(1L);
		product2.setName("DuplicateProduct");
		product2.setDescription("Description");
		product2.setPrice(50.0);
		List<Product> mockProducts = Arrays.asList(product1, product2);
		when(productRepository.findAll()).thenReturn(mockProducts);

		List<Product> actualProducts = productController.getAllProducts();

		assertThat((List<Product>) actualProducts).containsExactly(product1, product2);
		verify(productRepository, times(1)).findAll();
	}

	/*
	 * ROOST_METHOD_HASH=getAllProducts_fef141838b
	 * ROOST_METHOD_SIG_HASH=getAllProducts_7e38cc05f6
	 *
	 */
	@Tag("boundary")
	@Test
	public void handleLargeDataFetchFromRepository() {

		List<Product> mockProducts = new ArrayList<>();
		for (long i = 1; i <= 1000; i++) {
			Product product = new Product();
			product.setId(i);
			product.setName("Product" + i);
			product.setDescription("Description" + i);
			product.setPrice((double) i * 10);
			mockProducts.add(product);
		}
		when(productRepository.findAll()).thenReturn(mockProducts);

		List<Product> actualProducts = productController.getAllProducts();

		assertThat((List<Product>) actualProducts).hasSize(1000);
		assertThat((List<Product>) actualProducts).isEqualTo(mockProducts);
		verify(productRepository, times(1)).findAll();
	}

	/*
	 * ROOST_METHOD_HASH=getAllProducts_fef141838b
	 * ROOST_METHOD_SIG_HASH=getAllProducts_7e38cc05f6
	 *
	 */
	@Tag("valid")
	@Test
	public void verifyProductObjectsInReturnedList() {

		Product product1 = new Product();
		product1.setId(1L);
		product1.setName("Product1");
		product1.setDescription("Description1");
		product1.setPrice(100.0);
		Product product2 = new Product();
		product2.setId(2L);
		product2.setName("Product2");
		product2.setDescription("Description2");
		product2.setPrice(200.0);
		List<Product> mockProducts = Arrays.asList(product1, product2);
		when(productRepository.findAll()).thenReturn(mockProducts);

		List<Product> actualProducts = productController.getAllProducts();

		assertThat((List<Product>) actualProducts).allMatch(product -> product instanceof Product);
		verify(productRepository, times(1)).findAll();
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@Test
	@Tag("valid")
	public void productIsCreatedSuccessfully() {

		Product product = new Product();

		product.setId(1L);

		product.setName("Test Product");

		product.setDescription("Test Description");

		product.setPrice(99.99);
		when(productRepository.save(product)).thenReturn(product);

		Product createdProduct = productController.createProduct(product);

		assertThat(createdProduct).isNotNull();
		assertThat(createdProduct.getName()).isEqualTo(product.getName());
		assertThat(createdProduct.getDescription()).isEqualTo(product.getDescription());
		assertThat(createdProduct.getPrice()).isEqualTo(product.getPrice());
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("valid")
	public void getProductByValidId() {

		Long validId = 1L;
		Product mockProduct = new Product();

		mockProduct.setId(validId);
		mockProduct.setName("Valid Product Name");
		mockProduct.setDescription("Valid Product Description");
		mockProduct.setPrice(10.99);
		when(productRepository.findById(validId)).thenReturn(Optional.of(mockProduct));

		ResponseEntity<Product> response = productController.getProductById(validId);

		assertThat(response).isNotNull();
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
	public void getProductByInvalidId() {

		Long invalidId = 999L;
		when(productRepository.findById(invalidId)).thenReturn(Optional.empty());

		ResponseEntity<Product> response = productController.getProductById(invalidId);

		assertThat(response).isNotNull();
		assertThat(response.getStatusCodeValue()).isEqualTo(404);
		assertThat(response.getBody()).isNull();

		verify(productRepository, times(1)).findById(invalidId);
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("boundary")
	public void getProductByBoundaryId() {

		Long boundaryId = Long.MAX_VALUE;
		Product mockProduct = new Product();
		mockProduct.setId(boundaryId);
		mockProduct.setName("Boundary Product");
		mockProduct.setDescription("Boundary Product Description");
		mockProduct.setPrice(99.99);
		when(productRepository.findById(boundaryId)).thenReturn(Optional.of(mockProduct));

		ResponseEntity<Product> response = productController.getProductById(boundaryId);

		assertThat(response).isNotNull();
		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		assertThat(response.getBody()).isEqualTo(mockProduct);

		verify(productRepository, times(1)).findById(boundaryId);
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("integration")
	public void getProductByIdIntegrationTest() {

		Long id = 5L;
		when(productRepository.findById(id)).thenReturn(Optional.of(new Product()));

		ResponseEntity<Product> response = productController.getProductById(id);

		assertThat(response).isNotNull();
		assertThat(response.getStatusCodeValue()).isBetween(200, 404);
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Tag("valid")
	@Test
	public void updateExistingProductSuccessfully() {

		Product existingProduct = new Product();
		existingProduct.setId(1L);
		existingProduct.setName("Old Name");
		existingProduct.setDescription("Old Description");
		existingProduct.setPrice(100.0);
		Product updatedProductDetails = new Product();
		updatedProductDetails.setName("New Name");
		updatedProductDetails.setDescription("New Description");
		updatedProductDetails.setPrice(200.0);
		when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
		when(productRepository.save(existingProduct)).thenReturn(existingProduct);

		ResponseEntity<Product> response = productController.updateProduct(1L, updatedProductDetails);

		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		assertThat(response.getBody().getName()).isEqualTo("New Name");
		assertThat(response.getBody().getDescription()).isEqualTo("New Description");
		assertThat(response.getBody().getPrice()).isEqualTo(200.0);
		verify(productRepository, times(1)).save(existingProduct);
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
@Tag("invalid")
@Test
public void updateNonexistentProduct() {

    when(productRepository.findById(2L)).thenReturn(Optional.empty());

    ResponseEntity<Product> response = productController.updateProduct(2L, new Product());

    assertThat(response.getStatusCodeValue()).isEqualTo(404);
}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Tag("invalid")
	@Test
	public void updateProductWithNullFields() {

		Product existingProduct = new Product();
		existingProduct.setId(3L);
		existingProduct.setName("Original Name");
		existingProduct.setDescription("Original Description");
		existingProduct.setPrice(50.0);
		Product productWithNullFields = new Product();
		when(productRepository.findById(3L)).thenReturn(Optional.of(existingProduct));

		ResponseEntity<Product> response = productController.updateProduct(3L, productWithNullFields);

		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		assertThat(response.getBody().getName()).isEqualTo("Original Name");
		assertThat(response.getBody().getDescription()).isEqualTo("Original Description");
		assertThat(response.getBody().getPrice()).isEqualTo(50.0);
		verify(productRepository, never()).save(existingProduct);
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
@Tag("invalid")
@Test
public void updateProductWithInvalidIdFormat() {

    when(productRepository.findById(-1L)).thenReturn(Optional.empty());

    ResponseEntity<Product> response = productController.updateProduct(-1L, new Product());

    assertThat(response.getStatusCodeValue()).isEqualTo(404);
}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Tag("valid")
	@Test
	public void ensureProductPersistedInRepositoryAfterUpdate() {

		Product existingProduct = new Product();
		existingProduct.setId(4L);
		existingProduct.setName("Old Product");
		existingProduct.setDescription("Old Description");
		existingProduct.setPrice(150.0);
		Product updatedProductDetails = new Product();
		updatedProductDetails.setName("Updated Product");
		updatedProductDetails.setDescription("Updated Description");
		updatedProductDetails.setPrice(250.0);
		when(productRepository.findById(4L)).thenReturn(Optional.of(existingProduct));
		when(productRepository.save(existingProduct)).thenReturn(existingProduct);

		ResponseEntity<Product> response = productController.updateProduct(4L, updatedProductDetails);

		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		assertThat(response.getBody().getName()).isEqualTo("Updated Product");
		assertThat(response.getBody().getDescription()).isEqualTo("Updated Description");
		assertThat(response.getBody().getPrice()).isEqualTo(250.0);
		verify(productRepository, times(1)).save(existingProduct);
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Tag("invalid")
	@Test
	public void updateProductWithMissingMandatoryField() {

		Product existingProduct = new Product();
		existingProduct.setId(5L);
		existingProduct.setName("Mandatory Name");
		existingProduct.setDescription("Mandatory Description");
		existingProduct.setPrice(300.0);
		Product productWithMissingMandatoryField = new Product();
		productWithMissingMandatoryField.setDescription("Updated Description");
		productWithMissingMandatoryField.setPrice(400.0);
		when(productRepository.findById(5L)).thenReturn(Optional.of(existingProduct));

		ResponseEntity<Product> response = productController.updateProduct(5L, productWithMissingMandatoryField);

		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		assertThat(response.getBody().getName()).isEqualTo("Mandatory Name");
		assertThat(response.getBody().getDescription()).isEqualTo("Mandatory Description");
		assertThat(response.getBody().getPrice()).isEqualTo(300.0);
		verify(productRepository, never()).save(existingProduct);
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Tag("boundary")
	@Test
	public void updateProductWithBoundaryValueForPrice() {

		Product existingProduct = new Product();
		existingProduct.setId(6L);
		existingProduct.setName("Boundary Name");
		existingProduct.setDescription("Boundary Description");
		existingProduct.setPrice(1000.0);
		Product updatedProductDetails = new Product();
		updatedProductDetails.setName("Boundary Updated Name");
		updatedProductDetails.setDescription("Boundary Updated Description");

		updatedProductDetails.setPrice(0.0);
		when(productRepository.findById(6L)).thenReturn(Optional.of(existingProduct));
		when(productRepository.save(existingProduct)).thenReturn(existingProduct);

		ResponseEntity<Product> response = productController.updateProduct(6L, updatedProductDetails);

		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		assertThat(response.getBody().getPrice()).isEqualTo(0.0);
		verify(productRepository, times(1)).save(existingProduct);
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Tag("integration")
	@Test
	public void validateResponseHeadersOnSuccessfulUpdate() {

		Product existingProduct = new Product();
		existingProduct.setId(7L);
		existingProduct.setName("Header Name");
		existingProduct.setDescription("Header Description");
		existingProduct.setPrice(250.0);
		Product updatedProductDetails = new Product();
		updatedProductDetails.setName("Updated Header Name");
		updatedProductDetails.setDescription("Updated Header Description");
		updatedProductDetails.setPrice(750.0);
		when(productRepository.findById(7L)).thenReturn(Optional.of(existingProduct));
		when(productRepository.save(existingProduct)).thenReturn(existingProduct);

		ResponseEntity<Product> response = productController.updateProduct(7L, updatedProductDetails);

		assertThat(response.getStatusCodeValue()).isEqualTo(200);

		assertThat(response.getHeaders().getContentType()).isNotNull();
		verify(productRepository, times(1)).save(existingProduct);
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Tag("invalid")
	@Test
	public void handleRepositorySaveFailure() {

		Product existingProduct = new Product();
		existingProduct.setId(8L);
		existingProduct.setName("Failure Product");
		existingProduct.setDescription("Failure Description");
		existingProduct.setPrice(550.0);
		Product updatedProductDetails = new Product();
		updatedProductDetails.setName("Updated Failure Product");
		updatedProductDetails.setDescription("Updated Failure Description");
		updatedProductDetails.setPrice(650.0);
		when(productRepository.findById(8L)).thenReturn(Optional.of(existingProduct));

		when(productRepository.save(existingProduct)).thenThrow(new RuntimeException("Save Failed"));

		Assertions.assertThrows(RuntimeException.class, () -> {
			productController.updateProduct(8L, updatedProductDetails);
		});
		verify(productRepository, times(1)).save(existingProduct);
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
	public void deleteExistingProductSuccessfully() {

		Long productId = 1L;
		Product mockProduct = new Product();
		when(productRepository.findById(productId)).thenReturn(Optional.of(mockProduct));

		ResponseEntity<Object> response = productController.deleteProduct(productId);

		verify(productRepository, times(1)).delete(mockProduct);
		assertEquals(ResponseEntity.ok().build(), response);
	}

	/*
	 * ROOST_METHOD_HASH=deleteProduct_5ea3a876a4
	 * ROOST_METHOD_SIG_HASH=deleteProduct_dcaff736d4
	 *
	 */
	@Test
	@Tag("invalid")
	public void deleteNonExistingProduct() {

		Long productId = 99L;
		when(productRepository.findById(productId)).thenReturn(Optional.empty());

		ResponseEntity<Object> response = productController.deleteProduct(productId);

		verify(productRepository, times(0)).delete(any());
		assertEquals(ResponseEntity.notFound().build(), response);
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
		when(productRepository.findById(productId)).thenReturn(Optional.empty());

		ResponseEntity<Object> response = productController.deleteProduct(productId);

		verify(productRepository, times(0)).delete(any());
		assertEquals(ResponseEntity.notFound().build(), response);
	}

}