package Commands.AggregateCommands;

import DataBase.DataBase;
import DataBase.Table;

import java.util.Map;

public class AVG implements IAggregateFunctions{
    @Override
    public void functions(String tableName, String columnName) throws ClassCastException{
        int counter = 0;
        double sum = 0;
        double avg = 0.0;
        for (Map.Entry<String, Table> entry : DataBase.getDataBase().entrySet()) {
            if (tableName.equals(entry.getKey())) {
                for(int i = 0;i < entry.getValue().getRows().size();i++){
                    if(entry.getValue().getRows().get(i).equals(columnName)){
                        sum+=(double)entry.getValue().getRows().get(i+2);
                        counter++;
                    }
                }
            }
        }
        avg = sum / counter;
        System.out.println(avg);
    }
}
