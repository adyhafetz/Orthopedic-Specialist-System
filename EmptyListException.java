public class EmptyListException extends RuntimeException
{
    public EmptyListException() // Default constructor
    {
        super("The list is empty");  // Calls the constructor of the superclass and passes the message that will be associated with the exception when it is thrown.
    }
}