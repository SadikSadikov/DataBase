package Commands.AggregateCommands;

import DataBase.DataBase;
import DataBase.Table;

import java.util.Map;

public class Max implements IAggregateFunctions{

    @Override
    public void functions(String tableName, String columnName) throws ClassCastException{
        int maxNum = 0;
        for (Map.Entry<String, Table> entry : DataBase.getDataBase().entrySet()) {
            if (tableName.equals(entry.getKey())) {
                for(int i = 0;i < entry.getValue().getRows().size();i++){
                    if(entry.getValue().getRows().get(i).equals(columnName)){
                        if(maxNum<(int) entry.getValue().getRows().get(i+2)){
                            maxNum = (int) entry.getValue().getRows().get(i+2);
                        }
                    }
                }
            }
        }
        System.out.println(maxNum);
    }
}
