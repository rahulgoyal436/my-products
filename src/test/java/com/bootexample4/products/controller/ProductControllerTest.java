package com.bootexample4.products.controller;

import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import com.bootexample4.products.model.Product;
import com.bootexample4.products.repository.ProductRepository;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation;
import org.junit.jupiter.api.BeforeEach;
import org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ProductControllerTest {

	// Merged class fields
	private ProductRepository productRepository;

	private ProductController productController;

	// Merged constructors
	public ProductControllerGetAllProductsTest() {
    MockitoAnnotations.initMocks(this);
}

	// Merged test methods

	/*
	 * ROOST_METHOD_HASH=getAllProducts_fef141838b
	 * ROOST_METHOD_SIG_HASH=getAllProducts_7e38cc05f6
	 *
	 */
	@Test
	@Tag("valid")
	public void retrieveMultipleProductsFromRepository() {

		Product product1 = new Product();
		product1.setId(1L);
		product1.setName("Product 1");
		product1.setDescription("Description 1");
		product1.setPrice(10.0);
		Product product2 = new Product();
		product2.setId(2L);
		product2.setName("Product 2");
		product2.setDescription("Description 2");
		product2.setPrice(20.0);
		when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

		List<Product> products = productController.getAllProducts();

		assertEquals(2, products.size());
		assertEquals(product1, products.get(0));
		assertEquals(product2, products.get(1));
	}

	/*
	 * ROOST_METHOD_HASH=getAllProducts_fef141838b
	 * ROOST_METHOD_SIG_HASH=getAllProducts_7e38cc05f6
	 *
	 */
@Test
@Tag("valid")
public void retrieveEmptyProductList() {

    when(productRepository.findAll()).thenReturn(Arrays.asList());

    List<Product> products = productController.getAllProducts();

    assertEquals(0, products.size());
    assertTrue(products.isEmpty());
}

	/*
	 * ROOST_METHOD_HASH=getAllProducts_fef141838b
	 * ROOST_METHOD_SIG_HASH=getAllProducts_7e38cc05f6
	 *
	 */
	@Test
	@Tag("valid")
	public void retrieveSingleProductFromRepository() {

		Product product = new Product();
		product.setId(1L);
		product.setName("Product 1");
		product.setDescription("Description 1");
		product.setPrice(10.0);
		when(productRepository.findAll()).thenReturn(Arrays.asList(product));

		List<Product> products = productController.getAllProducts();

		assertEquals(1, products.size());
		assertEquals(product, products.get(0));
	}

	/*
	 * ROOST_METHOD_HASH=getAllProducts_fef141838b
	 * ROOST_METHOD_SIG_HASH=getAllProducts_7e38cc05f6
	 *
	 */
@Test
@Tag("invalid")
public void handleRepositoryException() {

    when(productRepository.findAll()).thenThrow(new RuntimeException("Database error"));

    assertThrows(RuntimeException.class, () -> {
        productController.getAllProducts();
    });
}

	/*
	 * ROOST_METHOD_HASH=getAllProducts_fef141838b
	 * ROOST_METHOD_SIG_HASH=getAllProducts_7e38cc05f6
	 *
	 */
	@Test
	@Tag("valid")
	public void maintainProductListOrder() {

		Product product1 = new Product();
		product1.setId(1L);
		product1.setName("Product 1");
		product1.setDescription("Description 1");
		product1.setPrice(10.0);
		Product product2 = new Product();
		product2.setId(2L);
		product2.setName("Product 2");
		product2.setDescription("Description 2");
		product2.setPrice(20.0);
		Product product3 = new Product();
		product3.setId(3L);
		product3.setName("Product 3");
		product3.setDescription("Description 3");
		product3.setPrice(30.0);
		when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2, product3));

		List<Product> products = productController.getAllProducts();

		assertEquals(product1, products.get(0));
		assertEquals(product2, products.get(1));
		assertEquals(product3, products.get(2));
	}

	/*
	 * ROOST_METHOD_HASH=getAllProducts_fef141838b
	 * ROOST_METHOD_SIG_HASH=getAllProducts_7e38cc05f6
	 *
	 */
	@Test
	@Tag("boundary")
	public void handleNullEntriesInRepository() {

		Product product = new Product();
		product.setId(1L);
		product.setName("Product 1");
		product.setDescription("Description 1");
		product.setPrice(10.0);
		when(productRepository.findAll()).thenReturn(Arrays.asList(null, product, null));

		List<Product> products = productController.getAllProducts();

		assertEquals(3, products.size());
		assertNull(products.get(0));
		assertEquals(product, products.get(1));
		assertNull(products.get(2));
	}

	/*
	 * ROOST_METHOD_HASH=getAllProducts_fef141838b
	 * ROOST_METHOD_SIG_HASH=getAllProducts_7e38cc05f6
	 *
	 */
@Test
@Tag("integration")
public void ensureFindAllMethodCalledOnce() {

    when(productRepository.findAll()).thenReturn(Arrays.asList());

    productController.getAllProducts();

    verify(productRepository, times(1)).findAll();
}

	/*
	 * ROOST_METHOD_HASH=getAllProducts_fef141838b
	 * ROOST_METHOD_SIG_HASH=getAllProducts_7e38cc05f6
	 *
	 */
	@Test
	@Tag("valid")
	public void handleDuplicateProducts() {

		Product product = new Product();
		product.setId(1L);
		product.setName("Product 1");
		product.setDescription("Description 1");
		product.setPrice(10.0);
		when(productRepository.findAll()).thenReturn(Arrays.asList(product, product));

		List<Product> products = productController.getAllProducts();

		assertEquals(2, products.size());
		assertEquals(product, products.get(0));
		assertEquals(product, products.get(1));
	}

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
	public void createProductWithValidData() {

		Product mockProduct = new Product();

		mockProduct.setName("Sample Product");
		mockProduct.setDescription("Sample Description");
		mockProduct.setPrice(99.99);
		when(productRepository.save(mockProduct)).thenReturn(mockProduct);

		Product createdProduct = productController.createProduct(mockProduct);

		verify(productRepository, times(1)).save(mockProduct);
		assertThat(createdProduct).isNotNull();
		assertThat(createdProduct.getName()).isEqualTo(mockProduct.getName());
		assertThat(createdProduct.getDescription()).isEqualTo(mockProduct.getDescription());
		assertThat(createdProduct.getPrice()).isEqualTo(mockProduct.getPrice());
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@Test
	@Tag("invalid")
	public void createProductWithNullObject() {

		Product nullProduct = null;
		when(productRepository.save(nullProduct)).thenThrow(new IllegalArgumentException("Product object is null"));

		try {
			productController.createProduct(nullProduct);
		}
		catch (IllegalArgumentException e) {
			assertThat(e.getMessage()).isEqualTo("Product object is null");
		}
		verify(productRepository, times(0)).save(nullProduct);
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@Test
	@Tag("boundary")
	public void createProductWithEmptyFields() {

		Product mockProduct = new Product();
		mockProduct.setName("");
		mockProduct.setDescription("");

		mockProduct.setPrice(0.00);
		when(productRepository.save(mockProduct)).thenReturn(mockProduct);

		Product createdProduct = productController.createProduct(mockProduct);

		verify(productRepository, times(1)).save(mockProduct);
		assertThat(createdProduct).isNotNull();
		assertThat(createdProduct.getName()).isEqualTo(mockProduct.getName());
		assertThat(createdProduct.getDescription()).isEqualTo(mockProduct.getDescription());
		assertThat(createdProduct.getPrice()).isEqualTo(mockProduct.getPrice());
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Test
	@Tag("valid")
	public void updateProductWithValidId() {

		Long productId = 1L;

		Product existingProduct = new Product();
		existingProduct.setId(productId);
		existingProduct.setName("Old Product");
		existingProduct.setDescription("Old Description");
		existingProduct.setPrice(100.0);
		Product updatedProduct = new Product();
		updatedProduct.setName("Updated Product");
		updatedProduct.setDescription("Updated Description");
		updatedProduct.setPrice(150.0);
		when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
		when(productRepository.save(existingProduct)).thenReturn(existingProduct);

		ResponseEntity<Product> response = productController.updateProduct(productId, updatedProduct);

		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		assertThat(response.getBody()).isNotNull();
		Product responseProduct = response.getBody();
		assertThat(responseProduct.getName()).isEqualTo(updatedProduct.getName());
		assertThat(responseProduct.getDescription()).isEqualTo(updatedProduct.getDescription());
		assertThat(responseProduct.getPrice()).isEqualTo(updatedProduct.getPrice());
		verify(productRepository).findById(productId);
		verify(productRepository).save(existingProduct);
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Test
	@Tag("invalid")
	public void updateProductWithInvalidId() {

		Long invalidProductId = 99L;
		Product updatedProduct = new Product();
		updatedProduct.setName("Non-existent Product");
		updatedProduct.setDescription("Non-existent Description");
		updatedProduct.setPrice(200.0);
		when(productRepository.findById(invalidProductId)).thenReturn(Optional.empty());

		ResponseEntity<Product> response = productController.updateProduct(invalidProductId, updatedProduct);

		assertThat(response.getStatusCodeValue()).isEqualTo(404);
		assertThat(response.getBody()).isNull();
		verify(productRepository).findById(invalidProductId);

		verify(productRepository, never()).save(any());
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Test
	@Tag("boundary")
	public void updateProductWithBoundaryPrice() {

		Long productId = 2L;
		Product existingProduct = new Product();
		existingProduct.setId(productId);
		existingProduct.setName("Boundary Test Product");
		existingProduct.setDescription("Boundary Test Description");

		existingProduct.setPrice(0.0);
		Product updatedProduct = new Product();
		updatedProduct.setName("Boundary Product");
		updatedProduct.setDescription("Boundary Description");

		updatedProduct.setPrice(0.01);
		when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
		when(productRepository.save(existingProduct)).thenReturn(existingProduct);

		ResponseEntity<Product> response = productController.updateProduct(productId, updatedProduct);

		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		assertThat(response.getBody()).isNotNull();
		Product responseProduct = response.getBody();
		assertThat(responseProduct.getName()).isEqualTo(updatedProduct.getName());
		assertThat(responseProduct.getDescription()).isEqualTo(updatedProduct.getDescription());
		assertThat(responseProduct.getPrice()).isEqualTo(updatedProduct.getPrice());
		verify(productRepository).findById(productId);
		verify(productRepository).save(existingProduct);
	}

	/*
	 * ROOST_METHOD_HASH=updateProduct_e220585694
	 * ROOST_METHOD_SIG_HASH=updateProduct_9454a9af90
	 *
	 */
	@Test
	@Tag("integration")
	public void updateProductIntegrationTest() {

		Long productId = 3L;

		Product existingProduct = new Product();
		existingProduct.setId(productId);
		existingProduct.setName("Integration Test Product");
		existingProduct.setDescription("Integration Test Description");
		existingProduct.setPrice(300.0);
		Product updatedProduct = new Product();
		updatedProduct.setName("Updated Integration Product");
		updatedProduct.setDescription("Updated Integration Description");
		updatedProduct.setPrice(350.0);
		when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
		when(productRepository.save(existingProduct)).thenReturn(existingProduct);

		ResponseEntity<Product> response = productController.updateProduct(productId, updatedProduct);

		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		assertThat(response.getBody()).isNotNull();
		Product responseProduct = response.getBody();
		assertThat(responseProduct.getName()).isEqualTo(updatedProduct.getName());
		assertThat(responseProduct.getDescription()).isEqualTo(updatedProduct.getDescription());
		assertThat(responseProduct.getPrice()).isEqualTo(updatedProduct.getPrice());
		verify(productRepository).findById(productId);
		verify(productRepository).save(existingProduct);
	}

	/*
	 * ROOST_METHOD_HASH=deleteProduct_5ea3a876a4
	 * ROOST_METHOD_SIG_HASH=deleteProduct_dcaff736d4
	 *
	 */
	@Test
	@Tag("valid")
	public void deleteExistingProductSuccessfully() {

		Product mockProduct = new Product();

		mockProduct.setId(1L);
		mockProduct.setName("Product Name");
		mockProduct.setDescription("Product Description");
		mockProduct.setPrice(100.0);
		Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(mockProduct));
		doNothing().when(productRepository).delete(mockProduct);

		ResponseEntity<Object> response = productController.deleteProduct(1L);

		Assertions.assertEquals(200, response.getStatusCodeValue());
		verify(productRepository, times(1)).delete(mockProduct);
		verify(productRepository, times(1)).findById(1L);
	}

	/*
	 * ROOST_METHOD_HASH=deleteProduct_5ea3a876a4
	 * ROOST_METHOD_SIG_HASH=deleteProduct_dcaff736d4
	 *
	 */
	@Test
	@Tag("invalid")
	public void deleteNonExistingProductReturnsNotFound() {

		Mockito.when(productRepository.findById(999L)).thenReturn(Optional.empty());

		ResponseEntity<Object> response = productController.deleteProduct(999L);

		Assertions.assertEquals(404, response.getStatusCodeValue());
		verify(productRepository, never()).delete(any(Product.class));
		verify(productRepository, times(1)).findById(999L);
	}

	/*
	 * ROOST_METHOD_HASH=deleteProduct_5ea3a876a4
	 * ROOST_METHOD_SIG_HASH=deleteProduct_dcaff736d4
	 *
	 */
	@Test
	@Tag("boundary")
	public void deleteProductWithNegativeIdReturnsNotFound() {

		Mockito.when(productRepository.findById(-1L)).thenReturn(Optional.empty());

		ResponseEntity<Object> response = productController.deleteProduct(-1L);

		Assertions.assertEquals(404, response.getStatusCodeValue());
		verify(productRepository, never()).delete(any(Product.class));
		verify(productRepository, times(1)).findById(-1L);
	}

	/*
	 * ROOST_METHOD_HASH=deleteProduct_5ea3a876a4
	 * ROOST_METHOD_SIG_HASH=deleteProduct_dcaff736d4
	 *
	 */
	@Test
	@Tag("integration")
	public void deleteProductIntegrationTest() {

		Product newProduct = new Product();

		newProduct.setId(10L);
		newProduct.setName("Integration Product");
		newProduct.setDescription("Integration Description");
		newProduct.setPrice(200.0);

		productRepository.save(newProduct);

		ResponseEntity<Object> response = productController.deleteProduct(10L);

		Assertions.assertEquals(200, response.getStatusCodeValue());
		Optional<Product> deletedProduct = productRepository.findById(10L);

		Assertions.assertFalse(deletedProduct.isPresent());
	}

}