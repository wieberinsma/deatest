package database;

import database.config.AppConfig;
import database.repository.ItemsRepository;
import database.service.DatabaseDemoService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class DatabaseDemo
{
    @Inject
    private ItemsRepository itemsRepository;

    @Inject
    private DatabaseDemoService databaseDemoService;

    public static void main(String[] args)
    {
        // Here we need to initialize this 'main' class to run the application (i.e. non-statically)
        DatabaseDemo databaseDemoApplication = new DatabaseDemo();
        databaseDemoApplication.findAllItemsAndPrint();
    }

    private void findAllItemsAndPrint()
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Here, we have a managed Bean version of the current 'main' class
        var applicationRootBean = context.getBean(DatabaseDemo.class);

        // Which is why here we have the root classes' dependencies injected
        var itemsRepositoryBean = applicationRootBean.getItemsRepository();
        var databaseServiceBean = applicationRootBean.getDatabaseDemoService();
        var entityManagerFactoryBean = databaseServiceBean.getEntityManagerFactory();

        System.out.println("Get class name for repository bean: " + itemsRepositoryBean.getClass());
        System.out.println("Get class name for service bean: " + databaseServiceBean.getClass());
        System.out.println("Get class name for emf bean: " + entityManagerFactoryBean.getClass());
    }

    public ItemsRepository getItemsRepository() {
        return itemsRepository;
    }

    private DatabaseDemoService getDatabaseDemoService()
    {
        return databaseDemoService;
    }
}
