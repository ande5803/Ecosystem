package dk.bundgaard.anders.ecosystem.processor;

import dk.bundgaard.anders.ecosystem.Ecosystem;
import dk.bundgaard.anders.ecosystem.animal.Animal;
import dk.bundgaard.anders.ecosystem.animal.Eagle;
import dk.bundgaard.anders.ecosystem.animal.Fox;
import dk.bundgaard.anders.ecosystem.animal.Mouse;
import org.jcp.xml.dsig.internal.MacOutputStream;

public class CollisionProcessor implements Processor{

    @Override
    public void process(float deltaTime) {
        for (Animal animal : Ecosystem.getInstance().animals) {
            for (Animal other : Ecosystem.getInstance().animals) {
                if (animal.getBoundingRectangle().overlaps(other.getBoundingRectangle())) {
                    processCollision(animal, other);
                }
            }
        }
    }

    private void processCollision(Animal animal, Animal other) {
        //Reproduction
        if ((animal instanceof Mouse && other instanceof Mouse)
                || (animal instanceof Fox && other instanceof Fox)
                || (animal instanceof Eagle && other instanceof Eagle)) {

            if (animal.isFemale() && animal.canReproduce()) {
                animal.becomePregnant();
                other.resetTarget();
            } else if (other.isFemale() && other.canReproduce()) {
                other.becomePregnant();
                animal.resetTarget();
            }
        }

        //Preying on other species
        if (animal.canEat()) {
            if (animal instanceof Fox && other instanceof Mouse) animal.eat(other);
            if (animal instanceof Eagle && !(other instanceof Eagle)) animal.eat(other);
        } else if (other.canEat()) {
            if (animal instanceof Mouse && other instanceof Fox) other.eat(animal);
            if (!(animal instanceof Eagle) && other instanceof Eagle) other.eat(animal);
        }
    }
}
