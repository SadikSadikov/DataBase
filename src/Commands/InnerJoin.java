package Commands;

import DataBase.*;
import DataBase.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InnerJoin {

    public void innerJoin(String tableName1,String columnName1,String tableName2,String columnName2,String matchingColumn){
        List<Object> table1List = new ArrayList<>();
        List<Object> table2List = new ArrayList<>();
        List<Object> matching = new ArrayList<>();
        List<Object> newTable = new ArrayList<>();

        for (Map.Entry<String, Table> entry : DataBase.getDataBase().entrySet()) {
            if (tableName1.equals(entry.getKey())) {
                for (int i = 0; i < entry.getValue().getRows().size(); i++) {
                    if (entry.getValue().getRows().get(i).equals(matchingColumn)) {
                        table1List.add(entry.getValue().getRows().get(i - 1));
                    }
                }

            }
        }
        for (Map.Entry<String, Table> entry : DataBase.getDataBase().entrySet()) {
            if (tableName2.equals(entry.getKey())) {
                for(int i = 0; i < entry.getValue().getRows().size();i++){
                    if(entry.getValue().getRows().get(i).equals(matchingColumn)){
                        table2List.add(entry.getValue().getRows().get(i-1));
                    }
                }

            }

        }
        for(Object o:table1List){
            if(table2List.contains(o)){
                matching.add(o);
            }
        }

        for (Map.Entry<String, Table> entry : DataBase.getDataBase().entrySet()) {
            if (tableName1.equals(entry.getKey())) {
                for (int i = 0; i < entry.getValue().getRows().size(); i++) {
                    if (entry.getValue().getRows().get(i).equals(columnName1)) {
                        if (matching.contains(entry.getValue().getRows().get(i-1))) {
                            newTable.add(entry.getValue().getRows().get(i-1));
                            newTable.add(entry.getValue().getRows().get(i));
                            newTable.add(entry.getValue().getRows().get(i + 1));
                            newTable.add(entry.getValue().getRows().get(i + 2));
                        }
                    }
                }

            }
        }
        for (Map.Entry<String, Table> entry : DataBase.getDataBase().entrySet()) {
            if (tableName2.equals(entry.getKey())) {
                for(int i = 0; i < entry.getValue().getRows().size();i++){
                    if(entry.getValue().getRows().get(i).equals(columnName2)){
                        if(matching.contains(entry.getValue().getRows().get(i-1))){
                            newTable.add(entry.getValue().getRows().get(i-1));
                            newTable.add(entry.getValue().getRows().get(i));
                            newTable.add(entry.getValue().getRows().get(i+1));
                            newTable.add(entry.getValue().getRows().get(i+2));
                        }
                    }
                }

            }

        }
        Table table = new Table();
        table.printTable(newTable);
    }
}