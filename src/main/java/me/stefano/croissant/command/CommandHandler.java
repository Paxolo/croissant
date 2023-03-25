package me.stefano.croissant.command;

import me.stefano.croissant.command.type.Command;import me.stefano.croissant.command.type.CommandPolicy;import me.stefano.croissant.command.type.CommandRetention;import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommandHandler {

    private HashMap<String, CommandExecutor> commandMap;
    public CommandHandler() {
        this.commandMap = new HashMap<>();
    }

    public void register(CommandExecutor executor) {
        var commandAnnotation = executor.getClass().getAnnotation(Command.class);
        if (commandAnnotation == null) {
            System.out.println("An error has occurred registering command " + executor.getClass().getName() + ".");
            System.out.println("Make sure to correctly use the @Command annotation!");
            return;
        }

        var labels = new ArrayList<String>();
        labels.add(commandAnnotation.value());
        labels.addAll(List.of(commandAnnotation.aliases()));
        labels.forEach(label -> this.commandMap.put(label, executor));
    }

    public void handle(Update update) {
        var message = update.getMessage();
        var text = message.getText();
        var label = text.split(" ")[0].substring(1);
        var chatId = message.getChatId();

        var retentionAnnotation = this.commandMap.get(label).getClass().getAnnotation(CommandRetention.class);
        if (retentionAnnotation != null) {
            var retention = retentionAnnotation.value();
            if (
                    retention == CommandPolicy.PRIVATE && chatId < 0 ||
                    retention == CommandPolicy.GROUPS && chatId >= 0
            ) return;
        }

        this.commandMap.getOrDefault(label, null).execute(update);
    }

}
