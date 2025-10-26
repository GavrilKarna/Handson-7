package Task3;

// Implementasikan class Product dengan constructor overloading
class Product {
    // Instance variables
    // productId, name, description, price, category, inStock, supplier
    String productId;
    String name;
    String description;
    double price;
    String category;
    int inStock;
    String supplier;

    // Constructor 1: Full parameters
    // public Product(String productId, String name, String description, double price, String category, int inStock, String supplier)
    public Product(String productId, String name, String description, double price, String category, int inStock, String supplier) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.inStock = inStock;
        this.supplier = supplier;
    }

    // Constructor 2: Essential parameters only
    // public Product(String productId, String name, double price)
    public Product(String productId, String name, double price) {
        this(productId, name, "No description", price, "Uncategorized", 0, "Unknown");
    }

    // Constructor 3: Copy constructor
    // public Product(Product other)
    public Product(Product other) {
        this(other.productId, other.name, other.description, other.price, other.category, other.inStock, other.supplier);
    }

    // Constructor 4: Default constructor with default values
    // public Product()
    public Product() {
        this("N/A", "Unnamed", "No description", 0.0, "Unknown", 0, "None");
    }

    // Methods
    // displayProductInfo()
    public void displayProductInfo() {
        System.out.println("ID: " + productId + ", Name: " + name + ", Price: " + price +
                ", Category: " + category + ", Stock: " + inStock + ", Supplier: " + supplier);
    }

    // updateStock(int quantity)
    public void updateStock(int quantity) {
        this.inStock += quantity;
        System.out.println("Stock diperbarui. Stok saat ini: " + inStock);
    }

    // applyDiscount(double percentage)
    public void applyDiscount(double percentage) {
        this.price -= this.price * (percentage / 100);
        System.out.println("Diskon " + percentage + "% diterapkan. Harga baru: " + price);
    }

    // isAvailable()
    public boolean isAvailable() {
        return this.inStock > 0;
    }
}
