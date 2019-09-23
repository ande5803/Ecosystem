package dk.bundgaard.anders.ecosystem.animal;

import java.util.Random;

public class Fox extends Animal {

    private static final int WIDTH = 50;
    private static final int HEIGHT = 30;
    private static final int REPRODUCTION_AGE = 10;
    private static final int MAXIMUM_AGE = 100;
    private static final float MOVEMENT_SPEED = 50;
    private static final int MATERNITY_PERIOD = 3;
    private static final int NUTRITION_VALUE = 6;
    private static final int HUNGER_VALUE = 50;

    public Fox(float positionX, float positionY) {
        super("fox", positionX, positionY, WIDTH, HEIGHT);
        setHungerValue(HUNGER_VALUE);
    }

    @Override
    public int getReproductionAge() {
        return REPRODUCTION_AGE;
    }

    @Override
    public int getMaximumAge() {
        return MAXIMUM_AGE;
    }

    @Override
    public float getMovementSpeed() {
        return MOVEMENT_SPEED;
    }

    @Override
    public int getNutritionValue() {
        return NUTRITION_VALUE;
    }

    @Override
    public int getMaternityPeriod() {
        return MATERNITY_PERIOD;
    }

    @Override
    void birth() {
        Fox fox = new Fox(this.getX(), this.getY());
        fox.setFemale(new Random().nextBoolean());
    }
}
