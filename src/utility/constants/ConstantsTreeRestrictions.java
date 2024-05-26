package utility.constants;


public interface ConstantsTreeRestrictions
{
    int MAX_yearOfPlanting = ConstantsCSV.DATASET_YEAR; // Caps maximum year of plantation to dataset year
    int MIN_age = 0; // Minimum cap to age
    int MIN_yearOfPlanting = 1322; // Minimum cap to year of plantation
    int MAX_age = ConstantsCSV.DATASET_YEAR - MIN_yearOfPlanting; // Caps maximum age based on previous values

    int MIN_MEASUREMENT_VALUE = 0; // Minimum allowed value to all variables
    double MAX_croneDiameterM = 2.5f;
    int MAX_circumferenceCM = 780;
    float MAX_heightM = 43;

    String[] excludeValues = {"unbekannt", null, ""}; // Excludes Strings in datasets setting them to default
}