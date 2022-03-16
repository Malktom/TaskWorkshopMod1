package pl.coderslab;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class TaskManager {
    public static void main(String[] args) {

        viewControlPanel();
        Scanner scan = new Scanner(System.in);

        Path path1 = Paths.get("tasks.csv");

        while (scan.hasNextLine()) {

            String option = scan.nextLine();

           if ("exit".equals(option)) {      // warunek skonczenia programu - sprawdzamy input poprzez przypisanie go do referencji
               System.out.println(ConsoleColors.RED + "Bye, bye.");
               break;
            }
            switch (option) {
                case "add":
                    System.out.println("add");
                    break;
                case "remove":
                    System.out.println("remove");
                    break;
                case "list":
                    System.out.println("Tasks list:");
                    try {
                        for (String line : Files.readAllLines(path1)) {
                            System.out.println(line);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

            }
           viewControlPanel();


        }
    }

    private static void viewControlPanel() {
        System.out.println();
        System.out.println(ConsoleColors.BLUE + "Please select an option");
        System.out.println(ConsoleColors.RESET + "add");
        System.out.println("remove");
        System.out.println("list");
        System.out.println("exit");
        System.out.println();
    }
}
