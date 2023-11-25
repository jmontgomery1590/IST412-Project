package UserAuthentication.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginConnection {

    public void establishServerConnection(){
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection conn = DriverManager.getConnection("jdbc:ucanaccess://C://Users//archo//OneDrive//Documents//IntelliJProjects//IST412-Project//LMSDB.accdb");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM UserTable");

            while (rs.next())
            {
                String column1 = rs.getString(1);
                String column2 = rs.getString(2);
                String column3 = rs.getString(3);
                String column4 = rs.getString(4);
                String column5 = rs.getString(5);
                String column6 = rs.getString(6);

                System.out.println("UserID: " + column1+ " Username: " + column2 + " Password: " + column3 + " FirstName: " + column4 + " LastName: " + column5 + " RoleID: " + column6);
            }

            conn.commit();
            conn.close();
        }
        catch (Exception ee)
        {
            System.out.println(ee);
        }
    }
}
