package mortvana.melteddashboard.world;

/*
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info
 *
 * This code is the property of CovertJaguar
 * and may only be used with explicit written
 * permission unless otherwise specified on the
 * license page at http://railcraft.info/wiki/info:license.
 */
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.util.ForgeDirection;

import mortvana.melteddashboard.util.helpers.WorldHelper;

/**
 *  Taken from Railcraft with permission from the licence for Poor Ore blocks.
 *
 *  @author CovertJaguar <http://www.railcraft.info>
 */
public class WorldGenSmallDeposits extends WorldGenerator {

    public final Block ore, replace;
    public final int meta, number;

    public WorldGenSmallDeposits(Block ore, int meta, int number, Block replace) {
        this.ore = ore;
        this.meta = meta;
        this.number = number;
        this.replace = replace;
    }

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z) {
        if (canGen(world, x, y, z)) {
            placeOre(world, rand, x, y, z);
            return true;
        } else {
            return false;
        }
    }

    public boolean canGen(World world, int x, int y, int z) {
        return true;
    }

    public void placeOre(World world, Random rand, int x, int y, int z) {
        for (int num = 0; num < number; num++) {
            Block block = WorldHelper.getBlock(world, x, y, z);
            if (block != null && block.isReplaceableOreGen(world, x, y, z, replace)) {
                world.setBlock(x, y, z, ore, meta, 2);
            }

            ForgeDirection dir = ForgeDirection.getOrientation(rand.nextInt(6));

            x = WorldHelper.getXOnSide(x, dir);
            y = WorldHelper.getYOnSide(y, dir);
            z = WorldHelper.getZOnSide(z, dir);

            if (!world.blockExists(x, y, z)) {
                break;
            }
        }
    }

}