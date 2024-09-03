/**
 * Two circular buffers for the input and output
 * of the file being processed.
 */
public class Buffer
{
    static final int SIZE = 20;

    private static char[] inputBuffer = new char[SIZE];
    private static char[] outputBuffer = new char[SIZE];
    private static int inputIn = 0, inputOut = 0;
    private static int outputIn = 0, outputOut = 0;

    public Buffer()
    {
    }

    public static char GetInput()
    {
        while (inputIn == inputOut) {}

        char c = inputBuffer[inputOut];

        inputOut = (inputOut + 1) % Buffer.SIZE;

        return c;
    }

    public static void SetInput(char c)
    {
        while (inputOut == (inputIn + 1) % Buffer.SIZE) {}

        inputBuffer[inputIn] = c;

        inputIn = (inputIn + 1) % Buffer.SIZE;
    }

    public static char GetOutput()
    {
        while (outputIn == outputOut) {}

        char c = outputBuffer[outputOut];

        outputOut = (outputOut + 1) % Buffer.SIZE;

        return c;
    }

    public static void SetOutput(char c)
    {
        while (outputOut == (outputIn + 1) % Buffer.SIZE) {}

        outputBuffer[outputIn] = c;

        outputIn = (outputIn + 1) % Buffer.SIZE;
    }
}
