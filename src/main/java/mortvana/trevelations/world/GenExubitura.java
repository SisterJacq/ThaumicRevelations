package mortvana.trevelations.world;

import cpw.mods.fml.common.IWorldGenerator;
import mortvana.trevelations.common.ModContent;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

import java.util.Random;

public class GenExubitura implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

        int X = chunkX * 16 + random.nextInt(128);
        int Z = chunkZ * 16 + random.nextInt(128);
        int Y = world.getHeightValue(X, Z);

        if (world.isAirBlock(X, Y, Z) && ModContent.blockExubitura.canBlockStay(world, X, Y, Z) && random.nextInt(1000) <= 10) {

            world.setBlock(X, Y, Z, ModContent.blockExubitura, 0, 2);

        }


    }

}
