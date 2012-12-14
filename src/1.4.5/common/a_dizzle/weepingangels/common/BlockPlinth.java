package a_dizzle.weepingangels.common;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import java.util.logging.Level;

import cpw.mods.fml.common.FMLLog;

import net.minecraft.src.Block;
import net.minecraft.src.BlockContainer;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

public class BlockPlinth extends BlockContainer
{
    private Class signEntityClass;
    private int idDrop;
	
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
    		return WeepingAngelsMod.statue.shiftedIndex;
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

    @Override
    public void updateTick(World world, int i, int j, int k, Random random)
    {
        if(world.isBlockIndirectlyGettingPowered(i, j, k) || world.isBlockIndirectlyGettingPowered(i, j + 1, k))
        {
        	System.out.println("Powered");
            ComeToLife(world, i, j, k);
        }
    }

    private void ComeToLife(World world, int i, int j, int k)
    {		
    	TileEntityPlinth tileentityplinth = (TileEntityPlinth)world.getBlockTileEntity(i, j, k);
 		if(tileentityplinth.statueEntity != null)
        {
            EntityStatue entitystatue = (EntityStatue)tileentityplinth.statueEntity;
            idDrop = entitystatue.dropId;
            if(tileentityplinth.canBeActivated)
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
                	FMLLog.log(Level.SEVERE, instantiationexception.getMessage());
                }
                catch(IllegalAccessException illegalaccessexception)
                {
                	FMLLog.log(Level.SEVERE, illegalaccessexception.getMessage());
                }
                catch(IllegalArgumentException illegalargumentexception)
                {
                	FMLLog.log(Level.SEVERE, illegalargumentexception.getMessage());
                }
                catch(InvocationTargetException invocationtargetexception)
                {
                	FMLLog.log(Level.SEVERE, invocationtargetexception.getMessage());
                }
                catch(SecurityException securityexception)
                {
                	FMLLog.log(Level.SEVERE, securityexception.getMessage());
                }
                entityliving.setPositionAndRotation(i + 0.5, j + 0.5, k + 0.5, (float)(tileentityplinth.getRotation()* 360) / 16f, 0.0F);
                //world.entityJoinedWorld(entityliving);
                world.spawnEntityInWorld(entityliving);
                tileentityplinth.canBeActivated = false;
            }
        }
    }

    @Override
    public void onBlockAdded(World world, int i, int j, int k)
    {
        super.onBlockAdded(world, i, j, k);
    }

    @Override
    public void breakBlock(World world, int i, int j, int k, int par5, int par6)
    {
    	if(WeepingAngelsMod.DEBUG) System.out.println("BlockPlinth broken at i: " + i + " j: " + j + " k: " + k);
        TileEntityPlinth tileentityplinth = (TileEntityPlinth)world.getBlockTileEntity(i, j, k);
        if(WeepingAngelsMod.DEBUG) System.out.println(tileentityplinth.statueEntity);
        if(tileentityplinth.statueEntity != null)
        {
            idDrop = ((EntityStatue)tileentityplinth.statueEntity).dropId;
            if(WeepingAngelsMod.DEBUG) System.out.println("Dropped item: " + idDrop);
            tileentityplinth.statueEntity.setDead();
        }
        super.breakBlock(world, i, j, k, par5, par6);
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World war1)
    {
        try
        {
            return new TileEntityPlinth();
        }
        catch(Exception exception)
        {
            throw new RuntimeException(exception);
        }
    }
}
