package model.exceptions;


/**
 * The IrreparableDatasetException inherits from the Exception class.
 * It is used when a dataset cannot be in any way recovered.
 * The exception is typically thrown, when a dataset cannot be recovered in any way possible.
 *
 * @author Michael Markov
 * @version v1
 */
public class IrreparableDatasetException extends Exception
{
    /**
     * Constructs a new IrreparableDatasetException with no detail message.
     */
    public IrreparableDatasetException ()
    {
        super();
    }
}
