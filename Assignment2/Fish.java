package Assignment2;

class Fish extends Animal {
    private String waterType;

    public Fish(String name, String sound, int legs, String waterType) {
        super(name, sound, legs);
        this.waterType = waterType;
    }

    public void swim() {
        System.out.println(name + " is swimming in " + waterType.toLowerCase() + " water.");
    }
}
