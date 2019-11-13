package com.derf.fabric.tutorial.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.text.LiteralText;

public class ModItemEventTest extends Item {

	public ModItemEventTest(Settings settings) {
		super(settings);
	}

	@Override
	public void usageTick(
			World world, 
			LivingEntity livingEntity, 
			ItemStack itemStack, 
			int i) {
		super.usageTick(world, livingEntity, itemStack, i);
		
		if(!world.isClient) {
			livingEntity.sendMessage(new LiteralText("on usageTick()"));
		}
	}

	@SuppressWarnings("static-access")
@Override
	public ActionResult useOnBlock(ItemUsageContext usageContext) {
		
		if(!usageContext.getWorld().isClient) {
			usageContext.getPlayer().sendMessage(new LiteralText("on useOnBlock()"));
			
			if(usageContext.getWorld().getBlockEntity(usageContext.getBlockPos()) == null) {
				BlockPos pos = usageContext.getBlockPos();
				World world = usageContext.getWorld();
				BlockState state = usageContext.getWorld().getBlockState(pos);
				Block block = state.getBlock();
				
				//block.getDroppedStacks(blockState_1, lootContext$Builder_1)
				List<ItemStack> stacks = block.getDroppedStacks(state, (ServerWorld) world, pos, world.getBlockEntity(pos));
				
				stacks.forEach((ItemStack stack) -> world.spawnEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack)));
				
				world.setBlockState(pos, Blocks.AIR.getDefaultState());
			}
			
			return ActionResult.SUCCESS;
		}
		
		return super.useOnBlock(usageContext);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public TypedActionResult<ItemStack> use(
			World world, 
			PlayerEntity player, 
			Hand hand) {
		
		if(!world.isClient) {
			
			player.sendMessage(new LiteralText("on use()"));
			return new TypedActionResult(ActionResult.SUCCESS, player.getStackInHand(hand));
			
		}
		return super.use(world, player, hand);
	}

	@Override
	public boolean postHit(
			ItemStack itemStack, 
			LivingEntity target, 
			LivingEntity attacker) {
		
		attacker.sendMessage(new LiteralText("on posHit()"));
		
		target.setVelocity(0.0, 2.0, 0.0);
		target.addPotionEffect(new StatusEffectInstance(StatusEffects.POISON, 10, 100));
		target.setOnFireFor(10);
		
		return super.postHit(itemStack, target, attacker);
	}

	@Override
	public boolean postMine(
			ItemStack itemStack, 
			World world, 
			BlockState blockState, 
			BlockPos blockPos,
			LivingEntity livingEntity) {
		
		if(!world.isClient) {
			livingEntity.sendMessage(new LiteralText("on postMine()"));
			
			world.createExplosion(livingEntity, blockPos.getX(), blockPos.getY(), blockPos.getZ(), 5.0f, Explosion.DestructionType.DESTROY);
			return true;
		}
		return super.postMine(itemStack, world, blockState, blockPos, livingEntity);
	}

	@Override
	public boolean useOnEntity(
			ItemStack itemStack, 
			PlayerEntity playerEntity, 
			LivingEntity target,
			Hand hand) {
		
		if(!playerEntity.world.isClient) {
			playerEntity.sendMessage(new LiteralText("on useOnEntity()"));
			
			target.setPosition(target.x, target.y + 20, target.z);
			return true;
		}
		
		return super.useOnEntity(itemStack, playerEntity, target, hand);
	}

	@Override
	public void onCraft(
			ItemStack itemStack, 
			World world, 
			PlayerEntity playerEntity) {
		super.onCraft(itemStack, world, playerEntity);
		
		if(!world.isClient) {
			playerEntity.sendMessage(new LiteralText("on onCraft()"));
			
			world.createExplosion(null, playerEntity.x, playerEntity.y, playerEntity.z, 3.0f, Explosion.DestructionType.DESTROY);
		}
	}

	@Override
	public void onStoppedUsing(
			ItemStack itemStack, 
			World world, 
			LivingEntity livingEntity, 
			int i) {
		super.onStoppedUsing(itemStack, world, livingEntity, i);
		
		if(!world.isClient) {
			livingEntity.sendMessage(new LiteralText("on onStoppedUsing()"));
		}
	}

	@Override
	public void inventoryTick(
			ItemStack stack, 
			World world, 
			Entity entity, 
			int i, 
			boolean b) {
		super.inventoryTick(stack, world, entity, i, b);
		
		
		if(!world.isClient) {
			
			if(entity instanceof PlayerEntity) {
				PlayerEntity playerEntity = (PlayerEntity)entity;
				
				if(playerEntity.getActiveItem().getItem() == this) {
					
				}
			}
		}
	}

	@Override
	public float getMiningSpeed(ItemStack itemStack_1, BlockState blockState_1) {
		// TODO Auto-generated method stub
		return 20f;
	}
	
	
	
}
