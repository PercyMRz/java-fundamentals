package collections;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static collections.Inventory.*;
import collections.BatchProcessor.*;

public class Main {

    public static void main(String[] args) {
        Inventory inventory = new Inventory();

        inventory.addProduct("H2SO4", 150.5);
        inventory.addProduct("NaOH", 80.0);
        inventory.addProduct("CH4", 100.0);
        inventory.addProduct("CaOH", 200.0);
        inventory.addProduct("CH3COOH", 300.0);

        inventory.checkStock(100.0);
        inventory.containsProduct("CH3OH");
        inventory.containsProduct("CH3COOH");



        BatchProcessor processor = new BatchProcessor();

        // 1. Record readings (computeIfAbsent)
        processor.recordReading("PLANT-A", 650.5);
        processor.recordReading("PLANT-A", 710.0);
        processor.recordReading("PLANT-B", 450.0);

        // 1.2* Add new whole plant readings (putIfAbsent)
        processor.addNewPlantReadings("PLANT-C", List.of(670.1, 650.2, 700.2, 645.1));

        // 1.3* Update measurements of a plant (replace)
        processor.updatePlantMeasurements("PLANT-C", List.of(700.1, 689.0, 710.2));

        // 2. Fetch readings safely (getOrDefault)
        System.out.println("Plant A: " + processor.getReadingsForPlant("PLANT-A")); // [650.5, 710.0]
        System.out.println("Plant C (Unknown): " + processor.getReadingsForPlant("PLANT-C")); // []

        // 3. Blacklist sensors (Set.add boolean)
        System.out.println("Sensor 1 added? " + processor.registerSensor("SENSOR-01")); // true
        System.out.println("Sensor 1 added again? " + processor.registerSensor("SENSOR-01")); // false

        // 4. Purge low temperatures (removeIf)
        processor.purgeLowReadings("PLANT-A", 700.0);
        System.out.println("Plant A after purge: " + processor.getReadingsForPlant("PLANT-A")); // [710.0]
    }

}
