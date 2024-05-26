package utility.constants;


public interface ConstantsDatasets
{
    String FILE_PATH = "src/resources/Baumkataster_Berlin.csv"; // Path to datasets
    char SPLITTER_CHARACTER = ';'; // Splits each line into cells
    char IGNORE_IN = '"'; // Ignores splitter character when surrounded by this character
    int AMOUNT_OF_ATTRIBUTES = 12; // Amount of attributes in CSV sheet
    int LINE_LIMIT_ONE_INSTANCE = 2; // Hard limit adding lines to string
    int HARDLIMIT_LINE_READINGS = 999999; // Hard limit in order to avoid infinite loop


    // CSV trait indices
    int INDEX_ID = 0;
    int INDEX_Name = 1;
    int INDEX_TypeGerman = 2;
    int INDEX_TypeBotanical = 3;
    int INDEX_SpeciesGerman = 4;
    int INDEX_SpeciesBotanical = 5;
    int INDEX_YearOfPlantation = 6;
    int INDEX_Age = 7;
    int INDEX_CroneDiameterM = 8;
    int INDEX_CircumferenceCM = 9;
    int INDEX_HeightM = 10;
    int INDEX_District = 11;
}
