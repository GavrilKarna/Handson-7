package Task5;

class Student {
    // Static variables
    // static String universityName
    // static int totalStudents
    static String universityName;
    static int totalStudents = 0;

    // Instance variables
    // String studentId, name, major
    // double gpa
    String studentId, name, major;
    double gpa;

    // Constructor
    // Increment totalStudents
    public Student(String studentId, String name, String major, double gpa) {
        this.studentId = studentId;
        this.name = name;
        this.major = major;
        this.gpa = gpa;
        totalStudents++;
    }

    // Static methods
    // static int getTotalStudents()
    // static void setUniversityName(String name)
    // static String getUniversityName()
    public static int getTotalStudents() {
        return totalStudents;
    }

    public static void setUniversityName(String name) {
        universityName = name;
    }

    public static String getUniversityName() {
        return universityName;
    }

    // Instance methods
    // void updateGPA(double newGPA)
    // void displayStudentInfo()
    // boolean isHonorStudent()
    public void updateGPA(double newGPA) {
        gpa = newGPA;
    }

    public void displayStudentInfo() {
        System.out.println("[" + studentId + "] " + name + " (" + major + ") GPA: " + gpa +
                " | Universitas: " + universityName + " | Honor: " + (isHonorStudent() ? "Ya" : "Tidak"));
    }

    public boolean isHonorStudent() {
        return gpa >= 3.7;
    }
}
