package mortvana.melteddashboard.intermod.thaumcraft.research;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.melteddashboard.util.IStackProvider;
import mortvana.melteddashboard.util.helpers.StringHelper;
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


	public FluxGearResearchItem(String key, AspectList tags, int col, int row, int complex, IStackProvider icon) {
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

	public FluxGearResearchItem addPages(boolean bool, ResearchPage... pages) {
		if (bool) {
			addPages(pages);
		}
		return this;
	}

	public FluxGearResearchItem addPages(ResearchPage... pages) {
		ResearchPage[] pgs = getPages();
		ResearchPage[] temp = new ResearchPage[pgs.length + pages.length];
		System.arraycopy(pgs, 0, temp, 0, pgs.length);
		System.arraycopy(pages, 0, temp, pgs.length, pages.length);
		setPages(temp);
		return this;
	}

	public FluxGearResearchItem addParents(String... par) {
		String[] temp = new String[parents.length + par.length];
		System.arraycopy(parents, 0, temp, 0, parents.length);
		System.arraycopy(par, 0, temp, parents.length, par.length);
		setParents(temp);
		return this;
	}


	public FluxGearResearchItem addParentsHidden(String... par) {
		String[] temp = new String[parentsHidden.length + par.length];
		System.arraycopy(parentsHidden, 0, temp, 0, parentsHidden.length);
		System.arraycopy(par, 0, temp, parentsHidden.length, par.length);
		setParents(temp);
		return this;
	}

	public FluxGearResearchItem addSiblings(String... sib) {
		String[] temp = new String[siblings.length + sib.length];
		System.arraycopy(siblings, 0, temp, 0, siblings.length);
		System.arraycopy(sib, 0, temp, siblings.length, sib.length);
		setParents(temp);
		return this;
	}
}
