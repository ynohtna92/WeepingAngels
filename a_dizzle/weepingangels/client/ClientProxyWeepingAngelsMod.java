package a_dizzle.weepingangels.client;

import net.minecraft.src.ModLoader;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import a_dizzle.weepingangels.common.CommonProxyWeepingAngelsMod;
import a_dizzle.weepingangels.common.EntityStatue;
import a_dizzle.weepingangels.common.EntityWeepingAngel;
import a_dizzle.weepingangels.common.TileEntityPlinth;
import a_dizzle.weepingangels.common.WeepingAngelsMod;
import a_dizzle.weepingangels.common.WeepingAngelsMod_EventSounds;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxyWeepingAngelsMod extends CommonProxyWeepingAngelsMod{

	@Override
	public void registerRenderThings()
	{
		TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);

		RenderingRegistry.registerEntityRenderingHandler(EntityWeepingAngel.class, new RenderWeepingAngel(0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityStatue.class, new RenderWeepingAngelStatue());

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPlinth.class, new TileEntityPlinthRenderer());
		ModLoader.registerTileEntity(TileEntityPlinth.class, "TileEntityPlinth", new TileEntityPlinthRenderer());
		
	}

	public void preInit()
	{
		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.EVENT_BUS.register(new WeepingAngelsMod_EventSounds());
	}
}
