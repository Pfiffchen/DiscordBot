package io.pfiff.dalalaitha;

import io.pfiff.dalalaitha.commands.*;
import io.pfiff.dalalaitha.commands.developer.*;
import io.pfiff.dalalaitha.commands.fun.*;
import io.pfiff.dalalaitha.commands.general.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CommandHandler extends ListenerAdapter{
    public static final List<Command> COMMANDS = new ArrayList<>(Arrays.asList(
            new Penis(),
            new Ping(),
            new Uptime(),
            new Invite(),
            new ServerIcon(),
            new UserAvatar(),
            new Shutdown(),
            new BotAbout(),
            new Help()
    ));
    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;

        String[] args = event.getMessage().getContentRaw().split(" ", 2);
        if ( !event.getMessage().getContentRaw().startsWith(Config.PREFIX)) { return;}
        String name = args[0].substring(Config.PREFIX.length());
        Optional<Command> cmd = COMMANDS.stream().filter(c -> c.getName().equalsIgnoreCase(name)).findFirst();
        cmd.ifPresent(command -> {
            if (command.getCategory() == Category.DEVELOPER && event.getAuthor().getIdLong() != 837650460387180544L) {
                event.getChannel().sendMessage("tvoya mat' sosala kopchenie huyi").queue();
                return;
            }
                command.invoke(event, args.length > 1 ? args[1] : "");
        });


    }
}

