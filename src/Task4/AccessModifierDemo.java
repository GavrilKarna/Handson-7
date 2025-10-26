package Task4;

// Implementasikan class AccessModifierDemo
class AccessModifierDemo {
    private String privateField = "Private";
    protected String protectedField = "Protected";
    String defaultField = "Default";
    public String publicField = "Public";

    private void privateMethod() {
        System.out.println("Private method called");
    }

    protected void protectedMethod() {
        System.out.println("Protected method called");
    }

    void defaultMethod() {
        System.out.println("Default method called");
    }

    public void publicMethod() {
        System.out.println("Public method called");
    }

    // Method untuk test accessibility dari dalam class
    public void testAccess() {
        // Panggil semua methods dan akses semua field
        System.out.println("=== Testing Access dari dalam class ===");
        System.out.println("Private field: " + privateField);
        System.out.println("Protected field: " + protectedField);
        System.out.println("Default field: " + defaultField);
        System.out.println("Public field: " + publicField);

        privateMethod();
        protectedMethod();
        defaultMethod();
        publicMethod();
    }
}
