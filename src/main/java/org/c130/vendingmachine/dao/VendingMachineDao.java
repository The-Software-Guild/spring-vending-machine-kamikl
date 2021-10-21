package org.c130.vendingmachine.dao;

import org.c130.vendingmachine.dto.Item;

import java.util.List;

public interface VendingMachineDao {
    List<Item> getAllItems()
        throws VendingMachinePersistenceException;

    Item getItem(String name)
        throws VendingMachinePersistenceException;

    void updateQty(Item item)
        throws VendingMachinePersistenceException;

    void addItem(Item item)
        throws VendingMachinePersistenceException;

}
