package control;


import utility.constants.ConstantsMath;


/**
 * The Mathematics class provides methods for basic mathematical operations such as summation, subtraction, multiplication and division.
 * As well as methods for determining the average.
 * Additionally, providing methods consisting of formulae for circles, and others that correlate with circles.
 * Further offers different types of implementation of the mitchell formula.
 *
 * @author Michael Markov
 * @version 1
 */
public class Mathematics
{
    /**
     * Calculates the diameter of a circle from its perimeter.
     *
     * @param perimeter The perimeter of the circle.
     *                  ({@code perimeter} must be a non-negative value.)
     * @return Returns the diameter of the circle based on the given perimeter.
     */
    public static double getDiameterFromPerimeter (double perimeter)
    {
        return (int) getDiameterFromRadius(getRadiusFromPerimeter(perimeter));
    }


    /**
     * Calculates the diameter of a circle from its radius.
     * Formula: diameter = 2 * radius
     *
     * @param radius The radius of the circle.
     *               ({@code radius} must be a non-negative value.)
     * @return Returns the diameter of the circle based on the given radius.
     */
    private static double getDiameterFromRadius (double radius)
    {
        // radius * 2 = diameter
        return multiplication(radius, ConstantsMath.TWO);
    }


    /**
     * Calculates the radius of a circle from its perimeter.
     * radius = perimeter * 0.5 * (1/PI)
     *
     * @param perimeter The perimeter of the circle.
     *                  ({@code perimeter} must be a non-negative value.)
     * @return Returns the radius of the circle based on the given perimeter.
     */
    public static double getRadiusFromPerimeter (double perimeter)
    {
        // perimeter * 0.5 * (1/PI) = radius
        return multiplication(perimeter, ConstantsMath.ZERO_POINT_FIVE, division(ConstantsMath.ONE, ConstantsMath.PI));
    }


    /**
     * Limits a variable to a specified upper limit, setting all variables above the upper limit to the upper limit.
     *
     * @param variable The variable to be limited.
     * @param limit    The upper limit.
     * @return Returns the variable limited to the specified upper limit.
     */
    public static double upperLimit (double variable, double limit)
    {
        if (variable > limit) variable = limit; // Values above limit will be set to the limit
        return variable;
    }


    /**
     * Calculates the girth of a tree in centimeters using Mitchell's formula based on its age.
     * Formula: Girth = age / 0.6
     *
     * @param age The age of the tree.
     *            ({@code age} is a non-negative integer.)
     * @return The returned value represents the girth of the tree calculated using Mitchell's formula.
     */
    public static int mitchellFormulaGetGirthCM (int age)
    {
        return (int) (Mathematics.division(age, ConstantsMath.MITCHELL_FACTOR));
    }


    /**
     * Calculates the age of a tree using the Mitchell formula based on its girth in centimeters.
     * Formula: age = girth * 0.6
     *
     * @param girthCM The girth of the tree in centimeters.
     *                ({@code girthCM} is a non-negative integer.)
     * @return The returned value represents the age of the tree calculated using Mitchell's formula.
     */
    public static int mitchellFormulaGetAge (int girthCM)
    {
        return (int) Mathematics.multiplication(girthCM, ConstantsMath.MITCHELL_FACTOR);
    }


    /**
     * Computes the average of the given values.
     *
     * @param values The values to compute the average from.
     * @return Returns the average of the provided values.
     */
    public static double average (double... values)
    {
        if (values.length == ConstantsMath.ZERO)
        {
            return ConstantsMath.ZERO; // If no values are provided, return 0
        }

        double sum = ConstantsMath.ZERO; // Initialize sum to 0

        // Add each value in the array
        for (double value : values)
        {
            sum += value;
        }

        return division(sum, values.length); // Return the average
    }


    /**
     * Computes the multiplication of the given values.
     *
     * @param values The values to compute the multiplication from.
     * @return Returns the multiplication of the provided values.
     */
    public static double multiplication (double... values)
    {
        if (values.length == ConstantsMath.ZERO)
        {
            return ConstantsMath.ZERO; // If no values are provided, return 0
        }

        double product = ConstantsMath.ONE; // Initialize product to 1

        // Multiply each value in the array
        for (double value : values)
        {
            product *= value;
        }

        return product; // Return the final product
    }


    /**
     * Computes the division of the given values.
     *
     * @param values The values to compute the division from.
     * @return Returns the division of the provided values.
     * @throws IllegalArgumentException If any of the divisor values is 0.
     */
    public static double division (double... values)
    {
        if (values.length == ConstantsMath.ZERO)
        {
            return ConstantsMath.ZERO; // If no values are provided, return 0
        }

        double quotient = values[(int) ConstantsMath.ZERO]; // Initialize quotient to the first value

        // Divide each value after the first one by the first value
        for (int i = (int) ConstantsMath.ONE; i < values.length; i++)
        {
            if (values[i] == ConstantsMath.ZERO)
            {
                throw new IllegalArgumentException(ConstantsMath.divisionByZeroException);
            }
            quotient /= values[i];
        }

        return quotient; // Return the final quotient
    }


    /**
     * Computes the sum of the given values.
     *
     * @param values The values to compute the sum from.
     * @return Returns the sum of the provided values.
     */
    public static double sum (double... values)
    {
        double sum = ConstantsMath.ZERO; // Initialize sum to 0

        // Add each value in the array
        for (double value : values)
        {
            sum += value;
        }

        return sum; // Return the final sum
    }


    /**
     * Computes the subtraction of the given values.
     *
     * @param values The values to compute the subtraction from.
     * @return Returns the subtraction of the provided values.
     */
    public static double subtraction (double... values)
    {
        if (values.length == ConstantsMath.ZERO)
        {
            return ConstantsMath.ZERO; // If no values are provided, return 0
        }

        double difference = values[(int) ConstantsMath.ZERO]; // Initialize difference to the first value

        // Subtract each value after the first one from the first value
        for (int i = (int) ConstantsMath.ONE; i < values.length; i++)
        {
            difference -= values[i];
        }

        return difference; // Return the final difference
    }
}
