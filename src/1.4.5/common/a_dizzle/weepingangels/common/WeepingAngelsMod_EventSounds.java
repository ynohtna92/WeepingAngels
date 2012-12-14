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
			event.manager.soundPoolSounds.addSound("angel/crumble.ogg", WeepingAngelsMod.class.getResource("mod/sound/mob/angel/crumble.ogg"));
			event.manager.soundPoolSounds.addSound("angel/light.ogg", WeepingAngelsMod.class.getResource("mod/sound/mob/angel/light.ogg"));
			event.manager.soundPoolSounds.addSound("angel/stone1.ogg", WeepingAngelsMod.class.getResource("mod/sound/mob/angel/stone1.ogg"));
			event.manager.soundPoolSounds.addSound("angel/stone2.ogg", WeepingAngelsMod.class.getResource("mod/sound/mob/angel/stone2.ogg"));
			event.manager.soundPoolSounds.addSound("angel/stone3.ogg", WeepingAngelsMod.class.getResource("mod/sound/mob/angel/stone3.ogg"));
			event.manager.soundPoolSounds.addSound("angel/stone4.ogg", WeepingAngelsMod.class.getResource("mod/sound/mob/angel/stone4.ogg"));
			event.manager.soundPoolSounds.addSound("angel/teleport_activate.ogg", WeepingAngelsMod.class.getResource("mod/sound/mob/angel/teleport_activate.ogg"));
		}
		catch(Exception e)
		{
			FMLLog.log(Level.SEVERE, e, "Weeping Angels Mod failed to register one or more sounds.");
			FMLLog.severe(e.getMessage());
		}
	}
}
