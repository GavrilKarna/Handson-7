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

// ===== CLASS DEFINITIONS =====

// Implementasikan class Counter dengan static dan instance counters
class Counter {
    // Static variables
    // static int globalCount
    // static final String APP_NAME
    static int globalCount = 0;
    static final String APP_NAME;

    // Instance variables
    // int instanceCount
    // String counterName
    int instanceCount;
    String counterName;

    // Static initialization block
    // static { ... }
    static {
        APP_NAME = "Static Counter App";
        System.out.println("Static block dijalankan: " + APP_NAME + " diinisialisasi.");
    }

    // Constructor
    // Increment both static dan instance counters
    public Counter(String counterName) {
        this.counterName = counterName;
        instanceCount = 0;
        globalCount++;
    }

    // Static methods
    // static int getGlobalCount()
    public static int getGlobalCount() {
        return globalCount;
    }

    // static void resetGlobalCount()
    public static void resetGlobalCount() {
        globalCount = 0;
    }

    // static void displayAppInfo()
    public static void displayAppInfo() {
        System.out.println("Aplikasi: " + APP_NAME + " | Total objek dibuat: " + globalCount);
    }

    // Instance methods
    // int getInstanceCount()
    public int getInstanceCount() {
        return instanceCount;
    }

    // void incrementInstance()
    public void incrementInstance() {
        instanceCount++;
    }

    // void displayCounterInfo()
    public void displayCounterInfo() {
        System.out.println(counterName + " -> instanceCount: " + instanceCount + ", globalCount: " + globalCount);
    }
}

// Implementasikan class MathUtils dengan static utility methods
class MathUtils {
    // Constants
    // static final double PI
    // static final double E
    static final double PI = 3.14159;
    static final double E = 2.71828;

    // Private constructor (utility class)
    // private MathUtils() { ... }
    private MathUtils() {
    }

    // Static utility methods
    // static double calculateCircleArea(double radius)
    public static double calculateCircleArea(double radius) {
        return PI * radius * radius;
    }

    // static double calculateDistance(double x1, double y1, double x2, double y2)
    public static double calculateDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    // static boolean isPrime(int number)
    public static boolean isPrime(int number) {
        if (number <= 1) return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }
        return true;
    }

    // static int factorial(int n)
    public static int factorial(int n) {
        if (n <= 1) return 1;
        return n * factorial(n - 1);
    }

    // static double power(double base, int exponent)
    public static double power(double base, int exponent) {
        double result = 1;
        for (int i = 1; i <= exponent; i++) {
            result *= base;
        }
        return result;
    }
}

// Implementasikan class DatabaseConnection dengan static connection pool
class DatabaseConnection {
    // Static variables untuk connection pool
    // static int maxConnections
    // static int activeConnections
    // static boolean isInitialized
    static int maxConnections = 3;
    static int activeConnections = 0;
    static boolean isInitialized = false;

    // Instance variables
    // String connectionId
    // boolean isConnected
    // String database
    String connectionId;
    boolean isConnected;
    String database;

    // Static initialization
    // static block untuk setup connection pool
    static {
        System.out.println("Inisialisasi connection pool...");
        isInitialized = true;
    }

    // Constructor
    private DatabaseConnection(String connectionId, String database) {
        this.connectionId = connectionId;
        this.database = database;
        this.isConnected = false;
    }

    // Static methods untuk connection management
    // static DatabaseConnection getConnection()
    public static DatabaseConnection getConnection() {
        if (activeConnections < maxConnections) {
            activeConnections++;
            return new DatabaseConnection("CONN-" + activeConnections, "MainDB");
        } else {
            System.out.println("Tidak bisa membuat koneksi baru: pool penuh!");
            return null;
        }
    }

    // static void closeAllConnections()
    public static void closeAllConnections() {
        System.out.println("Menutup semua koneksi...");
        activeConnections = 0;
    }

    // static int getActiveConnectionCount()
    public static int getActiveConnectionCount() {
        return activeConnections;
    }

    // Instance methods
    // void connect()
    public void connect() {
        if (!isConnected) {
            isConnected = true;
            System.out.println(connectionId + " terhubung ke " + database);
        }
    }

    // void disconnect()
    public void disconnect() {
        if (isConnected) {
            isConnected = false;
            activeConnections--;
            System.out.println(connectionId + " terputus dari " + database);
        }
    }

    // void executeQuery(String query)
    public void executeQuery(String query) {
        if (isConnected) {
            System.out.println(connectionId + " menjalankan query: " + query);
        } else {
            System.out.println("Koneksi belum aktif!");
        }
    }
}

// Implementasikan class Student dengan mixed static/instance
class Student {
    // Static variables
    // static String universityName
    // static int totalStudents
    static String universityName;
    static int totalStudents = 0;

    // Instance variables
    // String studentId, name, major
    // double gpa
    String studentId, name, major;
    double gpa;

    // Constructor
    // Increment totalStudents
    public Student(String studentId, String name, String major, double gpa) {
        this.studentId = studentId;
        this.name = name;
        this.major = major;
        this.gpa = gpa;
        totalStudents++;
    }

    // Static methods
    // static int getTotalStudents()
    public static int getTotalStudents() {
        return totalStudents;
    }

    // static void setUniversityName(String name)
    public static void setUniversityName(String name) {
        universityName = name;
    }

    // static String getUniversityName()
    public static String getUniversityName() {
        return universityName;
    }

    // Instance methods
    // void updateGPA(double newGPA)
    public void updateGPA(double newGPA) {
        gpa = newGPA;
    }

    // void displayStudentInfo()
    public void displayStudentInfo() {
        System.out.println("[" + studentId + "] " + name + " (" + major + ") GPA: " + gpa +
                " | Universitas: " + universityName + " | Honor: " + (isHonorStudent() ? "Ya" : "Tidak"));
    }

    // boolean isHonorStudent()
    public boolean isHonorStudent() {
        return gpa >= 3.7;
    }
}
