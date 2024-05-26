package control;


import utility.MyIO;


/**
 * The Main class is where the program runs. The main method is located here.
 * This class is a singleton for security purposes.
 *
 * @author Michael Markov
 * @version 1
 */
public class Main
{
    private static Main instance;


    /**
     * Provides a thread-safe way to get the singleton instance of the Main class.
     *
     * @return The singleton instance of the Main class.
     */
    public static synchronized Main getInstance ()
    {
        if (instance == null)
        {
            instance = new Main();
        }
        return instance;
    }


    /**
     * The main entry point of the program.
     * It initializes the singleton instance of Main and invokes its run method.
     *
     * @param args The command-line arguments passed to the program.
     */
    public static void main (String[] args)
    {
        Main singleton = Main.getInstance();
        singleton.run();
    }


    /**
     * Runs the main logic of the program.
     * It initiates the parsing of tree datasets and handles questions and answers.
     */
    private void run ()
    {
        MyIO.parser();
    }
}
