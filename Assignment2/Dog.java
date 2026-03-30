package Assignment2;

class Dog extends Animal {
    private String breed;

    public Dog(String name, String sound, int legs, String breed) {
        super(name, sound, legs);
        this.breed = breed;
    }

    @Override
    public void speak() {
        System.out.println(name + " says " + sound + " and wags tail");
    }

    public String getBreed() {
        return breed;
    }
}
