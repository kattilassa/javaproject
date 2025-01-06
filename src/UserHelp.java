/**
* The class UserHelp contains the tutorial method of the ContactsApp application.
*
* The tutorial method prints correct contact information format examples.
*
* @return tutorial method does not return any values.
*
* @param infoId is the user input from the main method or an integer
* from other methods to display specific tutorials.
*
*/
public class UserHelp {

    public static final int FINNISH_ID = 1;
    public static final int NAME = 2;
    public static final int PHONE_NUMBER = 3;
    public static final int ADDRESS = 4;
    public static final int EMAIL = 5;
    public static final int EXIT_TUTORIAL = 6;

/**
*
* The tutorial method prints correct contact information format examples.
*
* The tutorial method is called from other classes to give guidance for the user in the contact creation process.
*
*/
    public static void tutorial(int infoId) {

            switch (infoId) {
                case FINNISH_ID:
                    System.out.println("Enter the Finnish ID in this format DDMMYYCZZZQ. The ID consists of eleven characters: \n"
                    + "DDMMYY = Date of birth (day, month, and year in a specific format).\n"
                    + "C = Century marker indicating the century of birth: \n"
                    + "'+' for the 1800s. | '-' or Y, X, W, V, U for the 1900s | A, B, C, D, E, F for the 2000s.\n"
                    + "ZZZ = Individual number between 002 and 899 (even number for females, odd number for males).\n"
                    + "Q = Control character is determined by dividing DDMMYYZZZ by 31 and using the remainder (0-30)\n"
                    + "to pick up the corresponding character from the string: 0123456789ABCDEFHJKLMNPRSTUVWXY\n"
                    + "Finnish ID example: 311299-9872");
                    break;
                case NAME:
                    System.out.println("First and last name has to be at least one character long.\n"
                    + "First and last name example: Matti Meikäläinen");
                    break;
                case PHONE_NUMBER:
                    System.out.println("Enter phone number in this format: country code + 7-13 digits\n"
                    + "First digit of the phone number is replaced with the Finnish country code +358.\n"
                    + "Finnish mobile number example: 044023423 \n"
                    + "Correct formatting example: +3584023423");
                    break;
                case ADDRESS:
                    System.out.println("Contact address details are not mandatory to enter.\n"
                    + "If you wish to skip this step in the contact creation process - insert '1' to skip.\n"
                    + "Please enter the street name, postal code and the city seperated by spaces.\n"
                    + "Address example: Hämeenkatu 1 33100 Tampere");
                    break;
                case EMAIL:
                    System.out.println("Email address details are not mandatory to enter.\n"
                    + "If you wish to skip this step in the contact creation process - insert '1' to skip.\n"
                    + "Email address example: contacts@app.com");
                    break;
                case EXIT_TUTORIAL:
                    return;
        }
    }
}
