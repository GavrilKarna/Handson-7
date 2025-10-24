package Task3;

import java.time.LocalDate;

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
        SimpleClass obj = new SimpleClass();
        System.out.println("Objek SimpleClass dibuat menggunakan default constructor.");

        // ===== CONSTRUCTOR OVERLOADING =====
        System.out.println("\n=== CONSTRUCTOR OVERLOADING ===");

        // Latihan 2: Multiple constructors untuk berbagai skenario
        // Implementasikan class Product dengan multiple constructors
        // Setiap constructor untuk kasus penggunaan yang berbeda

        // Buat object Product menggunakan berbagai constructors
        Product p1 = new Product("P001", "Laptop", "Laptop Gaming", 15000.0, "Elektronik", 10, "SupplierA");
        Product p2 = new Product("P002", "Mouse", 500.0);
        Product p3 = new Product(p1);
        Product p4 = new Product();
        p1.displayProductInfo();
        p2.displayProductInfo();
        p3.displayProductInfo();
        p4.displayProductInfo();

        // ===== KEYWORD THIS =====
        System.out.println("\n=== KEYWORD THIS ===");

        // Latihan 3: Penggunaan this dalam constructor dan methods
        // Demonstrasikan this untuk menghindari name collision
        // Gunakan this untuk memanggil constructor lain
        // Gunakan this untuk mereferensikan current object

        // Demonstrasikan berbagai penggunaan this
        p1.updateStock(5);
        System.out.println("Stok setelah update: " + p1.inStock);
        p1.applyDiscount(10.0);
        System.out.println("Harga setelah diskon: " + p1.price);
        System.out.println("Apakah tersedia? " + p1.isAvailable());

        // ===== CONSTRUCTOR CHAINING =====
        System.out.println("\n=== CONSTRUCTOR CHAINING ===");

        // Latihan 4: Constructor yang memanggil constructor lain
        // Implementasikan constructor chaining dengan this()
        // Tunjukkan keuntungan constructor chaining

        // Implementasikan constructor chaining
        Employee emp1 = new Employee("E001", "John", "Doe", "IT", "Developer", 5000.0, LocalDate.of(2020, 1, 1));
        Employee emp2 = new Employee("E002", "Jane", "Smith", "HR", "Manager", 6000.0);
        Employee emp3 = new Employee("E003", "Bob", "Johnson");
        emp1.getEmployeeInfo();
        emp2.getEmployeeInfo();
        emp3.getEmployeeInfo();

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
    private String nama;
    private int umur;
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
        this(productId, name, "", price, "", 0, "");
    }

    // Constructor 3: Copy constructor
    // public Product(Product other)
    public Product(Product other) {
        this(other.productId, other.name, other.description, other.price, other.category, other.inStock, other.supplier);
    }

    // Constructor 4: Default constructor with default values
    // public Product()
    public Product() {
        this("", "", "", 0.0, "", 0, "");
    }

    // Methods
    // displayProductInfo()
    public void displayProductInfo() {
        System.out.println("ID: " + productId + ", Nama: " + name + ", Harga: " + price + ", Stok: " + inStock);
    }

    // updateStock(int quantity)
    public void updateStock(int quantity) {
        this.inStock += quantity;
    }

    // applyDiscount(double percentage)
    public void applyDiscount(double percentage) {
        this.price -= this.price * percentage / 100;
    }

    // isAvailable()
    public boolean isAvailable() {
        return inStock > 0;
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
    public Employee(String employeeId, String firstName, String lastName, String department, String position, double salary, LocalDate hireDate) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.position = position;
        this.salary = salary;
        this.hireDate = hireDate;
    }

    public Employee(String employeeId, String firstName, String lastName, String department, String position, double salary) {
        this(employeeId, firstName, lastName, department, position, salary, LocalDate.now());
    }

    public Employee(String employeeId, String firstName, String lastName) {
        this(employeeId, firstName, lastName, "", "", 0.0);
    }

    // Methods
    // getFullName()
    public String getFullName() {
        return firstName + " " + lastName;
    }

    // calculateYearsOfService()
    public int calculateYearsOfService() {
        return LocalDate.now().getYear() - hireDate.getYear();
    }

    // getEmployeeInfo()
    public void getEmployeeInfo() {
        System.out.println("ID: " + employeeId + ", Nama: " + getFullName() + ", Departemen: " + department + ", Lama Kerja: " + calculateYearsOfService() + " tahun");
    }

    // giveRaise(double percentage)
    public void giveRaise(double percentage) {
        this.salary += this.salary * percentage / 100;
    }
}

// Implementasikan class InitializationDemo
class InitializationDemo {
    // Tunjukkan instance variable initialization
    private int a = 10;
    private String b;

    {
        System.out.println("Blok inisialisasi instance dieksekusi");
        b = "Diinisialisasi dalam blok";
    }

    // Tunjukkan urutan eksekusi constructor
    public InitializationDemo() {
        System.out.println("Constructor dipanggil");
        System.out.println("Nilai a: " + a + ", Nilai b: " + b);
    }

    // Tambahkan System.out.println di berbagai tempat untuk tracking
}
