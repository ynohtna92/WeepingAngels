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
		
		WeepingAngelsMod.plinthBlock.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/resources/plinth.png");
		WeepingAngelsMod.statue.setIconIndex(ModLoader.addOverride("/gui/items.png", "/resources/statue.png"));
	}

	public void preInit()
	{
		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.EVENT_BUS.register(new WeepingAngelsMod_EventSounds());
		
		MinecraftForgeClient.preloadTexture("/resources/plinth.png");
	    MinecraftForgeClient.preloadTexture("/resources/statue.png");
	    MinecraftForgeClient.preloadTexture("/resources/weepingangel.png");
	    MinecraftForgeClient.preloadTexture("/resources/weepingangel-angry.png");
	    MinecraftForgeClient.preloadTexture("/resources/weepingangelXMAS.png");
	    MinecraftForgeClient.preloadTexture("/resources/weepingangel-angryXMAS.png");
	}
}
package a_dizzle.weepingangels.client;

import net.minecraft.src.ModLoader;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.registry.TickRegistry;
import a_dizzle.weepingangels.client.RenderWeepingAngelStatue;
import a_dizzle.weepingangels.common.CommonProxyWeepingAngelsMod;
import a_dizzle.weepingangels.common.EntityStatue;
import a_dizzle.weepingangels.common.EntityWeepingAngel;
import a_dizzle.weepingangels.common.TileEntityPlinth;
import a_dizzle.weepingangels.common.WeepingAngelsMod;
import a_dizzle.weepingangels.common.WeepingAngelsMod_EventSounds;

public class ClientProxyWeepingAngelsMod extends CommonProxyWeepingAngelsMod{

	@Override
	public void registerRenderThings()
	{
		TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);

		RenderingRegistry.registerEntityRenderingHandler(EntityWeepingAngel.class, new RenderWeepingAngel(0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityStatue.class, new RenderWeepingAngelStatue());

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPlinth.class, new TileEntityPlinthRenderer());
		ModLoader.registerTileEntity(TileEntityPlinth.class, "TileEntityPlinth", new TileEntityPlinthRenderer());
		
		WeepingAngelsMod.plinthBlock.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/resources/plinth.png");
		WeepingAngelsMod.statue.setIconIndex(ModLoader.addOverride("/gui/items.png", "/resources/statue.png"));
	}

	public void preInit()
	{
		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.EVENT_BUS.register(new WeepingAngelsMod_EventSounds());
		
		MinecraftForgeClient.preloadTexture("/resources/plinth.png");
	    MinecraftForgeClient.preloadTexture("/resources/statue.png");
	    MinecraftForgeClient.preloadTexture("/resources/weepingangel.png");
	    MinecraftForgeClient.preloadTexture("/resources/weepingangel-angry.png");
	    MinecraftForgeClient.preloadTexture("/resources/weepingangelXMAS.png");
	    MinecraftForgeClient.preloadTexture("/resources/weepingangel-angryXMAS.png");
	}
}
