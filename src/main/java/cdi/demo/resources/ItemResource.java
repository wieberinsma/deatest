package cdi.demo.resources;

import cdi.demo.annotations.DiyGET;
import cdi.demo.annotations.DiyInject;
import cdi.demo.annotations.DiyPath;
import cdi.demo.services.ItemService;
import cdi.demo.services.dto.ItemDTO;

import java.util.List;

@DiyPath("/items")
public class ItemResource
{
    private ItemService itemService;

    @DiyInject
    public void setItemService(ItemService itemService)
    {
        this.itemService = itemService;
    }

    @DiyGET
    public List<ItemDTO> getJsonItems()
    {
        return itemService.getAll();
    }
}
