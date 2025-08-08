package fr.thefox580.theevent5802.commands;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.ColorType;
import fr.thefox580.theevent5802.utils.Variables;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class VariablesCommand implements CommandExecutor, TabCompleter {

    private final TheEvent580_2 plugin;

    public VariablesCommand(TheEvent580_2 plugin){
        this.plugin = plugin;
        Objects.requireNonNull(plugin.getCommand("variables")).setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {

        switch (strings[0]) {
            case "save" -> {
                plugin.getInstances().getVariablesDatabase().addVariables();
                commandSender.sendMessage(Component.text("Variables have been correctly saved!", ColorType.MC_LIME.getColor()));
            }
            case "load" -> {
                plugin.getInstances().getVariablesDatabase().getVariables(true);
                commandSender.sendMessage(Component.text("Variables have been correctly loaded!", ColorType.MC_LIME.getColor()));
            }
            case "get" -> {
                commandSender.sendMessage(Component.text("\nHere are all of your variables:"));
                Map<String, Object> variables = Variables.getVariables();
                variables.forEach((String name, Object value) -> {
                    commandSender.sendMessage(Component.text(name + " : " + value));

                });
            }
            case "set" -> {
                if (strings.length != 4) {
                    commandSender.sendMessage(Component.text("This variable could not be saved!", ColorType.MC_RED.getColor()));
                } else {
                    switch (strings[3]) {
                        case "int" -> Variables.setVariable(strings[1], Integer.valueOf(strings[2]));
                        case "double" -> Variables.setVariable(strings[1], Double.valueOf(strings[2]));
                        case "float" -> Variables.setVariable(strings[1], Float.valueOf(strings[2]));
                        case "long" -> Variables.setVariable(strings[1], Long.valueOf(strings[2]));
                        case "bool" -> Variables.setVariable(strings[1], Boolean.valueOf(strings[2]));
                        default -> Variables.setVariable(strings[1], strings[2]);
                    }
                    commandSender.sendMessage(Component.text("Variable  \"" + strings[1] + "\" has correctly been saved!", ColorType.MC_LIME.getColor()));
                }
            }
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String @NotNull [] args) {
        List<String> tab = new ArrayList<>();

        if (args.length == 1) {
            tab.add("save");
            tab.add("load");
            tab.add("get");
            tab.add("set");
        } else if (args[0].equals("set")){
            if (args.length == 2) {
                tab.addAll(Variables.getVariables().keySet());
            } else if (args.length == 4){
                tab.add("int");
                tab.add("bool");
                tab.add("double");
                tab.add("long");
                tab.add("float");
                tab.add("str");
            }
        }
        return tab.stream().filter(completion -> completion.toLowerCase().startsWith(args[args.length - 1].toLowerCase())).toList();
    }

}
