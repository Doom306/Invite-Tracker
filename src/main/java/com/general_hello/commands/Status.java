package com.general_hello.commands;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Activity;

public class Status {
    public Thread status() {
        Runnable runnable = () -> {
            while (true) {
                JDA jda = Bot.jda;
                jda.getPresence().setActivity(Activity.listening("Made by " + jda.getUserById(Config.get("owner_id_partner")).getAsTag()));

                try {
                    Thread.sleep(1000000);
                } catch (Exception ignored) {
                }

                jda.getPresence().setActivity(Activity.competing("Using / commands"));

                try {
                    Thread.sleep(1000000);
                } catch (Exception ignored) {
                }
            }
        };

        return new Thread(runnable);
    }
}
