
package net.mcreator.snowflake.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;

import net.mcreator.snowflake.itemgroup.SnowFlakeTabItemGroup;
import net.mcreator.snowflake.SnowFlakeModElements;

@SnowFlakeModElements.ModElement.Tag
public class IceSwordItem extends SnowFlakeModElements.ModElement {
	@ObjectHolder("snow_flake:ice_sword")
	public static final Item block = null;
	public IceSwordItem(SnowFlakeModElements instance) {
		super(instance, 14);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new SwordItem(new IItemTier() {
			public int getMaxUses() {
				return 250;
			}

			public float getEfficiency() {
				return 6f;
			}

			public float getAttackDamage() {
				return 4f;
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
		}, 3, -3f, new Item.Properties().group(SnowFlakeTabItemGroup.tab)) {
		}.setRegistryName("ice_sword"));
	}
}
