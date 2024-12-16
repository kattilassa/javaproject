import java.io.Console;

/**
* The class ContactsApp contains the main method of the ContactsApp application.
*
* The main method displays user prompts that are printed to the terminal and acts as a user menu.
* User is able to use or shut down the application from the main method.
*
* The application asks for user input and based on the input user will be directed to:
* - ContactInfo.java for creating and managing contact information.
* - UserHelp.java for a user tutorial.
*
* @param userInput determines to which class and method user will be directed to.
* @param infoId is the user input from the main method that will be sent to the UserHelp class tutorial method to display specific tutorials.
*
* @author Kati Savolainen
*/

class ContactsApp {
    public static void main(String [] args) {
        Console c = System.console();

        boolean running = true;
        while (running){
            System.out.println("Insert a number to begin using your ContactsApp application");
            System.out.println("1. Add a new contact");
            System.out.println("2. View contact information");
            System.out.println("3. Edit contact details");
            System.out.println("4. Delete a contact");
            System.out.println("5. Contact formatting guide");
            System.out.println("6. Exit the application");

            int userInput = Integer.parseInt(c.readLine());

            switch(userInput) {
                case 1:
                    System.out.println("Create a new contact:");
                    ContactInfo.newContact(c);
                    break;
                case 2:
                    System.out.println("Displaying all contact information:");
                    ContactInfo.readContacts();
                    break;
                case 3:
                    System.out.println("Update contact information:");
                    ContactInfo.updateContacts(c);
                    break;
                case 4:
                    System.out.println("Delete a contact");
                    ContactInfo.deleteContacts(c);
                break;
                case 5:
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
                case 6:
                    running=false;
                    System.out.println("Thank you for using our application - see you soon!");
                    break;
            }
        }
    }
}

