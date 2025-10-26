package Task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InstanceMembersPractice {
    public static void main(String[] args) {
        /*
         * PRAKTIK HANDS-ON: Instance Variables & Methods
         *
         * Instruksi: Lengkapi semua latihan di bawah ini untuk menguasai
         * instance variables, instance methods, dan perbedaannya dengan static.
         */

        // ===== INSTANCE VARIABLES BASICS =====
        System.out.println("=== INSTANCE VARIABLES BASICS ===");

        // Latihan 1: Memahami instance variables
        // Buat beberapa object dari class BankAccount
        // Tunjukkan bahwa setiap object memiliki data sendiri

        // Buat 3 object BankAccount dengan data berbeda
        BankAccount acc1 = new BankAccount("A001", "Andi", 5000, "Savings", true);
        BankAccount acc2 = new BankAccount("A002", "Budi", 10000, "Checking", true);
        BankAccount acc3 = new BankAccount("A003", "Citra", 2000, "Savings", false);

        // Tampilkan saldo masing-masing account
        acc1.displayBalance();
        acc2.displayBalance();
        acc3.displayBalance();

        // Lakukan transaksi pada masing-masing account
        acc1.deposit(2000);
        acc2.withdraw(3000);
        acc3.activateAccount();
        acc3.deposit(1000);

        // Tunjukkan bahwa perubahan pada satu object tidak mempengaruhi yang lain
        System.out.println("\nSetelah transaksi:");
        acc1.displayBalance();
        acc2.displayBalance();
        acc3.displayBalance();

        // ===== INSTANCE METHODS ADVANCED =====
        // Latihan 2: Instance methods yang bekerja dengan instance variables
        // Implementasikan berbagai jenis instance methods
        // Methods yang mengubah state object
        // Methods yang mengembalikan nilai berdasarkan state
        // Methods yang berinteraksi dengan object lain

        // Demonstrasikan berbagai jenis instance methods
        System.out.println("\n=== INSTANCE METHODS ADVANCED ===");
        System.out.println("Bunga tahunan untuk akun Andi (2%): " + acc1.calculateInterest(0.02));
        System.out.println("Status akun Budi: " + acc2.getAccountStatus());

        // ===== METHOD INTERACTION =====
        // Latihan 3: Methods yang memanggil methods lain
        // Buat methods yang memanggil methods lain dalam class yang sama
        // Demonstrasikan method chaining

        // Implementasikan method chaining dan interaction
        System.out.println("\n=== METHOD INTERACTION ===");
        acc1.deposit(500).withdraw(300).displayBalance();

        // ===== OBJECT COLLABORATION =====
        // Latihan 4: Object yang berinteraksi dengan object lain
        // Implementasikan transfer uang antar account
        // Buat history transaksi

        // Implementasikan object collaboration
        System.out.println("\n=== OBJECT COLLABORATION ===");
        acc2.transfer(acc1, 1000);

        acc1.printStatement();
        acc2.printStatement();

        Customer cust = new Customer("CUST01", "Dewi", "08123456789", "dewi@email.com");
        cust.addAccount(acc1);
        cust.addAccount(acc3);
        cust.showAllAccounts();
    }
}
