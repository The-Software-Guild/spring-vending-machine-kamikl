package org.c130.vendingmachine.service;

import org.c130.vendingmachine.dao.VendingMachineDao;
import org.c130.vendingmachine.dao.VendingMachinePersistenceException;
import org.c130.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VendingMachineDaoStubImpl implements VendingMachineDao {

    public Item onlyItem;

    public VendingMachineDaoStubImpl() {
        onlyItem = new Item("Coca Cola", 5, new BigDecimal("1.39"));
    }
    
    public VendingMachineDaoStubImpl(Item testItem) {this.onlyItem = testItem;}
    
    
    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        List<Item> itemList = new ArrayList<>();
        itemList.add(onlyItem);
        return itemList;
    }

    @Override
    public Item getItem(String name) throws VendingMachinePersistenceException {
        if(name.equals(onlyItem.getName())) {
            return onlyItem;
        }
        else {
            return null;
        }
    }

    @Override
    public void updateQty(Item item) throws VendingMachinePersistenceException {
        onlyItem.setQty(item.getQty());
    }

    @Override
    public void addItem(Item item) throws VendingMachinePersistenceException {

    }
}
