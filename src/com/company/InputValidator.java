package com.company;

import java.util.List;

import static java.lang.Character.toLowerCase;

/**
 * This class validates user input.
 * @author Gabriel Beutler
 * @version 1.0
 */
public class InputValidator {
    private int valid_length;
    private List<Character> valid_commands;
    private boolean convertInputToLowerCase = true;

    /**
     * Setting rules for the validation.
     * @param valid_length Required length.
     * @param valid_commands Allowed commands("prefixes").
     */
    public InputValidator(int valid_length, List<Character> valid_commands) {
        this.valid_length = valid_length;
        this.valid_commands = valid_commands;
    }

    /**
     * Checks if the input is valid.
     * @param values User input as char array.
     * @return is valid.
     */
    public boolean isValid(char[] values){
        if(this.convertInputToLowerCase){
            values = this.convertToLowerCase(values);
        }
        boolean valid = true;
        if(values.length != this.valid_length) valid = false;
        if(!this.valid_commands.contains(values[0])) valid = false;
        return valid;
    }

    /**
     * Converts every char in a array to a lower case character when possible.
     * @param values User input as char array.
     * @return user input in lower case.
     */
    private char[] convertToLowerCase(char[] values){
        for(int i = 0; i < values.length; i++){
            values[i] = toLowerCase(values[i]);
        }
        return values;
    }
}
