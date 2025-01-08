import java.io.Console;

/**
 * The class ContactsApp contains the main method of the ContactsApp application.
 *
 * The main method is the starting point for the application and it acts as a user menu.
 *
 * ContactsApp is a program where users can create, read, update and delete Finnish contacts.
 * Saved contacts are stored in a persistent CSV-file.
 *
 * @author Kati Savolainen
 * @version 1.0
 * @since   2024-12-16
 */
public class ContactsApp {

    /**Constant for the action of adding a new contact. */
    public static final int ADD_CONTACT = 1;
    /**Constant for the action of reading the saved contact list. */
    public static final int READ_CONTACT = 2;
    /**Constant for the action of editing an existing contact. */
    public static final int EDIT_CONTACT = 3;
    /**Constant for the action of deleting an existing contact. */
    public static final int DELETE_CONTACT = 4;
    /**Constant for the action of accessing the user tutorial. */
    public static final int TUTORIAL = 5;
    /**Constant for the action of exiting the application. */
    public static final int EXIT_APP = 6;

/**
 * The main method of the ContactsApp application prints user prompts
 * to standard output.
 *
 * Based on the input user can:
 * - access the ContactInfo.java class for creating and managing contact information.
 * - access the UserHelp.java class for the tutorial method.
 * - shut down the application.
 *
 * @param args command-line arguments are not used in this application.
 */
    public static void main(String[] args) {
        Console c = System.console();

        boolean running = true;
        while (running) {
            System.out.println("Insert a number to begin using your ContactsApp application");
            System.out.println("1. Add a new contact");
            System.out.println("2. View contact information");
            System.out.println("3. Edit contact details");
            System.out.println("4. Delete a contact");
            System.out.println("5. Contact formatting guide");
            System.out.println("6. Exit the application");

            int userInput = Integer.parseInt(c.readLine());

            switch (userInput) {
                case ADD_CONTACT:
                    System.out.println("Create a new contact:");
                    ContactInfo.newContact(c);
                    break;
                case READ_CONTACT:
                    System.out.println("Displaying all contact information:");
                    ContactInfo.readContacts();
                    break;
                case EDIT_CONTACT:
                    System.out.println("Update contact information:");
                    ContactInfo.updateContacts(c);
                    break;
                case DELETE_CONTACT:
                    System.out.println("Delete a contact");
                    ContactInfo.deleteContacts(c);
                    break;
                case TUTORIAL:
                    System.out.println("Help with contact information formatting");
                    System.out.println("1. Finnish ID tutorial");
                    System.out.println("2. Naming policy");
                    System.out.println("3. Finnish phone number format");
                    System.out.println("4. Address information");
                    System.out.println("5. Email information");
                    System.out.println("6. Exit to menu");

                    int infoId = Integer.parseInt(c.readLine());
                    UserHelp.tutorial(infoId);
                    break;
                case EXIT_APP:
                    running = false;
                    System.out.println("Thank you for using our application - see you soon!");
                    break;
            }
        }
    }
}
