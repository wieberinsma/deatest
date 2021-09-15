package httpclient.services;

import httpclient.services.dtos.Todo;
import httpclient.services.mappers.TodoMapper;

import java.util.function.Consumer;

public class JsonPlaceholderService
{
    private final TodoMapper todoMapper;

    public JsonPlaceholderService()
    {
        todoMapper = new TodoMapper();
    }

    public void getTodos()
    {

    }

    public void getTodosWithCallback(Consumer<String> callback)
    {

    }

    public void createNewTodoItemOnServer(Consumer<String> callback)
    {

    }

    private Todo createNewTodoItem(int id)
    {
        var todo = new Todo();
        todo.setId(id);
        todo.setCompleted(false);
        todo.setTitle("Finish the DEA Exercise");
        todo.setUserId(2);
        return todo;
    }
}
