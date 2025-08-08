package fr.thefox580.theevent5802.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.CustomModelDataComponent;

import java.util.List;

public class FlagHead {

    public static ItemStack getPrideFlag(){
        ItemStack prideFlag = new ItemStack(Material.CARROT_ON_A_STICK);
        ItemMeta prideFlagMeta = prideFlag.getItemMeta();
        CustomModelDataComponent prideFlagCustomModel = prideFlagMeta.getCustomModelDataComponent();
        prideFlagCustomModel.setStrings(List.of("pride_flag"));
        prideFlagMeta.setCustomModelDataComponent(prideFlagCustomModel);
        prideFlagMeta.displayName(Component.text("Pride Flag", ColorType.PRIDE.getColor()).decoration(TextDecoration.ITALIC, false));
        prideFlag.setItemMeta(prideFlagMeta);

        return prideFlag;
    }

    public static ItemStack getAsexualFlag(){
        ItemStack asexualFlag = new ItemStack(Material.CARROT_ON_A_STICK);
        ItemMeta asexualFlagMeta = asexualFlag.getItemMeta();
        CustomModelDataComponent asexualFlagCustomModel = asexualFlagMeta.getCustomModelDataComponent();
        asexualFlagCustomModel.setStrings(List.of("asexual_flag"));
        asexualFlagMeta.setCustomModelDataComponent(asexualFlagCustomModel);
        asexualFlagMeta.displayName(Component.text("Asexual Flag", ColorType.ASEXUAL.getColor()).decoration(TextDecoration.ITALIC, false));
        asexualFlag.setItemMeta(asexualFlagMeta);

        return asexualFlag;
    }

    public static ItemStack getPansexualFlag(){
        ItemStack pansexualFlag = new ItemStack(Material.CARROT_ON_A_STICK);
        ItemMeta pansexualFlagMeta = pansexualFlag.getItemMeta();
        CustomModelDataComponent pansexualFlagCustomModel = pansexualFlagMeta.getCustomModelDataComponent();
        pansexualFlagCustomModel.setStrings(List.of("pansexual_flag"));
        pansexualFlagMeta.setCustomModelDataComponent(pansexualFlagCustomModel);
        pansexualFlagMeta.displayName(Component.text("Pansexual Flag", ColorType.PANSEXUAL.getColor()).decoration(TextDecoration.ITALIC, false));
        pansexualFlag.setItemMeta(pansexualFlagMeta);

        return pansexualFlag;
    }

    public static ItemStack getTransgenderFlag(){
        ItemStack transgenderFlag = new ItemStack(Material.CARROT_ON_A_STICK);
        ItemMeta transgenderFlagMeta = transgenderFlag.getItemMeta();
        CustomModelDataComponent transgenderFlagCustomModel = transgenderFlagMeta.getCustomModelDataComponent();
        transgenderFlagCustomModel.setStrings(List.of("transgender_flag"));
        transgenderFlagMeta.setCustomModelDataComponent(transgenderFlagCustomModel);
        transgenderFlagMeta.displayName(Component.text("Transgender Flag", ColorType.TRANSGENDER.getColor()).decoration(TextDecoration.ITALIC, false));
        transgenderFlag.setItemMeta(transgenderFlagMeta);

        return transgenderFlag;
    }

    public static ItemStack getLesbianFlag(){
        ItemStack lesbianFlag = new ItemStack(Material.CARROT_ON_A_STICK);
        ItemMeta lesbianFlagMeta = lesbianFlag.getItemMeta();
        CustomModelDataComponent lesbianFlagCustomModel = lesbianFlagMeta.getCustomModelDataComponent();
        lesbianFlagCustomModel.setStrings(List.of("lesbian_flag"));
        lesbianFlagMeta.setCustomModelDataComponent(lesbianFlagCustomModel);
        lesbianFlagMeta.displayName(Component.text("Lesbian Flag", ColorType.LESBIAN.getColor()).decoration(TextDecoration.ITALIC, false));
        lesbianFlag.setItemMeta(lesbianFlagMeta);

        return lesbianFlag;
    }

    public static ItemStack getNonBinaryFlag(){
        ItemStack nonBinaryFlag = new ItemStack(Material.CARROT_ON_A_STICK);
        ItemMeta nonBinaryFlagMeta = nonBinaryFlag.getItemMeta();
        CustomModelDataComponent nonBinaryFlagCustomModel = nonBinaryFlagMeta.getCustomModelDataComponent();
        nonBinaryFlagCustomModel.setStrings(List.of("non_binary_flag"));
        nonBinaryFlagMeta.setCustomModelDataComponent(nonBinaryFlagCustomModel);
        nonBinaryFlagMeta.displayName(Component.text("Non-Binary Flag", ColorType.NON_BINARY.getColor()).decoration(TextDecoration.ITALIC, false));
        nonBinaryFlag.setItemMeta(nonBinaryFlagMeta);

        return nonBinaryFlag;
    }

    public static ItemStack getGayFlag(){
        ItemStack gayFlag = new ItemStack(Material.CARROT_ON_A_STICK);
        ItemMeta gayFlagMeta = gayFlag.getItemMeta();
        CustomModelDataComponent gayFlagCustomModel = gayFlagMeta.getCustomModelDataComponent();
        gayFlagCustomModel.setStrings(List.of("trans_inclusive_gay_men_flag"));
        gayFlagMeta.setCustomModelDataComponent(gayFlagCustomModel);
        gayFlagMeta.displayName(Component.text("Trans-Inclusive Gay Men Flag", ColorType.GAY.getColor()).decoration(TextDecoration.ITALIC, false));
        gayFlag.setItemMeta(gayFlagMeta);

        return gayFlag;
    }

    public static ItemStack getBisexualFlag(){
        ItemStack bisexualFlag = new ItemStack(Material.CARROT_ON_A_STICK);
        ItemMeta bisexualFlagMeta = bisexualFlag.getItemMeta();
        CustomModelDataComponent bisexualFlagCustomModel = bisexualFlagMeta.getCustomModelDataComponent();
        bisexualFlagCustomModel.setStrings(List.of("bisexual_flag"));
        bisexualFlagMeta.setCustomModelDataComponent(bisexualFlagCustomModel);
        bisexualFlagMeta.displayName(Component.text("Bisexual Flag", ColorType.BISEXUAL.getColor()).decoration(TextDecoration.ITALIC, false));
        bisexualFlag.setItemMeta(bisexualFlagMeta);

        return bisexualFlag;
    }

    public static ItemStack getUnlabledFlag(){
        ItemStack unlabledFlag = new ItemStack(Material.CARROT_ON_A_STICK);
        ItemMeta unlabledFlagMeta = unlabledFlag.getItemMeta();
        CustomModelDataComponent unlabledFlagCustomModel = unlabledFlagMeta.getCustomModelDataComponent();
        unlabledFlagCustomModel.setStrings(List.of("unlabled_flag"));
        unlabledFlagMeta.setCustomModelDataComponent(unlabledFlagCustomModel);
        unlabledFlagMeta.displayName(Component.text("Unlabled Flag", ColorType.RAINBOW.getColor()).decoration(TextDecoration.ITALIC, false));
        unlabledFlag.setItemMeta(unlabledFlagMeta);

        return unlabledFlag;
    }

    public static ItemStack getAromanticFlag(){
        ItemStack aromanticFlag = new ItemStack(Material.CARROT_ON_A_STICK);
        ItemMeta aromanticFlagMeta = aromanticFlag.getItemMeta();
        CustomModelDataComponent aromanticFlagCustomModel = aromanticFlagMeta.getCustomModelDataComponent();
        aromanticFlagCustomModel.setStrings(List.of("aromantic_flag"));
        aromanticFlagMeta.setCustomModelDataComponent(aromanticFlagCustomModel);
        aromanticFlagMeta.displayName(Component.text("Aromantic Flag", ColorType.AROMANTIC.getColor()).decoration(TextDecoration.ITALIC, false));
        aromanticFlag.setItemMeta(aromanticFlagMeta);

        return aromanticFlag;
    }

}
