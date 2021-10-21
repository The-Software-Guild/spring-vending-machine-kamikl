package org.c130.vendingmachine.dao;

import org.c130.vendingmachine.dto.Item;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;


public class VendingMachineDaoFileImpl implements VendingMachineDao{

    public final String ITEM_FILE;
    public static final String DELIMITER = "::";

    private Map<String, Item> items = new HashMap<>();

    public VendingMachineDaoFileImpl() {
        ITEM_FILE = "items.txt";
    }

    public VendingMachineDaoFileImpl(String itemsTextFile) {
        ITEM_FILE = itemsTextFile;
    }

    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        loadRoster();
        return new ArrayList(items.values());
    }

    @Override
    public Item getItem(String name) throws VendingMachinePersistenceException {
        loadRoster();
        return items.get(name);
    }

    @Override
    public void updateQty(Item item) throws VendingMachinePersistenceException {
        loadRoster();
        items.replace(item.getName(), item);
        writeRoster();
    }

    @Override
    public void addItem(Item item) throws VendingMachinePersistenceException {
        loadRoster();
        items.put(item.getName(), item);
        writeRoster();
    }


    private Item unmarshallItem(String itemAsText){
        String[] itemTokens = itemAsText.split(DELIMITER);
        Item itemFromFile = new Item(itemTokens[0],Integer.parseInt(itemTokens[1]),new BigDecimal(itemTokens[2]));
        return itemFromFile;
    }

    private void loadRoster() throws VendingMachinePersistenceException {
        Scanner scanner;
        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(ITEM_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException(
                    "Could not load roster data into memory.", e);
        }
        String currentLine;
        Item currentItem;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentItem = unmarshallItem(currentLine);
            items.put(currentItem.getName(), currentItem);
        }
        scanner.close();
    }

    private String marshallItem(Item item){
        String itemAsText = item.getName() + DELIMITER + item.getQty() + DELIMITER + item.getCost();
        return itemAsText;
    }

    private void writeRoster() throws VendingMachinePersistenceException {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(ITEM_FILE));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException(
                    "Could not save item data.", e);
        }
        String itemAsText;
        List<Item> itemList = getAllItems();
        for (Item currentItem : itemList) {
            itemAsText = marshallItem(currentItem);
            out.println(itemAsText);
            out.flush();
        }
        out.close();
    }
}
