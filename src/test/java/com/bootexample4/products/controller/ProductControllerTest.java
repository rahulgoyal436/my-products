package com.bootexample4.products.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.Tag;
import java.util.Collections;
import java.util.List;
import com.bootexample4.products.model.Product;
import com.bootexample4.products.repository.ProductRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import java.util.Optional;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProductControllerTest {

	// Merged class fields
	private ProductRepository productRepository;

	private ProductController productController;

	List<Product> result = productController.getAllProducts();

	Product product1 = new Product();

	RuntimeException exception = assertThrows(RuntimeException.class, () -> productController.getAllProducts());

	List<Product> result1 = productController.getAllProducts();

	List<Product> beforeCall = productRepository.findAll();

	private Product validProduct;

	Product nullProduct = null;

	Product emptyProduct = new Product();

	Product duplicateProduct = new Product();

	Product largeProduct = new Product();

	Product specialCharProduct = new Product();

	Product maxPriceProduct = new Product();

	Product minPriceProduct = new Product();

	Product malformedProduct = null;

	Product mockProduct = new Product();

	Long validId = 1L;

	Product product = new Product();

	ResponseEntity<Object> response = productController.deleteProduct(validId);

	Long nonExistentId = 2L;

	Long nullId = null;

	ProductController uninitializedController = new ProductController();

	Long negativeId = -1L;

	Long specificId = 10L;

	// Merged test methods

	/*
	 * ROOST_METHOD_HASH=getAllProducts_fef141838b
	 * ROOST_METHOD_SIG_HASH=getAllProducts_7e38cc05f6
	 *
	 */
	@BeforeEach
	void setup() {
		Mockito.reset(productRepository);
	}

	/*
	 * ROOST_METHOD_HASH=getAllProducts_fef141838b
	 * ROOST_METHOD_SIG_HASH=getAllProducts_7e38cc05f6
	 *
	 */
@Test
@Tag("valid")
public void getAllProductsReturnsEmptyListWhenNoProductsExist() {

    when(productRepository.findAll()).thenReturn(Collections.emptyList());

    List<Product> result = productController.getAllProducts();

    assertNotNull(result, "Returned list should not be null");
    assertEquals(0, result.size(), "Expected empty list when no products exist");
}

	/*
	 * ROOST_METHOD_HASH=getAllProducts_fef141838b
	 * ROOST_METHOD_SIG_HASH=getAllProducts_7e38cc05f6
	 *
	 */
	@Test
	@Tag("valid")
	public void getAllProductsReturnsPopulatedListWhenProductsExist() {

		Product product1 = new Product();
		product1.setName("Product 1");
		product1.setDescription("Description 1");
		product1.setPrice(100.0);
		Product product2 = new Product();
		product2.setName("Product 2");
		product2.setDescription("Description 2");
		product2.setPrice(200.0);
		List<Product> products = List.of(product1, product2);
		when(productRepository.findAll()).thenReturn(products);

		List<Product> result = productController.getAllProducts();

		assertNotNull(result, "Returned list should not be null");
		assertEquals(2, result.size(), "Expected list with 2 products");
		assertEquals(products, result, "Returned list should match the predefined list");
	}

	/*
	 * ROOST_METHOD_HASH=getAllProducts_fef141838b
	 * ROOST_METHOD_SIG_HASH=getAllProducts_7e38cc05f6
	 *
	 */
@Test
@Tag("invalid")
public void getAllProductsHandlesRepositoryExceptions() {

    when(productRepository.findAll()).thenThrow(new RuntimeException("Database error"));

    RuntimeException exception = assertThrows(RuntimeException.class, () -> productController.getAllProducts());
    assertEquals("Database error", exception.getMessage(), "Exception message should match the thrown exception");
}

	/*
	 * ROOST_METHOD_HASH=getAllProducts_fef141838b
	 * ROOST_METHOD_SIG_HASH=getAllProducts_7e38cc05f6
	 *
	 */
@Test
@Tag("integration")
public void getAllProductsIntegrationWithRepository() {

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
	@Tag("valid")
	public void getAllProductsCanBeInvokedMultipleTimes() {

		Product product1 = new Product();
		product1.setName("Product 1");
		product1.setDescription("Description 1");
		product1.setPrice(100.0);
		List<Product> products = List.of(product1);
		when(productRepository.findAll()).thenReturn(products);

		List<Product> result1 = productController.getAllProducts();
		List<Product> result2 = productController.getAllProducts();

		assertEquals(result1, result2, "Results of multiple invocations should be identical");
	}

	/*
	 * ROOST_METHOD_HASH=getAllProducts_fef141838b
	 * ROOST_METHOD_SIG_HASH=getAllProducts_7e38cc05f6
	 *
	 */
	@Test
	@Tag("boundary")
	public void getAllProductsHasNoSideEffectsOnRepository() {

		Product product1 = new Product();
		product1.setName("Product 1");
		product1.setDescription("Description 1");
		product1.setPrice(100.0);
		List<Product> products = List.of(product1);
		when(productRepository.findAll()).thenReturn(products);

		List<Product> beforeCall = productRepository.findAll();
		productController.getAllProducts();
		List<Product> afterCall = productRepository.findAll();

		assertEquals(beforeCall, afterCall, "Repository state should remain unchanged");
	}

	/*
	 * ROOST_METHOD_HASH=getAllProducts_fef141838b
	 * ROOST_METHOD_SIG_HASH=getAllProducts_7e38cc05f6
	 *
	 */
	@Test
	@Tag("valid")
	public void getAllProductsReturnsCorrectObjectTypeInList() {

		Product product1 = new Product();
		product1.setName("Product 1");
		product1.setDescription("Description 1");
		product1.setPrice(100.0);
		List<Product> products = List.of(product1);
		when(productRepository.findAll()).thenReturn(products);

		List<Product> result = productController.getAllProducts();

		assertNotNull(result, "Returned list should not be null");
		assertTrue(result.stream().allMatch(item -> item instanceof Product),
				"All objects in the list should be of type Product");
	}

	/*
	 * ROOST_METHOD_HASH=getAllProducts_fef141838b
	 * ROOST_METHOD_SIG_HASH=getAllProducts_7e38cc05f6
	 *
	 */
	@Test
	@Tag("invalid")
	public void getAllProductsThrowsNullPointerExceptionWithUninitializedRepository() {

		productController = new ProductController();

		NullPointerException exception = assertThrows(NullPointerException.class,
				() -> productController.getAllProducts());
		assertEquals(
				"Cannot invoke \"com.bootexample4.products.repository.ProductRepository.findAll()\" because \"this.productRepository\" is null",
				exception.getMessage());
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@BeforeEach
	void setup() {
		validProduct = new Product();
		validProduct.setName("Valid Product");
		validProduct.setDescription("Valid Description");
		validProduct.setPrice(100.50);
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
@Test
@Tag("valid")
public void createProductWithValidInput() {

    when(productRepository.save(validProduct)).thenReturn(validProduct);

    Product result = productController.createProduct(validProduct);

    assertThat(result).isNotNull();
    assertThat(result.getName()).isEqualTo(validProduct.getName());
    assertThat(result.getDescription()).isEqualTo(validProduct.getDescription());
    assertThat(result.getPrice()).isEqualTo(validProduct.getPrice());
    verify(productRepository, times(1)).save(validProduct);
}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@Test
	@Tag("invalid")
	public void createProductWithNullInput() {

		Product nullProduct = null;

		assertThrows(NullPointerException.class, () -> {
			productController.createProduct(nullProduct);
		});
		verify(productRepository, never()).save(nullProduct);
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@Test
	@Tag("invalid")
	public void createProductWithEmptyFields() {

		Product emptyProduct = new Product();
		emptyProduct.setName("");
		emptyProduct.setDescription("");
		emptyProduct.setPrice(0.0);
		when(productRepository.save(emptyProduct)).thenReturn(emptyProduct);

		Product result = productController.createProduct(emptyProduct);

		assertThat(result).isNotNull();
		assertThat(result.getName()).isEqualTo(emptyProduct.getName());
		assertThat(result.getDescription()).isEqualTo(emptyProduct.getDescription());
		assertThat(result.getPrice()).isEqualTo(emptyProduct.getPrice());
		verify(productRepository, times(1)).save(emptyProduct);
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@Test
	@Tag("integration")
	public void createProductWithDuplicateData() {

		Product duplicateProduct = new Product();
		duplicateProduct.setName("Duplicate Product");
		duplicateProduct.setDescription("Duplicate Description");
		duplicateProduct.setPrice(200.00);
		when(productRepository.save(duplicateProduct)).thenReturn(duplicateProduct);

		Product result = productController.createProduct(duplicateProduct);

		assertThat(result).isNotNull();
		assertThat(result.getName()).isEqualTo(duplicateProduct.getName());
		assertThat(result.getDescription()).isEqualTo(duplicateProduct.getDescription());
		assertThat(result.getPrice()).isEqualTo(duplicateProduct.getPrice());
		verify(productRepository, times(1)).save(duplicateProduct);
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
@Test
@Tag("invalid")
public void createProductRepositoryUnavailable() {

    when(productRepository.save(validProduct)).thenThrow(new RuntimeException("Repository Unavailable"));

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
	@Tag("boundary")
	public void createProductWithLargeFieldValues() {

		Product largeProduct = new Product();

		largeProduct.setName("A".repeat(10000));

		largeProduct.setDescription("B".repeat(10000));
		largeProduct.setPrice(100.00);
		when(productRepository.save(largeProduct)).thenReturn(largeProduct);

		Product result = productController.createProduct(largeProduct);

		assertThat(result).isNotNull();
		assertThat(result.getName()).isEqualTo(largeProduct.getName());
		assertThat(result.getDescription()).isEqualTo(largeProduct.getDescription());
		assertThat(result.getPrice()).isEqualTo(largeProduct.getPrice());
		verify(productRepository, times(1)).save(largeProduct);
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@Test
	@Tag("valid")
	public void createProductWithSpecialCharacters() {

		Product specialCharProduct = new Product();
		specialCharProduct.setName("@#Product!%");
		specialCharProduct.setDescription("Description😊");
		specialCharProduct.setPrice(150.00);
		when(productRepository.save(specialCharProduct)).thenReturn(specialCharProduct);

		Product result = productController.createProduct(specialCharProduct);

		assertThat(result).isNotNull();
		assertThat(result.getName()).isEqualTo(specialCharProduct.getName());
		assertThat(result.getDescription()).isEqualTo(specialCharProduct.getDescription());
		assertThat(result.getPrice()).isEqualTo(specialCharProduct.getPrice());
		verify(productRepository, times(1)).save(specialCharProduct);
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@Test
	@Tag("boundary")
	public void createProductWithMaximumPriceValue() {

		Product maxPriceProduct = new Product();
		maxPriceProduct.setName("Max Price Product");
		maxPriceProduct.setDescription("Product Description");
		maxPriceProduct.setPrice(Double.MAX_VALUE);
		when(productRepository.save(maxPriceProduct)).thenReturn(maxPriceProduct);

		Product result = productController.createProduct(maxPriceProduct);

		assertThat(result).isNotNull();
		assertThat(result.getName()).isEqualTo(maxPriceProduct.getName());
		assertThat(result.getDescription()).isEqualTo(maxPriceProduct.getDescription());
		assertThat(result.getPrice()).isEqualTo(maxPriceProduct.getPrice());
		verify(productRepository, times(1)).save(maxPriceProduct);
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@Test
	@Tag("boundary")
	public void createProductWithMinimumPriceValue() {

		Product minPriceProduct = new Product();
		minPriceProduct.setName("Min Price Product");
		minPriceProduct.setDescription("Product Description");
		minPriceProduct.setPrice(0.01);
		when(productRepository.save(minPriceProduct)).thenReturn(minPriceProduct);

		Product result = productController.createProduct(minPriceProduct);

		assertThat(result).isNotNull();
		assertThat(result.getName()).isEqualTo(minPriceProduct.getName());
		assertThat(result.getDescription()).isEqualTo(minPriceProduct.getDescription());
		assertThat(result.getPrice()).isEqualTo(minPriceProduct.getPrice());
		verify(productRepository, times(1)).save(minPriceProduct);
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@Test
	@Tag("invalid")
	public void createProductWithMalformedInput() {

		Product malformedProduct = null;

		assertThrows(RuntimeException.class, () -> {
			productController.createProduct(malformedProduct);
		});
		verify(productRepository, never()).save(null);
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("valid")
	public void validProductIdReturnsProductDetails() {
		Long validId = 1L;

		Product mockProduct = new Product();
		mockProduct.setId(1L);
		mockProduct.setName("Product A");
		mockProduct.setDescription("Description A");
		mockProduct.setPrice(100.0);
		ProductRepository mockRepository = mock(ProductRepository.class);
		when(mockRepository.findById(validId)).thenReturn(Optional.of(mockProduct));

		setPrivateField(productController, "productRepository", mockRepository);
		ResponseEntity<Product> response = productController.getProductById(validId);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(mockProduct, response.getBody());
		verify(mockRepository, times(1)).findById(validId);
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("invalid")
	public void nonExistentProductIdReturns404NotFound() {
		Long invalidId = 99L;
		ProductRepository mockRepository = mock(ProductRepository.class);
		when(mockRepository.findById(invalidId)).thenReturn(Optional.empty());
		setPrivateField(productController, "productRepository", mockRepository);
		ResponseEntity<Product> response = productController.getProductById(invalidId);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertNull(response.getBody());
		verify(mockRepository, times(1)).findById(invalidId);
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("invalid")
	public void productRepositoryNullBehavior() {
		ProductController productControllerWithNullRepo = new ProductController();
		setPrivateField(productControllerWithNullRepo, "productRepository", null);
		Long validId = 1L;
		assertThrows(NullPointerException.class, () -> productControllerWithNullRepo.getProductById(validId));
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("invalid")
	public void nullProductIdReturnsErrorResponse() {
		ProductRepository mockRepository = mock(ProductRepository.class);
		setPrivateField(productController, "productRepository", mockRepository);
		Long nullId = null;
		assertThrows(IllegalArgumentException.class, () -> productController.getProductById(nullId));
		verify(mockRepository, never()).findById(Mockito.any());
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("invalid")
	public void repositoryThrowsException() {
		Long validId = 1L;
		ProductRepository mockRepository = mock(ProductRepository.class);
		when(mockRepository.findById(validId)).thenThrow(new RuntimeException("Simulated repository error"));
		setPrivateField(productController, "productRepository", mockRepository);
		RuntimeException exception = assertThrows(RuntimeException.class,
				() -> productController.getProductById(validId));
		assertEquals("Simulated repository error", exception.getMessage());
		verify(mockRepository, times(1)).findById(validId);
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("boundary")
	public void edgeCaseProductIdResponseValidation() {
		Long minId = Long.MIN_VALUE;
		Long maxId = Long.MAX_VALUE;
		ProductRepository mockRepository = mock(ProductRepository.class);
		when(mockRepository.findById(minId)).thenReturn(Optional.empty());
		when(mockRepository.findById(maxId)).thenReturn(Optional.empty());
		setPrivateField(productController, "productRepository", mockRepository);
		ResponseEntity<Product> minResponse = productController.getProductById(minId);
		ResponseEntity<Product> maxResponse = productController.getProductById(maxId);
		assertEquals(HttpStatus.NOT_FOUND, minResponse.getStatusCode());
		assertNull(minResponse.getBody());
		assertEquals(HttpStatus.NOT_FOUND, maxResponse.getStatusCode());
		assertNull(maxResponse.getBody());
		verify(mockRepository, times(1)).findById(minId);
		verify(mockRepository, times(1)).findById(maxId);
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	private void setPrivateField(Object targetObject, String fieldName, Object fieldValue) {
		try {
			java.lang.reflect.Field field = targetObject.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			field.set(targetObject, fieldValue);
		}
		catch (Exception e) {
			throw new RuntimeException("Failed to set private field", e);
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
	public void deleteExistingProductById() {

		Long validId = 1L;

		Product product = new Product();
		when(productRepository.findById(validId)).thenReturn(Optional.of(product));

		ResponseEntity<Object> response = productController.deleteProduct(validId);

		verify(productRepository, times(1)).delete(product);
		assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}

	/*
	 * ROOST_METHOD_HASH=deleteProduct_5ea3a876a4
	 * ROOST_METHOD_SIG_HASH=deleteProduct_dcaff736d4
	 *
	 */
	@Test
	@Tag("invalid")
	public void deleteNonExistentProductById() {

		Long nonExistentId = 2L;
		when(productRepository.findById(nonExistentId)).thenReturn(Optional.empty());

		ResponseEntity<Object> response = productController.deleteProduct(nonExistentId);

		verify(productRepository, never()).delete(any(Product.class));
		assertThat(response.getStatusCodeValue()).isEqualTo(404);
	}

	/*
	 * ROOST_METHOD_HASH=deleteProduct_5ea3a876a4
	 * ROOST_METHOD_SIG_HASH=deleteProduct_dcaff736d4
	 *
	 */
	@Test
	@Tag("invalid")
	public void deleteProductWithNullId() {

		Long nullId = null;

		Throwable exception = null;
		try {
			productController.deleteProduct(nullId);
		}
		catch (Exception ex) {
			exception = ex;
		}

		assertThat(exception).isInstanceOf(NullPointerException.class);
	}

	/*
	 * ROOST_METHOD_HASH=deleteProduct_5ea3a876a4
	 * ROOST_METHOD_SIG_HASH=deleteProduct_dcaff736d4
	 *
	 */
	@Test
	@Tag("invalid")
	public void deleteProductWhenRepositoryIsNull() {

		ProductController uninitializedController = new ProductController();

		Long validId = 1L;

		Throwable exception = null;
		try {
			uninitializedController.deleteProduct(validId);
		}
		catch (Exception ex) {
			exception = ex;
		}

		assertThat(exception).isInstanceOf(NullPointerException.class);
	}

	/*
	 * ROOST_METHOD_HASH=deleteProduct_5ea3a876a4
	 * ROOST_METHOD_SIG_HASH=deleteProduct_dcaff736d4
	 *
	 */
	@Test
	@Tag("invalid")
	public void deleteProductWithNegativeId() {

		Long negativeId = -1L;
		when(productRepository.findById(negativeId)).thenReturn(Optional.empty());

		ResponseEntity<Object> response = productController.deleteProduct(negativeId);

		verify(productRepository, never()).delete(any(Product.class));
		assertThat(response.getStatusCodeValue()).isEqualTo(404);
	}

	/*
	 * ROOST_METHOD_HASH=deleteProduct_5ea3a876a4
	 * ROOST_METHOD_SIG_HASH=deleteProduct_dcaff736d4
	 *
	 */
	@Test
	@Tag("integration")
	public void deleteProductFromMultipleProducts() {

		Long specificId = 10L;
		Product product = new Product();
		when(productRepository.findById(specificId)).thenReturn(Optional.of(product));

		ResponseEntity<Object> response = productController.deleteProduct(specificId);

		verify(productRepository, times(1)).delete(product);
		assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}

	/*
	 * ROOST_METHOD_HASH=deleteProduct_5ea3a876a4
	 * ROOST_METHOD_SIG_HASH=deleteProduct_dcaff736d4
	 *
	 */
	@Test
	@Tag("boundary")
	public void deleteProductWhenFindByIdFails() {

		Long validId = 1L;
		when(productRepository.findById(validId)).thenThrow(new RuntimeException("Database connection failure"));

		Throwable exception = null;
		try {
			productController.deleteProduct(validId);
		}
		catch (Exception ex) {
			exception = ex;
		}

		assertThat(exception).isInstanceOf(RuntimeException.class).hasMessage("Database connection failure");
	}

	/*
	 * ROOST_METHOD_HASH=deleteProduct_5ea3a876a4
	 * ROOST_METHOD_SIG_HASH=deleteProduct_dcaff736d4
	 *
	 */
	@Test
	@Tag("boundary")
	public void deleteProductDeleteActionFails() {

		Long validId = 1L;
		Product product = new Product();
		when(productRepository.findById(validId)).thenReturn(Optional.of(product));
		doThrow(new RuntimeException("Deletion error")).when(productRepository).delete(product);

		Throwable exception = null;
		try {
			productController.deleteProduct(validId);
		}
		catch (Exception ex) {
			exception = ex;
		}

		assertThat(exception).isInstanceOf(RuntimeException.class).hasMessage("Deletion error");
	}

}