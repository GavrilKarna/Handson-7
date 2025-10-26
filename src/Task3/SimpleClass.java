package Task3;

// Implementasikan class SimpleClass (tanpa custom constructor)
class SimpleClass {
    // Hanya instance variables, tanpa constructor
    String message = "Default constructor aktif";

    public SimpleClass() {
        System.out.println("Default constructor dipanggil (SimpleClass)");
    }

    public SimpleClass(String message) {
        this.message = "Custom constructor: " + message;
    }
}
