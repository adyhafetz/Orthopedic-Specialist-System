public class Node
{
    Patient data;
    Node next;
    
    Node(Patient p)  // First normal constructor.
    {
        this(p, null);  // Calls the second constructor with the given Patient object and a null reference for the next node.
    }
    
    Node(Patient p, Node next)  // Second normal constructor.
    {
        data = p;  //Assigns the provided Patient object p to the data member of the node.
        this.next = next;  // Assigns the provided Node reference next to the next member of the node.
    }
}