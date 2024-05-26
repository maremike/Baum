package control;


import model.Tree;
import model.exceptions.InvalidTraitException;
import model.exceptions.IrreparableDatasetException;
import utility.constants.ConstantsCSV;
import utility.constants.ConstantsDatasets;
import utility.constants.ConstantsMath;
import utility.constants.ConstantsTreeRestrictions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * The DataHandling class provides methods for fixing and handling data in an ArrayList.
 *
 * @author Michael Markov
 * @version 1
 */
public class DataRepair
{
    private static int corruptCounter = (int) ConstantsMath.ZERO;
    private static int repairedCounter = (int) ConstantsMath.ZERO;
    private static int deletedCounter = (int) ConstantsMath.ZERO;


    /**
     * Fixes the data of trees by repairing missing attributes.
     *
     * @param oldTrees The list of trees with potentially missing or corrupt data.
     *                 ({@code oldTrees} != {@code null})
     * @return A list of trees with fixed attributes. The returned list contains trees with repaired attributes.
     */
    public List<Tree> fixData (List<Tree> oldTrees)
    {
        TreeController treeController = new TreeController();

        // List will collect repaired trees
        List<Tree> fixedTrees = new ArrayList<>();
        // List will be used to translate tree type from one to another (if one is missing)
        List<Map<String, Integer>> clusteredListByType = treeController.clusterCorrelatingVariables(
                oldTrees, ConstantsDatasets.INDEX_TypeGerman, ConstantsDatasets.INDEX_TypeBotanical);
        // List will be used to translate tree species from one to another (if one is missing)
        List<Map<String, Integer>> clusteredListBySpecies = treeController.clusterCorrelatingVariables(
                oldTrees, ConstantsDatasets.INDEX_SpeciesGerman, ConstantsDatasets.INDEX_SpeciesBotanical);
        // Gets default tree from original values, that are available and not default
        Tree defaultTree = treeController.getAverageTree(oldTrees);

        for (Tree tree : oldTrees) // Iterates through each individual tree
        {
            try
            {
                String[] traits = filterStrings(tree.getTraits()); // Filters Strings, setting unwanted values to default

                if (needsRepairing(traits))
                {
                    corruptCounter++;
                    if (!isRepairable(traits)) throw new IrreparableDatasetException(); // Cannot be repaired
                    traits = repair(traits, clusteredListByType, clusteredListBySpecies, defaultTree);
                    repairedCounter++;
                }
                boolean isDeciduous = treeController.isDeciduous(tree); // Determines whether tree is deciduous or coniferous
                fixedTrees.add(new Tree(traits, isDeciduous)); // Adds to new list
            } catch (IrreparableDatasetException e)
            {
                deletedCounter++; // Counts "ignored" trees that cannot be repaired
            } catch (InvalidTraitException e)
            {
                throw new RuntimeException(e.getMessage());
            }
        }
        return fixedTrees;
    }


    /**
     * Repairs the attributes of a tree based on available data and default values.
     *
     * @param traits                 The array of traits representing a tree.
     *                               ({@code traits} != {@code null})
     * @param clusteredListByType    The list of maps for clustering types.
     *                               ({@code clusteredListByType} != {@code null})
     * @param clusteredListBySpecies The list of maps for clustering species.
     *                               ({@code clusteredListBySpecies} != {@code null})
     * @param defaultTree            The default tree to fill missing attributes.
     *                               ({@code defaultTree} != {@code null})
     * @return An array of traits with repaired values. The returned array contains repaired traits for a tree.
     */
    private String[] repair (String[] traits, List<Map<String, Integer>> clusteredListByType,
                             List<Map<String, Integer>> clusteredListBySpecies, Tree defaultTree)
    {
        TreeController treeController = new TreeController();
        boolean[] traitIsDefault = checkWhetherTraitsDefault(traits);

        // Translate type
        if (traitIsDefault[ConstantsDatasets.INDEX_TypeGerman] && !traitIsDefault[ConstantsDatasets.INDEX_TypeBotanical])
        {
            // Botanical type available, but not German
            traits[ConstantsDatasets.INDEX_TypeGerman] = treeController.findCorrelatingVariable(clusteredListByType,
                    traits[ConstantsDatasets.INDEX_TypeBotanical], ConstantsDatasets.INDEX_TypeBotanical,
                    ConstantsDatasets.INDEX_TypeGerman); // Finds correlating value using the available one
        } else if (!traitIsDefault[ConstantsDatasets.INDEX_TypeGerman] && traitIsDefault[ConstantsDatasets.INDEX_TypeBotanical])
        {
            // German type available, but not Botanical
            traits[ConstantsDatasets.INDEX_TypeBotanical] = treeController.findCorrelatingVariable(clusteredListByType,
                    traits[ConstantsDatasets.INDEX_TypeGerman], ConstantsDatasets.INDEX_TypeGerman,
                    ConstantsDatasets.INDEX_TypeBotanical); // Finds correlating value using the available one
        }

        // Translate species
        if (traitIsDefault[ConstantsDatasets.INDEX_SpeciesGerman] && !traitIsDefault[ConstantsDatasets.INDEX_SpeciesBotanical])
        {
            // Botanical species available, but not German
            traits[ConstantsDatasets.INDEX_SpeciesGerman] = treeController.findCorrelatingVariable(clusteredListBySpecies,
                    traits[ConstantsDatasets.INDEX_SpeciesBotanical], ConstantsDatasets.INDEX_SpeciesBotanical,
                    ConstantsDatasets.INDEX_SpeciesGerman); // Finds correlating value using the available one
        } else if (!traitIsDefault[ConstantsDatasets.INDEX_SpeciesGerman] && traitIsDefault[ConstantsDatasets.INDEX_SpeciesBotanical])
        {
            // German species available, but not Botanical
            traits[ConstantsDatasets.INDEX_SpeciesBotanical] = treeController.findCorrelatingVariable(clusteredListBySpecies,
                    traits[ConstantsDatasets.INDEX_SpeciesGerman], ConstantsDatasets.INDEX_SpeciesGerman,
                    ConstantsDatasets.INDEX_SpeciesBotanical); // Finds correlating value using the available one
        }

        // Mitchell Formula
        if (traitIsDefault[ConstantsDatasets.INDEX_Age] && !traitIsDefault[ConstantsDatasets.INDEX_CircumferenceCM])
        {
            // Age missing, but circumference available
            traits[ConstantsDatasets.INDEX_Age] = String.valueOf(
                    Mathematics.mitchellFormulaGetAge(Integer.parseInt(traits[ConstantsDatasets.INDEX_CircumferenceCM]))
            );// Mitchell formula application using circumference
        } else if (!traitIsDefault[ConstantsDatasets.INDEX_Age] && traitIsDefault[ConstantsDatasets.INDEX_CircumferenceCM])
        {
            // Circumference missing, but age available
            traits[ConstantsDatasets.INDEX_CircumferenceCM] = String.valueOf(
                    Mathematics.mitchellFormulaGetGirthCM(Integer.parseInt(traits[ConstantsDatasets.INDEX_Age]))
            );// Mitchell formula application using age
        } else if (!traitIsDefault[ConstantsDatasets.INDEX_YearOfPlantation] && traitIsDefault[ConstantsDatasets.INDEX_CircumferenceCM])
        {
            // Circumference missing, but year of plantation available
            traits[ConstantsDatasets.INDEX_Age] = String.valueOf(
                    Mathematics.subtraction(
                            ConstantsCSV.DATASET_YEAR, Integer.parseInt(traits[ConstantsDatasets.INDEX_YearOfPlantation])
                    )
            ); // Determines age
            traits[ConstantsDatasets.INDEX_CircumferenceCM] = String.valueOf(
                    Mathematics.mitchellFormulaGetGirthCM(Integer.parseInt(traits[ConstantsDatasets.INDEX_Age]))
            ); // Mitchell formula application using age
        }

        // Default tree (last option)
        // If any trait is still default, it gets the default tree's traits
        traitIsDefault = checkWhetherTraitsDefault(traits);
        if (traitIsDefault[ConstantsDatasets.INDEX_YearOfPlantation]) traits[ConstantsDatasets.INDEX_YearOfPlantation] =
                defaultTree.getTrait(ConstantsDatasets.INDEX_YearOfPlantation);
        if (traitIsDefault[ConstantsDatasets.INDEX_Age]) traits[ConstantsDatasets.INDEX_Age] =
                defaultTree.getTrait(ConstantsDatasets.INDEX_Age);
        if (traitIsDefault[ConstantsDatasets.INDEX_CroneDiameterM]) traits[ConstantsDatasets.INDEX_CroneDiameterM] =
                defaultTree.getTrait(ConstantsDatasets.INDEX_CroneDiameterM);
        if (traitIsDefault[ConstantsDatasets.INDEX_CircumferenceCM]) traits[ConstantsDatasets.INDEX_CircumferenceCM] =
                defaultTree.getTrait(ConstantsDatasets.INDEX_CircumferenceCM);
        if (traitIsDefault[ConstantsDatasets.INDEX_HeightM]) traits[ConstantsDatasets.INDEX_HeightM] =
                defaultTree.getTrait(ConstantsDatasets.INDEX_HeightM);

        return traits;
    }


    /**
     * Filters out forbidden values from the traits array and replaces them with default values.
     *
     * @param traits The array of traits to be filtered.
     *               ({@code traits} != {@code null})
     * @return The returned array contains no forbidden values as specified by ConstantsTreeRestrictions.
     */
    private String[] filterStrings (String[] traits)
    {
        // Checks whether any value in traits array equals to a "forbidden" value
        for (int i = (int) ConstantsMath.ZERO; i < ConstantsTreeRestrictions.excludeValues.length; i++)
        {
            if (traits[ConstantsDatasets.INDEX_Name].equalsIgnoreCase(ConstantsTreeRestrictions.excludeValues[i]))
                traits[ConstantsDatasets.INDEX_Name] = String.valueOf(ConstantsCSV.NUMBER_DEFAULT_VALUE);
            if (traits[ConstantsDatasets.INDEX_TypeGerman].equalsIgnoreCase(ConstantsTreeRestrictions.excludeValues[i]))
                traits[ConstantsDatasets.INDEX_TypeGerman] = String.valueOf(ConstantsCSV.NUMBER_DEFAULT_VALUE);
            if (traits[ConstantsDatasets.INDEX_TypeBotanical].equalsIgnoreCase(ConstantsTreeRestrictions.excludeValues[i]))
                traits[ConstantsDatasets.INDEX_TypeBotanical] = String.valueOf(ConstantsCSV.NUMBER_DEFAULT_VALUE);
            if (traits[ConstantsDatasets.INDEX_SpeciesGerman].equalsIgnoreCase(ConstantsTreeRestrictions.excludeValues[i]))
                traits[ConstantsDatasets.INDEX_SpeciesGerman] = String.valueOf(ConstantsCSV.NUMBER_DEFAULT_VALUE);
            if (traits[ConstantsDatasets.INDEX_SpeciesBotanical].equalsIgnoreCase(ConstantsTreeRestrictions.excludeValues[i]))
                traits[ConstantsDatasets.INDEX_SpeciesBotanical] = String.valueOf(ConstantsCSV.NUMBER_DEFAULT_VALUE);
            if (traits[ConstantsDatasets.INDEX_District].equalsIgnoreCase(ConstantsTreeRestrictions.excludeValues[i]))
                traits[ConstantsDatasets.INDEX_District] = String.valueOf(ConstantsCSV.NUMBER_DEFAULT_VALUE);
        }

        return traits;
    }


    /**
     * Determines whether the given traits array needs repairing.
     *
     * @param traits The array of traits to be checked.
     *               ({@code traits} != {@code null})
     * @return {@code true} if any value in the traits array is a default value; {@code false} otherwise.
     */
    private boolean needsRepairing (String[] traits)
    {
        boolean[] traitIsDefault = checkWhetherTraitsDefault(traits);

        // If any value in traits array is default, then repairing is needed
        for (int i = (int) ConstantsMath.ZERO; i < ConstantsDatasets.AMOUNT_OF_ATTRIBUTES; i++)
        {
            if (traitIsDefault[i]) return ConstantsMath.bTrue;
        }
        return ConstantsMath.bFalse;
    }


    /**
     * Determines whether the given traits array is repairable.
     *
     * @param traits The array of traits to be checked.
     *               ({@code traits} != {@code null})
     * @return {@code true} if the traits array is repairable; {@code false} otherwise.
     */
    private boolean isRepairable (String[] traits)
    {
        boolean[] traitIsDefault = checkWhetherTraitsDefault(traits);

        // Is irrepairable if:
        // Cannot be translated
        if (traitIsDefault[ConstantsDatasets.INDEX_TypeGerman] && traitIsDefault[ConstantsDatasets.INDEX_TypeBotanical])
            return ConstantsMath.bFalse;
        // Cannot be translated
        if (traitIsDefault[ConstantsDatasets.INDEX_SpeciesGerman] && traitIsDefault[ConstantsDatasets.INDEX_SpeciesBotanical])
            return ConstantsMath.bFalse;
        // Name or location missing
        if (traitIsDefault[ConstantsDatasets.INDEX_Name] || traitIsDefault[ConstantsDatasets.INDEX_District])
            return ConstantsMath.bFalse;

        // Otherwise repairable
        return ConstantsMath.bTrue;
    }


    /**
     * Checks whether each trait in the given traits array is default.
     *
     * @param traits The array of traits to be checked.
     *               ({@code traits} != {@code null})
     * @return The returned array contains {@code true} at index i if the corresponding trait is default, otherwise {@code false}.
     */
    private boolean[] checkWhetherTraitsDefault (String[] traits)
    {
        boolean[] traitIsDefault = new boolean[ConstantsDatasets.AMOUNT_OF_ATTRIBUTES];

        // Determines whether String array has default values
        for (int i = (int) ConstantsMath.ZERO; i < ConstantsDatasets.AMOUNT_OF_ATTRIBUTES; i++)
        {
            if (traits[i].equals(String.valueOf(ConstantsCSV.NUMBER_DEFAULT_VALUE)))
            {
                // True if current trait equals default value
                traitIsDefault[i] = ConstantsMath.bTrue;
            } else if (traits[i].equals(String.valueOf((float) ConstantsCSV.NUMBER_DEFAULT_VALUE)))
            {
                // Float value contains a dot, thus needs its own case
                traitIsDefault[i] = ConstantsMath.bTrue;
            } else
            {
                traitIsDefault[i] = ConstantsMath.bFalse;
            }
        }
        return traitIsDefault;
    }


    /**
     * Retrieves the count of deleted items during data fixing.
     *
     * @return The count of deleted items.
     */
    public static int getDeletedCounter ()
    {
        return deletedCounter;
    }


    /**
     * Retrieves the count of corrupt items during data fixing.
     *
     * @return The count of corrupt items.
     */
    public static int getCorruptCounter ()
    {
        return corruptCounter;
    }


    /**
     * Retrieves the count of repaired items during data fixing.
     *
     * @return The count of repaired items.
     */
    public static int getRepairedCounter ()
    {
        return repairedCounter;
    }
}