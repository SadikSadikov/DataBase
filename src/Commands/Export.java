package Commands;

import DataBase.DataBase;
import DataBase.ListTable;
import DataBase.Table;
import DataBase.Types;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Export {

    public void export(String fileName,String tableName) {
        List<Types> typesList = new ArrayList<>();

                try{
                    for (Map.Entry<String, Table> entry : DataBase.getDataBase().entrySet()) {
                        if(tableName.equals(entry.getKey())){
                            for(Types t:entry.getValue().getTypesList()){
                                    typesList.add(t);
                            }
                        }


                        ListTable lt = new ListTable();
                        lt.setTypesList(typesList);
                        JAXBContext jc = JAXBContext.newInstance(ListTable.class);
                        Marshaller ms = jc.createMarshaller();
                        ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                        ms.marshal(lt, new File("src\\Data\\" + fileName));

                    }
                    System.out.println("Successfully exported");
                    DataBase.getFiles().add(fileName);
                }
                catch (Exception e){
                    System.out.println(e);
                }
        }
    }
