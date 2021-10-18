package org.c130.vendingmachine.dao;

public interface VendingMachineAuditDao {
    public void writeAuditEntry(String entry) throws VendingMachinePersistenceException;
}
