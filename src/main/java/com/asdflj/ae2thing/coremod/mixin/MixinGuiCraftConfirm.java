package com.asdflj.ae2thing.coremod.mixin;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.inventory.Container;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.asdflj.ae2thing.api.AE2ThingAPI;
import com.asdflj.ae2thing.util.ModAndClassUtil;
import com.asdflj.ae2thing.util.TheUtil;

import appeng.api.storage.data.IAEItemStack;
import appeng.client.gui.AEBaseGui;
import appeng.client.gui.implementations.GuiCraftConfirm;
import appeng.container.implementations.ContainerCraftConfirm;

@Mixin(GuiCraftConfirm.class)
public abstract class MixinGuiCraftConfirm extends AEBaseGui {

    @Shadow(remap = false)
    private GuiButton start;

    public MixinGuiCraftConfirm(Container container) {
        super(container);
    }

    @Inject(method = { "actionPerformed", "func_146284_a" }, at = @At(value = "HEAD"), remap = false)
    private void actionPerformed(GuiButton btn, CallbackInfo ci) {
        if (btn == start && this.inventorySlots instanceof ContainerCraftConfirm ccc) {
            IAEItemStack item;
            item = ccc.getItemToCraft();
            if (ModAndClassUtil.THE && TheUtil.isItemCraftingAspect(item)) {
                item = TheUtil.itemCraftingAspect2FluidDrop(item);
            }
            AE2ThingAPI.instance()
                .getPinned()
                .add(item);
        }
    }
}
