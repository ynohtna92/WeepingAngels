package a_dizzle.weepingangels.client;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.src.GuiScreen;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class ClientTickHandler implements ITickHandler{

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		if(type.equals(EnumSet.of(TickType.RENDER)))
		{
			onRenderTick();
		}
		else if(type.equals(EnumSet.of(TickType.CLIENT)))
		{
			GuiScreen guiscreen = Minecraft.getMinecraft().currentScreen;
			if(guiscreen != null)
			{
				onTickInGUI(guiscreen);
			}
			else
			{
				onTickInGame();
			}
		}
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.RENDER, TickType.CLIENT);
	}

	@Override
	public String getLabel() {
		return null;
	}	
	
	private void onRenderTick() {
		// TODO Auto-generated method stub	
	}
	
	private void onTickInGUI(GuiScreen guiscreen)
	{
		//TODO
	}

	private void onTickInGame()
	{
		//TODO
	}
}
