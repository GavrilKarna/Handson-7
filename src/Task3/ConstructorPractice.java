package Task3;

import java.time.LocalDate;
import java.time.Period;

public class ConstructorPractice {
    public static void main(String[] args) {
        /*
         * PRAKTIK HANDS-ON: Constructors
         *
         * Instruksi: Lengkapi semua latihan di bawah ini untuk menguasai
         * constructors, constructor overloading, dan keyword this.
         */

        // ===== DEFAULT VS CUSTOM CONSTRUCTOR =====
        System.out.println("=== DEFAULT VS CUSTOM CONSTRUCTOR ===");

        // Latihan 1: Memahami default constructor
        // Buat class dengan dan tanpa custom constructor
        // Tunjukkan perbedaan penggunaan default constructor

        // Demonstrasikan penggunaan default constructor
        SimpleClass simple1 = new SimpleClass();
        SimpleClass simple2 = new SimpleClass("Contoh dengan parameter");
        System.out.println(simple1.message);
        System.out.println(simple2.message);

        // ===== CONSTRUCTOR OVERLOADING =====
        System.out.println("\n=== CONSTRUCTOR OVERLOADING ===");

        // Latihan 2: Multiple constructors untuk berbagai skenario
        // Implementasikan class Product dengan multiple constructors
        // Setiap constructor untuk kasus penggunaan yang berbeda

        // Buat object Product menggunakan berbagai constructors
        Product fullProduct = new Product("P001", "Laptop", "Laptop Gaming", 15000000, "Electronics", 10, "Asus Supplier");
        Product simpleProduct = new Product("P002", "Mouse", 150000);
        Product copiedProduct = new Product(fullProduct);
        Product defaultProduct = new Product();

        fullProduct.displayProductInfo();
        simpleProduct.displayProductInfo();
        copiedProduct.displayProductInfo();
        defaultProduct.displayProductInfo();

        // ===== KEYWORD THIS =====
        System.out.println("\n=== KEYWORD THIS ===");

        // Latihan 3: Penggunaan this dalam constructor dan methods
        // Demonstrasikan this untuk menghindari name collision
        // Gunakan this untuk memanggil constructor lain
        // Gunakan this untuk mereferensikan current object

        Product discountedProduct = new Product("P003", "Keyboard", "Mechanical Keyboard", 500000, "Accessories", 5, "Logitech");
        discountedProduct.applyDiscount(10);
        discountedProduct.displayProductInfo();
        discountedProduct.updateStock(3);
        System.out.println("Apakah tersedia? " + discountedProduct.isAvailable());

        // ===== CONSTRUCTOR CHAINING =====
        System.out.println("\n=== CONSTRUCTOR CHAINING ===");

        // Latihan 4: Constructor yang memanggil constructor lain
        // Implementasikan constructor chaining dengan this()
        // Tunjukkan keuntungan constructor chaining

        Employee emp1 = new Employee();
        Employee emp2 = new Employee("E001", "John", "Doe");
        Employee emp3 = new Employee("E002", "Jane", "Smith", "IT", "Developer", 8000000, LocalDate.of(2020, 5, 15));

        emp1.getEmployeeInfo();
        emp2.getEmployeeInfo();
        emp3.getEmployeeInfo();

        emp3.giveRaise(10);
        emp3.getEmployeeInfo();
        System.out.println("Lama bekerja: " + emp3.calculateYearsOfService() + " tahun");

        // ===== INITIALIZATION ORDER =====
        System.out.println("\n=== INITIALIZATION ORDER ===");

        // Latihan 5: Urutan inisialisasi object
        // Tunjukkan urutan eksekusi saat object dibuat
        // Instance variable initialization vs constructor

        // Demonstrasikan urutan inisialisasi
        InitializationDemo demo = new InitializationDemo();
    }
}

// ===== CLASS DEFINITIONS =====

// Implementasikan class SimpleClass (tanpa custom constructor)
class SimpleClass {
    // Hanya instance variables, tanpa constructor
    String message = "Default constructor aktif";

    public SimpleClass() {
        System.out.println("Default constructor dipanggil (SimpleClass)");
    }

    public SimpleClass(String message) {
        this.message = "Custom constructor: " + message;
    }
}

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

// Implementasikan class Employee dengan constructor chaining
class Employee {
    // Instance variables
    // employeeId, firstName, lastName, department, position, salary, hireDate
    String employeeId;
    String firstName;
    String lastName;
    String department;
    String position;
    double salary;
    LocalDate hireDate;

    // Constructor chaining examples
    // Buat multiple constructors yang saling memanggil dengan this()
    public Employee() {
        this("N/A", "Unknown", "Employee");
    }

    public Employee(String employeeId, String firstName, String lastName) {
        this(employeeId, firstName, lastName, "General", "Staff", 0.0, LocalDate.now());
    }

    public Employee(String employeeId, String firstName, String lastName, String department, String position, double salary, LocalDate hireDate) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.position = position;
        this.salary = salary;
        this.hireDate = hireDate;
    }

    // Methods
    // getFullName()
    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    // calculateYearsOfService()
    public int calculateYearsOfService() {
        return Period.between(this.hireDate, LocalDate.now()).getYears();
    }

    // getEmployeeInfo()
    public void getEmployeeInfo() {
        System.out.println("Employee: " + getFullName() + " | Dept: " + department + " | Pos: " + position + " | Gaji: " + salary);
    }

    // giveRaise(double percentage)
    public void giveRaise(double percentage) {
        this.salary += this.salary * (percentage / 100);
        System.out.println("Kenaikan gaji " + percentage + "% diberikan. Gaji baru: " + salary);
    }
}

// Implementasikan class InitializationDemo
class InitializationDemo {
    // Tunjukkan instance variable initialization
    int a = initializeA();
    int b = 20;

    // Tunjukkan urutan eksekusi constructor
    static {
        System.out.println("Static block dieksekusi terlebih dahulu (sekali saja).");
    }

    {
        System.out.println("Instance initializer block dieksekusi sebelum constructor.");
    }

    // Tambahkan System.out.println di berbagai tempat untuk tracking
    public InitializationDemo() {
        System.out.println("Constructor dipanggil setelah inisialisasi variabel instance.");
        System.out.println("Nilai a: " + a + ", Nilai b: " + b);
    }

    private int initializeA() {
        System.out.println("Instance variable 'a' sedang diinisialisasi.");
        return 10;
    }
}
