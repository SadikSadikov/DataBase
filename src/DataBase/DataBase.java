package DataBase;

import Commands.*;
import Exceptions.InvalidData;
import Exceptions.InvalidFileName;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.*;


public class DataBase {

    private static Map<String,Table> dataBase = new HashMap<>();
    private static List<String> dataBaseFile = new ArrayList<>();
    private static List<String> files = new ArrayList<>();



    public static Map<String, Table> getDataBase() {
        return dataBase;
    }

    public static void setDataBase(Map<String, Table> dataBase) {
        DataBase.dataBase = dataBase;
    }

    public static List<String> getDataBaseFile() {
        return dataBaseFile;
    }

    public static void setDataBaseFile(List<String> dataBaseFile) {
        DataBase.dataBaseFile = dataBaseFile;
    }

    public static List<String> getFiles() {
        return files;
    }

    public static void setFiles(List<String> files) {
        DataBase.files = files;
    }

    public void readyTables() throws JAXBException, IOException {
        Table customers = new Table();
        Table staffs = new Table();
        Table stores = new Table();
        Table orders = new Table();
        dataBase.put("customers", customers);
        dataBase.put("staffs", staffs);
        dataBase.put("stores", stores);
        dataBase.put("orders", orders);

        customers.unmarshal("customers.xml");
        staffs.unmarshal("staffs.xml");
        stores.unmarshal("stores.xml");
        orders.unmarshal("orders.xml");
    }

    public void commands() throws JAXBException, IOException{
        readyTables();
        Scanner scanner = new Scanner(System.in);
        Scanner scannerString = new Scanner(System.in);
        Scanner scannerInt = new Scanner(System.in);
        String fileName;
        String tableName;
        String columnName;
        String value;
        int id;
        int choice;

        do {
            System.out.println("************************MENU************************");
            System.out.println("1-> Import <file name>");
            System.out.println("2-> Show Tables ");
            System.out.println("3-> Describe <name>");
            System.out.println("4-> Print <name>");
            System.out.println("5-> Export <name> <file name>");
            System.out.println("6-> Select <column-n> <value> <table name>");
            System.out.println("7-> AddColumn <table name> <column name> <column type>");
            System.out.println("8-> Update <table name> <search column n> <search value> <target column n> <target value>");
            System.out.println("9-> Delete <table name> <search column n> <search value>");
            System.out.println("10-> Insert <table name> <column 1> <column n>");
            System.out.println("11-> InnerJoin <table 1> <column n1> <table 2> <column n2>");
            System.out.println("12-> Rename <old name> <new name>");
            System.out.println("13-> Count <table name> <search column n> <search value>");
            System.out.println("14-> Aggregate <table name> <search column n> <search value> <target column n> <operation>");
            System.out.println("0-> Exit: ");
            System.out.println("****************************************************");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice){
                case 1:
                    Import importt = new Import();
                    if(!(files.isEmpty())){
                        fileNames();
                        System.out.print("Enter file name: ");
                        fileName = scannerString.nextLine();
                        for(String s:files){
                            if(fileName.equals(s)){
                                try {
                                    importt.importFile(fileName);
                                } catch (InvalidFileName e) {
                                    System.out.println(e);
                                }
                            }
                        }
                    }
                    else{
                        System.out.println("There are no files, please add from the export command");
                    }


                    System.out.println();

                    break;
                case 2:
                    ShowTables showTables = new ShowTables();
                    showTables.showTables();
                    break;
                case 3:
                    Describe describe = new Describe();
                    tableNames();
                    System.out.print("Please enter table name: ");
                    tableName = scannerString.nextLine();
                    try {
                        describe.describe(tableName);
                    } catch (InvalidData e) {
                        System.out.println(e);
                    }
                    break;
                case 4:
                    Print print = new Print();
                    tableNames();
                    System.out.print("Please enter table name: ");
                    tableName = scannerString.nextLine();
                    boolean status = false;
                    for (Map.Entry<String, Table> entry : DataBase.getDataBase().entrySet()) {
                        if (tableName.equals(entry.getKey())) {
                            print.print(tableName);
                            status = true;
                        }

                    }
                    if(!status){
                        System.out.println("There is no such table");
                    }
                    break;
                case 5:
                    Export export = new Export();
                    System.out.print("Please enter file name: ");
                    fileName = scannerString.nextLine()+".xml";
                    tableNames();
                    System.out.print("Please enter table name: ");
                    tableName = scannerString.nextLine();
                    boolean status1 = false;
                    for (Map.Entry<String, Table> entry : DataBase.getDataBase().entrySet()) {
                        if (tableName.equals(entry.getKey())) {
                            export.export(fileName,tableName);
                            status1 = true;
                        }

                    }
                    if(!status1){
                    System.out.println("There is no such table");
                    }
                    break;
                case 6:
                    Select select = new Select();
                    System.out.print("Please enter column name: ");
                    columnName = scannerString.nextLine();
                    tableNames();
                    System.out.println("Please enter table name: ");
                    tableName = scannerString.nextLine();
                    select.select(columnName,tableName);
                    break;
                case 7:
                    AddColumn addColumn = new AddColumn();
                    tableNames();
                    System.out.println("Please enter table name: ");
                    tableName = scannerString.nextLine();
                    System.out.print("Please enter column name: ");
                    columnName = scannerString.nextLine();

                    System.out.println("*******MENU*******");
                    System.out.println("1 - INT");
                    System.out.println("2 - DOUBLE");
                    System.out.println("3 - STRING");
                    System.out.println("4 - NULL");
                    System.out.println("******************");

                    System.out.print("Please enter type: ");
                    int choiceTypes = scannerInt.nextInt();
                    addColumn.addColumn(tableName,columnName,determinationType(choiceTypes));
                    break;
                case 8:
                    Update update = new Update();
                    tableNames();
                    System.out.print("Please enter table name: ");
                    tableName = scannerString.nextLine();
                    System.out.print("Please enter column name: ");
                    columnName = scannerString.nextLine();
                    System.out.print("Please enter your target value: ");
                    String targetValue = scannerString.nextLine();
                    System.out.print("Please enter int which id: ");
                    id = scannerInt.nextInt();
                    update.update(tableName,columnName,targetValue,id);
                    break;
                case 9:
                    Delete delete = new Delete();

                    tableNames();
                    System.out.print("Please enter table name: ");
                    tableName = scannerString.nextLine();
                    System.out.print("Please enter column name: ");
                    columnName = scannerString.nextLine();
                    System.out.print("Please enter value: ");
                    value = scannerString.nextLine();
                    System.out.print("Please enter id: ");
                    id = scannerInt.nextInt();
                    delete.delete(tableName,columnName,value,id);
                    break;
                case 10:
                    Insert insert = new Insert();
                    tableNames();
                    System.out.print("Please enter table name: ");
                    tableName = scannerString.nextLine();
                    System.out.print("Please enter column name: ");
                    columnName = scannerString.nextLine();
                    System.out.print("Please enter value: ");
                    value = scannerString.nextLine();
                    insert.insert(tableName,columnName,value);
                    break;
                case 11:
                    InnerJoin innerJoin = new InnerJoin();
                    tableNames();
                    System.out.print("Please enter table name1: ");
                    tableName = scannerString.nextLine();

                    tableNames();
                    System.out.print("Please enter table name2: ");
                    String tableName2 = scannerString.nextLine();

                    System.out.print("Please enter column name1: ");
                    columnName = scannerString.nextLine();

                    System.out.print("Please enter column name2: ");
                    String columnName2 = scannerString.nextLine();

                    System.out.print("Please enter matching column name: ");
                    String matchingColumnName = scannerString.nextLine();

                        innerJoin.innerJoin(tableName,columnName,tableName2,columnName2,matchingColumnName);
                    break;
                case 12:
                    Rename rename = new Rename();
                    tableNames();
                    System.out.print("Please enter older table name: ");
                    tableName = scannerString.nextLine();

                    System.out.print("Please enter new table name: ");
                     String newTableName = scannerString.nextLine();
                    rename.rename(tableName,newTableName);
                    break;
                case 13:
                    Count count = new Count();

                    tableNames();
                    System.out.print("Please enter table name: ");
                    tableName = scannerString.nextLine();

                    System.out.print("Please enter column name: ");
                    columnName = scannerString.nextLine();

                    System.out.print("Please enter value: ");
                    value = scannerString.nextLine();
                    count.count(tableName,columnName,value);
                    break;

                    case 14:
                        Aggregate aggregate = new Aggregate();

                        tableNames();
                        System.out.print("Please enter table name: ");
                        tableName = scannerString.nextLine();

                        System.out.print("Please enter column name: ");
                        columnName = scannerString.nextLine();
                        System.out.println("*****MENU*****");
                        System.out.println("1 - SUM");
                        System.out.println("2 - AVG");
                        System.out.println("3 - MAX");
                        System.out.println("4 - MIN");
                        System.out.println("**************");
                        System.out.print("Enter your choice: ");
                        int choice1 = scannerInt.nextInt();
                        aggregate.aggregate(tableName,columnName,choice1);
                        break;

            }

        }while(choice != 0);

    }

    public void tableNames(){
        System.out.println("*****-Table names-*****");
        for (Map.Entry<String, Table> entry : DataBase.getDataBase().entrySet()) {
            System.out.println(entry.getKey());
        }
        System.out.println("************************");
    }
    public void fileNames(){
        System.out.println("*****-Table file names-*****");
        for(String s:files){
            System.out.println(s);
        }
        System.out.println("************************");
    }

    public static void showDataBase(Map<String,Table> map){
        for (Map.Entry<String, Table> entry : map.entrySet()) {
            System.out.println("Key: "+entry.getKey());
        }
    }

    public Types determinationType(int choice){
        switch (choice){
            case 1:
                return Types.INT;
            case 2:
                return Types.DOUBLE;
            case 3:
                return Types.STRING;
            case 4:
                return Types.NULL;
            default:
                return Types.NULL;
        }
    }



}
