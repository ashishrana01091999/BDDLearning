package org.example.utility;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShareData {

    private final Map<String, Object> dataMap;

    public ShareData() {
        dataMap = new HashMap<>();
    }

    /**
     * Store data with a key.
     * @param key the key to store the data
     * @param value the value to store (can be String, List, etc.)
     */
    public void put(String key, Object value) {
        dataMap.put(key, value);
    }

    /**
     * Retrieve a String value by key.
     * @param key the key to retrieve the value
     * @return the String value or null if the key doesn't exist or the value isn't a String
     */
    public String getString(String key) {
        Object value = dataMap.get(key);
        return value instanceof String ? (String) value : null;
    }

    /**
     * Retrieve an Integer value by key.
     * @param key the key to retrieve the value
     * @return the Integer value or null if the key doesn't exist or the value isn't an Integer
     */
    public Integer getInteger(String key) {
        Object value = dataMap.get(key);
        return value instanceof Integer ? (Integer) value : null;
    }

    /**
     * Retrieve a List value by key.
     * @param key the key to retrieve the value
     * @return the List value or null if the key doesn't exist or the value isn't a List
     */
    public List<?> getList(String key) {
        Object value = dataMap.get(key);
        return value instanceof List ? (List<?>) value : null;
    }

    /**
     * Retrieve an ArrayList value by key.
     * @param key the key to retrieve the value
     * @return the ArrayList value or null if the key doesn't exist or the value isn't an ArrayList
     */
    public List<?> getArrayList(String key) {
        Object value = dataMap.get(key);
        return value instanceof     ArrayList ? (ArrayList<?>) value : null;
    }

    /**
     * Retrieve an Object value by key.
     * @param key the key to retrieve the value
     * @return the Object value or null if the key doesn't exist
     */
    public Object getObject(String key) {
        return dataMap.get(key);
    }

    /**
     * Check if the key exists in the map.
     * @param key the key to check
     * @return true if the key exists, false otherwise
     */
    public boolean containsKey(String key) {
        return dataMap.containsKey(key);
    }

    /**
     * Remove the entry associated with the key.
     * @param key the key to remove
     */
    public void remove(String key) {
        dataMap.remove(key);
    }
}
