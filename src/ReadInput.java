import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadInput {
  private Scanner scanner = new Scanner(System.in);
  private Integer choice;
  public String data;

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
        System.out.println("Input not recognized. Enter 1 to enter a statement or 2 to enter a file: ");
      }
    }
  }


  public String readFromUser() {
    if(choice == 1) {
      getAndReadFromConsole();
    }
    else {
      getAndReadFromFile();
    }

    return data;
  }



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


  public void getAndReadFromFile() {
    String s;

    while(true) {
      System.out.print("Enter the file name including the extension: ");

      String fileName = scanner.next();

      File file = new File("src/inputfiles/" + fileName);
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


  public void close() {
    scanner.close();
  }






}
