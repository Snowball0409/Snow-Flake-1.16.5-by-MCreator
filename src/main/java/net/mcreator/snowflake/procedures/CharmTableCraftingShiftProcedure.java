package net.mcreator.snowflake.procedures;

import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.CapabilityItemHandler;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.snowflake.item.CoolCharmItem;
import net.mcreator.snowflake.SnowFlakeModElements;
import net.mcreator.snowflake.SnowFlakeMod;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.Map;

@SnowFlakeModElements.ModElement.Tag
public class CharmTableCraftingShiftProcedure extends SnowFlakeModElements.ModElement {
	public CharmTableCraftingShiftProcedure(SnowFlakeModElements instance) {
		super(instance, 44);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SnowFlakeMod.LOGGER.warn("Failed to load dependency entity for procedure CharmTableCraftingShift!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				SnowFlakeMod.LOGGER.warn("Failed to load dependency x for procedure CharmTableCraftingShift!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				SnowFlakeMod.LOGGER.warn("Failed to load dependency y for procedure CharmTableCraftingShift!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				SnowFlakeMod.LOGGER.warn("Failed to load dependency z for procedure CharmTableCraftingShift!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				SnowFlakeMod.LOGGER.warn("Failed to load dependency world for procedure CharmTableCraftingShift!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		double minStack = 0;
		double indexOfSlot = 0;
		minStack = (double) 64;
		indexOfSlot = (double) 0;
		for (int index0 = 0; index0 < (int) (5); index0++) {
			if (((new Object() {
				public int getAmount(IWorld world, BlockPos pos, int sltid) {
					AtomicInteger _retval = new AtomicInteger(0);
					TileEntity _ent = world.getTileEntity(pos);
					if (_ent != null) {
						_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
							_retval.set(capability.getStackInSlot(sltid).getCount());
						});
					}
					return _retval.get();
				}
			}.getAmount(world, new BlockPos((int) x, (int) y, (int) z), (int) ((indexOfSlot)))) < (minStack))) {
				minStack = (double) (new Object() {
					public int getAmount(IWorld world, BlockPos pos, int sltid) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null) {
							_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
								_retval.set(capability.getStackInSlot(sltid).getCount());
							});
						}
						return _retval.get();
					}
				}.getAmount(world, new BlockPos((int) x, (int) y, (int) z), (int) ((indexOfSlot))));
			}
			indexOfSlot = (double) ((indexOfSlot) + 1);
		}
		indexOfSlot = (double) 0;
		for (int index1 = 0; index1 < (int) (5); index1++) {
			{
				TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
				if (_ent != null) {
					final int _sltid = (int) ((indexOfSlot));
					final int _amount = (int) (minStack);
					_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
						if (capability instanceof IItemHandlerModifiable) {
							ItemStack _stk = capability.getStackInSlot(_sltid).copy();
							_stk.shrink(_amount);
							((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _stk);
						}
					});
				}
			}
			indexOfSlot = (double) ((indexOfSlot) + 1);
		}
		if (entity instanceof PlayerEntity) {
			ItemStack _setstack = new ItemStack(CoolCharmItem.block, (int) (1));
			_setstack.setCount((int) ((minStack) - 1));
			ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
		}
	}
}
