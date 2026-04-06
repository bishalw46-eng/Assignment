package Assignment3;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Assignment3 {
    public static void main(String[] args) {
        runArrayListDemo();
        System.out.println();

        runHashMapDemo();
        System.out.println();

        runHashSetDemo();
        System.out.println();

        runTryCatchFinallyDemo();
        System.out.println();

        runThrowThrowsDemo();
        System.out.println();

        runThreadingDemo();
        System.out.println();

        runSynchronizationDemo();
        System.out.println();

        runLambdaDemo();
        System.out.println();

        runFunctionalInterfacesDemo();
        System.out.println();

        runStreamApiDemo();
    }

    private static void runArrayListDemo() {
        ArrayList<String> tasks = new ArrayList<>();
        tasks.add("Buy groceries");
        tasks.add("Read a book");
        tasks.add("Exercise");
        tasks.add("Call mom");
        tasks.add("Pay bills");

        int taskNumber = 1;
        for (String task : tasks) {
            System.out.println(taskNumber + ". " + task);
            taskNumber++;
        }

        tasks.remove("Exercise");
        tasks.remove(0);

        boolean payBillsPresent = tasks.contains("Pay bills");

        tasks.add(1, "Study Java");

        System.out.println("After removals and additions:");
        taskNumber = 1;
        for (String task : tasks) {
            System.out.println(taskNumber + ". " + task);
            taskNumber++;
        }

        System.out.println("Pay bills present: " + payBillsPresent);
        System.out.println("List size: " + tasks.size());
    }

    private static void runHashMapDemo() {
        HashMap<String, Integer> markSheet = new LinkedHashMap<>();
        markSheet.put("Alice", 88);
        markSheet.put("Bob", 74);
        markSheet.put("Carol", 95);
        markSheet.put("David", 60);
        markSheet.put("Eva", 82);

        System.out.println("Bob's marks: " + markSheet.get("Bob"));
        System.out.println("Frank's marks: " + markSheet.getOrDefault("Frank", 0));

        markSheet.put("David", 75);

        System.out.println("Carol present: " + markSheet.containsKey("Carol"));
        for (Map.Entry<String, Integer> entry : markSheet.entrySet()) {
            System.out.println("Name: " + entry.getKey() + " Marks: " + entry.getValue());
        }
        System.out.println("Total entries: " + markSheet.size());
    }

    private static void runHashSetDemo() {
        HashSet<String> registeredEmails = new LinkedHashSet<>();
        registeredEmails.add("alice@mail.com");
        registeredEmails.add("bob@mail.com");
        registeredEmails.add("carol@mail.com");
        registeredEmails.add("alice@mail.com");
        registeredEmails.add("david@mail.com");

        System.out.println("Set size: " + registeredEmails.size());

        register(registeredEmails, "bob@mail.com");
        if (register(registeredEmails, "eve@mail.com")) {
            System.out.println("eve@mail.com registered successfully.");
        }

        System.out.println("carol@mail.com exists: " + registeredEmails.contains("carol@mail.com"));
        System.out.println("All emails:");
        for (String email : registeredEmails) {
            System.out.println(email);
        }
    }

    public static boolean register(HashSet<String> set, String email) {
        if (set.contains(email)) {
            System.out.println("Already registered: " + email);
            return false;
        }

        set.add(email);
        return true;
    }

    private static void runTryCatchFinallyDemo() {
        System.out.println("Result: " + divide(10, 2));
        System.out.println("Result: " + divide(5, 0));

        try {
            Integer.parseInt("abc");
        } catch (NumberFormatException exception) {
            System.out.println("Error: Invalid number format.");
        }
    }

    public static double divide(int a, int b) {
        try {
            return (double) (a / b);
        } catch (ArithmeticException exception) {
            System.out.println("Error: Cannot divide by zero.");
            return 0.0;
        } finally {
            System.out.println("--- Operation complete ---");
        }
    }

    private static void runThrowThrowsDemo() {
        String[] names = {"Alice", "Bob", "Carol"};
        int[] ages = {22, 16, 18};

        for (int i = 0; i < names.length; i++) {
            try {
                registerVoter(names[i], ages[i]);
            } catch (UnderAgeException exception) {
                System.out.println("UnderAgeException: " + exception.getMessage());
            }
        }

        System.out.println("Registration process completed.");
    }

    public static void registerVoter(String name, int age) throws UnderAgeException {
        if (age >= 18) {
            System.out.println("Registered: " + name);
            return;
        }

        throw new UnderAgeException(name + " is too young. Age: " + age);
    }

    private static void runThreadingDemo() {
        Thread bbcThread = new Thread(new NewsDownloader("BBC", 2));
        bbcThread.setName("BBC-Thread");

        Thread cnnThread = new Thread(new NewsDownloader("CNN", 1));
        cnnThread.setName("CNN-Thread");

        Thread reutersThread = new Thread(new NewsDownloader("Reuters", 3));
        reutersThread.setName("Reuters-Thread");

        bbcThread.start();
        cnnThread.start();
        reutersThread.start();

        try {
            bbcThread.join();
            cnnThread.join();
            reutersThread.join();
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            System.out.println("Main thread interrupted while waiting for downloads.");
        }

        System.out.println("All articles downloaded. App is ready!");
    }

    private static void runSynchronizationDemo() {
        TicketCounter ticketCounter = new TicketCounter();

        Thread counterA = new Thread(() -> runBookingLoop(ticketCounter, "Counter-A"));
        Thread counterB = new Thread(() -> runBookingLoop(ticketCounter, "Counter-B"));
        Thread counterC = new Thread(() -> runBookingLoop(ticketCounter, "Counter-C"));

        counterA.start();
        counterB.start();
        counterC.start();

        try {
            counterA.join();
            counterB.join();
            counterC.join();
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            System.out.println("Booking threads were interrupted.");
        }

        System.out.println("Booking closed. Seats remaining: " + ticketCounter.getAvailableSeats());
    }

    private static void runBookingLoop(TicketCounter ticketCounter, String counterName) {
        for (int i = 0; i < 4; i++) {
            ticketCounter.bookSeat(counterName);
            try {
                Thread.sleep(50);
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    private static void runLambdaDemo() {
        ArrayList<String> products = new ArrayList<>(Arrays.asList(
                "Laptop", "Phone", "Tablet", "Monitor", "Keyboard", "Mouse", "Headphones"
        ));

        ArrayList<String> alphabeticalProducts = new ArrayList<>(products);
        alphabeticalProducts.sort((first, second) -> first.compareTo(second));
        System.out.println("Alphabetical: " + alphabeticalProducts);

        ArrayList<String> productsByLength = new ArrayList<>(products);
        productsByLength.sort((first, second) -> Integer.compare(first.length(), second.length()));
        System.out.println("By length: " + productsByLength);

        Runnable printTask = () -> System.out.println("Processing product list...");
        Thread printThread = new Thread(printTask);
        printThread.start();
        try {
            printThread.join();
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            System.out.println("Print task interrupted.");
        }

        productsByLength.removeIf(product -> product.length() < 6);
        System.out.println("After removeIf (length < 6): " + productsByLength);
    }

    private static void runFunctionalInterfacesDemo() {
        Predicate<Integer> isEven = number -> number % 2 == 0;
        Predicate<Integer> isGreaterThan50 = number -> number > 50;

        System.out.println("48 is even: " + isEven.test(48));
        System.out.println("48 > 50: " + isGreaterThan50.test(48));
        System.out.println("72 is even: " + isEven.test(72));
        System.out.println("72 > 50: " + isGreaterThan50.test(72));
        System.out.println("72 is even AND > 50: " + isEven.and(isGreaterThan50).test(72));

        Function<String, String> addGreeting = name -> "Hello, " + name + "!";
        System.out.println(addGreeting.apply("Alice"));
        System.out.println(addGreeting.apply("Bob"));

        Consumer<String> printUpperCase = text -> System.out.println(text.toUpperCase());
        List<String> words = Arrays.asList("java", "streams", "lambda");
        words.forEach(printUpperCase);

        Supplier<Double> randomScore = () -> Math.random() * 100;
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        for (int i = 1; i <= 3; i++) {
            System.out.println("Score " + i + ": " + decimalFormat.format(randomScore.get()));
        }
    }

    private static void runStreamApiDemo() {
        List<Integer> salaries = Arrays.asList(25000, 42000, 15000, 68000, 31000, 72000, 19000, 55000);

        List<Integer> ascending = salaries.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("Ascending: " + ascending);

        List<Integer> descending = salaries.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        System.out.println("Descending: " + descending);

        int totalSalary = salaries.stream().reduce(0, Integer::sum);
        System.out.println("Total salary: " + totalSalary);

        long countAbove40000 = salaries.stream()
                .filter(salary -> salary > 40000)
                .count();
        System.out.println("Count above 40000: " + countAbove40000);

        int firstAbove60000 = salaries.stream()
                .filter(salary -> salary > 60000)
                .findFirst()
                .orElse(-1);
        System.out.println("First salary > 60000: " + firstAbove60000);

        double averageSalary = salaries.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0);
        System.out.println("Average salary: " + averageSalary);
    }
}
