package Commands;

import DataBase.DataBase;
import Exceptions.InvalidFileName;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import DataBase.ListTable;

@XmlRootElement(name = "Table")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Import {

    public void importFile(String fileName) throws InvalidFileName {
            if(DataBase.getDataBaseFile().isEmpty()){
                DataBase.getDataBaseFile().add(fileName);
                System.out.println("The file has been imported");
                insertCatalog();
            }
            else{
                if(DataBase.getDataBaseFile().contains(fileName)){
                    throw new InvalidFileName();
                }
                else{
                    DataBase.getDataBaseFile().add(fileName);
                    System.out.println("The file has been imported");
                    insertCatalog();
                }
            }

    }

    public void insertCatalog() {
        try {
            List<String> list = new ArrayList<>();
            for(String s: DataBase.getDataBaseFile()){
                list.add(s);
            }

            ListTable lt = new ListTable();
            lt.setFileList(list);
            JAXBContext jc = JAXBContext.newInstance(ListTable.class);
            Marshaller ms = jc.createMarshaller();
            ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            ms.marshal(lt,new File("C:\\Users\\USER\\IdeaProjects\\DataBase\\src\\Catalog\\Catalog.xml"));

            JAXBContext context = JAXBContext.newInstance(ListTable.class);
            Unmarshaller ums = context.createUnmarshaller();
            ListTable tables = (ListTable) ums.unmarshal(new File("C:\\Users\\USER\\IdeaProjects\\DataBase\\src\\Catalog\\Catalog.xml"));
            for(String s:tables.getFileList()){
                System.out.println("file name: "+s);
            }


        }catch (Exception e){
            System.out.println(e);
        }

    }

}
