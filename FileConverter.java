import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class FileConverter
{
    public static void main(String[] args) throws IOException, InterruptedException
    {
        System.out.println("Running File Converter...");

        ThreadReader threadReader = new ThreadReader("source_v2.txt");
        ThreadWriter threadWriter = new ThreadWriter("test.txt");
        ThreadProcessor threadProcessor = new ThreadProcessor();

        threadReader.run();
        threadWriter.run();
        threadProcessor.run();

        TimeUnit.SECONDS.sleep(5);

        threadReader.join();
        threadWriter.join();
        threadProcessor.join();

        System.out.println("Finished Converting Files...");
    }
}
