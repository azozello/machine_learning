package core;

import core.algs.KNNClassification;
import core.entities.Data;
import core.entities.Object;
import core.models.Model;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Data trainData = new Data();
        trainData.loadData("src/main/resources/train.txt");

        Data testData = new Data();
        testData.loadData("src/main/resources/test.txt");

        Model KNNmodel = KNNClassification.getModel(3, 3, trainData);

        double all = 0;
        double passed = 0;
        double percentage;

        for (Object o : trainData.getObjects()) {
            all += 1;
            if (o.getObjectsClass() == KNNmodel.guess(o))
                passed +=1;
        }

        percentage = (passed/all) * 100;
        System.out.println("Результат на учебном наборе: "+percentage+"%");
        all = 0;
        passed = 0;

        double allTime = 0;
        long startTime;

        for (Object o : testData.getObjects()) {
            all += 1;
            startTime = new Date().getTime();
            if (o.getObjectsClass() == KNNmodel.guess(o))
                passed +=1;
            allTime += new Date().getTime()-startTime;
        }
        percentage = (passed/all) * 100;
        System.out.println("Результат на тестовом наборе: "+percentage+"%");

        System.out.println("Время вычисления одного предположения: "
                +allTime/testData.getObjects().size()+"ms");
    }
}
