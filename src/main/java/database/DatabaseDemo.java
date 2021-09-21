package database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DatabaseDemo
{
    private final Properties dbProps = new Properties();

    public static void main(String[] args)
    {
        DatabaseDemo dbDemo = new DatabaseDemo();
        dbDemo.doDatabaseCheck();
    }

    private void doDatabaseCheck()
    {
        InputStream is = getClass().getClassLoader().getResourceAsStream("database.properties");

        if (is != null) {
            try
            {
                dbProps.load(is);
                is.close();

                String url = dbProps.getProperty("url");
                Connection conn = DriverManager.getConnection(url, dbProps);

                PreparedStatement statement = conn.prepareStatement("SELECT * FROM public.item");
                ResultSet resultSet = statement.executeQuery();

                resultSet.next();

                String id = resultSet.getString("id");

                System.out.println("ID of first item = " + id);
            }
            catch (SQLException | IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
