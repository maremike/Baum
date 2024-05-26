package model;


import utility.constants.ConstantsCSV;


/**
 * The PlantMeasurements class offers a way to structure the measurements of a plant.
 * It includes traits like the croneDiameter (meters), circumference (centimetres) and the height (meters).
 *
 * @author Michael Markov
 * @version v1
 */
public class PlantMeasurements
{
    private double croneDiameterM = ConstantsCSV.NUMBER_DEFAULT_VALUE;
    private int circumferenceCM = ConstantsCSV.NUMBER_DEFAULT_VALUE;
    private float heightM = ConstantsCSV.NUMBER_DEFAULT_VALUE;


    /**
     * Constructs a new PlantMeasurements object with the specified measurements.
     *
     * @param croneDiameterM  The crone diameter of the tree in meters.
     * @param circumferenceCM The circumference of the tree in centimeters.
     * @param heightM         The height of the tree in meters.
     */
    public PlantMeasurements (double croneDiameterM, int circumferenceCM, float heightM)
    {
        this.croneDiameterM = croneDiameterM;
        this.circumferenceCM = circumferenceCM;
        this.heightM = heightM;
    }


    /**
     * Constructs a new PlantMeasurements object with default values.
     */
    public PlantMeasurements ()
    {

    }


    /**
     * Returns the crone diameter of the tree.
     *
     * @return The crone diameter of the tree in meters.
     */
    public double getCroneDiameterM ()
    {
        return croneDiameterM;
    }


    /**
     * Returns the circumference of the tree.
     *
     * @return The circumference of the tree in centimeters.
     */
    public int getCircumferenceCM ()
    {
        return circumferenceCM;
    }


    /**
     * Returns the height of the tree.
     *
     * @return The height of the tree in meters.
     */
    public float getHeightM ()
    {
        return heightM;
    }
}
