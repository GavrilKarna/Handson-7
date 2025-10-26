package Task6;

import java.time.LocalDate;
import java.util.Objects;

public class Book {
    // Private instance variables
    // isbn, title, author, publisher, yearPublished, category, isAvailable, borrowCount
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private int yearPublished;
    private BookCategory category;
    private boolean isAvailable;
    private int borrowCount;

    // Static variables
    // static int totalBooks
    private static int totalBooks = 0;

    // Constructors dengan overloading
    // Multiple constructors
    public Book(String isbn, String title, String author, String publisher, int yearPublished, BookCategory category) {
        if (!LibraryUtils.isValidISBN(isbn)) {
            throw new IllegalArgumentException("ISBN tidak valid: " + isbn);
        }
        this.isbn = isbn;
        this.title = Objects.requireNonNull(title, "Title tidak boleh null");
        this.author = Objects.requireNonNull(author, "Author tidak boleh null");
        this.publisher = publisher != null ? publisher : "Unknown";
        this.yearPublished = Math.max(0, yearPublished);
        this.category = category != null ? category : BookCategory.NON_FICTION;
        this.isAvailable = true;
        this.borrowCount = 0;
        totalBooks++;
    }

    // Getters & setters dengan validation
    // All getters and setters dengan appropriate validation
    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = Objects.requireNonNull(title); }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = Objects.requireNonNull(author); }
    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher != null ? publisher : this.publisher; }
    public int getYearPublished() { return yearPublished; }
    public void setYearPublished(int yearPublished) { this.yearPublished = Math.max(0, yearPublished); }
    public BookCategory getCategory() { return category; }
    public void setCategory(BookCategory category) { if (category != null) this.category = category; }
    public boolean isAvailable() { return isAvailable; }
    public int getBorrowCount() { return borrowCount; }
    public static int getTotalBooks() { return totalBooks; }

    // Business methods
    // borrowBook(), returnBook(), getPopularityScore()
    public boolean borrowBook() {
        if (!isAvailable) return false;
        isAvailable = false;
        borrowCount++;
        return true;
    }

    public boolean returnBook() {
        if (isAvailable) return false;
        isAvailable = true;
        return true;
    }

    public double getPopularityScore() {
        int age = getAgeInYears();
        return borrowCount / (1.0 + Math.log(Math.max(1, age)));
    }

    // Utility
    // displayBookInfo(), isClassic(), getAgeInYears()
    public void displayBookInfo() {
        System.out.printf("ISBN: %s | %s — %s (%d) | %s | Available: %s | Borrowed: %d%n",
                isbn, title, author, yearPublished, category, isAvailable ? "Yes" : "No", borrowCount);
    }

    public boolean isClassic() {
        return getAgeInYears() >= 50;
    }

    public int getAgeInYears() {
        int now = LocalDate.now().getYear();
        return now - yearPublished;
    }
}
