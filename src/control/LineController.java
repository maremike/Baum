package control;


import model.Line;
import utility.constants.ConstantsMath;


/**
 * The LineController class provides methods for handling and manipulating lines of text.
 *
 * @author Michael Markov
 * @version 1
 */
public class LineController
{
    Line line = new Line();


    /**
     * Creates a Line object from a given string.
     *
     * @param line The string to create the Line object from.
     *             ({@code line} != {@code null})
     * @return Returns a Line object created from the given string.
     */
    public Line createLineFromString (String line)
    {
        line = removeWhitespaces(line); // Removes whitespaces from string
        this.line = new Line(line);
        return this.line;
    }


    /**
     * Removes leading and trailing whitespaces from a string.
     *
     * @param text The string from which whitespaces will be removed.
     *             ({@code text} != {@code null})
     * @return Returns the input string without leading and trailing whitespaces.
     */
    public String removeWhitespaces (String text)
    {
        return (text.trim());
    }


    /**
     * Counts occurrences of a specific character in a string while ignoring occurrences enclosed in another character.
     *
     * @param line            The string in which character occurrences will be counted.
     * @param countCharacter  The character to count occurrences of.
     * @param ignoreCharacter The character defining the enclosure to ignore.
     * @return Returns the count of occurrences of {@code countCharacter} in {@code line} while ignoring occurrences enclosed in {@code ignoreCharacter}.
     */
    public int countSpecificCharacterIgnoringThoseEnclosedInAnotherCharacter (String line, char countCharacter, char ignoreCharacter)
    {
        int counter = (int) ConstantsMath.ZERO;
        boolean isInEnclosure = ConstantsMath.bFalse;
        for (int j = (int) ConstantsMath.ZERO; j < line.length(); j++)
        {
            // Meets ignoreCharacter and not in enclosure: is now in enclosure
            if (line.charAt(j) == ignoreCharacter && !isInEnclosure)
            {
                isInEnclosure = ConstantsMath.bTrue;
                continue;
            }
            // Meets ignore character and is in enclosure: is now not in enclosure
            if (line.charAt(j) == ignoreCharacter && isInEnclosure) isInEnclosure = false;

            // if the current index is in enclosure, the loop keeps iterating without any action
            if (isInEnclosure) continue;
            // Counts character if not inEnclosure
            if (line.charAt(j) == countCharacter) counter++;
        }
        return counter;
    }
}
