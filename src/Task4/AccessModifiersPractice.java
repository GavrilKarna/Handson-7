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
