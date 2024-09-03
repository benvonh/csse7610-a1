public class ThreadProcessor extends Thread
{
    public ThreadProcessor()
    {

    }

    @Override
    public void run()
    {
        try
        {
            while (true)
            {
                System.out.println("Hi! I am thread processor!");
                sleep(500);
            }
        }
        catch (Exception e)
        {
            // TODO: handle exception
        }
    }
}
