package model;


/**
 * The Line class represents a simple container for a text line.
 *
 * @author Michael Markov
 * @version v1
 */
public class Line
{
    private String line;


    /**
     * Default constructor for creating an empty Line.
     */
    public Line ()
    {
    }


    /**
     * Constructs a Line object with the specified text line without any whitespaces.
     *
     * @param line The text content of the line.
     */
    public Line (String line)
    {
        this.line = line;
    }


    /**
     * Gets the text content of the line.
     *
     * @return The text content of the line.
     */
    public String getLine ()
    {
        return line;
    }
}

