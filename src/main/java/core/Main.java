package core;

import core.algs.KNNClassification;
import core.entities.Object;
import core.models.Model;

import java.util.Arrays;


/**
 * TODO: Not working
 */
public class Main {
    public static void main(String[] args) {
        Model KNNmodel = KNNClassification.getModel(3, 3,
                "src/main/resources/train.txt");
        Object testObject = new Object();
        Double coords[] = {6.0,3.0};
        testObject.setCoords(Arrays.asList(coords));

        System.out.println(KNNmodel.guess(testObject));
    }
}
