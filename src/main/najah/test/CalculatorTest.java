package main.najah.test;

import main.najah.code.Calculator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(OrderAnnotation.class)
public class CalculatorTest {

    Calculator calculator;

    @BeforeAll
    static void setupAll() {
        System.out.println("Starting Calculator Tests...");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("Finished Calculator Tests.");
    }

    @BeforeEach
    void init() {
        calculator = new Calculator();
        System.out.println("New Calculator Instance Created.");
    }

    @AfterEach
    void cleanup() {
        System.out.println("Test completed.");
    }

    @Test
    @Order(1)
    @DisplayName("Addition: Valid multiple inputs")
    void testAddMultipleNumbers() {
        int result = calculator.add(1, 2, 3, 4);
        assertEquals(10, result);
    }

    @Test
    @Order(2)
    @DisplayName("Addition: No inputs")
    void testAddNoInput() {
        assertEquals(0, calculator.add());
    }

    @Test
    @Order(3)
    @DisplayName("Division: Valid input")
    void testDivideValid() {
        assertEquals(5, calculator.divide(10, 2));
    }

    @Test
    @Order(4)
    @DisplayName("Division: Divide by zero")
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> calculator.divide(10, 0));
    }

    @Test
    @Order(5)
    @DisplayName("Factorial: Valid input")
    void testFactorialValid() {
        assertEquals(120, calculator.factorial(5));
    }

    @ParameterizedTest
    @Order(6)
    @ValueSource(ints = {-1, -5, -10})
    @DisplayName("Factorial: Invalid negative input")
    void testFactorialInvalid(int value) {
        assertThrows(IllegalArgumentException.class, () -> calculator.factorial(value));
    }

    @Test
    @Order(7)
    @Timeout(2)
    @DisplayName("Factorial: Timeout test")
    void testFactorialTimeout() {
        assertEquals(1, calculator.factorial(0));
    }

    @Test
    @Disabled("This test fails intentionally. Fix: 2 + 2 = 4, not 5.")
    void failingTest() {
        assertEquals(5, calculator.add(2, 2));
    }
}
