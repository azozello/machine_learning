package core.algs;

import core.entities.Data;
import core.models.KNNModel;
import core.models.Model;

public class KNNClassification {

    public static Model getModel(int neighbors, int classesAmount, Data data) {
        return new KNNModel(neighbors, classesAmount, data.getObjects());
    }

    public static Model getModel(int neighbors, int classesAmount, String pathToData) {
        Data data = new Data();
        data.loadData(pathToData);
        return new KNNModel(neighbors, classesAmount, data.getObjects());
    }
}
