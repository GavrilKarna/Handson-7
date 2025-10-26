package Task6;

import java.time.LocalDate;

public class BorrowRecord {
    // Private variables
    // recordId, memberId, isbn, borrowDate, dueDate, returnDate, fine
    private final int recordId;
    private final int memberId;
    private final String isbn;
    private final LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private double fine;

    // Static variables
    // static int totalRecords
    private static int totalRecords = 0;

    // Constructor
    // Constructor dengan auto-calculation due date
    public BorrowRecord(int memberId, String isbn, LocalDate borrowDate, int loanPeriodDays) {
        this.recordId = ++totalRecords;
        this.memberId = memberId;
        this.isbn = isbn;
        this.borrowDate = borrowDate != null ? borrowDate : LocalDate.now();
        this.dueDate = this.borrowDate.plusDays(Math.max(1, loanPeriodDays));
        this.returnDate = null;
        this.fine = 0.0;
    }

    public int getRecordId() { return recordId; }
    public int getMemberId() { return memberId; }
    public String getIsbn() { return isbn; }
    public LocalDate getBorrowDate() { return borrowDate; }
    public LocalDate getDueDate() { return dueDate; }
    public LocalDate getReturnDate() { return returnDate; }
    public double getFine() { return fine; }
    public static int getTotalRecords() { return totalRecords; }

    public boolean isOverdue() {
        LocalDate checkDate = (returnDate != null) ? returnDate : LocalDate.now();
        return checkDate.isAfter(dueDate);
    }

    // Methods
    // calculateFine(), isOverdue(), returnBook(), extendLoan()
    public double calculateFine() {
        LocalDate effective = (returnDate != null) ? returnDate : LocalDate.now();
        if (!effective.isAfter(dueDate)) {
            fine = 0;
            return fine;
        }
        long daysLate = java.time.temporal.ChronoUnit.DAYS.between(dueDate, effective);
        fine = Library.FINE_PER_DAY * daysLate;
        return fine;
    }

    public void returnBook(LocalDate returnDate) {
        if (this.returnDate != null) {
            return; // already returned
        }
        this.returnDate = returnDate != null ? returnDate : LocalDate.now();
        calculateFine();
    }

    public boolean extendLoan(int extraDays, int maxExtendDays) {
        if (extraDays <= 0) return false;
        if (extraDays > maxExtendDays) return false;
        if (isOverdue()) return false;
        this.dueDate = this.dueDate.plusDays(extraDays);
        return true;
    }

    @Override
    public String toString() {
        return String.format("Record[%d] Member:%d ISBN:%s Borrow:%s Due:%s Return:%s Fine:%.2f",
                recordId, memberId, isbn, borrowDate, dueDate, returnDate, fine);
    }
}
