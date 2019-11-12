package com.derf.fabric.tutorial;

import com.derf.fabric.tutorial.item.ModItems;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class Loader implements ModInitializer, ClientModInitializer {
	
	public static String MODID = "tutorial";
	
	public static ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
			new Identifier(MODID, "general"), 
			() -> new ItemStack(Items.ENCHANTED_GOLDEN_APPLE));
	
	@Override
	public void onInitializeClient() {
		System.out.println("Hello, from client...");
		ModItems.onClientInitialize();
	}

	@Override
	public void onInitialize() {
		System.out.println("Hello, from server...");
		ModItems.onInitialize();
	}

}
