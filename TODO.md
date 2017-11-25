Before v0.0.1:
- [ ] Fix Texture Issue
- [ ] Colorizer
- [ ] Wild Cotton
- [ ] Thistle
- [x] Cotton Crafting
- [ ] Quartz Renderer
- [ ] Quartz Stairs
- [x] Wardernic Obsidian
- [x] Eldritch Stone
- [x] Eldrich Keystone
- [ ] Ores
	- [ ] Chalcocite
	- [ ] Sphalerite
	- [ ] Cassiterite
	- [ ] Millerite
	- [ ] Native Silver
	- [ ] Galena
	- [ ] Xenotime
	- [ ] Wolframite
	- [ ] Iridosmium
	- [ ] Bismuthinite
	- [ ] Tennantite
	- [ ] Tetrahedrite
	- [ ] Pyrope
	- [ ] Dioptase
	- [ ] Fluonic Sapphire
- [ ] Ore Transmutation
- [ ] Ore Clusters
- [ ] Arcane Singularity Booms
- [ ] Primal Pendant
- [ ] Thaumic Electrum
- [x] Animated Piston
- [x] Ench. Ag-wood
- [x] Wardencloth Armor
- [x] Wardenic Bronze Chain Armor
- [x] Wardenic Steel Plate Armor
- [x] Wardenic Composite Armor
- [ ] Awakened Wardenic Composite Armor
- [ ] Infused Riftish Cotton Clothes
- [ ] Flux Robes
- [ ] Aspect Infusion System
- [ ] Excubitor Aspect Stats
- [ ] Fluxus Aspect Stats

Maybe Before v0.0.1:
- [ ] Wand Focus: Illumination (Witor)
- [ ] Cotton and Thistle Farming
- [ ] Excubitura Farming
- [ ] Thaumium and Void Shears and Sickles
- [ ] Transmutaions
	- Vannila:
		- [ ] Diamond
		- [ ] Emerald
		- [ ] Coal
		- [ ] Redstone
		- [ ] Lapis Lazuli
		- [ ] Nether Quartz
	- Other OreDict:
		- [ ] Nickel (TF, ElC)
		- [ ] Aluminium (TiC, ElC)
		- [ ] Platinum (TF, ElC)
		- [ ] Uranium (IC2, ReC, BR)
	- Thaumcraft:
		- [ ] Shards
		- [ ] Amber
		- [ ] Quicksilver
		- [ ] Salis Mundus
	- Thaumic Revelations/Project Flux Gear:
		- [ ] Zinc
		- [ ] Bismuth
		- [ ] Arsenic
		- [ ] Antimony
		- [ ] Tungsten
		- [ ] Iridium
		- [ ] Osmium
		- [ ] Dioptase
        - [ ] Pyrope
        - [ ] Fluonic Sapphire
	- Project Flux Gear Future-proofing:
		- [ ] Titanium
		- [ ] Molybdenum
		- [ ] Palladium
		- [ ] Cobalt (NaturalCobalt)
		- [ ] Chromium
		- [ ] Tellurium
		- [ ] Cadmium
		- [ ] Myuvil
		- [ ] Neodymium
		- [ ] Vanadium
		- [ ] Manganese
		- [ ] Thorium
	- Tinker's Construct:
		- [ ] Nether Cobalt
		- [ ] Ardite
	- Various Gems:
		- [ ] Corrundum
		- [ ] Ruby
		- [ ] Sapphire
		- [ ] Green Sapphire
		- [ ] Emery
		- [ ] Peridot
		- [ ] Topaz
		- [ ] Tanzanite
		- [ ] Malachite
	- AM2:
		- [ ] Blue Topaz
		- [ ] Chimerite
		- [ ] Sunstone
		- [ ] Moonstone
		- [ ] Vitreum?
	- Railcraft:
		- [ ] Sulfur
		- [ ] Saltpeter
		- [ ] Firestone
	- Project:Red:
		- [ ] Electrotine
	- Applied Energistic 2:
		- [ ] Certus Quartz
		- [ ] Fluix Quartz	
	- Forestry:
		- [ ] Apatite
	- Magical Crops:
		- [ ] Essence
	- Thaumic Tinkerer 2:
		- [ ] Nether Shards
		- [ ] End Shards
	- Forrbiden Magic:
		- [ ] Sin Shards
		- [ ] Tainted Shard
	- Metallurgy:
		- [ ] Fantasy
		- [ ] Nether
		- [ ] End
		- [ ] Other
	- ReactorCraft (if Reika allows):
		- [ ] Indium
		- [ ] Cadmium
		- [ ] Ammonium Chloride
		- [ ] Calcite
		- [ ] Fluorite
		- [ ] Magnetite
- [ ] Wesly's Hammer
- [ ] Elemental Shears
- [ ] Elemental Sickle
- [ ] Thaumic Hammermill
- [ ] Fix Purity Focus
- [ ] Arcane Mechanic's Armor
- [ ] Thauimic Engineer's Armor
- [ ] Magneoturge's Armor
- [ ] Awakened Magneoturge's Armor
- [ ] All 5 tiers of Eldritch Armor
- [ ] All 5 tiers of Wardenic Tools
- [ ] All 5 tiers of Magneoturge Tools
- [ ] All 5 tiers of Eldritch Tools
- [ ] Thaumium and Void Bows and Fishing Rods
- [ ] Elemental Fishing Rod
- [ ] Bloodwood Wand/Staff Core
- [ ] Wand Caps
	- [ ] Lead
	- [ ] Bismuth
	- [ ] Brass
	- [ ] Bronze
	- [ ] Electrum
	- [ ] Platinum
	- [ ] Thaumic Electrum
	- [ ] Enderium
	- [ ] Signalum
	- [ ] Lumium
- [ ] Runic Infuser
- [ ] Infernal Blast Furnace
- [ ] Wardenic TiC Modifier
- [ ] TTKami Staff Core
- [ ] Shadowforge
- [ ] Elemental Reeds
- [ ] Crossover Oreberries
- [ ] Magical Botany/Arboriculture
	- [ ] Thaumcraft
	- [ ] Ars Magica
	- [ ] ChromatiCraft (ask Reika)
- [ ] Warded Chest
- [ ] Alumentum Pressurizer
- [ ] Brass Pipe

Classes to Reimplement:
* ../block/BlockQuartzStair (No comment)
* ../block/BlockWitor (Reimplement in BlockFakeAir)
* ../block/tile/TileWitor (Reimplement as non-TE rendering in BlockFakeAir)
* ../client/ModelFleshGolem (Reimplement alongside Flesh Golems [WIP])
* ../client/gui/GuiHandler (Reimplement when first TE or Wesly's Hammer is added)
* ../client/gui/GuiWaslieHammer (Reimplement alongside Wesly's Hammer)
* ../client/render/RenderFleshGolem (Reimplement alongside Flesh Golems [WIP])
* ../crafting/ModRecipes (Reimplement some parts alongside Tier 4 Wardenic things and Wesly's Hammer)
* ../entity/EntityFleshProjectile (Reimplement alongside Flesh Golems [WIP])
* ../entity/FleshGolem (Reimplement alongside Flesh Golems [WIP] as EntityFleshGolem)
* ../inventory/ContainerHammer (Reimplement alongside Wesly's Hammer)
* ../item/ItemFocusIllumination (Reimplement with Witor)
* ../item/ItemWardenArmor (Replace with T5 armor)
* ../item/ItemWardenBoots (Unneeded with new system)
* ../item/ItemWardenChest (Unneeded with new system)
* ../item/ItemWardenHelm (Unneeded with new system)
* ../item/ItemWardenLegs (Unneeded with new system)
* ../item/ItemWardenWeapon (Replace with T5 sword)
* ../item/ItemWaslieHammer (Reimplement after Tier 5 Wardenic)
* ../research/ModResearch (Reimplement some parts alongside Tier 4 Wardenic things and Wesly's Hammer)
* ../util/DamageSourceWarden (Not sure how/if I'll redo damage)
* ../util/wardenic/WardenicChargeEvents (Reimplement as AspectInfusionHandler and modify for new system)
* ../util/wardenic/WardenicChargeHelper (Move contents into AspectInfusionHelper and modify for new system)
* ../util/wardenic/WardenicUpgrades (Move to ThaumRevLibrary and ThaumRevContent)
* ../util/wardenic/upgrade/* (Reimplement along Aspect Infusion)