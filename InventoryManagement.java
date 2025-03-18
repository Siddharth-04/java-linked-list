class Inventory {
    String itemName;
    int itemID;
    double itemPrice;
    int itemQuantity;
    Inventory next = null;

    public Inventory(String itemName, int itemID, double itemPrice, int itemQuantity) {
        this.itemName = itemName;
        this.itemID = itemID;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
    }
}

class InventoryList {
    Inventory head;

    public void addItemAtBeginning(String itemName, int itemID, double itemPrice, int itemQuantity) {
        Inventory temp = new Inventory(itemName, itemID, itemPrice, itemQuantity);
        temp.next = head;
        head = temp;
    }

    public void addItemAtEnd(String itemName, int itemID, double itemPrice, int itemQuantity) {
        Inventory temp = new Inventory(itemName, itemID, itemPrice, itemQuantity);
        if (head == null) {
            head = temp;
            return;
        }
        Inventory node = head;
        while (node.next != null) {
            node = node.next;
        }
        node.next = temp;
    }

    public void addItemAtSpecific(String itemName, int itemID, double itemPrice, int itemQuantity, int position) {
        Inventory temp = new Inventory(itemName, itemID, itemPrice, itemQuantity);
        if (position <= 1 || head == null) {
            addItemAtBeginning(itemName, itemID, itemPrice, itemQuantity);
            return;
        }
        Inventory node = head;
        for (int i = 1; i < position - 1 && node.next != null; i++) {
            node = node.next;
        }
        temp.next = node.next;
        node.next = temp;
    }

    public void removeItem(int itemID) {
        if (head == null) return;
        if (head.itemID == itemID) {
            head = head.next;
            return;
        }
        Inventory temp = head;
        while (temp.next != null && temp.next.itemID != itemID) {
            temp = temp.next;
        }
        if (temp.next != null) {
            temp.next = temp.next.next;
        }
    }

    public void updateQuantityOfItem(int itemID, int itemQuantity) {
        Inventory temp = head;
        while (temp != null) {
            if (temp.itemID == itemID) {
                temp.itemQuantity = itemQuantity;
                return;
            }
            temp = temp.next;
        }
    }

    public void searchItem(String itemName) {
        Inventory temp = head;
        while (temp != null) {
            if (temp.itemName.equals(itemName)) {
                System.out.println("ID: " + temp.itemID);
                System.out.println("Name: " + temp.itemName);
                System.out.println("Price: " + temp.itemPrice);
                System.out.println("Quantity: " + temp.itemQuantity);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item not found.");
    }

    public void totalValue() {
        Inventory temp = head;
        double total = 0;
        while (temp != null) {
            total += temp.itemPrice * temp.itemQuantity;
            temp = temp.next;
        }
        System.out.println("Total Value: " + total);
    }

    public void sort() {
        if (head == null || head.next == null) return;
        for (Inventory node = head; node != null; node = node.next) {
            for (Inventory node2 = head; node2.next != null; node2 = node2.next) {
                if (node2.itemPrice > node2.next.itemPrice) {
                    swap(node2, node2.next);
                }
            }
        }
    }

    private void swap(Inventory itemOne, Inventory itemTwo) {
        String tempName = itemOne.itemName;
        int tempID = itemOne.itemID;
        double tempPrice = itemOne.itemPrice;
        int tempQuantity = itemOne.itemQuantity;

        itemOne.itemName = itemTwo.itemName;
        itemOne.itemID = itemTwo.itemID;
        itemOne.itemPrice = itemTwo.itemPrice;
        itemOne.itemQuantity = itemTwo.itemQuantity;

        itemTwo.itemName = tempName;
        itemTwo.itemID = tempID;
        itemTwo.itemPrice = tempPrice;
        itemTwo.itemQuantity = tempQuantity;
    }

    public void displayInventory() {
        Inventory temp = head;
        while (temp != null) {
            System.out.println(temp.itemName + " " + temp.itemID + " " + temp.itemPrice + " " + temp.itemQuantity);
            temp = temp.next;
        }
    }
}

public class InventoryManagement {
    public static void main(String[] args) {
        InventoryList inventory = new InventoryList();

        inventory.addItemAtBeginning("Laptop", 101, 5000.0, 3);
        inventory.addItemAtBeginning("Mouse", 102, 1000.0, 4);
        inventory.addItemAtBeginning("Bag", 104, 1500.0, 7);
        inventory.addItemAtSpecific("Keyboard", 103, 7.0, 1, 2);
        inventory.updateQuantityOfItem(102, 15);
        inventory.removeItem(103);
        inventory.totalValue();
        inventory.searchItem("Bag");
        inventory.sort();
        inventory.displayInventory();
    }
}
//Total Value: 40500.0
//ID: 104
//Name: Bag
//Price: 1500.0
//Quantity: 7
//Mouse 102 1000.0 15
//Bag 104 1500.0 7
//Laptop 101 5000.0 3