package Assignment2;

class Shape {
    protected String color;

    public Shape(String color) {
        this.color = color;
    }

    public double area() {
        return 0;
    }

    public double perimeter() {
        return 0;
    }

    public void displayInfo() {
        System.out.println("Shape color: " + color);
        System.out.println("Area: " + area());
        System.out.println("Perimeter: " + perimeter());
        System.out.println("--------------------------------------");
    }
}
