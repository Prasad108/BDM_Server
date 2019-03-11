package com.app.Reports;

public class TotalBDWithinDateRange {
    private int bookId;
    private String bookName;
    private String bookLanguage;
    private String bookType;
    private Long numberOfBooks;
    private Long totalSaleValue;

    public TotalBDWithinDateRange() {
    }

    public TotalBDWithinDateRange(int bookId, String bookName, String bookLanguage, String bookType, Long numberOfBooks, Long totalSaleValue) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookLanguage = bookLanguage;
        this.bookType = bookType;
        this.numberOfBooks = numberOfBooks;
        this.totalSaleValue = totalSaleValue;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookLanguage() {
        return bookLanguage;
    }

    public void setBookLanguage(String bookLanguage) {
        this.bookLanguage = bookLanguage;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public Long getNumberOfBooks() {
        return numberOfBooks;
    }

    public void setNumberOfBooks(Long numberOfBooks) {
        this.numberOfBooks = numberOfBooks;
    }

    public Long getTotalSaleValue() {
        return totalSaleValue;
    }

    public void setTotalSaleValue(Long totalSaleValue) {
        this.totalSaleValue = totalSaleValue;
    }
}
