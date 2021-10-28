package io.pfiff.dalalaitha;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;


public class Main {
    public static void main(String[] args) throws Exception {
        JDA api = JDABuilder.createDefault(Config.TOKEN).addEventListeners(new CommandHandler())
                .setActivity(Activity.watching("gay porn")).build();
    }
}

