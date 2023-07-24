package com.lamplockout;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

@ConfigGroup(LampLockoutPlugin.CONFIG_GROUP)
public interface LampLockoutConfig extends Config
{
	@ConfigSection(
			name = "Combat skills",
			description = "",
			position = 0
	)
	String combatSection = "combatSection";

	@ConfigItem(
			keyName = LampLockoutPlugin.ENABLE_ATTACK,
			name = "Allow Attack",
			description = "",
			position = 0,
			section = combatSection
	)
	default boolean enableAttack() { return false; }

	@ConfigItem(
			keyName = LampLockoutPlugin.ENABLE_STRENGTH,
			name = "Allow Strength",
			description = "",
			position = 1,
			section = combatSection
	)
	default boolean enableStrength() { return false; }

	@ConfigItem(
			keyName = LampLockoutPlugin.ENABLE_DEFENCE,
			name = "Allow Defence",
			description = "",
			position = 2,
			section = combatSection
	)
	default boolean enableDefence() { return false; }

	@ConfigItem(
			keyName = LampLockoutPlugin.ENABLE_RANGED,
			name = "Allow Ranged",
			description = "",
			position = 3,
			section = combatSection
	)
	default boolean enableRanged() { return false; }

	@ConfigItem(
			keyName = LampLockoutPlugin.ENABLE_MAGIC,
			name = "Allow Magic",
			description = "",
			position = 4,
			section = combatSection
	)
	default boolean enableMagic() { return false; }

	@ConfigItem(
			keyName = LampLockoutPlugin.ENABLE_PRAYER,
			name = "Allow Prayer",
			description = "",
			position = 5,
			section = combatSection
	)
	default boolean enablePrayer() { return false; }

	@ConfigItem(
			keyName = LampLockoutPlugin.ENABLE_HITPOINTS,
			name = "Allow Hitpoints",
			description = "",
			position = 6,
			section = combatSection
	)
	default boolean enableHitpoints() { return false; }

	@ConfigSection(
			name = "Members skills",
			description = "",
			position = 1
	)
	String membersSection = "membersSkills";

	@ConfigItem(
			keyName = LampLockoutPlugin.ENABLE_AGILITY,
			name = "Allow Agility",
			description = "",
			section = membersSection
	)
	default boolean enableAgility() { return false; }

	@ConfigItem(
			keyName = LampLockoutPlugin.ENABLE_HERBLORE,
			name = "Allow Herblore",
			description = "",
			section = membersSection
	)
	default boolean enableHerblore() { return false; }

	@ConfigItem(
			keyName = LampLockoutPlugin.ENABLE_THIEVING,
			name = "Allow Thieving",
			description = "",
			section = membersSection
	)
	default boolean enableThieving() { return false; }

	@ConfigItem(
			keyName = LampLockoutPlugin.ENABLE_FLETCHING,
			name = "Allow Fletching",
			description = "",
			section = membersSection
	)
	default boolean enableFletching() { return false; }

	@ConfigItem(
			keyName = LampLockoutPlugin.ENABLE_SLAYER,
			name = "Allow Slayer",
			description = "",
			section = membersSection
	)
	default boolean enableSlayer() { return false; }

	@ConfigItem(
			keyName = LampLockoutPlugin.ENABLE_HUNTER,
			name = "Allow Hunter",
			description = "",
			section = membersSection
	)
	default boolean enableHunter() { return false; }

	@ConfigItem(
			keyName = LampLockoutPlugin.ENABLE_FARMING,
			name = "Allow Farming",
			description = "",
			section = membersSection
	)
	default boolean enableFarming() { return false; }

	@ConfigItem(
			keyName = LampLockoutPlugin.ENABLE_CONSTRUCTION,
			name = "Allow Construction",
			description = "",
			section = membersSection
	)
	default boolean enableConstruction() { return false; }

	@ConfigSection(
			name = "F2P skills",
			description = "",
			position = 2
	)
	String freeSection = "freeSkills";

	@ConfigItem(
			keyName = LampLockoutPlugin.ENABLE_MINING,
			name = "Allow Mining",
			description = "",
			section = freeSection
	)
	default boolean enableMining() { return false; }

	@ConfigItem(
			keyName = LampLockoutPlugin.ENABLE_SMITHING,
			name = "Allow Smithing",
			description = "",
			section = freeSection
	)
	default boolean enableSmithing() { return false; }

	@ConfigItem(
			keyName = LampLockoutPlugin.ENABLE_FISHING,
			name = "Allow Fishing",
			description = "",
			section = freeSection
	)
	default boolean enableFishing() { return false; }

	@ConfigItem(
			keyName = LampLockoutPlugin.ENABLE_COOKING,
			name = "Allow Cooking",
			description = "",
			section = freeSection
	)
	default boolean enableCooking() { return false; }

	@ConfigItem(
			keyName = LampLockoutPlugin.ENABLE_FIREMAKING,
			name = "Allow Firemaking",
			description = "",
			section = freeSection
	)
	default boolean enableFiremaking() { return false; }

	@ConfigItem(
			keyName = LampLockoutPlugin.ENABLE_WOODCUTTING,
			name = "Allow Woodcutting",
			description = "",
			section = freeSection
	)
	default boolean enableWoodcutting() { return false; }

	@ConfigItem(
			keyName = LampLockoutPlugin.ENABLE_CRAFTING,
			name = "Allow Crafting",
			description = "",
			section = freeSection
	)
	default boolean enableCrafting() { return false; }

	@ConfigItem(
			keyName = LampLockoutPlugin.ENABLE_RUNECRAFT,
			name = "Allow Runecraft",
			description = "",
			section = freeSection
	)
	default boolean enableRunecraft() { return false; }
}
