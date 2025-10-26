package Task6;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LibraryUtils {
    // Constants
    // static final variables untuk berbagai konstanta
    public static final int DEFAULT_LOAN_DAYS = 14;
    public static final double DEFAULT_FINE_PER_DAY = Library.FINE_PER_DAY;

    // Private constructor
    // private LibraryUtils() { }
    private LibraryUtils() { }

    // Static utility methods
    // static boolean isValidISBN(), static boolean isValidEmail()
    public static boolean isValidISBN(String isbn) {
        if (isbn == null) return false;
        String digits = isbn.replaceAll("[^0-9Xx]", "");
        return digits.length() == 10 || digits.length() == 13;
    }

    public static boolean isValidEmail(String email) {
        if (email == null) return false;
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    // static String formatDate(), static double calculateLateFee()
    public static String formatDate(LocalDate d) {
        return d == null ? "" : d.format(DateTimeFormatter.ISO_DATE);
    }

    public static double calculateLateFee(long daysLate) {
        if (daysLate <= 0) return 0.0;
        return daysLate * DEFAULT_FINE_PER_DAY;
    }
}
