package mortvana.thaumrev.melteddashboard.intermod.thaumcraft.research;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.thaumrev.melteddashboard.util.IEnumItem;
import mortvana.thaumrev.melteddashboard.util.helpers.StringHelper;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;

public class FluxGearResearchItem extends ResearchItem {

	public String textPrefix = "thaumrev";
	private static final String KEY = "THAUMREV";

	public FluxGearResearchItem(String key, String category, AspectList tags, int col, int row, int complex, ResourceLocation icon) {
		super(key, category, tags, col, row, complex, icon);
	}

	public FluxGearResearchItem(String key, String category, AspectList tags, int col, int row, int complex, ItemStack icon, String prefix) {
		super(key, category, tags, col, row, complex, icon);
		setPrefix(prefix);
	}

	public FluxGearResearchItem(String key, String category, AspectList tags, int col, int row, int complex, ItemStack icon) {
		super(key, category, tags, col, row, complex, icon);
	}

	public FluxGearResearchItem(String key, AspectList tags, int col, int row, int complex, ItemStack icon) {
		super(key, KEY, tags, col, row, complex, icon);
	}

	public FluxGearResearchItem(String key, AspectList tags, int col, int row, int complex, ResourceLocation icon) {
		super(key, KEY, tags, col, row, complex, icon);
	}

	public FluxGearResearchItem(String key, String category) {
		super(key, category);
	}


	public FluxGearResearchItem(String key, AspectList tags, int col, int row, int complex, IEnumItem icon) {
		super(key, KEY, tags, col, row, complex, icon.getStack());
	}


	public FluxGearResearchItem setPrefix(String prefix) {
		textPrefix = prefix;
		return this;
	}

	@Override
	public ResearchItem setPages(ResearchPage... pages) {
		for (ResearchPage page : pages) {
			if (page.type == ResearchPage.PageType.TEXT) {
				page.text = "thaumrev.page." + key + "." + page.text;
			}
		}
		return super.setPages(pages);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getName() {
		return StringHelper.localize(textPrefix + ".name." + key);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getText() {
		return StringHelper.localize(textPrefix + ".text." + key);
	}
}
