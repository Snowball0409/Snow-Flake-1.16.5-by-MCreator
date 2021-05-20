
package net.mcreator.snowflake.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;
import net.minecraft.item.AxeItem;

import net.mcreator.snowflake.itemgroup.SnowFlakeTabItemGroup;
import net.mcreator.snowflake.SnowFlakeModElements;

@SnowFlakeModElements.ModElement.Tag
public class HardenedIceAxeItem extends SnowFlakeModElements.ModElement {
	@ObjectHolder("snow_flake:hardened_ice_axe")
	public static final Item block = null;
	public HardenedIceAxeItem(SnowFlakeModElements instance) {
		super(instance, 42);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new AxeItem(new IItemTier() {
			public int getMaxUses() {
				return 1561;
			}

			public float getEfficiency() {
				return 6f;
			}

			public float getAttackDamage() {
				return 7f;
			}

			public int getHarvestLevel() {
				return 3;
			}

			public int getEnchantability() {
				return 14;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(HardenedIceCrystalItem.block, (int) (1)));
			}
		}, 1, -3f, new Item.Properties().group(SnowFlakeTabItemGroup.tab)) {
		}.setRegistryName("hardened_ice_axe"));
	}
}
