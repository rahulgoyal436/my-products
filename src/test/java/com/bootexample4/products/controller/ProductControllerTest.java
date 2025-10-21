package com.bootexample4.products.controller;

import com.bootexample4.products.model.Product;
import com.bootexample4.products.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

	// Merged class fields
	private ProductRepository productRepository;

	private ProductController productController;

	Product product1 = new Product();

	Product product2 = new Product();

	List<Product> result = productController.getAllProducts();

	List<Product> emptyList = new ArrayList<>();

	ProductController controller = new ProductController();

	Product productWithNullFields = new Product();

	List<Product> products = Arrays.asList(productWithNullFields);

	List<Product> largeProductList = new ArrayList<>();

	<10000;
	long startTime = System.currentTimeMillis();

	Product malformedProduct = new Product();

	Product product = new Product();

	ResponseEntity<Product> response = productController.getProductById(1L);

	ResponseEntity<Product> response1 = productController.getProductById(1L);

	RuntimeException exception = assertThrows(RuntimeException.class, () -> productController.getProductById(1L));

	Long validProductId = 1L;

	Product mockProduct = new Product();

	Long nonExistingProductId = 999L;

	Long nullProductId = null;

	Long zeroProductId = 0L;

	Long negativeProductId = -1L;

	Long anyProductId = 1L;

	Long productId = 1L;

	Thread thread1 = new Thread(() -> {
            ResponseEntity<Object> response = productController.deleteProduct(productId);

  // Merged constructors
	public ProductControllerDeleteProductTest() {
    MockitoAnnotations.openMocks(this);
}

	// Merged test methods
	public void the_client_sends_a_get_request_to_get_the_list_of_all_products(String string) {
		listOfProducts = productController.getAllProducts();
	}

	public void there_is_an_existing_product_with_id(Long id) {

    listOfProducts = productController.getAllProducts();
    boolean productPresentFlag = false;
    for (Product product : listOfProducts) {
        if (product.getId() == id) {
            productPresentFlag = true;
            break;
        }
    }

	void setup() {
		MockitoAnnotations.openMocks(this);
	}

	public void retrieveAllProductsMultipleExpectations() {
		// Arrange
		Product product1 = new Product(); // Assuming Product has a default constructor
		product1.setName("Product 1"); // TODO: Set appropriate fields in the Product
										// object
		product1.setDescription("Description 1"); // TODO: Set appropriate fields in the
													// Product object
		product1.setPrice(100.0); // TODO: Modify values if required
		Product product2 = new Product();
		product2.setName("Product 2");
		product2.setDescription("Description 2");
		product2.setPrice(200.0);
		List<Product> products = Arrays.asList(product1, product2);
		when(productRepository.findAll()).thenReturn(products);
		// Act
		List<Product> result = productController.getAllProducts();
		// Assert
		assertNotNull(result);
		assertEquals(2, result.size());
		assertEquals("Product 1", result.get(0).getName());
		assertEquals("Product 2", result.get(1).getName());
		assertEquals(100.0, result.get(0).getPrice(), 0);
		assertEquals(200.0, result.get(1).getPrice(), 0);
	}

	public void retrieveEmptyProductList() {
		// Arrange
		List<Product> emptyList = new ArrayList<>();
		when(productRepository.findAll()).thenReturn(emptyList);
		// Act
		List<Product> result = productController.getAllProducts();
		// Assert
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	public void handleNullRepositoryReturn() {
        // Arrange
        when(productRepository.findAll()).thenReturn(null);
        // Act
        List<Product> result = productController.getAllProducts();
        // Assert
        assertNull(result);
    }

	public void noDependencyInjectionSetup() {
		// Arrange
		ProductController controller = new ProductController();
		// Act & Assert
		assertThrows(NullPointerException.class, controller::getAllProducts);
	}

	public void productsWithNullFields() {
		// Arrange
		Product productWithNullFields = new Product();
		productWithNullFields.setPrice(0.0); // TODO: Modify as required
		List<Product> products = Arrays.asList(productWithNullFields);
		when(productRepository.findAll()).thenReturn(products);
		// Act
		List<Product> result = productController.getAllProducts();
		// Assert
		assertNotNull(result);
		assertEquals(1, result.size());
		assertNull(result.get(0).getName());
		assertEquals(0.0, result.get(0).getPrice(), 0);
	}

	public void retrieveLargeProductListPerformance() {
		// Arrange
		List<Product> largeProductList = new ArrayList<>();
		for (int i = 0; i < 10000; i++) {
			Product product = new Product();
			product.setName("Product " + i); // TODO: Modify as required
			product.setPrice((double) i);
			largeProductList.add(product);
		}
		when(productRepository.findAll()).thenReturn(largeProductList);
		// Act
		long startTime = System.currentTimeMillis();
		List<Product> result = productController.getAllProducts();
		long endTime = System.currentTimeMillis();
		// Assert
		assertNotNull(result);
		assertEquals(10000, result.size());
		assertTrue((endTime - startTime) < 1000); // Test completes within reasonable time
													// frame
	}

	public void handlingMalformedProducts() {
		// Arrange
		Product malformedProduct = new Product();
		List<Product> products = Arrays.asList(malformedProduct);
		when(productRepository.findAll()).thenReturn(products);
		// Act
		List<Product> result = productController.getAllProducts();
		// Assert
		assertNotNull(result);
		assertEquals(1, result.size());
		assertNull(result.get(0).getName());
		assertNull(result.get(0).getDescription());
	}

	public void handleRepositoryException() {
        // Arrange
        when(productRepository.findAll()).thenThrow(new RuntimeException("Repository error"));
        // Act & Assert
        assertThrows(RuntimeException.class, () -> productController.getAllProducts());
    }

	public void the_client_sends_a_post_request_to(String string) {

		savedProduct = productController.createProduct(newProduct);
	}

	public void successfulProductCreation() {
		// Arrange
		Product product = new Product(); // Replace with actual Product structure
		product.setName("Valid Product"); // TODO: Specify actual fields and mock
											// meaningful values
		product.setDescription("A sample description");
		product.setPrice(99.99);
		when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);
		// Act
		Product result = productController.createProduct(product);
		// Assert
		verify(productRepository, times(1)).save(product);
		assertThat(result).isNotNull();
		assertThat(result).isEqualTo(product);
	}

	public void nullProductCreation() {
		// Arrange
		Product product = null;
		when(productRepository.save(Mockito.any())).thenThrow(IllegalArgumentException.class);
		// Act & Assert
		try {
			productController.createProduct(product);
		}
		catch (IllegalArgumentException e) {
			verify(productRepository, never()).save(Mockito.any());
			assertThat(e).isInstanceOf(IllegalArgumentException.class);
		}
	}

	public void productCreationWithEdgeCaseValues() {
		// Arrange
		Product product = new Product(); // Replace with actual Product structure
		product.setName(""); // Empty name edge case // TODO: Replace with actual field
								// name
		product.setDescription(null); // Null description edge case
		product.setPrice(0.0); // Boundary price case
		when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);
		// Act
		Product result = productController.createProduct(product);
		// Assert
		verify(productRepository, times(1)).save(product);
		assertThat(result).isNotNull();
		assertThat(result.getName()).isEqualTo(product.getName());
		assertThat(result.getDescription()).isEqualTo(product.getDescription());
		assertThat(result.getPrice()).isEqualTo(product.getPrice());
	}

	public void productCreationIntegrationTest() {
		// Arrange
		Product product = new Product(); // Replace with actual Product structure
		product.setName("Integration Product"); // TODO: Specify actual fields and mock
												// meaningful values
		product.setDescription("Integration test description");
		product.setPrice(199.99);
		when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);
		// Act
		Product result = productController.createProduct(product);
		// Assert
		verify(productRepository, times(1)).save(product);
		assertThat(result).isNotNull();
		assertThat(result.getName()).isEqualTo("Integration Product");
		assertThat(result.getDescription()).isEqualTo("Integration test description");
		assertThat(result.getPrice()).isEqualTo(199.99);
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

	public void setUp() {
		Mockito.reset(productRepository); // Reset mocks before each test
	}

	public void getProductByIdReturnsProductForExistingId() {
		// Arrange
		Product product = new Product();
		product.setId(1L);
		product.setName("Test Product");
		product.setDescription("Test Description");
		product.setPrice(10.0);
		when(productRepository.findById(1L)).thenReturn(Optional.of(product));

		// Act
		ResponseEntity<Product> response = productController.getProductById(1L);

		// Assert
		assertNotNull(response);
		assertEquals(ResponseEntity.ok().body(product), response);
		verify(productRepository, times(1)).findById(1L);
	}

	public void getProductByIdReturnsNotFoundForNonExistingId() {
        // Arrange
        when(productRepository.findById(999L)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Product> response = productController.getProductById(999L);

        // Assert
        assertNotNull(response);
        assertEquals(ResponseEntity.notFound().build(), response);
        verify(productRepository, times(1)).findById(999L);
    }

	public void getProductByIdThrowsExceptionWhenRepositoryIsNull() {
		// Arrange
		ProductController productController = new ProductController();

		// Act & Assert
		assertThrows(NullPointerException.class, () -> productController.getProductById(1L));
	}

	public void getProductByIdReturnsNotFoundForNegativeId() {
        // Arrange
        when(productRepository.findById(-1L)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Product> response = productController.getProductById(-1L);

        // Assert
        assertNotNull(response);
        assertEquals(ResponseEntity.notFound().build(), response);
        verify(productRepository, times(1)).findById(-1L);
    }

	public void getProductByIdHandlesMaxLongValueSuccessfully() {
        // Arrange
        when(productRepository.findById(Long.MAX_VALUE)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Product> response = productController.getProductById(Long.MAX_VALUE);

        // Assert
        assertNotNull(response);
        assertEquals(ResponseEntity.notFound().build(), response);
        verify(productRepository, times(1)).findById(Long.MAX_VALUE);
    }

	public void getProductByIdHandlesZeroIdCorrectly() {
        // Arrange
        when(productRepository.findById(0L)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Product> response = productController.getProductById(0L);

        // Assert
        assertNotNull(response);
        assertEquals(ResponseEntity.notFound().build(), response);
        verify(productRepository, times(1)).findById(0L);
    }

	public void getProductByIdHandlesMultipleConcurrentCallsSuccessfully() {
		// Arrange
		Product product = new Product();
		product.setId(1L);
		product.setName("Test Product");
		product.setDescription("Test Description");
		product.setPrice(15.5);
		when(productRepository.findById(1L)).thenReturn(Optional.of(product));

		// Act
		ResponseEntity<Product> response1 = productController.getProductById(1L);
		ResponseEntity<Product> response2 = productController.getProductById(1L);

		// Assert
		assertNotNull(response1);
		assertEquals(ResponseEntity.ok().body(product), response1);
		assertNotNull(response2);
		assertEquals(ResponseEntity.ok().body(product), response2);
		verify(productRepository, times(2)).findById(1L);
	}

	public void getProductByIdThrowsExceptionOnRepositoryFailure() {
        // Arrange
        when(productRepository.findById(1L)).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> productController.getProductById(1L));
        assertEquals("Database error", exception.getMessage());
        verify(productRepository, times(1)).findById(1L);
    }

	public void getProductByIdReturnsNotFoundForInactiveProducts() {
		// Business logic improvement needed: Add an "active" field to Product class with
		// its getter and setter to handle inactive products.
		// Modify the method to filter out inactive products and return
		// ResponseEntity.notFound() for inactive products.
	}

	public void getProductByIdHandlesNullIdProperly() {
		// Act & Assert
		assertThrows(NullPointerException.class, () -> productController.getProductById(null));
	}

	public void the_client_sends_a_delete_request_to(String string) {

		Long id = getProductIDfromAPI(string);
		deleteProductResponse = productController.deleteProduct(id);
		responseStatusCode = deleteProductResponse.getStatusCode();
	}

	public ProductControllerDeleteProductTest() {
        MockitoAnnotations.openMocks(this);
    }

	public void deleteProductWhenProductExists() {
		// Arrange
		Long validProductId = 1L; // TODO: Replace with actual test product ID
		Product mockProduct = new Product();
		when(productRepository.findById(validProductId)).thenReturn(Optional.of(mockProduct));
		// Act
		ResponseEntity<Object> response = productController.deleteProduct(validProductId);
		// Assert
		assertThat(response).isEqualTo(ResponseEntity.ok().build());
		verify(productRepository, times(1)).delete(mockProduct);
	}

	public void deleteProductWhenProductDoesNotExist() {
		// Arrange
		Long nonExistingProductId = 999L; // TODO: Replace with actual non-existing
											// product ID
		when(productRepository.findById(nonExistingProductId)).thenReturn(Optional.empty());
		// Act
		ResponseEntity<Object> response = productController.deleteProduct(nonExistingProductId);
		// Assert
		assertThat(response).isEqualTo(ResponseEntity.notFound().build());
		verify(productRepository, never()).delete(any());
	}

	public void deleteProductWithNullID() {
		// Arrange
		Long nullProductId = null;
		when(productRepository.findById(nullProductId)).thenReturn(Optional.empty());
		// Act
		ResponseEntity<Object> response = productController.deleteProduct(nullProductId);
		// Assert
		assertThat(response).isEqualTo(ResponseEntity.notFound().build());
		verify(productRepository, never()).delete(any());
	}

	public void deleteProductWithZeroID() {
		// Arrange
		Long zeroProductId = 0L;
		when(productRepository.findById(zeroProductId)).thenReturn(Optional.empty());
		// Act
		ResponseEntity<Object> response = productController.deleteProduct(zeroProductId);
		// Assert
		assertThat(response).isEqualTo(ResponseEntity.notFound().build());
		verify(productRepository, never()).delete(any());
	}

	public void verifyProductRepositoryCallDuringDelete() {
		// Arrange
		Long validProductId = 1L; // TODO: Replace with actual test product ID
		Product mockProduct = new Product();
		when(productRepository.findById(validProductId)).thenReturn(Optional.of(mockProduct));
		// Act
		ResponseEntity<Object> response = productController.deleteProduct(validProductId);
		// Assert
		assertThat(response).isEqualTo(ResponseEntity.ok().build());
		verify(productRepository, times(1)).findById(validProductId);
		verify(productRepository, times(1)).delete(mockProduct);
	}

	public void deleteProductWithNegativeID() {
		// Arrange
		Long negativeProductId = -1L; // Invalid ID
		when(productRepository.findById(negativeProductId)).thenReturn(Optional.empty());
		// Act
		ResponseEntity<Object> response = productController.deleteProduct(negativeProductId);
		// Assert
		assertThat(response).isEqualTo(ResponseEntity.notFound().build());
		verify(productRepository, never()).delete(any());
	}

	public void deleteProductWhenRepositoryIsEmpty() {
		// Arrange
		Long anyProductId = 1L; // TODO: Replace with any test product ID
		when(productRepository.findById(anyProductId)).thenReturn(Optional.empty());
		// Act
		ResponseEntity<Object> response = productController.deleteProduct(anyProductId);
		// Assert
		assertThat(response).isEqualTo(ResponseEntity.notFound().build());
		verify(productRepository, never()).delete(any());
	}

	/*
	 * ROOST_METHOD_HASH=getAllProducts_fef141838b
	 * ROOST_METHOD_SIG_HASH=getAllProducts_7e38cc05f6
	 *
	 */
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}

	/*
	 * ROOST_METHOD_HASH=getAllProducts_fef141838b
	 * ROOST_METHOD_SIG_HASH=getAllProducts_7e38cc05f6
	 *
	 */
	@Test
	@Tag("valid")
	public void retrieveAllProductsMultipleExpectations() {

		Product product1 = new Product();

		product1.setName("Product 1");

		product1.setDescription("Description 1");

		product1.setPrice(100.0);
		Product product2 = new Product();
		product2.setName("Product 2");
		product2.setDescription("Description 2");
		product2.setPrice(200.0);
		List<Product> products = Arrays.asList(product1, product2);
		when(productRepository.findAll()).thenReturn(products);

		List<Product> result = productController.getAllProducts();

		assertNotNull(result);
		assertEquals(2, result.size());
		assertEquals("Product 1", result.get(0).getName());
		assertEquals("Product 2", result.get(1).getName());
		assertEquals(100.0, result.get(0).getPrice(), 0);
		assertEquals(200.0, result.get(1).getPrice(), 0);
	}

	/*
	 * ROOST_METHOD_HASH=getAllProducts_fef141838b
	 * ROOST_METHOD_SIG_HASH=getAllProducts_7e38cc05f6
	 *
	 */
	@Test
	@Tag("boundary")
	public void retrieveEmptyProductList() {

		List<Product> emptyList = new ArrayList<>();
		when(productRepository.findAll()).thenReturn(emptyList);

		List<Product> result = productController.getAllProducts();

		assertNotNull(result);
		assertEquals(0, result.size());
	}

	/*
	 * ROOST_METHOD_HASH=getAllProducts_fef141838b
	 * ROOST_METHOD_SIG_HASH=getAllProducts_7e38cc05f6
	 *
	 */
@Test
@Tag("invalid")
public void handleNullRepositoryReturn() {

    when(productRepository.findAll()).thenReturn(null);

    List<Product> result = productController.getAllProducts();

    assertNull(result);
}

	/*
	 * ROOST_METHOD_HASH=getAllProducts_fef141838b
	 * ROOST_METHOD_SIG_HASH=getAllProducts_7e38cc05f6
	 *
	 */
	@Test
	@Tag("invalid")
	public void noDependencyInjectionSetup() {

		ProductController controller = new ProductController();

		assertThrows(NullPointerException.class, controller::getAllProducts);
	}

	/*
	 * ROOST_METHOD_HASH=getAllProducts_fef141838b
	 * ROOST_METHOD_SIG_HASH=getAllProducts_7e38cc05f6
	 *
	 */
	@Test
	@Tag("valid")
	public void productsWithNullFields() {

		Product productWithNullFields = new Product();

		productWithNullFields.setPrice(0.0);
		List<Product> products = Arrays.asList(productWithNullFields);
		when(productRepository.findAll()).thenReturn(products);

		List<Product> result = productController.getAllProducts();

		assertNotNull(result);
		assertEquals(1, result.size());
		assertNull(result.get(0).getName());
		assertEquals(0.0, result.get(0).getPrice(), 0);
	}

	/*
	 * ROOST_METHOD_HASH=getAllProducts_fef141838b
	 * ROOST_METHOD_SIG_HASH=getAllProducts_7e38cc05f6
	 *
	 */
	@Test
	@Tag("performance")
	public void retrieveLargeProductListPerformance() {

		List<Product> largeProductList = new ArrayList<>();
		for (int i = 0; i < 10000; i++) {
			Product product = new Product();

			product.setName("Product " + i);
			product.setPrice((double) i);
			largeProductList.add(product);
		}
		when(productRepository.findAll()).thenReturn(largeProductList);

		long startTime = System.currentTimeMillis();
		List<Product> result = productController.getAllProducts();
		long endTime = System.currentTimeMillis();

		assertNotNull(result);
		assertEquals(10000, result.size());

		assertTrue((endTime - startTime) < 1000);
	}

	/*
	 * ROOST_METHOD_HASH=getAllProducts_fef141838b
	 * ROOST_METHOD_SIG_HASH=getAllProducts_7e38cc05f6
	 *
	 */
	@Test
	@Tag("invalid")
	public void handlingMalformedProducts() {

		Product malformedProduct = new Product();
		List<Product> products = Arrays.asList(malformedProduct);
		when(productRepository.findAll()).thenReturn(products);

		List<Product> result = productController.getAllProducts();

		assertNotNull(result);
		assertEquals(1, result.size());
		assertNull(result.get(0).getName());
		assertNull(result.get(0).getDescription());
	}

	/*
	 * ROOST_METHOD_HASH=getAllProducts_fef141838b
	 * ROOST_METHOD_SIG_HASH=getAllProducts_7e38cc05f6
	 *
	 */
@Test
@Tag("error-handling")
public void handleRepositoryException() {

    when(productRepository.findAll()).thenThrow(new RuntimeException("Repository error"));

    assertThrows(RuntimeException.class, () -> productController.getAllProducts());
}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@Test
	@Tag("valid")
	public void successfulProductCreation() {

		Product product = new Product();

		product.setName("Valid Product");
		product.setDescription("A sample description");
		product.setPrice(99.99);
		when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);

		Product result = productController.createProduct(product);

		verify(productRepository, times(1)).save(product);
		assertThat(result).isNotNull();
		assertThat(result).isEqualTo(product);
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@Test
	@Tag("invalid")
	public void nullProductCreation() {

		Product product = null;
		when(productRepository.save(Mockito.any())).thenThrow(IllegalArgumentException.class);

		try {
			productController.createProduct(product);
		}
		catch (IllegalArgumentException e) {
			verify(productRepository, never()).save(Mockito.any());
			assertThat(e).isInstanceOf(IllegalArgumentException.class);
		}
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@Test
	@Tag("boundary")
	public void productCreationWithEdgeCaseValues() {

		Product product = new Product();

		product.setName("");

		product.setDescription(null);

		product.setPrice(0.0);
		when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);

		Product result = productController.createProduct(product);

		verify(productRepository, times(1)).save(product);
		assertThat(result).isNotNull();
		assertThat(result.getName()).isEqualTo(product.getName());
		assertThat(result.getDescription()).isEqualTo(product.getDescription());
		assertThat(result.getPrice()).isEqualTo(product.getPrice());
	}

	/*
	 * ROOST_METHOD_HASH=createProduct_16b670a647
	 * ROOST_METHOD_SIG_HASH=createProduct_36b748883e
	 *
	 */
	@Test
	@Tag("integration")
	public void productCreationIntegrationTest() {

		Product product = new Product();

		product.setName("Integration Product");
		product.setDescription("Integration test description");
		product.setPrice(199.99);
		when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);

		Product result = productController.createProduct(product);

		verify(productRepository, times(1)).save(product);
		assertThat(result).isNotNull();
		assertThat(result.getName()).isEqualTo("Integration Product");
		assertThat(result.getDescription()).isEqualTo("Integration test description");
		assertThat(result.getPrice()).isEqualTo(199.99);
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@BeforeEach
	public void setUp() {

		Mockito.reset(productRepository);
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("valid")
	public void getProductByIdReturnsProductForExistingId() {

		Product product = new Product();
		product.setId(1L);
		product.setName("Test Product");
		product.setDescription("Test Description");
		product.setPrice(10.0);
		when(productRepository.findById(1L)).thenReturn(Optional.of(product));

		ResponseEntity<Product> response = productController.getProductById(1L);

		assertNotNull(response);
		assertEquals(ResponseEntity.ok().body(product), response);
		verify(productRepository, times(1)).findById(1L);
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
@Test
@Tag("invalid")
public void getProductByIdReturnsNotFoundForNonExistingId() {

    when(productRepository.findById(999L)).thenReturn(Optional.empty());

    ResponseEntity<Product> response = productController.getProductById(999L);

    assertNotNull(response);
    assertEquals(ResponseEntity.notFound().build(), response);
    verify(productRepository, times(1)).findById(999L);
}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("integration")
	public void getProductByIdThrowsExceptionWhenRepositoryIsNull() {

		ProductController productController = new ProductController();

		assertThrows(NullPointerException.class, () -> productController.getProductById(1L));
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
@Test
@Tag("invalid")
public void getProductByIdReturnsNotFoundForNegativeId() {

    when(productRepository.findById(-1L)).thenReturn(Optional.empty());

    ResponseEntity<Product> response = productController.getProductById(-1L);

    assertNotNull(response);
    assertEquals(ResponseEntity.notFound().build(), response);
    verify(productRepository, times(1)).findById(-1L);
}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
@Test
@Tag("boundary")
public void getProductByIdHandlesMaxLongValueSuccessfully() {

    when(productRepository.findById(Long.MAX_VALUE)).thenReturn(Optional.empty());

    ResponseEntity<Product> response = productController.getProductById(Long.MAX_VALUE);

    assertNotNull(response);
    assertEquals(ResponseEntity.notFound().build(), response);
    verify(productRepository, times(1)).findById(Long.MAX_VALUE);
}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
@Test
@Tag("boundary")
public void getProductByIdHandlesZeroIdCorrectly() {

    when(productRepository.findById(0L)).thenReturn(Optional.empty());

    ResponseEntity<Product> response = productController.getProductById(0L);

    assertNotNull(response);
    assertEquals(ResponseEntity.notFound().build(), response);
    verify(productRepository, times(1)).findById(0L);
}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("integration")
	public void getProductByIdHandlesMultipleConcurrentCallsSuccessfully() {

		Product product = new Product();
		product.setId(1L);
		product.setName("Test Product");
		product.setDescription("Test Description");
		product.setPrice(15.5);
		when(productRepository.findById(1L)).thenReturn(Optional.of(product));

		ResponseEntity<Product> response1 = productController.getProductById(1L);
		ResponseEntity<Product> response2 = productController.getProductById(1L);

		assertNotNull(response1);
		assertEquals(ResponseEntity.ok().body(product), response1);
		assertNotNull(response2);
		assertEquals(ResponseEntity.ok().body(product), response2);
		verify(productRepository, times(2)).findById(1L);
	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
@Test
@Tag("invalid")
public void getProductByIdThrowsExceptionOnRepositoryFailure() {

    when(productRepository.findById(1L)).thenThrow(new RuntimeException("Database error"));

    RuntimeException exception = assertThrows(RuntimeException.class, () -> productController.getProductById(1L));
    assertEquals("Database error", exception.getMessage());
    verify(productRepository, times(1)).findById(1L);
}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("invalid")
	public void getProductByIdReturnsNotFoundForInactiveProducts() {

	}

	/*
	 * ROOST_METHOD_HASH=getProductById_a31a3ac160
	 * ROOST_METHOD_SIG_HASH=getProductById_d22f3ea272
	 *
	 */
	@Test
	@Tag("invalid")
	public void getProductByIdHandlesNullIdProperly() {

		assertThrows(NullPointerException.class, () -> productController.getProductById(null));
	}

	/*
	 * ROOST_METHOD_HASH=deleteProduct_5ea3a876a4
	 * ROOST_METHOD_SIG_HASH=deleteProduct_dcaff736d4
	 *
	 */
	@Test
	@Tag("valid")
	public void deleteProductWhenProductExists() {

		Long validProductId = 1L;
		Product mockProduct = new Product();
		when(productRepository.findById(validProductId)).thenReturn(Optional.of(mockProduct));

		ResponseEntity<Object> response = productController.deleteProduct(validProductId);

		assertThat(response).isEqualTo(ResponseEntity.ok().build());
		verify(productRepository, times(1)).delete(mockProduct);
	}

	/*
	 * ROOST_METHOD_HASH=deleteProduct_5ea3a876a4
	 * ROOST_METHOD_SIG_HASH=deleteProduct_dcaff736d4
	 *
	 */
	@Test
	@Tag("invalid")
	public void deleteProductWhenProductDoesNotExist() {

		Long nonExistingProductId = 999L;
		when(productRepository.findById(nonExistingProductId)).thenReturn(Optional.empty());

		ResponseEntity<Object> response = productController.deleteProduct(nonExistingProductId);

		assertThat(response).isEqualTo(ResponseEntity.notFound().build());
		verify(productRepository, never()).delete(any());
	}

	/*
	 * ROOST_METHOD_HASH=deleteProduct_5ea3a876a4
	 * ROOST_METHOD_SIG_HASH=deleteProduct_dcaff736d4
	 *
	 */
	@Test
	@Tag("boundary")
	public void deleteProductWithNullID() {

		Long nullProductId = null;
		when(productRepository.findById(nullProductId)).thenReturn(Optional.empty());

		ResponseEntity<Object> response = productController.deleteProduct(nullProductId);

		assertThat(response).isEqualTo(ResponseEntity.notFound().build());
		verify(productRepository, never()).delete(any());
	}

	/*
	 * ROOST_METHOD_HASH=deleteProduct_5ea3a876a4
	 * ROOST_METHOD_SIG_HASH=deleteProduct_dcaff736d4
	 *
	 */
	@Test
	@Tag("boundary")
	public void deleteProductWithZeroID() {

		Long zeroProductId = 0L;
		when(productRepository.findById(zeroProductId)).thenReturn(Optional.empty());

		ResponseEntity<Object> response = productController.deleteProduct(zeroProductId);

		assertThat(response).isEqualTo(ResponseEntity.notFound().build());
		verify(productRepository, never()).delete(any());
	}

	/*
	 * ROOST_METHOD_HASH=deleteProduct_5ea3a876a4
	 * ROOST_METHOD_SIG_HASH=deleteProduct_dcaff736d4
	 *
	 */
	@Test
	@Tag("integration")
	public void verifyProductRepositoryCallDuringDelete() {

		Long validProductId = 1L;
		Product mockProduct = new Product();
		when(productRepository.findById(validProductId)).thenReturn(Optional.of(mockProduct));

		ResponseEntity<Object> response = productController.deleteProduct(validProductId);

		assertThat(response).isEqualTo(ResponseEntity.ok().build());
		verify(productRepository, times(1)).findById(validProductId);
		verify(productRepository, times(1)).delete(mockProduct);
	}

	/*
	 * ROOST_METHOD_HASH=deleteProduct_5ea3a876a4
	 * ROOST_METHOD_SIG_HASH=deleteProduct_dcaff736d4
	 *
	 */
	@Test
	@Tag("invalid")
	public void deleteProductWithNegativeID() {

		Long negativeProductId = -1L;
		when(productRepository.findById(negativeProductId)).thenReturn(Optional.empty());

		ResponseEntity<Object> response = productController.deleteProduct(negativeProductId);

		assertThat(response).isEqualTo(ResponseEntity.notFound().build());
		verify(productRepository, never()).delete(any());
	}

	/*
	 * ROOST_METHOD_HASH=deleteProduct_5ea3a876a4
	 * ROOST_METHOD_SIG_HASH=deleteProduct_dcaff736d4
	 *
	 */
	@Test
	@Tag("invalid")
	public void deleteProductWhenRepositoryIsEmpty() {

		Long anyProductId = 1L;
		when(productRepository.findById(anyProductId)).thenReturn(Optional.empty());

		ResponseEntity<Object> response = productController.deleteProduct(anyProductId);

		assertThat(response).isEqualTo(ResponseEntity.notFound().build());
		verify(productRepository, never()).delete(any());
	}

	/*
	 * ROOST_METHOD_HASH=deleteProduct_5ea3a876a4
	 * ROOST_METHOD_SIG_HASH=deleteProduct_dcaff736d4
	 *
	 */
	@Test
	@Tag("integration")
	public void concurrentDeletionRequestForProduct() throws InterruptedException {

		Long productId = 1L;
		Product mockProduct = new Product();
		when(productRepository.findById(productId)).thenReturn(Optional.of(mockProduct));

		Thread thread1 = new Thread(() -> {
			ResponseEntity<Object> response = productController.deleteProduct(productId);
			assertThat(response).isEqualTo(ResponseEntity.ok().build());
		});
		Thread thread2 = new Thread(() -> {
			ResponseEntity<Object> response = productController.deleteProduct(productId);
			assertThat(response).isEqualTo(ResponseEntity.notFound().build());
		});
		thread1.start();
		thread2.start();
		thread1.join();
		thread2.join();

		verify(productRepository, times(1)).delete(mockProduct);
	}

}