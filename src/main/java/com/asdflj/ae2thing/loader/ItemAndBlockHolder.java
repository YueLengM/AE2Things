package com.asdflj.ae2thing.loader;

import static com.asdflj.ae2thing.common.item.ItemCreativeFluidCell.lava_bucket;
import static com.asdflj.ae2thing.common.item.ItemCreativeFluidCell.water_bucket;

import com.asdflj.ae2thing.common.block.BlockEssentiaDiscretizer;
import com.asdflj.ae2thing.common.block.BlockFishBig;
import com.asdflj.ae2thing.common.block.BlockInfusionInterface;
import com.asdflj.ae2thing.common.item.ItemBackpackTerminal;
import com.asdflj.ae2thing.common.item.ItemCreativeFluidCell;
import com.asdflj.ae2thing.common.item.ItemInfinityStorageCell;
import com.asdflj.ae2thing.common.item.ItemInfinityStorageFluidCell;
import com.asdflj.ae2thing.common.item.ItemPartInfusionPatternTerminal;
import com.asdflj.ae2thing.common.item.ItemPartThaumatoriumInterface;
import com.asdflj.ae2thing.common.item.ItemPartWirelessConnectorTerminal;
import com.asdflj.ae2thing.common.item.ItemPhial;
import com.asdflj.ae2thing.util.ModAndClassUtil;
import com.asdflj.ae2thing.util.NameConst;

public class ItemAndBlockHolder implements Runnable {

    public static final ItemAndBlockHolder INSTANCE = new ItemAndBlockHolder();

    public static ItemBackpackTerminal BACKPACK_MANAGER = new ItemBackpackTerminal().register();
    public static ItemPartInfusionPatternTerminal INFUSION_PATTERN_TERMINAL;
    public static ItemPartThaumatoriumInterface THAUMATRIUM_INTERFACE;
    public static BlockInfusionInterface INFUSION_INTERFACE;
    public static ItemPhial PHIAL;
    public static BlockEssentiaDiscretizer ESSENTIA_DISCRETIZER;
    public static ItemInfinityStorageCell ITEM_INFINITY_CELL = new ItemInfinityStorageCell().register();
    public static ItemInfinityStorageFluidCell ITEM_INFINITY_FLUID_CELL = new ItemInfinityStorageFluidCell().register();
    public static ItemPartWirelessConnectorTerminal WIRELESS_CONNECTOR_TERMINAL = new ItemPartWirelessConnectorTerminal()
        .register();
    public static ItemCreativeFluidCell ITEM_CREATIVE_WATER_CELL = new ItemCreativeFluidCell(
        NameConst.ITEM_CREATIVE_FLUID_CELL_WATER,
        water_bucket).register();
    public static ItemCreativeFluidCell ITEM_CREATIVE_LAVA_CELL = new ItemCreativeFluidCell(
        NameConst.ITEM_CREATIVE_FLUID_CELL_LAVA,
        lava_bucket).register();

    public static BlockFishBig BLOCK_FISH_BIG = new BlockFishBig().register();

    @Override
    public void run() {
        if (ModAndClassUtil.THE) {
            INFUSION_PATTERN_TERMINAL = new ItemPartInfusionPatternTerminal().register();
            THAUMATRIUM_INTERFACE = new ItemPartThaumatoriumInterface().register();
            INFUSION_INTERFACE = new BlockInfusionInterface().register();
            PHIAL = new ItemPhial().register();
            ESSENTIA_DISCRETIZER = new BlockEssentiaDiscretizer().register();
        }
    }
}
