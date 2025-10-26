package Task4;

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
