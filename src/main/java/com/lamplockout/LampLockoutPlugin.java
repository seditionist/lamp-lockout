package com.lamplockout;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.Skill;
import net.runelite.api.events.WidgetLoaded;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
@PluginDescriptor(
	name = "Lamp Lockout",
	description = "Remove skill options from lamp interface.",
	tags = { "lamp", "lamps", "xp" }
)
public class LampLockoutPlugin extends Plugin
{
	static final String CONFIG_GROUP = "Lamp-Lockout";

	static final String ENABLE_ATTACK = "enableAttack";
	static final String ENABLE_DEFENCE = "enableDefence";
	static final String ENABLE_STRENGTH = "enableStrength";
	static final String ENABLE_HITPOINTS = "enableHitpoints";
	static final String ENABLE_RANGED = "enableRanged";
	static final String ENABLE_PRAYER = "enablePrayer";
	static final String ENABLE_MAGIC = "enableMagic";
	static final String ENABLE_COOKING = "enableCooking";
	static final String ENABLE_WOODCUTTING = "enableWoodcutting";
	static final String ENABLE_FLETCHING = "enableFletching";
	static final String ENABLE_FISHING = "enableFishing";
	static final String ENABLE_FIREMAKING = "enableFiremaking";
	static final String ENABLE_CRAFTING = "enableCrafting";
	static final String ENABLE_SMITHING = "enableSmithing";
	static final String ENABLE_MINING = "enableMining";
	static final String ENABLE_HERBLORE = "enableHerblore";
	static final String ENABLE_AGILITY = "enableAgility";
	static final String ENABLE_THIEVING = "enableThieving";
	static final String ENABLE_SLAYER = "enableSlayer";
	static final String ENABLE_FARMING = "enableFarming";
	static final String ENABLE_RUNECRAFT = "enableRunecraft";
	static final String ENABLE_HUNTER = "enableHunter";
	static final String ENABLE_CONSTRUCTION = "enableConstruction";

	private static final int LAMP_WIDGET = 240;
	private static final int LAMP_WIDGET_ID = WidgetInfo.PACK(LAMP_WIDGET, 0);

	private static final Map<String, Skill> SKILLS = new HashMap<>();

	static {
		for (Skill skill : Skill.values())
			SKILLS.put(skill.getName(), skill);
	}

	private final Set<Skill> enabledSkills = EnumSet.noneOf(Skill.class);

	@Inject
	private Client client;

	@Inject
	private ClientThread clientThread;

	@Inject
	private LampLockoutConfig config;

	@Override
	protected void startUp() {
		log.info("Lamp-Lockout started!");

		loadEnabledSkills();

		if (client.getGameState() == GameState.LOGGED_IN)
			clientThread.invoke(this::start);
	}

	@Override
	protected void shutDown() {
		updateInterface(true);

		log.info("Lamp-Lockout stopped!");
	}

	private void start() {
		updateInterface();
	}

	@Subscribe
	public  void onConfigChanged(ConfigChanged event) {
		if (!event.getGroup().equals(CONFIG_GROUP))
			return;

		boolean enabled = Boolean.TRUE.toString().equals(event.getNewValue());
		switch (event.getKey()) {
			case ENABLE_ATTACK:
				updateEnabledSkills(enabled, Skill.ATTACK);
				break;
			case ENABLE_DEFENCE:
				updateEnabledSkills(enabled, Skill.DEFENCE);
				break;
			case ENABLE_STRENGTH:
				updateEnabledSkills(enabled, Skill.STRENGTH);
				break;
			case ENABLE_HITPOINTS:
				updateEnabledSkills(enabled, Skill.HITPOINTS);
				break;
			case ENABLE_RANGED:
				updateEnabledSkills(enabled, Skill.RANGED);
				break;
			case ENABLE_PRAYER:
				updateEnabledSkills(enabled, Skill.PRAYER);
				break;
			case ENABLE_MAGIC:
				updateEnabledSkills(enabled, Skill.MAGIC);
				break;
			case ENABLE_COOKING:
				updateEnabledSkills(enabled, Skill.COOKING);
				break;
			case ENABLE_WOODCUTTING:
				updateEnabledSkills(enabled, Skill.WOODCUTTING);
				break;
			case ENABLE_FLETCHING:
				updateEnabledSkills(enabled, Skill.FLETCHING);
				break;
			case ENABLE_FISHING:
				updateEnabledSkills(enabled, Skill.FISHING);
				break;
			case ENABLE_FIREMAKING:
				updateEnabledSkills(enabled, Skill.FIREMAKING);
				break;
			case ENABLE_CRAFTING:
				updateEnabledSkills(enabled, Skill.CRAFTING);
				break;
			case ENABLE_SMITHING:
				updateEnabledSkills(enabled, Skill.SMITHING);
				break;
			case ENABLE_MINING:
				updateEnabledSkills(enabled, Skill.MINING);
				break;
			case ENABLE_HERBLORE:
				updateEnabledSkills(enabled, Skill.HERBLORE);
				break;
			case ENABLE_AGILITY:
				updateEnabledSkills(enabled, Skill.AGILITY);
				break;
			case ENABLE_THIEVING:
				updateEnabledSkills(enabled, Skill.THIEVING);
				break;
			case ENABLE_SLAYER:
				updateEnabledSkills(enabled, Skill.SLAYER);
				break;
			case ENABLE_FARMING:
				updateEnabledSkills(enabled, Skill.FARMING);
				break;
			case ENABLE_RUNECRAFT:
				updateEnabledSkills(enabled, Skill.RUNECRAFT);
				break;
			case ENABLE_HUNTER:
				updateEnabledSkills(enabled, Skill.HUNTER);
				break;
			case ENABLE_CONSTRUCTION:
				updateEnabledSkills(enabled, Skill.CONSTRUCTION);
				break;
		}
	}

	@Subscribe
	public void onWidgetLoaded(WidgetLoaded widgetLoaded) {
		int groupID = widgetLoaded.getGroupId();
		if (groupID != LAMP_WIDGET)
			return;

		updateInterface();
	}

	private void updateInterface() {
		updateInterface(false);
	}

	private void updateInterface(boolean showAll) {
		Widget widget = client.getWidget(LAMP_WIDGET_ID);
		if (widget == null)
			return;

		Widget[] children = widget.getStaticChildren();
		if (children == null)
			return;

		for (Widget child : children) {

			String[] actions = child.getActions();
			if (actions == null)
				continue;

			String action = actions[0];
			Skill skill = SKILLS.get(action);
			if (skill == null)
				continue;

			child.setHidden(!enabledSkills.contains(skill) && !showAll);
		}
	}

	private void updateEnabledSkills(boolean enabled, Skill skill) {
		if (enabled)
			enabledSkills.add(skill);
		else
			enabledSkills.remove(skill);

		updateInterface();
	}

	private void loadEnabledSkills() {
		enabledSkills.clear();

		if (config.enableAttack()) enabledSkills.add(Skill.ATTACK);
		if (config.enableDefence()) enabledSkills.add(Skill.DEFENCE);
		if (config.enableStrength()) enabledSkills.add(Skill.STRENGTH);
		if (config.enableHitpoints()) enabledSkills.add(Skill.HITPOINTS);
		if (config.enableRanged()) enabledSkills.add(Skill.RANGED);
		if (config.enablePrayer()) enabledSkills.add(Skill.PRAYER);
		if (config.enableMagic()) enabledSkills.add(Skill.MAGIC);
		if (config.enableCooking()) enabledSkills.add(Skill.COOKING);
		if (config.enableWoodcutting()) enabledSkills.add(Skill.WOODCUTTING);
		if (config.enableFletching()) enabledSkills.add(Skill.FLETCHING);
		if (config.enableFishing()) enabledSkills.add(Skill.FISHING);
		if (config.enableFiremaking()) enabledSkills.add(Skill.FIREMAKING);
		if (config.enableCrafting()) enabledSkills.add(Skill.CRAFTING);
		if (config.enableSmithing()) enabledSkills.add(Skill.SMITHING);
		if (config.enableMining()) enabledSkills.add(Skill.MINING);
		if (config.enableHerblore()) enabledSkills.add(Skill.HERBLORE);
		if (config.enableAgility()) enabledSkills.add(Skill.AGILITY);
		if (config.enableThieving()) enabledSkills.add(Skill.THIEVING);
		if (config.enableSlayer()) enabledSkills.add(Skill.SLAYER);
		if (config.enableFarming()) enabledSkills.add(Skill.FARMING);
		if (config.enableRunecraft()) enabledSkills.add(Skill.RUNECRAFT);
		if (config.enableHunter()) enabledSkills.add(Skill.HUNTER);
		if (config.enableConstruction()) enabledSkills.add(Skill.CONSTRUCTION);
	}

	@Provides
	LampLockoutConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(LampLockoutConfig.class);
	}
}
