package com.asdflj.ae2thing.api.adapter.item.terminal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import com.asdflj.ae2thing.util.BaublesUtil;
import com.asdflj.ae2thing.util.ModAndClassUtil;
import com.glodblock.github.common.item.ItemWirelessUltraTerminal;
import com.glodblock.github.inventory.gui.GuiType;
import com.glodblock.github.util.NameConst;

import appeng.util.Platform;

public class FCUltraTerminal implements IItemTerminal {

    @Override
    public List<Class<? extends Item>> getClasses() {
        return Arrays.asList(ItemWirelessUltraTerminal.class);
    }

    @Override
    public boolean supportBaubles() {
        return true;
    }

    @Override
    public List<TerminalItems> getTerminalItems() {
        List<TerminalItems> terminal = new ArrayList<>(getMainInvTerminals());
        if (ModAndClassUtil.BAUBLES) {
            IInventory handler = BaublesUtil.getBaublesInv(player());
            if (handler != null) {
                terminal.addAll(getInvTerminals(handler));
            }
        }
        return terminal;
    }

    @Override
    public List<TerminalItems> getMainInvTerminals() {
        List<TerminalItems> terminal = new ArrayList<>();
        for (int i = 0; i < player().inventory.mainInventory.length; ++i) {
            ItemStack item = player().inventory.getStackInSlot(i);
            terminal.addAll(getTerminalItems(item));
        }
        return terminal;
    }

    private List<TerminalItems> getTerminalItems(ItemStack source) {
        List<TerminalItems> terminal = new ArrayList<>();
        if (source != null && source.getItem() instanceof ItemWirelessUltraTerminal terminalItem) {
            List<GuiType> guis = ItemWirelessUltraTerminal.getGuis();
            for (GuiType guiType : guis) {
                ItemStack t = source.copy();
                terminalItem.setNext(guiType, t);
                NBTTagCompound data = Platform.openNbtData(t);
                if (data.hasKey("display")) {
                    terminal.add(
                        new TerminalItems(
                            source,
                            t,
                            t.getDisplayName() + " "
                                + I18n.format(NameConst.TT_ULTRA_TERMINAL + "." + terminalItem.guiGuiType(t))));
                } else {
                    terminal.add(new TerminalItems(source, t));
                }
            }
        }
        return terminal;
    }

    @Override
    public List<TerminalItems> getInvTerminals(IInventory handler) {
        List<TerminalItems> terminal = new ArrayList<>();
        for (int i = 0; i < handler.getSizeInventory(); ++i) {
            ItemStack item = handler.getStackInSlot(i);
            terminal.addAll(getTerminalItems(item));
        }
        return terminal;
    }
}
