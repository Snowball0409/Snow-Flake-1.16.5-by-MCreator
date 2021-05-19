package net.mcreator.snowflake.procedures;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.state.Property;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import net.mcreator.snowflake.item.SnowFlakeItem;
import net.mcreator.snowflake.block.IceFurnaceBlock;
import net.mcreator.snowflake.SnowFlakeModElements;
import net.mcreator.snowflake.SnowFlakeMod;

import java.util.Map;

@SnowFlakeModElements.ModElement.Tag
public class ClickIceProcedure extends SnowFlakeModElements.ModElement {
	public ClickIceProcedure(SnowFlakeModElements instance) {
		super(instance, 5);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				SnowFlakeMod.LOGGER.warn("Failed to load dependency x for procedure ClickIce!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				SnowFlakeMod.LOGGER.warn("Failed to load dependency y for procedure ClickIce!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				SnowFlakeMod.LOGGER.warn("Failed to load dependency z for procedure ClickIce!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				SnowFlakeMod.LOGGER.warn("Failed to load dependency world for procedure ClickIce!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		double RandomDrop = 0;
		if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.ICE.getDefaultState().getBlock())) {
			RandomDrop = (double) Math.random();
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
			if (((RandomDrop) >= 0.7)) {
				if (world instanceof World && !world.isRemote()) {
					ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z, new ItemStack(SnowFlakeItem.block, (int) (1)));
					entityToSpawn.setPickupDelay((int) 0);
					world.addEntity(entityToSpawn);
				}
			} else {
				if (world instanceof World && !world.isRemote()) {
					ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z, new ItemStack(Blocks.ICE, (int) (1)));
					entityToSpawn.setPickupDelay((int) 0);
					world.addEntity(entityToSpawn);
				}
			}
		} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.PACKED_ICE.getDefaultState().getBlock())) {
			RandomDrop = (double) Math.random();
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
			if (((RandomDrop) >= 0.7)) {
				if (world instanceof World && !world.isRemote()) {
					ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z, new ItemStack(SnowFlakeItem.block, (int) (1)));
					entityToSpawn.setPickupDelay((int) 0);
					world.addEntity(entityToSpawn);
				}
			} else {
				if (world instanceof World && !world.isRemote()) {
					ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z, new ItemStack(Blocks.PACKED_ICE, (int) (1)));
					entityToSpawn.setPickupDelay((int) 0);
					world.addEntity(entityToSpawn);
				}
			}
		} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.FURNACE.getDefaultState().getBlock())) {
			{
				BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
				BlockState _bs = IceFurnaceBlock.block.getDefaultState();
				BlockState _bso = world.getBlockState(_bp);
				for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
					Property _property = _bs.getBlock().getStateContainer().getProperty(entry.getKey().getName());
					if (_property != null && _bs.get(_property) != null)
						try {
							_bs = _bs.with(_property, (Comparable) entry.getValue());
						} catch (Exception e) {
						}
				}
				TileEntity _te = world.getTileEntity(_bp);
				CompoundNBT _bnbt = null;
				if (_te != null) {
					_bnbt = _te.write(new CompoundNBT());
					_te.remove();
				}
				world.setBlockState(_bp, _bs, 3);
				if (_bnbt != null) {
					_te = world.getTileEntity(_bp);
					if (_te != null) {
						try {
							_te.read(_bso, _bnbt);
						} catch (Exception ignored) {
						}
					}
				}
			}
		}
	}
}
