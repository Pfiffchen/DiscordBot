package io.pfiff.dalalaitha.commands.general;

import io.pfiff.dalalaitha.Config;
import io.pfiff.dalalaitha.commands.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.interactions.components.Button;

import java.awt.*;

public class Invite implements Command {
    @Override
    public String getName() {
        return "invite";
    }

    @Override
    public void invoke(GuildMessageReceivedEvent event, String args) {
        var button = Button.link(Config.INVITE_LINK , "Invite Link");
        event.getChannel().sendMessage(EmbedBuilder.ZERO_WIDTH_SPACE).setActionRow(button).queue();
    }
}
