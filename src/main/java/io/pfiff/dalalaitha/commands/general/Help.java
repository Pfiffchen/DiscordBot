package io.pfiff.dalalaitha.commands.general;

import io.pfiff.dalalaitha.CommandHandler;
import io.pfiff.dalalaitha.Config;
import io.pfiff.dalalaitha.commands.Category;
import io.pfiff.dalalaitha.commands.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.SelfUser;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.apache.commons.lang3.StringUtils;

import java.util.TreeMap;
import java.util.stream.Collectors;

public class Help implements Command {
    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Sends the list of commands or provides the user with help for the specified command";
    }

    @Override
    public Category getCategory() {
        return Category.GENERAL;
    }

    @Override
    public void invoke(GuildMessageReceivedEvent event, String args) {
        if (args.isEmpty()) {
            event.getChannel().sendMessageEmbeds(getCommandList(event.getJDA().getSelfUser())).queue();
            return;
        }

        var embed = new EmbedBuilder()
                .setColor(Config.FAILURE)
                .setAuthor("Failure!")
                .setDescription("No command has been found by the query!")
                .build();

        var command = CommandHandler.COMMANDS.stream().filter(c -> c.getName().equalsIgnoreCase(args)).findAny();
        command.ifPresentOrElse(
                c -> event.getChannel().sendMessageEmbeds(getCommandHelp(c, event.getJDA().getSelfUser())).queue(),
                () -> event.getChannel().sendMessageEmbeds(embed).queue());
    }

    private MessageEmbed getCommandList(SelfUser bot) {
        var embed = new EmbedBuilder()
                .setAuthor(String.format("%s Help", bot.getName()), null, bot.getEffectiveAvatarUrl())
                .setColor(Config.SUCCESS);
        var grouped = CommandHandler.COMMANDS
                .stream()
                .collect(Collectors.groupingBy(c -> c.getCategory().name()));
        var sorted = new TreeMap<>(grouped);
        sorted.forEach((category, commands) -> {
            embed.addField(StringUtils.capitalize(category.toLowerCase()) + " Commands",
                    commands.stream().map(Command::getName).sorted().collect(Collectors.joining(", ")), false);
        });
        return embed.build();
    }

    private MessageEmbed getCommandHelp(Command cmd, SelfUser bot) {
         return new EmbedBuilder()
                .setAuthor(Config.PREFIX + cmd.getName(), null, bot.getEffectiveAvatarUrl())
                .setColor(Config.SUCCESS)
                .setDescription(cmd.getDescription())
                .addField("Category", StringUtils.capitalize(cmd.getCategory().name().toLowerCase()), true).build();
    }
}
