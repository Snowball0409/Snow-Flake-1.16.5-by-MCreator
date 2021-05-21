package net.mcreator.snowflake.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import net.mcreator.snowflake.SnowFlakeModElements;
import net.mcreator.snowflake.SnowFlakeMod;

import java.util.Map;

@SnowFlakeModElements.ModElement.Tag
public class IceCharmCheckProcedure extends SnowFlakeModElements.ModElement {
	public IceCharmCheckProcedure(SnowFlakeModElements instance) {
		super(instance, 36);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SnowFlakeMod.LOGGER.warn("Failed to load dependency entity for procedure IceCharmCheck!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				SnowFlakeMod.LOGGER.warn("Failed to load dependency itemstack for procedure IceCharmCheck!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				SnowFlakeMod.LOGGER.warn("Failed to load dependency x for procedure IceCharmCheck!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				SnowFlakeMod.LOGGER.warn("Failed to load dependency y for procedure IceCharmCheck!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				SnowFlakeMod.LOGGER.warn("Failed to load dependency z for procedure IceCharmCheck!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				SnowFlakeMod.LOGGER.warn("Failed to load dependency world for procedure IceCharmCheck!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		double sx = 0;
		double sy = 0;
		double sz = 0;
		if ((!((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == Blocks.WATER.getDefaultState().getBlock()))) {
			sx = (double) (-3);
			for (int index0 = 0; index0 < (int) (6); index0++) {
				sy = (double) (-1);
				for (int index1 = 0; index1 < (int) (1); index1++) {
					sz = (double) (-3);
					for (int index2 = 0; index2 < (int) (6); index2++) {
						if ((((world.getBlockState(new BlockPos((int) (x + (sx)), (int) (y + (sy)), (int) (z + (sz))))).getBlock() == Blocks.WATER
								.getDefaultState().getBlock())
								&& (!((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.WATER.getDefaultState()
										.getBlock())))) {
							{
								BlockPos _bp = new BlockPos((int) (x + (sx)), (int) (y + (sy)), (int) (z + (sz)));
								BlockState _bs = Blocks.ICE.getDefaultState();
								world.setBlockState(_bp, _bs, 3);
							}
							if (world instanceof World && !world.isRemote()) {
								((World) world)
										.playSound(null, new BlockPos((int) x, (int) y, (int) z),
												(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
														.getValue(new ResourceLocation("block.glass.break")),
												SoundCategory.NEUTRAL, (float) 1, (float) 1);
							} else {
								((World) world).playSound(x, y, z,
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("block.glass.break")),
										SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
							}
							((itemstack)).setDamage((int) ((((itemstack)).getDamage()) + 1));
						}
						sz = (double) ((sz) + 1);
					}
					sy = (double) ((sy) + 1);
				}
				sx = (double) ((sx) + 1);
			}
		}
		if (((((itemstack)).getDamage()) >= (((itemstack)).getMaxDamage()))) {
			if (entity instanceof PlayerEntity) {
				ItemStack _stktoremove = (itemstack);
				((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
						((PlayerEntity) entity).container.func_234641_j_());
			}
			((itemstack)).setDamage((int) 0);
			if (world instanceof World && !world.isRemote()) {
				((World) world).playSound(null, new BlockPos((int) x, (int) y, (int) z),
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.item.break")),
						SoundCategory.NEUTRAL, (float) 1, (float) 1);
			} else {
				((World) world).playSound(x, y, z,
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.item.break")),
						SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
			}
		}
	}
}
