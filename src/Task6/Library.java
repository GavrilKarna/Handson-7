package Task6;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Library {
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
