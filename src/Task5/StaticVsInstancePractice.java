package Task5;

public class StaticVsInstancePractice {
    public static void main(String[] args) {
        /*
         * PRAKTIK HANDS-ON: Static vs Instance
         *
         * Instruksi: Lengkapi semua latihan di bawah ini untuk menguasai
         * perbedaan static dan instance members, kapan menggunakan masing-masing.
         */

        // ===== STATIC VARIABLES DEMO =====
        System.out.println("=== STATIC VARIABLES DEMO ===");

        // Latihan 1: Static variables sebagai shared data
        // Buat beberapa object dari class yang memiliki static counter
        // Tunjukkan bahwa static variable di-share oleh semua object

        // Demonstrasikan static variable sharing
        Counter c1 = new Counter("Counter-A");
        Counter c2 = new Counter("Counter-B");
        Counter c3 = new Counter("Counter-C");

        c1.displayCounterInfo();
        c2.displayCounterInfo();
        c3.displayCounterInfo();

        System.out.println("Total global count (harus 3): " + Counter.getGlobalCount());

        // ===== STATIC METHODS DEMO =====
        System.out.println("\n=== STATIC METHODS DEMO ===");

        // Latihan 2: Static methods sebagai utility functions
        // Implementasikan utility methods yang tidak butuh object
        // Tunjukkan cara memanggil static methods

        // Demonstrasikan static methods usage
        double area = MathUtils.calculateCircleArea(10);
        double distance = MathUtils.calculateDistance(0, 0, 3, 4);
        boolean primeCheck = MathUtils.isPrime(17);
        int factorial = MathUtils.factorial(5);
        double power = MathUtils.power(2, 10);

        System.out.println("Area of circle radius 10: " + area);
        System.out.println("Distance (0,0)-(3,4): " + distance);
        System.out.println("Is 17 prime? " + primeCheck);
        System.out.println("Factorial of 5: " + factorial);
        System.out.println("2^10 = " + power);

        // ===== STATIC VS INSTANCE COMPARISON =====
        System.out.println("\n=== STATIC VS INSTANCE COMPARISON ===");
        // Latihan 3: Perbandingan langsung static vs instance
        // Tunjukkan memory usage difference
        // Performance comparison

        // Implementasikan perbandingan
        Counter c4 = new Counter("Counter-D");
        c4.incrementInstance();
        c4.incrementInstance();

        System.out.println("Static global count (semua objek): " + Counter.getGlobalCount());
        System.out.println("Instance count milik c4 saja: " + c4.getInstanceCount());

        System.out.println("Perbedaan: static di-share semua objek, instance milik satu objek saja.");

        // ===== STATIC INITIALIZATION =====
        System.out.println("\n=== STATIC INITIALIZATION ===");
        // Latihan 4: Static initialization blocks
        // Tunjukkan kapan static variables diinisialisasi
        // Static blocks vs static variable initialization

        // Demonstrasikan static initialization
        DatabaseConnection conn1 = DatabaseConnection.getConnection();
        conn1.connect();
        conn1.executeQuery("SELECT * FROM users");

        DatabaseConnection conn2 = DatabaseConnection.getConnection();
        conn2.connect();

        System.out.println("Active connections: " + DatabaseConnection.getActiveConnectionCount());

        conn1.disconnect();
        conn2.disconnect();
        DatabaseConnection.closeAllConnections();

        // ===== BEST PRACTICES =====
        System.out.println("\n=== BEST PRACTICES ===");
        // Latihan 5: Kapan menggunakan static vs instance
        // Constants (static final)
        // Utility methods (static)
        // Counters/global state (static)
        // Object-specific data (instance)

        // Implementasikan best practices examples
        Student.setUniversityName("Universitas Indonesia");

        Student s1 = new Student("S001", "Andi", "Informatika", 3.8);
        Student s2 = new Student("S002", "Budi", "Sistem Informasi", 3.4);

        s1.displayStudentInfo();
        s2.displayStudentInfo();

        System.out.println("Total mahasiswa: " + Student.getTotalStudents());
        System.out.println("Universitas: " + Student.getUniversityName());
    }
}
