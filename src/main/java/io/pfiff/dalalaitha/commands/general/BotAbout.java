package io.pfiff.dalalaitha.commands.general;

import io.pfiff.dalalaitha.Config;
import io.pfiff.dalalaitha.commands.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDAInfo;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.lang.management.ManagementFactory;

import static io.pfiff.dalalaitha.CommandHandler.COMMANDS;

public class BotAbout implements Command {
    @Override
    public String getName() {
        return "about";
    }

    @Override
    public void invoke(GuildMessageReceivedEvent event, String args) {
        var thumbnail = event.getJDA().getSelfUser().getEffectiveAvatarUrl();
        var icon = event.getJDA().getSelfUser().getEffectiveAvatarUrl();
        var gatewayPing = event.getJDA().getGatewayPing();
        var runtime = ManagementFactory.getRuntimeMXBean().getUptime();
        var secs = runtime / 1000;
        var h = (int) (secs / 3600);
        var m = (int) ((secs - (h * 3600)) / 60);
        var s = (int) (secs - (h * 3600) - m * 60);

        event.getJDA().retrieveApplicationInfo().queue(appInfo -> {
            event.getJDA().getRestPing().queue(restPing -> {
                var embed = new EmbedBuilder()
                        .setThumbnail(thumbnail + "?size=512")
                        .setColor(Config.ARCTIC_COLOR)
                        .setAuthor(event.getJDA().getSelfUser().getName(), null, icon)
                        .appendDescription(event.getJDA().getSelfUser().getName() + " is a modern Discord multi-purpose 100% Kotlin-coded bot that currently relies mostly on its musical functionality\n" + "[Invite Link](" + Config.INVITE_LINK + ")")
                        .addField("Owner:", appInfo.getOwner().getAsTag(), false)
                        .addField("JDA Version:", JDAInfo.VERSION, false)
                        .addField("Java Version:", System.getProperty("java.version"), false)
                        .addField("Total Commands:", Integer.toString(COMMANDS.size()), false)
                        .addField("Servers:", Integer.toString(event.getJDA().getGuilds().size()), false)
                        .addField("Uptime:", String.format("%02d:%02d:%02d", h,m,s), false)
                        .addField("Rest Ping:", String.format("%d ms", restPing), false)
                        .addField("Gateway Ping:", String.format("%d ms", gatewayPing), false)
                        .build();
                event.getChannel().sendMessageEmbeds(embed).queue();
            });
        });
    }
}