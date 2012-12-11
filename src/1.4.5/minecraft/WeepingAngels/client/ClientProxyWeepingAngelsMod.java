package WeepingAngels.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import WeepingAngels.common.CommonProxyWeepingAngelsMod;
import WeepingAngels.common.EntityStatue;
import WeepingAngels.common.EntityWeepingAngel;
import WeepingAngels.common.TileEntityPlinth;

public class ClientProxyWeepingAngelsMod extends CommonProxyWeepingAngelsMod{
	
	@Override
	public void registerRenderThings()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityWeepingAngel.class, new RenderWeepingAngel(0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityStatue.class, new RenderWeepingAngelStatue());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPlinth.class, new TileEntityPlinthRenderer());
	}
}
