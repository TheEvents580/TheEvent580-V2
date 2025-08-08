package fr.thefox580.theevent5802.commands;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.BossbarManager;
import fr.thefox580.theevent5802.utils.ColorTypeAlt;
import fr.thefox580.theevent5802.utils.Timer;
import fr.thefox580.theevent5802.utils.TimerEnum;
import me.clip.placeholderapi.libs.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TimerCommand implements CommandExecutor, TabCompleter {

    public TimerCommand(TheEvent580_2 plugin){
        Objects.requireNonNull(plugin.getCommand("timer")).setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        if (args.length > 0){

            switch (args[0]){
                case "pause" -> {
                    Timer.setPaused(true);
                    BossBar countBossbar = BossbarManager.getBossbar("count");
                    assert countBossbar != null;

                    BossbarManager.setBossbarProgression(countBossbar, 0f);
                    BossbarManager.setBossbarText(countBossbar, me.clip.placeholderapi.libs.kyori.adventure.text.Component.text("氣到惡", ColorTypeAlt.NO_SHADOW.getColor())
                            .append(me.clip.placeholderapi.libs.kyori.adventure.text.Component.text("--:--", ColorTypeAlt.BOSSBAR.getColor())));
                }
                case "resume" -> Timer.setPaused(false);
                case "mode" -> {
                    if (args.length == 2){
                        Timer.setEnum(TimerEnum.getEnumFromMode(Integer.parseInt(args[1])));
                    } else {
                        sender.sendMessage(Component.text("Current mode : " + Timer.getEnum().getMeaning()));
                    }
                }
                case "set" -> {
                    if (args.length == 3){
                        Timer.setSeconds(Integer.parseInt(args[1])*60+Integer.parseInt(args[2]));
                    }
                }
                default -> {
                    return false;
                }
            }
        } else {
            sender.sendMessage(Component.text("Time left on the timer : " + Timer.getFormatedTimer()));
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        List<String> tab = new ArrayList<>();

        switch (args.length) {
            case 1:
                tab.add("pause");
                tab.add("resume");
                tab.add("mode");
                tab.add("set");
                break;
            case 2:
                if (args[0].equals("mode")) {
                    for (int i = 0; i < 11; i++) {
                        tab.add(String.valueOf(i));
                    }
                } else if (args[0].equals("set")) {
                    for (int i = 0; i < 60; i++) {
                        tab.add(String.valueOf(i));
                    }
                }
                break;
            case 3:
                if (args[0].equals("set")) {
                    for (int i = 0; i < 60; i++) {
                        tab.add(String.valueOf(i));
                    }
                }
        }

        return tab.stream().filter(completion -> completion.startsWith(args[args.length - 1])).toList();
    }
}
