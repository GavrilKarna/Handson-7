package Task4;

// Implementasikan class BadExample (dengan public variables)
class BadExample {
    // Semua variables public - tunjukkan masalahnya
    public String name;
    public int age;
    public double salary;
    public String email;

    // Constructor sederhana
    public BadExample(String name, int age, double salary, String email) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.email = email;
    }

    // Method untuk menunjukkan data corruption
    public void showCorruptedData() {
        System.out.println("Data setelah dirusak:");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Salary: " + salary);
        System.out.println("Email: " + email);
    }
}
