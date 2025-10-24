package Task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        BankAccount acc1 = new BankAccount("001", "Alice", 1000.0, "Tabungan");
        BankAccount acc2 = new BankAccount("002", "Bob", 2000.0, "Giro");
        BankAccount acc3 = new BankAccount("003", "Charlie", 500.0, "Tabungan");

        // Tampilkan saldo masing-masing account
        System.out.println("Saldo acc1: " + acc1.getBalance());
        System.out.println("Saldo acc2: " + acc2.getBalance());
        System.out.println("Saldo acc3: " + acc3.getBalance());

        // Lakukan transaksi pada masing-masing account
        acc1.deposit(500);
        acc2.withdraw(300);
        acc3.transfer(acc1, 200);

        // Tunjukkan bahwa perubahan pada satu object tidak mempengaruhi yang lain
        System.out.println("Setelah transaksi:");
        System.out.println("Saldo acc1: " + acc1.getBalance());
        System.out.println("Saldo acc2: " + acc2.getBalance());
        System.out.println("Saldo acc3: " + acc3.getBalance());

        // ==== INSTANCE METHODS ADVANCED =====
        System.out.println("\n===  INSTANCE METHODS ADVANCED ===");

        // Latihan 2: Instance methods yang bekerja dengan instance variables
        // Implementasikan berbagai jenis instance methods
        // Methods yang mengubah state object
        // Methods yang mengembalikan nilai berdasarkan state
        // Methods yang berinteraksi dengan object lain

        // Demonstrasikan berbagai jenis instance methods
        acc1.displayBalance();
        System.out.println("Dapat menarik 1500 dari acc1? " + acc1.canWithdraw(1500));
        System.out.println("Bunga pada acc1 dengan 5%: " + acc1.calculateInterest(5.0));
        System.out.println("Status acc1: " + acc1.getAccountStatus());
        acc1.deactivateAccount();
        System.out.println("Status acc1 setelah dinonaktifkan: " + acc1.getAccountStatus());
        acc1.activateAccount();
        System.out.println("Status acc1 setelah diaktifkan: " + acc1.getAccountStatus());

        // ===== INSTANCE METHODS ADVANCED =====
        System.out.println("\n=== METHOD INTERACTION ===");

        // Latihan 3: Methods yang memanggil methods lain
        // Buat methods yang memanggil methods lain dalam class yang sama
        // Demonstrasikan method chaining

        // Implementasikan method chaining dan interaction
        acc2.performDepositAndCheck(400); // Memanggil deposit dan kemudian canWithdraw
        acc3.performWithdrawAndDisplay(100); // Memanggil withdraw dan kemudian displayBalance

        // =====OBJECT COLLABORATION =====
        System.out.println("\n=== OBJECT COLLABORATION ===");

        // Latihan 4: Object yang berinteraksi dengan object lain
        // Implementasikan transfer uang antar account
        // Buat history transaksi

        // Implementasikan object collaboration
        acc1.transfer(acc2, 300); // Transfer dan tambahkan ke riwayat
        acc2.printStatement(); // Tampilkan riwayat
        Customer cust = new Customer("C001", "Alice", "123-456", "alice@email.com");
        cust.addAccount(acc1);
        cust.addAccount(acc3);
        System.out.println("Total saldo untuk pelanggan: " + cust.getTotalBalance());
    }
}

// ===== DEFINISI KELAS =====

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
    public BankAccount(String accountNumber, String accountHolderName, double balance, String accountType) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.accountType = accountType;
        this.isActive = true;
        this.transactions = new ArrayList<>();
    }

    public BankAccount(String accountNumber, String accountHolderName, String accountType) {
        this(accountNumber, accountHolderName, 0.0, accountType);
    }

    // Instance methods untuk account operations
    // deposit(double amount)
    public void deposit(double amount) {
        if (isActive && amount > 0) {
            balance += amount;
            transactions.add(new Transaction(generateId(), "deposit", amount, "Setoran ke akun"));
        }
    }

    // withdraw(double amount) - with validation
    public boolean withdraw(double amount) {
        if (canWithdraw(amount)) {
            balance -= amount;
            transactions.add(new Transaction(generateId(), "withdraw", amount, "Penarikan dari akun"));
            return true;
        }
        return false;
    }

    // transfer(BankAccount target, double amount)
    public boolean transfer(BankAccount target, double amount) {
        if (withdraw(amount)) {
            target.deposit(amount);
            transactions.add(new Transaction(generateId(), "transfer", amount, "Transfer ke " + target.getAccountNumber()));
            target.transactions.add(new Transaction(generateId(), "transfer", amount, "Transfer dari " + this.accountNumber));
            return true;
        }
        return false;
    }

    // getBalance()
    public double getBalance() {
        return balance;
    }

    // getAccountInfo()
    public String getAccountInfo() {
        return "Akun: " + accountNumber + ", Pemegang: " + accountHolderName + ", Tipe: " + accountType + ", Aktif: " + isActive;
    }

    // activateAccount() / deactivateAccount()
    public void activateAccount() {
        isActive = true;
    }

    public void deactivateAccount() {
        isActive = false;
    }

    // Instance methods untuk business logic
    // calculateInterest(double rate)
    public double calculateInterest(double rate) {
        return balance * rate / 100;
    }

    // canWithdraw(double amount)
    public boolean canWithdraw(double amount) {
        return isActive && balance >= amount;
    }

    // getAccountStatus()
    public String getAccountStatus() {
        return isActive ? "Aktif" : "Tidak Aktif";
    }

    // Instance methods untuk formatting/display
    // displayBalance()
    public void displayBalance() {
        System.out.println("Saldo untuk " + accountHolderName + ": " + balance);
    }

    // printStatement()
    public void printStatement() {
        System.out.println("Laporan untuk " + accountHolderName + ":");
        for (Transaction t : transactions) {
            System.out.println(t.getDescription() + " - " + t.getType() + " - " + t.getAmount());
        }
    }

    // Metode tambahan untuk interaksi
    public void performDepositAndCheck(double amount) {
        deposit(amount);
        System.out.println("Dapat menarik setelah setoran? " + canWithdraw(amount));
    }

    public void performWithdrawAndDisplay(double amount) {
        withdraw(amount);
        displayBalance();
    }

    private String generateId() {
        return "TXN" + System.currentTimeMillis();
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}

// Implementasikan class Transaction (untuk history)
class Transaction {
    // transactionId, type, amount, timestamp, description
    private String transactionId;
    private String type;
    private double amount;
    private long timestamp;
    private String description;

    // Constructor dan methods
    public Transaction(String transactionId, String type, double amount, String description) {
        this.transactionId = transactionId;
        this.type = type;
        this.amount = amount;
        this.timestamp = System.currentTimeMillis();
        this.description = description;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getDescription() {
        return description;
    }
}

// Implementasikan class Customer
class Customer {
    private String customerId;
    private String name;
    private String phone;
    private String email;
    private List<BankAccount> accounts;

    public Customer(String customerId, String name, String phone, String email) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.accounts = new ArrayList<>();
    }

    // Methods untuk mengelola multiple accounts
    public void addAccount(BankAccount account) {
        accounts.add(account);
    }

    public void removeAccount(BankAccount account) {
        accounts.remove(account);
    }

    public double getTotalBalance() {
        double total = 0;
        for (BankAccount acc : accounts) {
            total += acc.getBalance();
        }
        return total;
    }

    public List<BankAccount> getAccounts() {
        return accounts;
    }
}
