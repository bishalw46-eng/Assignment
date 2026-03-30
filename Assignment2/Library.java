package Assignment2;

class Library {
    private String name;
    private Book[] books;
    private int bookCount;

    public Library(String name) {
        this.name = name;
        this.books = new Book[20];
        this.bookCount = 0;
    }

    public void addBook(Book b) {
        if (bookCount >= books.length) {
            System.out.println("Library is full. Cannot add more books.");
            return;
        }
        books[bookCount] = b;
        bookCount++;
    }

    public Book findByTitle(String title) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getTitle().equalsIgnoreCase(title)) {
                System.out.println("Book found in " + name + ":");
                books[i].displayInfo();
                return books[i];
            }
        }
        System.out.println("Not found");
        return null;
    }

    public void findByAuthor(String author) {
        boolean found = false;
        System.out.println("Books by " + author + ":");
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getAuthor().equalsIgnoreCase(author)) {
                books[i].displayInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books found for this author.");
        }
    }

    public void issueBook(String title) {
        Book book = findBook(title);
        if (book == null) {
            System.out.println("Book not found for issuing.");
            return;
        }
        if (book.isIssued()) {
            System.out.println("Book is already issued.");
            return;
        }
        book.markIssued();
        System.out.println(title + " issued successfully.");
    }

    public void returnBook(String title) {
        Book book = findBook(title);
        if (book == null) {
            System.out.println("Book not found for return.");
            return;
        }
        if (!book.isIssued()) {
            System.out.println("Book was not issued.");
            return;
        }
        book.markReturned();
        System.out.println(title + " returned successfully.");
    }

    public void displayAllBooks() {
        System.out.println("All books in " + name + ":");
        for (int i = 0; i < bookCount; i++) {
            books[i].displayInfo();
        }
    }

    public int countAvailable() {
        int available = 0;
        for (int i = 0; i < bookCount; i++) {
            if (!books[i].isIssued()) {
                available++;
            }
        }
        return available;
    }

    private Book findBook(String title) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getTitle().equalsIgnoreCase(title)) {
                return books[i];
            }
        }
        return null;
    }
}
