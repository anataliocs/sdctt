package com.thumbtack.database.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Contains the current data that is being queried and modified by the user.
 * If there are transactions open, this will contain the data of the latest opened transaction
 * <p>
 * Choose to use maps for storing the data and valueCountMap, because we have O(1) runtime for get, set, unset, numEqualTo.
 * The list keysToBeDeleted is used to mark the keys that need to be deleted when committing the transactions
 * <p>
 * In case of a concurrent situation, then the Map should be ConcurrentMaps and the operations should acquire locks on the
 * keys it modifies, before taking any action.
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
        //merge keys
        transaction.getData().keySet().forEach(
                k -> this.data.put(k, transaction.getKeyValue(k))
        );

        //delete keys
        transaction.getKeysToBeDeleted().forEach(
                this.data::remove
        );

        //update value count
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
