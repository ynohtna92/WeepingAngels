package net.minecraft.src;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minecraft.client.Minecraft;

// Referenced classes of package net.minecraft.src:
//            Item, World, Material, mod_statues, 
//            Block, EntityPlayer, MathHelper, EntityStatue, 
//            ItemStack, ModLoader, TileEntityPlinth, GuiEditPlinth

public class ItemStatue extends Item
{

    protected ItemStatue(int i, Class class1)
    {
        super(i);
        maxStackSize = 64;
        statue = class1;
    }

    protected ItemStatue(int i, Class class1, int j)
    {
        this(i, class1);
        armorId = j;
    }

    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l)
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
        if(!mod_WeepingAngel.plinthBlock.canPlaceBlockAt(world, i, j, k))
        {
            return false;
        }
        if(l == 1)
        {
            world.setBlockAndMetadataWithNotify(i, j, k, mod_WeepingAngel.plinthBlock.blockID, MathHelper.floor_double((double)(((entityplayer.rotationYaw + 180F) * 16F) / 360F) + 0.5D) & 0xf);
        } else
        {
            world.setBlockAndMetadataWithNotify(i, j, k, mod_WeepingAngel.plinthBlock.blockID, l);
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
            ModLoader.getLogger().log(Level.SEVERE, instantiationexception.getMessage());
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            ModLoader.getLogger().log(Level.SEVERE, illegalaccessexception.getMessage());
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            ModLoader.getLogger().log(Level.SEVERE, illegalargumentexception.getMessage());
        }
        catch(InvocationTargetException invocationtargetexception)
        {
            ModLoader.getLogger().log(Level.SEVERE, invocationtargetexception.getMessage());
        }
        catch(SecurityException securityexception)
        {
            ModLoader.getLogger().log(Level.SEVERE, securityexception.getMessage());
        }
        TileEntityPlinth tileentityplinth = (TileEntityPlinth)world.getBlockTileEntity(i, j, k);
        tileentityplinth.statueType = entitystatue.dropId;
        itemstack.stackSize--;
        /*if(tileentityplinth != null)
        {
            ModLoader.getMinecraftInstance().displayGuiScreen(new GuiEditPlinth(tileentityplinth));
        }*/
        return true;
    }

    private Class statue;
    private int armorId;
}
