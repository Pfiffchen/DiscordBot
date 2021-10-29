package io.pfiff.dalalaitha.commands.general;

import io.pfiff.dalalaitha.Config;
import io.pfiff.dalalaitha.commands.Category;
import io.pfiff.dalalaitha.commands.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Ping implements Command {

    @Override
    public String getName() {
        return "ping";
    }

    @Override
    public Category getCategory() {
        return Category.GENERAL;
    }

    @Override
    public String getDescription() {
        return "Sends bot's response latency";
    }

    @Override
    public void invoke(GuildMessageReceivedEvent event, String args) {
        var gatewayPing = event.getJDA().getGatewayPing();
        event.getJDA().getRestPing().queue(restPing -> {
            var embed = new EmbedBuilder()
                    .setColor(Config.SUCCESSFUL)
                    .addField("Rest Ping", String.format("%d ms", restPing), true)
                    .addField("Gateway Ping", String.format("%d ms", gatewayPing), true)
                    .build();
            event.getChannel().sendMessageEmbeds(embed).queue();
        });
    }
}
