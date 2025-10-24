package Task1;

import java.util.Scanner;

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
        // Buat variabel untuk menyimpan data 3 mahasiswa
        // Setiap mahasiswa memiliki: nama, nim, jurusan, ipk

        String namaMhs1 = "Alice"; // Isi dengan data mahasiswa 1
        String nimMhs1 = "12345";
        String jurusanMhs1 = "Informatika";
        double ipkMhs1 = 3.5;

        String namaMhs2 = "Bob"; // Isi dengan data mahasiswa 2
        String nimMhs2 = "67890";
        String jurusanMhs2 = "Sistem Informasi";
        double ipkMhs2 = 2.8;

        String namaMhs3 = "Charlie"; // Isi dengan data mahasiswa 3
        String nimMhs3 = "11111";
        String jurusanMhs3 = "Teknik Elektro";
        double ipkMhs3 = 3.2;

        // Print semua data mahasiswa menggunakan cara prosedural
        // Implementasikan printing untuk semua mahasiswa
        System.out.println("Mahasiswa 1: Nama = " + namaMhs1 + ", NIM = " + nimMhs1 + ", Jurusan = " + jurusanMhs1 + ", IPK = " + ipkMhs1);
        System.out.println("Mahasiswa 2: Nama = " + namaMhs2 + ", NIM = " + nimMhs2 + ", Jurusan = " + jurusanMhs2 + ", IPK = " + ipkMhs2);
        System.out.println("Mahasiswa 3: Nama = " + namaMhs3 + ", NIM = " + nimMhs3 + ", Jurusan = " + jurusanMhs3 + ", IPK = " + ipkMhs3);

        System.out.println("\n=== MASALAH DENGAN CARA PROSEDURAL ===");
        // Tuliskan dalam komentar 3 masalah yang Anda lihat dari cara di atas
        // 1. Kode menjadi berulang dan sulit dikelola ketika jumlah mahasiswa bertambah.
        // 2. Tidak ada struktur yang jelas, sehingga mudah terjadi kesalahan dalam pengelolaan data.
        // 3. Sulit untuk menambahkan fitur baru seperti validasi atau operasi pada data mahasiswa.

        // ===== DENGAN OOP: CARA OBJECT-ORIENTED =====
        System.out.println("\n=== DENGAN OOP: CARA OBJECT-ORIENTED ===");

        // Latihan 2: Menggunakan class Mahasiswa (akan dibuat di bawah)
        // Buat 3 object Mahasiswa
        // Set data untuk setiap mahasiswa
        // Print menggunakan method dari class

        // Buat object mahasiswa dan isi datanya
        Mahasiswa mhs1 = new Mahasiswa("Alice", "12345", "Informatika", 3.5);
        Mahasiswa mhs2 = new Mahasiswa("Bob", "67890", "Sistem Informasi", 2.8);
        Mahasiswa mhs3 = new Mahasiswa("Charlie", "11111", "Teknik Elektro", 3.2);

        mhs1.tampilkanInfo();
        mhs2.tampilkanInfo();
        mhs3.tampilkanInfo();

        // ===== ANALOGI DUNIA NYATA =====
        System.out.println("\n=== ANALOGI DUNIA NYATA ===");

        // Latihan 3: Implementasi analogi perpustakaan
        // Buat beberapa object Buku
        // Buat object Mahasiswa yang meminjam buku
        // Simulasikan proses peminjaman

        // Implementasikan analogi perpustakaan
        Perpustakaan perpustakaan = new Perpustakaan();
        Buku buku1 = new Buku("Pemrograman Java", "John Doe", "ISBN123", 2020, true);
        Buku buku2 = new Buku("Struktur Data", "Jane Smith", "ISBN456", 2019, true);
        perpustakaan.tambahBuku(buku1);
        perpustakaan.tambahBuku(buku2);

        Mahasiswa peminjam = new Mahasiswa("Alice", "12345", "Informatika", 3.5);
        System.out.println("Simulasi peminjaman buku:");
        buku1.pinjam();
        buku1.kembalikan();

        // ===== KEUNTUNGAN OOP =====
        System.out.println("\n=== KEUNTUNGAN OOP ===");
        // Tuliskan dalam komentar 5 keuntungan OOP yang Anda rasakan
        // 1. Encapsulation: Data dan metode terkait dikelompokkan dalam satu unit (class), sehingga lebih aman dan terorganisir.
        // 2. Inheritance: Memungkinkan pewarisan sifat dari class induk ke anak, mengurangi duplikasi kode.
        // 3. Polymorphism: Objek dapat memiliki banyak bentuk, memudahkan penggunaan interface yang sama untuk class berbeda.
        // 4. Abstraction: Menyembunyikan detail implementasi, fokus pada apa yang dilakukan objek.
        // 5. Modularity: Kode lebih mudah dipelihara, diperbaiki, dan diperluas karena terbagi dalam modul-modul kecil.
    }
}

// ===== DEFINISI CLASS =====

// Buat class Mahasiswa dengan struktur berikut:
class Mahasiswa {
    // Instance variables
    // Definisikan instance variables untuk nama, nim, jurusan, ipk
    private String nama;
    private String nim;
    private String jurusan;
    private double ipk;

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
        System.out.println("Nama: " + nama + ", NIM: " + nim + ", Jurusan: " + jurusan + ", IPK: " + ipk);
    }

    // Buat method untuk mengecek apakah mahasiswa lulus (IPK >= 2.75)
    public boolean isLulus() {
        return ipk >= 2.75;
    }

    // Buat method untuk menghitung predikat berdasarkan IPK
    public String getPredikat() {
        if (ipk >= 3.5) {
            return "Cum Laude";
        } else if (ipk >= 3.0) {
            return "Sangat Memuaskan";
        } else if (ipk >= 2.75) {
            return "Memuaskan";
        } else {
            return "Tidak Lulus";
        }
    }
}

// Buat class Buku dengan struktur berikut:
class Buku {
    // Instance variables
    // Definisikan variables untuk judul, penulis, isbn, tahunTerbit, tersedia
    private String judul;
    private String penulis;
    private String isbn;
    private int tahunTerbit;
    private boolean tersedia;

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
        System.out.println("Judul: " + judul + ", Penulis: " + penulis + ", ISBN: " + isbn + ", Tahun Terbit: " + tahunTerbit + ", Tersedia: " + tersedia);
    }

    // Buat method untuk meminjam buku
    public void pinjam() {
        if (tersedia) {
            tersedia = false;
            System.out.println("Buku '" + judul + "' berhasil dipinjam.");
        } else {
            System.out.println("Buku '" + judul + "' tidak tersedia.");
        }
    }

    // Buat method untuk mengembalikan buku
    public void kembalikan() {
        tersedia = true;
        System.out.println("Buku '" + judul + "' telah dikembalikan.");
    }
}

// Buat class Perpustakaan yang mengelola buku dan peminjaman
class Perpustakaan {
    // Implementasikan class perpustakaan
    private java.util.ArrayList<Buku> bukuList = new java.util.ArrayList<>();

    public void tambahBuku(Buku buku) {
        bukuList.add(buku);
    }

    public void tampilkanBuku() {
        for (Buku b : bukuList) {
            b.tampilkanInfo();
        }
    }
}
