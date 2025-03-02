package com.asdflj.ae2thing.api.adapter.item.terminal;

import java.util.List;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;

import com.asdflj.ae2thing.util.BaublesUtil;
import com.asdflj.ae2thing.util.ModAndClassUtil;
import com.glodblock.github.common.item.ItemWirelessUltraTerminal;
import com.glodblock.github.inventory.InventoryHandler;
import com.glodblock.github.inventory.gui.GuiType;
import com.glodblock.github.util.BlockPos;
import com.glodblock.github.util.Util;

import appeng.util.Platform;

public class UltraTerminalHandler implements ITerminalHandler {

    private static final List<GuiType> guis = ItemWirelessUltraTerminal.getGuis();

    @Override
    public void openGui(ItemStack item, ITerminalHandler terminal, TerminalItems terminalItems, EntityPlayerMP player) {
        if (item == null) return;
        if (item.getItem() instanceof ItemWirelessUltraTerminal itemWirelessTerminal) {
            for (int i = 0; i < player.inventory.mainInventory.length; i++) {
                ItemStack stack = player.inventory.getStackInSlot(i);
                if (Platform.isSameItemPrecise(stack, item)) {
                    itemWirelessTerminal
                        .setMode(ItemWirelessUltraTerminal.readMode(terminalItems.getTargetItem()), stack);
                    openGui(player, Util.GuiHelper.encodeType(i, Util.GuiHelper.InvType.PLAYER_INV), stack);
                    return;
                }
            }
            if (!ModAndClassUtil.BAUBLES) return;
            IInventory handler = BaublesUtil.getBaublesInv(player);
            if (handler == null) return;
            for (int i = 0; i < handler.getSizeInventory(); ++i) {
                ItemStack is = handler.getStackInSlot(i);
                if (Platform.isSameItemPrecise(is, item)) {
                    itemWirelessTerminal.setMode(ItemWirelessUltraTerminal.readMode(terminalItems.getTargetItem()), is);
                    openGui(player, Util.GuiHelper.encodeType(i, Util.GuiHelper.InvType.PLAYER_BAUBLES), is);
                    return;
                }
            }
        }
    }

    private void openGui(EntityPlayerMP player, int x, ItemStack is) {
        GuiType type = ItemWirelessUltraTerminal.readMode(is);
        InventoryHandler.openGui(
            player,
            player.worldObj,
            new BlockPos(x, Util.GuiHelper.encodeType(guis.indexOf(type), Util.GuiHelper.GuiType.ITEM), 1),
            ForgeDirection.UNKNOWN,
            type);
    }
}
