import java.io.IOException;

/**
 * Benjamin von Snarski - 45287008
 * 
 * Writes lines of 80 characters to the output file using A1Writer.
 */
public class ThreadWriter extends Thread
{
    // Max line size
    static final int MAX_LINE_SIZE = 80;

    // Internal counter for characters written
    private int count = 0;
    // The file writer
    private A1Writer writer;

    /**
     * Construct a thread for writing to a file.
     * 
     * @param fileName name of the output file
     * @throws IOException file writer fails
     */
    public ThreadWriter(String fileName) throws IOException
    {
        System.out.println("Creating writer thread with file name: " + fileName);
        writer = new A1Writer(fileName);
    }

    /**
     * Writes each character in the OUTPUT buffer to a file.
     * A line break is added every MAX LINE SIZE (80) characters.
     * Stops when the OUTPUT buffer is done.
     */
    @Override
    public void run()
    {
        try
        {
            while (true)
            {
                int c = FileConverter.OUTPUT_BUFFER.get();

                // Buffer is done
                if (c < 0)
                {
                    break;
                }

                writer.write((char)c);

                if (++count == MAX_LINE_SIZE)
                {
                    count = 0;
                    writer.lineBreak();
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Caught exception: " + e.getMessage());
        }
        finally
        {
            writer.close();
        }
    }
}
