package application;

import entities.*;
import framework.*;
import services.*;
import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ProductService productService = new ProductService();
    private static final OrderService orderService = new OrderService();
    private static final CustomerService customerService =
            new CustomerService(productService, orderService);
    private static final AdminService adminService = new AdminService(orderService);
    private static final ShopFactory shopFactory = new GSShopFactory();

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n===== Online Shopping System =====");
            System.out.println("1. Admin Menu");
            System.out.println("2. Customer Menu");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = readInt();
            switch (choice) {
                case 1 -> adminMenu();
                case 2 -> customerMenu();
                case 3 -> exit = true;
                default -> System.out.println("Invalid choice!");
            }
        }
        scanner.close();
    }

    // ================== ADMIN MENU ==================
    private static void adminMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n----- Admin Menu -----");
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. View All Products");
            System.out.println("4. Update Order Status");
            System.out.println("5. View All Orders");
            System.out.println("6. Return to Main Menu");
            System.out.print("Choose an option: ");
            int choice = readInt();
            switch (choice) {
                case 1 -> addProduct();
                case 2 -> removeProduct();
                case 3 -> viewProducts();
                case 4 -> updateOrderStatus();
                case 5 -> viewAllOrders();
                case 6 -> back = true;
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void addProduct() {
        System.out.print("Enter Product ID: ");
        int productId = readInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Product Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Price: ");
        double price = readDouble();
        System.out.print("Enter Stock Quantity: ");
        int stock = readInt();
        productService.addProduct(new Product(productId, name, price, stock));
        System.out.println("Product added successfully!");
    }

    private static void removeProduct() {
        System.out.print("Enter Product ID to remove: ");
        int productId = readInt();
        productService.removeProduct(productId);
        System.out.println("Product removed successfully!");
    }

    private static void viewProducts() {
        List<Product> products = productService.getProducts();
        if (products.isEmpty()) {
            System.out.println("No products found!");
            return;
        }
        System.out.println("\n===== Products =====");
        products.forEach(System.out::println);
    }

    private static void updateOrderStatus() {
        System.out.print("Enter Order ID: ");
        int orderId = readInt();
        System.out.print("Enter new status (Completed/Delivered/Cancelled): ");
        String status = scanner.next();
        adminService.updateOrderStatus(orderId, status);
    }

    private static void viewAllOrders() {
        List<Order> orders = orderService.getOrder();
        if (orders.isEmpty()) {
            System.out.println("No orders found!");
            return;
        }
        System.out.println("\n===== All Orders =====");
        orders.forEach(System.out::println);
    }

    // ================== CUSTOMER MENU ==================
    private static void customerMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n----- Customer Menu -----");
            System.out.println("1. Create New Customer");
            System.out.println("2. View All Customers");
            System.out.println("3. Place Order");
            System.out.println("4. View My Orders");
            System.out.println("5. View Products");
            System.out.println("6. Return to Main Menu");
            System.out.print("Choose an option: ");
            int choice = readInt();
            switch (choice) {
                case 1 -> createCustomer();
                case 2 -> viewAllCustomers();
                case 3 -> placeOrder();
                case 4 -> viewCustomerOrders();
                case 5 -> viewProducts();
                case 6 -> back = true;
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void createCustomer() {
        System.out.print("Enter User ID: ");
        int userId = readInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();

        // Account type selection
        System.out.print("Choose account type (1. Prime, 2. Normal): ");
        int accType = readInt();
        ShopAcc account;
        if (accType == 1) {
            account = shopFactory.getNewprimeAccount(userId, username);
        } else {
            account = shopFactory.getNewNormalAccount(userId, username, 50.0f);
        }

        Customer customer = new Customer(userId, username, email, address, account);
        customerService.addCustomer(customer);
        System.out.println("Customer created successfully!");
    }

    private static void viewAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("No customers found!");
            return;
        }
        System.out.println("\n===== Customers =====");
        customers.forEach(c -> System.out.println(
                "User ID: " + c.getUserId() +
                        ", Name: " + c.getUsername() +
                        ", Address: " + c.getAddress()
        ));
    }

    private static void placeOrder() {
        System.out.print("Enter Customer ID: ");
        int customerId = readInt();
        Customer customer = customerService.getAllCustomers().stream()
                .filter(c -> c.getUserId() == customerId)
                .findFirst()
                .orElse(null);

        if (customer == null) {
            System.out.println("Customer not found!");
            return;
        }

        // Add products to cart
        boolean addingItems = true;
        while (addingItems) {
            System.out.print("Enter Product ID (or -1 to finish): ");
            int productId = readInt();
            if (productId == -1) break;

            Product product = productService.getProductById(productId);
            if (product == null) {
                System.out.println("Product not found!");
                continue;
            }

            System.out.print("Enter Quantity: ");
            int quantity = readInt();
            customer.getShoppingCart().addItem(product, quantity);
            System.out.println("Added to cart: " + product.getName() + " x" + quantity);
        }

        // Place order
        customerService.placeOrder(customer);
    }

    private static void viewCustomerOrders() {
        System.out.print("Enter Customer ID: ");
        int customerId = readInt();
        Customer customer = customerService.getAllCustomers().stream()
                .filter(c -> c.getUserId() == customerId)
                .findFirst()
                .orElse(null);

        if (customer == null) {
            System.out.println("Customer not found!");
            return;
        }

        List<Order> orders = customer.getOrder();
        if (orders.isEmpty()) {
            System.out.println("No orders found for this customer!");
            return;
        }
        System.out.println("\n===== Your Orders =====");
        orders.forEach(System.out::println);
    }

    // ================== HELPER METHODS ==================
    private static int readInt() {
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print("Invalid input! Enter an integer: ");
        }
        return scanner.nextInt();
    }

    private static double readDouble() {
        while (!scanner.hasNextDouble()) {
            scanner.next();
            System.out.print("Invalid input! Enter a number: ");
        }
        return scanner.nextDouble();
    }
}