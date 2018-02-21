package core.entities;

import core.exceptions.TrainingFileException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Data {

    private List<Object> objects;

    public Data() {
        this.objects = new ArrayList<>();
    }

    public Data(List<Object> objects) {
        this.objects = objects;
    }

    public void loadData(String path) throws TrainingFileException {
        try (BufferedReader br = new BufferedReader(new FileReader(new File(path)))){
            List<String> lines = new ArrayList<>();
            while (br.ready()) {
                lines.add(br.readLine());
            }
            Object object = new Object();
            for (String line : lines) {
                List<Double> coords = new ArrayList<>();
                int objectClass;

                String coordsString = line.split(":")[0];
                for (String coordString : coordsString.split(" ")) {
                    coords.add(Double.valueOf(coordString.trim()));
                }

                String classString = line.split(":")[1];
                objectClass = Integer.valueOf(classString.trim());

                object.setCoords(coords);
                object.setObjectsClass(objectClass);

                objects.add(object);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            throw new TrainingFileException(ioe.getMessage());
        }
    }

    public List<Object> getObjects() {
        return objects;
    }

    public void setObjects(List<Object> objects) {
        this.objects = objects;
    }
}
