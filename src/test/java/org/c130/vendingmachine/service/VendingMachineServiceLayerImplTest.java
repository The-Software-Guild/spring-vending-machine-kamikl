package org.c130.vendingmachine.service;

import org.c130.vendingmachine.dao.VendingMachineAuditDao;
import org.c130.vendingmachine.dao.VendingMachineDao;
import org.c130.vendingmachine.dao.VendingMachinePersistenceException;
import org.c130.vendingmachine.dto.Item;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class VendingMachineServiceLayerImplTest {
    private VendingMachineServiceLayer service;

    public VendingMachineServiceLayerImplTest() {
        VendingMachineDao dao = new VendingMachineDaoStubImpl();
        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoStubImpl();

        service = new VendingMachineServiceLayerImpl(dao, auditDao);
    }

    @Test
    public void testGetItem () throws VendingMachinePersistenceException {
        Item cloneItem = new Item("Coca Cola" , 5, new BigDecimal("1.39"));

        Item testItem = service.getItem("Coca Cola");

        assertEquals(cloneItem, testItem);
    }

    @Test
    public void testGetAllItems() throws VendingMachinePersistenceException {
        Item cloneItem = new Item("Coca Cola" , 5, new BigDecimal("1.39"));

        assertEquals(1, service.getAllItems().size(), "Should only have one item");
        assertTrue(service.getAllItems().contains(cloneItem), "Coca cola item should be inside");
    }

    @Test
    public void testBuyValidItem() throws VendingMachineInsufficientFundsException, VendingMachinePersistenceException, VendingMachineNoItemInventoryException, VendingMachineNoSuchItemException {
        service.buyItem("Coca Cola", new BigDecimal("1.39"));
        Item purchasedItem =  service.getItem("Coca Cola");
        assertEquals(4, purchasedItem.getQty());
    }

    @Test
    public void testBuyInsufficientFunds() throws VendingMachineInsufficientFundsException, VendingMachinePersistenceException, VendingMachineNoItemInventoryException, VendingMachineNoSuchItemException {
        try {
            service.buyItem("Coca Cola", new BigDecimal("1.00"));
            fail("Excepted InsufficientFundsException was not thrown");
        } catch (VendingMachineNoSuchItemException | VendingMachinePersistenceException | VendingMachineNoItemInventoryException e) {
            fail("Incorrect exception thrown");
        } catch(VendingMachineInsufficientFundsException e) {
            return;
        }
    }

    @Test
    public void testBuyInsufficientQty() throws VendingMachineInsufficientFundsException, VendingMachinePersistenceException, VendingMachineNoItemInventoryException, VendingMachineNoSuchItemException {
        service.buyItem("Coca Cola", new BigDecimal("1.39"));
        service.buyItem("Coca Cola", new BigDecimal("1.39"));
        service.buyItem("Coca Cola", new BigDecimal("1.39"));
        service.buyItem("Coca Cola", new BigDecimal("1.39"));
        service.buyItem("Coca Cola", new BigDecimal("1.39"));

        try {
            service.buyItem("Coca Cola", new BigDecimal("1.39"));
            fail("Excepted exception was not thrown");
        } catch (VendingMachineNoSuchItemException | VendingMachinePersistenceException | VendingMachineInsufficientFundsException e) {
            fail("Incorrect exception thrown");
        } catch(VendingMachineNoItemInventoryException e) {
            return;
        }
    }

    public void testBuyInvalidItem() {
        try {
            service.buyItem("Pepsi", new BigDecimal("1.00"));
            fail("Excepted exception was not thrown");
        } catch (VendingMachineInsufficientFundsException | VendingMachinePersistenceException | VendingMachineNoItemInventoryException e) {
            fail("Incorrect exception thrown");
        } catch(VendingMachineNoSuchItemException e) {
            return;
        }
    }

}