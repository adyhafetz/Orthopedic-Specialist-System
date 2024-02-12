import java.text.*;
import java.util.*;

public class LinkedList
{
    private Node head, tail, current;  // Declare nodes
    private int size;  //Declare variable for size

    public LinkedList()  // Default constructor.
    {
        head = tail = current = null;  // Initializes a new linked list with head, tail, and current set to null.
        size = 0;  // Initializes the size of the list to 0.
    }

    public void insertAtFront(Patient insertItem) // Inserts a new Patient object at the front of the linked list.
    {
        if(isEmpty()) // Case 1: If the list is empty.
        {
            head = tail = new Node(insertItem);  // Sets both head and tail to the new node.
        }
        else  // Case 2: If the list is not empty.
        {
            head = new Node(insertItem, head);  // Adds a new node at the front and updates the head.
        }
        size++;  // Size of the list increase when new node is inserted.
    }

    public void insertAtBack(Patient insertItem)  // Inserts a new Patient object at the back of the linked list.
    {
        if(isEmpty())  // Case 1: If the list is empty.
        {
            head = tail = new Node(insertItem);  // Sets both head and tail to the new node.
        }
        else  // Case 2: If the list is not empty.
        {
            tail = tail.next = new Node(insertItem);  // Adds a new node at the end and updates the tail.
        }
        size++;  // Size of the list increase when new node is inserted.
    }

    public Patient remove(Patient removeItem) throws EmptyListException  // Remove specified Patient object from anywhere in the linked list.
    {
        Node trav = head;  // Pointer to traverse the list.
        Node previous = null;  // Pointer to keep track of the previous node.
        boolean found = false;  // Flag to indicate whether the item is found.

        if (isEmpty())  // Case 1: If the list is empty.
        {
            throw new EmptyListException();  // Throw an exception if the list is empty.
        }
        else  // Case 2: If the list is not empty.
        {
            while (trav != null && !found)  // Iterate through the list until the item is found or the end is reached.
            {
                if (trav.data == removeItem) // Case 1: If current node contains the item to be removed.
                {
                    if (removeItem == head.data)  // Case 1: If the item to be removed is at the head.
                    {
                        head = head.next;  // Update the head to the next node.
                    }
                    else if (trav == tail)  // Case 2: If the item to be removed is at the tail.
                    {
                        tail = previous;  // Update the tail to the previous node.
                        tail.next = null;  // Set the next of the new tail to null.
                    }
                    else  // Case 3: If the item to be removed is in the middle of the list.
                    {
                        previous.next = trav.next;  // Skip the current node in the list.
                    }
                    found = true;  // Set the flag to true since the item is found.
                }
                else // Case 2: If current node does not contains the item to be removed.
                {
                    // Move to the next node
                    previous = trav;  // Update the previous pointer.
                    trav = trav.next;  // Move to the next node.
                }
            }
        }

        if (found)  // Case 1: If the item was found and removed.
        {
            size--;  // Size of the list decrease when node is removed.
            return removeItem;  // Return the removed item
        }
        else  // Case 2: If the item was not found.
        {
            return null;  // Return null if the item was not found.
        }
    }

    public Patient getHead()  // Retrieves the data of the head node.
    {
        if(isEmpty())  //Case 1: If the list empty
        {
            return null;  // Returns null if the list is empty.
        }
        else  //Case 2: If the list is not empty
        {
            current = head;  // Set the current node to the head node.
            return current.data;  // Return the data of the head node.
        }
    }

    public Patient getNext()  // Retrieves the data of the next node in the list.
    {
        if(current.next != null)  // Case 1: If the next node exists.
        {
            current = current.next;  // Move to the next node and set it as the current node.
            return current.data;  // Return the data of the new current node.
        }
        else  // Case 1: If the next node does not exists.
        {
            return null;  // Returns null if there is no next node.
        }
    }         

    public int size()  // Returns the current size of the linked list.
    {
        return size;  // Return size.
    }

    public boolean isEmpty()  // Checks if the linked list is empty.
    {
        return head == null;  // Returns true if the head of the linked list is null, indicating an empty list. Returns false otherwise.
    }

    public void display()  // Prints the data of each node in the linked list.
    {
        Node trav = head;  // Pointer to traverse the list.
        while (trav != null)  // Iterate through the list until the end is reached. 
        {
            System.out.println(trav.data.toString());  // Prints the data of the trav node.
            trav = trav.next;  // Moves the trav reference to the next node in the list.
        }
    }

    public void sortDateAscending()
    {    
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");  // Create a SimpleDateFormat object for parsing dates in the "dd-MM-yyyy" format.

        Node first = null;  // Pointer to first node for comparison.
        Node second = null;  // Pointer to second node for comparison.
        Patient temp;  // Facilitate swapping patient data.

        if(isEmpty())  //Case 1: If the list empty
        {  
            throw new EmptyListException();  
        }  
        else  //Case 1: If the list is not empty
        {  
            first = head;  //Set first pointer to head

            while(first != null)  // Outer loop iterates through the list using the first pointer.
            {   
                second = first.next;  // Set second pointer to first.next

                while(second != null)  // Inner loop iterates through the the list starting from the second pointer.
                {  
                    try
                    {
                        Date D1 = sdf.parse(first.data.getAppointmentDate());  // Parses the appointment date of the patient at the first node.
                        Date D2 = sdf.parse(second.data.getAppointmentDate());  // Parses the appointment date of the patient at the second node.

                        if (D1.compareTo(D2) > 0)  // Case: If first appomiment date is later than the second appoinment date.
                        {  
                            // Swapping data
                            temp = first.data;  // First object data stored in the temp object temporarily.
                            first.data = second.data;  // First object data is now equlas to second object data.
                            second.data = temp;  // Second object data is then equals to first object data stored in temp object.
                        }  
                    }
                    catch (ParseException e)  // Catch parse exception.
                    {
                        System.out.println("Parse Unsuccessful");  // Display error message if parse unsuccessful.
                    }

                    second = second.next;  // Moves the second pointer to the next node in the list.
                } 

                first = first.next;  // Moves the first pointer to the next node in the list after completing inner loop iterations.
            }      
        }
    }
}