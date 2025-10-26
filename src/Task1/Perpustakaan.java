package Task1;

import java.util.ArrayList;

// Buat class Perpustakaan yang mengelola buku dan peminjaman
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
