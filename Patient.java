import java.text.DecimalFormat;  // Importing the DecimalFormat class for formatting numeric values

public class Patient  // Defining the Patient class
{
    // Creating a DecimalFormat object with a 2 decimal places
    DecimalFormat df = new DecimalFormat("0.00");
    
    // Declaring private instance variables for the Patient class
    private String patientName;
    private String patientICNo;
    private String patientTelNo;
    private char treatmentType;
    private boolean treatmentStatus;
    private String appointmentDate;
    
    // Default constructor initializing instance variables to default values
    public Patient()
    {
        this.patientName = null;
        this.patientICNo = null;
        this.patientTelNo = null;
        this.treatmentType = ' ';
        this.treatmentStatus = false;
        this.appointmentDate = null;
    }

    // Normal constructor to set values for instance variables
    public Patient(String patientName, String patientICNo, String patientTelNo, char treatmentType, boolean treatmentStatus, 
                    String appoinmentDate)
    {
        this.patientName = patientName;
        this.patientICNo = patientICNo;
        this.patientTelNo = patientTelNo;
        this.treatmentType = treatmentType;
        this.treatmentStatus = treatmentStatus;
        this.appointmentDate = appoinmentDate;
    }

    // Getter method for retrieving patientName
    public String getPatientName()
    {
        return patientName;
    }

    // Getter method for retrieving patientICNo
    public String getPatientICNo()
    {
        return patientICNo;
    }

    // Getter method for retrieving patientTelNo
    public String getPatientTelNo()
    {
        return patientTelNo;
    }
    
    // Getter method for retrieving treatmentType
    public char getTreatmentType()
    {
        return treatmentType;
    }
    
    // Getter method for retrieving treatmentStatus
    public boolean getTreatmentStatus()
    {
        return treatmentStatus;
    }
    
    // Getter method for retrieving appointmentDate
    public String getAppointmentDate()
    {
        return appointmentDate;
    }
    
    // Setter method for updating treatmentStatus
    public void setTreatmentStatus(boolean treatmentStatus)
    {
        this.treatmentStatus = treatmentStatus;
    }
    
    // Method to calculate treatment cost based on treatment type
    public double treatmentCost()
    {
        double cost = 0.00;
        if (treatmentType == 'A')
        {
            cost = 5000.00;
        }
        else if (treatmentType == 'J')
        {
            cost = 10000.00;
        }
        else if (treatmentType == 'F')
        {    
            cost = 1500.00;
        }
        else if (treatmentType == 'S')
        {
            cost = 8000.00;
        }
        
        return cost;
    }
    
    // Method display
    public String toString()
    {       
        // Mapping treatmentType character to the actual treatment names
        String type = null;
        if (treatmentType == 'A')
        {
            type = "Arthroscopy";
        }
        else if (treatmentType == 'J')
        {
            type = "Joint Replacement";
        }
        else if (treatmentType == 'F')
        {    
            type = "Fracture Repair";
        }
        else if (treatmentType == 'S')
        {
            type = "Spinal Surgery";
        }
        
        // Mapping treatmentStatus boolean value the actual status
        String status;
        if (treatmentStatus)
        {
            status = "Completed";
        }
        else
        {
            status = "Pending";
        }
        
        // Formatting and constructing the string representation of the object
        return String.format ("|%-32s|%-18s|%-15s|%-21s|%-13s|%-13s|%-10s|",patientName, patientICNo, patientTelNo, type, status, appointmentDate, df.format(treatmentCost()));
    }
}
