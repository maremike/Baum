package control;


import model.Tree;
import model.exceptions.IllegalInputException;
import utility.constants.ConstantsDatasets;
import utility.constants.ConstantsIO;
import utility.constants.ConstantsMath;
import view.PrintConsole;
import view.PrintTree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * The IOController class provides methods for solving certain questions in the console.
 * In addition, it provides a method to get the answer to the question required which also throws an Exception if unexpected input occurs.
 *
 * @author Michael Markov
 * @version 1
 */
public class IOController
{
    TreeController treeController = new TreeController();
    MapOperations mapOperations = new MapOperations();


    /**
     * Retrieves an answer based on the provided menu input and list of trees.
     *
     * @param menuInput The menu input indicating the type of information requested.
     *                  ({@code menuInput} != {@code null})
     * @param treeList  The list of trees to process.
     *                  ({@code treeList} != {@code null})
     * @return The returned string contains information based on the menu input and tree list.
     * @throws IllegalInputException If the menu input is invalid.
     */
    public String getAnswer (String menuInput, List<Tree> treeList) throws IllegalInputException
    {
        PrintConsole printConsole = new PrintConsole();
        String answer = ConstantsMath.emptyString;
        switch (menuInput)
        {
            case ConstantsIO.questionNumber0:
                answer += printConsole.getAnswer(ConstantsIO.answer0, districtWithMostTrees(treeList));
                break;
            case ConstantsIO.questionNumber1:
                answer += printConsole.getAnswer(ConstantsIO.answer1, districtWithTallestTree(treeList));
                break;
            case ConstantsIO.questionNumber2:
                answer += printConsole.getAnswer(ConstantsIO.answer2, PrintTree.getTree(treeWithHighestCircumference(treeList)));
                break;
            case ConstantsIO.questionNumber3:
                answer += printConsole.getAnswer(ConstantsIO.answer3, PrintTree.getTree(treeWithBiggestCrone(treeList)));
                break;
            case ConstantsIO.questionNumber4:
                answer += printConsole.getAnswer(ConstantsIO.answer4, PrintTree.getTree(treeWithMostAge(treeList)));
                break;
            case ConstantsIO.questionNumber5:
                answer += printConsole.getAnswer(ConstantsIO.answer5, String.valueOf(amountOfTypesOfTrees(treeList)));
                break;
            case ConstantsIO.questionNumber6:
                answer += printConsole.getAnswer(ConstantsIO.answer6, String.valueOf(amountOfSpeciesOfTrees(treeList)));
                break;
            case ConstantsIO.questionNumber7:
                answer += printConsole.getAnswer(ConstantsIO.answer7, mostOccurringSpeciesOfTrees(treeList));
                break;
            case ConstantsIO.questionNumber8:
                answer += printConsole.getAnswer(ConstantsIO.answer8, mostSpeciesDiverseDistrict(treeList));
                break;
            case ConstantsIO.questionNumber9:
                answer += printConsole.getAnswer(ConstantsIO.answer9, averageTallestSpecies(treeList));
                break;
            case ConstantsIO.questionNumber10:
                answer += printConsole.getAnswer(ConstantsIO.answer10, averageHighestCircumferenceSpecies(treeList));
                break;
            case ConstantsIO.questionNumber11:
                answer += printConsole.getAnswer(ConstantsIO.answer11, String.valueOf(totalCarbonSaved(treeList)));
                break;
            case ConstantsIO.questionNumber12:
                answer += printConsole.getAnswer(ConstantsIO.answer12, districtWithMostCarbonSaved(treeList));
                break;
            case ConstantsIO.questionNumber13:
                answer += printConsole.getAnswer(ConstantsIO.answer13, speciesWithMostCarbonSaved(treeList));
                break;
            default:
                // Any other input will cause an exception
                throw new IllegalInputException(printConsole.getInputErrorMessage(menuInput));
        }
        return answer;
    }


    /**
     * Retrieves the district with the most trees in the list.
     *
     * @param treeList The list of trees to process.
     *                 ({@code treeList} != {@code null})
     * @return The returned string represents the district with the most trees.
     */
    public String districtWithMostTrees (List<Tree> treeList)
    {
        // Key will be the district, and the value the occurrence of trees in the correlating key
        Map<String, Integer> occurrenceAtDistrict = treeController.getTypesOfTraitsAndTheAmountOfOccurrences(
                treeList, ConstantsDatasets.INDEX_District);

        // Map with only one value (the maximum number)
        Map<String, Integer> maxMap = mapOperations.getMaximumEntry(occurrenceAtDistrict);
        return mapOperations.getLastKey(maxMap);
    }


    /**
     * Retrieves the district with the tallest tree in the list.
     *
     * @param treeList The list of trees to process.
     *                 ({@code treeList} != {@code null})
     * @return The returned string represents the district with the highest tree.
     */
    public String districtWithTallestTree (List<Tree> treeList)
    {
        Tree tallestTree = treeController.getTreeWithMaximumValue(treeList, ConstantsDatasets.INDEX_HeightM);
        return tallestTree.getDistrict();
    }


    /**
     * Retrieves the tree with the highest circumference in the list.
     *
     * @param treeList The list of trees to process.
     *                 ({@code treeList} != {@code null})
     * @return The returned tree has the highest circumference in the list.
     */
    public Tree treeWithHighestCircumference (List<Tree> treeList)
    {
        return treeController.getTreeWithMaximumValue(treeList, ConstantsDatasets.INDEX_CircumferenceCM);
    }


    /**
     * Retrieves the tree with the biggest crone diameter in the list.
     *
     * @param treeList The list of trees to process.
     *                 ({@code treeList} != {@code null})
     * @return The returned tree has the biggest crone in the list.
     */
    public Tree treeWithBiggestCrone (List<Tree> treeList)
    {
        return treeController.getTreeWithMaximumValue(treeList, ConstantsDatasets.INDEX_CroneDiameterM);
    }


    /**
     * Retrieves the tree with the highest age number in the list.
     *
     * @param treeList The list of trees to process.
     *                 ({@code treeList} != {@code null})
     * @return The returned tree has the most age in the list.
     */
    public Tree treeWithMostAge (List<Tree> treeList)
    {
        return treeController.getTreeWithMaximumValue(treeList, ConstantsDatasets.INDEX_Age);
    }


    /**
     * Retrieves the amount of Botanical types the list has.
     *
     * @param treeList The list of trees to process.
     *                 ({@code treeList} != {@code null})
     * @return The returned integer represents the amount of types of trees.
     */
    public int amountOfTypesOfTrees (List<Tree> treeList)
    {
        List<String> typesList = treeController.getTypesOfTraits(treeList, ConstantsDatasets.INDEX_TypeBotanical);
        return typesList.size();
    }


    /**
     * Retrieves the amount of Botanical species' the list has.
     *
     * @param treeList The list of trees to process.
     *                 ({@code treeList} != {@code null})
     * @return The returned integer represents the amount of species of trees.
     */
    public int amountOfSpeciesOfTrees (List<Tree> treeList)
    {
        List<String> typesList = treeController.getTypesOfTraits(treeList, ConstantsDatasets.INDEX_SpeciesBotanical);
        return typesList.size();
    }


    /**
     * Retrieves the Botanical species that occurs the highest amount of times in the list.
     *
     * @param treeList The list of trees to process.
     *                 ({@code treeList} != {@code null})
     * @return The returned string represents the most occurring species of trees.
     */
    public String mostOccurringSpeciesOfTrees (List<Tree> treeList)
    {
        // Key contains the various species, and the value contains the amount of times they are found in the list
        Map<String, Integer> occurrenceOfSpecies = treeController.getTypesOfTraitsAndTheAmountOfOccurrences(
                treeList, ConstantsDatasets.INDEX_SpeciesBotanical);

        // Map with only one value (the maximum number)
        Map<String, Integer> maxMap = mapOperations.getMaximumEntry(occurrenceOfSpecies);
        return mapOperations.getLastKey(maxMap);
    }


    /**
     * Retrieves the district that contains as many different tree species' in the list.
     *
     * @param treeList The list of trees to process.
     *                 ({@code treeList} != {@code null})
     * @return The returned string represents the district with the most diverse species.
     */
    public String mostSpeciesDiverseDistrict (List<Tree> treeList)
    {
        // Splits up treeList in multiple treeLists sorted by districts
        Map<String, List<Tree>> treesSeparatedByType = treeController.getMapOfTypesOfTraitsAndAllTheCorrelatingTrees(
                treeList, ConstantsDatasets.INDEX_District);
        Map<String, Integer> amountOfSpeciesDividedByDistrict = new HashMap<>();


        // Counts the occurrence of species' in each list and assigns it to the key value (district)
        for (Map.Entry<String, List<Tree>> entry : treesSeparatedByType.entrySet())
        {
            amountOfSpeciesDividedByDistrict.put(entry.getKey(), treeController.getTypesOfTraitsAndTheAmountOfOccurrences(
                    entry.getValue(), ConstantsDatasets.INDEX_SpeciesBotanical).size());
        }

        // Map contains one pair with the maximum value
        Map<String, Integer> maximumValueMap = mapOperations.getMaximumEntry(amountOfSpeciesDividedByDistrict);
        return mapOperations.getLastKey(maximumValueMap);
    }


    /**
     * Retrieves Botanical species that has overall the highest average height in the list.
     *
     * @param treeList The list of trees to process.
     *                 ({@code treeList} != {@code null})
     * @return The returned string represents the average tallest species.
     */
    public String averageTallestSpecies (List<Tree> treeList)
    {
        // Splits up treeList in multiple treeLists sorted by species'
        Map<String, List<Tree>> treesSeparatedBySpecies = treeController.getMapOfTypesOfTraitsAndAllTheCorrelatingTrees(
                treeList, ConstantsDatasets.INDEX_SpeciesBotanical);
        Map<String, Double> averageHeightDividedBySpecies = new HashMap<>();

        // Determines the average height while iterating through each species and assigning the average to each species
        for (Map.Entry<String, List<Tree>> entry : treesSeparatedBySpecies.entrySet())
        {
            averageHeightDividedBySpecies.put(entry.getKey(), treeController.getAverageNumberFromTreeList(
                    entry.getValue(), ConstantsDatasets.INDEX_HeightM));
        }

        // Map contains one pair with the maximum value
        Map<String, Double> maximumValueMap = mapOperations.getMaximumEntry(averageHeightDividedBySpecies);
        return mapOperations.getLastKey(maximumValueMap);
    }


    /**
     * Retrieves Botanical species that has overall the highest average circumference in the list.
     *
     * @param treeList The list of trees to process.
     *                 ({@code treeList} != {@code null})
     * @return The returned string represents the average highest circumference species.
     */
    public String averageHighestCircumferenceSpecies (List<Tree> treeList)
    {
        // Splits up treeList in multiple treeLists sorted by species'
        Map<String, List<Tree>> treesSeparatedBySpecies = treeController.getMapOfTypesOfTraitsAndAllTheCorrelatingTrees(
                treeList, ConstantsDatasets.INDEX_SpeciesBotanical);
        Map<String, Double> averageCircumferenceDividedBySpecies = new HashMap<>();

        // Determines the average circumference while iterating through each species and assigning the average to each species
        for (Map.Entry<String, List<Tree>> entry : treesSeparatedBySpecies.entrySet())
        {
            averageCircumferenceDividedBySpecies.put(entry.getKey(), treeController.getAverageNumberFromTreeList(
                    entry.getValue(), ConstantsDatasets.INDEX_CircumferenceCM));
        }

        // Map contains one pair with the maximum value
        Map<String, Double> maximumValueMap = mapOperations.getMaximumEntry(averageCircumferenceDividedBySpecies);
        return mapOperations.getLastKey(maximumValueMap);
    }


    /**
     * Retrieves the total amount of carbon saved by all trees in the list.
     *
     * @param treeList The list of trees to process.
     *                 ({@code treeList} != {@code null})
     * @return The returned integer represents the total carbon saved.
     */
    public int totalCarbonSaved (List<Tree> treeList)
    {
        CO2 co2 = new CO2();
        // Map contains each tree paired with its carbon values
        Map<Tree, Double> treeAndCarbonMap = co2.getMapOfTreeAndCarbon(treeList);
        // Sum of values in the map returned
        return (int) Math.round(mapOperations.getSumOfValuesFromMap(treeAndCarbonMap));
    }


    /**
     * Retrieves the district in the list which saved the most carbon.
     *
     * @param treeList The list of trees to process.
     *                 ({@code treeList} != {@code null})
     * @return The returned string represents the district with the most carbon saved.
     */
    public String districtWithMostCarbonSaved (List<Tree> treeList)
    {
        CO2 co2 = new CO2();
        // Splits up treeList in multiple treeLists sorted by districts
        Map<String, List<Tree>> treesSeparatedByDistrict = treeController.getMapOfTypesOfTraitsAndAllTheCorrelatingTrees(
                treeList, ConstantsDatasets.INDEX_District);
        Map<String, Double> totalCO2DividedByDistrict = new HashMap<>();

        // Determines the total co2 saved while iterating through each species and assigning the sum to each district
        for (Map.Entry<String, List<Tree>> entry : treesSeparatedByDistrict.entrySet())
        {
            totalCO2DividedByDistrict.put(entry.getKey(), co2.sumOfCO2StoredByTrees(entry.getValue()));
        }

        // Map contains one pair with the maximum value
        Map<String, Double> maxMap = mapOperations.getMaximumEntry(totalCO2DividedByDistrict);
        return mapOperations.getLastKey(maxMap);
    }


    /**
     * Retrieves the species in the list that in total saved the most carbon.
     *
     * @param treeList The list of trees to process.
     *                 ({@code treeList} != {@code null})
     * @return The returned string represents the species with the most carbon saved.
     */
    public String speciesWithMostCarbonSaved (List<Tree> treeList)
    {
        CO2 co2 = new CO2();

        // Splits up treeList in multiple treeLists sorted by species'
        Map<String, List<Tree>> treesSeparatedByDistrict = treeController.getMapOfTypesOfTraitsAndAllTheCorrelatingTrees(
                treeList, ConstantsDatasets.INDEX_SpeciesBotanical);
        Map<String, Double> totalCO2DividedBySpecies = new HashMap<>();

        // Determines the total co2 saved while iterating through each species and assigning the sum to each species
        for (Map.Entry<String, List<Tree>> entry : treesSeparatedByDistrict.entrySet())
        {
            totalCO2DividedBySpecies.put(entry.getKey(), co2.sumOfCO2StoredByTrees(entry.getValue()));
        }

        // Map contains one pair with the maximum value
        Map<String, Double> maxMap = mapOperations.getMaximumEntry(totalCO2DividedBySpecies);
        return mapOperations.getLastKey(maxMap);
    }
}
