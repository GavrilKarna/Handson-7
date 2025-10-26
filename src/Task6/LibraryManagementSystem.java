package Task6;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        // ===== SETUP LIBRARY SYSTEM =====
        System.out.println("=== LIBRARY MANAGEMENT SYSTEM ===");
        // Latihan 1: Setup library dengan berbagai tipe buku dan member
        // Buat library instance
        Library lib = new Library("Perpustakaan Kota", 3, 14); // max 3 buku per member, loan 14 hari
        // Tambahkan berbagai buku
        lib.addBook(new Book("9780306406157", "Introduction to Algorithms", "Cormen", "MIT Press", 2009, BookCategory.SCIENCE));
        lib.addBook(new Book("9780140449136", "The Republic", "Plato", "Penguin", 2003, BookCategory.HISTORY));
        lib.addBook(new Book("9780451524935", "1984", "George Orwell", "Signet Classics", 1949, BookCategory.FICTION));
        lib.addBook(new Book("9780262033848", "Artificial Intelligence: A Modern Approach", "Russell & Norvig", "Pearson", 2020, BookCategory.SCIENCE));

        // Daftarkan beberapa member
        lib.registerMember(new Member("Afi", "afi@example.com", "081234567890", "Jl. Merdeka 1", MembershipType.STUDENT));
        lib.registerMember(new Member("Budi", "budi@example.com", "081298765432", "Jl. Sudirman 2", MembershipType.FACULTY));
        lib.registerMember(new Member("Citra", "citra@example.com", "081300011122", "Jl. Thamrin 3", MembershipType.PUBLIC));

        // ===== BOOK OPERATIONS =====
        System.out.println("\n=== BOOK OPERATIONS ===");
        // Latihan 2: Operasi buku
        // Tambah buku baru
        lib.addBook(new Book("9780307265432", "The Road", "Cormac McCarthy", "Vintage", 2006, BookCategory.FICTION));
        // Cari buku berdasarkan kriteria
        List<Book> search = lib.searchBooks("1984");
        System.out.println("Hasil pencarian '1984':");
        search.forEach(Book::displayBookInfo);

        // Update informasi buku
        Book b1984 = lib.getBookByISBN("9780451524935");
        if (b1984 != null) {
            b1984.setPublisher("Penguin Classics");
        }

        // ===== MEMBER OPERATIONS =====
        System.out.println("\n=== MEMBER OPERATIONS ===");
        // Latihan 3: Operasi member
        // Registrasi member baru
        Member mAfi = lib.getMemberById(1);
        if (mAfi != null) {
            System.out.println("Member 1 info:");
            System.out.println(mAfi);
        }

        // Update informasi member
        lib.updateMember(1, "Afi Nur", "afi.nur@example.com", null, null);
        System.out.println("Setelah update:");
        System.out.println(lib.getMemberById(1));

        // Cek status member
        System.out.println("\nMember Status Check:");
        lib.getActiveMembers().forEach(member ->
                System.out.println(member.getName() + " can borrow more: " + member.canBorrowMore(0, 3))
        );

        // ===== BORROWING SYSTEM =====
        System.out.println("\n=== BORROWING SYSTEM ===");
        // Latihan 4: Sistem peminjaman
        // Pinjam buku
        lib.borrowBook(1, "9780451524935");
        lib.borrowBook(2, "9780306406157");
        lib.borrowBook(1, "9780140449136");
        // Try borrow exceeding limit
        lib.borrowBook(1, "9780262033848");

        // Kembalikan buku
        lib.returnBook(1, "9780451524935");

        // Cek denda
        BorrowRecord overdueRecord = lib.forceCreateBorrow(3, "9780307265432", LocalDate.now().minusDays(30));
        System.out.println("Fine current for overdue record: " + overdueRecord.calculateFine());

        // Return overdue
        lib.returnBook(3, "9780307265432");

        // Perpanjang peminjaman
        lib.borrowBook(2, "9780262033848"); // Budi borrows AI book
        lib.extendLoan(2, "9780262033848", 7); // extend 7 hari

        // ===== REPORTING =====
        System.out.println("\n=== REPORTING ===");
        // Latihan 5: Sistem reporting
        // Laporan buku terpopuler
        // Laporan member aktif
        // Laporan denda
        // Statistik perpustakaan
        lib.generatePopularBooksReport(5);
        lib.generateActiveMembersReport(5);
        lib.generateOverdueReport();

        // ===== ADMIN FUNCTIONS =====
        System.out.println("\n=== ADMIN FUNCTIONS ===");
        // Latihan 6: Fungsi admin
        // Backup data
        // Generate reports
        // System maintenance
        lib.displayLibraryStats();
        lib.backup();
        lib.maintenance();
    }
}

// ===== CLASS DEFINITIONS =====

// Implementasikan class Book dengan encapsulation lengkap
class Book {
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

// Implementasikan class Member
class Member {
    // Private instance variables
    // memberId, name, email, phone, address, joinDate, membershipType, isActive
    private final int memberId;
    private String name;
    private String email;
    private String phone;
    private String address;
    private final LocalDate joinDate;
    private MembershipType membershipType;
    private boolean isActive;

    // Static variables
    // static int totalMembers, static final int MAX_BOOKS_ALLOWED
    private static int totalMembers = 0;
    public static final int MAX_BOOKS_ALLOWED_DEFAULT = 3;

    // Constructors
    // Constructor dengan validation
    public Member(String name, String email, String phone, String address, MembershipType membershipType) {
        this.memberId = ++totalMembers;
        this.name = Objects.requireNonNull(name, "Nama tidak boleh null");
        if (!LibraryUtils.isValidEmail(email)) throw new IllegalArgumentException("Email tidak valid");
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.joinDate = LocalDate.now();
        this.membershipType = membershipType != null ? membershipType : MembershipType.PUBLIC;
        this.isActive = true;
    }

    // Convenience constructor used in main (without id)
    public Member(String name, String email, String phone, String address) {
        this(name, email, phone, address, MembershipType.PUBLIC);
    }

    // Getter/Setter
    // With appropriate validation
    public int getMemberId() { return memberId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = Objects.requireNonNull(name); }
    public String getEmail() { return email; }
    public void setEmail(String email) {
        if (!LibraryUtils.isValidEmail(email)) throw new IllegalArgumentException("Email tidak valid");
        this.email = email;
    }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public LocalDate getJoinDate() { return joinDate; }
    public MembershipType getMembershipType() { return membershipType; }
    public void setMembershipType(MembershipType membershipType) {
        if (membershipType != null) this.membershipType = membershipType;
    }
    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { this.isActive = active; }
    public static int getTotalMembers() { return totalMembers; }

    // Business methods
    // canBorrowMore(), calculateMembershipDuration(), upgradeMembership()
    public boolean canBorrowMore(int currentlyBorrowed, int maxAllowed) {
        return isActive && currentlyBorrowed < maxAllowed;
    }

    public int calculateMembershipDuration() {
        return Period.between(joinDate, LocalDate.now()).getYears();
    }

    public void upgradeMembership(MembershipType newType) {
        if (newType != null && newType != this.membershipType) {
            this.membershipType = newType;
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ISO_DATE;
        return String.format("Member[%d] %s | %s | %s | Joined: %s | Type: %s | Active: %s",
                memberId, name, email, phone, joinDate.format(fmt), membershipType, isActive);
    }
}

// Implementasikan class BorrowRecord
class BorrowRecord {
    // Private variables
    // recordId, memberId, isbn, borrowDate, dueDate, returnDate, fine
    private final int recordId;
    private final int memberId;
    private final String isbn;
    private final LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private double fine;

    // Static variables
    // static int totalRecords
    private static int totalRecords = 0;

    // Constructor
    // Constructor dengan auto-calculation due date
    public BorrowRecord(int memberId, String isbn, LocalDate borrowDate, int loanPeriodDays) {
        this.recordId = ++totalRecords;
        this.memberId = memberId;
        this.isbn = isbn;
        this.borrowDate = borrowDate != null ? borrowDate : LocalDate.now();
        this.dueDate = this.borrowDate.plusDays(Math.max(1, loanPeriodDays));
        this.returnDate = null;
        this.fine = 0.0;
    }

    public int getRecordId() { return recordId; }
    public int getMemberId() { return memberId; }
    public String getIsbn() { return isbn; }
    public LocalDate getBorrowDate() { return borrowDate; }
    public LocalDate getDueDate() { return dueDate; }
    public LocalDate getReturnDate() { return returnDate; }
    public double getFine() { return fine; }
    public static int getTotalRecords() { return totalRecords; }

    public boolean isOverdue() {
        LocalDate checkDate = (returnDate != null) ? returnDate : LocalDate.now();
        return checkDate.isAfter(dueDate);
    }

    // Methods
    // calculateFine(), isOverdue(), returnBook(), extendLoan()
    public double calculateFine() {
        LocalDate effective = (returnDate != null) ? returnDate : LocalDate.now();
        if (!effective.isAfter(dueDate)) {
            fine = 0;
            return fine;
        }
        long daysLate = java.time.temporal.ChronoUnit.DAYS.between(dueDate, effective);
        fine = Library.FINE_PER_DAY * daysLate;
        return fine;
    }

    public void returnBook(LocalDate returnDate) {
        if (this.returnDate != null) {
            return; // already returned
        }
        this.returnDate = returnDate != null ? returnDate : LocalDate.now();
        calculateFine();
    }

    public boolean extendLoan(int extraDays, int maxExtendDays) {
        if (extraDays <= 0) return false;
        if (extraDays > maxExtendDays) return false;
        if (isOverdue()) return false;
        this.dueDate = this.dueDate.plusDays(extraDays);
        return true;
    }

    @Override
    public String toString() {
        return String.format("Record[%d] Member:%d ISBN:%s Borrow:%s Due:%s Return:%s Fine:%.2f",
                recordId, memberId, isbn, borrowDate, dueDate, returnDate, fine);
    }
}

// Implementasikan class Library
class Library {
    // Private variables untuk collections
    // ArrayList<Book> books, ArrayList<Member> members, ArrayList<BorrowRecord> borrowRecords
    private ArrayList<Book> books;
    private ArrayList<Member> members;
    private ArrayList<BorrowRecord> borrowRecords;

    // Static variables
    // static String libraryName, static final double FINE_PER_DAY
    public static String libraryName;
    public static final double FINE_PER_DAY = 1.5; // nominal denda per hari

    // Private variables untuk business logic
    // maxBooksPerMember, loanPeriodDays
    private int maxBooksPerMember;
    private int loanPeriodDays;
    private int maxExtendDays = 14;

    // Constructor
    // Initialize collections dan set library parameters
    public Library(String name, int maxBooksPerMember, int loanPeriodDays) {
        Library.libraryName = name;
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
        this.borrowRecords = new ArrayList<>();
        this.maxBooksPerMember = Math.max(1, maxBooksPerMember);
        this.loanPeriodDays = Math.max(1, loanPeriodDays);
    }

    // Book management methods
    // addBook(), removeBook(), searchBooks(), getAvailableBooks()
    public boolean addBook(Book book) {
        if (book == null) return false;
        if (getBookByISBN(book.getIsbn()) != null) {
            System.out.println("Book already exists: " + book.getIsbn());
            return false;
        }
        books.add(book);
        System.out.println("Added book: " + book.getTitle());
        return true;
    }

    public boolean removeBook(String isbn) {
        Book b = getBookByISBN(isbn);
        if (b == null) return false;
        if (!b.isAvailable()) {
            System.out.println("Tidak dapat menghapus: buku sedang dipinjam.");
            return false;
        }
        books.remove(b);
        return true;
    }

    public List<Book> searchBooks(String query) {
        String q = query != null ? query.toLowerCase() : "";
        return books.stream()
                .filter(b -> b.getTitle().toLowerCase().contains(q)
                        || b.getAuthor().toLowerCase().contains(q)
                        || b.getIsbn().contains(q))
                .collect(Collectors.toList());
    }

    public List<Book> getAvailableBooks() {
        return books.stream().filter(Book::isAvailable).collect(Collectors.toList());
    }

    public Book getBookByISBN(String isbn) {
        return books.stream().filter(b -> b.getIsbn().equals(isbn)).findFirst().orElse(null);
    }

    // Member management methods
    // registerMember(), updateMember(), getMemberById(), getActiveMembers()
    public boolean registerMember(Member member) {
        if (member == null) return false;
        if (getMemberById(member.getMemberId()) != null) {
            System.out.println("Member sudah terdaftar: " + member.getMemberId());
            return false;
        }
        members.add(member);
        System.out.println("Registered member: " + member.getName());
        return true;
    }

    public boolean updateMember(int memberId, String name, String email, String phone, String address) {
        Member m = getMemberById(memberId);
        if (m == null) return false;
        if (name != null) m.setName(name);
        if (email != null) m.setEmail(email);
        if (phone != null) m.setPhone(phone);
        if (address != null) m.setAddress(address);
        return true;
    }

    public Member getMemberById(int id) {
        return members.stream().filter(m -> m.getMemberId() == id).findFirst().orElse(null);
    }

    public List<Member> getActiveMembers() {
        return members.stream().filter(Member::isActive).collect(Collectors.toList());
    }

    // Borrowing methods
    // borrowBook(), returnBook(), extendLoan(), calculateFine()
    public boolean borrowBook(int memberId, String isbn) {
        Member m = getMemberById(memberId);
        Book b = getBookByISBN(isbn);
        if (!isValidMember(m) || b == null) {
            System.out.println("Borrow gagal: member atau buku tidak ditemukan.");
            return false;
        }
        int currentlyBorrowed = (int) borrowRecords.stream()
                .filter(r -> r.getMemberId() == memberId && r.getReturnDate() == null)
                .count();
        if (!m.canBorrowMore(currentlyBorrowed, maxBooksPerMember)) {
            System.out.println(m.getName() + " tidak dapat meminjam lebih banyak buku (limit).");
            return false;
        }
        if (!b.isAvailable()) {
            System.out.println("Buku tidak tersedia: " + b.getTitle());
            return false;
        }
        boolean borrowed = b.borrowBook();
        if (!borrowed) return false;
        BorrowRecord rec = new BorrowRecord(memberId, isbn, LocalDate.now(), loanPeriodDays);
        borrowRecords.add(rec);
        System.out.println(String.format("%s meminjam '%s' (Due: %s)", m.getName(), b.getTitle(), rec.getDueDate()));
        return true;
    }

    public BorrowRecord forceCreateBorrow(int memberId, String isbn, LocalDate borrowDate) {
        Member m = getMemberById(memberId);
        Book b = getBookByISBN(isbn);
        if (!isValidMember(m) || b == null || !b.isAvailable()) {
            System.out.println("forceCreateBorrow gagal");
            return null;
        }
        b.borrowBook();
        BorrowRecord rec = new BorrowRecord(memberId, isbn, borrowDate, loanPeriodDays);
        borrowRecords.add(rec);
        return rec;
    }

    public boolean returnBook(int memberId, String isbn) {
        Optional<BorrowRecord> maybe = borrowRecords.stream()
                .filter(r -> r.getMemberId() == memberId && r.getIsbn().equals(isbn) && r.getReturnDate() == null)
                .findFirst();
        if (!maybe.isPresent()) {
            System.out.println("Tidak ada catatan peminjaman aktif untuk member " + memberId + " dan buku " + isbn);
            return false;
        }
        BorrowRecord rec = maybe.get();
        rec.returnBook(LocalDate.now());
        Book b = getBookByISBN(isbn);
        if (b != null) b.returnBook();
        double fine = rec.calculateFine();
        if (fine > 0) {
            System.out.printf("Buku dikembalikan dengan denda: %.2f%n", fine);
        } else {
            System.out.println("Buku dikembalikan tanpa denda.");
        }
        return true;
    }

    public boolean extendLoan(int memberId, String isbn, int extraDays) {
        Optional<BorrowRecord> maybe = borrowRecords.stream()
                .filter(r -> r.getMemberId() == memberId && r.getIsbn().equals(isbn) && r.getReturnDate() == null)
                .findFirst();
        if (!maybe.isPresent()) {
            System.out.println("Tidak dapat memperpanjang: record tidak ditemukan.");
            return false;
        }
        BorrowRecord rec = maybe.get();
        boolean ok = rec.extendLoan(extraDays, maxExtendDays);
        System.out.println(ok ? "Perpanjangan berhasil. New due: " + rec.getDueDate() : "Perpanjangan gagal.");
        return ok;
    }

    public double calculateFine(int memberId, String isbn) {
        return borrowRecords.stream()
                .filter(r -> r.getMemberId() == memberId && r.getIsbn().equals(isbn))
                .mapToDouble(BorrowRecord::calculateFine)
                .sum();
    }

    // Reporting methods
    // generatePopularBooksReport(), generateActiveMembersReport(), generateOverdueReport()
    public void generatePopularBooksReport(int topN) {
        System.out.println("Laporan: Buku Terpopuler (Top " + topN + ")");
        books.stream()
                .sorted(Comparator.comparingInt(Book::getBorrowCount).reversed())
                .limit(topN)
                .forEach(b -> System.out.printf("%s — Borrowed: %d%n", b.getTitle(), b.getBorrowCount()));
    }

    public void generateActiveMembersReport(int topN) {
        System.out.println("Laporan: Member Paling Aktif (Top " + topN + ")");
        Map<Integer, Long> counts = borrowRecords.stream()
                .collect(Collectors.groupingBy(BorrowRecord::getMemberId, Collectors.counting()));
        counts.entrySet().stream()
                .sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
                .limit(topN)
                .forEach(e -> {
                    Member m = getMemberById(e.getKey());
                    System.out.printf("%s — Transactions: %d%n", m != null ? m.getName() : "Unknown", e.getValue());
                });
    }

    public void generateOverdueReport() {
        System.out.println("Laporan: Overdue Records");
        borrowRecords.stream()
                .filter(r -> r.getReturnDate() == null && r.isOverdue())
                .forEach(r -> {
                    Member m = getMemberById(r.getMemberId());
                    Book b = getBookByISBN(r.getIsbn());
                    System.out.printf("Member: %s | Book: %s | Due: %s | Days Late: %d | Est. Fine: %.2f%n",
                            m != null ? m.getName() : "Unknown",
                            b != null ? b.getTitle() : r.getIsbn(),
                            r.getDueDate(),
                            java.time.temporal.ChronoUnit.DAYS.between(r.getDueDate(), LocalDate.now()),
                            r.calculateFine());
                });
    }

    // Utility methods
    // displayLibraryStats(), backup(), maintenance()
    public void displayLibraryStats() {
        System.out.println("Library: " + libraryName);
        System.out.println("Total Books: " + Book.getTotalBooks());
        System.out.println("Total Members: " + Member.getTotalMembers());
        System.out.println("Total Borrow Records: " + BorrowRecord.getTotalRecords());
        System.out.println("Available Books: " + getAvailableBooks().size());
    }

    public void backup() {
        System.out.println("Backup data dilakukan (simulasi). Buku: " + books.size() + ", Members: " + members.size() + ", Records: " + borrowRecords.size());
    }

    public void maintenance() {
        System.out.println("Maintenance: Members inactive check ...");
        LocalDate tenYearsAgo = LocalDate.now().minusYears(10);
        for (Member m : members) {
            if (m.getJoinDate().isBefore(tenYearsAgo)) {
                m.setActive(false);
            }
        }
        System.out.println("Maintenance selesai.");
    }

    // Private helper methods
    // private boolean isValidMember(), private boolean isBookAvailable()
    private boolean isValidMember(Member m) {
        return m != null && m.isActive();
    }

    public boolean isBookAvailable(String isbn) {
        Book b = getBookByISBN(isbn);
        return b != null && b.isAvailable();
    }
}

// Implementasikan class LibraryUtils (static utility class)
class LibraryUtils {
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

// Implementasikan enum untuk BookCategory
enum BookCategory {
    FICTION,
    NON_FICTION,
    SCIENCE,
    HISTORY,
    BIOGRAPHY,
    ART,
    TECHNOLOGY,
    CHILDREN
}

// Implementasikan enum untuk MembershipType
enum MembershipType {
    STUDENT,
    FACULTY,
    PUBLIC
}
