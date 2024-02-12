import java.util.LinkedList;
import java.text.*;
import java.util.*;

public class Queue <Patient> 
{
    protected LinkedList <Patient> list;

    public Queue()  //Default consturctor.
    {
        list = new LinkedList();  // Create new linked list.
    }

    public void enqueue(Patient insertItem)  // Inserts a new Patient object at the last of the queue.    
    {
        list.addLast(insertItem);  // Use addLast() method from LinkedList class.
    }

    public Patient dequeue()  // Remove Patient object from the front of the queue.
    {
        return list.removeFirst();  // Use removeFirst() method from LinkedList class.
    }

    public int size()  // Returns the current size of the linked list.
    {
        return list.size();  // Use size() method from LinkedList class.
    }

    public boolean isEmpty()  // Checks if the linked list is empty.
    {
        return list.isEmpty();  // Use size() method from LinkedList class.
    }
}