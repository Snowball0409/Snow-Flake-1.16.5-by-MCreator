package net.mcreator.snowflake.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.Direction;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import net.mcreator.snowflake.SnowFlakeModElements;
import net.mcreator.snowflake.SnowFlakeMod;

import java.util.function.Function;
import java.util.Map;
import java.util.Comparator;

@SnowFlakeModElements.ModElement.Tag
public class UseSnowBallWandProcedure extends SnowFlakeModElements.ModElement {
	public UseSnowBallWandProcedure(SnowFlakeModElements instance) {
		super(instance, 53);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("direction") == null) {
			if (!dependencies.containsKey("direction"))
				SnowFlakeMod.LOGGER.warn("Failed to load dependency direction for procedure UseSnowBallWand!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				SnowFlakeMod.LOGGER.warn("Failed to load dependency x for procedure UseSnowBallWand!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				SnowFlakeMod.LOGGER.warn("Failed to load dependency y for procedure UseSnowBallWand!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				SnowFlakeMod.LOGGER.warn("Failed to load dependency z for procedure UseSnowBallWand!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				SnowFlakeMod.LOGGER.warn("Failed to load dependency world for procedure UseSnowBallWand!");
			return;
		}
		Direction direction = (Direction) dependencies.get("direction");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((world.isAirBlock(
				new BlockPos((int) (x + (direction.getXOffset())), (int) (y + (direction.getYOffset())), (int) (z + (direction.getZOffset())))))
				&& (!(((Entity) world
						.getEntitiesWithinAABB(PlayerEntity.class,
								new AxisAlignedBB((x + (direction.getXOffset())) - (1 / 2d), (y + (direction.getYOffset())) - (1 / 2d),
										(z + (direction.getZOffset())) - (1 / 2d), (x + (direction.getXOffset())) + (1 / 2d),
										(y + (direction.getYOffset())) + (1 / 2d), (z + (direction.getZOffset())) + (1 / 2d)),
								null)
						.stream().sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
							}
						}.compareDistOf((x + (direction.getXOffset())), (y + (direction.getYOffset())), (z + (direction.getZOffset())))).findFirst()
						.orElse(null)) != null)))) {
			world.setBlockState(
					new BlockPos((int) (x + (direction.getXOffset())), (int) (y + (direction.getYOffset())), (int) (z + (direction.getZOffset()))),
					Blocks.SNOW_BLOCK.getDefaultState(), 3);
		}
	}
}
