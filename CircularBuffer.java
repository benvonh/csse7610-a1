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
    private boolean stopped = false;

    /**
     * Get the next data from the buffer.
     * 
     * @return last char added
     */
    public char get()
    {
        Thread.yield();

        while (in == out) {}

        char c = buffer[out];

        out = (out + 1) % CircularBuffer.SIZE;

        return c;
    }

    /**
     * Set the next data in the buffer.
     * 
     * @param c newest char to be added
     */
    public void set(char c)
    {
        Thread.yield();

        while (out == (in + 1) % CircularBuffer.SIZE) {}

        buffer[in] = c;

        in = (in + 1) % CircularBuffer.SIZE;
    }

    /**
     * Signal the buffer is no longer updated.
     */
    public void end()
    {
        stopped = true;
    }

    /**
     * Check if the buffer has ended.
     * 
     * @return whether the buffer is done
     */
    public boolean done()
    {
        return stopped && in == out;
    }
}
