package dk.bundgaard.anders.ecosystem.animal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import dk.bundgaard.anders.ecosystem.Ecosystem;


public abstract class Animal extends Sprite {
    private boolean dead = false;
    private int currentAge = 0;
    private boolean female;
    private boolean canReproduce = false;
    private boolean pregnant = false;
    private int maternityPeriodLeft;
    private Animal target = null;
    private int hungerValue = Integer.MAX_VALUE;
    private int eatingPause = 3;
    private int eatingPauseLeft = 0;

    Animal(String textureName, float positionX, float positionY, int width, int height) {
        super(
            new Texture(Gdx.files.internal(textureName + ".png"))
        );
        setSize(width, height);
        setPosition(positionX, positionY);
        Ecosystem.getInstance().animals.add(this);
    }

    public abstract int getReproductionAge();
    public abstract int getMaximumAge();
    public abstract int getMaternityPeriod();
    public abstract float getMovementSpeed();
    public abstract int getNutritionValue();
    abstract void birth();

    public boolean isDead() {
        return dead;
    }

    public void kill() {
        this.dead = true;
    }

    public int getCurrentAge() {
        return currentAge;
    }

    public void setCurrentAge(int currentAge) {
        this.currentAge = currentAge;
    }

    public boolean isFemale() {
        return female;
    }

    public void setFemale(boolean female) {
        this.female = female;
    }

    public boolean canReproduce() {
        return canReproduce;
    }

    public void setCanReproduce(boolean canReproduce) {
        this.canReproduce = canReproduce;
    }

    public int getMaternityPeriodLeft() {
        return maternityPeriodLeft;
    }

    public void setMaternityPeriodLeft(int maternityPeriodLeft) {
        this.maternityPeriodLeft = maternityPeriodLeft;
    }

    public boolean isPregnant() {
        return pregnant;
    }

    public void decrementEatingPauseLeft() {
        if (eatingPauseLeft > 0) eatingPauseLeft--;
    }

    public boolean canEat() {
        return eatingPauseLeft <= 0;
    }

    public int getHungerValue() {
        return hungerValue;
    }

    public void setHungerValue(int hungerValue) {
        this.hungerValue = hungerValue;
    }

    public void giveBirth() {
        pregnant = false;
        canReproduce = true;
        maternityPeriodLeft = 0;
        birth();
    }

    public Animal getTarget() {
        return target;
    }
    public void resetTarget() {
        this.target = null;
    }

    /**
     * Chases or flees from a target Animal.
     * @param target the target to React to
     * @param deltaTime time since last frame
     */
    public void engage(Animal target, float deltaTime) {
        if (target.isDead()) {
            this.target = null;
            return;
        }
        this.target = target;

        /*  Calculate the angle to run as arc tangent of a trigonometric triangle
            with the line between the two animals as hypotenuse */
        Vector2 position = new Vector2(getX(), getY()); //This animal's position
        Vector2 targetPosition = new Vector2(target.getX(), target.getY()); //Target's position
        Vector2 delta = targetPosition.sub(position).nor(); //Difference between positions

        Vector2 newPosition;
        if (this.chases(target)) {
            newPosition = position.add(delta.scl(getMovementSpeed() * deltaTime));
        } else {
            newPosition = position.sub(delta.scl(getMovementSpeed() * deltaTime));
        }
        setPosition(newPosition.x, newPosition.y);


        checkOutOfBounds();
    }

    private void checkOutOfBounds() {
        if (getX() > 1500) setX(1500);
        if (getX() < 0) setX(0);
        if (getY() > 800) setY(800);
        if (getY() < 0) setY(0);
    }

    /**
     * Determine whether this animal wants to chase the target, be it for food
     * or reproduction purposes.
     * @param target the target to chase or flee from
     * @return true if the target is desired by this animal, or false if this animal wants to flee the target
     */
    private boolean chases(Animal target) {
        if (this instanceof Mouse) {
            return target instanceof Mouse;
        } else if (this instanceof Fox) {
            return target instanceof Mouse || target instanceof Fox;
        } else return this instanceof Eagle;
    }

    public void eat(Animal prey) {
        prey.kill();
        this.eatingPauseLeft = eatingPause;
        this.setHungerValue(getHungerValue() + prey.getNutritionValue());
        this.target = null;
    }

    public void becomePregnant() {
        pregnant = true;
        maternityPeriodLeft = getMaternityPeriod();
        canReproduce = false;
        this.target = null;
    }
}
