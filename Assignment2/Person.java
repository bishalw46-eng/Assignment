package Assignment2;

class Person {
    private String name;
    private int age;
    private String email;
    private double salary;

    public Person(String name, int age, String email, double salary) {
        this.name = name;
        this.age = 0;
        this.email = "default@example.com";
        this.salary = 0;

        setAge(age);
        setEmail(email);
        setSalary(salary);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public double getSalary() {
        return salary;
    }

    public void setAge(int age) {
        if (age >= 0 && age <= 120) {
            this.age = age;
        } else {
            System.out.println("Invalid age. Old value preserved.");
        }
    }

    public void setEmail(String email) {
        if (email != null && email.contains("@")) {
            this.email = email;
        } else {
            System.out.println("Invalid email. Old value preserved.");
        }
    }

    public void setSalary(double salary) {
        if (salary >= 0) {
            this.salary = salary;
        } else {
            System.out.println("Invalid salary. Old value preserved.");
        }
    }
}
