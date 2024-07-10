import java.util.*;

/**
 * Done By : Taher Asghar - 10121434 & Mohammad Hodroj-10121506
 * The following code takes a user email as an input and checks its validity according to the deterministic finite automata recognition criteria
 */

public class DFA {

    public static void printCurrentStateAndCharacter(String state, char c) {
        //prints out the current state and the current processed character in the string
        System.out.println("Current character = " + c + " | Current State = " + state);
    }

    private static boolean belongsToGamma(char c) {
        //checks if the current character belongs to Gamma that contains all the alphabets
        return Character.isAlphabetic(c);
    }

    private static boolean belongsToGammaExceptLetterC(char c) {
        //checks if the current character belongs to Gamma excluding letter c
        return Character.isAlphabetic(c) && c != 'c';
    }

    private static boolean belongsToGammaExceptLetterO(char c) {
        //checks if the current character belongs to Gamma excluding letter o
        return Character.isAlphabetic(c) && c != 'o';
    }

    private static boolean belongsToGammaExceptLetterM(char c) {
        //checks if the current character belongs to Gamma excluding letter m
        return Character.isAlphabetic(c) && c != 'm';
    }

    private static boolean belongsToDelta(char c) {
        //checks if the current character belongs to Delta
        return c == '.';
    }

    private static boolean belongsToTheta(char c) {
        //checks if the current character belongs to Theta
        return c == '@';
    }

    public boolean evaluate(String email){
        // The current state in the dfa
        String currentState = "q1"; // restarting from the start state after each email
        for (int i = 0; i < email.length(); i++) {  // the loop will loop among each character
            char c = email.charAt(i); // saving the current character to variable c

            switch (currentState) { // checking the current state in order to perform the next step
                case "q1":
                    if (belongsToGamma(c)) {
                        currentState = "q2";
                        System.out.println("Current character = " + c + " | Current State = " + currentState);
                    }
                    else {
                        currentState = "qtrap";
                    }
                    break;

                case "q2":
                    if (belongsToGamma(c)) {
                        printCurrentStateAndCharacter(currentState, c);
                    }
                    else if (belongsToDelta(c)) {
                        currentState = "q3";
                        printCurrentStateAndCharacter(currentState, c);
                    }
                    else if (belongsToTheta(c)) {
                        currentState = "q4";
                        printCurrentStateAndCharacter(currentState, c);
                    }
                    break;

                case "q3":
                    if (belongsToGamma(c)) {
                        currentState = "q2";
                        printCurrentStateAndCharacter(currentState, c);
                    }
                    else {
                        currentState = "qtrap";
                    }
                    break;

                case "q4":

                case "q6":
                    if (belongsToGammaExceptLetterC(c)) {
                        currentState = "q5";
                        printCurrentStateAndCharacter(currentState, c);
                    }
                    else if (c == 'c') {
                        currentState = "q7";
                        printCurrentStateAndCharacter(currentState, c);
                    }
                    else {
                        currentState = "qtrap";
                    }
                    break;

                case "q5":
                    if (belongsToGamma(c)) {
                        printCurrentStateAndCharacter(currentState, c);
                    }
                    else if (belongsToDelta(c)) {
                        currentState = "q6";
                        printCurrentStateAndCharacter(currentState, c);
                    }
                    else {
                        currentState = "qtrap";
                    }
                    break;

                case "q7":
                    if (belongsToGammaExceptLetterO(c)) {
                        currentState = "q5";
                        printCurrentStateAndCharacter(currentState, c);
                    }
                    else if (c == 'o') {
                        currentState = "q8";
                        printCurrentStateAndCharacter(currentState, c);
                    }
                    else if (belongsToDelta(c)) {
                        currentState = "q4";
                        printCurrentStateAndCharacter(currentState, c);
                    }
                    else {
                        currentState = "qtrap";
                    }
                    break;

                case "q8":
                    if (belongsToGammaExceptLetterM(c)) {
                        currentState = "q5";
                        printCurrentStateAndCharacter(currentState, c);
                    }
                    else if (belongsToDelta(c)) {
                        currentState = "q4";
                        printCurrentStateAndCharacter(currentState, c);
                    }
                    else if (c == 'm') {
                        currentState = "q9";
                        printCurrentStateAndCharacter(currentState, c);
                    }
                    else {
                        currentState = "qtrap";
                    }
                    break;

                case "q9":
                    if (belongsToGamma(c)) {
                        currentState = "q5";
                        printCurrentStateAndCharacter(currentState, c);
                    }
                    else if (belongsToDelta(c)) {
                        currentState = "q4";
                        printCurrentStateAndCharacter(currentState, c);
                    }
                    else {
                        currentState = "qtrap";
                    }
                    break;

                case "qtrap":
                    printCurrentStateAndCharacter(currentState, c);
            }
        }
        return currentState.equals("q9");
    }

    public static void main(String[] args) {
        DFA o = new DFA();
        Scanner s = new Scanner(System.in);
        char c; // the user's choice to try again
        int i = 1;
        System.out.println("Enter the input (start with 'y')");
        do {
            c = s.next().charAt(0);
            if (c != 'n'){
                String email = s.next();
                System.out.println(i + ". "+ email);
                 // evaluating the input
                if (o.evaluate(email))
                    System.out.println("Result: Accepted\n");
                else
                    System.out.println("Result: Rejected\n");
                i++;
            }
        } while (c != 'n');
        System.out.println("Program is terminated!");
    }
}