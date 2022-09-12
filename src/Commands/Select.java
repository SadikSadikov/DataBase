package Commands;

import DataBase.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Select {

    public void select(String columnName,String tableName){
        String name = "";
        List<Object> list = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        for (Map.Entry<String, Table> entry : DataBase.getDataBase().entrySet()) {
            if (tableName.equals(entry.getKey())) {
                for(int i = 0 ; i < entry.getValue().getRows().size() ; i++){
                    if(columnName.equals(entry.getValue().getRows().get(i))){
                        name = (String) entry.getValue().getRows().get(i);
                        list.add(entry.getValue().getRows().get(i+3));
                    }
                }

            }
        }

        if(!(list.isEmpty())){
            String choice;
            int number = 0;
            System.out.println(name);
            printTables(list,number);
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
                        if(number < list.size()){
                            System.out.println(name);
                            number+= 2;
                            printTables(list,number);
                        }


                        break;
                    case "2":
                        if(number >= 0){
                            System.out.println(name);
                            number-= 2;
                            printTables(list,number);
                        }
                        break;

                }

            } while (!(choice.equals("3")));
        }

    }
    private void printTables(List<Object> list,int number) {
        int i = number + 2;
        int k = i - 2;

        if(number >= 0 && number < list.size()){

            for (int j = number; j < list.size(); j++) {
                if(j < i) {
                    if(k == i - 1){
                        System.out.println();
                    }
                    System.out.print(list.get(j) + "   ");

                    k++;
                }

            }
            System.out.println();
        }

    }
}
