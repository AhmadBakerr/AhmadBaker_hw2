package main.najah.test;

import main.najah.code.Product;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import static org.junit.jupiter.api.Assertions.*;

@Execution(ExecutionMode.CONCURRENT)
public class ProductTest {

    Product product;

    @BeforeEach
    void init() {
        product = new Product("Book", 100.0);
    }

    @Test
    @DisplayName("Product: Apply valid discount")
    void testValidDiscount() {
        product.applyDiscount(10);
        assertAll("Discount Calculations",
                () -> assertEquals(10, product.getDiscount()),
                () -> assertEquals(90.0, product.getFinalPrice())
        );
    }

    @ParameterizedTest
    @ValueSource(doubles = {-10, 60})
    @DisplayName("Product: Invalid discount values")
    void testInvalidDiscount(double discount) {
        assertThrows(IllegalArgumentException.class, () -> product.applyDiscount(discount));
    }

    @Test
    @DisplayName("Product: Constructor with negative price throws exception")
    void testNegativePrice() {
        assertThrows(IllegalArgumentException.class, () -> new Product("Bad", -20));
    }

    @Test
    @Timeout(2)
    @DisplayName("Product: Timeout test")
    void testTimeout() {
        product.applyDiscount(0);
        assertEquals(100.0, product.getFinalPrice());
    }
}
