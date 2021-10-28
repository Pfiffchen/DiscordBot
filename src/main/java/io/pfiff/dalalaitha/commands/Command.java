package io.pfiff.dalalaitha.commands;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public interface Command {
        String getName();
        void invoke(GuildMessageReceivedEvent event, String args);
}
