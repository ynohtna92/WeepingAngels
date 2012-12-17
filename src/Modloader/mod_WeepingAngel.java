package net.minecraft.src;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import net.minecraft.client.Minecraft;

public class mod_WeepingAngel extends BaseMod
{

	public mod_WeepingAngel()
	{
	}

	public void load()
	{
		getProperties();
		
		plinthBlock = (new BlockPlinth(plinthBlockID, net.minecraft.src.TileEntityPlinth.class, Material.rock)).setHardness(2.0F).setResistance(10F).setStepSound(Block.soundStoneFootstep).setBlockName("Plinth");
		blockWeepingAngelSpawn = new BlockWeepingAngelSpawn(spawnBlockID, 1).setHardness(0.5F).setBlockName("weepingangelspawn");	
		statue = (new ItemStatue(statueItemID, net.minecraft.src.EntityStatue.class)).setItemName("Statue");
		
		//Weeping Angel Entity
		ModLoader.registerEntityID(net.minecraft.src.EntityWeepingAngel.class, "Weeping Angel", ModLoader.getUniqueEntityId(), 0, 0xffffff);
		if(spawnrate != 0){
			ModLoader.addSpawn("Weeping Angel", spawnrate, 1, 3, EnumCreatureType.monster);
		}

		//Spawn Block Entity
		ModLoader.registerBlock(blockWeepingAngelSpawn);		
		ModLoader.addName(blockWeepingAngelSpawn, "Weeping Angel Spawn Block");

		//Statue Block and Item
		ModLoader.registerBlock(plinthBlock);
		plinthBlock.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/angels/plinth.png");
		statue.iconIndex = ModLoader.addOverride("/gui/items.png", "/angels/statue.png");
		ModLoader.addName(plinthBlock, "Plinth");
		ModLoader.addName(statue, "Weeping Angel Statue");

		ModLoader.registerTileEntity(net.minecraft.src.TileEntityPlinth.class, "TileEntityPlinth", new TileEntityPlinthRenderer());	
	}

	public String getVersion()
	{
		return "[1.2.5] Weeping Angels v1.5.4";
	}

	public void addRenderer(Map map)
	{
		map.put(EntityWeepingAngel.class, new RenderWeepingAngel(0.5F));
		map.put(net.minecraft.src.EntityStatue.class, new RenderWeepingAngelStatue());
	}
	
	private static void getProperties() {
	    System.out.println("Getting Properties");
	    Properties properties = new Properties();
	    try {
	      String path = Minecraft.getMinecraftDir() + "/mods/weepingAngels.properties";

	      properties.load(new FileInputStream(path));
	      String spawnRate = properties.getProperty("spawnrate");
	      spawnrate = Integer.parseInt(spawnRate.trim());
	      String canteleport = properties.getProperty("teleport");
	      canTeleport = Boolean.parseBoolean(canteleport.trim());
	      String plinthID = properties.getProperty("plinthBlockID");
	      plinthBlockID = Integer.parseInt(plinthID.trim());
	      String spawnID = properties.getProperty("spawnBlockID");
	      spawnBlockID = Integer.parseInt(spawnID.trim());
	      String statueitemID = properties.getProperty("statueItemID");
	      statueItemID = Integer.parseInt(statueitemID.trim());
	    }
	    catch (IOException e)
	    {
	      System.out.println(e.getMessage());
	      canTeleport = true;
	      spawnrate = 2;
	      plinthBlockID = 255;
	      spawnBlockID = 207;
	      statueItemID = 1234;
	    }
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
