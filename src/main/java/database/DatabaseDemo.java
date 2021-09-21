package database;

import database.jpa.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

@Component
public class DatabaseDemo
{
    private final Properties dbProps = new Properties();

    @Autowired
    private ItemsRepository itemsRepository;

    public static void main(String[] args)
    {
        DatabaseDemo dbDemo = new DatabaseDemo();
        dbDemo.printItemIds();
        dbDemo.deleteSecondItem();
        dbDemo.printItemIds();
        dbDemo.insertAnotherItem();
        dbDemo.printItemIds();

        // Using Spring Data (and Hibernate, and JPA, etc...)
//        dbDemo.findAllItemsAndPrint();
    }

    private void printItemIds()
    {
        Connection conn = getConnection();

        if (conn != null)
        {
            try
            {
                PreparedStatement statement = conn.prepareStatement("SELECT * FROM public.items");
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next())
                {
                    System.out.println("ID of item on row " + resultSet.getRow() + " = " +
                            resultSet.getString("id"));
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void deleteSecondItem()
    {
        Connection conn = getConnection();

        if (conn != null)
        {
            try
            {
                PreparedStatement statement = conn.prepareStatement("DELETE FROM public.items WHERE id = 2");
                statement.execute();
                System.out.println("Record deleted!");
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void insertAnotherItem()
    {
        Connection conn = getConnection();

        if (conn != null)
        {
            try
            {
                PreparedStatement statement = conn.prepareStatement(
                        "INSERT INTO public.items VALUES (2, 'Soup', array['Lunch', 'Dinner'], 'Soup not so much!')");
                statement.execute();
                System.out.println("Record inserted!");
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    private Connection getConnection()
    {
        Connection conn = null;

        InputStream is = getClass().getClassLoader().getResourceAsStream("database.properties");
        if (is != null)
        {
            try
            {
                dbProps.load(is);
                is.close();

                String url = dbProps.getProperty("url");
                conn = DriverManager.getConnection(url, dbProps);
            }
            catch (IOException | SQLException e)
            {
                e.printStackTrace();
            }
        }

        return conn;
    }

    private void findAllItemsAndPrint()
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        if (itemsRepository != null)
        {
            for (Item item : itemsRepository.findAll())
            {
                System.out.println(item.toString());
            }
        }
    }
}
