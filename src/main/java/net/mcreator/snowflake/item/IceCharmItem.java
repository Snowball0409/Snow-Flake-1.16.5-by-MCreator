
package net.mcreator.snowflake.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.Entity;
import net.minecraft.block.BlockState;

import net.mcreator.snowflake.procedures.IceCharmCheckProcedure;
import net.mcreator.snowflake.itemgroup.SnowFlakeTabItemGroup;
import net.mcreator.snowflake.SnowFlakeModElements;

import java.util.Map;
import java.util.HashMap;

@SnowFlakeModElements.ModElement.Tag
public class IceCharmItem extends SnowFlakeModElements.ModElement {
	@ObjectHolder("snow_flake:ice_charm")
	public static final Item block = null;
	public IceCharmItem(SnowFlakeModElements instance) {
		super(instance, 36);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(SnowFlakeTabItemGroup.tab).maxDamage(150).rarity(Rarity.COMMON));
			setRegistryName("ice_charm");
		}

		@Override
		public int getItemEnchantability() {
			return 5;
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}

		@Override
		public void inventoryTick(ItemStack itemstack, World world, Entity entity, int slot, boolean selected) {
			super.inventoryTick(itemstack, world, entity, slot, selected);
			double x = entity.getPosX();
			double y = entity.getPosY();
			double z = entity.getPosZ();
			if (selected) {
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("itemstack", itemstack);
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				IceCharmCheckProcedure.executeProcedure($_dependencies);
			}
		}
	}
}
