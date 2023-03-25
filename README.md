# Croissant
 A Java library that simplifies the creation of Telegram Bots

# Installation
- Clone this repository with `git clone https://github.com/paxolo/croissant.git`
- Build it with `mvn clean install`
- Add the following code to your `pom.xml`:
```xml
<dependency>
  <groupId>me.stefano</groupId>
  <artifactId>croissant</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```

# Creating commands
Croissant uses annotations to simplify the creation of commands.
In this example, we will create a simple 'hello' command, that makes the bot print 'Hi there!' to the console when someone invokes it.

### The @Command annotation

The @Command annotation is used to specify the following properties:
- label, _must_ be specified
- aliases (optional), defaults to an empty array of strings
- description (optional), defaults to "Not available."

```java
@Command("mycommand")
// @Command(value = "mycommand", aliases = { "mycmd" })
// @Command(value = "mycommand", description = "Example command.")
// @Command(value = "mycommand", aliases = { "mycmd" }, description = "Example command.")
public static class MyCommand implements CommandExecutor {

    @Override
    public void execute(Update update) {
        System.out.println("Hi there!");
    }
    
}
```

### Using Croissant

In order to tell the bot how to handle our commands, we need to create an instance of Croissant and use it to register them.
To do that, you will need to edit the Bot class as follows:
- override the onRegister() method
- create an instance of Croissant
- inside the onUpdateReceived() method, call the handle() method provided by the library

```java
public class MyBot extends TelegramLongPollingBot {

    private Croissant croissant;
    
    // ...
    
    @Override
    public void onRegister() {
        this.croissant = new Croissant();
    }

    @Override
    public void onUpdateReceived(Update update) {
        this.croissant.handle(update);
    }

    // ...

}
```

### Registering commands

You can use the registerCommand() method after creating an instance of the library to allow Croissant to handle commands.

```java
public class MyBot extends TelegramLongPollingBot {
        
    // ...
    
    @Override
    public void onRegister() {
        this.croissant = new Croissant();
        
        this.croissant.registerCommand(new MyCommand());
    }
    
    // ...
    
}
```

### The @CommandRetention annotation

Some commands just don't belong to private chats. On the contrary, some others don't belong to groups.
The @CommandRetention annotations allows you to specify the context a command is supposed to be used in.

By default, commands can be used both in private and in group chats.
Let's edit the 'hello' command we created earlier so that it can only be executed in private chats.

```java
@Command("mycommand")
@CommandRetention(CommandPolicy.PRIVATE)
// @CommandRetention(CommandPolicy.GROUPS)
public static class MyCommand implements CommandExecutor {

    @Override
    public void execute(Update update) {
        System.out.println("Hi there!");
    }
    
}
```