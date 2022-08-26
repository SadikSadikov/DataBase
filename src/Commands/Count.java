package Commands;

import DataBase.*;
import DataBase.Table;

import java.util.Map;

public class Count {

    public void count(String tableName,String columnName,String searchValue) {
        int count = 0;
        for (Map.Entry<String, Table> entry : DataBase.getDataBase().entrySet()) {
            if (tableName.equals(entry.getKey())) {
                for (int i = 0; i < entry.getValue().getRows().size(); i++) {
                    if(entry.getValue().getRows().get(i).equals(columnName)){
                        if (entry.getValue().getRows().get(i+2).equals(entry.getValue().determinationValue((Types) entry.getValue().getRows().get(i+1),searchValue))) {
                           count++;
                        }

                    }

                }
            }
        }
        if (count == 0) {
            System.out.println("This value not existing: ");
        } else {
            System.out.print("\n" +
                    "The number of the value: " +
                    searchValue + " = " + count);
            System.out.println();
        }
    }
}
