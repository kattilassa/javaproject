import java.io.Console;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
*
* ContactInfo class contains methods for creating, reading, updating and
* deleting Finnish contacts that are stored in the contactsapp.csv file.
*
* ContactInfo class contains all the main features of the ContactsApp
* application and the methods are called from the ContactsApp.java class that
* acts as user menu.
*
* @author Kati Savolainen
* @version 1.0
* @since   2024-12-16
*/
public class ContactInfo {
    /**Finnish ID number of the contact. */
    private String idNumber;
    /**First name of the contact. */
    private String firstName;
    /**Last name of the contact. */
    private String lastName;
    /**Phone number of the contact. */
    private String phoneNumber;
    /**Home address of the contact. */
    private String address;
    /**Email address of the contact. */
    private String email;
    /**Unique contact ID number of the contact. */
    private String contactId;

//User input constants that are used in the code

/**Constant for confirming actions. */
    public static final int YES = 1;
/**Constant used for cancelling actions. */
    public static final int CANCEL = 2;
/**Constant used for exiting back to the user menu. */
    public static final int EXIT_TO_MENU = 3;

/**
 * Constructor for the ContactInfo object.
 *
 * ContactInfo constructor assigns a unique contact ID for the contact and
 * sends the user input for the validation process through the setter methods.
 *
 * @param idNumber is the Finnish ID of the contact.
 * @param firstName is the first name of the contact.
 * @param lastName is the last name of the contact.
 * @param phoneNumber is the phone number of the contact.
 * @param address is the home address of the contact.
 * @param email is the email address of the contact.
 */
    public ContactInfo(String idNumber, String firstName, String lastName,
        String phoneNumber, String address, String email) {
        setcontactId();
        setidNumber(idNumber);
        setfirstName(firstName);
        setlastName(lastName);
        setphoneNumber(phoneNumber);
        setAddress(address);
        setEmail(email);
    }

/**
 * SetcontactId sets the contact ID for the contact.
 *
 * The setcontactId method checks the contactsapp.csv for the latest contact ID
 * and it assigns a unique number identifier for the contact by adding +1 to the latest ID.
 *
 * If contactsapp.csv has not been created or no previous ID's have not been set
 * - the first contact ID will be assigned as 1.
 */
    public void setcontactId() {

    int newId = 1;
    //newId is assigned as 1 if the contactsapp.csv file does not exist
    //or if no previous contact ID numbers have been set before.

      try {
            File contactsapp = new File("contactsapp.csv");
            Scanner myReader = new Scanner(contactsapp);

            while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            String[] info = line.split(",");
            newId = Integer.parseInt(info[0]) + 1;
            //newId is number where + 1 has been added to the latest contact ID.
            //this ensures every contact has a unique contact ID number.
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        this.contactId = Integer.toString(newId);
    }
/**
 * Returns the Contact ID of the contact.
 *
 * @return the contact ID of the contact.
 */
    public String getcontactId() {
        return this.contactId;
    }
/**
 * SetphoneNumber validates and sets the phone number for the contact.
 *
 * The SetphoneNumber method checks that the phone number is formatted correctly.
 * This method checks that the user input starts with the Finnish country code +358
 * and the input is between 7-13 digits and contains only digits.
 *
 * @param phoneNumber is the phone number sent by the user in the contact creation process.
 */
    public void setphoneNumber(String phoneNumber) {

        String countryCode = "+358";
        String validNumber = "";

            if (phoneNumber.startsWith("+358") && phoneNumber.length() >= 7 && phoneNumber.length() <= 13) {
                validNumber += countryCode;
                for (int d = 4; d < phoneNumber.length(); d++) {
                    if (Character.isDigit(phoneNumber.charAt(d))) {
                        validNumber += phoneNumber.charAt(d);
                        //if the user input starts with +358 and it's between 7-13 digits
                        //countryCode is added to the validNumber String.
                        //validNumber checks and stores all the remaining digits after the country code.
                    }
                }
                this.phoneNumber = validNumber;
            } else {
                System.out.println("invalid input - seek help from our phone number formatting tutorial");
                UserHelp.tutorial(3);
                throw new IllegalArgumentException("Phone number needs to be formatted correctly!");
            }
    }

/**
 * Returns the phone number of the contact.
 *
 * @return the phone number of the contact.
 */
    public String getphoneNumber() {
        return this.phoneNumber;
    }

/**
 * SetidNumber validates and sets the Finnish ID number for the contact.
 *
 * The SetIdNumber method checks that the Finnish ID is formatted correctly.
 * Finnish ID has to have 11 characters and the ID is checked with the
 * control character that is determined by dividing first 9 digits by 31
 * and using the remainder (0-30) to pick up the corresponding character
 * from the string ctrlCharacters.
 *
 * If the last ID number/letter matches the calculated control character
 * the Finnish ID is formatted correctly and it's an valid Finnish ID.
 *
 * @param input Finnish ID sent by the user in the contact creation process.
 */
    public void setidNumber(String input) {
        if (input.length() != 11) {
            System.out.println("invalid input - seek help from our Finnish ID formatting tutorial");
            UserHelp.tutorial(1);
            throw new IllegalArgumentException("Finnish ID needs to be formatted correctly");
        }

        String idNumber = input.toUpperCase();
        String ctrlCharacters = "0123456789ABCDEFHJKLMNPRSTUVWXY"; // 0-31
        String idString = "";
        long divideId = 0;
        long remainder = 0;

            for (int i = 0; i < 10; i++) {
                char x = idNumber.charAt(i);
                if (x != '-' && x != '+' && x != 'A' && x != 'B' && x != 'C' && x != 'D' && x != 'E'
                    && x != 'F' && x != 'Y' && x != 'X' && x != 'W' && x != 'V' && x != 'U') {
                    idString += x;
                    }
            }
        //Century markers are excluded from the calculation:
        //'+' for the 1800s.
        // '-' or Y, X, W, V, U for the 1900s.
        // A, B, C, D, E, F for the 2000s.

            divideId = Long.parseLong(idString);

            //remainder calculation
            remainder = divideId % 31;

            char checkId = ctrlCharacters.charAt((int) remainder);
            //checkId will be assigned as the control character from the string

            if (idNumber.charAt(10) == checkId) {
                this.idNumber = input;
            //If the last character from the ID matches the control character
            //user inputted Finnish ID is valid.
            } else {
            System.out.println("invalid input - seek help from our Finnish ID formatting tutorial");
            UserHelp.tutorial(1);
            throw new IllegalArgumentException("Finnish ID needs to be formatted correctly!");

            }
    }

/**
 * Returns the Finnish ID number of the contact.
 *
 * @return the Finnish ID number of the contact.
 */
    public String getidNumber() {
        return this.idNumber;
    }

/**
 * SetfirstName validates and sets the first name for the contact.
 *
 * The SetfirstName method checks that the name is between 1 and 24 characters long
 * and that the first letter is in upper case and the rest are in lower case.
 * this method also allows hyphens between names such as "Hanna-Mari".
 *
 * @param name is the first name of the contact sent by the user in the contact creation process.
 */
    public void setfirstName(String name) {
        String validName = "";
        if (name.length() >= 1 && name.length() <= 24) {
            for (int i = 0; i < name.length(); i++) {
                char letter = name.charAt(i);
                if (i == 0 && Character.isLetter(letter)) {
                    validName += Character.toUpperCase(letter);
                } else if (Character.isLetter(letter) || name.charAt(i) == '-') {
                    validName += Character.toLowerCase(letter);
                }
            }
            this.firstName = validName;
        } else {
            System.out.println("invalid input - seek help from our formatting tutorial");
            UserHelp.tutorial(2);
            throw new IllegalArgumentException("Formatting error!");
        }
    }

/**
 * Returns the first name of the contact.
 *
 * @return the first name of the contact.
 */
    public String getfirstName() {
        return this.firstName;
    }

/**
 * SetlastName validates and sets the last name for the contact.
 *
 * The SetlastName method checks that the last name is between 1 and 24 characters long
 * and that the first letter is in upper case and the rest are in lower case.
 * this method also allows hyphenated last names such as "Meikäläinen-Mattilainen".
 *
 * @param lastName is the last name of the contact sent by the user in the contact creation process.
 */
    public void setlastName(String lastName) {
        String validLastName = "";

        if (lastName.length() >= 1 && lastName.length() <= 24) {
            for (int i = 0; i < lastName.length(); i++) {
                char Letter = lastName.charAt(i);
                if (i == 0 && Character.isLetter(Letter)) {
                    validLastName += Character.toUpperCase(Letter);
                } else if (Character.isLetter(Letter) || lastName.charAt(i) == '-') {
                    validLastName += Character.toLowerCase(Letter);
                    }
                }
            this.lastName = validLastName;
        } else {
            System.out.println("invalid input - seek help from our formatting tutorial");
            UserHelp.tutorial(2);
            throw new IllegalArgumentException("Formatting error!");
        }
    }

/**
 * Returns the last name of the contact.
 *
 * @return the last name of the contact.
 */
    public String getlastName() {
        return this.lastName;
    }

/**
 * Returns the address of the contact.
 *
 * @return the address of the contact.
 */
    public String getAddress() {
        return this.address;
    }

/**
 * SetAddress validates and sets the address for the contact.
 *
 * The SetAddress method checks that the address contains only upper- and lowercase
 * letters, numbers from 0-9, hyphens "-" and empty spaces.
 *
 * Address information is optional in the contact creation process.
 * If the user inputs "1" and doesn't want to give contact address information
 * the contact address will be set to "-" by default and can be changed later.
 *
 * @param address is the home address of the contact sent by the user in the contact creation process.
 */
    public void setAddress(String address) {
        String option = "1";
        String empty = "-";
        Pattern validAddress = Pattern.compile("^[äöåÅÄÖÅA-Z0-9'\\-\\s]+$", Pattern.CASE_INSENSITIVE);
        Matcher match = validAddress.matcher(address);

        if (address.equals(option)) {
            this.address = empty;
        //the contact address will be set to "-" if the user wishes to skip this part.
        } else if (match.matches()) {
        this.address = address;
        } else {
            System.out.println("invalid input - seek help from our formatting tutorial");
            UserHelp.tutorial(4);
            throw new IllegalArgumentException("Address format error!");
        }
    }

/**
 * Returns the email address of the contact.
 *
 * @return the email address of the contact.
 */
    public String getEmail() {
        return this.email;
    }

/**
 * SetEmail validates and sets the email address for the contact.
 *
 * This method allows letters from A-Z, numbers from 0-9 and special characters
 * ".", "_", "%", "+", and "-" before the @ character.
 * End of the email must contain a dot "." and 2-3 letters such as .com or .fi for example.
 *
 * Email information is optional in the contact creation process.
 * If the user inputs "1" and doesn't want to fill email information
 * the contact email will be set to "-" by default and can be changed later.
 *
 * @param email is the email address of the contact sent by the user in the contact creation process.
 */
    public void setEmail(String email) {
        String option = "1";
        String empty = "-";

        Pattern validEmail = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,3}$", Pattern.CASE_INSENSITIVE);
        Matcher match = validEmail.matcher(email);

        if (email.equals(option)) {
            this.email = empty;
            //the email address will be set to "-" if the user wishes to skip this part.
        } else if (match.matches()) {
        this.email = email;
        } else {
            System.out.println("invalid input - seek help from our formatting tutorial");
            UserHelp.tutorial(5);
            throw new IllegalArgumentException("Email format error!");
        }
    }
/**
 * newContact method creates a new contact from user input after the validation.
 *
 * newContact method prints user prompts and calls the tutorial method in
 * Userhelp.java class so it can instruct the user in the contact creation process.
 *
 * After creating the contact the writeandConfirm method is called to
 * display the contact details to the user.
 *
 * @param c console for reading user input.
 */
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

        //Create a contact after the user input has been
        // validated through the constructor.
        ContactInfo newContact = new ContactInfo(
        idNumber, firstName, lastName, phoneNumber, address, email);

        writeandConfirm(c, newContact);
        //writeandConfirm method is called to display
        //the contact and print user prompts.
    }
/**
 * writeandConfirm method displays the contact details after the
 * contact creation process and prints user prompts.
 *
 * User is able to view the contact details before it's saved to the CSV-file.
 *
 * After the method displays the contact details, user has three choices :
 * - Save the contact by writing it to the CSV-file.
 * - Cancel and start the contact creation from the beginning
 *   by calling the newContact method.
 * - Exit back to the user menu.
 *
 * @param c Console for reading user input.
 * @param newContact is the new contact created in the contact creation process.
 */
    public static void writeandConfirm(Console c, ContactInfo newContact) {
        System.out.println("Do you want to save this contact?:");
        System.out.println("---------------------------------------"
            + "\n"
            + "Finnish ID   | " + newContact.getidNumber()
            + "\n"
            + "First Name   | " + newContact.getfirstName()
            + "\n"
            + "Last Name    | " + newContact.getlastName()
            + "\n"
            + "Phone Number | " + newContact.getphoneNumber()
            + "\n"
            + "Address      | " + newContact.getAddress()
            + "\n"
            + "Email        | " + newContact.getEmail()
            + "\n"
            + "---------------------------------------");
            //object getters are called to get the newly created contact details
        System.out.println("1. Save this contact");
        System.out.println("2. Cancel and start again without saving");
        System.out.println("3. Exit contact creation without saving");

        int confirm = Integer.parseInt(c.readLine());

        if (confirm == YES) {

        try {
            //if user confirms to save the contact - contact details are written
            //to the contactsapp.csv file seperated by commas - ",".
            FileWriter myWriter = new FileWriter("contactsapp.csv", true);
            myWriter.write(newContact.getcontactId() + ","
            + newContact.getidNumber() + ","
            + newContact.getfirstName() + ","
            + newContact.getlastName() + ","
            + newContact.getphoneNumber() + ","
            + newContact.getAddress() + ","
            + newContact.getEmail()
            + "\n"); //new line after every contact
            myWriter.close();

            System.out.println("Successfully added a new contact. How do you want to proceed?");

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        } else if (confirm == CANCEL) {
            newContact(c);
            //User can also cancel and start the contact creation process from the beginning
            //this is done by calling the newContact method.
            return;
        } else if (confirm == EXIT_TO_MENU) {
            //User also has a choice to exit back to the menu.
            System.out.println("Contact creation was cancelled by the user.");
        } else {
            return;
        }
    }

/**
 * readContacts method reads all saved contact details from the CSV-file
 * and prints them to standard output.
 *
 * User is able to view all the contact details stored in the CSV-file through
 * this method as it prints the contact details arranged as a contact list.
 */
    public static void readContacts() {

    try {
        File contactsapp = new File("contactsapp.csv");
        Scanner myReader = new Scanner(contactsapp);
        while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        String info[] = data.split(",");
        //After reading each line, contact details are splitted by the commas
        //and stored in an array for printing.

        //Contact details are printed to standard output in this order:
        System.out.println("Contact ID:  " + info[0]);
        System.out.println("---------------------------------------"
        + "\n"
        + "Finnish ID   | " + info[1]
        + "\n"
        + "First Name   | " + info[2]
        + "\n"
        + "Last Name    | " + info[3]
        + "\n"
        + "Phone Number | " + info[4]
        + "\n"
        + "Address      | " + info[5]
        + "\n"
        + "Email        | " + info[6]
        + "\n"
        + "---------------------------------------");
        }
        myReader.close();
    } catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
        }
    }
/**
 * updateContacts method is used to update contact details from an existing contact.
 *
 * Method prints the contact list for contact selection and guides the user
 * through the contact updating process. Contacts are stored in an ArrayList
 * and the updated contact list will be written back to the CSV-file.
 *
 * @param c Console for reading user input.
 */
    public static void updateContacts(Console c) {

        readContacts();
        //readContacts method displays the contact list for the user.
        System.out.println("Please enter the contact ID of the contact you want to change information from:");
        String targetId = c.readLine();
        //prompts the user to enter the contact ID for the contact they want to update.

        ArrayList<String> contacts = new ArrayList<String>();
        //ArrayList is created for reading and storing all the contacts from the
        //CSV-file. This ArrayList will be written back to the CSV-file later if
        //the user confirms the contact detail changes.

        int targetRow = 0;
        //targetRow is set to 0 as default and will be replaced with the index
        //of the selected contact if the contact ID is found from the list.

        try {
            File contactsapp = new File("contactsapp.csv");
            Scanner myReader = new Scanner(contactsapp);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                contacts.add(data);
                String info[] = data.split(",");
                    if (info[0].equals(targetId)) {
                        targetRow = contacts.size() - 1;
                    }
            //If the current contact ID matches to the user input ID
            //Int targetrow is the row index where the contact ID's matched.
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

                //Splits and prints the target contact info to the user
                //if any of the contact ID's matched to the user input.
                String[]Target = contacts.get(targetRow).split(",");
                    System.out.println("Do you want to edit this contact?");
                    System.out.println("Contact ID: " + Target[0]);
                    System.out.println("---------------------------------------"
                    + "\n"
                    + "Finnish ID   | " + Target[1]
                    + "\n"
                    + "First Name   | " + Target[2]
                    + "\n"
                    + "Last Name    | " + Target[3]
                    + "\n"
                    + "Phone Number | " + Target[4]
                    + "\n"
                    + "Address      | " + Target[5]
                    + "\n"
                    + "Email        | " + Target[6]
                    + "\n"
                    + "---------------------------------------");
                    System.out.println("1.Yes, I want to edit this contact.");
                    System.out.println("2.No, return to the contact selection screen.");
                    System.out.println("3.Exit back to menu.");

                    int confirm = Integer.parseInt(c.readLine());
                    //After the contact updating process user has three options:
                    //Confirm and update contact details from the selected contact into the file.
                    //Select a new contact from the contact list and start from the beginning.
                    //Exit back to the user menu.

            if (confirm == YES) {
                //user prompts are printed and tutorial method in Userhelp.java
                //class is called so the user is instructed in the contact updating process.
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
                String newAddress = c.readLine();
                UserHelp.tutorial(5);
                System.out.println("Contact email address:");
                String newEmail = c.readLine();

                //New updated contact is created with the updated contact details.
                //UpdateContact object gets the same contact ID from the old contact.
                ContactInfo updateContact = new ContactInfo(newidNumber, newfirstName, newlastName, newphoneNumber, newAddress, newEmail);
                String updatedInfo = Target[0] + ","
                + updateContact.getidNumber() + ","
                + updateContact.getfirstName() + ","
                + updateContact.getlastName() + ","
                + updateContact.getphoneNumber() + ","
                + updateContact.getAddress() + ","
                + updateContact.getEmail();

                //User gets so see the contact information before rewriting
                //the contact to the CSV-file in the target row line.
                System.out.println("Do you want to update and save this contact?:");
                System.out.println("---------------------------------------"
                + "\n"
                + "Finnish ID   | " + updateContact.getidNumber()
                + "\n"
                + "First Name   | " + updateContact.getfirstName()
                + "\n"
                + "Last Name    | " + updateContact.getlastName()
                + "\n"
                + "Phone Number | " + updateContact.getphoneNumber()
                + "\n"
                + "Address      | " + updateContact.getAddress()
                + "\n"
                + "Email        | " + updateContact.getEmail()
                + "\n"
                + "---------------------------------------");
                System.out.println("1. Yes, update this contact.");
                System.out.println("2. No, cancel contact updating without saving.");

                confirm = Integer.parseInt(c.readLine());

                if (confirm == YES) {
                    contacts.set(targetRow, updatedInfo);
                //this will replace the old contact with the updated contact details.
                    try {
                        FileWriter myWriter = new FileWriter("contactsapp.csv");
                            for (int i = 0; i < contacts.size(); i++) {
                                myWriter.write(contacts.get(i));
                                myWriter.write("\n");
                //After user confirms the changes, contacts are written back
                //to the CSV-file from the arraylist with the updated contact line.
                                }
                                myWriter.close();
                                System.out.println("Successfully updated the contact. How do you want to proceed?");
                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }
                } else if (confirm == CANCEL) {
                    return;
                }
            } else if (confirm == CANCEL) {
                updateContacts(c);
                //User can also return to the contact selection screen
                //and start the process from the beginning.
            } else {
                return;
            }
    }
/**
 * deleteContacts method is used to delete existing contacts from the CSV-file.
 *
 * Method prints the contact list for contact selection and guides the user
 * through the deletion process. Contacts are stored in an ArrayList and the
 * updated contact list will be written back to the CSV-file after the
 * contact has been deleted from the ArrayList.
 *
 * @param c Console for reading user input.
 */
    public static void deleteContacts(Console c) {

    readContacts();
    //readContacts method displays the contact list for the user.
    System.out.println("Please enter the contact ID of the contact you want to delete:");
    //prompts the user to enter the contact ID for the contact they want to delete.
        String deleteId = c.readLine();
        ArrayList<String> contacts = new ArrayList<String>();
    //ArrayList is created for reading and storing all the contacts from the
    //CSV-file. This ArrayList will be written back to the CSV-file later if
    //the user confirms the contact deletion.
        int targetRow = 0;
    //targetRow is set to 0 as default and will be replaced with the index
    //of the selected contact if the contact ID is found from the list.

        try {
            File contactsapp = new File("contactsapp.csv");
            Scanner myReader = new Scanner(contactsapp);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                contacts.add(data);
                String info[] = data.split(",");
                    if (info[0].equals(deleteId)) {
                        targetRow = contacts.size() - 1;
                    }
            //If the current contact ID matches to the user input ID
            //Int targetrow is the row index where the contact ID's matched.
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
                //Splits and prints the target contact info to the user
                //if any of the contact ID's matched to the user input.
                String[]Target = contacts.get(targetRow).split(",");
                    System.out.println("Are you sure you want to delete this contact?");
                    System.out.println("Contact ID: " + Target[0]);
                    System.out.println("---------------------------------------"
                    + "\n"
                    + "Finnish ID   | " + Target[1]
                    + "\n"
                    + "First Name   | " + Target[2]
                    + "\n"
                    + "Last Name    | " + Target[3]
                    + "\n"
                    + "Phone Number | " + Target[4]
                    + "\n"
                    + "Address      | " + Target[5]
                    + "\n"
                    + "Email        | " + Target[6]
                    + "\n"
                           + "---------------------------------------");
                    System.out.println("1. Yes, delete this contact.");
                    System.out.println("2. No, cancel deletion and return to the contact selection screen.");
                    System.out.println("3. Exit back to menu.");

                    int confirm = Integer.parseInt(c.readLine());
                    //User has three options:
                    //Confirm and delete the contact from the ArrayList.
                    //Select a new contact from the contact list.
                    //Exit back to the user menu.

        if (confirm == YES) {
            try {
                contacts.remove(targetRow);
                //this removes the selected contact from the ArrayList.
                FileWriter myWriter = new FileWriter("contactsapp.csv");
                   for (int i = 0; i < contacts.size(); i++) {
                        myWriter.write(contacts.get(i));
                        myWriter.write("\n");
                //Updated contact list will be written back to the CSV-file.
                        }
                        System.out.println("Successfully deleted the contact. How do you want to proceed?");
                    myWriter.close();
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
        } else if (confirm == CANCEL) {
            //User can cancel and start the deletion process from the beginning.
            deleteContacts(c);
        } else {
            //User can also exit back to the menu.
            return;
        }
    }
}
