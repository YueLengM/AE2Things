package com.asdflj.ae2thing.nei;

import java.util.ArrayList;
import java.util.List;

import com.asdflj.ae2thing.AE2Thing;
import com.asdflj.ae2thing.Tags;
import com.asdflj.ae2thing.client.gui.GuiCraftingTerminal;
import com.asdflj.ae2thing.client.gui.GuiInfusionPatternTerminal;
import com.asdflj.ae2thing.client.gui.GuiInterfaceWireless;
import com.glodblock.github.nei.recipes.FluidRecipe;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;

@SuppressWarnings("unused")
public class NEI_TH_Config implements IConfigureNEI {

    @Override
    public void loadConfig() {
        API.registerNEIGuiHandler(new AE2TH_NEIGuiHandler());
        List<String> recipes = new ArrayList<>();
        recipes.add("crafting");
        recipes.add("crafting2x2");
        for (String identifier : recipes) {
            // that NEE handlers take priority
            if (!API.hasGuiOverlayHandler(GuiCraftingTerminal.class, identifier)) {
                API.registerGuiOverlayHandler(
                    GuiCraftingTerminal.class,
                    FluidCraftingTransferHandler.INSTANCE,
                    identifier);
            }
        }
        recipes.clear();
        recipes.add("infusionCrafting");
        recipes.add("cruciblerecipe");
        for (String identifier : recipes) {
            if (!API.hasGuiOverlayHandler(GuiInfusionPatternTerminal.class, identifier)) {
                API.registerGuiOverlayHandler(
                    GuiInfusionPatternTerminal.class,
                    FluidPatternTerminalRecipeTransferHandler.INSTANCE,
                    identifier);
            }
        }
        for (String identifier : FluidRecipe.getSupportRecipes()) {
            if (!API.hasGuiOverlayHandler(GuiInterfaceWireless.class, identifier)) {
                API.registerGuiOverlayHandler(
                    GuiInterfaceWireless.class,
                    FluidPatternTerminalRecipeTransferHandler.INSTANCE,
                    identifier);
            }
        }
    }

    @Override
    public String getName() {
        return AE2Thing.NAME;
    }

    @Override
    public String getVersion() {
        return Tags.VERSION;
    }
}
