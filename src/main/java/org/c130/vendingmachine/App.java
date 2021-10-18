package org.c130.vendingmachine;

import org.c130.vendingmachine.controller.VendingMachineController;
import org.c130.vendingmachine.dao.*;
import org.c130.vendingmachine.service.*;
import org.c130.vendingmachine.ui.*;

public class App {
    public static void main(String[] args) throws VendingMachinePersistenceException {
        UserIO myIo = new UserIOConsoleImpl();
        VendingMachineView myView = new VendingMachineView(myIo);
        VendingMachineDao myDao = new VendingMachineDaoFileImpl();
        VendingMachineAuditDao myAuditDao = new VendingMachineAuditDaoFileImpl();
        VendingMachineServiceLayer myService = new VendingMachineServiceLayerImpl(myDao, myAuditDao);
        VendingMachineController controller = new VendingMachineController(myService, myView);
        controller.run();
    }
}
