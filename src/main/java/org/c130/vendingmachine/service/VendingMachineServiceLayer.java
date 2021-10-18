package org.c130.vendingmachine.service;

import org.c130.vendingmachine.dao.VendingMachinePersistenceException;
import org.c130.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;

public interface VendingMachineServiceLayer {

    List<Item> getAllItems() throws
            VendingMachinePersistenceException;

    Item getItem(String name) throws
            VendingMachinePersistenceException;

    int[] buyItem(String name, BigDecimal balance) throws
            VendingMachinePersistenceException, VendingMachineInsufficientFundsException, VendingMachineNoItemInventoryException, VendingMachineNoSuchItemException;

}
