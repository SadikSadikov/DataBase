package Commands;

import DataBase.*;
import Exceptions.InvalidData;

import java.util.Map;

public class Describe {

    public void describe(String tableName) throws InvalidData {
        for (Map.Entry<String, Table> entry : DataBase.getDataBase().entrySet()) {
            if(tableName.equals(entry.getKey())){
                System.out.println("Table name: "+tableName);
                for(Types t:entry.getValue().getTypesList()){
                    System.out.println(t);
                }
                return;
            }

        }
        throw new InvalidData();

    }
}
