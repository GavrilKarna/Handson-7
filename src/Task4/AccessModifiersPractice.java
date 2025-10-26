package Task4;

public class AccessModifiersPractice {
    public static void main(String[] args) {
        /*
         * PRAKTIK HANDS-ON: Access Modifiers & Encapsulation
         *
         * Tujuan:
         * - Memahami masalah dengan public variables
         * - Menerapkan encapsulation (private fields + getter/setter)
         * - Mengetahui berbagai access modifiers (private, default, protected, public)
         * - Menerapkan getter/setter yang baik dengan validasi
         */

        // ===== MASALAH DENGAN PUBLIC VARIABLES =====
        System.out.println("=== MASALAH DENGAN PUBLIC VARIABLES ===");

        // Latihan 1: Tunjukkan masalah dengan public instance variables
        // Buat object dengan public variables
        // Tunjukkan bagaimana data bisa dirusak dari luar
        BadExample bad = new BadExample("Andi", 25, 5000000, "andi@mail.com");

        // Demonstrasikan masalah public variables
        // Data awal
        System.out.println("Data awal: " + bad.name + ", " + bad.age + ", " + bad.salary + ", " + bad.email);

        // Data dirusak dari luar
        bad.age = -5;
        bad.salary = -10000;
        bad.email = "bukanEmail";
        bad.name = null;

        // Tampilkan data setelah rusak
        bad.showCorruptedData();

        // ===== ENCAPSULATION SOLUTION =====
        System.out.println("\n=== ENCAPSULATION SOLUTION ===");
        // Latihan 2: Implementasi encapsulation
        // Gunakan private variables dengan getter/setter
        // Tambahkan validasi dalam setter
        GoodExample good = new GoodExample("Budi", 30, 8000000, "budi@mail.com");

        good.displayInfo();

        // Demonstrasikan encapsulation dengan class yang proper
        // Uji setter dengan validasi
        System.out.println("\n-- Coba ubah data dengan nilai tidak valid --");
        good.setAge(10);
        good.setSalary(-5000);
        good.setEmail("salahEmail");
        good.setName("");

        // Lihat hasil setelah validasi
        good.displayInfo();

        // ===== ACCESS MODIFIER COMBINATIONS =====
        System.out.println("\n=== ACCESS MODIFIER COMBINATIONS ===");
        // Latihan 3: Berbagai kombinasi access modifiers
        // Tunjukkan perbedaan private, public, protected, default
        AccessModifierDemo demo = new AccessModifierDemo();
        demo.publicMethod();
        demo.defaultMethod();
        demo.protectedMethod();
        demo.testAccess();

        // ===== GETTER/SETTER BEST PRACTICES =====
        System.out.println("\n=== GETTER/SETTER BEST PRACTICES ===");
        // Latihan 4: Implementasi getter/setter yang proper
        // Naming conventions
        // Validation dalam setter
        // Read-only dan write-only properties
        BankAccountSecure account = new BankAccountSecure("12345678", 1000000, "4321");

        // Implementasikan getter/setter yang proper
        account.deposit(500000);
        account.checkBalance("4321");
        account.withdraw(300000, "4321");
        account.checkBalance("4321");

        // Coba salah PIN
        account.withdraw(100000, "0000");

        // Coba ubah PIN
        account.changePin("4321", "9999");
        account.checkBalance("9999");
    }
}

// ===== CLASS DEFINITIONS =====

// Implementasikan class BadExample (dengan public variables)
class BadExample {
    // Semua variables public - tunjukkan masalahnya
    public String name;
    public int age;
    public double salary;
    public String email;

    // Constructor sederhana
    public BadExample(String name, int age, double salary, String email) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.email = email;
    }

    // Method untuk menunjukkan data corruption
    public void showCorruptedData() {
        System.out.println("Data setelah dirusak:");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Salary: " + salary);
        System.out.println("Email: " + email);
    }
}

// Implementasikan class GoodExample (dengan encapsulation)
class GoodExample {
    // Private instance variables
    // private String name, age, salary, email
    private String name;
    private int age;
    private double salary;
    private String email;

    // Constructor
    // Constructor dengan parameter validation
    public GoodExample(String name, int age, double salary, String email) {
        setName(name);
        setAge(age);
        setSalary(salary);
        setEmail(email);
    }

    // Getter methods
    // Implementasikan getter methods yang proper
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public String getEmail() {
        return email;
    }

    // Setter methods dengan validation
    // setName() - tidak boleh null/kosong
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("[ERROR] Nama tidak boleh kosong.");
        } else {
            this.name = name;
        }
    }

    // setAge() - harus antara 17-65
    public void setAge(int age) {
        if (age < 17 || age > 65) {
            System.out.println("[ERROR] Umur harus antara 17 dan 65 tahun.");
        } else {
            this.age = age;
        }
    }

    // setSalary() - harus positif
    public void setSalary(double salary) {
        if (salary <= 0) {
            System.out.println("[ERROR] Gaji harus positif.");
        } else {
            this.salary = salary;
        }
    }

    // setEmail() - format email yang valid
    public void setEmail(String email) {
        if (!validateEmail(email)) {
            System.out.println("[ERROR] Format email tidak valid.");
        } else {
            this.email = email;
        }
    }

    // Business methods
    // validateEmail(String email)
    private boolean validateEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }

    // isRetirementAge()
    public boolean isRetirementAge() {
        return age >= 60;
    }

    // calculateTax()
    public double calculateTax() {
        return salary * 0.1;
    }

    public void displayInfo() {
        System.out.println("Nama: " + name);
        System.out.println("Usia: " + age);
        System.out.println("Gaji: " + salary);
        System.out.println("Email: " + email);
        System.out.println("Pajak (10%): " + calculateTax());
        System.out.println("Sudah pensiun? " + (isRetirementAge() ? "Ya" : "Belum"));
    }
}

// Implementasikan class BankAccountSecure
class BankAccountSecure {
    // Private variables
    // accountNumber, balance, pin, isLocked
    private String accountNumber;
    private double balance;
    private String pin;
    private boolean isLocked = false;

    // Constructor
    // Constructor dengan validation
    public BankAccountSecure(String accountNumber, double balance, String pin) {
        if (accountNumber == null || accountNumber.length() < 6) {
            throw new IllegalArgumentException("Nomor akun tidak valid!");
        }
        if (balance < 0) {
            throw new IllegalArgumentException("Saldo tidak boleh negatif!");
        }
        if (pin == null || pin.length() < 4) {
            throw new IllegalArgumentException("PIN minimal 4 digit!");
        }

        this.accountNumber = accountNumber;
        this.balance = balance;
        this.pin = pin;
    }

    // Public interface methods
    // deposit(double amount)
    public void deposit(double amount) {
        if (isLocked) {
            System.out.println("[AKUN TERKUNCI] Tidak dapat melakukan deposit.");
            return;
        }

        if (isValidAmount(amount)) {
            balance += amount;
            System.out.println("Deposit berhasil: +" + amount);
        } else {
            System.out.println("Jumlah deposit tidak valid.");
        }
    }

    // withdraw(double amount, String pin)
    public void withdraw(double amount, String pin) {
        if (isLocked) {
            System.out.println("[AKUN TERKUNCI] Tidak dapat melakukan penarikan.");
            return;
        }

        if (!validatePin(pin)) {
            System.out.println("PIN salah! Akun terkunci untuk keamanan.");
            lockAccount();
            return;
        }

        if (!isValidAmount(amount)) {
            System.out.println("Jumlah penarikan tidak valid.");
        } else if (amount > balance) {
            System.out.println("Saldo tidak cukup.");
        } else {
            balance -= amount;
            System.out.println("Penarikan berhasil: -" + amount);
        }
    }

    // checkBalance(String pin)
    public void checkBalance(String pin) {
        if (validatePin(pin)) {
            System.out.println("Saldo saat ini: " + balance);
        } else {
            System.out.println("PIN salah. Tidak dapat melihat saldo.");
        }
    }

    // changePin(String oldPin, String newPin)
    public void changePin(String oldPin, String newPin) {
        if (!validatePin(oldPin)) {
            System.out.println("PIN lama salah.");
            return;
        }

        if (newPin == null || newPin.length() < 4) {
            System.out.println("PIN baru tidak valid.");
            return;
        }

        this.pin = newPin;
        System.out.println("PIN berhasil diubah.");
    }

    // Private helper methods
    // private boolean validatePin(String pin)
    private boolean validatePin(String pin) {
        return this.pin.equals(pin);
    }

    // private void lockAccount()
    private void lockAccount() {
        this.isLocked = true;
    }

    // private boolean isValidAmount(double amount)
    private boolean isValidAmount(double amount) {
        return amount > 0;
    }

    // Read-only properties
    // getAccountNumber() - tanpa setter
    public String getAccountNumber() {
        return accountNumber;
    }

    // getAccountStatus()
    public String getAccountStatus() {
        return isLocked ? "Terkunci" : "Aktif";
    }

    // Write-only properties (jarang digunakan)
    // setSecurityLevel(int level) - tanpa getter
    public void setSecurityLevel(int level) {
        System.out.println("Level keamanan diset ke: " + level);
    }
}

// Implementasikan class AccessModifierDemo
class AccessModifierDemo {
    private String privateField = "Private";
    protected String protectedField = "Protected";
    String defaultField = "Default";
    public String publicField = "Public";

    private void privateMethod() {
        System.out.println("Private method called");
    }

    protected void protectedMethod() {
        System.out.println("Protected method called");
    }

    void defaultMethod() {
        System.out.println("Default method called");
    }

    public void publicMethod() {
        System.out.println("Public method called");
    }

    // Method untuk test accessibility dari dalam class
    public void testAccess() {
        // Panggil semua methods dan akses semua field
        System.out.println("=== Testing Access dari dalam class ===");
        System.out.println("Private field: " + privateField);
        System.out.println("Protected field: " + protectedField);
        System.out.println("Default field: " + defaultField);
        System.out.println("Public field: " + publicField);

        privateMethod();
        protectedMethod();
        defaultMethod();
        publicMethod();
    }
}
