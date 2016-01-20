package com.thumbtack.database.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Data currently being operated on
 * If this were a concurrent application, then  ConcurrentMaps should be used.
 * Operations should acquire locks on the keys before performing any action.
 */
public class DataValues {
    private final Map<String, String> data = new HashMap<>();
    private final Map<String, Integer> valueCountMap = new HashMap<>();

    private final List<String> keysToBeDeleted = new ArrayList<>();

    public void setData(String key, String value) {
        if (keysToBeDeleted.contains(key)) {
            keysToBeDeleted.remove(key);
        }
        data.put(key, value);
    }

    Map<String, String> getData() {
        return data;
    }

    public boolean isKeyDeleted(String key) {
        return keysToBeDeleted.contains(key);
    }

    public String getKeyValue(String key) {
        if (keysToBeDeleted.contains(key)) {
            return null;
        }
        return data.get(key);
    }

    public Integer getValueCount(String value) {
        if (!valueCountMap.containsKey(value)) {
            return null;
        }
        return valueCountMap.get(value);
    }

    public void setValueCount(String value, Integer valueCount) {
        valueCountMap.put(value, valueCount);
    }

    public void unsetKey(String key) {
        keysToBeDeleted.add(key);
        data.remove(key);
    }

    public void mergeTransaction(DataValues transaction) {
        // Merge
        transaction.getData().keySet().forEach(
                k -> this.data.put(k, transaction.getKeyValue(k))
        );

        // Delete
        transaction.getKeysToBeDeleted().forEach(
                this.data::remove
        );

        // Update count
        transaction.getValueCountMap().keySet().stream().forEach(
                k -> this.valueCountMap.put(k, transaction.getValueCount(k))
        );
    }

    private List<String> getKeysToBeDeleted() {
        return keysToBeDeleted;
    }

    private Map<String, Integer> getValueCountMap() {
        return valueCountMap;
    }
}
