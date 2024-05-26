package model;


import control.Mathematics;
import utility.constants.ConstantsCSV;
import utility.constants.ConstantsMath;
import utility.constants.ConstantsTreeRestrictions;


/**
 * The Age class represents information about the age and year of plantation of a tree.
 *
 * @author Michael Markov
 * @version v1
 */
public class Age
{
    private int year = ConstantsCSV.NUMBER_DEFAULT_VALUE;
    private int age = ConstantsCSV.NUMBER_DEFAULT_VALUE;


    /**
     * Constructs an Age object with the specified year of plantation and age.
     *
     * @param year The year of the tree's plantation.
     * @param age  The age of the tree.
     */
    public Age (int year, int age)
    {
        init(year, age);
    }


    /**
     * Constructs an Age object with default age.
     * The default age is set to 0.
     */
    public Age ()
    {
        this.age = (int) ConstantsMath.ZERO;
    }


    /**
     * Constructs an Age object with a specified age.
     *
     * @param age The age of the tree.
     */
    public Age (int age)
    {
        init(year, age);
    }


    /**
     * Initializes the Age object with the given year and age.
     *
     * @param year The year of plantation.
     * @param age  The age of the tree.
     */
    private void init (int year, int age)
    {
        if (!ageCheckCorrect(year, age)) // Checks whether age and year add up rightfully
        {
            if (year != ConstantsCSV.NUMBER_DEFAULT_VALUE && age == ConstantsCSV.NUMBER_DEFAULT_VALUE)
            {
                // Age is default, but year is not
                age = (int) (Mathematics.subtraction(ConstantsCSV.DATASET_YEAR, year));
            } else if (year == ConstantsCSV.NUMBER_DEFAULT_VALUE && age != ConstantsCSV.NUMBER_DEFAULT_VALUE)
            {
                // Year is default, but age is not
                year = (int) (Mathematics.subtraction(ConstantsCSV.DATASET_YEAR, age));
            } else if (year != ConstantsCSV.NUMBER_DEFAULT_VALUE && age != ConstantsCSV.NUMBER_DEFAULT_VALUE)
            {
                // Year and age are not default -> gets year from age
                year = (int) (Mathematics.subtraction(ConstantsTreeRestrictions.MAX_yearOfPlanting, age));
            }
        }
        this.year = year;
        this.age = age;
    }


    /**
     * Checks if the given year and age add up to the dataset year.
     *
     * @param year The year of the tree's plantation.
     * @param age  The age of the tree.
     * @return {@code true} if the sum of the year and age equals the dataset year; {@code false} otherwise.
     */
    public boolean ageCheckCorrect (int year, int age)
    {
        return ((Mathematics.sum(year, age)) == ConstantsCSV.DATASET_YEAR);
    }


    /**
     * Gets the year of the tree's plantation.
     *
     * @return The year of plantation.
     */
    public int getYearOfPlantation ()
    {
        return this.year;
    }


    /**
     * Sets the year of the tree's plantation.
     *
     * @param yearOfPlantation The year to set for the plantation.
     */
    public void setYearOfPlantation (int yearOfPlantation)
    {
        this.year = yearOfPlantation;
    }


    /**
     * Gets the age of the tree.
     *
     * @return The age of the tree.
     */
    public int getAge ()
    {
        return this.age;
    }


    /**
     * Sets the age of the tree.
     *
     * @param age The age to set for the tree.
     */
    public void setAge (int age)
    {
        this.age = age;
    }
}