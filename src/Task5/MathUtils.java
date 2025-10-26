package Task5;

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
