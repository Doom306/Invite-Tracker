package com.general_hello.commands.OtherEvents;

import com.general_hello.commands.Config;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.time.OffsetDateTime;

public class OnGuildMemberJoined extends ListenerAdapter {
    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        if (event.getUser().isBot()) return;
        if (!event.getGuild().getId().equals(Config.get("guild"))) return;
        Guild guild = event.getGuild();
        TextChannel sendCodeChannel = event.getGuild().getTextChannelById(Config.get("textchannel"));
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setAuthor("Welcome to " + guild.getName(), null, guild.getIconUrl());
        embedBuilder.setDescription("**Kindly follow the [steps below](https://discord.com) to be verified.**\n\n" +
                "**1.)** Copy the code your friend sent you. (Example: `sACVlQJ`)\n" +
                "**2.)** Send it at " + sendCodeChannel.getAsMention() + " or [here](https://discord.com/channels/" + Config.get("guild") + "/" + sendCodeChannel.getId() + ")\n" +
                "**3.)** You're done!");
        embedBuilder.setColor(Color.ORANGE);
        embedBuilder.setFooter("Take note that your friend will get credit once you get verified.").setTimestamp(OffsetDateTime.now());
        event.getUser().openPrivateChannel()
                .flatMap(channel -> channel.sendMessageEmbeds(embedBuilder.build())).queue();
    }
}