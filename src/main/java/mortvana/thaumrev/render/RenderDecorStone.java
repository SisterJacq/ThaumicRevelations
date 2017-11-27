package mortvana.thaumrev.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

import mortvana.melteddashboard.util.helpers.RenderHelper;

import mortvana.thaumrev.library.ThaumRevLibrary;
import org.lwjgl.opengl.GL11;

public class RenderDecorStone implements ISimpleBlockRenderingHandler {

	/*public boolean renderBlockQuartz(Block p_147779_1_, int p_147779_2_, int p_147779_3_, int p_147779_4_)
	{
		int l = this.blockAccess.getBlockMetadata(p_147779_2_, p_147779_3_, p_147779_4_);

		if (l == 5)
		{

		}

		boolean flag = this.renderStandardBlock(p_147779_1_, p_147779_2_, p_147779_3_, p_147779_4_);
		this.uvRotateSouth = 0;
		this.uvRotateEast = 0;
		this.uvRotateWest = 0;
		this.uvRotateNorth = 0;
		this.uvRotateTop = 0;
		this.uvRotateBottom = 0;
		return flag;
	}*/

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
		GL11.glPushMatrix();

		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		renderer.setRenderBoundsFromBlock(block);

		if (metadata == 5) {
			renderer.uvRotateEast = 1;
			renderer.uvRotateWest = 1;
			renderer.uvRotateTop = 1;
			renderer.uvRotateBottom = 1;
		} else if (metadata == 6) {
			renderer.uvRotateSouth = 1;
			renderer.uvRotateNorth = 1;
		}

		RenderHelper.drawInventoryBlock(block, metadata, renderer);

		GL11.glPopMatrix();
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		int meta = world.getBlockMetadata(x, y, z);
		renderer.setRenderBounds(0, 0, 0, 1, 1, 1);
		if (meta == 5) {
			renderer.uvRotateEast = 1;
			renderer.uvRotateWest = 1;
			renderer.uvRotateTop = 1;
			renderer.uvRotateBottom = 1;
			renderer.renderStandardBlock(block, x, y, z);
			return true;
		} else if (meta == 6) {
			renderer.uvRotateSouth = 1;
			renderer.uvRotateNorth = 1;
			renderer.renderStandardBlock(block, x, y, z);
			return true;
		}
		return renderer.renderStandardBlock(block, x, y, z);
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return ThaumRevLibrary.renderDecorStoneID;
	}
}
