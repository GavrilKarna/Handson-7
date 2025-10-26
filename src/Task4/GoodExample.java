package Task4;

// Implementasikan class GoodExample (dengan encapsulation)
class GoodExample {
    // Private instance variables
    // private String name, age, salary, email
    private String name;
    private int age;
    private double salary;
    private String email;

    // Constructor
    // Constructor dengan parameter validation
    public GoodExample(String name, int age, double salary, String email) {
        setName(name);
        setAge(age);
        setSalary(salary);
        setEmail(email);
    }

    // Getter methods
    // Implementasikan getter methods yang proper
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public String getEmail() {
        return email;
    }

    // Setter methods dengan validation
    // setName() - tidak boleh null/kosong
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("[ERROR] Nama tidak boleh kosong.");
        } else {
            this.name = name;
        }
    }

    // setAge() - harus antara 17-65
    public void setAge(int age) {
        if (age < 17 || age > 65) {
            System.out.println("[ERROR] Umur harus antara 17 dan 65 tahun.");
        } else {
            this.age = age;
        }
    }

    // setSalary() - harus positif
    public void setSalary(double salary) {
        if (salary <= 0) {
            System.out.println("[ERROR] Gaji harus positif.");
        } else {
            this.salary = salary;
        }
    }

    // setEmail() - format email yang valid
    public void setEmail(String email) {
        if (!validateEmail(email)) {
            System.out.println("[ERROR] Format email tidak valid.");
        } else {
            this.email = email;
        }
    }

    // Business methods
    // validateEmail(String email)
    private boolean validateEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }

    // isRetirementAge()
    public boolean isRetirementAge() {
        return age >= 60;
    }

    // calculateTax()
    public double calculateTax() {
        return salary * 0.1;
    }

    public void displayInfo() {
        System.out.println("Nama: " + name);
        System.out.println("Usia: " + age);
        System.out.println("Gaji: " + salary);
        System.out.println("Email: " + email);
        System.out.println("Pajak (10%): " + calculateTax());
        System.out.println("Sudah pensiun? " + (isRetirementAge() ? "Ya" : "Belum"));
    }
}
