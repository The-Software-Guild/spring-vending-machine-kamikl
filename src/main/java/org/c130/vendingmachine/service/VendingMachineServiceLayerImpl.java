package org.c130.vendingmachine.service;

import org.c130.vendingmachine.dao.VendingMachineAuditDao;
import org.c130.vendingmachine.dao.VendingMachineDao;
import org.c130.vendingmachine.dao.VendingMachinePersistenceException;
import org.c130.vendingmachine.dto.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer{

    private VendingMachineDao dao;
    private VendingMachineAuditDao auditDao;

    @Autowired
    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        return dao.getAllItems();
    }

    @Override
    public Item getItem(String name) throws VendingMachinePersistenceException {
        return dao.getItem(name);
    }

    @Override
    public int[] buyItem(String name, BigDecimal balance)
            throws VendingMachinePersistenceException, VendingMachineInsufficientFundsException,
            VendingMachineNoItemInventoryException, VendingMachineNoSuchItemException {
        Item item = dao.getItem(name);
        if (item==null) {
            throw new VendingMachineNoSuchItemException("No such item in inventory");
        }
        if(item.getQty() > 0) {
            if (balance.compareTo(item.getCost()) >= 0) {
                item.setQty(item.getQty() - 1);
                dao.updateQty(item);

                BigDecimal change = balance.subtract(item.getCost());
                change = change.movePointRight(2);
                int intChange = change.intValue();
                Change coinChange = new Change(intChange);
                auditDao.writeAuditEntry("Item " + item.getName() + " PURCHASED.");
                return coinChange.calculateChange();
            }
            else {
                throw new VendingMachineInsufficientFundsException("Could not purchase item, insufficient funds entered.");
            }
        }
        else {
            throw new VendingMachineNoItemInventoryException("Could not purchase item, insufficient quantity.");
        }
    }

}
