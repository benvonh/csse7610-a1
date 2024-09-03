import java.io.IOException;

public class ThreadWriter extends Thread
{
    private A1Writer M_writer;

    public ThreadWriter(String fileName) throws IOException
    {
        M_writer = new A1Writer(fileName);
    }

    @Override
    public void run()
    {
        try
        {
            while (true)
            {
                System.out.println("Hi! I am thread writer!");
                sleep(1000);
            }
        }
        catch (Exception e)
        {
            // TODO: handle exception
        }
    }
}
