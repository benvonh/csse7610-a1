# File Converter

The file converter utilises three threads to read a file, strip whitespaces,
resize each line to 80 characters and then write it to a new file. Each thread
communicates via 2 circular buffers which connect like in the diagram below.

{ Reader Thread } -> [Input Buffer] -> { Processor Thread } -> [Output Buffer] -> { Writer Thread }

where the reader thread sets data and processor thread gets data, incrementing the index each time, and so on with the output buffer. Mutual exclusion is achieved by using synchronized blocks for each atomic operation in the A1 circular buffer table. Freedom from starvation is also assisted with by yielding threads inside the pre-condition await line.

# Classes

- File Converter
    - Contains the entry point to the Java program.
    - Has global (static) variables for the 2 circular buffers: `INPUT buffer` and `OUTPUT buffer`.

- Circular Buffer
    - Implementation of the described A1 circular buffer.
    - Also has an internal flag that can be set to check if the circular buffer will no longer receive new data.

- Thread Reader
    - Reads characters from the input file using `A1Reader` and replaces newline characters with spaces.
    - The character is stored in the `INPUT buffer` and flagged as finished when no more characters are read.

- Thread Processor
    - Each character from the `INPUT buffer` is retrieved and stored in the `OUTPUT buffer`.
    - Tabs and consecutive spaces are replaced with a single space.
    - The `OUTPUT buffer` is flagged as finished when the `INPUT buffer` is done.

- Thread Writer
    - Writes each character in the `OUTPUT buffer` to a file.
    - A line break is added every 80 characters.
    - Stops when the `OUTPUT buffer` is done.
