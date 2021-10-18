package org.c130.vendingmachine.service;

public class VendingMachineNoItemInventoryException extends Throwable {
    public VendingMachineNoItemInventoryException(String message) {
        super(message);
    }

    public VendingMachineNoItemInventoryException(String message, Throwable cause) {
        super(message,cause);
    }
}
