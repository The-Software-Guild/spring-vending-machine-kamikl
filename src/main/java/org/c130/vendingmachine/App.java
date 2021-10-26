package org.c130.vendingmachine;

import org.c130.vendingmachine.controller.VendingMachineController;
import org.c130.vendingmachine.dao.*;
import org.c130.vendingmachine.service.*;
import org.c130.vendingmachine.ui.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) throws VendingMachinePersistenceException {
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
            appContext.scan("org.c130.vendingmachine");
            appContext.refresh();

            VendingMachineController controller = appContext.getBean("vendingMachineController", VendingMachineController.class);
            controller.run();
    }
}
