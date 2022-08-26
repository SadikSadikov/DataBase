package Commands;

import DataBase.*;
import java.util.Map;

public class AddColumn {

    public void addColumn(String tableName, String columnName, Types types){
        int j = 0;
        Object sum = 0;
        for (Map.Entry<String, Table> entry : DataBase.getDataBase().entrySet()) {
            if (tableName.equals(entry.getKey())) {
                for(int i = 0;i < entry.getValue().getRows().size();i++){
                    if(j == i){
                      sum = entry.getValue().getRows().get(i);
                        j+=4;
                    }
                }
                int maxValue =(int)sum;
                entry.getValue().getRows().add(maxValue+1);
                entry.getValue().getRows().add(columnName);
                entry.getValue().getRows().add(types);
                entry.getValue().getTypesList().add(types);
                entry.getValue().getRows().add("NULL");
                entry.getValue().printTable(entry.getValue().getRows());
            }
        }
    }
}
