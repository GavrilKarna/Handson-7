package Task6;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Member {
    // Private instance variables
    // memberId, name, email, phone, address, joinDate, membershipType, isActive
    private final int memberId;
    private String name;
    private String email;
    private String phone;
    private String address;
    private final LocalDate joinDate;
    private MembershipType membershipType;
    private boolean isActive;

    // Static variables
    // static int totalMembers, static final int MAX_BOOKS_ALLOWED
    private static int totalMembers = 0;
    public static final int MAX_BOOKS_ALLOWED_DEFAULT = 3;

    // Constructors
    // Constructor dengan validation
    public Member(String name, String email, String phone, String address, MembershipType membershipType) {
        this.memberId = ++totalMembers;
        this.name = Objects.requireNonNull(name, "Nama tidak boleh null");
        if (!LibraryUtils.isValidEmail(email)) throw new IllegalArgumentException("Email tidak valid");
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.joinDate = LocalDate.now();
        this.membershipType = membershipType != null ? membershipType : MembershipType.PUBLIC;
        this.isActive = true;
    }

    // Convenience constructor used in main (without id)
    public Member(String name, String email, String phone, String address) {
        this(name, email, phone, address, MembershipType.PUBLIC);
    }

    // Getter/Setter
    // With appropriate validation
    public int getMemberId() { return memberId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = Objects.requireNonNull(name); }
    public String getEmail() { return email; }
    public void setEmail(String email) {
        if (!LibraryUtils.isValidEmail(email)) throw new IllegalArgumentException("Email tidak valid");
        this.email = email;
    }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public LocalDate getJoinDate() { return joinDate; }
    public MembershipType getMembershipType() { return membershipType; }
    public void setMembershipType(MembershipType membershipType) {
        if (membershipType != null) this.membershipType = membershipType;
    }
    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { this.isActive = active; }
    public static int getTotalMembers() { return totalMembers; }

    // Business methods
    // canBorrowMore(), calculateMembershipDuration(), upgradeMembership()
    public boolean canBorrowMore(int currentlyBorrowed, int maxAllowed) {
        return isActive && currentlyBorrowed < maxAllowed;
    }

    public int calculateMembershipDuration() {
        return Period.between(joinDate, LocalDate.now()).getYears();
    }

    public void upgradeMembership(MembershipType newType) {
        if (newType != null && newType != this.membershipType) {
            this.membershipType = newType;
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ISO_DATE;
        return String.format("Member[%d] %s | %s | %s | Joined: %s | Type: %s | Active: %s",
                memberId, name, email, phone, joinDate.format(fmt), membershipType, isActive);
    }
}
