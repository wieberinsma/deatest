package cdi.workshop.resources;

import cdi.workshop.services.ItemService;
import cdi.workshop.services.dto.ItemDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Nodig om Mockito te laten werken met jUnit 5 wanneer je in je test automatisch wil injecten; zie
 * {@link InjectMocks}.
 * Je hebt hierdoor zelfs geen {@link BeforeEach} methode meer nodig, omdat Mockito de SUT _ook_ zelf
 * initialiseert.
 * <p>
 * De tests zijn verder hetzelfde.
 */
@ExtendWith(MockitoExtension.class)
public class ItemResourceTest
{
    private static final int ITEM_ID = 1;
    private static final int HTTP_OK = 200;
    private static final int HTTP_CREATED = 201;
    private static final int HTTP_NO_CONTENT = 204;

    @InjectMocks
    private ItemResource sut;

    @Mock
    private ItemService itemService;

    @Test
    public void getTextItemsTest()
    {
        // Arrange

        // Act
        var textItems = sut.getTextItems();

        // Assert
        assertEquals("bread, butter", textItems);
    }

    @Test
    public void getJsonItemsTest()
    {
        List<ItemDTO> items = new ArrayList<>();
        items.add(new ItemDTO(1, "Bread", new String[] {"Breakfast, Lunch"}, "Delicious!"));

        // Arrange
        when(itemService.getAll()).thenReturn(items);

        // Act
        var response = sut.getJsonItems();

        // Assert global
        assertEquals(HTTP_OK, response.getStatus());
        assertEquals(items, response.getEntity());

        // Assert specific
        assertTrue(response.getEntity() instanceof ArrayList);

        var itemsAsList = ((ArrayList<ItemDTO>) response.getEntity());
        assertEquals(1, itemsAsList.size());
        assertEquals("Bread", itemsAsList.get(0).getName());
    }
}
