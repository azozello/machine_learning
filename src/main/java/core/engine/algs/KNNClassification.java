package core.engine.algs;

import core.engine.entities.Data;
import core.engine.entities.Object;
import core.engine.models.KNNModel;
import core.engine.models.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KNNClassification {

    public static Model getModel(int neighbors, int classesAmount, Data data) {
        KNNModel model = new KNNModel(neighbors, classesAmount, data.getObjects());
        int coordsAmout = data.getObjects().get(0).getCoords().size();
        List<Double> mix = new ArrayList<>();
        List<Double> max = new ArrayList<>();

        for (int i=0; i<coordsAmout; i++) {
            List<Double> coordinateList = new ArrayList<>();
            for (Object o : data.getObjects()) {
                coordinateList.add(o.getCoords().get(i));
            }
            Collections.sort(coordinateList);
            mix.add(coordinateList.get(0));
            max.add(coordinateList.get(coordinateList.size()-1));
        }
        model.setMaxValues(max);
        model.setMinValues(mix);

        return model;
    }
}
