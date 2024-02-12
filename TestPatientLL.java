import java.util.*;
import java.io.*;
import java.text.*;

public class TestPatientLL
{
    public static void main(String[] args) throws IOException
    {
        try
        {
            LinkedList patientLL = new LinkedList ();

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
                patientLL.insertAtFront(p);

                i++;
            }
            scanfile.close();

            Patient obj;

            /**Display Patient List*/
            output.println("==================================================================================================================================");
            output.println("Patient List");
            output.println("==================================================================================================================================");
            output.println("----------------------------------------------------------------------------------------------------------------------------------");
            output.println(String.format ("|%-32s|%-18s|%-15s|%-21s|%-13s|%-13s|%-10s|","Name", "IC Number", "Telephone No.", "Type", "Status", "Date", "Cost(RM)"));
            output.println("----------------------------------------------------------------------------------------------------------------------------------");
            obj =  patientLL.getHead();
            while(obj != null)
            {
                output.println(obj.toString());
                obj =  patientLL.getNext();
            }
            output.println("----------------------------------------------------------------------------------------------------------------------------------");

            /**Sort Patient By Appointment Date In Ascending Order*/
            patientLL.sortDateAscending();
            
            /**Display Patient By Appointment Date In Ascending Order*/
            output.println("\n\n==================================================================================================================================");
            output.println("Patient List (Sorted By Appointment Date)");
            output.println("==================================================================================================================================");
            output.println("----------------------------------------------------------------------------------------------------------------------------------");
            output.println(String.format ("|%-32s|%-18s|%-15s|%-21s|%-13s|%-13s|%-10s|","Name", "IC Number", "Telephone No.", "Type", "Status", "Date", "Cost(RM)"));
            output.println("----------------------------------------------------------------------------------------------------------------------------------");
            obj =  patientLL.getHead();
            while(obj != null)
            {
                output.println(obj.toString());
                obj =  patientLL.getNext();
            }
            output.println("----------------------------------------------------------------------------------------------------------------------------------");

            /**Search Patient By IC Number*/
            System.out.print("Enter patient IC number: ");
            String searchIC = scan.next();
            Patient fObj = null;
            boolean found = false;
            obj =  patientLL.getHead();
            while(obj != null && !found)
            {
                if(obj.getPatientICNo().equalsIgnoreCase(searchIC))
                {
                    found = true;
                    fObj = obj;
                }
                else
                {
                    obj =  patientLL.getNext();
                }
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

            /**Count Total Patient According To Treatment Type*/
            int countA = 0, countJ = 0, countS = 0, countF = 0;
            obj =  patientLL.getHead();
            while(obj != null)
            {
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
                obj = patientLL.getNext();
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
            obj =  patientLL.getHead();
            while(obj != null)
            {
                totalCostAll += obj.treatmentCost();
                obj = patientLL.getNext();
            }

            /**Display Total All Cost*/
            output.println("\n\n===============================");
            output.println("Total Cost For All Patient(RM)");
            output.println("===============================");
            output.println("-------------------------------");
            output.println(String.format ("|%-29s|",df.format(totalCostAll)));
            output.println("-------------------------------");

            /**Remove Patient With Completed Status*/
            LinkedList completeLL = new LinkedList();
            obj =  patientLL.getHead();
            while(obj != null)
            {
                if(obj.getTreatmentStatus())
                {
                    patientLL.remove(obj);
                    completeLL.insertAtBack(obj);
                }
                obj =  patientLL.getNext();
            }

            /**Display Complete List*/
            output.println("\n\n==================================================================================================================================");
            output.println("Complete List");
            output.println("==================================================================================================================================");
            output.println("----------------------------------------------------------------------------------------------------------------------------------");
            output.println(String.format ("|%-32s|%-18s|%-15s|%-21s|%-13s|%-13s|%-10s|","Name", "IC Number", "Telephone No.", "Type", "Status", "Date", "Cost"));
            output.println("----------------------------------------------------------------------------------------------------------------------------------");
            obj =  completeLL.getHead();
            while(obj != null)
            {
                output.println(obj.toString());
                obj =  completeLL.getNext();
            }
            output.println("----------------------------------------------------------------------------------------------------------------------------------");
            
            /**Display Patient List After Removed*/
            output.println("\n\n==================================================================================================================================");
            output.println("Patient List (Removed)");
            output.println("==================================================================================================================================");
            output.println("----------------------------------------------------------------------------------------------------------------------------------");
            output.println(String.format ("|%-32s|%-18s|%-15s|%-21s|%-13s|%-13s|%-10s|","Name", "IC Number", "Telephone No.", "Type", "Status", "Date", "Cost"));
            output.println("----------------------------------------------------------------------------------------------------------------------------------");
            obj =  patientLL.getHead();
            while(obj != null)
            {
                output.println(obj.toString());
                obj =  patientLL.getNext();
            }
            output.println("----------------------------------------------------------------------------------------------------------------------------------");
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
}