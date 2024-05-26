package control;


import utility.constants.ConstantsCSV;
import utility.constants.ConstantsMath;

import java.util.*;


/**
 * The MapOperations class provides methods for handling and manipulating map data.
 *
 * @author Michael Markov
 * @version 1
 */
public class MapOperations
{
    /**
     * Computes the sum of values from a map containing numeric values.
     *
     * @param <K> The type of keys in the map.
     * @param <V> The type of numeric values in the map.
     * @param map The map from which to compute the sum of values.
     *            ({@code map} != {@code null})
     * @return Returns the sum of numeric values from the map.
     */
    public <K, V extends Number> V getSumOfValuesFromMap (Map<K, V> map)
    {
        V sum = null;
        for (Map.Entry<K, V> entry : map.entrySet())
        { // Iterate through map entries
            if (sum == null)
            {
                // Set sum to entry value
                sum = entry.getValue();
            } else
            {
                // Add the value of the current entry to the sum
                sum = (V) (Number) (sum.doubleValue() + entry.getValue().doubleValue());
            }
        }
        return sum;
    }


    /**
     * Retrieves the key associated with a specified value from a map.
     *
     * @param map   The map from which to retrieve the key.
     *              ({@code map} != {@code null})
     * @param value The value for which to find the key.
     * @return Returns the key associated with the specified value from the map, or a default value string if not found.
     */
    public String getStringKey (Map<String, Integer> map, int value)
    {
        for (Map.Entry<String, Integer> entry : map.entrySet()) // Iterate through map entries
        {
            // If current entry value, the value looked for, then return key
            if (entry.getValue() == value) return entry.getKey();
        }
        return String.valueOf(ConstantsCSV.NUMBER_DEFAULT_VALUE); // If no entry with that value
    }


    /**
     * Retrieves the last key from a map.
     *
     * @param <K> The type of keys in the map.
     * @param <V> The type of values in the map.
     * @param map The map from which to retrieve the last key.
     *            ({@code map} != {@code null})
     * @return Returns the last key from the map, or {@code null} if the map is empty.
     */
    public <K, V> K getLastKey (Map<K, V> map)
    {
        if (map.isEmpty())
        {
            return null; // Return null if the map is empty
        }

        // Convert the keys to a list
        List<K> keysList = new ArrayList<>(map.keySet());

        // Get the last key from the list
        return keysList.get((int) Mathematics.subtraction(keysList.size(), (int) ConstantsMath.ONE));
    }


    /**
     * Retrieves the entry with the maximum value from a map.
     *
     * @param <K> The type of keys in the map.
     * @param <V> The type of values in the map, which must be comparable.
     * @param map The map from which to retrieve the entry with the maximum value.
     *            ({@code map} != {@code null})
     * @return Returns a map containing the entry with the maximum value from the input map, or an empty map if the input map is null or empty.
     */
    public <K, V extends Comparable<V>> Map<K, V> getMaximumEntry (Map<K, V> map)
    {
        Map<K, V> maxMap = new HashMap<>();

        if (map == null || map.isEmpty())
        {
            return maxMap; // Return an empty map instead of null
        }

        V maxValue = Collections.max(map.values()); // Get the maximum value from the map
        K maxKey = null;

        // Find the key corresponding to the maximum value
        for (Map.Entry<K, V> entry : map.entrySet()) // Iterates through map entries
        {
            if (entry.getValue().equals(maxValue)) // If current key corresponds to the maxValue, key is found
            {
                maxKey = entry.getKey();
                break;
            }
        }

        if (maxKey != null) // Makes sure key is available before adding entry to map
        {
            maxMap.put(maxKey, maxValue);
        }
        return maxMap;
    }
}
