package DataBase;

import Commands.Import;

import java.util.*;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Table")
public class ListTable {
    private List<Table> tableList = new ArrayList<Table>();
    private List<Types> typesList = new ArrayList<Types>();
    private List<String> fileList = new ArrayList<>();


    @XmlElement(name = "Column")
    public List<Types> getTypesList() {
        return typesList;
    }

    public void setTypesList(List<Types> typesList) {
        this.typesList = typesList;
    }

    @XmlElement(name = "Column")
    public List<Table> getTableList() {
        return tableList;
    }

    @XmlElement(name = "FileName")
    public List<String> getFileList() {
        return fileList;
    }

    public void setFileList(List<String> fileList) {
        this.fileList = fileList;
    }

    public void setTableList(List<Table> tableList) {
        this.tableList = tableList;
    }




}
