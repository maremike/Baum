package model;


import model.exceptions.InvalidTraitException;
import utility.constants.ConstantsCSV;
import utility.constants.ConstantsDatasets;
import utility.constants.ConstantsMath;
import utility.constants.ConstantsTreeRestrictions;


/**
 * The Tree class provides the structure for creating an instance of the tree datasets.
 * It makes sure the data is withing certain boundaries, and throws and exception if something is wrong.
 *
 * @author Michael Markov
 * @version v2
 */
public class Tree
{
    private int id;
    private PlantInformation plantInformation;
    private Age age;
    private PlantMeasurements plantMeasurements;
    private Location location;
    boolean isDeciduous;


    /**
     * Constructor for creating a Tree object with specified age, crone diameter, circumference, and height.
     *
     * @param age             The age of the tree.
     * @param croneDiameterM  The diameter of the tree's crone in meters.
     * @param circumferenceCM The circumference of the tree in centimeters.
     * @param heightM         The height of the tree in meters.
     */
    public Tree (int age, double croneDiameterM, int circumferenceCM, float heightM)
    {
        this.plantInformation = new PlantInformation();
        this.age = new Age(age);
        this.plantMeasurements = new PlantMeasurements(croneDiameterM, circumferenceCM, heightM);
        this.location = new Location();
    }


    /**
     * Constructor for creating a Tree object from an array of traits.
     * The array must contain valid traits for the tree.
     *
     * @param traits An array containing traits of the tree.
     * @throws InvalidTraitException if the traits array contains invalid or null values.
     */
    public Tree (String[] traits) throws InvalidTraitException
    {
        init(traits);
    }


    /**
     * Constructor for creating a Tree object from an array of traits and specifying if it's deciduous.
     * The array must contain valid traits for the tree.
     *
     * @param traits      An array containing traits of the tree.
     * @param isDeciduous A boolean indicating whether the tree is deciduous.
     * @throws InvalidTraitException if the traits array contains invalid or null values.
     */
    public Tree (String[] traits, boolean isDeciduous) throws InvalidTraitException
    {
        init(traits);
        this.isDeciduous = isDeciduous;
    }


    /**
     * Initializes the Tree object with traits from the provided array.
     * This method validates the traits and sets up the Tree object accordingly.
     *
     * @param traits An array containing traits of the tree.
     * @throws InvalidTraitException if the traits array contains invalid or null values.
     */
    private void init (String[] traits) throws InvalidTraitException
    {
        for (int i = (int) ConstantsMath.ZERO; i < traits.length; i++) // Iterates through traits
        {
            if (traits[i] == null) throw new InvalidTraitException(i); // Throws exception if one of the traits is null
        }

        // Checks whether number is not convertible to Integer
        if (!isParsable(traits[ConstantsDatasets.INDEX_YearOfPlantation], Integer.class))
        {
            throw new InvalidTraitException(ConstantsDatasets.INDEX_YearOfPlantation);
        } else if (
                Integer.parseInt(traits[ConstantsDatasets.INDEX_YearOfPlantation]) > ConstantsTreeRestrictions.MAX_yearOfPlanting ||
                        (
                                Integer.parseInt(traits[ConstantsDatasets.INDEX_YearOfPlantation]) < ConstantsTreeRestrictions.MIN_yearOfPlanting &&
                                        Integer.parseInt(traits[ConstantsDatasets.INDEX_YearOfPlantation]) != ConstantsCSV.NUMBER_DEFAULT_VALUE
                        )
        ) // Checks whether number exceeds maximum or is below minimum (excluding default value)
        {
            throw new InvalidTraitException(ConstantsDatasets.INDEX_YearOfPlantation);
        }

        // Checks whether age is not convertible to Integer
        if (!isParsable(traits[ConstantsDatasets.INDEX_Age], Integer.class))
        {
            throw new InvalidTraitException(ConstantsDatasets.INDEX_Age);
        } else if (
                Integer.parseInt(traits[ConstantsDatasets.INDEX_Age]) > ConstantsTreeRestrictions.MAX_age ||
                        (
                                Integer.parseInt(traits[ConstantsDatasets.INDEX_Age]) < ConstantsTreeRestrictions.MIN_age &&
                                        Integer.parseInt(traits[ConstantsDatasets.INDEX_Age]) != ConstantsCSV.NUMBER_DEFAULT_VALUE
                        )
        ) // Checks whether number exceeds maximum or is below minimum (excluding default value)
        {
            throw new InvalidTraitException(ConstantsDatasets.INDEX_Age);
        }

        // Checks whether number is not convertible to Double
        if (!isParsable(traits[ConstantsDatasets.INDEX_CroneDiameterM], Double.class))
        {
            throw new InvalidTraitException(ConstantsDatasets.INDEX_CroneDiameterM);
        } else if (
                Double.parseDouble(traits[ConstantsDatasets.INDEX_CroneDiameterM]) > ConstantsTreeRestrictions.MAX_croneDiameterM ||
                        (
                                Double.parseDouble(traits[ConstantsDatasets.INDEX_CroneDiameterM]) < ConstantsTreeRestrictions.MIN_MEASUREMENT_VALUE &&
                                        Double.parseDouble(traits[ConstantsDatasets.INDEX_CroneDiameterM]) != ConstantsCSV.NUMBER_DEFAULT_VALUE
                        )
        ) // Checks whether number exceeds maximum or is below minimum (excluding default value)
        {
            throw new InvalidTraitException(ConstantsDatasets.INDEX_CroneDiameterM);
        }

        // Checks whether number is not convertible to Integer
        if (!isParsable(traits[ConstantsDatasets.INDEX_CircumferenceCM], Integer.class))
        {
            throw new InvalidTraitException(ConstantsDatasets.INDEX_CircumferenceCM);
        } else if (
                Integer.parseInt(traits[ConstantsDatasets.INDEX_CircumferenceCM]) > ConstantsTreeRestrictions.MAX_circumferenceCM ||
                        (
                                Integer.parseInt(traits[ConstantsDatasets.INDEX_CircumferenceCM]) < ConstantsTreeRestrictions.MIN_MEASUREMENT_VALUE &&
                                        Integer.parseInt(traits[ConstantsDatasets.INDEX_CircumferenceCM]) != ConstantsCSV.NUMBER_DEFAULT_VALUE
                        )
        ) // Checks whether number exceeds maximum or is below minimum (excluding default value)
        {
            throw new InvalidTraitException(ConstantsDatasets.INDEX_CircumferenceCM);
        }

        // Checks whether number is not convertible to Float
        if (!isParsable(traits[ConstantsDatasets.INDEX_HeightM], Float.class))
        {
            throw new InvalidTraitException(ConstantsDatasets.INDEX_HeightM);
        } else if (
                Float.parseFloat(traits[ConstantsDatasets.INDEX_HeightM]) > ConstantsTreeRestrictions.MAX_heightM ||
                        (
                                Float.parseFloat(traits[ConstantsDatasets.INDEX_HeightM]) < ConstantsTreeRestrictions.MIN_MEASUREMENT_VALUE &&
                                        Float.parseFloat(traits[ConstantsDatasets.INDEX_HeightM]) != ConstantsCSV.NUMBER_DEFAULT_VALUE
                        )
        ) // Checks whether number exceeds maximum or is below minimum (excluding default value)
        {
            throw new InvalidTraitException(ConstantsDatasets.INDEX_HeightM);
        }

        // Else accepts values
        this.id = Integer.parseInt(traits[ConstantsDatasets.INDEX_ID]);
        this.plantInformation = new PlantInformation(
                traits[ConstantsDatasets.INDEX_Name],
                traits[ConstantsDatasets.INDEX_TypeGerman],
                traits[ConstantsDatasets.INDEX_TypeBotanical],
                traits[ConstantsDatasets.INDEX_SpeciesGerman],
                traits[ConstantsDatasets.INDEX_SpeciesBotanical]
        );
        this.age = new Age(
                Integer.parseInt(traits[ConstantsDatasets.INDEX_YearOfPlantation]),
                Integer.parseInt(traits[ConstantsDatasets.INDEX_Age])
        );
        this.plantMeasurements = new PlantMeasurements(
                Double.parseDouble(traits[ConstantsDatasets.INDEX_CroneDiameterM]),
                Integer.parseInt(traits[ConstantsDatasets.INDEX_CircumferenceCM]),
                Float.parseFloat(traits[ConstantsDatasets.INDEX_HeightM]));
        this.location = new Location(traits[ConstantsDatasets.INDEX_District]);
    }


    /**
     * Checks if the provided value is parsable to the specified target type.
     *
     * @param value      The value to be parsed.
     * @param targetType The target type to parse the value into (Integer, Float, Double).
     * @return true if the value is parsable to the target type, false otherwise.
     */
    private boolean isParsable (String value, Class<?> targetType)
    {
        try
        {
            if (targetType == Integer.class) // Parsable to Integer?
            {
                Integer.parseInt(value);
            } else if (targetType == Float.class) // Parsable to Float?
            {
                Float.parseFloat(value);
            } else if (targetType == Double.class) // Parsable to Double?
            {
                Double.parseDouble(value);
            }

            return ConstantsMath.bTrue; // Successfully parsed
        } catch (NumberFormatException e)
        {
            return ConstantsMath.bFalse; // Parsing failed
        }
    }


    /**
     * Retrieves the trait value at the specified index.
     *
     * @param index The index of the trait to retrieve.
     * @return The trait value at the specified index.
     */
    public String getTrait (int index)
    {
        String[] traits = {String.valueOf(getID()), getName(), getTypeGerman(), getTypeBotanical(),
                getSpeciesGerman(), getSpeciesBotanical(), String.valueOf(getYearOfPlantation()),
                String.valueOf(getAge()), String.valueOf(getCroneDiameterM()), String.valueOf(getCircumferenceCM()),
                String.valueOf(getHeightM()), getDistrict()};
        return traits[index];
    }


    /**
     * Retrieves all traits of the tree as an array.
     *
     * @return An array containing all traits of the tree.
     */
    public String[] getTraits ()
    {
        return new String[]{String.valueOf(getID()), getName(), getTypeGerman(), getTypeBotanical(),
                getSpeciesGerman(), getSpeciesBotanical(), String.valueOf(getYearOfPlantation()),
                String.valueOf(getAge()), String.valueOf(getCroneDiameterM()), String.valueOf(getCircumferenceCM()),
                String.valueOf(getHeightM()), getDistrict()};
    }


    /**
     * Indicates whether the tree is deciduous.
     *
     * @return true if the tree is deciduous, otherwise false.
     */
    public boolean getIsDeciduous ()
    {
        return this.isDeciduous;
    }


    /**
     * Getter for the ID of the tree.
     *
     * @return The number of the tree.
     */
    public int getID ()
    {
        return this.id;
    }


    /**
     * Getter for the name of the tree.
     *
     * @return The name of the tree.
     */
    public String getName ()
    {
        return this.plantInformation.getName();
    }


    /**
     * Getter for the German name of the type of tree.
     *
     * @return The German name of the type of tree.
     */
    public String getTypeGerman ()
    {
        return this.plantInformation.getTypeGerman();
    }


    /**
     * Getter for the Botanical name of the type of tree.
     *
     * @return The Botanical name of the type of tree.
     */
    public String getTypeBotanical ()
    {
        return this.plantInformation.getTypeBotanical();
    }


    /**
     * Getter for the German name of the tree species.
     *
     * @return The German name of the tree species.
     */
    public String getSpeciesGerman ()
    {
        return this.plantInformation.getSpeciesGerman();
    }


    /**
     * Getter for the Botanical name of the tree species.
     *
     * @return The Botanical name of the tree species.
     */
    public String getSpeciesBotanical ()
    {
        return this.plantInformation.getSpeciesBotanical();
    }


    /**
     * Getter for the year of the tree's plantation.
     *
     * @return The year of the tree's plantation.
     */
    public int getYearOfPlantation ()
    {
        return age.getYearOfPlantation();
    }


    /**
     * Getter for the age of the tree.
     *
     * @return The age of the tree.
     */
    public int getAge ()
    {
        return this.age.getAge();
    }


    /**
     * Getter for the diameter (in Meters) of the tree.
     *
     * @return The diameter of the tree in meters.
     */
    public double getCroneDiameterM ()
    {
        return this.plantMeasurements.getCroneDiameterM();
    }


    /**
     * Getter for the perimeter (in Centimeters) of the tree.
     *
     * @return The perimeter of the tree in centimeters.
     */
    public int getCircumferenceCM ()
    {
        return this.plantMeasurements.getCircumferenceCM();
    }


    /**
     * Getter for the height of the tree (in Meters).
     *
     * @return The height of the tree in meters.
     */
    public float getHeightM ()
    {
        return this.plantMeasurements.getHeightM();
    }


    /**
     * Getter for the name of the district at the tree's location.
     *
     * @return The name of the district where the tree is located.
     */
    public String getDistrict ()
    {
        return this.location.getDistrict();
    }
}
