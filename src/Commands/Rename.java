package Commands;

import DataBase.DataBase;
import DataBase.Table;

import java.util.Map;

public class Rename {

    public void rename(String oldName,String newName){
        boolean check = false;
        for (Map.Entry<String, Table> entry : DataBase.getDataBase().entrySet()) {
            if(newName.equals(entry.getKey())){
                check = false;
            }
            else{
                check = true;
            }
        }

        if(check){
            for (Map.Entry<String, Table> entry : DataBase.getDataBase().entrySet()) {
                if(oldName.equals(entry.getKey())){
                    DataBase.getDataBase().put(newName, DataBase.getDataBase().get(oldName));
                    DataBase.getDataBase().remove(oldName);
                }

            }
            DataBase.showDataBase(DataBase.getDataBase());
        }
        else{
            System.out.println("Existing name");
        }

    }
}
