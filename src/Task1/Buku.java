package Task1;

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
