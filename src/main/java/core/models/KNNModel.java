package core.models;

import core.entities.Object;
import core.exceptions.CompareException;

import java.util.*;

public class KNNModel implements Model {

    private List<Double> minValues;
    private List<Double> maxValues;
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
        int index = 0;
        for (int i=0; i<classesAmount-1; i++) {
            if (classes.get(i+1) > classes.get(i)) {
                index = i+1;
            }
        }
        return index;
    }

    private double getDistance(Object o1, Object o2) throws CompareException {
        double result = 0;
        if (o1.getCoords().size() != o2.getCoords().size())
            throw new CompareException("Objects have different amount of coordinates");
        else {
            for (int i=0; i<o1.getCoords().size(); i++) {
                double temp = Math.pow(
                        normalize(o2.getCoords().get(i), i)
                                -
                                normalize(o1.getCoords().get(i), i),
                        2);
                result += temp;
            }
            return Math.sqrt(result);
        }
    }

    private double normalize(double value, int index) {
        return (value-minValues.get(index)) / (maxValues.get(index)-minValues.get(index));
    }

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

        int index = 0;
        for (Map.Entry<Object, Double> entry : sortedPlane.entrySet()) {
            if (index == neighborsAmount)
                break;
            else {
                result.add(entry.getKey());
                index++;
            }
        }
        return result;
    }

    public void setMinValues(List<Double> minValues) {
        this.minValues = minValues;
    }

    public void setMaxValues(List<Double> maxValues) {
        this.maxValues = maxValues;
    }
}
