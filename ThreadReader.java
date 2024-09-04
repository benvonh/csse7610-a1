import java.io.IOException;

/**
 * Benjamin von Snarski - 45287008
 * 
 * Reads characters from the input file using A1Reader
 * and replaces newline characters with spaces.
 */
public class ThreadReader extends Thread
{
    // The file reader
    private A1Reader reader;

    /**
     * Construct a thread for reading from a file
     * and loading it into the INPUT buffer.
     * 
     * @param fileName name of the input file
     * @throws IOException file reader fails
     */
    public ThreadReader(String fileName) throws IOException
    {
        System.out.println("Creating reader thread with file name: " + fileName);
        reader = new A1Reader(fileName);
    }

    /**
     * Read each character from a file, replaces newlines with spaces
     * and loads it to the INPUT buffer in the file converter.
     * Ends the INPUT buffer when read no longer returns a character.
     */
    @Override
    public void run()
    {
        try
        {
            int i;

            while ((i = reader.read()) != -1)
            {
                char c = (char)i;

                if (c == '\n')
                {
                    c = ' ';
                }

                FileConverter.INPUT_BUFFER.set(c);
            }
            FileConverter.INPUT_BUFFER.end();
        }
        catch (Exception e)
        {
            System.out.println("Caught exception: " + e.getMessage());
        }
        finally
        {
            reader.close();
        }
    }
}
