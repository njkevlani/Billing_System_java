//STEP 1. Import required packages
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DB {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/Rasmalai_Billing?allowMultiQueries=true";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";

    Connection conn = null;
    Statement stmt = null;

    public DB(){
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            //System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    void add_row_in_Billing(String[] a, int[] ia) {

        try{
            String cname = a[0], item = a[1], bill_no = a[2], date = a[3];
            int rate = ia[0], qty = ia[1];

            //STEP 4: Execute a query
            //System.out.println("Creating database...");

            String sql = "INSERT INTO `Bills` (`cname`, `item`, `rate`, `qty`, `date`, `bill_no`) VALUES ('" + cname + "','" + item + "'," + rate + "," + qty + "," + "STR_TO_DATE('" + date + "','%d/%m/%Y'),'" + bill_no + "')";
            //String sql = "insert into Bills values (`" + cname + "`,`" + item + "`," + rate + "," + qty + "," + "TO_DATE('" + date + "'," + "'mm/dd/yyyy'),`" + bill_no + "`)";
            //String sql = "INSERT INTO `Bills` (`cname`, `item`, `rate`, `qty`, `date`, `bill_no`) VALUES ('cname1',`item1`,85,15,TO_DATE('"+date+"','mm/dd/yyyy'),`bill_no`)";
            stmt.executeUpdate(sql);

            //stmt.executeUpdate(sql);
            //System.out.println("Database created successfully...");
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }//end try
        System.out.println("Goodbye!");
    }

    String[][] getBillData() {
        int nCol=0,i=0;
        String ret[][] = null;
        List<String[]> table = new ArrayList<>();
        try {
            ResultSet r = stmt.executeQuery("Select count(*) from Bills");
            //System.out.println(i);
            r.next();
            i = Integer.parseInt(r.getString("count(*)"));
            //System.out.println(i);
            ResultSet result = stmt.executeQuery("Select * from Bills");
            nCol = result.getMetaData().getColumnCount();
            ret = new String[i][nCol];
            int ii=0;
            while (result.next()) {
                //i++;
                String[] row = new String[nCol];
                for (int iCol = 1; iCol <= nCol; iCol++) {
                    Object obj = result.getObject(iCol);
                    row[iCol - 1] = (obj == null) ? null : obj.toString();
                }
                ret[ii]=row;
                ii++;
                table.add(row);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        //return table;

         //= (String[][])table.toArray();
        return ret;
    }
}