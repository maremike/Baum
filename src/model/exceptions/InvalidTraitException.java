package model.exceptions;


/**
 * The InvalidTraitException class inherits from the exception class.
 * It is used to make sure the tree traits stay valid.
 * It is typically thrown, when a value cannot be taken up by the tree class (either due to exceeding limitations, or through an invalid format)
 *
 * @author Michael Markov
 * @version v1
 */
public class InvalidTraitException extends Exception
{
    int traitNumber;


    /**
     * Constructs a new InvalidTraitException with the specified trait number.
     *
     * @param traitNumber The index of the invalid trait.
     */
    public InvalidTraitException (int traitNumber)
    {
        this.traitNumber = traitNumber;
    }


    /**
     * Retrieves the index of the invalid trait that caused the exception.
     *
     * @return The index of the invalid trait.
     */
    public int getTraitNumber ()
    {
        return traitNumber;
    }
}
