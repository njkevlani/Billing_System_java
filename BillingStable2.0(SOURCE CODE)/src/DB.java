//STEP 1. Import required packages
import java.sql.*;

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
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    void add_row_in_Billing(String[] a, int[] ia) {

        try{
            System.out.print("hii");
            String cname = a[0], item = a[1], bill_no = a[2], date = a[3];
            int rate = ia[0], qty = ia[1];

            String sql = "INSERT INTO `Bills` (`cname`, `item`, `rate`, `qty`, `date`, `bill_no`) VALUES ('" + cname + "','" + item + "'," + rate + "," + qty + "," + "STR_TO_DATE('" + date + "','%d/%m/%Y'),'" + bill_no + "')";
            stmt.executeUpdate(sql);

        }

        catch(Exception e){
            e.printStackTrace();
        }
    }

    void add_in_BillPaymentPending(String cname,String bill_no,int sum,int paid,String date){
        String str = "'" + cname +"','" + bill_no +"','" + sum + "','" + paid + "', STR_TO_DATE('" + date + "','%d/%m/%Y')"  ;
        String sql = "INSERT INTO `BillPaymentPending` (`cname`,`bill_no`, `amount`, `paid`, `date`) VALUES ("+ str +")";
        try {
            stmt.executeUpdate(sql);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    void update_BillPaymentPending(String bill_no, int amount){
        String sql = "Select * from `BillPaymentPending` WHERE bill_no=" + bill_no;
        int paid = 0,total = 0;
        try {
            ResultSet r = stmt.executeQuery(sql);
            r.next();
            paid = Integer.parseInt( r.getString("paid"));
            total = Integer.parseInt( r.getString("amount"));
        }
        catch (Exception e){
            e.printStackTrace();
        }

        if((total - paid) != amount){
            paid += amount;
            sql = "UPDATE BillPaymentPending SET paid = "+ paid  +" WHERE bill_no="+bill_no;
            try {
                stmt.executeUpdate(sql);
            }
            catch (Exception e){
                System.out.println("Helllo");
                e.printStackTrace();
            }
        }
        else{
            try {
                stmt.executeUpdate("DELETE FROM `BillPaymentPending` WHERE bill_no="+bill_no);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    String[][] getBillData() {
        int nCol=0,i=0;
        String ret[][] = null;
        try {
            ResultSet r = stmt.executeQuery("Select count(*) from Bills");
            r.next();
            i = Integer.parseInt(r.getString("count(*)"));
            ResultSet result = stmt.executeQuery("Select cname, item, rate, qty, DATE_FORMAT(date,'%d/%m/%Y') date, bill_no from Bills ORDER BY bill_no DESC");
            nCol = result.getMetaData().getColumnCount();
            ret = new String[i][nCol];
            int ii=0;
            while (result.next()) {
                String[] row = new String[nCol];
                for (int iCol = 1; iCol <= nCol; iCol++) {
                    Object obj = result.getObject(iCol);
                    row[iCol - 1] = (obj == null) ? null : obj.toString();
                }
                ret[ii]=row;
                ii++;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    int getPaid(String bill_no){
        ResultSet r;
        int i = -1;
        try {
           r = stmt.executeQuery("SELECT paid from BillPaymentPending where bill_no='" + bill_no + "'");
           if(r.next())
               i = Integer.parseInt(r.getString("paid"));
        }
        catch (Exception e){
           e.printStackTrace();
        }
        return i;

    }

    String[][] getBillData(String cname,String bill_no,String date) {
        int nCol=0,i=0;
        String ret[][] = null;
        String sql = "Select cname, item, rate, qty,DATE_FORMAT(date,'%d/%m/%Y') date, bill_no from Bills ";
        String subsql = "";

        if(!(cname.equals("Cname") && bill_no.equals("") && date.equals(""))){
            subsql += "WHERE ";
            if(!cname.equals("Cname")){
                subsql += "cname='"+cname+"' ";
                if(!bill_no.equals("") || !date.equals(""))
                    subsql += "AND ";
            }
            if(!bill_no.equals("")){
                subsql += "bill_no="+bill_no+" ";
                if(!date.equals(""))
                    subsql += "AND ";
            }
            if(!date.equals("")){
                subsql += "date=STR_TO_DATE('" + date + "','%d/%m/%Y')";
            }

            sql += subsql;
            System.out.print(sql);
        }
        try {
            ResultSet r = stmt.executeQuery("Select count(*) from Bills " + subsql);
            r.next();
            i = Integer.parseInt(r.getString("count(*)"));
            ResultSet result = stmt.executeQuery(sql + " ORDER BY bill_no DESC");
            nCol = result.getMetaData().getColumnCount();
            ret = new String[i][nCol];
            int ii=0;
            while (result.next()) {
                String[] row = new String[nCol];
                for (int iCol = 1; iCol <= nCol; iCol++) {
                    Object obj = result.getObject(iCol);
                    row[iCol - 1] = (obj == null) ? null : obj.toString();
                }
                ret[ii]=row;
                ii++;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }



    String[][] getBillPaymentPendingData() {
        int nCol=0,i=0;
        String ret[][] = null;
        try {
            ResultSet r = stmt.executeQuery("Select count(*) from BillPaymentPending");
            r.next();
            i = Integer.parseInt(r.getString("count(*)"));
            ResultSet result = stmt.executeQuery("Select cname,bill_no,amount,paid,DATE_FORMAT(date,'%d/%m/%Y') date from BillPaymentPending ORDER BY bill_no DESC");
            nCol = result.getMetaData().getColumnCount();
            ret = new String[i][nCol];
            int ii=0;
            while (result.next()) {
                String[] row = new String[nCol];
                for (int iCol = 1; iCol <= nCol; iCol++) {
                    Object obj = result.getObject(iCol);
                    row[iCol - 1] = (obj == null) ? null : obj.toString();
                }
                ret[ii]=row;
                ii++;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
}