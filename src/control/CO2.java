package control;


import model.Tree;
import model.exceptions.LookUpTableException;
import resources.TreeCarbonData;
import utility.constants.ConstantsCO2;
import utility.constants.ConstantsMath;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * The CO2 class provides methods for calculating the carbon saved by trees.
 *
 * @author Michael Markov
 * @version 1
 */
public class CO2
{
    /**
     * Generates a mapping of trees to their respective carbon storage values.
     *
     * @param treeList The list of trees for which carbon storage is calculated.
     *                 ({@code treeList} != {@code null})
     * @return A mapping of trees to their carbon storage values.
     */
    public Map<Tree, Double> getMapOfTreeAndCarbon (List<Tree> treeList)
    {
        Map<Tree, Double> treeAndCarbonMap = new HashMap<>();
        for (Tree tree : treeList) // Iterate through treeList
        {
            double co2;
            try
            {
                co2 = getAdjustedCO2FromTree(tree); // Calculate CO2 storage
            } catch (LookUpTableException ignored)
            {
                continue;
            }
            treeAndCarbonMap.put(tree, co2); // Put the tree and its carbon storage into the map
        }
        return treeAndCarbonMap;
    }


    /**
     * Computes the total carbon dioxide stored by the given list of trees.
     *
     * @param treeList The list of trees for which total carbon dioxide storage is calculated.
     *                 ({@code treeList} != {@code null})
     * @return The total carbon dioxide stored by the trees.
     */
    public Double sumOfCO2StoredByTrees (List<Tree> treeList)
    {
        double totalCO2 = (int) ConstantsMath.ZERO;
        for (Tree tree : treeList)
        {
            try
            {
                // Adds the CO2 values from current tree to previous values
                totalCO2 = Mathematics.sum(totalCO2, getAdjustedCO2FromTree(tree));
            } catch (LookUpTableException ignored)
            {

            }
        }
        return totalCO2;
    }


    /**
     * Calculates the adjusted carbon dioxide storage from a single tree based on its characteristics.
     *
     * @param tree The tree for which adjusted carbon dioxide storage is calculated.
     *             ({@code tree} != {@code null})
     * @return The adjusted carbon dioxide storage value for the tree.
     * @throws LookUpTableException If an error occurs while looking up carbon storage values.
     */
    public Integer getAdjustedCO2FromTree (Tree tree) throws LookUpTableException
    {
        // Variable too big for look-up table, but will still be counted in using the nearest available value
        double heightM = (Mathematics.upperLimit(Math.round(tree.getHeightM()), ConstantsCO2.maximumHeightM));
        double logDiameterCM = (Mathematics.upperLimit(Math.round((Mathematics.getDiameterFromPerimeter(tree.getCircumferenceCM()))), ConstantsCO2.maximumLogDiameterCM));

        // Adjusts height value to index value to fit for look-up table
        int heightIndex = (int) (Mathematics.subtraction(heightM, ConstantsCO2.subtrahendLookUpTableHeight));
        int logDiameterIndex = (int) (Mathematics.subtraction(logDiameterCM, ConstantsCO2.subtrahendLookUpTableLogDiameter));

        // Index below zero (tree too small to be counted in)
        if (heightIndex < ConstantsMath.ZERO) throw new LookUpTableException();
        if (logDiameterIndex < ConstantsMath.ZERO) throw new LookUpTableException();

        double co2 = getCO2FromTree(tree, logDiameterIndex, heightIndex);

        // Hinders 0 value from getting counted in, which throws off average values
        if (co2 == ConstantsMath.ZERO) throw new LookUpTableException();

        return (int) Math.round(co2);
    }


    /**
     * Computes the carbon dioxide storage from a single tree based on its characteristics and indices.
     *
     * @param tree             The tree for which carbon dioxide storage is calculated.
     *                         ({@code tree} != {@code null})
     * @param logDiameterIndex The index of the log diameter in the lookup table.
     *                         ({@code logDiameterIndex} valid index for lookup table)
     * @param heightIndex      The index of the height in the lookup table.
     *                         ({@code heightIndex} valid index for lookup table)
     * @return The carbon dioxide storage value for the tree.
     * @throws LookUpTableException If an error occurs while looking up carbon storage values.
     */
    private static double getCO2FromTree (Tree tree, int logDiameterIndex, int heightIndex) throws LookUpTableException
    {
        double co2;
        if (tree.getIsDeciduous())
        {
            // Index out of bounds of look-up table
            if (logDiameterIndex >= TreeCarbonData.pineCarbonByHeightAndCircumference[heightIndex].length)
                throw new LookUpTableException();
            if (logDiameterIndex >= TreeCarbonData.spruceCarbonByHeightAndCircumference[heightIndex].length)
                throw new LookUpTableException();

            // Value is equal to the average of the deciduous values from the look-up tables
            co2 = (Mathematics.average(TreeCarbonData.pineCarbonByHeightAndCircumference[heightIndex][logDiameterIndex],
                    TreeCarbonData.spruceCarbonByHeightAndCircumference[heightIndex][logDiameterIndex]));
        } else
        {
            // Index out of bounds of look-up table
            if (logDiameterIndex >= TreeCarbonData.beechCarbonByHeightAndCircumference[heightIndex].length)
                throw new LookUpTableException();
            if (logDiameterIndex >= TreeCarbonData.oakCarbonByHeightAndCircumference[heightIndex].length)
                throw new LookUpTableException();

            // Value is equal to the average of the coniferous values from the look-up tables
            co2 = (Mathematics.average(TreeCarbonData.beechCarbonByHeightAndCircumference[heightIndex][logDiameterIndex],
                    TreeCarbonData.oakCarbonByHeightAndCircumference[heightIndex][logDiameterIndex]));
        }
        return co2;
    }
}
