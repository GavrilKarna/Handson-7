package Task2;

import java.util.ArrayList;
import java.util.List;

// Implementasikan class Customer
public class Customer {
    // customerId, name, phone, email, accounts[]
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
