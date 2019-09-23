package dk.bundgaard.anders.ecosystem.processor;

import dk.bundgaard.anders.ecosystem.Ecosystem;
import dk.bundgaard.anders.ecosystem.animal.Animal;

import java.util.ListIterator;

public class LifecycleProcessor implements Processor {

    private static final float TICK_TIME = 1;
    private float tick = TICK_TIME;

    @Override
    public void process(float deltaTime) {
        tick -= deltaTime;

        removeDeadAnimals();

        if (tick <= 0) {
            tick = TICK_TIME;
            for (int i = 0; i < Ecosystem.getInstance().animals.size(); i++) {
                Animal animal = Ecosystem.getInstance().animals.get(i);

                //Maturity and death by old age
                animal.setCurrentAge(animal.getCurrentAge() + 1);
                if (animal.getCurrentAge() > animal.getMaximumAge()) {
                    animal.kill();
                } else if (animal.getCurrentAge() >= animal.getReproductionAge()) {
                    animal.setCanReproduce(true);
                }

                //Birth
                if (animal.isFemale() && animal.isPregnant()) {
                    animal.setMaternityPeriodLeft(animal.getMaternityPeriodLeft() - 1);
                    if (animal.getMaternityPeriodLeft() <= 0) {
                        animal.giveBirth();
                    }
                }

                //Starvation
                animal.setHungerValue(animal.getHungerValue() - 1);
                if (animal.getHungerValue() <= 0) animal.kill();

                animal.decrementEatingPauseLeft();

                animal.resetTarget();
            }
        }
    }

    private void removeDeadAnimals() {
        ListIterator<Animal> animalIterator = Ecosystem.getInstance().animals.listIterator();
        while (animalIterator.hasNext()) {
            Animal animal = animalIterator.next();
            if (animal.isDead()) {
                animalIterator.remove();
            }
        }
    }
}
