package Assignment2;

import java.util.ArrayList;

public class Assignment2 {
    public static void main(String[] args) {
        runPart1();
        runPart2();
        runPart3();
        runPart4();
        runPart5();
        runPart6();
        runPart7();
        runPart8();
        runQuizScoreAnalyzer();
        runGroceryTracker();
    }

    private static void runPart1() {
        System.out.println("===== Part 1: Control Flow & Loops =====");

        System.out.println("Multiplication table for 7:");
        for (int i = 1; i <= 10; i++) {
            System.out.println("7 x " + i + " = " + (7 * i));
        }

        int number = 2;
        while ((number * number) <= 500) {
            number++;
        }
        System.out.println("First number whose square is greater than 500: " + number);

        System.out.println("Triangle pattern:");
        for (int row = 1; row <= 5; row++) {
            for (int col = 1; col <= row; col++) {
                System.out.print("*");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void runPart2() {
        System.out.println("===== Part 2: String Manipulation =====");
        StringTools stringTools = new StringTools();
        String sample = "A man a plan a canal Panama";
        String text = "Java is fun. Java is powerful. Learning Java is useful.";

        System.out.println("Vowel count: " + stringTools.countVowels(sample));
        System.out.println("Reverse: " + stringTools.reverseString("Core Java"));
        System.out.println("\"" + sample + "\" palindrome? " + stringTools.isPalindrome(sample));
        System.out.println("Occurrences of \"Java\": " + stringTools.countOccurrences(text, "Java"));
        System.out.println();
    }

    private static void runPart3() {
        System.out.println("===== Part 3: Bank Account =====");
        BankAccount account1 = new BankAccount("SB1001", "John", 10000.0, "Savings");
        BankAccount account2 = new BankAccount("CU2001", "Emily", 6000.0, "Current");

        account1.deposit(2000.0);
        account2.deposit(1000.0);
        account1.withdraw(1500.0);
        account1.transfer(account2, 2500.0);
        account2.withdraw(1200.0);
        account2.transfer(account1, 800.0);

        account1.displayStatement();
        account2.displayStatement();
        System.out.println();
    }

    private static void runPart4() {
        System.out.println("===== Part 4: Library System =====");
        Library library = new Library("City Central Library");

        library.addBook(new Book("Clean Code", "Robert C. Martin", "ISBN001", "Programming"));
        library.addBook(new Book("Effective Java", "Joshua Bloch", "ISBN002", "Programming"));
        library.addBook(new Book("The Alchemist", "Paulo Coelho", "ISBN003", "Fiction"));
        library.addBook(new Book("1984", "George Orwell", "ISBN004", "Dystopian"));
        library.addBook(new Book("Wings of Fire", "A. P. J. Abdul Kalam", "ISBN005", "Biography"));
        library.addBook(new Book("The Hobbit", "J. R. R. Tolkien", "ISBN006", "Fantasy"));

        library.issueBook("Clean Code");
        library.issueBook("1984");
        library.returnBook("1984");
        library.findByTitle("The Hobbit");
        library.findByAuthor("Joshua Bloch");

        System.out.println("Available books: " + library.countAvailable());
        library.displayAllBooks();
        System.out.println();
    }

    private static void runPart5() {
        System.out.println("===== Part 5: Encapsulation =====");
        Person person = new Person("Daniel", 25, "daniel@gmail.com", 55000.0);

        System.out.println("Initial person details:");
        displayPerson(person);

        person.setAge(-5);
        person.setSalary(-1000.0);
        person.setEmail("notvalid");

        System.out.println("After trying invalid updates:");
        displayPerson(person);
        System.out.println();
    }

    private static void runPart6() {
        System.out.println("===== Part 6: Inheritance & Method Overriding =====");
        Dog dog = new Dog("Bruno", "Woof", 4, "Labrador");
        Bird bird = new Bird("Tweety", "Chirp", 2, true);
        Fish fish = new Fish("Nemo", "Blub", 0, "Salt");
        Animal animal = new Animal("Leo", "Roar", 4);

        dog.speak();
        bird.speak();
        fish.speak();
        animal.speak();
        bird.fly();
        fish.swim();

        Animal[] animals = {dog, bird, fish, animal};
        System.out.println("Animal array polymorphism:");
        for (Animal current : animals) {
            current.speak();
        }
        System.out.println();
    }

    private static void runPart7() {
        System.out.println("===== Part 7: Polymorphism - Shapes =====");
        Shape[] shapes = {
                new Circle("Red", 7.0),
                new Rectangle("Blue", 8.0, 5.0),
                new Triangle("Green", 6.0, 4.0, 5.0, 5.0, 6.0),
                new Square("Yellow", 4.0)
        };

        for (Shape shape : shapes) {
            shape.displayInfo();
        }
        System.out.println();
    }

    private static void runPart8() {
        System.out.println("===== Part 8: Abstract Classes - Payment Processing =====");
        Payment[] payments = {
                new CashPayment(1500.0, "John"),
                new CardPayment(3200.0, "Emma", "1234567812345678", "City Bank"),
                new UPIPayment(875.0, "Michael", "michael.pay@gmail.com"),
                new EMIPayment(24000.0, "Sophia", 12)
        };

        for (Payment payment : payments) {
            payment.processPayment();
            payment.displayReceipt();
            System.out.println();
        }

        // Payment p = new Payment();
        // This causes a compile time error because Payment is an abstract class
        // and abstract classes cannot be instantiated directly.
    }

    private static void runQuizScoreAnalyzer() {
        System.out.println("===== Fixed-Size Array: Quiz Score Analyzer =====");
        int[] quizScores = {85, 92, 78, 90, 88, 76, 95, 89};

        int highest = quizScores[0];
        int lowest = quizScores[0];
        int total = 0;

        for (int score : quizScores) {
            if (score > highest) {
                highest = score;
            }
            if (score < lowest) {
                lowest = score;
            }
            total += score;
        }

        double average = total / (double) quizScores.length;
        System.out.println("Highest score: " + highest);
        System.out.println("Lowest score: " + lowest);
        System.out.println("Average score: " + average);
        System.out.println();
    }

    private static void runGroceryTracker() {
        System.out.println("===== Dynamic ArrayList: Grocery Tracker =====");
        ArrayList<String> groceryList = new ArrayList<>();
        groceryList.add("Apples");
        groceryList.add("Bread");
        groceryList.add("Milk");
        groceryList.add("Eggs");
        groceryList.add("Coffee");

        groceryList.remove("Bread");

        if (groceryList.contains("Milk")) {
            System.out.println("Milk is on the list!");
        } else {
            System.out.println("Milk is not on the list.");
        }

        System.out.println("Total items: " + groceryList.size());
        System.out.println("Items in grocery list:");
        for (String item : groceryList) {
            System.out.println(item);
        }
        System.out.println();
    }

    private static void displayPerson(Person person) {
        System.out.println("Name: " + person.getName());
        System.out.println("Age: " + person.getAge());
        System.out.println("Email: " + person.getEmail());
        System.out.println("Salary: " + person.getSalary());
    }
}
