package Commands;

import DataBase.*;
import DataBase.Table;

import java.util.Map;

public class Delete {
    public void delete(String tableName,String searchColumnName,String value,int columnId){
        for (Map.Entry<String, Table> entry : DataBase.getDataBase().entrySet()) {
            if (tableName.equals(entry.getKey())) {
                for (int i = 0; i < entry.getValue().getRows().size(); i++) {
                    if(entry.getValue().getRows().get(i).equals(columnId)){
                        if (entry.getValue().getRows().get(i+1).equals(searchColumnName)) {
                            if(entry.getValue().getRows().get(i+3).equals(entry.getValue().determinationValue((Types) entry.getValue().getRows().get(i+2),value))){
                                entry.getValue().getRows().remove(i);
                                entry.getValue().getRows().remove(i);
                                entry.getValue().getRows().remove(i);
                                entry.getValue().getRows().remove(i);

                            }

                        }

                    }

                }
                entry.getValue().printTable(entry.getValue().getRows());
            }
        }
    }

}
