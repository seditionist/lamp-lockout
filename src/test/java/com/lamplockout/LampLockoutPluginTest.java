package com.lamplockout;

import com.lamplockout.LampLockoutPlugin;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class LampLockoutPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(LampLockoutPlugin.class);
		RuneLite.main(args);
	}
}