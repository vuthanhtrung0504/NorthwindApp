package wk3;

import wk3.db.DBApp;


public class NorthwindApp {
    public static String customercl[] = { "CustomerID", "CompanyName",
            "ContactName", "ContactTitle", "Address", "City", "Region",
            "PostalCode", "Country", "Phone", "Fax"};

    public static String orders[] = {"orderid", "customerid", "employeeid",
            "orderdate", "requireddate", "shippeddate", "shipvia", "freight",
            "shipname", "shipaddress", "shipcity", "shipregion", "shippostalcode", "shipcountry"};

    public static String employees[] = {"#", "employeeid", "lastname", "firstname",
            "title", "titleofcourtesy", "birthdate", "hiredate", "address", "city",
            "region", "postalcode", "country", "homephone", "extension", "photo", "notes", "reportsto", "photopath"};

    public static String insert(char c){
        String sql = "";
        if(c=='1'){
//				String[] customer ={"","","","","","","","","","",""};
            String[] customer = new String[customercl.length];
            sql = "insert into Customers  VALUES (";
            for(int i = 0; i < customercl.length; i++){
                TextIO.putln( customercl[i]+": ");
                if(customer[i]==null)
                    customer[i]="";
                customer[i] += TextIO.getln();

                if (i==customercl.length-1)
                    sql += "'"+customer[i]+"'";
                else
                    sql += "'"+customer[i]+"'"+",";

            }
            sql +=")";
        }
        if(c=='2'){
            String[] order = new String[orders.length];
                sql = "insert into Orders  VALUES (";
                for(int i = 0; i < orders.length; i++){
                    TextIO.putln( orders[i]+": ");
                    if(order[i]==null)
                        order[i]="";
                order[i] += TextIO.getln();

                if (i==orders.length-1)
                    sql += "'"+order[i]+"'";
                else
                    sql += "'"+order[i]+"'"+",";

            }
            sql +=")";
        }
        if(c=='3'){
            String[] employee = new String[employees.length];
            sql = "insert into Employees  VALUES (";
            for(int i = 0; i < employees.length; i++){
                TextIO.putln( employees[i]+": ");
                if(employee[i]==null)
                    employee[i]="";
                employee[i] += TextIO.getln();

                if (i==employees.length-1)
                    sql += "'"+employee[i]+"'";
                else
                    sql += "'"+employee[i]+"'"+",";

            }
            sql +=")";
        }
        return sql;

    }

    public static String update(char c, String ID){
        String sql = "";
        if(c=='1'){
//			String[] customer ={"","","","","","","","","","",""};
            String[] customer = new String[customercl.length];
            sql = "update Customers SET ";
            for(int i = 1; i < customercl.length; i++){
                TextIO.putln( customercl[i]+": ");
                if(customer[i]==null)
                    customer[i]="";
                customer[i] += TextIO.getln();


                if (i==customercl.length-1)
                    sql += ""+customercl[i]+""+"='"+customer[i]+"'";
                else
                    sql += ""+customercl[i]+""+"='"+customer[i]+"'"+",";

            }
            sql +=" WHERE customerid = '"+ID+"'";
        }
        if(c=='2'){
            String[] order = new String[orders.length];
            sql = "update Customers SET ";
            for(int i = 1; i < orders.length; i++){
                TextIO.putln( orders[i]+": ");
                if(order[i]==null)
                    order[i]="";
                order[i] += TextIO.getln();


                if (i==orders.length-1)
                    sql += ""+orders[i]+""+"='"+order[i]+"'";
                else
                    sql += ""+orders[i]+""+"='"+order[i]+"'"+",";

            }
            sql +=" WHERE orderid = '"+ID+"'";

        }
        if(c=='3'){
            String[] employee = new String[employees.length];
            sql = "update Customers SET ";
            for(int i = 1; i < employees.length; i++){
                TextIO.putln( employees[i]+": ");
                if(employee[i]==null)
                    employee[i]="";
                employee[i] += TextIO.getln();


                if (i==employees.length-1)
                    sql += ""+employees[i]+""+"='"+employee[i]+"'";
                else
                    sql += ""+employees[i]+""+"='"+employee[i]+"'"+",";

            }
            sql +=" WHERE employeeid = '"+ID+"'";

        }
        return sql;
    }
    public static void main (String[] args){
        // general tasks: preparation
        DBApp dba = null;
        dba = new DBApp(DBApp.DRIVER_POSTGRESQL);

        // connect to database
        boolean ok = dba.connect("Northwind","postgres","trung0504");
        /** alternative: connect using specific username, password
         * (requires the updated library file)
         String dbName = "northwind";
         String userName = "postgres";
         String password = "postgres";
         boolean ok = dba.connect(dbName, userName, password);
         */

        if (!ok)
            System.exit(1);

        // application specific tasks
        try {
            String sql = "";
            TextIO.putln("a.select from Customers table");
            TextIO.putln("b.select from orders table table");
            TextIO.putln("c.select from employees table");
            TextIO.putln("d.search from Custommers table with specified contact name");
            TextIO.putln("e.insert into a table");
            TextIO.putln("f.delete a data row");
            TextIO.putln("g.update a data row");
            char c = TextIO.getlnChar();

            switch(c){
                case 'a':
                    sql="select * from customers";
                    break;
                case 'b':
                    sql="select * from orders";
                    break;
                case 'c':
                    sql="select * from employees";
                    break;
                case 'd':
                    TextIO.putln("enter contact name");
                    String key = TextIO.getln();
                    sql="select * from Customers Where contactname LIKE '%"+key+"%'";
                    break;
                case 'f':
                    TextIO.putln("which table do you want to delete a data row? ");
                    TextIO.putln(" (1)Customers");
                    TextIO.putln(" (2)Orders");
                    TextIO.putln(" (3)Employees");
                    char i = TextIO.getlnChar();
                    switch(i){
                        case '1':
                            String result1 = dba.select("select customerid from customers");
                            TextIO.put(result1);
                            TextIO.putln("enter CustomerID you want to delete");
                            String CustomerID = TextIO.getlnString();
                            sql="delete FROM Customers where customerid = '"+CustomerID+"' ";
                            break;
                        case '2':
                            String result2 = dba.select("select orderid from orders");
                            TextIO.put(result2);
                            TextIO.putln("enter OrderID you want to delete");
                            String Orderid = TextIO.getlnString();
                            sql="delete FROM Customers where customerid = '"+Orderid+"' ";
                            break;
                        case '3':
                            String result3 = dba.select("select employeeid from employees");
                            TextIO.put(result3);
                            TextIO.putln("enter EmployeeID you want to delete");
                            String EmployeeID = TextIO.getlnString();
                            sql="delete FROM Customers where employeeid = '"+EmployeeID+"' ";
                            break;
                        default:
                            TextIO.putln("option is not available!");
                    }
                    break;
                case 'g':
                    TextIO.putln("which table do you want to update a data row? ");
                    TextIO.putln(" (1)Customers");
                    TextIO.putln(" (2)Orders");
                    TextIO.putln(" (3)Employees");
                    char k = TextIO.getlnChar();
                    switch(k){
                        case '1':
                            String result1 = dba.select("select customerid from customers");
                            TextIO.putln(result1);
                            TextIO.putln();
                            TextIO.put("enter CustomerID you want to update");
                            String CustomerID = TextIO.getlnString();
//			    		String detail = dba.select("select * FROM Customers where customerid = '"+CustomerID+"' ");
//			    		sql="select * FROM Customers where customerid = '"+CustomerID+"' ";
//			    		TextIO.putln(detail);
                            sql=update('1',CustomerID);
                            break;
                        case '2':
                            String result2 = dba.select("select orderid from ordrers");
                            TextIO.putln(result2);
                            TextIO.putln();
                            TextIO.put("enter OrderID you want to update");
                            String OrderID = TextIO.getlnString();
                            sql=update('2',OrderID);
                            break;
                        case '3':
                            String result3 = dba.select("select employeeid from employees");
                            TextIO.putln(result3);
                            TextIO.putln();
                            TextIO.put("enter EmployeeID you want to update");
                            String EmployeeID = TextIO.getlnString();
                            sql=update('3',EmployeeID);
                            break;
                        default:
                            TextIO.putln("option is not available!");
                    }
                    break;
                case 'e':
                    TextIO.putln("which table do you want to insert? ");
                    TextIO.putln(" (1)Customers");
                    TextIO.putln(" (2)Orders");
                    TextIO.putln(" (3)Employees");
                    char j = TextIO.getlnChar();
                    switch(j){
                        case '1':
                            sql=insert('1');
                            break;
                        case '2':
                            sql=insert('2');
                            break;
                        case '3':
                            sql=insert('3');
                            break;
                        default:
                            TextIO.putln("option is not available!");
                    }
                    break;

                default:
                    TextIO.putln("option is not available!");
            }


            System.out.println("Executing query: " + sql);
            if(sql.contains("select")){
                // execute SQL statement to get result
                String result = dba.select(sql);

                //System.out.println(result);

                // write result to file
                String userDir = System.getProperty("user.dir");
                String fileChar = System.getProperty("file.separator");
                String file = userDir+fileChar+"sqloutput.html";

                // OR String file = "sqloutput.html"

                TextIO.writeFile(file);
                TextIO.putln("<b>Result for query:</b><br>" + sql);
                TextIO.putln("<p>");
                TextIO.putln(result);

                System.out.println("Written result to file " + file);

                TextIO.writeStandardOutput();
            }
            else if(sql.contains("insert")){
                boolean result = dba.insert(sql);
                System.out.println(result + " Executing successfully!");
            }
            else if(sql.contains("delete")){
                boolean result = dba.delete(sql);
                System.out.println(result + " Executing successfully!");
            }
            else if(sql.contains("update")){
                boolean result = dba.update(sql);
                System.out.println(result + " Executing successfully!");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // general tasks: close
        dba.close();
    }
}