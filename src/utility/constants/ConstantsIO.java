package utility.constants;


public interface ConstantsIO
{
    int answerCharactersUntilNewLine = 20; // Number of characters until new line upon putting out an answer


    // Console feedback
    // Main
    String tab = "\t";
    String newLine = "\n";
    String slash = "/";
    String millisecond = "ms";
    String meter = "m";
    String centimeter = "cm";

    // Exception messages
    String illegalInputMessage = "Following input not allowed: ";
    String invalidDataset = "Invalid dataset. Skipping line:";

    // Process of reading datasets
    String timeTaken = "Time taken:";
    String readingFileAndCreatingInstances = "Reading file and creating instances...";
    String amountOfInstancesCreated = "Amount of instances created:";
    String repairProcessInitiating = "Repair process initiating...";
    String amountOfCorruptInstances = "Amount of corrupt instances:";
    String instancesDeleted = "instances deleted.";
    String instancesRepaired = "instances repaired.";
    String amountOfInstancesLeft = "Amount of instances left:";
    String pleaseForExpectedInput = "Please enter a number from 0 up to 13.";


    // Output tree format
    String numberInfo = "Tree number:";
    String nameInfo = "Tree name:";
    String typeInfo = "Type (German/Latin):";
    String speciesInfo = "Species (German/Latin):";
    String yearInfo = "Year of plantation:";
    String ageInfo = "Age:";
    String croneDiameterInfo = "Crone diameter:";
    String circumferenceInfo = "Circumference:";
    String heightInfo = "Height:";
    String districtInfo = "The tree is located in the district:";


    // Console questions, answers, and inputs
    String questionNumber0 = "0";
    String question0 = "Welcher Bezirk hat die meisten Bäume?";
    String answer0 = "Die meisten Bäume liegen im Bezirk:";

    String questionNumber1 = "1";
    String question1 = "In welchem Bezirk steht der höchste Baum?";
    String answer1 = "Der höchste Baum steht im Bezirk: ";

    String questionNumber2 = "2";
    String question2 = "Welcher Baum hat den größten Umfang?";
    String answer2 = "Den größten Umfang hat der Baum:";

    String questionNumber3 = "3";
    String question3 = "Welcher Baum hat die größte Krone?";
    String answer3 = "Die größte Krone hat der Baum:";

    String questionNumber4 = "4";
    String question4 = "Welcher Baum ist der älteste?";
    String answer4 = "Der älteste Baum ist:";

    String questionNumber5 = "5";
    String question5 = "Wie viele Baumarten gibt es?";
    String answer5 = "Gesamte Anzahl von Baumarten:";

    String questionNumber6 = "6";
    String question6 = "Wie viele Gattungen gibt es?";
    String answer6 = "Gesamte Anzahl von Gattungen:";

    String questionNumber7 = "7";
    String question7 = "Welche Gattung kommt am häufigsten vor?";
    String answer7 = "Die am häufigsten vorkommende Gattung ist:";

    String questionNumber8 = "8";
    String question8 = "In welchem Bezirk stehen die meisten Baumarten?";
    String answer8 = "Die meisten Baumarten stehen im Bezirk:";

    String questionNumber9 = "9";
    String question9 = "Welche Gattung wächst am höchsten?";
    String answer9 = "Die am häufigsten wachsende Gattung ist:";

    String questionNumber10 = "10";
    String question10 = "Welche Gattung hat den größten Umfang?";
    String answer10 = "Die Gattung mit dem größten Umfang heißt:";

    String questionNumber11 = "11";
    String question11 = "Wie viel Kohlenstoff wurde von den Bäumen in Berlin gespeichert?";
    String answer11 = "Anzahl an Kohlenstoff, der in Berlin in Kilogram gespeichert wurde:";

    String questionNumber12 = "12";
    String question12 = "In welchem Bezirk fand die größte Kohlenstoffspeicherung statt?";
    String answer12 = "Die größte Kohlenstoffspeicherung fand statt im Bezirk:";

    String questionNumber13 = "13";
    String question13 = "Welche Gattung hat die größte Menge an Kohlenstoff gespeichert?";
    String answer13 = "Die größte Menge an Kohlenstoff wurde von der Gattung gespeichert:";
}
