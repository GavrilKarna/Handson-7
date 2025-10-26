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
        System.out.println("\n=== INSTANCE METHODS ADVANCED ===");
        // Latihan 2: Instance methods yang bekerja dengan instance variables
        // Implementasikan berbagai jenis instance methods
        // Methods yang mengubah state object
        // Methods yang mengembalikan nilai berdasarkan state
        // Methods yang berinteraksi dengan object lain

        // Demonstrasikan berbagai jenis instance methods
        System.out.println("Bunga tahunan untuk akun Andi (2%): " + acc1.calculateInterest(0.02));
        System.out.println("Status akun Budi: " + acc2.getAccountStatus());

        // ===== METHOD INTERACTION =====
        System.out.println("\n=== METHOD INTERACTION ===");
        // Latihan 3: Methods yang memanggil methods lain
        // Buat methods yang memanggil methods lain dalam class yang sama
        // Demonstrasikan method chaining
        acc1.deposit(500).withdraw(300).displayBalance();

        // ===== OBJECT COLLABORATION =====
        System.out.println("\n=== OBJECT COLLABORATION ===");
        // Latihan 4: Object yang berinteraksi dengan object lain
        // Implementasikan transfer uang antar account
        // Buat history transaksi
        acc2.transfer(acc1, 1000);

        // Print statement semua akun
        acc1.printStatement();
        acc2.printStatement();

        // Implementasikan object collaboration
        Customer cust = new Customer("CUST01", "Dewi", "08123456789", "dewi@email.com");
        cust.addAccount(acc1);
        cust.addAccount(acc3);
        cust.showAllAccounts();
    }
}

// ===== CLASS DEFINITIONS =====

// Implementasikan class BankAccount
class BankAccount {
    // Instance variables
    // accountNumber, accountHolderName, balance, accountType, isActive
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private String accountType;
    private boolean isActive;
    private List<Transaction> transactions;

    // Constructor(s)
    // Buat multiple constructors
    public BankAccount(String accountNumber, String accountHolderName, double balance, String accountType, boolean isActive) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.accountType = accountType;
        this.isActive = isActive;
        this.transactions = new ArrayList<>();
    }

    public BankAccount(String accountNumber, String accountHolderName) {
        this(accountNumber, accountHolderName, 0, "Savings", false);
    }

    // Instance methods untuk account operations
    // deposit(double amount)
    public BankAccount deposit(double amount) {
        if (!isActive) {
            System.out.println("Akun " + accountNumber + " tidak aktif. Aktifkan dahulu!");
            return this;
        }
        if (amount > 0) {
            balance += amount;
            addTransaction("DEPOSIT", amount, "Setoran tunai");
            System.out.println(accountHolderName + " menyetor: " + amount);
        }
        return this;
    }

    // withdraw(double amount) - with validation
    public BankAccount withdraw(double amount) {
        if (!isActive) {
            System.out.println("Akun tidak aktif. Tidak bisa melakukan penarikan.");
            return this;
        }
        if (canWithdraw(amount)) {
            balance -= amount;
            addTransaction("WITHDRAW", amount, "Penarikan tunai");
            System.out.println(accountHolderName + " menarik: " + amount);
        } else {
            System.out.println("Saldo tidak cukup atau jumlah tidak valid.");
        }
        return this;
    }

    // transfer(BankAccount target, double amount)
    public void transfer(BankAccount target, double amount) {
        if (!isActive || !target.isActive) {
            System.out.println("Salah satu akun tidak aktif. Transfer gagal.");
            return;
        }
        if (canWithdraw(amount)) {
            this.balance -= amount;
            target.balance += amount;
            addTransaction("TRANSFER_OUT", amount, "Transfer ke " + target.accountHolderName);
            target.addTransaction("TRANSFER_IN", amount, "Transfer dari " + this.accountHolderName);
            System.out.println("Transfer berhasil dari " + this.accountHolderName + " ke " + target.accountHolderName + " sebesar " + amount);
        } else {
            System.out.println("Transfer gagal. Saldo tidak cukup.");
        }
    }

    // getBalance()
    public double getBalance() {
        return balance;
    }

    // getAccountInfo()
    public String getAccountInfo() {
        return "No: " + accountNumber + " | Nama: " + accountHolderName + " | Tipe: " + accountType + " | Saldo: " + balance;
    }

    // activateAccount() / deactivateAccount()
    public void activateAccount() {
        isActive = true;
        System.out.println("Akun " + accountNumber + " telah diaktifkan.");
    }
    public void deactivateAccount() {
        isActive = false;
        System.out.println("Akun " + accountNumber + " telah dinonaktifkan.");
    }

    // Instance methods untuk business logic
    // calculateInterest(double rate)
    public double calculateInterest(double rate) {
        return balance * rate;
    }

    // canWithdraw(double amount)
    public boolean canWithdraw(double amount) {
        return amount > 0 && amount <= balance;
    }

    // getAccountStatus()
    public String getAccountStatus() {
        return isActive ? "Aktif" : "Nonaktif";
    }

    // Instance methods untuk formatting/display
    // displayBalance()
    public void displayBalance() {
        System.out.println("Saldo " + accountHolderName + ": " + balance);
    }

    // printStatement()
    public void printStatement() {
        System.out.println("\n=== Rekening: " + accountHolderName + " ===");
        for (Transaction t : transactions) {
            System.out.println(t);
        }
        System.out.println("Saldo akhir: " + balance);
    }

    private void addTransaction(String type, double amount, String description) {
        transactions.add(new Transaction(type, amount, description));
    }
}

// Implementasikan class Transaction (untuk history)
class Transaction {
    // transactionId, type, amount, timestamp, description
    private static int counter = 1000;
    private String transactionId;
    private String type;
    private double amount;
    private String timestamp;
    private String description;

    // Constructor dan methods
    public Transaction(String type, double amount, String description) {
        this.transactionId = "TX" + (++counter);
        this.type = type;
        this.amount = amount;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.description = description;
    }

    @Override
    public String toString() {
        return "[" + transactionId + "] " + timestamp + " | " + type + " | " + amount + " | " + description;
    }
}

// Implementasikan class Customer
class Customer {
    // customerId, name, phone, email, accounts[]
    private String customerId;
    private String name;
    private String phone;
    private String email;
    private List<BankAccount> accounts;

    // Methods untuk mengelola multiple accounts
    public Customer(String customerId, String name, String phone, String email) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.accounts = new ArrayList<>();
    }

    public void addAccount(BankAccount account) {
        accounts.add(account);
        System.out.println("Akun " + account.getAccountInfo() + " berhasil ditambahkan ke pelanggan " + name);
    }

    public void showAllAccounts() {
        System.out.println("\n=== Daftar Akun milik " + name + " ===");
        for (BankAccount acc : accounts) {
            System.out.println(acc.getAccountInfo());
        }
    }
}
