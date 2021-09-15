package httpclient.services.mappers;

import com.google.gson.Gson;
import httpclient.services.dtos.Todo;

public class TodoMapper
{
    private final Gson gson;

    public TodoMapper()
    {
        gson = new Gson();
    }

    public Todo[] mapToJava(String json)
    {
        return gson.fromJson(json, Todo[].class);
    }

    public String mapToJson(Todo dto)
    {
        return gson.toJson(dto);
    }
}
