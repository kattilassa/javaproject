import java.io.Console;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileNotFoundException;
import java.util.Scanner;

class ContactsApp {
    public static void main(String [] args) {
        Console c = System.console();

    boolean running = true;
    while (running){
    System.out.println("Insert a number to begin using your Contacts application");
    System.out.println("1. Create a new contact");
    System.out.println("2. Read contact information");
    System.out.println("3. Modify an existing contact");
    System.out.println("4. Delete a contact");
    System.out.println("5. Help with contact information formatting");
    System.out.println("6. Exit the program");

    int userinput = Integer.parseInt(c.readLine());

    switch(userinput) {
        case 1:
            System.out.println("Create a new contact:");
            newContact(c);
            break;
        case 2:
            System.out.println("Displaying all contact informations:");
            readContacts();
            break;
        case 3:
            System.out.println("Update contact information:");
            //updateContacts();
            break;
        case 4:
            System.out.println("Delete a contact");
            System.out.println("Kutsumetodia");
            System.out.println("Contact deletion has now been completed");
        break;
        case 5:
            System.out.println("Help with contact information formatting");
            System.out.println("1. Finnish ID tutorial");
            System.out.println("2. Naming policy");
            System.out.println("3. Finnish phone number format");
            System.out.println("4. Back to menu");
            int infoid = Integer.parseInt(c.readLine());
            tutorial(infoid);
             break;
        case 6:
            running=false;
            System.out.println("Thank you for using our application - see you soon!");
            break;

        default:
    }
    }
    }

    public static void tutorial(int infoid) {
        if (infoid == 1) {
        System.out.println("Enter the Finnish ID in this format DDMMYYCZZZQ. The ID consists of eleven characters: \n" +
        "DDMMYY = Date of birth (day, month, and year in a specific format).\n" +
        "C = Century marker indicating the century of birth: \n" +
        "'+' for the 1800s. | '-' or Y, X, W, V, U for the 1900s | A, B, C, D, E, F for the 2000s.\n" +
        "ZZZ = Individual number between 002 and 899 (even number for females, odd number for males).\n" +
        "Q = Control character is determined by dividing DDMMYYZZZ by 31 and using the remainder (0-30)\n"+
        "to pick up the corresponding character from the string: 0123456789ABCDEFHJKLMNPRSTUVWXY\n"+
        "Finnish ID example: 311299-9872");
        } else if (infoid == 2) {
             System.out.println("First and last name has to be at least one character long.\n" +
                                "First and last name example: Elmeri Gallen-Kallela-Siren-Guggenheim");
        } else if (infoid == 3){
            System.out.println("Enter phone number in this format: country code + 7-13 digits\n"+
            "First digit of the phone number is replaced with the Finnish country code +358.\n"+
                               "Finnish mobile number example: 044023423 \n"+
                                "Correct formatting example: +3584023423");

        } else if (infoid == 4){
            return;
        }
    }
    public static void readContacts() {

    try{
        File contactsapp = new File("contactsapp.csv");
        Scanner myReader = new Scanner(contactsapp);
        while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        String info[] = data.split(",");
        System.out.println("Contact number: "+ info[0]);
        System.out.println("---------------------------------------" +"\n" +
            "Finnish ID   | " + info[1] +"\n" +"First Name   | "+ info[2] + "\n" +"Last Name    | "+ info[3] +"\n"+"Phone Number | " + info[4] +"\n"+
            "---------------------------------------");
    }
    myReader.close();
    } catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
        }
        //https://www.w3schools.com/java/java_files_read.asp
    }

    //public static void updateContacts();{

    //Work in progress...

      //  String targetName="";

        //try{
        //File contactsapp = new File("contactsapp.csv");
        //Scanner myReader = new Scanner(contactsapp);
       // while (myReader.hasNextLine()) {
        //String data = myReader.nextLine();
        //String info[] = data.split(",");
        //System.out.println("Please enter the ID of the contact you want to change information from:");
        //readContacts();

        //System.out.println("Please enter the contact information as instructed:");
        //tutorial(1);
        //System.out.println("Please enter finnish personal ID number");
        //String newidNumber = c.readLine();
        //tutorial(2);
        //System.out.println("First name of the contact:");
        //String newfirstName = c.readLine();
        //System.out.println("Last name of the contact:");
        //String newlastName = c.readLine();
        //System.out.println("Please insert the phone number following our formatting tutorial:");
        //tutorial(3);
        //String newphoneNumber = c.readLine();



    //myReader.close();
    //} catch (FileNotFoundException e) {
       // System.out.println("An error occurred.");
       // e.printStackTrace();
     //   }
   // }
    //}

static class contactInfo {
    private String idNumber;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String email;
    private String contactId;

    public contactInfo(String idNumber, String firstName, String lastName,String phoneNumber){
        setidNumber(idNumber);
        setfirstName(firstName);
        setlastName(lastName);
        setphoneNumber(phoneNumber);
        setcontactId();

    }

    public void setcontactId(){

    int newId=1;

      try{
            File contactsapp = new File("contactsapp.csv");
            Scanner myReader = new Scanner(contactsapp);

            while(myReader.hasNextLine()){
            String line = myReader.nextLine();
            String[] info =line.split(",");
            newId = Integer.parseInt(info[0]) + 1;
            }
            myReader.close();

        } catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
        }

        this.contactId = Integer.toString(newId);


    }

    public String getcontactId(){
        return this.contactId;
    }


    public void setphoneNumber(String phoneNumber){

        String countryCode = "+358";
        String validNumber ="";

        if (phoneNumber.length() > 7 && phoneNumber.length() < 13){
            if(phoneNumber.startsWith("+358")){
                validNumber += countryCode;
                for(int d = 4; d < phoneNumber.length(); d++){
                    if(Character.isDigit(phoneNumber.charAt(d))){
                        validNumber += phoneNumber.charAt(d);
                    }
                }
                this.phoneNumber = validNumber;
            }
        } else {
            System.out.println("invalid input - seek help from our phone number formatting tutorial");
            tutorial(3);
            throw new IllegalArgumentException("Phone number needs to be formatted correctly!");
            }

    }

    public String getphoneNumber(){
        return this.phoneNumber;
    }

    public void setidNumber(String input){
        if (input.length() != 11) {
            System.out.println("invalid input - seek help from our Finnish ID formatting tutorial");
            tutorial(1);
            throw new IllegalArgumentException("Finnish ID needs to be formatted correctly");
        }

        String idNumber = input.toUpperCase();
        String ctrlcharacters ="0123456789ABCDEFHJKLMNPRSTUVWXY"; // 0-31
        String idstring ="";
        long divideid=0;
        long remainder=0;

            for(int i=0;i<10; i++) {
                char x = idNumber.charAt(i);
                if(x != '-'&& x!= '+' && x != 'A' && x != 'B' && x != 'C' && x != 'D' && x != 'E'
                    && x != 'F' && x != 'Y' && x != 'X' && x != 'W' && x != 'V' && x != 'U'){
                    idstring += x;
                    }
            }

            divideid = Long.parseLong(idstring);

            remainder = divideid % 31;

            char checkid = ctrlcharacters.charAt((int)remainder);

            if (idNumber.charAt(10) == checkid){
                this.idNumber = input;
            } else {
            System.out.println("invalid input - seek help from our Finnish ID formatting tutorial");
            tutorial(1);
            throw new IllegalArgumentException("Finnish ID needs to be formatted correctly!");
            }
    }

    public String getidNumber() {
        return this.idNumber;
    }

    public void setfirstName(String name) {
        String validfName="";
        if(name.length() >= 1){
            for( int i=0; i<name.length(); i++){
                char Letter = name.charAt(i);
                if(i==0 && Character.isLetter(Letter)){
                    validfName += Character.toUpperCase(Letter);
                } else if (Character.isLetter(Letter) || name.charAt(i) == '-') {
                    validfName += Character.toLowerCase(Letter);
                }
            }
            this.firstName = validfName;
        } else {
            System.out.println("invalid input - seek help from our formatting tutorial");
            tutorial(1);
            throw new IllegalArgumentException("Formatting error!");
        }
    }

    public String getfirstName() {
        return this.firstName;
    }

    public void setlastName(String lastname) {
        String validlName="";

        if(lastname.length() >= 1){
            for (int i = 0; i < lastname.length(); i++){
                char Letter = lastname.charAt(i);
                if(i == 0 && Character.isLetter(Letter)){
                    validlName += Character.toUpperCase(Letter);
                } else if (Character.isLetter(Letter) || lastname.charAt(i) == '-') {
                    validlName += Character.toLowerCase(Letter);
                    }
                }
            this.lastName = validlName;
        } else {
            System.out.println("invalid input - seek help from our formatting tutorial");
            tutorial(1);
            throw new IllegalArgumentException("Formatting error!");
        }
    }

    public String getlastName() {
        return this.lastName;
    }
}
    public static void newContact(Console c) {

        System.out.println("Please enter the contact information as instructed:");
        tutorial(1);
        System.out.println("Please enter finnish personal ID number");
        String idNumber = c.readLine();
        tutorial(2);
        System.out.println("First name of the contact:");
        String firstName = c.readLine();
        System.out.println("Last name of the contact:");
        String lastName = c.readLine();
        System.out.println("Please insert the phone number following our formatting tutorial:");
        tutorial(3);
        String phoneNumber = c.readLine();
        System.out.println(phoneNumber);


        contactInfo newContact = new contactInfo(idNumber, firstName, lastName, phoneNumber);
        try {FileWriter myWriter = new FileWriter("contactsapp.csv", true);
            myWriter.write(newContact.getcontactId() + "," + newContact.getidNumber() + "," + newContact.getfirstName()+ "," +newContact.getlastName()+ "," + newContact.getphoneNumber()+"\n");
            myWriter.close();
            System.out.println("Successfully added a new contact. How do you want to proceed?");

        }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        //https://www.w3schools.com/java/java_files_create.asp

    }
}
