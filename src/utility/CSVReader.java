package utility;


import control.LineController;
import control.Mathematics;
import model.Line;
import model.Tree;
import model.exceptions.InvalidTraitException;
import utility.constants.ConstantsCSV;
import utility.constants.ConstantsDatasets;
import utility.constants.ConstantsIO;
import utility.constants.ConstantsMath;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * CSVReader class is a utility that can be used to read data entries which are delimited by commas
 *
 * @author Michael Markov
 * @version v1
 */
public class CSVReader
{
    /**
     * Retrieves a list of Tree objects from a CSV file located at the specified path.
     *
     * @param path The path to the CSV file.
     * @return A list containing Tree objects parsed from the CSV file.
     * @throws RuntimeException if an I/O error occurs.
     */
    public static List<Tree> getListOfTrees (String path)
    {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path)))
        {
            List<Tree> treeList = new ArrayList<>();
            LineController lineController = new LineController();
            CSVReader csvReader = new CSVReader();
            String line;

            // Reads next line until the line is empty
            for (int i = (int) ConstantsMath.ZERO; i <= ConstantsDatasets.HARDLIMIT_LINE_READINGS && (line = bufferedReader.readLine()) != null; i++)
            {
                // Adds up next lines until the amount of splitter characters is equal to the amount of attributes -1 ignoring a specific character
                for (int j = (int) ConstantsMath.ZERO; j <= ConstantsDatasets.LINE_LIMIT_ONE_INSTANCE &&
                        (lineController.countSpecificCharacterIgnoringThoseEnclosedInAnotherCharacter(
                                line, ConstantsDatasets.SPLITTER_CHARACTER, ConstantsDatasets.IGNORE_IN) <
                                Mathematics.subtraction(ConstantsDatasets.AMOUNT_OF_ATTRIBUTES, ConstantsMath.ONE)); j++
                )
                {
                    line += bufferedReader.readLine();
                }

                try
                {
                    treeList.add(
                            csvReader.getTree(lineController.createLineFromString(line), ConstantsDatasets.SPLITTER_CHARACTER, ConstantsDatasets.IGNORE_IN)
                    ); // Adds trees from line to list
                } catch (NumberFormatException | NullPointerException e)
                {
                    System.out.println(ConstantsIO.invalidDataset + ConstantsIO.tab + i);
                }
            }
            return treeList;
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }


    /**
     * Parses a line of data into a Tree object using the specified splitter and ignore characters.
     *
     * @param line     The line of data to parse.
     * @param splitter The character used to split the attributes in the line.
     * @param ignoreIn The character to ignore when parsing attributes.
     * @return A Tree object created from the parsed attributes.
     */
    public Tree getTree (Line line, char splitter, char ignoreIn)
    {
        String[] attributes = new String[ConstantsDatasets.AMOUNT_OF_ATTRIBUTES];
        int nextFreeIndex = (int) ConstantsMath.ZERO;
        boolean characterEven = ConstantsMath.bFalse;
        int startIndex = (int) ConstantsMath.ZERO;

        for (int i = (int) ConstantsMath.ZERO; i < line.getLine().length(); i++)
        {
            // If finds ignoreIn, skips all indeces until ignoreIn will be found again
            if (line.getLine().charAt(i) == ignoreIn && characterEven)
            {
                characterEven = ConstantsMath.bFalse;
            } else if (line.getLine().charAt(i) == ignoreIn || characterEven)
            {
                characterEven = ConstantsMath.bTrue;
                continue;
            }

            if (line.getLine().charAt(i) == splitter)
            {
                if (i == startIndex) // Start-index is a splitter -> no value between two splitter characters
                {
                    attributes[nextFreeIndex++] = null;
                } else // Handle multiple characters between semicolons
                {
                    attributes[nextFreeIndex++] = line.getLine().substring(startIndex, i);
                }
                // Set startIndex to one value after the splitter character
                startIndex = (int) (Mathematics.sum(i, (int) ConstantsMath.ONE));
            }
            // Handle last value (no splitter)
            attributes[nextFreeIndex] = line.getLine().substring(startIndex, (int) (Mathematics.sum(i, ConstantsMath.ONE)));
        }

        // Loops until either a tree can be returned or hard limit reached
        for (int counter = (int) ConstantsMath.ZERO; counter < ConstantsCSV.HARDLIMIT_EXCEPTIONHANDLING_FOR_ONE_DATASET; counter++)
        {
            try
            {
                return new Tree(attributes);
            } catch (InvalidTraitException e)
            {
                attributes[e.getTraitNumber()] = String.valueOf(ConstantsCSV.NUMBER_DEFAULT_VALUE);
            }
        }
        return null;
    }
}
