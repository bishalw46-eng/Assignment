package Assignment2;

class Book {
    private String title;
    private String author;
    private String isbn;
    private String genre;
    private boolean isIssued;

    public Book(String title, String author, String isbn, String genre) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.genre = genre;
        this.isIssued = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void displayInfo() {
        System.out.println("Title: " + title + ", Author: " + author + ", ISBN: " + isbn
                + ", Genre: " + genre + ", Issued: " + isIssued);
    }

    public void markIssued() {
        isIssued = true;
    }

    public void markReturned() {
        isIssued = false;
    }
}
