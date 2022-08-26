package Commands.AggregateCommands;

import DataBase.DataBase;
import DataBase.Table;

import java.util.Map;

public class Min implements IAggregateFunctions{

    @Override
    public void functions(String tableName, String columnName) throws ClassCastException{
        int minNum = 0;
        boolean status = false;
        for (Map.Entry<String, Table> entry : DataBase.getDataBase().entrySet()) {
            if (tableName.equals(entry.getKey())) {
                for(int i = 0;i < entry.getValue().getRows().size();i++){
                    if(entry.getValue().getRows().get(i).equals(columnName)){
                        if(!(status)){
                            minNum = (int) entry.getValue().getRows().get(i+2);
                            status = true;
                        }
                        if(minNum>(int) entry.getValue().getRows().get(i+2)){
                            minNum = (int) entry.getValue().getRows().get(i+2);
                        }
                    }
                }
            }
        }
        System.out.println(minNum);
    }
}
