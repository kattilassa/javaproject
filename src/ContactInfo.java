import java.io.Console;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactInfo {
    private String idNumber;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String email;
    private String contactId;

    public ContactInfo(String idNumber, String firstName, String lastName,String phoneNumber, String address, String email){
        setcontactId();
        setidNumber(idNumber);
        setfirstName(firstName);
        setlastName(lastName);
        setphoneNumber(phoneNumber);
        setAddress(address);
        setEmail(email);
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

            if(phoneNumber.startsWith("+358") && phoneNumber.length() >= 7 && phoneNumber.length() <= 13){
                validNumber += countryCode;
                for(int d = 4; d < phoneNumber.length(); d++){
                    if(Character.isDigit(phoneNumber.charAt(d))){
                        validNumber += phoneNumber.charAt(d);
                    }
                }
                this.phoneNumber = validNumber;
            } else {
                System.out.println("invalid input - seek help from our phone number formatting tutorial");
                UserHelp.tutorial(3);
                throw new IllegalArgumentException("Phone number needs to be formatted correctly!");
            }
    }

    public String getphoneNumber(){
        return this.phoneNumber;
    }

    public void setidNumber(String input){
        if (input.length() != 11) {
            System.out.println("invalid input - seek help from our Finnish ID formatting tutorial");
            UserHelp.tutorial(1);
            throw new IllegalArgumentException("Finnish ID needs to be formatted correctly");
        }

        String idNumber = input.toUpperCase();
        String ctrlCharacters ="0123456789ABCDEFHJKLMNPRSTUVWXY"; // 0-31
        String idString ="";
        long divideId=0;
        long remainder=0;

            for(int i=0;i<10; i++) {
                char x = idNumber.charAt(i);
                if(x != '-'&& x!= '+' && x != 'A' && x != 'B' && x != 'C' && x != 'D' && x != 'E'
                    && x != 'F' && x != 'Y' && x != 'X' && x != 'W' && x != 'V' && x != 'U'){
                    idString += x;
                    }
            }

            divideId = Long.parseLong(idString);

            remainder = divideId % 31;

            char checkId = ctrlCharacters.charAt((int)remainder);

            if (idNumber.charAt(10) == checkId){
                this.idNumber = input;
            } else {
            System.out.println("invalid input - seek help from our Finnish ID formatting tutorial");
            UserHelp.tutorial(1);
            throw new IllegalArgumentException("Finnish ID needs to be formatted correctly!");
            }
    }

    public String getidNumber() {
        return this.idNumber;
    }

    public void setfirstName(String name) {
        String validName="";
        if(name.length() >= 1){
            for( int i=0; i<name.length(); i++){
                char Letter = name.charAt(i);
                if(i==0 && Character.isLetter(Letter)){
                    validName += Character.toUpperCase(Letter);
                } else if (Character.isLetter(Letter) || name.charAt(i) == '-') {
                    validName += Character.toLowerCase(Letter);
                }
            }
            this.firstName = validName;
        } else {
            System.out.println("invalid input - seek help from our formatting tutorial");
            UserHelp.tutorial(1);
            throw new IllegalArgumentException("Formatting error!");
        }
    }

    public String getfirstName() {
        return this.firstName;
    }

    public void setlastName(String lastName) {
        String validLastName="";

        if(lastName.length() >= 1){
            for (int i = 0; i < lastName.length(); i++){
                char Letter = lastName.charAt(i);
                if(i == 0 && Character.isLetter(Letter)){
                    validLastName += Character.toUpperCase(Letter);
                } else if (Character.isLetter(Letter) || lastName.charAt(i) == '-') {
                    validLastName += Character.toLowerCase(Letter);
                    }
                }
            this.lastName = validLastName;
        } else {
            System.out.println("invalid input - seek help from our formatting tutorial");
            UserHelp.tutorial(1);
            throw new IllegalArgumentException("Formatting error!");
        }
    }

    public String getlastName() {
        return this.lastName;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        String option="1";
        String empty="-";
        Pattern validAddress=Pattern.compile("^[äöåÅÄÖÅA-Z0-9'\\-\\s]+$", Pattern.CASE_INSENSITIVE);
        Matcher match = validAddress.matcher(address);

        if(address.equals(option)){
            this.address = empty;
        } else if (match.matches()) {
        this.address = address;
        } else {
            System.out.println("invalid input - seek help from our formatting tutorial");
            UserHelp.tutorial(4);
            throw new IllegalArgumentException("Address format error!");
        }
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        String option="1";
        String empty="-";

        Pattern validEmail=Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,3}$", Pattern.CASE_INSENSITIVE);
        Matcher match = validEmail.matcher(email);

        if(email.equals(option)){
            this.email = empty;
        } else if (match.matches()) {
        this.email = email;
        } else {
            System.out.println("invalid input - seek help from our formatting tutorial");
            UserHelp.tutorial(5);
            throw new IllegalArgumentException("Email format error!");
        }

    }

    public static void newContact(Console c) {

        System.out.println("Please enter the contact information as instructed:");
        UserHelp.tutorial(1);
        System.out.println("Please enter the Finnish personal ID number");
        String idNumber = c.readLine();
        UserHelp.tutorial(2);
        System.out.println("First name of the contact:");
        String firstName = c.readLine();
        System.out.println("Last name of the contact:");
        String lastName = c.readLine();
        System.out.println("Please insert the phone number following our formatting tutorial:");
        UserHelp.tutorial(3);
        String phoneNumber = c.readLine();
        UserHelp.tutorial(4);
        System.out.println("Contact address:");
        String address = c.readLine();
        UserHelp.tutorial(5);
        System.out.println("Contact email address:");
        String email = c.readLine();


        ContactInfo newContact = new ContactInfo(idNumber, firstName, lastName, phoneNumber, address, email);
        writeandConfirm(c,newContact);
    }
    public static void writeandConfirm(Console c, ContactInfo newContact) {
        System.out.println("Do you want to save this contact?:");
        System.out.println("---------------------------------------" +"\n" +
            "Finnish ID   | " + newContact.getidNumber() +"\n" +"First Name   | "+ newContact.getfirstName()  + "\n" +"Last Name    | "+ newContact.getlastName() +"\n"
            +"Phone Number | " + newContact.getphoneNumber() +"\n"+ "Address      | " + newContact.getAddress() +"\n"+ "Email        | " + newContact.getEmail()+"\n"+
            "---------------------------------------");
        System.out.println("1. Save this contact");
        System.out.println("2. Cancel and start again without saving");
        System.out.println("3. Exit contact creation without saving");

        int confirm = Integer.parseInt(c.readLine());

        if (confirm ==1){
        try {
            FileWriter myWriter = new FileWriter("contactsapp.csv", true);
            myWriter.write(newContact.getcontactId() + "," + newContact.getidNumber() + "," + newContact.getfirstName()+ "," +
            newContact.getlastName()+ "," + newContact.getphoneNumber()+","+newContact.getAddress()+","+ newContact.getEmail()+"\n");
            myWriter.close();
            System.out.println("Successfully added a new contact. How do you want to proceed?");

        }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        } else if (confirm==2) {
            newContact(c);
            return;
        } else if (confirm==3){
            System.out.println("Contact creation was cancelled by the user.");
        } else {
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
            "Finnish ID   | " + info[1] +"\n" +"First Name   | "+ info[2] + "\n" +"Last Name    | "+ info[3] +"\n"+"Phone Number | " +
             info[4] +"\n"+"Address      | "+ info[5] +"\n"+"Email        | "+ info[6] +"\n"+
            "---------------------------------------");
        }
        myReader.close();
    } catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
        }
    }

    public static void updateContacts(Console c) {

        readContacts();
        System.out.println("Please enter the contact number of the contact you want to change information from:");
        String targetId = c.readLine();
        ArrayList<String> contacts = new ArrayList<String>();
        int targetRow=0;

        try{
            File contactsapp = new File("contactsapp.csv");
            Scanner myReader = new Scanner(contactsapp);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                contacts.add(data);
                String info[] = data.split(",");
                    if(info[0].equals(targetId)){
                        targetRow = contacts.size() -1;
                    } else {
                        System.out.println("No match found for the contact number, please try again.");
                        return;
                    }
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

                String[]Target = contacts.get(targetRow).split(",");
                    System.out.println("Do you want to edit this contact?");
                    System.out.println("Contact number: "+ Target[0]);
                    System.out.println("---------------------------------------" +"\n" +
                            "Finnish ID   | " + Target[1] +"\n" +"First Name   | "+ Target[2] + "\n" +"Last Name    | "+ Target[3] +"\n"+"Phone Number | " +
                            Target[4] +"\n"+"Address      | "+ Target[5] +"\n"+"Email        | "+ Target[6] +"\n"+
                            "---------------------------------------");
                    System.out.println("1.Yes");
                    System.out.println("2.No");

                    String answer = c.readLine();


            if(answer.equals("1")){
                System.out.println("Please enter the contact information as instructed:");
                UserHelp.tutorial(1);
                System.out.println("Please enter finnish personal ID number");
                String newidNumber = c.readLine();
                UserHelp.tutorial(2);
                System.out.println("First name of the contact:");
                String newfirstName = c.readLine();
                System.out.println("Last name of the contact:");
                String newlastName = c.readLine();
                System.out.println("Please insert the phone number following our formatting tutorial:");
                UserHelp.tutorial(3);
                String newphoneNumber = c.readLine();
                System.out.println("Contact address:");
                UserHelp.tutorial(4);
                String newAddress=c.readLine();
                UserHelp.tutorial(5);
                System.out.println("Contact email address:");
                String newEmail=c.readLine();

                ContactInfo updateContact = new ContactInfo(newidNumber, newfirstName, newlastName, newphoneNumber, newAddress, newEmail);
                String updatedInfo = Target[0] + "," + updateContact.getidNumber() + "," + updateContact.getfirstName()+
                 "," +updateContact.getlastName()+ "," + updateContact.getphoneNumber()+","+ updateContact.getAddress()+","+ updateContact.getEmail();

                System.out.println("Do you want to update and save this contact?:");
                System.out.println("---------------------------------------" +"\n" +
                "Finnish ID   | " + updateContact.getidNumber() +"\n" +"First Name   | "+ updateContact.getfirstName()  + "\n" +"Last Name    | "+ updateContact.getlastName() +"\n"
                    +"Phone Number | " + updateContact.getphoneNumber() +"\n"+ "Address      | " + updateContact.getAddress() +"\n"+ "Email        | " + updateContact.getEmail()+"\n"+
                    "---------------------------------------");
                System.out.println("1. Update this contact");
                System.out.println("2. Cancel contact updating without saving");

                int confirm = Integer.parseInt(c.readLine());

                if (confirm==1){
                    contacts.set(targetRow,updatedInfo);
                    try{
                        FileWriter myWriter = new FileWriter("contactsapp.csv");
                            for(int i=0; i<contacts.size(); i++){
                                myWriter.write(contacts.get(i));
                                myWriter.write("\n");
                                }
                                myWriter.close();
                                System.out.println("Successfully updated the contact. How do you want to proceed?");
                    }catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }
                } else {
                    return;
                }
            }else {
            return;
            }
    }

    public static void deleteContacts(Console c) {

    readContacts();
    System.out.println("Please enter the contact number of the contact you want to delete:");
        String deleteId = c.readLine();
        ArrayList<String> contacts = new ArrayList<String>();
        int targetRow=0;

        try{
            File contactsapp = new File("contactsapp.csv");
            Scanner myReader = new Scanner(contactsapp);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                contacts.add(data);
                String info[] = data.split(",");
                    if(info[0].equals(deleteId)){
                        targetRow = contacts.size() -1;
                    } else {
                        System.out.println("No match found for the contact number, please try again.");
                        return;
                    }
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

                String[]Target = contacts.get(targetRow).split(",");
                    System.out.println("Are you sure you want to delete this contact?");
                    System.out.println("Contact number: "+ Target[0]);
                    System.out.println("---------------------------------------" +"\n" +
                            "Finnish ID   | " + Target[1] +"\n" +"First Name   | "+ Target[2] + "\n" +"Last Name    | "+ Target[3] +"\n"+"Phone Number | " +
                            Target[4] +"\n"+"Address      | "+ Target[5] +"\n"+"Email        | "+ Target[6] +"\n"+
                            "---------------------------------------");
                    System.out.println("1. Yes, delete this contact.");
                    System.out.println("2. No, cancel deletion and return to the contact selection screen.");
                    System.out.println("3. Exit back to menu");

                    String answer = c.readLine();

        if(answer.equals("1")){
            try{
                contacts.remove(targetRow);
                FileWriter myWriter = new FileWriter("contactsapp.csv");
                   for(int i=0; i<contacts.size(); i++){
                        myWriter.write(contacts.get(i));
                        myWriter.write("\n");
                        }
                        System.out.println("Successfully deleted the contact. How do you want to proceed?");
                    myWriter.close();
                }catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
        }else if (answer.equals("2")){
            deleteContacts(c);
        } else {
            return;
        }
    }
}