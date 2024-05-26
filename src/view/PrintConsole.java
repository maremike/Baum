package view;


import utility.constants.ConstantsIO;


/**
 * The PrintConsole class provides methods that return formatted Strings designed for user interaction with the data.
 *
 * @author Michael Markov
 * @version v1
 */
public class PrintConsole
{
    /**
     * Retrieves a string containing a set of predefined questions formatted with question numbers and tabs.
     *
     * @return A string containing the formatted questions.
     */
    public String getQuestions ()
    {
        return (ConstantsIO.newLine +
                ConstantsIO.questionNumber0 + ConstantsIO.tab + ConstantsIO.question0 + ConstantsIO.newLine +
                ConstantsIO.questionNumber1 + ConstantsIO.tab + ConstantsIO.question1 + ConstantsIO.newLine +
                ConstantsIO.questionNumber2 + ConstantsIO.tab + ConstantsIO.question2 + ConstantsIO.newLine +
                ConstantsIO.questionNumber3 + ConstantsIO.tab + ConstantsIO.question3 + ConstantsIO.newLine +
                ConstantsIO.questionNumber4 + ConstantsIO.tab + ConstantsIO.question4 + ConstantsIO.newLine +
                ConstantsIO.questionNumber5 + ConstantsIO.tab + ConstantsIO.question5 + ConstantsIO.newLine +
                ConstantsIO.questionNumber6 + ConstantsIO.tab + ConstantsIO.question6 + ConstantsIO.newLine +
                ConstantsIO.questionNumber7 + ConstantsIO.tab + ConstantsIO.question7 + ConstantsIO.newLine +
                ConstantsIO.questionNumber8 + ConstantsIO.tab + ConstantsIO.question8 + ConstantsIO.newLine +
                ConstantsIO.questionNumber9 + ConstantsIO.tab + ConstantsIO.question9 + ConstantsIO.newLine +
                ConstantsIO.questionNumber10 + ConstantsIO.tab + ConstantsIO.question10 + ConstantsIO.newLine +
                ConstantsIO.questionNumber11 + ConstantsIO.tab + ConstantsIO.question11 + ConstantsIO.newLine +
                ConstantsIO.questionNumber12 + ConstantsIO.tab + ConstantsIO.question12 + ConstantsIO.newLine +
                ConstantsIO.questionNumber13 + ConstantsIO.tab + ConstantsIO.question13 + ConstantsIO.newLine);
    }


    /**
     * Formats the answer with respect to the answer text and answer content.
     * If the answer length exceeds a certain threshold, it is split into a new line after the answer text.
     *
     * @param answerText The text of the answer.
     * @param answer     The content of the answer.
     * @return The formatted answer.
     */
    public String getAnswer (String answerText, String answer)
    {
        // If certain amount of characters exceeded, new line created after answer text
        if (answer.length() > ConstantsIO.answerCharactersUntilNewLine)
            return (answerText + ConstantsIO.newLine + answer);
        return (answerText + ConstantsIO.tab + answer);
    }


    /**
     * Constructs an error message for invalid input, incorporating the incorrect input received.
     *
     * @param wrongInput The input that was considered invalid.
     * @return The constructed error message.
     */
    public String getInputErrorMessage (String wrongInput)
    {
        return ConstantsIO.illegalInputMessage + wrongInput + ConstantsIO.newLine + ConstantsIO.pleaseForExpectedInput;
    }
}
