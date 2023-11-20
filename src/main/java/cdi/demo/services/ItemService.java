package cdi.demo.services;

import cdi.demo.services.dto.ItemDTO;

import java.util.List;

public interface ItemService
{
    List<ItemDTO> getAll();

}
