package streams;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import streams.helpers.Product;
import streams.helpers.ProductCategory;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;

public class StreamsTest {

    private Streams sut;

    @BeforeEach
    void setup() {
        sut = new Streams();
    }

    @Test
    void test1RemoveStringsWithMoreThanThreeCharacters() {
        // Arrange
        List<String> input = asList("Welcome", "to", "Java", "12", "Streams");

        // Act
        List<String> filteredStrings = sut.filterStringsShorterThanThreeCharacters(input);

        // Assert
        assertThat(filteredStrings, contains("to", "12"));
    }

    @Test
    void test2RemoveNonNumeralStrings() {
        // Arrange
        List<String> input = asList("Welcome", "to", "Java", "12", "Streams");

        // Act
        List<String> filteredStrings = sut.filterStringsThatContainOnlyNumerals(input);

        // Assert
        assertThat(filteredStrings, contains("12"));
    }

    @Test
    void test3AFindShortestString() {
        // Arrange
        List<String> input = asList("Welcome", "to", "Java", "12", "Streams");

        // Act
        String foundString = sut.findShortestString(input);

        // Assert
        Assertions.assertEquals("to", foundString);
    }

    @Test
    void test3BFindShortestStringOfEmptyList() {
        // Arrange
        List<String> input = asList(new String[]{});

        // Act
        String foundString = sut.findShortestString(input);

        // Assert
        Assertions.assertEquals(null, foundString);
    }

    @Test
    void test4CreateAFullSentenceFromTheList() {
        // Arrange
        List<String> input = asList("Welcome", "to", "Java", "12", "Streams");

        // Act
        String foundString = sut.createAFullSentenceFromTheList(input);

        // Assert
        Assertions.assertEquals("Welcome to Java 8 Streams", foundString);
    }

    @Test
    void test5CalculateTotalCostOfAllProducts() {
        // Arrange
        var input = initialiseTestProductSet();

        // Act
        int totalValue = sut.calculateTotalCostOfAllProducts(input);

        // Assert
        Assertions.assertEquals(7286, totalValue);
    }

    @Test
    void test6CalculateTotalCostOfAllGadgets() {
        // Arrange
        var input = initialiseTestProductSet();

        // Act
        int totalValue = sut.calculateTotalCostOfAllGadgets(input);

        // Assert
        Assertions.assertEquals(3886, totalValue);
    }

    private List<Product> initialiseTestProductSet() {
        final int PRICE_TV = 1200;
        final int PRICE_LAPTOP = 2300;
        final int PRICE_TABLET = 149;
        final int PRICE_PHONE = 237;
        final int PRICE_TABLE = 3400;

        var tv = new Product("TV", PRICE_TV);
        var laptop = new Product("Laptop", PRICE_LAPTOP);
        var tablet = new Product("tablet", PRICE_TABLET);
        var phone = new Product("phone", PRICE_PHONE);
        var table = new Product("table", PRICE_TABLE, ProductCategory.FURNITURE);

        return asList(tv, laptop, tablet, phone, table);
    }
}
