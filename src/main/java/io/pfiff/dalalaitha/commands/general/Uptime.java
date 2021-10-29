package io.pfiff.dalalaitha.commands.general;

import io.pfiff.dalalaitha.Config;
import io.pfiff.dalalaitha.commands.Category;
import io.pfiff.dalalaitha.commands.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.lang.management.ManagementFactory;

public class Uptime implements Command {

    @Override
    public String getName() {
        return "uptime";
    }

    @Override
    public String getDescription() {
        return "Send bot's uptime";
    }

    @Override
    public Category getCategory() {
        return Category.GENERAL;
    }

    @Override
    public void invoke(GuildMessageReceivedEvent event, String args) {
        var runtime = ManagementFactory.getRuntimeMXBean().getUptime();
        var secs = runtime / 1000;
        var h = (int) (secs / 3600);
        var m = (int) ((secs - (h * 3600)) / 60);
        var s = (int) (secs - (h * 3600) - m * 60);
        var embed = new EmbedBuilder()
                .setColor(Config.SUCCESSFUL)
                .setTitle("Uptime")
                .appendDescription(String.format("%02d:%02d:%02d", h,m,s))
                .build();
        event.getChannel().sendMessageEmbeds(embed).queue();
    }
}
