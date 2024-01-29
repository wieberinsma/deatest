package cdi.demo.resources;

import cdi.demo.annotations.DiyTest;
import cdi.demo.annotations.DiyInject;
import cdi.demo.annotations.DiyClass;
import cdi.demo.services.ItemService;
import cdi.demo.services.dto.ItemDTO;

import java.util.List;

@DiyClass("/items")
public class ItemResource
{
    private ItemService itemService;

    @DiyInject
    public void setItemService(ItemService itemService)
    {
        this.itemService = itemService;
    }

    @DiyTest
    public List<ItemDTO> getJsonItems()
    {
        return itemService.getAll();
    }
}
