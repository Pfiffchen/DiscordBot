package io.pfiff.dalalaitha.commands.info;

import io.pfiff.dalalaitha.Config;
import io.pfiff.dalalaitha.commands.Category;
import io.pfiff.dalalaitha.commands.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.Optional;

public class ServerInfo implements Command {
    @Override
    public String getName() {
        return "serverinfo";
    }

    @Override
    public String getDescription() {
        return "Sends the server detailed information";
    }

    @Override
    public Category getCategory() {
        return Category.INFO;
    }

    @Override
    public void invoke(GuildMessageReceivedEvent event, String args) {

        if (!event.getGuild().isLoaded()) {
            event.getGuild().loadMembers().onSuccess(m -> {
                event.getChannel().sendMessageEmbeds(infoBuilder(event.getGuild())).queue();
            });
        } else {
            event.getChannel().sendMessageEmbeds(infoBuilder(event.getGuild())).queue();
        }

    }

    private MessageEmbed infoBuilder(Guild guild) {
        var features = (!guild.getFeatures().isEmpty()) ? String.join("_",guild.getFeatures()) : "None";
        return new EmbedBuilder()
                .setColor(Config.SUCCESS)
                .setThumbnail(guild.getIconUrl() + "?size=512")
                .addField("Name", guild.getName(), true)
                .addField("ID", guild.getId(), true)
                .addField("Owner", Objects.requireNonNull(guild.getOwner()).getAsMention(), true)
                .addField("Online Members", guild.getMemberCache().stream().filter(m ->
                        m.getOnlineStatus() != OnlineStatus.OFFLINE).count()+"/"+guild.getMemberCount(),true)
                .addField("Humans", guild.getMemberCache().stream().filter(m ->
                        !m.getUser().isBot()).count()+"/"+guild.getMemberCount(), true)
                .addField("Bots", guild.getMemberCache().stream().filter(m ->
                        m.getUser().isBot()).count()+"/"+guild.getMemberCount(),true)
                .addField("Channel Categories", String.valueOf(guild.getCategoryCache().size()), true)
                .addField("Text Channels", String.valueOf(guild.getTextChannelCache().size()), true)
                .addField("Voice Channels", String.valueOf(guild.getVoiceChannelCache().size()), true)
                .addField("System Channel", Optional.ofNullable(guild.getSystemChannel()).map(GuildChannel::getAsMention).orElse("None"), true)
                .addField("Rules Channel", Optional.ofNullable(guild.getRulesChannel()).map(GuildChannel::getAsMention).orElse("None"), true)
                .addField("AFK Voice Channel", Optional.ofNullable(guild.getAfkChannel()).map(GuildChannel::getAsMention).orElse("None"), true)
                .addField("Roles", Integer.toString(guild.getRoles().size()), true)
                .addField("Custom Emojis", String.valueOf(guild.getEmoteCache().size()), true)
                .addField("Creation Date", String.valueOf(guild.getTimeCreated()), true)
                .addField("Boosts", String.valueOf(guild.getBoostCount()), true)
                .addField("Boost Tier", String.format("**Level %s**: \n Bitrate: %s kbps \n Emojis: %s slots \n File Size: %s MB",
                        guild.getBoostTier().getKey(), guild.getBoostTier().getMaxBitrate() / 1000,
                        guild.getBoostTier().getMaxEmotes(), guild.getBoostTier().getMaxFileSize() >> 20), true)
                .addField("Features", features, true)
                .addField("Verification Level", StringUtils.capitalize(String.valueOf(guild.getVerificationLevel()).toLowerCase()), true )
                .addField("Notifications Level", StringUtils.capitalize(String.valueOf(guild.getDefaultNotificationLevel()).toLowerCase().replace("_", " ")), true)
                .addField("NSFW Level", guild.getNSFWLevel().name() ,true)
                .addField("MFA Requirement", guild.getRequiredMFALevel() == Guild.MFALevel.TWO_FACTOR_AUTH ? "Yes" : "No", true)
                .build();
    }
}
