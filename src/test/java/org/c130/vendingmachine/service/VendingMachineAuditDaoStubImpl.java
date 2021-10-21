package org.c130.vendingmachine.service;

import org.c130.vendingmachine.dao.VendingMachineAuditDao;
import org.c130.vendingmachine.dao.VendingMachinePersistenceException;

public class VendingMachineAuditDaoStubImpl implements VendingMachineAuditDao {
    @Override
    public void writeAuditEntry(String entry) throws VendingMachinePersistenceException {

    }
}
