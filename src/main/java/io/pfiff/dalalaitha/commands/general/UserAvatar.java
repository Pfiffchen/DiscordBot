package io.pfiff.dalalaitha.commands.general;

import io.pfiff.dalalaitha.Config;
import io.pfiff.dalalaitha.commands.Category;
import io.pfiff.dalalaitha.commands.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class UserAvatar implements Command {
    @Override
    public String getName() {
        return "useravatar";
    }

    @Override
    public String getDescription() {
        return "Sends user's avatar";
    }

    @Override
    public Category getCategory() {
        return Category.GENERAL;
    }

    @Override
    public void invoke(GuildMessageReceivedEvent event, String args) {
        var avatar = event.getAuthor().getEffectiveAvatarUrl() + "?size=2048";
        var embed = new EmbedBuilder()
                .setColor(Config.SUCCESSFUL)
                .setImage(avatar)
                .setAuthor(event.getAuthor().getAsTag(), null, avatar)
                .appendDescription(String.format("**[Profile Picture URL](%s)**", avatar))
                .build();
        event.getChannel().sendMessageEmbeds(embed).queue();
    }
}
