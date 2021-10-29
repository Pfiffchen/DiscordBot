package io.pfiff.dalalaitha.commands.fun;

import io.pfiff.dalalaitha.commands.Category;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Penis implements io.pfiff.dalalaitha.commands.Command {
    @Override
    public String getName() {
        return "penis";
    }

    @Override
    public String getDescription() {
        return "Sends some funny text";
    }

    @Override
    public Category getCategory() {
        return Category.FUN;
    }

    @Override
    public void invoke(GuildMessageReceivedEvent event, String args) {
        event.getChannel().sendMessage("твою мать ебли толстые запорожские").queue();
    }
}
