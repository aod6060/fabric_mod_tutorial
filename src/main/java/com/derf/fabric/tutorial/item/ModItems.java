package com.derf.fabric.tutorial.item;

import com.derf.fabric.tutorial.Loader;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public final class ModItems {
	
	public static Item EXAMPLE_ITEM = new Item(new Item.Settings().group(Loader.ITEM_GROUP));
	
	public static void onInitialize() {
		register(EXAMPLE_ITEM, "example_item");
	}
	
	public static void onClientInitialize() {
		
	}
	
	public static void register(Item item, String name) {
		Registry.register(Registry.ITEM, new Identifier(Loader.MODID, name), item);
	}
}
