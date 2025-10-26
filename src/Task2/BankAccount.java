package Task2;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {
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
