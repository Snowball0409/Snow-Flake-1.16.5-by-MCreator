package net.mcreator.snowflake.entity.renderer;

import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.entity.model.SlimeModel;
import net.minecraft.client.renderer.entity.MobRenderer;

import net.mcreator.snowflake.entity.IceSlimeEntity;

@OnlyIn(Dist.CLIENT)
public class IceSlimeRenderer {
	public static class ModelRegisterHandler {
		@SubscribeEvent
		@OnlyIn(Dist.CLIENT)
		public void registerModels(ModelRegistryEvent event) {
			RenderingRegistry.registerEntityRenderingHandler(IceSlimeEntity.entity,
					renderManager -> new MobRenderer(renderManager, new SlimeModel(0), 1.2f) {
						@Override
						public ResourceLocation getEntityTexture(Entity entity) {
							return new ResourceLocation("snow_flake:textures/wip_pic.png");
						}
					});
		}
	}
}
