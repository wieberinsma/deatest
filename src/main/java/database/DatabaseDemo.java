package database;

import database.config.AppConfig;
import database.model.Item;
import database.repository.ItemsRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Properties;

public class DatabaseDemo
{
    private final Properties dbProps = new Properties();

    public static void main(String[] args)
    {
        DatabaseDemo dbDemo = new DatabaseDemo();

        // Using Spring Data (and Hibernate, and JPA, etc...)
        dbDemo.findAllItemsAndPrint();
    }

    private void findAllItemsAndPrint()
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        var demoService = context.getBean(DatabaseDemoService.class);
        var demoRepository = context.getBean(ItemsRepository.class);

        if (demoRepository != null)
        {
            for (Item item : demoRepository.findAll())
            {
                System.out.println(item.toString());
            }
        }
    }
}
