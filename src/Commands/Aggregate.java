package Commands;
import Commands.AggregateCommands.*;

public class Aggregate {

    public void aggregate(String tableName,String columnName,String operation){
        switch (operation){
            case "1":
                Sum sum = new Sum();
                try {
                    sum.functions(tableName, columnName);
                }
                catch (ClassCastException e){
                    System.out.println(e);
                }
                break;
            case "2":
                AVG avg = new AVG();
                try {
                    avg.functions(tableName,columnName);
                }
                catch (ClassCastException e){
                    System.out.println(e);
                }
                break;
            case "3":
                Max max = new Max();
                try {
                    max.functions(tableName,columnName);
                }
                catch (ClassCastException e){
                    System.out.println(e);
                }
                break;
            case "4":
                Min min = new Min();
                try {
                    min.functions(tableName,columnName);
                }
                catch (ClassCastException e){
                    System.out.println(e);
                }
                break;
            default:
                System.out.println("Try again");

        }
    }
}
