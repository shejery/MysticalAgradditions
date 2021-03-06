package com.blakebr0.mysticalagradditions.event;

import java.util.List;

import com.blakebr0.mysticalagradditions.config.ModConfig;
import com.blakebr0.mysticalagradditions.items.ModItems;
import com.blakebr0.mysticalagradditions.lib.MAHelper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MobDrops {

	@SubscribeEvent
	public void onMobDrops(LivingDropsEvent event) {
		List<EntityItem> drops = event.getDrops();

		if (event.getEntity() instanceof EntityWither) {
			drops.add(drop(event.getEntity(), ModItems.itemStuff, 1, 1, MAHelper.mobDrops.getChance(ModConfig.confWitherSouls)));
		}

		if (event.getEntity() instanceof EntityDragon) {
			drops.add(drop(event.getEntity(), ModItems.itemStuff, ModConfig.confDragonScales, 3, 8));
		}
	}

	public EntityItem drop(Entity entity, Item item, int amount, int meta, int chance) {
		ItemStack stack = new ItemStack(item, chance, meta);
		if (stack.getCount() > amount) {
			stack.setCount(amount);
		}
		
		EntityItem drop = new EntityItem(entity.world, entity.posX, entity.posY, entity.posZ, stack);
		
		return drop;
	}
}
