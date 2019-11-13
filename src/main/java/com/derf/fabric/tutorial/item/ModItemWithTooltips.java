package com.derf.fabric.tutorial.item;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;

public class ModItemWithTooltips extends Item {
	
	public static class ModTooltipWithStyle {
		private String translatable = null;
		private Style style = null;
		
		public ModTooltipWithStyle() {}
		
		public ModTooltipWithStyle(String translatable, Style style) {
			this.translatable = translatable;
			this.style = style;
		}
		
		public Text createText() {
			TranslatableText text = new TranslatableText(this.translatable);
			text.setStyle(this.style);
			return text;
		}
	}
	
	private List<ModTooltipWithStyle> tooltips = new ArrayList<>();
	
	public ModItemWithTooltips(Settings settings) {
		super(settings);
	}

	@Override
	public void appendTooltip(
			ItemStack itemStack, 
			World world, 
			List<Text> tooltip,
			TooltipContext tooltipContext) 
	{
		tooltips.forEach((ModTooltipWithStyle tt) -> {
			tooltip.add(tt.createText());
		});
	}
	
	
	protected void addTooltip(ModTooltipWithStyle tooltip) {
		tooltips.add(tooltip);
	}
	
	protected void addTooltip(String translatable, Style style) {
		ModTooltipWithStyle tooltip = new ModTooltipWithStyle(translatable, style);
		this.addTooltip(tooltip);
	}
	
	
}
