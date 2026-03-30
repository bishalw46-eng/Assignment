package Assignment2;

class Bird extends Animal {
    private boolean canFly;

    public Bird(String name, String sound, int legs, boolean canFly) {
        super(name, sound, legs);
        this.canFly = canFly;
    }

    @Override
    public void speak() {
        System.out.println(name + " says " + sound + " with fluttering wings");
    }

    public void fly() {
        if (canFly) {
            System.out.println(name + " is flying.");
        } else {
            System.out.println(name + " cannot fly.");
        }
    }
}
