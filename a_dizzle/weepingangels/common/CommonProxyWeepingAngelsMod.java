package a_dizzle.weepingangels.common;

import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxyWeepingAngelsMod {

	public void registerRenderThings()
	{
		GameRegistry.registerTileEntity(TileEntityPlinth.class, "TileEntityPlinth");
	}

	public void preInit()
	{
	}
}
