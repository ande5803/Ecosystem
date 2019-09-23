package dk.bundgaard.anders.ecosystem.animal;

import java.util.Random;

public class Eagle extends Animal {

    private static final int WIDTH = 40;
    private static final int HEIGHT = 40;
    private static final int REPRODUCTION_AGE = 10;
    private static final int MAXIMUM_AGE = 40;
    private static final float MOVEMENT_SPEED = 45;
    private static final int MATERNITY_PERIOD = 5;
    private static final int NUTRITION_VALUE = 0; //Eagles cannot be eaten
    private static final int HUNGER_VALUE = 15;

    public Eagle(float positionX, float positionY) {
        super("eagle", positionX, positionY, WIDTH, HEIGHT);
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
        Eagle eagle = new Eagle(this.getRegionX(), this.getRegionY());
        eagle.setFemale(new Random().nextBoolean());
    }
}
