package net.minecraft.src;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package net.minecraft.src:
//            BlockContainer, mod_statues, Item, Block, 
//            World, Material, TileEntityPlinth, EntityStatue, 
//            Entity, EntityLiving, ModLoader, TileEntity

public class BlockPlinth extends BlockContainer
{

    protected BlockPlinth(int i, Class class1, Material material)
    {
        super(i, material);
        idDrop = 0;
        signEntityClass = class1;
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
    }

    public int idDropped(int i, Random random, int j)
    {
    	if(idDrop == 0)
    	{
    		return mod_WeepingAngel.statue.shiftedIndex;
    	}
        return idDrop;
    }

    public void onNeighborBlockChange(World world, int i, int j, int k, int l)
    {
        if(l > 0 && Block.blocksList[l].canProvidePower())
        {
            boolean flag = world.isBlockIndirectlyGettingPowered(i, j, k) || world.isBlockIndirectlyGettingPowered(i, j + 1, k);
            if(flag)
            {
                world.scheduleBlockUpdate(i, j, k, blockID, tickRate());
            }
        }
    }

    public boolean canPlaceBlockAt(World world, int i, int j, int k)
    {
        int l = world.getBlockId(i, j, k);
        int i1 = world.getBlockId(i, j + 1, k);
        int j1 = world.getBlockId(i, j + 2, k);
        return l == 0 && i1 == 0 && (j1 == 0 || world.getBlockMaterial(i, j + 2, k) == Material.circuits);
    }

    public void updateTick(World world, int i, int j, int k, Random random)
    {
        if(world.isBlockIndirectlyGettingPowered(i, j, k) || world.isBlockIndirectlyGettingPowered(i, j + 1, k))
        {
            ComeToLife(world, i, j, k);
        }
    }

    private void ComeToLife(World world, int i, int j, int k)
    {
        TileEntityPlinth tileentityplinth = (TileEntityPlinth)world.getBlockTileEntity(i, j, k);
        if(tileentityplinth.statueEntity != null)
        {
        	float yaw = (float)tileentityplinth.statueEntity.rotationYaw;
            EntityStatue entitystatue = (EntityStatue)tileentityplinth.statueEntity;
            idDrop = entitystatue.dropId;
            if(entitystatue.canBeActivated)
            {
                tileentityplinth.statueEntity.setDead();
                EntityLiving entityliving = null;
                Class class1 = entitystatue.livingEntity;
                try
                {
                    entityliving = (EntityLiving)class1.getDeclaredConstructors()[0].newInstance(new Object[] {
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
                entityliving.setPositionAndRotation(i + 0.5, j + 0.5, k + 0.5, 0.0f, 0.0F);
                //world.entityJoinedWorld(entityliving);
                world.spawnEntityInWorld(entityliving);
                entityliving.setRotation(yaw, 0);
                entitystatue.canBeActivated = false;
            }
        }
    }

    public void onBlockPlaced(World world, int i, int j, int k, int l)
    {
        super.onBlockPlaced(world, i, j, k, l);
    }

    public void onBlockRemoval(World world, int i, int j, int k)
    {
        TileEntityPlinth tileentityplinth = (TileEntityPlinth)world.getBlockTileEntity(i, j, k);
        if(tileentityplinth.statueEntity != null)
        {
            idDrop = ((EntityStatue)tileentityplinth.statueEntity).dropId;
            tileentityplinth.statueEntity.setDead();
        }
        super.onBlockRemoval(world, i, j, k);
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public TileEntity getBlockEntity()
    {
        try
        {
            return (TileEntity)signEntityClass.newInstance();
        }
        catch(Exception exception)
        {
            throw new RuntimeException(exception);
        }
    }

    private Class signEntityClass;
    private int idDrop;
}