package mcjty.lostcities.dimensions.world.lost;

import mcjty.lostcities.dimensions.world.lost.cityassets.IAsset;
import net.minecraft.block.BlockDynamicLiquid;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Style implements IAsset {

    private final String name;

    private Map<String, IBlockState> styledBlocks = new HashMap<>();
    private Set<IBlockState> damagedToIronBars = new HashSet<>();

    public Style(String name) {
        this.name = name;
    }

    public Style(Style... styles) {
        this("__merged__");
        for (Style style : styles) {
            merge(style);
        }
    }

    public void merge(Style style) {
        styledBlocks.putAll(style.styledBlocks);
        damagedToIronBars.addAll(style.damagedToIronBars);
    }

    @Override
    public String getName() {
        return name;
    }

    public boolean isGlass(IBlockState b) {
        return b.getBlock() == Blocks.GLASS || b.getBlock() == Blocks.GLASS_PANE;
    }

    public boolean canBeDamagedToIronBars(IBlockState b) {
        return damagedToIronBars.contains(b);
    }

    public boolean isEasyToDestroy(IBlockState b) {
        return isGlass(b);
    }

    public boolean isLiquid(IBlockState b) {
        return b != null && (b.getBlock() instanceof BlockLiquid || b.getBlock() instanceof BlockDynamicLiquid);
    }

    /// Get a block defined by this style
    public IBlockState get(String name) {
        return styledBlocks.get(name);
    }

    public Style register(String name, IBlockState block) {
        styledBlocks.put(name, block);
        return this;
    }

    public Style register(String name, IBlockState block, boolean ironBars) {
        styledBlocks.put(name, block);
        if (ironBars) {
            damagedToIronBars.add(block);
        }
        return this;
    }
}
