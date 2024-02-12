import java.util.*;
import java.io.*;
import java.text.*;

public class TestPatientQ
{
    public static void main (String [] args) throws IOException
    {
        try
        {
            Queue <Patient> patientQ = new Queue();

            DecimalFormat df = new DecimalFormat("0.00");

            Scanner scan = new Scanner(System.in);
            scan.useDelimiter("\n");

            File file = new File("Patient.txt");
            Scanner scanfile = new Scanner(file);
            PrintWriter output = new PrintWriter("Output.txt");

            int i = 0;
            while(scanfile.hasNext())
            {
                String indata = scanfile.nextLine();
                StringTokenizer st = new StringTokenizer(indata,";");

                String patientName = st.nextToken();
                String patientICNo = st.nextToken();
                String patientTelNo = st.nextToken();
                char treatmentType = st.nextToken().charAt(0);
                String treatmentStatusStr = st.nextToken();
                boolean treatmentStatus = "Completed".equalsIgnoreCase(treatmentStatusStr);
                String appointmentDate = st.nextToken();

                Patient p = new Patient (patientName, patientICNo, patientTelNo, treatmentType, treatmentStatus, appointmentDate);
                patientQ.enqueue(p);

                i++;
            }
            scanfile.close();

            Patient obj;
            Queue <Patient> tempQ = new Queue();

            /**Display Patient List*/
            output.println("==================================================================================================================================");
            output.println("Patient List");
            output.println("==================================================================================================================================");
            output.println("----------------------------------------------------------------------------------------------------------------------------------");
            output.println(String.format ("|%-32s|%-18s|%-15s|%-21s|%-13s|%-13s|%-10s|","Name", "IC Number", "Telephone No.", "Type", "Status", "Date", "Cost(RM)"));
            output.println("----------------------------------------------------------------------------------------------------------------------------------");
            while(!patientQ.isEmpty())
            {
                obj = patientQ.dequeue();
                output.println(obj.toString());
                tempQ.enqueue(obj);
            }
            output.println("----------------------------------------------------------------------------------------------------------------------------------");

            while(!tempQ.isEmpty())
            {
                patientQ.enqueue(tempQ.dequeue());
            }

            /**Sort Patient By Appointment Date In Ascending Order*/
            sortDateAscending(patientQ);

            /**Display Patient By Appointment Date In Ascending Order*/
            output.println("/n/n==================================================================================================================================");
            output.println("Patient List (Sorted By Appointment Date)");
            output.println("==================================================================================================================================");
            output.println("----------------------------------------------------------------------------------------------------------------------------------");
            output.println(String.format ("|%-32s|%-18s|%-15s|%-21s|%-13s|%-13s|%-10s|","Name", "IC Number", "Telephone No.", "Type", "Status", "Date", "Cost(RM)"));
            output.println("----------------------------------------------------------------------------------------------------------------------------------");
            while(!patientQ.isEmpty())
            {
                obj = patientQ.dequeue();
                output.println(obj.toString());
                tempQ.enqueue(obj);
            }
            output.println("----------------------------------------------------------------------------------------------------------------------------------");

            while(!tempQ.isEmpty())
            {
                patientQ.enqueue(tempQ.dequeue());
            }

            /**Search Patient By IC Number*/
            System.out.print("Enter patient IC number: ");
            String searchIC = scan.next();
            Patient fObj = null;
            boolean found = false;
            while(!patientQ.isEmpty() && !found)
            {
                obj = patientQ.dequeue();
                if(obj.getPatientICNo().equalsIgnoreCase(searchIC))
                {
                    found = true;
                    fObj = obj;
                }
                tempQ.enqueue(obj);
            }

            if(found)
            {
                String currStatus = null;
                if(fObj.getTreatmentStatus())
                {
                    currStatus = "Completed";
                }
                else
                {
                    currStatus = "Pending";
                }
                System.out.print("Current status: " + currStatus);
                System.out.print("\nEnter new status [Completed | Pending]: ");
                String newStatusStr = scan.next();
                boolean newStatus = "Completed".equalsIgnoreCase(newStatusStr);
                fObj.setTreatmentStatus(newStatus);
                output.println("\n\n==================================================================================================================================");
                output.println("Patient List (Searched)");
                output.println("==================================================================================================================================");
                output.println("----------------------------------------------------------------------------------------------------------------------------------");
                output.println(String.format ("|%-32s|%-18s|%-15s|%-21s|%-13s|%-13s|%-10s|","Name", "IC Number", "Telephone No.", "Type", "Status", "Date", "Cost(RM)"));
                output.println("----------------------------------------------------------------------------------------------------------------------------------");
                output.println(fObj.toString());
                output.println("----------------------------------------------------------------------------------------------------------------------------------");
            }
            else
            {
                System.out.print("Patient not found");
            }

            while(!tempQ.isEmpty())
            {
                patientQ.enqueue(tempQ.dequeue());
            }

            /**Count Total Patient According To Treatment Type*/
            int countA = 0, countJ = 0, countS = 0, countF = 0;
            while(!patientQ.isEmpty())
            {
                obj = patientQ.dequeue();

                if(obj.getTreatmentType() == 'A')
                {
                    countA++;
                }
                else if(obj.getTreatmentType() == 'J')
                {
                    countJ++;
                }
                else if(obj.getTreatmentType() == 'S')
                {
                    countS++;
                }
                else if(obj.getTreatmentType() == 'F')
                {
                    countF++;
                }

                tempQ.enqueue(obj);
            }

            while(!tempQ.isEmpty())
            {
                patientQ.enqueue(tempQ.dequeue());
            }

            /**Display Total Patient According To Treatment Type*/
            output.println("\n\n=========================================================================================================");
            output.println("Total Patient According To Treatment Type");
            output.println("=========================================================================================================");
            output.println("---------------------------------------------------------------------------------------------------------");
            output.println(String.format ("|%-25s|%-25s|%-25s|%-25s|","Arthroscopy", "Joint Replacement", "Spinal Surgery", "Fracture Repair"));
            output.println("---------------------------------------------------------------------------------------------------------");
            output.println(String.format ("|%-25s|%-25s|%-25s|%-25s|",countA, countJ, countS, countF));
            output.println("---------------------------------------------------------------------------------------------------------");

            /**Calculate Total All Cost*/
            double totalCostAll = 0.0;
            while(!patientQ.isEmpty())
            {
                obj = patientQ.dequeue();
                totalCostAll += obj.treatmentCost();
                tempQ.enqueue(obj);
            }

            /**Display Total All Cost*/
            output.println("\n\n===============================");
            output.println("Total Cost For All Patient(RM)");
            output.println("===============================");
            output.println("-------------------------------");
            output.println(String.format ("|%-29s|",df.format(totalCostAll)));
            output.println("-------------------------------");

            while(!tempQ.isEmpty())
            {
                patientQ.enqueue(tempQ.dequeue());
            }

            /**Remove Patient With Completed Status*/
            Queue <Patient> completeQ = new Queue();
            while(!patientQ.isEmpty())
            {
                obj = patientQ.dequeue();

                if(obj.getTreatmentStatus())
                {
                    completeQ.enqueue(obj);
                }
                else
                {
                    tempQ.enqueue(obj);
                }
            }

            while(!tempQ.isEmpty())
            {
                patientQ.enqueue(tempQ.dequeue());
            }

            /**Display Complete List*/
            output.println("\n\n==================================================================================================================================");
            output.println("Complete List");
            output.println("==================================================================================================================================");
            output.println("----------------------------------------------------------------------------------------------------------------------------------");
            output.println(String.format ("|%-32s|%-18s|%-15s|%-21s|%-13s|%-13s|%-10s|","Name", "IC Number", "Telephone No.", "Type", "Status", "Date", "Cost"));
            output.println("----------------------------------------------------------------------------------------------------------------------------------");
            while(!completeQ.isEmpty())
            {
                obj = completeQ.dequeue();
                output.println(obj.toString());
                tempQ.enqueue(obj);
            }
            output.println("----------------------------------------------------------------------------------------------------------------------------------");

            while(!tempQ.isEmpty())
            {
                completeQ.enqueue(tempQ.dequeue());
            }

            /**Display Patient List After Removed*/
            output.println("\n\n==================================================================================================================================");
            output.println("Patient List (Removed)");
            output.println("==================================================================================================================================");
            output.println("----------------------------------------------------------------------------------------------------------------------------------");
            output.println(String.format ("|%-32s|%-18s|%-15s|%-21s|%-13s|%-13s|%-10s|","Name", "IC Number", "Telephone No.", "Type", "Status", "Date", "Cost"));
            output.println("----------------------------------------------------------------------------------------------------------------------------------");
            while(!patientQ.isEmpty())
            {
                obj = patientQ.dequeue();
                output.println(obj.toString());
                tempQ.enqueue(obj);
            }
            output.println("----------------------------------------------------------------------------------------------------------------------------------");

            while(!tempQ.isEmpty())
            {
                patientQ.enqueue(tempQ.dequeue());
            }

            output.close();
        }
        catch(FileNotFoundException fnfe)
        {
            System.out.println("File Not Found");
        }
        catch(IOException io)
        {
            System.out.println("Input/Output Error");
        }
        catch(Exception e)
        {
            System.out.println("Something Went Wrong");
        }
    }

    public static int findIndexOfMinimumElement(Queue <Patient> queue, int index)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        int minIndex = -1;
        Patient minElement = null;

        for(int i = 0; i < queue.size(); i++)
        {
            Patient currElement = queue.dequeue();

            if (minElement == null)
            {
                minElement = currElement;
                minIndex = i;
            }

            try
            {
                Date D1 = sdf.parse(minElement.getAppointmentDate());
                Date D2 = sdf.parse(currElement.getAppointmentDate());

                if(D2.compareTo(D1) <= 0 && i <= index)
                {
                    minIndex = i;
                    minElement = currElement;
                }
            } 
            catch(ParseException e)
            {
                System.out.println("Parse Unsuccessful");
            }

            queue.enqueue(currElement);
        }

        return minIndex;
    }

    public static void insertElementToRear(Queue <Patient> queue, int index)
    {
        Patient minValue = null;
        int size = queue.size();

        for(int i = 0; i < size; i++)
        {
            Patient elementAtFront = queue.dequeue();
            if(i != index)
            {
                queue.enqueue(elementAtFront);
            }
            else
            {
                minValue = elementAtFront;
            }
        }

        queue.enqueue(minValue);
    }

    public static void sortDateAscending(Queue <Patient> queue)
    {
        for(int i = 0; i < queue.size(); i++)
        {
            int index = findIndexOfMinimumElement(queue, queue.size() - i - 1);
            insertElementToRear(queue, index);
        }
    }
}