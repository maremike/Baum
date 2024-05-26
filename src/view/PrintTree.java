package view;


import model.Tree;
import utility.constants.ConstantsIO;


/**
 * The PrintTree class provides a method that returns a formatted String that outputs the tree for the user.
 * It allows the user to understand the data.
 *
 * @author Michael Markov
 * @version v1
 */
public class PrintTree
{
    /**
     * Formats tree information into a string containing various attributes such as ID, name, type, species, year of plantation, age, diameter, circumference, height, and district.
     * Each attribute is formatted with a tab and appropriate labels.
     *
     * @param tree The tree object containing information to be formatted.
     * @return The formatted string containing tree information.
     */
    public static String getTree (Tree tree)
    {
        return (ConstantsIO.newLine + ConstantsIO.numberInfo + ConstantsIO.tab + tree.getID() + ConstantsIO.newLine +
                ConstantsIO.nameInfo + ConstantsIO.tab + tree.getName() + ConstantsIO.newLine +
                ConstantsIO.typeInfo + ConstantsIO.tab + tree.getTypeGerman() + ConstantsIO.tab + ConstantsIO.slash + ConstantsIO.tab + tree.getTypeBotanical() + ConstantsIO.newLine +
                ConstantsIO.speciesInfo + ConstantsIO.tab + tree.getSpeciesGerman() + ConstantsIO.tab + ConstantsIO.slash + ConstantsIO.tab + tree.getSpeciesBotanical() + ConstantsIO.newLine +
                ConstantsIO.yearInfo + ConstantsIO.tab + tree.getYearOfPlantation() + ConstantsIO.tab + ConstantsIO.tab + ConstantsIO.ageInfo + ConstantsIO.tab + tree.getAge() + ConstantsIO.newLine +
                ConstantsIO.croneDiameterInfo + ConstantsIO.tab + tree.getCroneDiameterM() + ConstantsIO.meter + ConstantsIO.newLine +
                ConstantsIO.circumferenceInfo + ConstantsIO.tab + tree.getCircumferenceCM() + ConstantsIO.centimeter + ConstantsIO.newLine +
                ConstantsIO.heightInfo + ConstantsIO.tab + tree.getHeightM() + ConstantsIO.meter + ConstantsIO.newLine +
                ConstantsIO.districtInfo + ConstantsIO.tab + tree.getDistrict()
        );
    }
}
