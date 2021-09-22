package database.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "items")
public class Item
{
    @Id
    @Column
    private long id;

    @Column
    private String name;

    @Transient
    private List<String> categories;

    @Column
    private String title;

    public Item()
    {
    }

    public Item(long id, String name, List<String> categories, String title)
    {
        this.id = id;
        this.name = name;
        this.categories = categories;
        this.title = title;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<String> getCategories()
    {
        return categories;
    }

    public void setCategories(List<String> categories)
    {
        this.categories = categories;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    @Override
    public String toString()
    {
        return "Item\n" +
                "{\n" +
                    "\tid=" + id + ",\n" +
                    "\tname='" + name + '\'' + ",\n" +
                    "\ttitle='" + title + '\'' + "\n" +
                "}\n";
    }
}
