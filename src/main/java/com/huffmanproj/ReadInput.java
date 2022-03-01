package com.huffmanproj;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * Reads input from the user
 * @author Danielle Hatten
 */
public class ReadInput {
  private Scanner scanner = new Scanner(System.in);
  private Integer choice;
  private String data;


  /**
   * read user choice of whether to read text that is to be decoded
   * from a file or straight from the command line
   */
  public ReadInput() {
    while(true) {
      System.out.print("Enter 1 to enter a statement or 2 to enter a file: ");

      String stringCh = scanner.next();

      try {
        choice = Integer.parseInt(stringCh);
      } catch (NumberFormatException e) {
        choice = 0;
      }

      if (choice == 1 || choice == 2) {
        break;
      } else {
        System.out.print("Input not recognized. ");
      }
    }
  }

  /**
   * direct scanner to read from console or a file
   * @return data string that is to be decoded
   */
  public String readFromUser() {
    if(choice == 1) {
      getAndReadFromConsole();
    }
    else {
      getAndReadFromFile();
    }

    return data;
  }


  /**
   * read and store string to be decoded from the console
   */
  public void getAndReadFromConsole() {
    System.out.println("Enter string ending with '::' on a new line: ");
    String s = "";

    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();

      if (line.equals("::")) {
        break;
      }
      s = s + "\n" + line;

    }

    data = s;
    //return(s);
  }


  /**
   * read and store string that is to be decoded from a file
   */
  public void getAndReadFromFile() {

    String s;

    while (true) {
      System.out.print("Enter the file name including the extension: ");

      String fileName = scanner.next();

      File file = searchForFile(new File("./"), fileName);

      if (file == null) {
        System.out.print("File not found. ");
        continue;
      }

      s = "";

      try {
        scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
          String line = scanner.nextLine();
          s = s + "\n" + line;
        }
      } catch (FileNotFoundException e) {
        System.out.print("File not found. ");
        continue;
      }

      data = s;
      break;
    }

  }


  /**
   * recursively search the directory for the entered file name and return
   * the file if found
   * @param file current file/directory that is being searched/checked
   * @param fileName the file name being searched for
   * @return null is file is not found, file<File> if found
   */
  public File searchForFile(File file, String fileName) {
    if (file.isDirectory()) {
      File[] directory = file.listFiles();
      for (File f : directory) {
        File found = searchForFile(f, fileName);
        if (found != null)
          return found;
      }
    }
    else {
      if (file.getName().equals(fileName)) {
        return file;
      }
    }
    return null;
  }


  /**
   * close the scanner
   */
  public void close() {
    scanner.close();
  }






}
