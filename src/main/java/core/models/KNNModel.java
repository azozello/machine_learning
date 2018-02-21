package core.models;

import core.entities.Object;
import core.exceptions.CompareException;

import java.util.*;

public class KNNModel implements Model {

    private int neighborsAmount;
    private int classesAmount;
    private List<Object> plane;

    public KNNModel(int neighborsAmount, int classesAmount, List<Object> plane) {
        this.classesAmount = classesAmount;
        this.neighborsAmount = neighborsAmount;
        this.plane = plane;
    }

    public int guess(Object object) {
        List<Integer> classes = new ArrayList<>();

        for (int i=0; i<classesAmount; i++)
            classes.add(0);

        for (Object o : findClosest(object))
            classes.set(o.getObjectsClass(), (classes.get(o.getObjectsClass())+1));

        Collections.sort(classes);
        return classes.get(0);
    }

    private double getDistance(Object o1, Object o2) throws CompareException {
        double result = 0;
        if (o1.getCoords().size() != o2.getCoords().size())
            throw new CompareException("Objects have different amount of coordinates");
        else {
            for (int i=0; i<o1.getCoords().size(); i++) {
                double temp = Math.pow(o2.getCoords().get(i)-o1.getCoords().get(i), 2);
                result += Math.sqrt(temp);
            }
            return result;
        }
    }

    /**
     * TODO: TEST IT!!!!!!
     */
    private List<Object> findClosest(Object newObject) {
        HashMap<Object, Double> unsortedPlane = new HashMap<>();
        List<Object> result = new ArrayList<>(neighborsAmount);

        for (Object oldObject : plane) {
            unsortedPlane.put(oldObject, getDistance(oldObject, newObject));
        }

        HashMap<Object, Double> sortedPlane = new LinkedHashMap<>();
        unsortedPlane.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEachOrdered(x -> sortedPlane.put(x.getKey(), x.getValue()));

        for (int i=0; i<neighborsAmount; i++) {
            Map.Entry<Object, Double> entry = sortedPlane.entrySet().iterator().next();
            result.add(entry.getKey());
        }

        return result;
    }
}
