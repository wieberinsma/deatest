package httpclient.services;

import httpclient.services.dtos.Todo;
import httpclient.services.mappers.TodoMapper;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class JsonPlaceholderService
{
    private final HttpClient jsonPlaceholderClient = HttpClient.newHttpClient();

    private static final String JSON_PLACEHOLDER_URI = "https://jsonplaceholder.typicode.com/todos";

    private final TodoMapper todoMapper;

    public JsonPlaceholderService()
    {
        todoMapper = new TodoMapper();
    }

    public CompletableFuture<HttpResponse<String>> getTodos()
    {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(JSON_PLACEHOLDER_URI))
                .build();

        return jsonPlaceholderClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
    }

    public void getTodosWithCallback(Consumer<String> callback)
    {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(JSON_PLACEHOLDER_URI))
                .build();

        jsonPlaceholderClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenAccept(response -> callback.accept(response.body()));
    }

    public void createNewTodoItemOnServer(Consumer<String> callback)
    {
        getTodos().thenAccept(getResponse ->
        {
            Todo[] todos = todoMapper.mapToJava(getResponse.body());
            String newTodoJson = todoMapper.mapToJson(createNewTodoItem(todos.length + 1));

            postRequestWithCallback(newTodoJson, callback);
        });
    }

    private void postRequestWithCallback(String newTodoJson, Consumer<String> callback)
    {
        HttpRequest postRequest = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(newTodoJson))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .uri(URI.create(JSON_PLACEHOLDER_URI))
                .build();

        jsonPlaceholderClient.sendAsync(postRequest, HttpResponse.BodyHandlers.ofString())
                .thenAccept(postResponse -> callback.accept(postResponse.body()));
    }

    private Todo createNewTodoItem(int id)
    {
        var todo = new Todo();
        todo.setId(id);
        todo.setCompleted(true);
        todo.setTitle("Finished the DEA Exercise!");
        todo.setUserId(2);
        return todo;
    }
}
