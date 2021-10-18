package org.c130.vendingmachine.controller;

import org.c130.vendingmachine.dao.VendingMachinePersistenceException;
import org.c130.vendingmachine.dto.Item;
import org.c130.vendingmachine.service.VendingMachineInsufficientFundsException;
import org.c130.vendingmachine.service.VendingMachineNoItemInventoryException;
import org.c130.vendingmachine.service.VendingMachineNoSuchItemException;
import org.c130.vendingmachine.service.VendingMachineServiceLayer;
import org.c130.vendingmachine.ui.VendingMachineView;

import java.math.BigDecimal;
import java.util.List;

public class VendingMachineController {

    private VendingMachineView view;
    private VendingMachineServiceLayer service;

    public VendingMachineController(VendingMachineServiceLayer service, VendingMachineView view) {
        this.service = service;
        this.view = view;
    }

    public void run() throws VendingMachinePersistenceException {
        Boolean keepGoing = true;

        do {
            BigDecimal balance;
            List<Item> itemList = service.getAllItems();
            view.printItems(itemList);

            if (view.promptContinue().equals("n")) {
                return;
            }
            balance = view.takeMoney();
            do {
                view.viewBalance(balance);
                if (view.promptContinue().equals("n")) {
                    return;
                }
                if (purchaseItem(balance)) {
                    keepGoing = false;
                }
            } while (keepGoing);
        }while(true);
    }

    public Boolean purchaseItem(BigDecimal balance) throws VendingMachinePersistenceException {
        String itemName = view.takeSelection();
        int[] change = new int[0];
        try {
            change = service.buyItem(itemName, balance);
        }
        catch (VendingMachineNoItemInventoryException | VendingMachineInsufficientFundsException | VendingMachineNoSuchItemException e) {
            view.displayErrorMessage(e.getMessage());
            return false;
        }
        view.displayChange(change);
        return true;
    }




}
