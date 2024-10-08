/**
 * Benjamin von Snarski - 45287008
 * 
 * Replaces tabs and consecutive spaces with a single space.
 */
public class ThreadProcessor extends Thread
{
    // Flag for continuing whitespaces
    private boolean whitespace = false;

    /**
     * Construct a thread for processing the INPUT buffer
     * and returning it to the OUTPUT buffer.
     */
    public ThreadProcessor()
    {
        System.out.println("Creating processor thread");
    }

    /**
     * Get each character from the INPUT buffer
     * and stores it in the OUTPUT buffer.
     * Replaces consecutive whitespaces with a single space.
     * Ends the OUTPUT buffer when the INPUT buffer is done.
     */
    @Override
    public void run()
    {
        try
        {
            while (true)
            {
                int c = FileConverter.INPUT_BUFFER.get();

                // Buffer is done
                if (c < 0)
                {
                    break;
                }

                if (Character.isWhitespace(c))
                {
                    whitespace = true;
                }
                else
                {
                    if (whitespace)
                    {
                        whitespace = false;
                        FileConverter.OUTPUT_BUFFER.set(' ');
                    }
                    FileConverter.OUTPUT_BUFFER.set((char)c);
                }
            }
            FileConverter.OUTPUT_BUFFER.end();
        }
        catch (Exception e)
        {
        }
    }
}
