package Commands;

import DataBase.DataBase;
import DataBase.Table;
import java.util.Map;
import java.util.Scanner;

public class Print {

    public void print(String tableName) {
        String choice;
        int number = 2;

        Scanner scanner = new Scanner(System.in);
        for (Map.Entry<String, Table> entry : DataBase.getDataBase().entrySet()) {
            if (tableName.equals(entry.getKey())) {
                int maxValue = (int)entry.getValue().getRows().get(entry.getValue().getRows().size()-4);
                entry.getValue().printTables(entry.getValue().getRows(),number);
                System.out.println();
                    do {
                        System.out.println("****MENU****");
                        System.out.println("1 - Next Page");
                        System.out.println("2 - Prev Page");
                        System.out.println("3 - Exit");
                        System.out.println("*************");
                        System.out.print("Enter your choice: ");
                        choice = scanner.nextLine();
                        System.out.println();

                        switch (choice) {
                            case "1":
                                if(number <= maxValue) {
                                    number += 2;
                                    entry.getValue().printTables(entry.getValue().getRows(), number);
                                    System.out.println();
                                }
                                break;
                            case "2":
                                if(number > 0){
                                    number-= 2;
                                    entry.getValue().printTables(entry.getValue().getRows(),number);
                                    System.out.println();
                                }
                                break;

                        }

                    } while (!(choice.equals("3")));

            }

        }
    }
}

