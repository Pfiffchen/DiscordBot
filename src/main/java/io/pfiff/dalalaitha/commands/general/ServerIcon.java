package io.pfiff.dalalaitha.commands.general;

import io.pfiff.dalalaitha.Config;
import io.pfiff.dalalaitha.commands.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class ServerIcon implements Command {
    @Override
    public String getName() {
        return "servericon";
    }

    @Override
    public void invoke(GuildMessageReceivedEvent event, String args) {
        var icon = event.getGuild().getIconUrl();
        if (icon != null) {
            icon += "?size=2048";
            var embed = new EmbedBuilder()
                    .setColor(Config.ARCTIC_COLOR)
                    .setImage(icon)
                    .setAuthor(event.getGuild().getName(), null, icon)
                    .appendDescription(String.format("**[Server Icon URL](%s)**", icon))
                    .build();
            event.getChannel().sendMessageEmbeds(embed).queue();
        }
    }
}
