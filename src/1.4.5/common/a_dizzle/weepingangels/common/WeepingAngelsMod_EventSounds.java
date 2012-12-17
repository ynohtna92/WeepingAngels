package a_dizzle.weepingangels.common;

import java.util.logging.Level;

import cpw.mods.fml.common.FMLLog;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class WeepingAngelsMod_EventSounds {

	@ForgeSubscribe
	public void onSound(SoundLoadEvent event)
	{
		try
		{
			event.manager.soundPoolSounds.addSound("resources/crumble.ogg", WeepingAngelsMod.class.getResource("/resources/crumble.ogg"));
			event.manager.soundPoolSounds.addSound("resources/light.ogg", WeepingAngelsMod.class.getResource("/resourceslight.ogg"));
			event.manager.soundPoolSounds.addSound("resources/stone1.ogg", WeepingAngelsMod.class.getResource("/resources/stone1.ogg"));
			event.manager.soundPoolSounds.addSound("resources/stone2.ogg", WeepingAngelsMod.class.getResource("/resources/stone2.ogg"));
			event.manager.soundPoolSounds.addSound("resources/stone3.ogg", WeepingAngelsMod.class.getResource("/resources/stone3.ogg"));
			event.manager.soundPoolSounds.addSound("resources/stone4.ogg", WeepingAngelsMod.class.getResource("/resources/stone4.ogg"));
			event.manager.soundPoolSounds.addSound("resources/teleport_activate.ogg", WeepingAngelsMod.class.getResource("/resources/teleport_activate.ogg"));
		}
		catch(Exception e)
		{
			FMLLog.log(Level.SEVERE, e, "Weeping Angels Mod failed to register one or more sounds.");
			FMLLog.severe(e.getMessage());
		}
	}
}
