package pl.coderslab;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
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
                    addTask();
                    break;
                case "remove":
                    System.out.println("remove");
                   // System.out.println(getLinesNumber());
                    break;
                case "list":
                    getList();
                    break;
//                    System.out.println("Tasks list:");
//                    try {
//                        for (String line : Files.readAllLines(path1)) {
//                            System.out.println(line);
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    break;
                default:
                    System.out.println("Please select a corect option.");
            }
            viewControlPanel();

        }
    }

    private static void addTask() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Podaj dane:");
        String option = scan.nextLine();
        System.out.println(option);
        Path path1 = Paths.get("tasks.csv");
        try {
            Files.writeString(path1, option, StandardOpenOption.APPEND);   //   JAK DODAWAC WIERSZE OD NOWEJ LINII???
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getLinesNumber() {
        int lines = 0;
        try {
            File file = new File("tasks.csv");
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                scan.nextLine();
                lines++;
            }

        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }  return lines;
    }
        private static void getList () {


            try {
                File file = new File("tasks.csv");
                Scanner scan = new Scanner(file);
                String[] usersArray = new String[getLinesNumber()];   // tworzymy tablice taskow o dlugosci linii tekstu z pliku

                while (scan.hasNextLine()) {

                    for (int i = 0; i < getLinesNumber(); i++) {
                        String task = scan.nextLine();
                        usersArray[i] = task;                      //ZAPELNIAM TABLICE JEDNOWYMIAROWA LINIAMI Z PLIKU
                    }

                }
                System.out.println(Arrays.toString(usersArray));
                String[][] taskArray=new String [getLinesNumber()][4];
                for (int i = 0; i < getLinesNumber(); i++) {
                        taskArray[i]=usersArray[i].split(",");   //UZUPELNIAM TABLICE 2WYMIAROWA ELEMENTAMII Z KAZDEJ LINII ale bez licznika pozycji
                }
                System.out.println(Arrays.toString(taskArray[1]));
                System.out.println(taskArray[1][1]);
                String[][] numberedTaskArray=new String [getLinesNumber()][4];
                int licznik=1;
                for (int i = 0; i < getLinesNumber(); i++) {
                    for (int j = 0; j < 3; j++) {

                        numberedTaskArray[i][j+1] = taskArray[i][j];
                        if (i==j+1) {                                                         // DODAWANIE NUMEROW WIERSZY
                            numberedTaskArray[i][j] = (licznik)+":";
                        }
                    }
                }
                System.out.println("lista taskow ponumerowana");
                System.out.println(Arrays.toString(numberedTaskArray[1]));

            } catch (FileNotFoundException exception) {
                exception.printStackTrace();
            }
        }

        private static void viewControlPanel () {
            System.out.println();
            System.out.println(ConsoleColors.BLUE + "Please select an option");
            System.out.println(ConsoleColors.RESET + "add");
            System.out.println("remove");
            System.out.println("list");
            System.out.println("exit");
            System.out.println();
        }
    }
