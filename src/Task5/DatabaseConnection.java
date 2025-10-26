package Task5;

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

    // Static methods untuk connection management
    // static DatabaseConnection getConnection()
    // static void closeAllConnections()
    // static int getActiveConnectionCount()
    private DatabaseConnection(String connectionId, String database) {
        this.connectionId = connectionId;
        this.database = database;
        this.isConnected = false;
    }

    public static DatabaseConnection getConnection() {
        if (activeConnections < maxConnections) {
            activeConnections++;
            return new DatabaseConnection("CONN-" + activeConnections, "MainDB");
        } else {
            System.out.println("Tidak bisa membuat koneksi baru: pool penuh!");
            return null;
        }
    }

    public static void closeAllConnections() {
        System.out.println("Menutup semua koneksi...");
        activeConnections = 0;
    }

    public static int getActiveConnectionCount() {
        return activeConnections;
    }

    // Instance methods
    // void connect()
    // void disconnect()
    // void executeQuery(String query)
    public void connect() {
        if (!isConnected) {
            isConnected = true;
            System.out.println(connectionId + " terhubung ke " + database);
        }
    }

    public void disconnect() {
        if (isConnected) {
            isConnected = false;
            activeConnections--;
            System.out.println(connectionId + " terputus dari " + database);
        }
    }

    public void executeQuery(String query) {
        if (isConnected) {
            System.out.println(connectionId + " menjalankan query: " + query);
        } else {
            System.out.println("Koneksi belum aktif!");
        }
    }
}
