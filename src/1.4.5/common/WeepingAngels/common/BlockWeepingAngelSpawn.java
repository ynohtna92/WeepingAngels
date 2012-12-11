package WeepingAngels.common;

import java.util.List;
import java.util.Random;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.Material;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModLoader;
import net.minecraft.src.World;

public class BlockWeepingAngelSpawn extends Block{

	public BlockWeepingAngelSpawn(int i, int j)
	{
		super(i,j,Material.rock);
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
		world.setBlockMetadataWithNotify(i, j, k, l);
	}

	public void spawnWeepingAngel(World world, double i, double j, double k)
	{
		Random rand = new Random();
		int i1 = world.getBlockMetadata((int)i,(int)j, (int)k);
		EntityWeepingAngel ewp = new EntityWeepingAngel(world);
		ewp.setLocationAndAngles(i+0.5, j+1, k + 0.5,rand.nextFloat() * 360f,0f);
		world.spawnEntityInWorld(ewp);
	}	
}