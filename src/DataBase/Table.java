
package DataBase;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


@XmlRootElement(name = "Column")
public class Table {

    private String columnName;
    private int idColumn;
    private String value;
    private Types types;
    private List<Object> rows = new ArrayList<>();
    private List<Types> typesList = new ArrayList<>();


    public Table(Types types) {
        this.types = types;
    }

    public Table() {
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public int getIdColumn() {
        return idColumn;
    }

    public void setIdColumn(int idColumn) {
        this.idColumn = idColumn;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @XmlElement
    public Types getTypes() {
        return types;
    }

    public void setTypes(Types types) {
        this.types = types;
    }

    public List<Object> getRows() {
        return rows;
    }

    public void setRows(List<Object> rows) {
        this.rows = rows;
    }


    public List<Types> getTypesList() {
        return typesList;
    }

    public void setTypesList(List<Types> typesList) {
        this.typesList = typesList;
    }


    public void printTables(List<Object> list,int y){
        int newLine = 0;
        Object n = 1;
        int i = 0;
        int j = 1;
        for(Object o:list){
            if(i == j){
                if(n == list.get(j-1)){
                    System.out.print(o+"        ");
                    newLine++;
                }

                j+=4;
            }
            i++;
        }
        System.out.println();
        int counter = 0;
        int newLine1 = newLine;
        for(int k = 0;k < list.size();k+=4){
            if(list.get(k).equals(y-1) || list.get(k).equals(y)){
                    System.out.print(list.get(k+3) + "           ");
                    counter++;

                if(newLine1 == counter){
                    System.out.println();
                    newLine1+=newLine;
                }
            }
        }
    }

    public void printAllTables(List<Object> list){
        int newLine = 0;
        Object n = 1;
        int i = 0;
        int j = 1;
        for(Object o:list){
            if(i == j){
                if(n == list.get(j-1)){
                    System.out.print(o+"        ");
                    newLine++;
                }

                j+=4;
            }
            i++;
        }
        System.out.println();
        int counter = 0;
        int newLine1 = newLine;
        for(int k = 3;k < list.size();k+=4){
            System.out.print(list.get(k)+"        ");
            counter++;
                if(newLine1 == counter){
                    System.out.println();
                    newLine1+=newLine;
                }

        }
    }

    public void printTable(List<Object> list){
        int i = 1;
        int n = 4;
        System.out.println("CoID - CoName - Type - Value ");
        for (Object value : list) {
            if(i < n) {
                System.out.print(value + "   ");
            }
            else {
                System.out.print(value + "   ");
                System.out.println();


                n += 4;
            }

            i++;
        }
        System.out.println();
    }

        public void unmarshal(String fileName) throws JAXBException {
            JAXBContext context = JAXBContext.newInstance(ListTable.class);
            Unmarshaller ums = context.createUnmarshaller();
            ListTable tables = (ListTable) ums.unmarshal(new File("C:\\Users\\USER\\IdeaProjects\\DataBase\\src\\ReadyTables\\" + fileName));

            for (Table t : tables.getTableList()) {
                getRows().add(t.getIdColumn());
                getRows().add(t.getColumnName());
                getRows().add(t.getTypes());
                typesList.add(t.getTypes());
                getRows().add(determinationValue(t.getTypes(),t.getValue()));

            }
            System.out.println();
        }

        public Object determinationValue(Types types,String value){
            switch(types){
                case INT:
                    return numberInt(value);
                case DOUBLE:
                    return numberDouble(value);

                case STRING:
                    return numberString(value);

                case NULL:
                    return numberNull();
                default:
                    throw new IllegalArgumentException();
            }
        }

    public  int numberInt(String string) {
        int intValue = 0;


        try {
            intValue = Integer.parseInt(string);
            return intValue;
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        return intValue;
    }

    public  String numberNull() {

        return "NULL";

    }
    public  double numberDouble(String string) {
        double doubleValue = 0;
        try {

            doubleValue = Double.parseDouble(string);

            return doubleValue;
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        return doubleValue;

    }

    public String numberString(String string){
        return "\""+string+"\"";
    }

}







