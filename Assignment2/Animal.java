package Assignment2;

class Animal {
    protected String name;
    protected String sound;
    protected int legs;

    public Animal(String name, String sound, int legs) {
        this.name = name;
        this.sound = sound;
        this.legs = legs;
    }

    public void speak() {
        System.out.println(name + " says " + sound);
    }
}
