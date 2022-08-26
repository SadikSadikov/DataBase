package Commands;

import DataBase.DataBase;

public class ShowTables {

    public void showTables(){
        int i = 1;
        if(DataBase.getDataBaseFile().isEmpty()){
            System.out.println("There are no tables");
        }
        else{
            for(String s: DataBase.getDataBaseFile()){
                System.out.println("Table "+i+"-> "+s);
                i++;
            }
        }

    }

}
