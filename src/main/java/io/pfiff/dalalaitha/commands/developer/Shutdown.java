package io.pfiff.dalalaitha.commands.developer;

import io.pfiff.dalalaitha.commands.Command;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Shutdown implements Command {
    @Override
    public String getName() {
        return "shutdown";
    }

    @Override
    public void invoke(GuildMessageReceivedEvent event, String args) {
        if (event.getAuthor().getIdLong() == 837650460387180544L) {
            event.getChannel().sendMessage("ты пидорас").queue(m1 -> {
                event.getChannel().sendMessage("сука").queue(m2 -> {
                    event.getChannel().sendMessage("гандон").queue(m3 -> {
                        event.getJDA().shutdownNow();
                        System.exit(0);
                    });
                });
            });
        } else event.getChannel().sendMessage("ur sucker hahahaha").queue();
    }
}
