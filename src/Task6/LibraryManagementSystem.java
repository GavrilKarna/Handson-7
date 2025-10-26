package Task6;

import java.time.LocalDate;
import java.util.List;

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
