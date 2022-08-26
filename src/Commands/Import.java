package Commands;

import DataBase.DataBase;
import Exceptions.InvalidFileName;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import DataBase.ListTable;

@XmlRootElement(name = "Catalog")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Import {

    public void importFile(String fileName) throws InvalidFileName {
            if(DataBase.getDataBaseFile().isEmpty()){
                DataBase.getDataBaseFile().add(fileName);
                System.out.println("The file has been imported");
                insertCatalog();
            }
            else{
                /*
                int i = 0;
                for(String s: DataBase.getDataBaseFile()){
                    if (fileName.equals(s)){
                        i++;
                    }
                }
                if(i == 1){
                    throw new InvalidFileName();
                }

                 */
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
            ms.marshal(lt, new File("src\\Catalog\\Catalog.xml"));
        }catch (Exception e){
            System.out.println(e);
        }

    }

}
