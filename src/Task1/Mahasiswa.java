package Task1;

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
