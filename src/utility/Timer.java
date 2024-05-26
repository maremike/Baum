package utility;


/**
 * The Timer class provides a simple timer functionality to measure elapsed time.
 *
 * @author Michael Markov
 * @version v1
 */
public class Timer
{
    private Long startingTime;


    /**
     * Default constructor that initializes the timer.
     */
    public Timer ()
    {
        init();
    }


    /**
     * Initializes the timer by setting the starting time to the current system time.
     */
    private void init ()
    {
        startingTime = System.currentTimeMillis();
    }


    /**
     * Resets the timer by updating the starting time to the current system time.
     */
    public void reset ()
    {
        startingTime = System.currentTimeMillis();
    }


    /**
     * Calculates and returns the time passed (in milliseconds) since the timer was last initialized or reset.
     *
     * @return The time passed since the timer was last initialized or reset.
     */
    public Long timePassed ()
    {
        return System.currentTimeMillis() - startingTime;
    }
}
