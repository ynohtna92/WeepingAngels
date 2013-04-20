package a_dizzle.weepingangels.common;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.util.AxisAlignedBB;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.MathHelper;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;

public class BlockWeepingAngelSpawn extends Block{

	public BlockWeepingAngelSpawn(int i, int j)
	{
		super(i,Material.rock);
	}

	/*public int idDropped(int i, Random random, int j)
    {
		return mod_WeepingAngel.statue.shiftedIndex;
    }*/

	public void onNeighborBlockChange(World world, int i, int j, int k, int l)
	{
		if(l > 0 && Block.blocksList[l].canProvidePower() && world.isBlockIndirectlyGettingPowered(i, j, k))
		{
			int a1 = world.getEntitiesWithinAABB(EntityWeepingAngel.class, AxisAlignedBB.getBoundingBox(i, j, k, i + 1, j + 1, k + 1).expand(2D, 2D, 2D)).size();
			if(a1 < 1){
				spawnWeepingAngel(world, i, j ,k);
			}
		}
	}

	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLiving entityliving)
	{
		int l = MathHelper.floor_double((double)((entityliving.rotationYaw * 4F) / 360F) + 2.5D) & 3;
		world.setBlockMetadataWithNotify(i, j, k, l, 3);
	}

	public void spawnWeepingAngel(World world, double i, double j, double k)
	{
		Random rand = new Random();
		int i1 = world.getBlockMetadata((int)i,(int)j, (int)k);
		EntityWeepingAngel ewp = new EntityWeepingAngel(world);
		ewp.setLocationAndAngles(i+0.5, j+1, k + 0.5,(float)(rand.nextInt(15)* 360) / 16f,0f);
		world.spawnEntityInWorld(ewp);
	}
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("stone");
    }
}