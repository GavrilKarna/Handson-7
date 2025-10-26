package Task1;

import java.util.Scanner;
import java.util.ArrayList;

public class IntroToOOPPractice {
    public static void main(String[] args) {
        /*
         * PRAKTIK HANDS-ON: Introduction to OOP
         *
         * Instruksi: Lengkapi semua latihan di bawah ini untuk memahami
         * perbedaan antara pemrograman prosedural dan object-oriented.
         */

        // ===== SEBELUM OOP: CARA PROSEDURAL =====
        System.out.println("=== SEBELUM OOP: CARA PROSEDURAL ===");

        // Latihan 1: Data mahasiswa dengan variabel terpisah (cara lama)
        String namaMhs1 = "Andi";
        String nimMhs1 = "2310001";
        String jurusanMhs1 = "Informatika";
        double ipkMhs1 = 3.5;

        String namaMhs2 = "Budi";
        String nimMhs2 = "2310002";
        String jurusanMhs2 = "Sistem Informasi";
        double ipkMhs2 = 3.0;

        String namaMhs3 = "Citra";
        String nimMhs3 = "2310003";
        String jurusanMhs3 = "Teknik Komputer";
        double ipkMhs3 = 2.4;

        // Print semua data mahasiswa menggunakan cara prosedural
        // Implementasikan printing untuk semua mahasiswa
        System.out.println("Mahasiswa 1: " + namaMhs1 + " (" + nimMhs1 + "), " + jurusanMhs1 + ", IPK: " + ipkMhs1);
        System.out.println("Mahasiswa 2: " + namaMhs2 + " (" + nimMhs2 + "), " + jurusanMhs2 + ", IPK: " + ipkMhs2);
        System.out.println("Mahasiswa 3: " + namaMhs3 + " (" + nimMhs3 + "), " + jurusanMhs3 + ", IPK: " + ipkMhs3);

        System.out.println("\n=== MASALAH DENGAN CARA PROSEDURAL ===");
        /*
         * 1. Tidak efisien — jika ada 100 mahasiswa, kita harus buat 400 variabel!
         * 2. Data tidak terorganisir — sulit untuk mengelompokkan data tiap mahasiswa.
         * 3. Tidak mudah dikembangkan — sulit menambah fungsi (misalnya: cek lulus, hitung predikat, dsb).
         */

        // ===== DENGAN OOP: CARA OBJECT-ORIENTED =====
        System.out.println("\n=== DENGAN OOP: CARA OBJECT-ORIENTED ===");

        // Latihan 2: Menggunakan class Mahasiswa// Latihan 2: Menggunakan class Mahasiswa (akan dibuat di bawah)
        // Buat 3 object Mahasiswa
        // Set data untuk setiap mahasiswa
        Mahasiswa m1 = new Mahasiswa("Andi", "2310001", "Informatika", 3.5);
        Mahasiswa m2 = new Mahasiswa("Budi", "2310002", "Sistem Informasi", 3.0);
        Mahasiswa m3 = new Mahasiswa("Citra", "2310003", "Teknik Komputer", 2.4);

        // Print menggunakan method dari class

        // Buat object mahasiswa dan isi datanya
        m1.tampilkanInfo();
        m2.tampilkanInfo();
        m3.tampilkanInfo();

        // ===== ANALOGI DUNIA NYATA =====
        System.out.println("\n=== ANALOGI DUNIA NYATA: PERPUSTAKAAN ===");

        // Latihan 3: Implementasi analogi perpustakaan
        // Buat beberapa object Buku
        Buku b1 = new Buku("Belajar Java", "John Doe", "978-1111", 2022, true);
        Buku b2 = new Buku("Pemrograman OOP", "Jane Smith", "978-2222", 2023, true);
        Buku b3 = new Buku("Struktur Data", "Rudi Hartono", "978-3333", 2020, true);

        // Buat object Mahasiswa yang meminjam buku
        Perpustakaan perpustakaan = new Perpustakaan("Perpustakaan Informatika");
        perpustakaan.tambahBuku(b1);
        perpustakaan.tambahBuku(b2);
        perpustakaan.tambahBuku(b3);

        perpustakaan.tampilkanDaftarBuku();

        // Simulasikan proses peminjaman
        System.out.println("\n--- Proses Peminjaman ---");
        perpustakaan.pinjamBuku(m1, b2);
        perpustakaan.pinjamBuku(m3, b1);

        // Cek ketersediaan buku setelah dipinjam
        perpustakaan.tampilkanDaftarBuku();

        // Citra mengembalikan buku
        System.out.println("\n--- Proses Pengembalian ---");
        perpustakaan.kembalikanBuku(m3, b1);
        perpustakaan.tampilkanDaftarBuku();

        // ===== KEUNTUNGAN OOP =====
        System.out.println("\n=== KEUNTUNGAN OOP ===");
        /*
         * 1. Reusability — class dapat digunakan kembali tanpa menulis ulang kode.
         * 2. Encapsulation — data terlindungi di dalam objek.
         * 3. Modularity — kode terorganisir dengan baik dan mudah dikelola.
         * 4. Scalability — mudah menambah fitur tanpa mengubah kode lama.
         * 5. Real-world modeling — mudah memodelkan dunia nyata (misal mahasiswa, buku, perpustakaan).
         */
    }
}

// ===== CLASS MAHASISWA =====
// Buat class Mahasiswa dengan struktur berikut:
class Mahasiswa {
    // Instance variables
    // Definisikan instance variables untuk nama, nim, jurusan, ipk
    String nama;
    String nim;
    String jurusan;
    double ipk;

    // Constructor
    // Buat constructor untuk inisialisasi data mahasiswa
    public Mahasiswa(String nama, String nim, String jurusan, double ipk) {
        this.nama = nama;
        this.nim = nim;
        this.jurusan = jurusan;
        this.ipk = ipk;
    }

    // Methods
    // Buat method untuk menampilkan informasi mahasiswa
    public void tampilkanInfo() {
        System.out.println("Nama: " + nama);
        System.out.println("NIM: " + nim);
        System.out.println("Jurusan: " + jurusan);
        System.out.println("IPK: " + ipk);
        System.out.println("Status: " + (isLulus() ? "Lulus" : "Belum Lulus"));
        System.out.println("Predikat: " + getPredikat());
        System.out.println("---------------------------");
    }

    // Buat method untuk mengecek apakah mahasiswa lulus (IPK >= 2.75)
    public boolean isLulus() {
        return ipk >= 2.75;
    }

    // Buat method untuk menghitung predikat berdasarkan IPK
    public String getPredikat() {
        if (ipk >= 3.5) return "Cumlaude";
        else if (ipk >= 3.0) return "Sangat Baik";
        else if (ipk >= 2.75) return "Baik";
        else return "Perlu Perbaikan";
    }
}

// Buat class Buku dengan struktur berikut:
// ===== CLASS BUKU =====
class Buku {
    // Instance variables
    // Definisikan variables untuk judul, penulis, isbn, tahunTerbit, tersedia
    String judul;
    String penulis;
    String isbn;
    int tahunTerbit;
    boolean tersedia;

    // Constructor
    // Buat constructor
    public Buku(String judul, String penulis, String isbn, int tahunTerbit, boolean tersedia) {
        this.judul = judul;
        this.penulis = penulis;
        this.isbn = isbn;
        this.tahunTerbit = tahunTerbit;
        this.tersedia = tersedia;
    }

    // Methods
    // Buat method untuk menampilkan info buku
    public void tampilkanInfo() {
        System.out.println(judul + " - " + penulis + " (" + tahunTerbit + ")");
        System.out.println("ISBN: " + isbn);
        System.out.println("Status: " + (tersedia ? "Tersedia" : "Sedang Dipinjam"));
        System.out.println("---------------------------");
    }

    // Buat method untuk meminjam buku
    public void pinjam() {
        if (tersedia) {
            tersedia = false;
            System.out.println("Buku \"" + judul + "\" berhasil dipinjam.");
        } else {
            System.out.println("Maaf, buku \"" + judul + "\" sedang dipinjam.");
        }
    }

    // Buat method untuk mengembalikan buku
    public void kembalikan() {
        if (!tersedia) {
            tersedia = true;
            System.out.println("Buku \"" + judul + "\" telah dikembalikan.");
        } else {
            System.out.println("Buku \"" + judul + "\" memang sudah tersedia.");
        }
    }
}

// Buat class Perpustakaan yang mengelola buku dan peminjaman
// ===== CLASS PERPUSTAKAAN =====
class Perpustakaan {
    // Implementasikan class perpustakaan
    String namaPerpustakaan;
    ArrayList<Buku> daftarBuku = new ArrayList<>();

    public Perpustakaan(String namaPerpustakaan) {
        this.namaPerpustakaan = namaPerpustakaan;
    }

    public void tambahBuku(Buku buku) {
        daftarBuku.add(buku);
    }

    public void tampilkanDaftarBuku() {
        System.out.println("\nDaftar Buku di " + namaPerpustakaan + ":");
        for (Buku b : daftarBuku) {
            b.tampilkanInfo();
        }
    }

    public void pinjamBuku(Mahasiswa mhs, Buku buku) {
        System.out.println(mhs.nama + " mencoba meminjam \"" + buku.judul + "\"");
        buku.pinjam();
    }

    public void kembalikanBuku(Mahasiswa mhs, Buku buku) {
        System.out.println(mhs.nama + " mengembalikan \"" + buku.judul + "\"");
        buku.kembalikan();
    }
}