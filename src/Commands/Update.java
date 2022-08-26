package Commands;

import DataBase.*;
import DataBase.Table;
import java.util.Map;

public class Update {
    public void update(String tableName,String searchColumnName, String targetValue,int searchIdColumn) {
            for (Map.Entry<String, Table> entry : DataBase.getDataBase().entrySet()) {
                if (tableName.equals(entry.getKey())) {
                    for (int i = 0; i < entry.getValue().getRows().size(); i++) {
                        if(entry.getValue().getRows().get(i).equals(searchIdColumn)){
                            if (entry.getValue().getRows().get(i+1).equals(searchColumnName)) {
                               entry.getValue().getRows().set(i+3,entry.getValue().determinationValue((Types) entry.getValue().getRows().get(i+2),targetValue));
                            }

                        }

                    }
                    entry.getValue().printAllTables(entry.getValue().getRows());
                }
           }
    }
}
