package a_dizzle.weepingangels.common;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;

import cpw.mods.fml.common.FMLLog;

import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MathHelper;
import net.minecraft.src.World;

public class ItemStatue extends Item
{
	private Class statue;
	private int armorId;
	public int statueYaw = 0;

	protected ItemStatue(int i, Class class1)
	{
		super(i);
		statue = class1;
	}

	protected ItemStatue(int i, Class class1, int j)
	{
		this(i, class1);
		armorId = j;
	}
	
	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l, float par8, float par9, float par10)
	{
		if(l == 0)
		{
			return false;
		}
		if(!world.getBlockMaterial(i, j, k).isSolid())
		{
			return false;
		}
		if(l == 1)
		{
			j++;
		}
		if(l == 2)
		{
			k--;
		}
		if(l == 3)
		{
			k++;
		}
		if(l == 4)
		{
			i--;
		}
		if(l == 5)
		{
			i++;
		}
		if(!WeepingAngelsMod.plinthBlock.canPlaceBlockAt(world, i, j, k))
		{
			return false;
		}
		if(l == 1)
		{
			statueYaw = MathHelper.floor_double((double)((entityplayer.rotationYaw + 180f) * 16.0F / 360.0F) + 0.5D) & 15;
			world.setBlockAndMetadataWithNotify(i, j, k, WeepingAngelsMod.plinthBlock.blockID, 1);
		} else
		{
			world.setBlockAndMetadataWithNotify(i, j, k, WeepingAngelsMod.plinthBlock.blockID, l);
		}
		EntityStatue entitystatue = null;
		try
		{
			entitystatue = (EntityStatue)statue.getDeclaredConstructors()[0].newInstance(new Object[] {
					world
			});
		}
		catch(InstantiationException instantiationexception)
		{
			FMLLog.log(Level.SEVERE, instantiationexception.getMessage());
		}
		catch(IllegalAccessException illegalaccessexception)
		{
			FMLLog.getLogger().log(Level.SEVERE, illegalaccessexception.getMessage());
		}
		catch(IllegalArgumentException illegalargumentexception)
		{
			FMLLog.getLogger().log(Level.SEVERE, illegalargumentexception.getMessage());
		}
		catch(InvocationTargetException invocationtargetexception)
		{
			FMLLog.getLogger().log(Level.SEVERE, invocationtargetexception.getMessage());
		}
		catch(SecurityException securityexception)
		{
			FMLLog.getLogger().log(Level.SEVERE, securityexception.getMessage());
		}
		TileEntityPlinth tileentityplinth = (TileEntityPlinth)world.getBlockTileEntity(i, j, k);
		tileentityplinth.setRotation(statueYaw);
		tileentityplinth.statueType = entitystatue.dropId;
		itemstack.stackSize--;
		if(WeepingAngelsMod.DEBUG) System.out.println("l: " + l + " yaw: " + statueYaw + " playerYaw: " + entityplayer.rotationYaw);
		/*if(tileentityplinth != null)
        {
            ModLoader.getMinecraftInstance().displayGuiScreen(new GuiEditPlinth(tileentityplinth));
        }*/
        return true;
	}

}