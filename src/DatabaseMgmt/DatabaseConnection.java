package DatabaseMgmt;

import UserAuthentication.Controller.LoginController;
import UserAuthentication.Model.User;

import java.sql.*;

public class DatabaseConnection {
    private Connection connection;

    public DatabaseConnection(){}


    public void openConnection()
    {
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            connection = DriverManager.getConnection("jdbc:ucanaccess://C://Users//archo//OneDrive//Documents//IntelliJProjects//IST412-Project//LMSDB.accdb");
        }
        catch (Exception ee)
        {
            System.out.println(ee);
        }
    }

    public void executeQuery(String query, int columnNumber)
    {
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            StringBuilder stringBuilder = new StringBuilder();

            while (rs.next())
            {
                for (int i = 1; i <= columnNumber; i++) {
                    String column = rs.getString(i);
                    stringBuilder.append(column).append(" ");
                }
            }
            System.out.println(stringBuilder);
            connection.commit();
        }
        catch (Exception ee)
        {
            System.out.println(ee);
        }
    }

    public boolean getUserInfo(LoginController loginController){
        openConnection();
        String userName = loginController.getU1().getLoginID().toLowerCase();
        try
        {
            Statement st = connection.createStatement();
            String query = "SELECT * FROM UserTable WHERE username = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, userName);
            ResultSet rs = pstmt.executeQuery();
            int column = rs.findColumn("password");

            if (rs.next())
            {
                if (rs.getString(column).equals(loginController.getU1().getPassword()))
                {
                    loginController.getU1().setFirstName(rs.getString("firstname"));
                    loginController.getU1().setLastName(rs.getString("lastname"));
                    loginController.getU1().setRoleID(rs.getString("roleID"));
                    return true;
                }
            }
        }
        catch (Exception ee)
        {
            System.out.println(ee);;
        }
        closeConnection();
        return false;
    }

    public void closeConnection()
    {
        try
        {
            connection.close();
        }
        catch (Exception ee)
        {
            System.out.println(ee);
        }
    }
}
