import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Benjamin von Snarski - 45287008
 * 
 * Implementation of the circular buffer for A1.
 */
public class CircularBuffer
{
    // Size of the circular buffer
    static final int SIZE = 20;

    // Indices for data in and out
    private int in = 0, out = 0;
    // Buffer as an array of char primitives
    private char[] buffer = new char[SIZE];
    // Flag for when no more char will be added
    private AtomicBoolean finished = new AtomicBoolean(false);

    /**
     * Get the next data from the buffer.
     * 
     * @return last char added
     */
    public char get()
    {
        char c;
        boolean block = true;

        while (block)
        {
            Thread.yield();

            synchronized (this)
            {
                block = in == out && !finished.get();
            }
        }

        synchronized (this)
        {
            c = buffer[out];
        }

        synchronized (this)
        {
            out = (out + 1) % CircularBuffer.SIZE;
        }

        return c;
    }

    /**
     * Set the next data in the buffer.
     * 
     * @param c newest char to be added
     */
    public void set(char c)
    {
        boolean block = true;

        while (block)
        {
            Thread.yield();

            synchronized (this)
            {
                block = out == (in + 1) % CircularBuffer.SIZE;
            }
        }

        synchronized (this)
        {
            buffer[in] = c;
        }

        synchronized (this)
        {
            in = (in + 1) % CircularBuffer.SIZE;
        }
    }

    /**
     * Signal the buffer is no longer updated.
     */
    public void end()
    {
        finished.set(true);
    }

    /**
     * Check if the buffer has ended.
     * 
     * @return whether the buffer is done
     */
    public boolean done()
    {
        boolean b;

        synchronized (this)
        {
            b = finished.get() && in == out;
        }
        return b;
    }
}
