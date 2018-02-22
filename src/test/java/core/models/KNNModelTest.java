package core.models;

import core.entities.Data;
import core.entities.Object;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class KNNModelTest {

    @Test
    public void getDistanceTest() {
        KNNModel model = new KNNModel(1,1, new Data().getObjects());

        Object newObject = new Object();
        Object oldObject = new Object();

        Double oldCoords[] = {1.0,1.0,1.0};
        Double newCoords[] = {2.0,3.0,3.0};

        oldObject.setCoords(Arrays.asList(oldCoords));
        newObject.setCoords(Arrays.asList(newCoords));


    }
}
