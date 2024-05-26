package model.exceptions;


/**
 * The IllegalInputException class inherits from the exception class.
 * It is used to make sure the console inputs remain in the excepted boundaries.
 * The exception is typically thrown, when the user enters characters in the console that should not be entered.
 *
 * @author Michael Markov
 * @version v1
 */
public class IllegalInputException extends Exception
{
    /**
     * Constructs a new IllegalInputException with the specified error message.
     *
     * @param message The error message describing the illegal input.
     */
    public IllegalInputException (String message)
    {
        super(message);
    }
}
