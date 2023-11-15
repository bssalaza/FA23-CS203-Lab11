
import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Encrypter {

    private int shift;
    private String encrypted;

    /**
     * Default Constructor
     */
    public Encrypter() {
        this.shift = 1;
        this.encrypted = "";
    }

    /**
     * Non-default Constructor
     * @param s - custom shift amount
     */
    public Encrypter(int s) {
        this.shift = s;
        this.encrypted = "";
    }

    /**
     * Encrypts the content of a file and writes the result to another file.
     *
     * @param inputFilePath      the path to the file containing the text to be encrypted
     * @param encryptedFilePath the path to the file where the encrypted text will be written
     * @throws Exception if an error occurs while reading or writing the files
     */
    public void encrypt(String inputFilePath, String encryptedFilePath) throws Exception {
        //TODO: Call the read method, encrypt the file contents, and then write to new file
        String encryptFile = readFile(inputFilePath);
        String encryptedText = performEncryption(encryptFile, shift);
        writeFile(encryptedText, encryptedFilePath);
    }

    /**
     * Decrypts the content of an encrypted file and writes the result to another file.
     *
     * @param messageFilePath    the path to the file containing the encrypted text
     * @param decryptedFilePath the path to the file where the decrypted text will be written
     * @throws Exception if an error occurs while reading or writing the files
     */
    public void decrypt(String messageFilePath, String decryptedFilePath) throws Exception {
        //TODO: Call the read method, decrypt the file contents, and then write to new file
        String decyrptText = readFile(messageFilePath);
        String decryptedText = performDecryption(decyrptText, shift);
        writeFile(decryptedText, decryptedFilePath);
    }

    /**
     * Reads the content of a file and returns it as a string.
     *
     * @param filePath the path to the file to be read
     * @return the content of the file as a string
     * @throws Exception if an error occurs while reading the file
     */
    private static String readFile(String filePath) throws Exception {
        String message = "";

        //TODO: Read file from filePath
        try (Scanner fileScanner = new Scanner(new File(filePath))) {
            while (fileScanner.hasNextLine()) {
                message += fileScanner.nextLine() + "\n";
            }
            fileScanner.close();
        }
        return message;
    }

    /**
     * Writes data to a file.
     *
     * @param data     the data to be written to the file
     * @param filePath the path to the file where the data will be written
     */
    private static void writeFile(String data, String filePath) {
        //TODO: Write to filePath
        try (FileWriter output = new FileWriter(filePath)) {
            output.write(data);
            System.out.println(data);
            output.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
    }

    /**
     * Returns a string representation of the encrypted text.
     *
     * @return the encrypted text
     */
    private static String performEncryption(String text, int shift) {
        String encryptedText = "";
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                encryptedText += (char) (((c - base + shift) % 26 + 26) % 26 + base);
            } else {
                encryptedText += c;
            }
        }
        return encryptedText;
    }


    private static String performDecryption(String text, int shift) {
        // Decryption is the same as encryption with a negative shift
        return performEncryption(text, -shift);
    }

    public String toString() {
        return encrypted;
    }

}
