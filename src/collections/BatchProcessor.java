package collections;

import java.util.*;

public class BatchProcessor {

    private final Map<String, List<Double>> plantReadings = new HashMap<>();
    private final Set<String> blackListedSensors = new HashSet<>();

    /*
     * computeIfAbsent(key, mappingFunction):
     * 1. Checks if the key exists in the Map.
     * 2. If the key exists (and value is non-null), it simply returns the existing value.
     * 3. If the key DOES NOT exist (or maps to null), it executes the lambda (k -> new ArrayList<>()),
     *    saves the newly created list into the Map under that key, and then returns that list.
     * 4. We can immediately chain .add(temperature) because the return value is guaranteed to be a valid List.
     */
    public void recordReading(String plantID, double temperature) {
        plantReadings.computeIfAbsent(plantID, k -> new ArrayList<>()).add(temperature);
    }


    public void addNewPlantReadings(String plantID, List<Double> temperatures){
        plantReadings.putIfAbsent(plantID, temperatures);
    }

    public void updatePlantMeasurements(String plantID, List<Double> newTemperatures){
        plantReadings.replace(plantID, newTemperatures);
    }

    /*
     * getOrDefault(key, defaultValue):
     * 1. Looks up 'key' in the Map.
     * 2. Returns the associated value if present.
     * 3. If the key is missing (or mapped to null), it returns 'defaultValue' instead.
     * 4. KEY ADVANTAGE: Avoids returning 'null', preventing NullPointerExceptions (NPE)
     *    when the calling code attempts to iterate or perform operations on the result.
     */
    public List<Double> getReadingsForPlant(String plantID) {
        return plantReadings.getOrDefault(plantID, Collections.emptyList());
    }

    /*
     * Set.add(element):
     * 1. Hashes the element and checks if it already exists inside the Set.
     * 2. If NEW: inserts element and returns 'true'.
     * 3. If DUPLICATE: ignores insertion and returns 'false'.
     * 4. WHY IT MATTERS: Unlike List.add() which always returns true, we can use
     *    this boolean return value directly in 'if' conditions or return statements
     *    to detect duplicate operations.
     */
    public boolean registerSensor(String sensorID) {
        return blackListedSensors.add(sensorID);
    }

    /*
     * collection.removeIf(Predicate):
     * 1. Iterates through the collection internally.
     * 2. Evaluates the lambda condition (reading -> reading < threshold) for each item.
     * 3. Removes all items where the condition evaluates to 'true'.
     * 4. WHY IT MATTERS: In standard 'for' loops, removing items while iterating throws
     *    ConcurrentModificationException. removeIf handles safe element removal in-place.
     */
    public void purgeLowReadings(String plantID, double threshold) {
        List<Double> readings = plantReadings.get(plantID);
        if (readings != null) {
            readings.removeIf(reading -> reading < threshold);
        }
    }
}
