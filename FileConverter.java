/**
 * Benjamin von Snarski - 45287008
 * 
 * Entry point to the file converter program.
 * Contains two global circular buffer for communicating
 * characters between three threads.
 */
public class FileConverter
{
    // To be used by the reader and processor threads
    public static CircularBuffer INPUT_BUFFER = new CircularBuffer();
    // To be used by the processor and writer threads
    public static CircularBuffer OUTPUT_BUFFER = new CircularBuffer();

    public static void main(String[] args)
    {
        System.out.println("\nRunning File Converter...");

        ThreadReader threadReader;
        ThreadWriter threadWriter;
        ThreadProcessor threadProcessor;

        try
        {
            threadReader = new ThreadReader("source_v2.txt");
            threadWriter = new ThreadWriter("test.txt");
            threadProcessor = new ThreadProcessor();
        }
        catch (Exception e)
        {
            System.out.println("\nError: Failed to instantiate thread objects.");
            System.out.println(e.toString());
            return;
        }

        threadReader.start();
        threadWriter.start();
        threadProcessor.start();

        try
        {
            threadReader.join();
            threadWriter.join();
            threadProcessor.join();
        }
        catch (Exception e)
        {
            System.out.println("\nError: Failed to join thread objects.");
        }

        System.out.println("\nFinished Converting Files!");
    }
}
