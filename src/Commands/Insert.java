package Commands;

import DataBase.*;
import DataBase.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Insert {

    public void insert(String tableName,String columnName,String value) {
        List<String> columnNames = new ArrayList<>();
        int counter = 0;
        int j = 0;
        Object sum = 0;
        for (Map.Entry<String, Table> entry : DataBase.getDataBase().entrySet()) {
            if (tableName.equals(entry.getKey())) {
                for(int i = 0;i < entry.getValue().getRows().size();i+=4){
                    if(entry.getValue().getRows().get(i).equals(1)){
                        columnNames.add((String)entry.getValue().getRows().get(i+1));
                       counter++;
                    }
                }
                for(int i = 0;i < entry.getValue().getRows().size();i++){
                    if(j == i){
                        sum = entry.getValue().getRows().get(i);
                        j+=4;
                    }
                }
                int maxValue =(int)sum;

                for(int i = 0; i < counter;i++){
                    if(columnNames.get(i).equals(columnName)){
                        entry.getValue().getRows().add(maxValue+1);
                        entry.getValue().getRows().add(columnName);
                        entry.getValue().getRows().add(Types.NULL);
                        entry.getValue().getRows().add(value);
                    }
                    else{
                        entry.getValue().getRows().add(maxValue+1);
                        entry.getValue().getRows().add(columnNames.get(i));
                        entry.getValue().getRows().add(Types.NULL);
                        entry.getValue().getRows().add("NULL");
                    }

                }
                entry.getValue().printTable(entry.getValue().getRows());
            }

        }
    }

}
