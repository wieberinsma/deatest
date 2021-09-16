package httpclient;

import httpclient.services.ConsolePrinterService;
import httpclient.services.GitHubService;
import httpclient.services.JsonPlaceholderService;

import java.util.Scanner;

public class DeaConsoleBrowser
{
    private GitHubService gitHubService;
    private JsonPlaceholderService jsonPlaceholderService;
    private ConsolePrinterService consolePrinterService;

    public static void main(String[] args)
    {
        var consoleBrowser = new DeaConsoleBrowser();
        consoleBrowser.setGitHubService(new GitHubService());
        consoleBrowser.setJsonPlaceholderService(new JsonPlaceholderService());
        consoleBrowser.setConsolePrinterService(new ConsolePrinterService());

        consoleBrowser.start();
    }

    private void start()
    {
        var keyboard = new Scanner(System.in);
        var exit = false;

        consolePrinterService.printLogo();
        consolePrinterService.printSelectionScreen();

        while (!exit)
        {
            var input = keyboard.nextLine();
            if (input != null)
            {
                consolePrinterService.printOutputLine("You have selected : " + input);
                switch (input)
                {
                    case "q":
                        consolePrinterService.printOutputLine("Exiting program");
                        exit = true;
                        break;
                    case "1":
                        consolePrinterService.printResponse(gitHubService.getIndexHtml());
                        consolePrinterService.printSelectionScreen();
                        break;
                    case "2":
                        consolePrinterService.printResponse(gitHubService.getReadme());
                        consolePrinterService.printSelectionScreen();
                        break;
                    case "3":
                        jsonPlaceholderService.getTodos().thenAccept(response -> System.out.println(response.body()));
                        consolePrinterService.printSelectionScreen();
                        break;
                    case "4":
                        jsonPlaceholderService.getTodosWithCallback(
                                response -> consolePrinterService.printResponse(response));
                        consolePrinterService.printSelectionScreen();
                        break;
                    case "5":
                        jsonPlaceholderService.getTodosWithCallback(response ->
                        {
                            consolePrinterService.printResponse(response);
                            consolePrinterService.printSelectionScreen();
                        });
                        break;
                    case "6":
                        jsonPlaceholderService.createNewTodoItemOnServer(response ->
                        {
                            consolePrinterService.printResponse(response);
                            consolePrinterService.printSelectionScreen();
                        });
                        break;
                    case "7":
                        jsonPlaceholderService.runCompletableFuture(response ->
                        {
                            consolePrinterService.printResponse("Your total price is: " + response);
                            consolePrinterService.printSelectionScreen();
                        });
                        break;
                }
            }
        }

        keyboard.close();
    }

    public void setJsonPlaceholderService(JsonPlaceholderService jsonPlaceholderService)
    {
        this.jsonPlaceholderService = jsonPlaceholderService;
    }

    public void setGitHubService(GitHubService gitHubService)
    {
        this.gitHubService = gitHubService;
    }

    public void setConsolePrinterService(ConsolePrinterService consolePrinterService)
    {
        this.consolePrinterService = consolePrinterService;
    }
}
