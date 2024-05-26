package control;


import model.Tree;
import utility.constants.ConstantsCSV;
import utility.constants.ConstantsMath;
import utility.constants.ConstantsTreeRestrictions;
import utility.constants.ConstantsTreeType;

import java.util.*;


/**
 * The TreeController class provides methods for handling and manipulating tree data.
 *
 * @author Michael Markov
 * @version 1
 */
public class TreeController
{
    /**
     * Finds a correlating variable at specific trait index based on the variable from another map at specific trait index.
     *
     * @param mapsList       The list of maps containing keys, which correlate to each other through their equal value.
     *                       ({@code mapsList} != {@code null})
     * @param originKey      Available key.
     * @param originMapIndex List index at which the key is located.
     * @param goalMapIndex   List index at which the key is looked for.
     * @return Returns the correlating variable found in the goal map based on the value from the origin map.
     */
    public String findCorrelatingVariable (List<Map<String, Integer>> mapsList, String originKey, int originMapIndex, int goalMapIndex)
    {
        MapOperations mapOperations = new MapOperations();
        // Looks for value at origin map at specific key
        int value = mapsList.get(originMapIndex).get(originKey);
        // Returns the key at correlating value in goal map
        return mapOperations.getStringKey(mapsList.get(goalMapIndex), value);
    }


    /**
     * Clusters correlating variables from a list of trees into maps based on specified indices.
     *
     * @param treeList The list of trees from which to cluster variables.
     *                 ({@code treeList} != {@code null})
     * @param a        The starting index of the traits to be considered.
     * @param b        The ending index of the traits to be considered.
     * @return Returns an array list of maps containing clustered correlating variables.
     */
    public ArrayList<Map<String, Integer>> clusterCorrelatingVariables (List<Tree> treeList, int a, int b)
    {
        ArrayList<Map<String, Integer>> clusteredList = new ArrayList<>();

        for (int i = (int) ConstantsMath.ZERO; i <= b; i++)
        {
            clusteredList.add(new HashMap<>()); // Declares maps from 0 to b (although maps from 0 to a will be ignored)
        }

        int lastFreeIndex = (int) ConstantsMath.ZERO;
        for (Tree tree : treeList) // Iterates through treeList
        {
            int mapValueOfOldTraits = (int) ConstantsMath.ZERO;

            // Checks whether traits new or align with older traits
            boolean isNew = ConstantsMath.bTrue;
            for (int i = a; i <= b; i++) // Iterates through tree traits from a to b
            {
                String currentTrait = tree.getTrait(i);
                Map<String, Integer> currentMap = clusteredList.get(i);

                if (currentTrait.equals(String.valueOf(ConstantsCSV.NUMBER_DEFAULT_VALUE)))
                    continue; // Default values will be ignored
                if (currentMap.containsKey(currentTrait)) // Current trait in the list
                {
                    isNew = ConstantsMath.bFalse; // Tree contains values that are already in the list
                    mapValueOfOldTraits = currentMap.get(currentTrait); // Notes value for later use
                    break; // As we already defined that the values in current instance are old, there is no need to keep iterating
                }
            }

            // Assigns tree traits to map at noted value (if not empty)
            for (int i = a; i <= b; i++) // Iterates through tree traits from a to b
            {
                String currentTrait = tree.getTrait(i);
                Map<String, Integer> currentMap = clusteredList.get(i);

                if (currentTrait.equals(String.valueOf(ConstantsCSV.NUMBER_DEFAULT_VALUE)))
                    continue; // Default values will be ignored
                if (currentMap.containsKey(currentTrait)) continue; // Current key already in the list

                if (isNew)
                {
                    currentMap.put(currentTrait, lastFreeIndex); // Adds new values
                } else
                {
                    currentMap.putIfAbsent(currentTrait, mapValueOfOldTraits); // Assigning to other values if empty
                }
            }
            if (isNew) lastFreeIndex++;
        }
        return clusteredList;
    }


    /**
     * Generates a map associating types of traits with lists of trees having the corresponding trait.
     *
     * @param treeList   The list of trees to be mapped.
     *                   ({@code treeList} != {@code null})
     * @param traitIndex The index indicating the trait to be considered.
     * @return Returns a map where each key represents a type of trait and its corresponding value is a list of trees having that trait.
     */
    public Map<String, List<Tree>> getMapOfTypesOfTraitsAndAllTheCorrelatingTrees (List<Tree> treeList, int traitIndex)
    {
        Map<String, List<Tree>> treeMap = new HashMap<>();

        for (Tree tree : treeList) // Iterates through treeList
        {
            String currentTrait = tree.getTrait(traitIndex);
            // Gets list at trait of the tree
            List<Tree> valueList = treeMap.get(currentTrait);
            if (valueList == null) valueList = new ArrayList<>(); // If current trait not in map

            valueList.add(tree); // Adds tree with current trait to list that in the map corresponds to the key
            treeMap.put(currentTrait, valueList); // Updates map
        }
        return treeMap;
    }


    /**
     * Computes the average value of a specific trait from a list of trees.
     *
     * @param treeList   The list of trees from which the average is computed.
     *                   ({@code treeList} != {@code null})
     * @param traitIndex The index of the trait for which the average is computed.
     * @return Returns the average value of the specified trait among the trees in the list.
     */
    public Double getAverageNumberFromTreeList (List<Tree> treeList, int traitIndex)
    {
        double value = (int) ConstantsMath.ZERO;
        double counter = (int) ConstantsMath.ZERO;
        for (Tree tree : treeList) // Iterates through treeList
        {
            double currentValue = Double.parseDouble(tree.getTrait(traitIndex));
            if (currentValue == ConstantsCSV.NUMBER_DEFAULT_VALUE) continue; // Ignores default values
            value += currentValue; // Adds currentValue to previous values
            counter++;
        }
        return (Mathematics.division(value, counter)); // Returns average
    }


    /**
     * Retrieves a list of all the different traits at specific trait index.
     *
     * @param treeList   The list of trees from which to extract the types of traits.
     *                   ({@code treeList} != {@code null})
     * @param traitIndex The index of the trait to be considered.
     * @return Returns a list of unique types of traits present in the tree list.
     */
    public List<String> getTypesOfTraits (List<Tree> treeList, int traitIndex)
    {
        List<String> traitList = new LinkedList<>();

        for (Tree tree : treeList) // Iterates through treeList
        {
            String element = tree.getTrait(traitIndex);
            if (!traitList.contains(element))
            {
                // Adds trait to list, if trait not in the list
                traitList.add(element);
            }
        }
        return traitList;
    }


    /**
     * Generates a map associating types of traits with the count of occurrences of each trait in the provided list of trees.
     *
     * @param treeList   The list of trees to be analyzed.
     *                   ({@code treeList} != {@code null})
     * @param traitIndex The index indicating the trait to be considered.
     * @return Returns a map where each key represents a type of trait and its corresponding value is the count of occurrences
     * of that trait in the tree list.
     */
    public HashMap<String, Integer> getTypesOfTraitsAndTheAmountOfOccurrences (List<Tree> treeList, int traitIndex)
    {
        HashMap<String, Integer> occurrenceMap = new HashMap<>();

        for (Tree tree : treeList) // Iterates through treeList
        {
            String element = tree.getTrait(traitIndex);
            if (occurrenceMap.containsKey(element))
            {
                // If map contains key, increases value by 1
                occurrenceMap.put(element, ((int) Mathematics.sum(occurrenceMap.get(element), (int) ConstantsMath.ONE))); // Increment count
            } else
            {
                // Otherwise adds new key with value 1
                occurrenceMap.put(element, (int) ConstantsMath.ONE); // Add new element with count 1
            }
        }
        return occurrenceMap;
    }


    /**
     * Retrieves the tree with the maximum value of a specified trait from the provided list of trees.
     *
     * @param treeList The list of trees from which to find the tree with the maximum value.
     *                 ({@code treeList} != {@code null})
     * @param index    The index of the trait for which to find the maximum value.
     * @return Returns the tree with the maximum value of the specified trait in the tree list, or {@code null} if the list is empty.
     */
    public Tree getTreeWithMaximumValue (List<Tree> treeList, int index)
    {
        if (treeList == null || treeList.isEmpty())
        {
            return null; // Handle empty list case
        }

        Tree treeWithMaximumValue = null;
        Comparable maxValue = null;

        for (Tree tree : treeList) // Iterates through treeList
        {
            Comparable value = tree.getTrait(index);
            if (maxValue == null || value.compareTo(maxValue) > 0) // If variable empty, or value bigger than variable
            {
                maxValue = value; // Updates variable
                treeWithMaximumValue = tree; // Tree reference saved
            }
        }
        return treeWithMaximumValue;
    }


    /**
     * Computes the average tree based on several traits from the provided list of trees.
     *
     * @param treeList The list of trees from which to compute the average tree.
     *                 ({@code treeList} != {@code null})
     * @return Returns the average tree computed from the provided list.
     */
    public Tree getAverageTree (List<Tree> treeList)
    {
        int age = (int) ConstantsMath.ZERO;
        double croneDiameterM = (int) ConstantsMath.ZERO;
        int girthCM = (int) ConstantsMath.ZERO;
        float heightM = (int) ConstantsMath.ZERO;

        int ageCounter = (int) ConstantsMath.ZERO;
        int croneDiameterCounter = (int) ConstantsMath.ZERO;
        int girthCounter = (int) ConstantsMath.ZERO;
        int heightCounter = (int) ConstantsMath.ZERO;


        for (Tree tree : treeList) // Iterates through treeList
        {
            if (tree.getAge() != ConstantsCSV.NUMBER_DEFAULT_VALUE) // Not default
            {
                age += tree.getAge(); // Adds up value to previous values
                ageCounter++;
            }
            if (tree.getCroneDiameterM() != ConstantsCSV.NUMBER_DEFAULT_VALUE) // Not default
            {
                croneDiameterM += tree.getCroneDiameterM(); // Adds up value to previous values
                croneDiameterCounter++;
            }
            if (tree.getCircumferenceCM() != ConstantsCSV.NUMBER_DEFAULT_VALUE) // Not default
            {
                girthCM += tree.getCircumferenceCM(); // Adds up value to previous values
                girthCounter++;
            }
            if (tree.getHeightM() != ConstantsCSV.NUMBER_DEFAULT_VALUE) // Not default
            {
                heightM += tree.getHeightM(); // Adds up value to previous values
                heightCounter++;
            }
        }
        // Gets average by dividing sums with their individual counter
        age /= ageCounter;
        croneDiameterM /= croneDiameterCounter;
        girthCM /= girthCounter;
        heightM /= heightCounter;

        return new Tree(age, croneDiameterM, girthCM, heightM);
    }


    /**
     * Determines if the given tree is deciduous based on confidence values.
     *
     * @param tree The tree to be evaluated.
     *             ({@code tree} != {@code null})
     * @return Returns {@code true} if the confidence value of the tree is greater than or equal to {@link ConstantsTreeType#CONFIDENCE_VALUE_SPLITTER}.
     */
    public boolean isDeciduous (Tree tree)
    {
        // Using confidence values (high = deciduous...)
        double treeSpecificConfidenceValue = getConfidenceValueFromTree(tree);
        // If exceeds certain value, tree is considered deciduous
        if (treeSpecificConfidenceValue >= ConstantsTreeType.CONFIDENCE_VALUE_SPLITTER)
        {
            return ConstantsMath.bTrue;
        } else return ConstantsMath.bFalse;
    }


    /**
     * Retrieves the confidence value of a tree based on various parameters.
     * Contains 2 ways of determining.
     * The prior one uses an array of species and assigns a 100% confidence value to the tree.
     * The second one uses the sum of confidence values from the numerical values of the tree.
     *
     * @param tree The tree for which the confidence value is calculated.
     *             ({@code tree} != {@code null})
     * @return Returns the confidence value of the tree based on its species, height, crone diameter, and circumference.
     */
    public double getConfidenceValueFromTree (Tree tree)
    {
        // Using array of popular trees (highly effective)
        for (int i = (int) ConstantsMath.ZERO; i < ConstantsTreeType.popularConiferousTrees.length; i++)
        {
            if (tree.getSpeciesBotanical().equalsIgnoreCase(ConstantsTreeType.popularConiferousTrees[i]))
                return (int) ConstantsMath.ZERO;
        }
        for (int i = (int) ConstantsMath.ZERO; i < ConstantsTreeType.popularDeciduousTrees.length; i++)
        {
            if (tree.getSpeciesBotanical().equalsIgnoreCase(ConstantsTreeType.popularDeciduousTrees[i]))
                return (int) ConstantsMath.ONE;
        }

        // Otherwise value determined mathematically
        return (float) Mathematics.sum(getConfidenceValueFromHeight(tree.getHeightM()), getConfidenceValueFromCroneDiameter(tree.getCroneDiameterM()), getConfidenceValueFromCircumference(tree.getCircumferenceCM()));
    }


    /**
     * Retrieves the confidence value based on the height of the tree.
     * Subtracts deciduous from coniferous confidence value.
     *
     * @param heightM The height of the tree.
     * @return The confidence value derived from the tree's height.
     */
    private double getConfidenceValueFromHeight (double heightM)
    {
        // Confidence value = deciduous value - coniferous value
        return Mathematics.subtraction(deciduousHeightConfidenceValue(heightM), coniferousHeightConfidenceValue(heightM));
    }


    /**
     * Calculates the confidence value for deciduous trees based on height.
     * Uses a determining value from the average height of other coniferous trees.
     * The confidence value is capped to the minimum and maximum height.
     *
     * @param heightM The height of the tree.
     * @return The confidence value for deciduous trees based on height.
     */
    private double deciduousHeightConfidenceValue (double heightM)
    {
        double confidenceValue;
        double determiningHeight = ConstantsTreeType.decidiuousHeightM;
        double minimumHeight = ConstantsTreeRestrictions.MIN_MEASUREMENT_VALUE;
        double maximumHeight = ConstantsTreeRestrictions.MAX_heightM;

        confidenceValue = getConfidenceValue(heightM, determiningHeight, minimumHeight, maximumHeight);
        return confidenceValue;
    }


    /**
     * Calculates the confidence value for coniferous trees based on height.
     * Uses a determining value from the average height of other deciduous trees.
     * The confidence value is capped to the minimum and maximum height.
     *
     * @param heightM The height of the tree.
     * @return The confidence value for coniferous trees based on height.
     */
    private double coniferousHeightConfidenceValue (double heightM)
    {
        double confidenceValue;
        double determiningHeight = ConstantsTreeType.coniferousHeightM;
        double minimumHeight = ConstantsTreeRestrictions.MIN_MEASUREMENT_VALUE;
        double maximumHeight = ConstantsTreeRestrictions.MAX_heightM;

        confidenceValue = getConfidenceValue(heightM, determiningHeight, minimumHeight, maximumHeight);
        return confidenceValue;
    }


    /**
     * Retrieves the confidence value based on the crone diameter of the tree.
     * Subtracts deciduous from coniferous confidence value.
     *
     * @param croneDiameterM The crone diameter of the tree.
     * @return The confidence value derived from the tree's crone diameter.
     */
    private double getConfidenceValueFromCroneDiameter (double croneDiameterM)
    {
        // Confidence value = deciduous value - coniferous value
        return Mathematics.subtraction(deciduousCroneDiameterConfidenceValue(croneDiameterM), coniferousCroneDiameterConfidenceValue(croneDiameterM));
    }


    /**
     * Calculates the confidence value for deciduous trees based on crone diameter.
     * Uses a determining value from the average crone diameter of other deciduous trees.
     * The confidence value is capped to the minimum and maximum crone diameter.
     *
     * @param croneDiameterM The crone diameter of the tree.
     * @return The confidence value for deciduous trees based on crone diameter.
     */
    private double deciduousCroneDiameterConfidenceValue (double croneDiameterM)
    {
        double confidenceValue;
        double determiningCircumference = ConstantsTreeType.decidiuousDeterminingCroneDiameterM;
        double minimumDiameter = ConstantsTreeRestrictions.MIN_MEASUREMENT_VALUE;
        double maximumDiameter = ConstantsTreeRestrictions.MAX_croneDiameterM;

        confidenceValue = getConfidenceValue(croneDiameterM, determiningCircumference, minimumDiameter, maximumDiameter);
        return confidenceValue;
    }


    /**
     * Calculates the confidence value for coniferous trees based on crone diameter.
     * Uses a determining value from the average crone diameter of other coniferous trees.
     * The confidence value is capped to the minimum and maximum crone diameter.
     *
     * @param croneDiameterM The crone diameter of the tree.
     * @return The confidence value for coniferous trees based on crone diameter.
     */
    private double coniferousCroneDiameterConfidenceValue (double croneDiameterM)
    {
        double confidenceValue;
        double determiningCircumference = ConstantsTreeType.coniferousDeterminingCroneDiameterM;
        double minimumDiameter = ConstantsTreeRestrictions.MIN_MEASUREMENT_VALUE;
        double maximumDiameter = ConstantsTreeRestrictions.MAX_croneDiameterM;

        confidenceValue = getConfidenceValue(croneDiameterM, determiningCircumference, minimumDiameter, maximumDiameter);
        return confidenceValue;
    }


    /**
     * Retrieves the confidence value based on the circumference of the tree.
     * Subtracts deciduous from coniferous confidence value.
     *
     * @param circumferenceCM The circumference of the tree.
     * @return The confidence value derived from the tree's circumference.
     */
    private double getConfidenceValueFromCircumference (double circumferenceCM)
    {
        // Confidence value = deciduous value - coniferous value
        return Mathematics.subtraction(deciduousCircumferenceConfidenceValue(circumferenceCM), coniferousCircumferenceConfidenceValue(circumferenceCM));
    }


    /**
     * Calculates the confidence value for deciduous trees based on circumference.
     * Uses a determining value from the average circumference of other deciduous trees.
     * The confidence value is capped to the minimum and maximum circumference.
     *
     * @param circumferenceCM The circumference of the tree.
     * @return The confidence value for deciduous trees based on circumference.
     */
    private double deciduousCircumferenceConfidenceValue (double circumferenceCM)
    {
        double confidenceValue;
        double determiningCircumference = ConstantsTreeType.decidiuousDeterminingCircumferenceCM;
        double minimumCircumference = ConstantsTreeRestrictions.MIN_MEASUREMENT_VALUE;
        double maximumCircumference = ConstantsTreeRestrictions.MAX_circumferenceCM;

        confidenceValue = getConfidenceValue(circumferenceCM, determiningCircumference, minimumCircumference, maximumCircumference);
        return confidenceValue;
    }


    /**
     * Calculates the confidence value for coniferous trees based on circumference.
     * Uses a determining value from the average circumference of other coniferous trees.
     * The confidence value is capped to the minimum and maximum circumference.
     *
     * @param circumferenceCM The circumference of the tree.
     * @return The confidence value for coniferous trees based on circumference.
     */
    private double coniferousCircumferenceConfidenceValue (double circumferenceCM)
    {
        double confidenceValue;
        double determiningCircumference = ConstantsTreeType.coniferousDeterminingCircumferenceCM;
        double minimumCircumference = ConstantsTreeRestrictions.MIN_MEASUREMENT_VALUE;
        double maximumCircumference = ConstantsTreeRestrictions.MAX_circumferenceCM;

        confidenceValue = getConfidenceValue(circumferenceCM, determiningCircumference, minimumCircumference, maximumCircumference);
        return confidenceValue;
    }


    /**
     * Calculates the confidence value based on the provided parameters.
     *
     * @param x                The value for which confidence value is calculated.
     * @param determiningValue The determining value used in the calculation.
     * @param minimumValue     The minimum value for the range.
     * @param maximumValue     The maximum value for the range.
     * @return The confidence value based on the provided parameters.
     */
    private double getConfidenceValue (double x, double determiningValue, double minimumValue, double maximumValue)
    {
        double confidenceValue;

        // Uses partial function
        if (x > determiningValue)
        {
            // Rewritten: confidenceValue = ((maximumValue / (maximumValue - determiningValue)) - (x / (maximumValue - determiningValue)));
            confidenceValue = Mathematics.subtraction((Mathematics.division(maximumValue, (Mathematics.subtraction(maximumValue, determiningValue)))), (Mathematics.division(x, (Mathematics.subtraction(maximumValue, determiningValue)))));
        } else
        {
            // Rewritten: confidenceValue = ((x / (determiningValue - minimumValue)) - (minimumValue / (determiningValue - minimumValue)));
            confidenceValue = Mathematics.subtraction((Mathematics.division(x, (Mathematics.subtraction(determiningValue, minimumValue)))), (Mathematics.division(minimumValue, (Mathematics.subtraction(determiningValue, minimumValue)))));
        }
        return confidenceValue;
    }
}