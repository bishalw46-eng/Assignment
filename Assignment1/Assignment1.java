package Assignment1;

public class Assignment1 {
    public static void main(String[] args) {
        System.out.println("----- ESCAPE SEQUENCES -----");
        System.out.println("Newline example:\nThis sentence starts on a new line.");
        System.out.println("Tab example:\tThe value is separated by a tab.");
        System.out.println("Backslash example: Save the file in C:\\Users\\Student\\Documents");
        System.out.println("Double quote example: The teacher said, \"Practice Java every day.\"");
        System.out.println("Single quote example: It's important to review today's lesson.");
        System.out.println();

        System.out.println("---- RECEIPT ----");
        System.out.println("Item\t\t\tPrice");
        System.out.println("Coffee\t\t\tRs. 50");
        System.out.println("Sandwich\t\tRs. 120");
        System.out.println();

        System.out.println("----- TEMPERATURE CONVERTER -----");
        double celsius = 25.0;
        double fahrenheit = (celsius * 9 / 5) + 32;
        double kelvin = celsius + 273.15;
        System.out.println("Celsius: " + celsius + " C");
        System.out.println("Fahrenheit: " + fahrenheit + " F");
        System.out.println("Kelvin: " + kelvin + " K");
        System.out.println();

        System.out.println("----- SHOPPING BILL -----");
        final double RICE_5KG = 250.0;
        final double OIL_1L = 180.0;
        final double BREAD = 45.0;
        final double MILK_2L = 90.0;
        final double EGGS_12PC = 84.0;

        double subtotal = RICE_5KG + OIL_1L + BREAD + MILK_2L + EGGS_12PC;
        double gst = subtotal > 500 ? subtotal * 0.05 : 0;
        double total = subtotal;
        total += gst;
        double discount = total > 600 ? total * 0.10 : 0;
        total -= discount;

        double displaySubtotal = Math.round(subtotal * 100.0) / 100.0;
        double displayGst = Math.round(gst * 100.0) / 100.0;
        double displayDiscount = Math.round(discount * 100.0) / 100.0;
        double displayTotal = Math.round(total * 100.0) / 100.0;

        System.out.println("Rice 5kg: Rs. " + RICE_5KG);
        System.out.println("Oil 1L: Rs. " + OIL_1L);
        System.out.println("Bread: Rs. " + BREAD);
        System.out.println("Milk 2L: Rs. " + MILK_2L);
        System.out.println("Eggs 12pc: Rs. " + EGGS_12PC);
        System.out.println("Subtotal: Rs. " + displaySubtotal);
        System.out.println("GST (5%): Rs. " + displayGst);
        System.out.println("Discount: Rs. " + displayDiscount);
        System.out.println("TOTAL: Rs. " + displayTotal);
        System.out.println();

        System.out.println("----- NAME PROCESSING -----");
        String fullName = "  alice marie johnson  ";
        String trimmedName = fullName.trim();

        int firstSpace = trimmedName.indexOf(" ");
        int secondSpace = trimmedName.indexOf(" ", firstSpace + 1);

        String rawFirst = trimmedName.substring(0, firstSpace);
        String rawMiddle = trimmedName.substring(firstSpace + 1, secondSpace);
        String rawLast = trimmedName.substring(secondSpace + 1);

        String firstName = rawFirst.substring(0, 1).toUpperCase() + rawFirst.substring(1);
        String middleName = rawMiddle.substring(0, 1).toUpperCase() + rawMiddle.substring(1);
        String lastName = rawLast.substring(0, 1).toUpperCase() + rawLast.substring(1);
        String cleanedName = firstName + " " + middleName + " " + lastName;

        int vowelCount = 0;
        String lowerName = cleanedName.toLowerCase();
        for (int i = 0; i < lowerName.length(); i++) {
            char ch = lowerName.charAt(i);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                vowelCount++;
            }
        }

        String reversedName = "";
        for (int i = cleanedName.length() - 1; i >= 0; i--) {
            reversedName += cleanedName.charAt(i);
        }

        System.out.println("Cleaned: " + cleanedName);
        System.out.println("First: " + firstName + "  Middle: " + middleName + "  Last: " + lastName);
        System.out.println("Vowel count: " + vowelCount);
        System.out.println("Reversed: " + reversedName);
        System.out.println();

        System.out.println("----- TRAFFIC LIGHT -----");
        String light = "Red"; // Change to Yellow or Green to test other cases.
        String action;

        switch (light) {
            case "Red":
                action = "STOP - Do not proceed.";
                break;
            case "Yellow":
                action = "CAUTION - Prepare to stop.";
                break;
            case "Green":
                action = "GO - Proceed safely.";
                break;
            default:
                action = "UNKNOWN signal - treat as Red.";
                break;
        }

        boolean safeToGo = light.equals("Green") ? true : false;
        System.out.println("Light: " + light);
        System.out.println("Action: " + action);
        System.out.println("Safe to go: " + safeToGo);
        System.out.println();

        System.out.println("----- REPORT CARD -----");
        int maths = 88;
        int science = 76;
        int english = 92;
        int history = 68;
        int javaProgramming = 95;

        int totalMarks = maths + science + english + history + javaProgramming;
        double percentage = totalMarks / 5.0;

        String grade;
        if (percentage >= 90) {
            grade = "A+";
        } else if (percentage >= 80) {
            grade = "A";
        } else if (percentage >= 70) {
            grade = "B";
        } else if (percentage >= 60) {
            grade = "C";
        } else if (percentage >= 50) {
            grade = "D";
        } else {
            grade = "F";
        }

        boolean passed = maths >= 40 && science >= 40 && english >= 40 && history >= 40 && javaProgramming >= 40;
        String status = passed ? "PASS" : "FAIL";
        String scholarship = percentage >= 85 && passed
                ? "Eligible for Merit Scholarship"
                : "Not Eligible (below 85%)";

        int highestMark = maths;
        String highestSubject = "Maths";
        if (science > highestMark) {
            highestMark = science;
            highestSubject = "Science";
        }
        if (english > highestMark) {
            highestMark = english;
            highestSubject = "English";
        }
        if (history > highestMark) {
            highestMark = history;
            highestSubject = "History";
        }
        if (javaProgramming > highestMark) {
            highestMark = javaProgramming;
            highestSubject = "Java Programming";
        }

        int lowestMark = maths;
        String lowestSubject = "Maths";
        if (science < lowestMark) {
            lowestMark = science;
            lowestSubject = "Science";
        }
        if (english < lowestMark) {
            lowestMark = english;
            lowestSubject = "English";
        }
        if (history < lowestMark) {
            lowestMark = history;
            lowestSubject = "History";
        }
        if (javaProgramming < lowestMark) {
            lowestMark = javaProgramming;
            lowestSubject = "Java Programming";
        }

        System.out.println("Maths: " + maths + "  Science: " + science + "  English: " + english
                + "  History: " + history + "  Java: " + javaProgramming);
        System.out.println("Total: " + totalMarks + "  Percentage: " + percentage + "%");
        System.out.println("Grade: " + grade + "  Status: " + status);
        System.out.println("Highest: " + highestSubject + " (" + highestMark + ")");
        System.out.println("Lowest: " + lowestSubject + " (" + lowestMark + ")");
        System.out.println("Scholarship: " + scholarship);
    }
}
