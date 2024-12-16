/**
* The class UserHelp contains the tutorial method of the ContactsApp application.
*
* The tutorial method acts as a user guide. Tutorial displays correct contact information format examples which
* are printed to the terminal. User is able to view tutorials or exit back to the main method based on their input.
*
* @return tutorial method does not return any values.
*
* @param infoId is the user input from the main method or an integer from other methods to display specific tutorials.
*
*/
public class UserHelp {

/**
*
* The tutorial method of the ContactsApp application.
*
* The tutorial method displays correct contact information format examples to the user which are printed to the terminal.
* User is able to view tutorials from the main method or exit back to the main method based on their input.
* The tutorial method can also be called from other methods to display guides and correct information format examples for the user.
*
*/
    public static void tutorial(int infoId) {
        if (infoId == 1) {
            System.out.println("Enter the Finnish ID in this format DDMMYYCZZZQ. The ID consists of eleven characters: \n" +
            "DDMMYY = Date of birth (day, month, and year in a specific format).\n" +
            "C = Century marker indicating the century of birth: \n" +
            "'+' for the 1800s. | '-' or Y, X, W, V, U for the 1900s | A, B, C, D, E, F for the 2000s.\n" +
            "ZZZ = Individual number between 002 and 899 (even number for females, odd number for males).\n" +
            "Q = Control character is determined by dividing DDMMYYZZZ by 31 and using the remainder (0-30)\n"+
            "to pick up the corresponding character from the string: 0123456789ABCDEFHJKLMNPRSTUVWXY\n"+
            "Finnish ID example: 311299-9872");
        } else if (infoId == 2) {
             System.out.println("First and last name has to be at least one character long.\n" +
                                "First and last name example: Matti Meikäläinen");
        } else if (infoId == 3){
            System.out.println("Enter phone number in this format: country code + 7-13 digits\n"+
            "First digit of the phone number is replaced with the Finnish country code +358.\n"+
                               "Finnish mobile number example: 044023423 \n"+
                                "Correct formatting example: +3584023423");
        } else if (infoId == 4){
            System.out.println("Contact address details are not mandatory to enter.\n" +
            "If you wish to skip this step in the contact creation process - insert '1' to skip.\n" +
            "Please enter the street name, postal code and the city seperated by spaces.\n" +
            "Address example: Hämeenkatu 1 33100 Tampere");

        } else if (infoId == 5){
            System.out.println("Email address details are not mandatory to enter.\n" +
            "If you wish to skip this step in the contact creation process - insert '1' to skip.\n"+
            "Email address example: contacts@app.com");

        } else if (infoId == 6){
            return;
        }
    }
}