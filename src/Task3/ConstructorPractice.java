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

        // Demonstrasikan berbagai penggunaan this
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

        // Implementasikan constructor chaining
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
