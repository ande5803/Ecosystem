package dk.bundgaard.anders.ecosystem.processor;

import dk.bundgaard.anders.ecosystem.Ecosystem;
import dk.bundgaard.anders.ecosystem.animal.Animal;
import dk.bundgaard.anders.ecosystem.animal.Eagle;
import dk.bundgaard.anders.ecosystem.animal.Fox;
import dk.bundgaard.anders.ecosystem.animal.Mouse;

import java.awt.geom.Point2D;

public class EngagementProcessor implements Processor {
    @Override
    public void process(float deltaTime) {
        for (Animal animal : Ecosystem.getInstance().animals) {
            if (animal.getTarget() != null) {
                animal.engage(animal.getTarget(), deltaTime);
                continue;
            }

            Animal chosenTarget = null;
            double shortestDistance = Integer.MAX_VALUE;
            for (Animal target : Ecosystem.getInstance().animals) { //TODO Optimize O(n^2) complexity
                if (targetIsInvalid(animal, target)) continue;
                double distance = Math.abs(Point2D.distance(animal.getX(), animal.getY(), target.getX(), target.getY()));
                if (distance < shortestDistance) {
                    shortestDistance = distance;
                    chosenTarget = target;
                }
            }
            if (chosenTarget != null) {
                animal.engage(chosenTarget, deltaTime);
            }
        }
    }

    private boolean targetIsInvalid(Animal animal, Animal target) {
        //An animal cannot target itself
        if (animal.equals(target)) return true;

        //Animals of same species and sex cannot target each other
        if (animal instanceof Mouse && target instanceof Mouse
                && animal.isFemale() == target.isFemale()) return true;
        if (animal instanceof Fox && target instanceof Fox
                && animal.isFemale() == target.isFemale()) return true;
        if (animal instanceof Eagle && target instanceof Eagle
                && animal.isFemale() == target.isFemale()) return true;

        //Cannot target for reproduction if one party is unfit
        if (animal instanceof Mouse && target instanceof Mouse &&
                (!animal.canReproduce() || !target.canReproduce())) return true;
        if (animal instanceof Fox && target instanceof Fox &&
                (!animal.canReproduce() || !target.canReproduce())) return true;
        if (animal instanceof Eagle && target instanceof Eagle &&
                (!animal.canReproduce() || !target.canReproduce())) return true;

        return false;
    }
}
