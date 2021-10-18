package org.c130.vendingmachine.service;

public class VendingMachineNoSuchItemException extends Throwable {
    public VendingMachineNoSuchItemException(String message) {
        super(message);
    }

    public VendingMachineNoSuchItemException(String message, Throwable cause) {
        super(message,cause);
    }
}
