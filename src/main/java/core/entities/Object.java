package core.entities;

import java.util.List;

public class Object {

    private int objectsClass;
    private List<Double> coords;

    public Object(){}

    public Object(int objectsClass, List<Double> coords) {
        this.objectsClass = objectsClass;
        this.coords = coords;
    }

    public int getObjectsClass() {
        return objectsClass;
    }

    public void setObjectsClass(int objectsClass) {
        this.objectsClass = objectsClass;
    }

    public List<Double> getCoords() {
        return coords;
    }

    public void setCoords(List<Double> coords) {
        this.coords = coords;
    }
}
