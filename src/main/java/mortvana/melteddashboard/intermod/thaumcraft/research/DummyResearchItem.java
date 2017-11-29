package mortvana.melteddashboard.intermod.thaumcraft.research;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.*;

/**
 *	Almost the same thing (except a few field names) as the FauxResearchItem class in Thaumic Exploration, because it
 *	is, except it has FluxGearResearchItem goodies. All credit goes to Flaxbeard, the original author of Thaumic
 *	Exploration, who created the class.
 *
 *	For those who don't know, this allows for dummy research items of other research items, for a more intuitive
 *	thaumonomicon experience!
 */

public class DummyResearchItem extends FluxGearResearchItem {

	public ResearchItem original;

	public DummyResearchItem(String key, String category, String origin, String originCategory, int col, int row, ResourceLocation icon) {
		super(key, category, new AspectList(), col, row, 1, icon);
		original = (ResearchCategories.researchCategories.get(originCategory)).research.get(origin);
		bindToOriginal();
		setStub();
		setHidden();
	}

	public DummyResearchItem(String key, String category, String origin, String originCategory, int col, int row, ItemStack icon) {
		super(key, category, new AspectList(), col, row, 1, icon);
		original = (ResearchCategories.researchCategories.get(originCategory)).research.get(origin);
		bindToOriginal();
		setStub();
		setHidden();
	}

	public DummyResearchItem(String key, String origin, String originCategory, int col, int row, ResourceLocation icon) {
		super(key, new AspectList(), col, row, 1, icon);
		original = (ResearchCategories.researchCategories.get(originCategory)).research.get(origin);
		bindToOriginal();
		setStub();
		setHidden();
	}

	public DummyResearchItem(String key, String origin, String originCategory, int col, int row, ItemStack icon) {
		super(key, new AspectList(), col, row, 1, icon);
		original = (ResearchCategories.researchCategories.get(originCategory)).research.get(origin);
		bindToOriginal();
		setStub();
		setHidden();
	}

	private void bindToOriginal() {
		if (original.siblings == null) {
			original.setSiblings(key);
		} else {
			String[] family = original.siblings;
			String[] newFamily = new String[family.length + 1];

			System.arraycopy(family, 0, newFamily, 0, family.length);
			newFamily[family.length] = key;
			original.setSiblings(newFamily);
		}
		if (original.isSecondary()) {
			this.setSecondary();
		}
	}

	@Override
	public ResearchPage[] getPages() {
		return original.getPages();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getName() {
		return original.getName();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getText() {
		return original.getText();
	}

	@Override
	public boolean isStub() {
		return true;
	}

	@Override
	public boolean isHidden() {
		return true;
	}

	@Override
	public int getComplexity() {
		return 1;
	}
}
