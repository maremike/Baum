package model;


import utility.constants.ConstantsCSV;


/**
 * The PlantInformation class offers a way to structurise the information about a plant.
 * It includes traits like name, type (German/Botanical) and species (German/Botanical).
 *
 * @author Michael Markov
 * @version v1
 */
public class PlantInformation
{
    private String name = String.valueOf(ConstantsCSV.NUMBER_DEFAULT_VALUE);
    private String typeGerman = String.valueOf(ConstantsCSV.NUMBER_DEFAULT_VALUE);
    private String typeBotanical = String.valueOf(ConstantsCSV.NUMBER_DEFAULT_VALUE);
    private String speciesGerman = String.valueOf(ConstantsCSV.NUMBER_DEFAULT_VALUE);
    private String speciesBotanical = String.valueOf(ConstantsCSV.NUMBER_DEFAULT_VALUE);


    /**
     * Constructs a new PlantInformation object with the specified attributes.
     *
     * @param name             The name of the tree.
     * @param typeGerman       The German type of the tree.
     * @param typeBotanical    The botanical type of the tree.
     * @param speciesGerman    The German species of the tree.
     * @param speciesBotanical The botanical species of the tree.
     */
    public PlantInformation (String name, String typeGerman, String typeBotanical, String speciesGerman,
                             String speciesBotanical)
    {
        this.name = name;
        this.speciesGerman = speciesGerman;
        this.speciesBotanical = speciesBotanical;
        this.typeGerman = typeGerman;
        this.typeBotanical = typeBotanical;
    }


    public PlantInformation ()
    {

    }


    /**
     * Gets the name of the tree.
     *
     * @return The name of the tree.
     */
    public String getName ()
    {
        return name;
    }


    /**
     * Gets the German type of the tree.
     *
     * @return The German type of the tree.
     */
    public String getTypeGerman ()
    {
        return typeGerman;
    }


    /**
     * Gets the botanical type of the tree.
     *
     * @return The botanical type of the tree.
     */
    public String getTypeBotanical ()
    {
        return typeBotanical;
    }


    /**
     * Gets the German species of the tree.
     *
     * @return The German species of the tree.
     */
    public String getSpeciesGerman ()
    {
        return speciesGerman;
    }


    /**
     * Gets the botanical species of the tree.
     *
     * @return The botanical species of the tree.
     */
    public String getSpeciesBotanical ()
    {
        return speciesBotanical;
    }
}
