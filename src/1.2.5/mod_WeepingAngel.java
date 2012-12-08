package net.minecraft.src;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

import net.minecraft.client.Minecraft;
@Mod(modid = "weepingangel", name = "Weeping Angels Mod")//modid is a unique identifier for Forge, should not change
@NetworkMod(clientSideRequired = true, serverSideRequired = false)//common settings for a mod way to react to server
public class mod_WeepingAngel
{
@Instance("weepingangel")
	public static mod_WeepingAngel instance;
@SidedProxy(clientSide="a_dizzle.weepingangels.client.WeepingAngelClientProxy", serverSide="a_dizzle.weepingangels.common.WeepingAngelCommonProxy")
	public static WeepingAngelCommonProxy proxy;//proxies are commonly used to distinguish between client and server side registration and rendering
@PreInit//refers to minecraft preloading phase

	public void preInit(FMLPreInitializationEvent event)
	{
		proxy.preInit();//method inside proxies used to preload textures and sounds for example
	
			Configuration config = new Configuration(event.getSuggestedConfigurationFile());// find the config file in .minecraft/config/
			try
	        { 
			config.load();// loading the configuration from its file
        plinthBlockID = config.get(Configuration.CATEGORY_GENERAL, "PlinthBlockID", 3023).getInt();//getting the values from the config file, or using default values
        spawnBlockID = config.get(Configuration.CATEGORY_BLOCK, "SpawnBlockID", 3024).getInt();
        canTeleport = config.get(Configuration.CATEGORY_GENERAL, "Can Teleport", true).getBoolean(true);
        spawnrate = config.get(Configuration.CATEGORY_GENERAL, "Angel Spawnrate", 5).getInt();
        EntityWeepingAngelID = config.get(Configuration.CATEGORY_GENERAL, "EntityWeepingAngelID", 300).getInt();
        statueItemID = config.get(Configuration.CATEGORY_GENERAL, "StatueItemID", 12034).getInt();
	        }catch (Exception e)
			{
				FMLLog.log(Level.SEVERE, e, "Weeping Angels mod failed loading its configuration");
				FMLLog.severe(e.getMessage());				
			}
			finally{
        	config.save();// saving the configuration to its file
			}
       
	}
@Init//refers to minecraft loading phase
	public void init(FMLInitializationEvent event) {
		plinthBlock = (new BlockPlinth(plinthBlockID, TileEntityPlinth.class, Material.rock)).setHardness(2.0F).setResistance(10F).setStepSound(Block.soundStoneFootstep).setBlockName("Plinth");		
		blockWeepingAngelSpawn = new BlockWeepingAngelSpawn(spawnBlockID, 1).setHardness(0.5F).setBlockName("weepingangelspawn");	
		statue = (new ItemStatue(statueItemID, net.minecraft.src.EntityStatue.class)).setItemName("Statue");
		
		//Weeping Angel Entity
		EntityRegistry.registerModEntity(EntityWeepingAngel.class, "Weeping Angel", 1, this, 80, 3, true);
		EntityList.IDtoClassMapping.put(EntityWeepingAngelID, EntityWeepingAngel.class);//Forge way to register mob entity
		EntityList.entityEggs.put(EntityWeepingAngelID, new EntityEggInfo(EntityWeepingAngelID, 0, 0xffffff));//to add eggs
		if(spawnrate != 0){
			EntityRegistry.addSpawn("WeepingAngel", spawnrate, 1, 3, EnumCreatureType.monster);
		}

		//Spawn Block Entity
		blockWeepingAngelSpawn = new BlockWeepingAngelSpawn(spawnBlockID, 1);	
		LanguageRegistry.addName(blockWeepingAngelSpawn, "Weeping Angel Spawn Block");

		//Statue Block and Item
		GameRegistry.registerBlock(plinthBlock);
		GameRegistry.registerBlock(blockWeepingAngelSpawn);
		plinthBlock.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/angels/plinth.png");//this modloader method can be changed to prevent
		statue.iconIndex = ModLoader.addOverride("/gui/items.png", "/angels/statue.png");	// the use of terrain indices, but it is debateable
		LanguageRegistry.addName(plinthBlock, "Plinth");
		LanguageRegistry.addName(statue,"Weeping Angel Statue");

		ModLoader.registerTileEntity(TileEntityPlinth.class, "TileEntityPlinth", new TileEntityPlinthRenderer());
		proxy.registerRenderThings();//method to finish some registering and rendering on client side
	}


@PostInit//refers to minecraft closing phase
	public static void postInit(FMLPostInitializationEvent event)
	{	
	}
	
	public static int spawnrate;
	public static boolean canTeleport;
	public static int plinthBlockID;
	public static int spawnBlockID;
	public static int statueItemID;
	public static Block plinthBlock;
	public static Block blockWeepingAngelSpawn;
	public static Item statue;
	
}
