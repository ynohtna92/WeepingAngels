package a_dizzle.weepingangels.common;

import java.util.Iterator;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityList;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.INetworkManager;
import net.minecraft.src.ModLoader;
import net.minecraft.src.NBTBase;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.Packet;
import net.minecraft.src.Packet132TileEntityData;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

public class TileEntityPlinth extends TileEntity
{
	public int statueType = WeepingAngelsMod.statue.shiftedIndex;
	public String signText[] = {
			"", ""
	};
	public int lineBeingEdited;
	private boolean isEditable;
	public Entity statueEntity;
	public int rotation;
	public boolean canBeActivated;

	public TileEntityPlinth()
	{
		lineBeingEdited = -1;
		isEditable = true;
		canBeActivated = true; 
	}

	public void updateEntity()
	{
		super.updateEntity();
	}

	public void writeToNBT(NBTTagCompound nbttagcompound)
	{
		super.writeToNBT(nbttagcompound);
		nbttagcompound.setByte("rotation",(byte)(this.rotation & 255));
		nbttagcompound.setString("Text1", signText[0]);
		nbttagcompound.setString("Text2", signText[1]);
		nbttagcompound.setInteger("Type", statueType);
		nbttagcompound.setBoolean("activated", canBeActivated);
		if(statueEntity != null)
		{
			NBTTagCompound var1 = new NBTTagCompound();
			this.statueEntity.writeToNBT(var1);
			nbttagcompound.setTag("entityStored", var1);
		}
	}

	public void readFromNBT(NBTTagCompound nbttagcompound)
	{
		isEditable = false;
		super.readFromNBT(nbttagcompound);
		for(int i = 0; i < 2; i++)
		{
			signText[i] = nbttagcompound.getString((new StringBuilder()).append("Text").append(i + 1).toString());
			if(signText[i].length() > 15)
			{
				signText[i] = signText[i].substring(0, 15);
			}
		}

		this.statueType = nbttagcompound.getInteger("Type");
		this.rotation = nbttagcompound.getByte("rotation");
		this.canBeActivated = nbttagcompound.getBoolean("activated");
		if(nbttagcompound.hasKey("entityStored"))
		{
			this.statueEntity.readFromNBT(nbttagcompound);
		}
		System.out.println("rotation: "+ this.rotation + " Active: " + this.canBeActivated);
	}

	/**
	 * Overriden in a sign to provide the text.
	 */
	public Packet getDescriptionPacket()
	{
		NBTTagCompound var1 = new NBTTagCompound();
		this.writeToNBT(var1);
		return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 4, var1);
	}

	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
	{
		this.readFromNBT(pkt.customParam1);
		/*
		NBTTagCompound compundTag = pkt.customParam1;
		this.statueType = compundTag.getInteger("Type");
		this.rotation = compundTag.getByte("rotation");
		this.signText[0] = compundTag.getString("Text1");
		this.signText[1] = compundTag.getString("Text2");
		this.canBeActivated = compundTag.getBoolean("activated");
		*/
	}

	public void setRotation(int par1)
	{
		this.rotation = par1;
	}
	
	public void setActivated(boolean var1)
	{
		FMLClientHandler.instance().getServer().worldServers[0].setBlockMetadata(this.xCoord, this.yCoord, this.zCoord, var1 ? 1 : 0);
		FMLClientHandler.instance().getClient().theWorld.setBlockMetadata(this.xCoord, this.yCoord, this.zCoord, var1 ? 1 : 0);
	}

	@SideOnly(Side.CLIENT)
	public int getRotation()
	{
		return this.rotation;
	}

	@SideOnly(Side.CLIENT)
	public boolean getActivated()
	{
		return this.canBeActivated;
	}
	
	@SideOnly(Side.CLIENT)
	public Entity getStatueEntity()
	{
		return this.statueEntity;
	}
	
	@SideOnly(Side.CLIENT)
	public int getStatueType()
	{
		return this.statueType;
	}
}
