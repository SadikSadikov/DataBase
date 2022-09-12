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

    public void export(String fileName,String tableName,FileCommands fileCommands) {
        List<Types> typesList = new ArrayList<>();
                try{
                    for (Map.Entry<String, Table> entry : DataBase.getDataBase().entrySet()) {
                        if(tableName.equals(entry.getKey())){
                            for(Types t:entry.getValue().getTypesList()){
                                    typesList.add(t);
                            }
                            ListTable lt = new ListTable();
                            lt.setTypesList(typesList);
                            fileCommands.fileCommands("C:\\Files\\"+fileName);
                            if(fileCommands.isCloseOrSave()){
                                JAXBContext jc = JAXBContext.newInstance(ListTable.class);
                                Marshaller ms = jc.createMarshaller();
                                ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                                if(!(fileCommands.getPathName().equals(""))){
                                    ms.marshal(lt,new File(fileCommands.getPathName()));
                                    DataBase.getFiles().add(fileCommands.getPathName());
                                    fileCommands.setPathName("");
                                    System.out.println("Successfully exported");
                                }

                            }
                        }

                    }


                }
                catch (Exception e){
                    System.out.println(e);
                }
        }
    }
