package Task3;

// Implementasikan class InitializationDemo
class InitializationDemo {
    // Tunjukkan instance variable initialization
    int a = initializeA();
    int b = 20;

    // Tunjukkan urutan eksekusi constructor
    static {
        System.out.println("Static block dieksekusi terlebih dahulu (sekali saja).");
    }

    {
        System.out.println("Instance initializer block dieksekusi sebelum constructor.");
    }

    // Tambahkan System.out.println di berbagai tempat untuk tracking
    public InitializationDemo() {
        System.out.println("Constructor dipanggil setelah inisialisasi variabel instance.");
        System.out.println("Nilai a: " + a + ", Nilai b: " + b);
    }

    private int initializeA() {
        System.out.println("Instance variable 'a' sedang diinisialisasi.");
        return 10;
    }
}
