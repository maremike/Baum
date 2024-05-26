package utility;


import control.DataRepair;
import control.IOController;
import model.Tree;
import model.exceptions.IllegalInputException;
import utility.constants.ConstantsDatasets;
import utility.constants.ConstantsIO;
import view.PrintConsole;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * The MyIO class contains the centre of the project - The parser method.
 * Overall the class contains all methods regarding input and output using the console.
 *
 * @author Michael Markov
 * @version v1
 */
public class MyIO
{
    /**
     * Reads a single line of input from the standard input (console) using a Scanner object.
     *
     * @return A String containing the line of input read from the console.
     */
    public static String read ()
    {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }


    /**
     * Reads tree datasets from a CSV file located at the specified path.
     *
     * @param path The path to the CSV file containing tree datasets.
     * @return A List containing tree datasets read from the CSV file.
     */
    public static List readTreeDatasets (String path)
    {
        return CSVReader.getListOfTrees(path);
    }


    /**
     * Presents questions to the user and provides corresponding answers based on the provided tree list.
     * The user's input is processed until the program is terminated.
     *
     * @param treeList A List of Tree objects containing tree datasets.
     */
    public static void questionsAndAnswers (List<Tree> treeList)
    {
        IOController ioController = new IOController();
        PrintConsole printConsole = new PrintConsole();
        System.out.println(ConstantsIO.newLine + printConsole.getQuestions()); // Prints questions
        System.out.println(ConstantsIO.pleaseForExpectedInput + ConstantsIO.newLine); // Shows options that can be entered
        for (; ; )
        {
            String value = MyIO.read();
            try
            {
                System.out.println(ioController.getAnswer(value, treeList)); // Prints answer to selected question
            } catch (IllegalInputException e)
            {
                System.out.println(e.getMessage()); // Wrong input coverage
            } finally
            {
                System.out.println(); // Empty line for better visibility
            }
        }
    }


    /**
     * Initiates the parsing process, reading tree datasets from a file, repairing data, and presenting questions and answers to the user.
     */
    public static void parser ()
    {
        System.out.println(ConstantsIO.readingFileAndCreatingInstances);
        Timer timer = new Timer(); // Starts timer
        List<Tree> treeList = new ArrayList<>(MyIO.readTreeDatasets(ConstantsDatasets.FILE_PATH));
        // Amount of instances created
        System.out.println(ConstantsIO.newLine + ConstantsIO.amountOfInstancesCreated + ConstantsIO.tab + treeList.size());
        // Time
        System.out.println(ConstantsIO.timeTaken + ConstantsIO.tab + timer.timePassed() + ConstantsIO.millisecond);


        System.out.println(ConstantsIO.newLine + ConstantsIO.repairProcessInitiating);
        timer.reset();
        DataRepair dataRepair = new DataRepair();
        treeList = dataRepair.fixData(treeList); // Repairs list
        // Amount of corrupt instances
        System.out.println(ConstantsIO.newLine + ConstantsIO.amountOfCorruptInstances + ConstantsIO.tab + DataRepair.getCorruptCounter());
        // Amount repaired
        System.out.println(DataRepair.getRepairedCounter() + ConstantsIO.tab + ConstantsIO.instancesRepaired);
        // Amount deleted
        System.out.println(DataRepair.getDeletedCounter() + ConstantsIO.tab + ConstantsIO.instancesDeleted);
        // Amount of instances left
        System.out.println(ConstantsIO.amountOfInstancesLeft + ConstantsIO.tab + treeList.size());
        // Time
        System.out.println(ConstantsIO.timeTaken + ConstantsIO.tab + timer.timePassed() + ConstantsIO.millisecond);

        questionsAndAnswers(treeList);
    }
}