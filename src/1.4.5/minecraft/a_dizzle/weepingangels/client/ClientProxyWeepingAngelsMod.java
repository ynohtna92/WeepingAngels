package a_dizzle.weepingangels.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.registry.TickRegistry;
import a_dizzle.weepingangels.client.RenderWeepingAngelStatue;
import a_dizzle.weepingangels.common.CommonProxyWeepingAngelsMod;
import a_dizzle.weepingangels.common.EntityStatue;
import a_dizzle.weepingangels.common.EntityWeepingAngel;
import a_dizzle.weepingangels.common.TileEntityPlinth;

public class ClientProxyWeepingAngelsMod extends CommonProxyWeepingAngelsMod{
	
	@Override
	public void registerRenderThings()
	{
		TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);
		
		RenderingRegistry.registerEntityRenderingHandler(EntityWeepingAngel.class, new RenderWeepingAngel(0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityStatue.class, new RenderWeepingAngelStatue());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPlinth.class, new TileEntityPlinthRenderer());
	}
}
