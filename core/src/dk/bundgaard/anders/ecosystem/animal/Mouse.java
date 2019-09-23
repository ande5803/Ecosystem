package dk.bundgaard.anders.ecosystem.animal;

import java.util.Random;

public class Mouse extends Animal {
    private static final int WIDTH = 20;
    private static final int HEIGHT = 10;
    private static final int REPRODUCTION_AGE = 3;
    private static final int MAXIMUM_AGE = 50;
    private static final int MOVEMENT_SPEED = 60;
    private static final int MATERNITY_PERIOD = 2;
    private static final int NUTRITION_VALUE = 1;

    public Mouse(float positionX, float positionY) {
        super("mouse", positionX, positionY, WIDTH, HEIGHT);
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
        Mouse mouse = new Mouse(this.getX(), this.getY());
        mouse.setFemale(new Random().nextBoolean());
    }
}
