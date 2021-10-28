package io.pfiff.dalalaitha.commands.fun;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Penis implements io.pfiff.dalalaitha.commands.Command {
    @Override
    public String getName() {
        return "penis";
    }

    @Override
    public void invoke(GuildMessageReceivedEvent event, String args) {
        event.getChannel().sendMessage("твою мать ебли толстые запорожские").queue();
    }
}
