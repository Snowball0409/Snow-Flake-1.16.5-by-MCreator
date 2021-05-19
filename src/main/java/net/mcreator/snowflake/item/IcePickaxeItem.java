
package net.mcreator.snowflake.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;

import net.mcreator.snowflake.itemgroup.SnowFlakeTabItemGroup;
import net.mcreator.snowflake.SnowFlakeModElements;

@SnowFlakeModElements.ModElement.Tag
public class IcePickaxeItem extends SnowFlakeModElements.ModElement {
	@ObjectHolder("snow_flake:ice_pickaxe")
	public static final Item block = null;
	public IcePickaxeItem(SnowFlakeModElements instance) {
		super(instance, 12);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new PickaxeItem(new IItemTier() {
			public int getMaxUses() {
				return 250;
			}

			public float getEfficiency() {
				return 6f;
			}

			public float getAttackDamage() {
				return 0f;
			}

			public int getHarvestLevel() {
				return 2;
			}

			public int getEnchantability() {
				return 14;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(IceCrystalItem.block, (int) (1)));
			}
		}, 1, -3f, new Item.Properties().group(SnowFlakeTabItemGroup.tab)) {
		}.setRegistryName("ice_pickaxe"));
	}
}
