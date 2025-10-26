package Task5;

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
