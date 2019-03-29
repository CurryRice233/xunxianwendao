package curryrice.xunxianwendao.item;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;

public enum ToolMaterial implements IItemTier {
	jade(4, 2000, 12.0F, 4.0F, 10, (Item)ItemList.item_jade_primary);
	private float attackDamage, efficiency;
	private int maxUses, harvestLevel, enchantability;
	private Item repairMaterial;

	private ToolMaterial(int harvestLevelIn, int maxUsesIn, float efficiencyIn, float attackDamageIn, int enchantabilityIn,
			Item repairMaterial) {
		this.attackDamage = attackDamageIn;
		this.efficiency = efficiencyIn;
		this.maxUses = maxUsesIn;
		this.harvestLevel = harvestLevelIn;
		this.enchantability = enchantabilityIn;
		this.repairMaterial = repairMaterial;
	}

	@Override
	public int getMaxUses() {
		return this.maxUses;
	}

	@Override
	public float getEfficiency() {
		return this.efficiency;
	}

	@Override
	public float getAttackDamage() {
		return this.attackDamage;
	}

	@Override
	public int getHarvestLevel() {
		return this.harvestLevel;
	}

	@Override
	public int getEnchantability() {
		return this.enchantability;
	}

	@Override
	public Ingredient getRepairMaterial() {
		return Ingredient.fromItems(this.repairMaterial);
	}

}
