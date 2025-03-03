package com.asdflj.ae2thing.api.adapter.pattern;

import java.util.List;

import net.minecraft.inventory.Container;

import com.asdflj.ae2thing.nei.object.OrderStack;
import com.asdflj.ae2thing.network.CPacketTransferRecipe;

@FunctionalInterface
public interface IRecipeHandler {

    void transferPack(Container container, List<OrderStack<?>> inputs, List<OrderStack<?>> outputs, String identifier,
        IPatternTerminalAdapter adapter, CPacketTransferRecipe message);
}
