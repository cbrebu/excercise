package com.brebu;

import com.brebu.encoder.EncoderService;
import lombok.extern.log4j.Log4j2;

import java.util.Scanner;

/**
 * Main class to run the EncoderService from the console.
 */
@Log4j2
public class Main {
    public static void main(String[] args) {
        String plainText = readStringFromConsole();
        EncoderService encoderService = new EncoderService();
        encoderService.encode(plainText);
    }

    /**
     * Read a string from the console.
     * @return the string entered by the user
     */
    public static String readStringFromConsole() {
        log.info("Enter a string to encode and press enter:");
        return new Scanner(System.in).nextLine();
    }
}