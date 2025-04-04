package main.najah.test;

import main.najah.code.Recipe;
import main.najah.code.RecipeBook;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeBookTest {

    RecipeBook book;
    Recipe r1;

    @BeforeEach
    void setUp() {
        book = new RecipeBook();
        r1 = new Recipe();
        r1.setName("Mocha");
    }

    @Test
    @DisplayName("Add recipe successfully")
    void testAddRecipe() {
        assertTrue(book.addRecipe(r1));
    }

    @Test
    @DisplayName("Add duplicate recipe fails")
    void testDuplicateRecipe() {
        book.addRecipe(r1);
        assertFalse(book.addRecipe(r1));
    }

    @Test
    @DisplayName("Delete recipe by index")
    void testDeleteRecipe() {
        book.addRecipe(r1);
        assertEquals("Mocha", book.deleteRecipe(0));
    }

    @Test
    @DisplayName("Edit recipe at index")
    void testEditRecipe() {
        book.addRecipe(r1);
        Recipe newRecipe = new Recipe();
        newRecipe.setName("Latte");
        assertEquals("Mocha", book.editRecipe(0, newRecipe));
    }

    @Test
    @Timeout(2)
    @DisplayName("RecipeBook: Timeout test")
    void testTimeout() {
        assertNotNull(book.getRecipes());
    }
}
