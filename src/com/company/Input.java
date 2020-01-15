package com.company;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * This class requests input from the user.
 * @author Gabriel Beutler
 * @version 1.0
 */
public class Input {
    private Scanner input = new Scanner(System.in);
    private int valid_length;
    private List<Character> valid_commands;
    private InputValidator validator;

    /**
     * Creating a validator when class is initialised.
     */
    public Input() {
        Character[] array = new Character[]{'t', 'm'};
        valid_commands = Arrays.asList(array);
        this.valid_length = 3;
        this.validator = new InputValidator(this.valid_length, valid_commands);
    }

    /**
     * Requests input from the user until it's receiving valid input.
     * @return Valid user input
     */
    public char[] getUserInput(){
        char[] values;
        System.out.println();
        System.out.println(" > Testen Txy (z.B. T23)");
        System.out.println(" > Markieren Mxy (z.B. M37)");
        do{
            System.out.print(" > ");
            values = input.nextLine().toCharArray();
        }while(!this.validator.isValid(values));
        return values;
    }
}
