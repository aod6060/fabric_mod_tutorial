package com.derf.fabric.tutorial.item;

import net.minecraft.text.Style;
import net.minecraft.util.Formatting;

public class ModItemExample extends ModItemWithTooltips {

	public ModItemExample(Settings settings) {
		super(settings);
		
		this.addTooltip("item.tutorial.example_item.tooltip.red", new Style().setColor(Formatting.RED));
		this.addTooltip("item.tutorial.example_item.tooltip.green", new Style().setColor(Formatting.GREEN));
		this.addTooltip("item.tutorial.example_item.tooltip.blue", new Style().setColor(Formatting.BLUE));
		this.addTooltip("item.tutorial.example_item.tooltip.bold", new Style().setBold(true));
		this.addTooltip("item.tutorial.example_item.tooltip.italic", new Style().setItalic(true));
		this.addTooltip("item.tutorial.example_item.tooltip.underline", new Style().setUnderline(true));
		this.addTooltip("item.tutorial.example_item.tooltip.strike", new Style().setStrikethrough(true));
	}

}
