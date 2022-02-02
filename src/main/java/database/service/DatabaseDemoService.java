package database.service;

import database.repository.ItemsRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;

@Service
public class DatabaseDemoService
{
    @Inject
    private EntityManagerFactory entityManagerFactory;

    @Inject
    private ItemsRepository itemsRepository;

    public EntityManagerFactory getEntityManagerFactory()
    {
        return entityManagerFactory;
    }
}
