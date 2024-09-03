import java.io.IOException;

public class ThreadReader extends Thread
{
    private A1Reader M_reader;

    public ThreadReader(String fileName) throws IOException
    {
        M_reader = new A1Reader(fileName);
    }

    @Override
    public void run()
    {
        try
        {
            System.out.println("Hi! I am thread reader!");
            sleep(2000);
        }
        catch (Exception e)
        {
            // TODO: handle exception
        }
    }
}
