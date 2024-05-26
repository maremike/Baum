package model;


import utility.constants.ConstantsCSV;


/**
 * The Location class represents the geographical location information, specifically the district.
 *
 * @author Michael Markov
 * @version v1
 */
public class Location
{
    private String district = String.valueOf(ConstantsCSV.NUMBER_DEFAULT_VALUE);


    /**
     * Constructs a new Location object with the specified district.
     *
     * @param district The name of the district.
     */
    public Location (String district)
    {
        this.district = district;
    }


    /**
     * Constructs a new Location object.
     * This constructor initializes the location information for a tree.
     */
    public Location ()
    {

    }


    /**
     * Gets the name of the district for this location.
     *
     * @return Returns the name of the district.
     */
    public String getDistrict ()
    {
        return district;
    }
}
