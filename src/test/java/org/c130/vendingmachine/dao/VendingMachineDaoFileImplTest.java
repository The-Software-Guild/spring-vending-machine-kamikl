package org.c130.vendingmachine.dao;

import org.c130.vendingmachine.dto.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineDaoFileImplTest {

    VendingMachineDao testDao;

    public VendingMachineDaoFileImplTest(){}

    @BeforeEach
    public void setUp() throws Exception {
        String testFile = "testItems.txt";
        new FileWriter(testFile);
        testDao = new VendingMachineDaoFileImpl(testFile);
    }

    @Test
    public void testAddGetItem() throws Exception {
        Item item1 = new Item("Twix", 4, new BigDecimal("0.99"));
        testDao.addItem(item1);

        Item retrievedItem = testDao.getItem(item1.getName());

        assertEquals(item1.getName(), retrievedItem.getName(), "Item name should be same");
        assertEquals(item1.getCost(), retrievedItem.getCost(), "Item cost should be same");
        assertEquals(item1.getQty(), retrievedItem.getQty(), "Item quantity should be same");
    }

    @Test
    public void testAddGetAllItems() throws Exception {
        Item item1 = new Item("Twix", 4, new BigDecimal("0.99"));
        Item item2 = new Item("Coca Cola", 5, new BigDecimal("1.39"));
        testDao.addItem(item1);
        testDao.addItem(item2);

        List<Item> allItems = testDao.getAllItems();

        assertNotNull(allItems, "The list shouldn't be null");
        assertEquals(2, allItems.size(), "The list should contain two items");

        assertEquals(allItems.get(0), item1, "item 1 should be sane as first item in list");

        assertEquals(allItems.get(1), item2, "Item 2 should be same as second item in list");
    }

    @Test
    public void testUpdateQty() throws Exception {
        Item item1 = new Item("Twix", 4, new BigDecimal("0.99"));
        Item item2 = new Item("Twix", 9, new BigDecimal("0.99"));

        testDao.addItem(item1);
        testDao.updateQty(item2);

        Item retrievedItem = testDao.getItem("Twix");
        assertEquals(retrievedItem.getQty(), item2.getQty());
    }

}