import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

class Product {
    String name;
    double price;
    int quantity;

    Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}

class Store {
    private List<Product> inventory;

    Store() {
        inventory = new ArrayList<>();
    }

    void addProduct(String name, double price, int quantity) {
        inventory.add(new Product(name, price, quantity));
    }

    Product getProduct(String productName) {
        for (Product product : inventory) {
            if (product.name.equalsIgnoreCase(productName)) {
                return product;
            }
        }
        return null;
    }

    boolean purchaseProduct(String productName, int quantity) {
        Product product = getProduct(productName);
        if (product != null && product.quantity >= quantity) {
            product.quantity -= quantity;
            return true;
        }
        return false;
    }
}

public class StoreManagementAndBillingGUI extends Frame implements ActionListener {
    private Store store;
    private TextArea textArea;
    private TextField productNameField, quantityField;
    private Button purchaseButton, exitButton;

    public StoreManagementAndBillingGUI() {
        store = new Store();
        store.addProduct("pen", 800, 10);
        store.addProduct("book", 1200, 5);
        store.addProduct("bag", 1800, 8);

        setTitle("Store Management and Billing");
        setSize(400, 400);
        setLayout(new BorderLayout());


        textArea = new TextArea();
        textArea.setEditable(false);
        add(textArea, BorderLayout.CENTER);

        Panel inputPanel = new Panel();
        inputPanel.setLayout(new FlowLayout());

        Label productNameLabel = new Label("Enter product name:");
        productNameField = new TextField(15);
        inputPanel.add(productNameLabel);
        inputPanel.add(productNameField);

        Label quantityLabel = new Label("Enter quantity:");
        quantityField = new TextField(5);
        inputPanel.add(quantityLabel);
        inputPanel.add(quantityField);

        purchaseButton = new Button("Purchase");
        exitButton = new Button("Exit");

        purchaseButton.addActionListener(this);
        exitButton.addActionListener(this);

        inputPanel.add(purchaseButton);
        inputPanel.add(exitButton);

        add(inputPanel, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }
    

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == purchaseButton) {
            String productName = productNameField.getText();
            String quantityStr = quantityField.getText();

            if (!productName.isEmpty() && !quantityStr.isEmpty()) {
                int quantity = Integer.parseInt(quantityStr);

                Product product = store.getProduct(productName);

                if (product != null) {
                    if (store.purchaseProduct(productName, quantity)) {
                        double totalPrice = product.price * quantity;
                        double discountedTotal = applyDiscount(totalPrice);
                        textArea.append("Selected Product: " + product.name + "\n");
                        textArea.append("Price: " + product.price + "\n");
                        textArea.append("Available Quantity: " + product.quantity + "\n");
                        textArea.append("Total Price: " + totalPrice + "\n");
                        textArea.append("Discounted Total: " + discountedTotal + "\n");
                    } else {
                        textArea.append("Insufficient quantity available for " + productName + "\n");
                    }
                } else {
                    textArea.append("Product not found. Please choose from the available products.\n");
                }
            }
        } else if (ae.getSource() == exitButton) {
            System.exit(0);
        }
    }

    private double applyDiscount(double price) {
        if (price >= 1700) {
            return price * 0.90; // 10% discount
        } else if (price >= 1000) {
            return price * 0.95; // 5% discount
        } else {
            return price;
        }
    }

    public static void main(String[] args) {
        StoreManagementAndBillingGUI app = new StoreManagementAndBillingGUI();
        app.setVisible(true);
    }
}