Before v0.0.1:
Fix Texture Issue
Wild Cotton
Cotton Crafting
Quartz Renderer
Zinc Transmutation
Zinc Clusters
Arcane Singularity Booms
Primal Pendant
Wardenic Composite Armor
Awakened Wardenic Composite Armor
Infused Riftish Cotton Clothes
Flux Robes
Aspect Infusion System
Excubitor Aspect Stats
Fluxus Aspect Stats

Maybe Before v0.0.1:
Thaumium and Void Shears and Sickles
Thaumic Compressor

Fix Purity Focus
Tier 2-5 Magneoturge armor
All 5 tiers of Eldritch Armor
All 5 tiers of Wardenic Tools
All 5 tiers of Magneoturge Tools
All 5 tiers of Eldritch Tools
Aspect Orbs
Thaumium and Void Bows and Fishing Rods
Bloodwood Wands
Wand Caps
Runic Infuser
Infernal Blast Furnace
Alumentum Pressurizer
Brass Pipe
Wardenic TiC Modifier


Classes to Reimplement:
../block/BlockQuartzStair (No comment)
../block/BlockWitor (Reimplement in BlockFakeAir)
../block/tile/TileWitor (Reimplement as non-TE rendering in BlockFakeAir)
../client/ModelFleshGolem (Reimplement alongside Flesh Golems [WIP])
../client/gui/GuiHandler (Reimplement when first TE or Wesly's Hammer is added)
../client/gui/GuiWaslieHammer (Reimplement alongside Wesly's Hammer)
../client/render/RenderFleshGolem (Reimplement alongside Flesh Golems [WIP])
../crafting/ModRecipes (Reimplement some parts alongside Tier 4 Wardenic things and Wesly's Hammer)
../entity/EntityFleshProjectile (Reimplement alongside Flesh Golems [WIP])
../entity/FleshGolem (Reimplement alongside Flesh Golems [WIP] as EntityFleshGolem)
../inventory/ContainerHammer (Reimplement alongside Wesly's Hammer)
../item/ItemFocusIllumination (Reimplement with Witor)
../item/ItemWardenArmor (Replace with T5 armor)
../item/ItemWardenBoots (Unneeded with new system)
../item/ItemWardenChest (Unneeded with new system)
../item/ItemWardenHelm (Unneeded with new system)
../item/ItemWardenLegs (Unneeded with new system)
../item/ItemWardenWeapon (Replace with T5 sword)
../item/ItemWaslieHammer (Reimplement after Tier 5 Wardenic)
../research/ModResearch (Reimplement some parts alongside Tier 4 Wardenic things and Wesly's Hammer)
../util/DamageSourceWarden (Not sure how/if I'll redo damage)
../util/wardenic/WardenicChargeEvents (Reimplement as AspectInfusionHandler and modify for new system)
../util/wardenic/WardenicChargeHelper (Move contents into AspectInfusionHelper and modify for new system)
../util/wardenic/WardenicUpgrades (Move to ThaumRevLibrary and ThaumRevContent)
../util/wardenic/upgrade/* (Reimplement along Aspect Infusion)