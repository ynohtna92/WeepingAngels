package a_dizzle.weepingangels.common;

import java.util.List;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.block.Block;
import net.minecraft.util.DamageSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.chunk.*;

public class EntityWeepingAngel extends EntityMob
{
	private int attackStrength;
	private int torchTimer;
	private int torchNextBreak;
	private boolean breakOnePerTick;
	private boolean canSeeSkyAndDay;
	private int randomSoundDelay;
	public boolean armMovement;
	public boolean aggressiveArmMovement;
	private int slowPeriod;
	private int timeTillNextTeleport;
	private int spawntimer;
	private boolean timeLocked;
	private int[] transparentBlocks = { 20, 8, 9 , 10, 11, 18, 27, 
			28, 30, 31, 32, 37, 38, 39, 
			40, 44, 50, 51, 52, 59, 64, 
			65, 66, 67, 69, 70, 71, 72, 75, 
			76, 77, 78, 83, 85, 90, 92, 96, 
			101, 102, 106, 107, 108, 109, 
			111, 113, 114, 114, 117};

	public EntityWeepingAngel(World world)
	{
		super(world);
		this.texture = "/resources/weepingangel.png";
		this.stepHeight = 1.0F;
		this.health = 15;
		this.attackStrength = WeepingAngelsMod.attackStrength;
		this.isImmuneToFire = true;
		torchNextBreak = rand.nextInt(800);
		armMovement = false;
		aggressiveArmMovement = false;
		spawntimer = 5;
		//timeLocked = false;
	}
	
	@Override
    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(16, Byte.valueOf((byte)0)); //Angry
        this.dataWatcher.addObject(17, Byte.valueOf((byte)0)); //ArmMovement
    }
	
	@Override
	public int getMaxHealth()
	{
		return 15;
	}

	@Override
	protected int getDropItemId()
	{
		return 4;
	}

	@Override
	protected void dropFewItems(boolean par1, int par2)
	{
		int i = rand.nextInt(2 + par2);

		for (int k = 0; k < i; k++)
		{
			dropItem(4, 1);
		}
	}

	@Override
	protected void dropRareDrop(int par1)
	{
		dropItem(WeepingAngelsMod.statue.itemID, 1);
	}

	@Override
	protected Entity findPlayerToAttack()
	{
		if(spawntimer < 0){
			EntityPlayer entityplayer = worldObj.getClosestPlayerToEntity(this, 64D);
			if(entityplayer != null && canAngelBeSeenMultiplayer())
			{
				return entityplayer;
			}
			else
			{
				return null;
			}
		} 
		return null;

	}
	
	@Override
	public boolean attackEntityFrom(DamageSource damagesource, int i)
	{
		if(damagesource == null)
		{
			return false;
		}
		if(damagesource.getSourceOfDamage() instanceof EntityPlayer)
		{
			EntityPlayer entityplayer = (EntityPlayer)damagesource.getSourceOfDamage();
			ItemStack itemstack = entityplayer.inventory.getCurrentItem();
			if(worldObj.difficultySetting > 2)
			{
				if(itemstack != null && (itemstack.itemID == Item.pickaxeDiamond.itemID || itemstack.canHarvestBlock(Block.obsidian)))
				{
					super.attackEntityFrom(damagesource, i);
				}
			} else
				if(itemstack != null && (itemstack.itemID == Item.pickaxeDiamond.itemID || itemstack.itemID == Item.pickaxeSteel.itemID || (itemstack.canHarvestBlock(Block.oreDiamond) && (itemstack.itemID != Item.pickaxeGold.itemID))))
				{
					super.attackEntityFrom(damagesource, i);
				}
		}
		return false;
	}

	@Override
	protected void attackEntity(Entity entity, float f)
	{

		if(entityToAttack != null && (entityToAttack instanceof EntityPlayer) && !canAngelBeSeenMultiplayer())
		{
			EntityPlayer entityPlayer = (EntityPlayer)entityToAttack;
			
			//Always attack, but teleport sometimes as specified in the config
			super.attackEntity(entity, f);
			
			if(rand.nextInt(100) <= WeepingAngelsMod.teleportChance)
			{
				if(!entityPlayer.capabilities.isCreativeMode)
				{
					if(getDistancetoEntityToAttack() <= 2)
					{
						worldObj.playSoundEffect(entityToAttack.posX, entityToAttack.posY, entityToAttack.posZ, "mob.ghast.scream", getSoundVolume(), ((rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F) * 1.8F);
						worldObj.playSoundAtEntity(entityToAttack, "resources.teleport_activate", getSoundVolume(), ((rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F) * 1.8F);
						for(int k = 0; k < 5; k++)
						{
							worldObj.spawnParticle("portal", entityToAttack.posX + (rand.nextDouble() - 0.5D) * (double)width, (entityToAttack.posY + rand.nextDouble() * (double)height) - 0.25D, entityToAttack.posZ + (rand.nextDouble() - 0.5D) * (double)width, (rand.nextDouble() - 0.5D) * 2D, -rand.nextDouble(), (rand.nextDouble() - 0.5D) * 2D);
						}					
						
						teleportPlayer(entityToAttack);
						for(int k = 0; k < 5; k++)
						{
							worldObj.spawnParticle("portal", entityToAttack.posX + (rand.nextDouble() - 0.5D) * (double)width, (entityToAttack.posY + rand.nextDouble() * (double)height) - 0.25D, entityToAttack.posZ + (rand.nextDouble() - 0.5D) * (double)width, (rand.nextDouble() - 0.5D) * 2D, -rand.nextDouble(), (rand.nextDouble() - 0.5D) * 2D);
						}
						worldObj.playSoundAtEntity(entityToAttack, "resources.teleport_activate", getSoundVolume(), ((rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F) * 1.8F);
						entityToAttack = null;
					}

				}
			}
		}
	}
	
	@Override
	protected void updateEntityActionState()
	{
		//if(timeLocked){
		//	return;
		//}
		if(entityToAttack != null)
		{
			if(!canAngelBeSeenMultiplayer())
			{
				super.updateEntityActionState();
			}
		}
		else
		{
			super.updateEntityActionState();
		}
	}

	@Override
	public void onUpdate()
	{
		//if(WeepingAngelsMod.DEBUG)System.out.println("EWP Location x: " + this.posX + " y: " + this.posY+ " z: " + this.posZ);
		if(spawntimer >= 0)
			--spawntimer;
		breakOnePerTick = false;
		moveSpeed = entityToAttack != null ? 7F : 0.3F;
		isJumping = false;
		if(worldObj.isDaytime())
		{
			float f = getBrightness(1.0F);
			if(f > 0.5F && worldObj.canBlockSeeTheSky(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ)) && rand.nextFloat() * 30F < (f - 0.4F) * 2.0F)
			{
				canSeeSkyAndDay = true;
			} else
			{
				canSeeSkyAndDay = false;
			}
		}
		if(entityToAttack != null && (entityToAttack instanceof EntityPlayer))
		{
			if(!canAngelBeSeenMultiplayer())
			{
				if((getDistancetoEntityToAttack() > 15D && timeTillNextTeleport-- < 0))
				{
					func_35182_c(entityToAttack);
					worldObj.playSoundAtEntity(this, getMovementSound(), getSoundVolume() * 1.1f, ((rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F) * 1.8F);
					timeTillNextTeleport = rand.nextInt(60) + 20;
				}
				if(WeepingAngelsMod.DEBUG)System.out.println(timeTillNextTeleport);
				if((entityToAttack instanceof EntityPlayer) && getDistancetoEntityToAttack() <= 5D)
				{
					this.texture = "/resources/weepingangel-angry.png";
					this.aggressiveArmMovement = true;
					this.dataWatcher.updateObject(16, Byte.valueOf((byte)1));
					if(WeepingAngelsMod.DEBUG)System.out.println("Angry");
				}
				else
				{
					this.texture = "/resources/weepingangel.png";
					this.aggressiveArmMovement = false;
					this.dataWatcher.updateObject(16, Byte.valueOf((byte)0));
				}
				if((entityToAttack instanceof EntityPlayer) && getDistancetoEntityToAttack() > 5D && rand.nextInt(100) > 80)
				{
					armMovement = !armMovement;
					if(armMovement) 
						this.dataWatcher.updateObject(17, Byte.valueOf((byte)1));
					else
						this.dataWatcher.updateObject(17, Byte.valueOf((byte)0));
				}
			}
			if(worldObj.getFullBlockLightValue(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ)) < 1 && worldObj.getFullBlockLightValue(MathHelper.floor_double(entityToAttack.posX), MathHelper.floor_double(entityToAttack.posY), MathHelper.floor_double(entityToAttack.posZ)) < 1 && randomSoundDelay > 0 && --randomSoundDelay == 0)
			{
				worldObj.playSoundAtEntity(this, "mob.ghast.scream", getSoundVolume(), ((rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F) * 1.8F);
			}
			if(slowPeriod > 0)
			{
				slowPeriod--;
				entityToAttack.motionX *= 0.01D;
				entityToAttack.motionZ *= 0.01D;          	
			}
			//if(checkForOtherAngels())
			//{
			//	System.out.println("TimeLocked");
			//	moveStrafing = moveForward = 0.0F;
			//    moveSpeed = 0.0F;
			//}
			if((entityToAttack instanceof EntityPlayer) && (canAngelBeSeenMultiplayer() || timeLocked))
			{
				angelDirectLook((EntityPlayer)entityToAttack);
				moveStrafing = moveForward = 0.0F;
				moveSpeed = 0.0F;
				torchTimer++;
				if(torchTimer >= torchNextBreak && !canSeeSkyAndDay)
				{
					torchTimer = 0;
					torchNextBreak = rand.nextInt(1000) + 1000;
					findNearestTorch();
				}
			} else
			{
				faceEntity(entityToAttack, 100F, 100F);
			}
		}
		byte var1 = this.dataWatcher.getWatchableObjectByte(16);
        this.texture = var1 == 1 ? "/resources/weepingangel-angry.png" : "/resources/weepingangel.png";
		super.onUpdate();
	}

	private boolean canAngelBeSeen(EntityPlayer entityplayer)
	{
		if(worldObj.getFullBlockLightValue(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ)) < 1)
		{
			randomSoundDelay = rand.nextInt(40);
			return false;
		}
		if(entityplayer.canEntityBeSeen(this) || LineOfSightCheck(entityplayer))
		{
			return isInFieldOfVision(this, entityplayer, 70, 65);
		} else
		{
			return false;
		}
	}
	
	private boolean canAngelBeSeenMultiplayer()
	{
		if(worldObj.getFullBlockLightValue(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ)) < 1)
		{
			randomSoundDelay = rand.nextInt(40);
			return false;
		}
		int i = 0;
		List list = worldObj.getEntitiesWithinAABB(EntityPlayer.class, boundingBox.expand(64D, 20D, 64D));
		for(int j = 0; j < list.size(); j++)
		{
			EntityPlayer entity1 = (EntityPlayer)list.get(j);
			if(entity1 instanceof EntityPlayer)
			{
				if(canAngelBeSeen(entity1))
				{
					i++;
				}
			}
		}
		if(i > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	private boolean LineOfSightCheck(EntityLiving entity)
	{
		return (rayTraceBlocks(Vec3.createVectorHelper(posX, posY + (double)getEyeHeight(), posZ), Vec3.createVectorHelper(entity.posX, entity.posY + (double)entity.getEyeHeight(), entity.posZ)) == null)
				|| (rayTraceBlocks(Vec3.createVectorHelper(posX, posY + height, posZ), Vec3.createVectorHelper(entity.posX, entity.posY + (double)entity.getEyeHeight(), entity.posZ)) == null)
				|| (rayTraceBlocks(Vec3.createVectorHelper(posX, posY + (height * 0.1), posZ), Vec3.createVectorHelper(entity.posX, entity.posY + (double)entity.getEyeHeight(), entity.posZ)) == null)
				|| (rayTraceBlocks(Vec3.createVectorHelper(posX + 0.7, posY + (double)getEyeHeight(), posZ), Vec3.createVectorHelper(entity.posX, entity.posY + (double)entity.getEyeHeight(), entity.posZ)) == null)
				|| (rayTraceBlocks(Vec3.createVectorHelper(posX - 0.7, posY + (double)getEyeHeight(), posZ), Vec3.createVectorHelper(entity.posX, entity.posY + (double)entity.getEyeHeight(), entity.posZ)) == null)
				|| (rayTraceBlocks(Vec3.createVectorHelper(posX, posY + (double)getEyeHeight(), posZ + 0.7), Vec3.createVectorHelper(entity.posX, entity.posY + (double)entity.getEyeHeight(), entity.posZ)) == null)
				|| (rayTraceBlocks(Vec3.createVectorHelper(posX, posY + (double)getEyeHeight(), posZ - 0.7), Vec3.createVectorHelper(entity.posX, entity.posY + (double)entity.getEyeHeight(), entity.posZ)) == null)
				|| (rayTraceBlocks(Vec3.createVectorHelper(posX, posY + (height * 1.2), posZ - 0.7), Vec3.createVectorHelper(entity.posX, entity.posY + (double)entity.getEyeHeight(), entity.posZ)) == null)
				|| (rayTraceBlocks(Vec3.createVectorHelper(posX, posY + (height * 1.2) + 1, posZ), Vec3.createVectorHelper(entity.posX, entity.posY + (double)entity.getEyeHeight(), entity.posZ)) == null);
	}

	//C+P from world class. Modified for transparent blocks
	private MovingObjectPosition rayTraceBlocks(Vec3 par1Vec3D, Vec3 par2Vec3D)
	{
		boolean par3 = false;
		boolean par4 = false;

		if (Double.isNaN(par1Vec3D.xCoord) || Double.isNaN(par1Vec3D.yCoord) || Double.isNaN(par1Vec3D.zCoord))
		{
			return null;
		}

		if (Double.isNaN(par2Vec3D.xCoord) || Double.isNaN(par2Vec3D.yCoord) || Double.isNaN(par2Vec3D.zCoord))
		{
			return null;
		}

		int i = MathHelper.floor_double(par2Vec3D.xCoord);
		int j = MathHelper.floor_double(par2Vec3D.yCoord);
		int k = MathHelper.floor_double(par2Vec3D.zCoord);
		int l = MathHelper.floor_double(par1Vec3D.xCoord);
		int i1 = MathHelper.floor_double(par1Vec3D.yCoord);
		int j1 = MathHelper.floor_double(par1Vec3D.zCoord);
		int k1 = worldObj.getBlockId(l, i1, j1);
		int i2 = worldObj.getBlockMetadata(l, i1, j1);
		Block block = Block.blocksList[k1];

		if ((!par4 || block == null || block.getCollisionBoundingBoxFromPool(worldObj, l, i1, j1) != null) && k1 > 0 && block.canCollideCheck(i2, par3))
		{
			MovingObjectPosition movingobjectposition = block.collisionRayTrace(worldObj, l, i1, j1, par1Vec3D, par2Vec3D);

			if (movingobjectposition != null)
			{
				return movingobjectposition;
			}
		}

		for (int l1 = 200; l1-- >= 0;)
		{
			if (Double.isNaN(par1Vec3D.xCoord) || Double.isNaN(par1Vec3D.yCoord) || Double.isNaN(par1Vec3D.zCoord))
			{
				return null;
			}

			if (l == i && i1 == j && j1 == k)
			{
				return null;
			}

			boolean flag = true;
			boolean flag1 = true;
			boolean flag2 = true;
			double d = 999D;
			double d1 = 999D;
			double d2 = 999D;

			if (i > l)
			{
				d = (double)l + 1.0D;
			}
			else if (i < l)
			{
				d = (double)l + 0.0D;
			}
			else
			{
				flag = false;
			}

			if (j > i1)
			{
				d1 = (double)i1 + 1.0D;
			}
			else if (j < i1)
			{
				d1 = (double)i1 + 0.0D;
			}
			else
			{
				flag1 = false;
			}

			if (k > j1)
			{
				d2 = (double)j1 + 1.0D;
			}
			else if (k < j1)
			{
				d2 = (double)j1 + 0.0D;
			}
			else
			{
				flag2 = false;
			}

			double d3 = 999D;
			double d4 = 999D;
			double d5 = 999D;
			double d6 = par2Vec3D.xCoord - par1Vec3D.xCoord;
			double d7 = par2Vec3D.yCoord - par1Vec3D.yCoord;
			double d8 = par2Vec3D.zCoord - par1Vec3D.zCoord;

			if (flag)
			{
				d3 = (d - par1Vec3D.xCoord) / d6;
			}

			if (flag1)
			{
				d4 = (d1 - par1Vec3D.yCoord) / d7;
			}

			if (flag2)
			{
				d5 = (d2 - par1Vec3D.zCoord) / d8;
			}

			byte byte0 = 0;

			if (d3 < d4 && d3 < d5)
			{
				if (i > l)
				{
					byte0 = 4;
				}
				else
				{
					byte0 = 5;
				}

				par1Vec3D.xCoord = d;
				par1Vec3D.yCoord += d7 * d3;
				par1Vec3D.zCoord += d8 * d3;
			}
			else if (d4 < d5)
			{
				if (j > i1)
				{
					byte0 = 0;
				}
				else
				{
					byte0 = 1;
				}

				par1Vec3D.xCoord += d6 * d4;
				par1Vec3D.yCoord = d1;
				par1Vec3D.zCoord += d8 * d4;
			}
			else
			{
				if (k > j1)
				{
					byte0 = 2;
				}
				else
				{
					byte0 = 3;
				}

				par1Vec3D.xCoord += d6 * d5;
				par1Vec3D.yCoord += d7 * d5;
				par1Vec3D.zCoord = d2;
			}

			Vec3 vec3d = Vec3.createVectorHelper(par1Vec3D.xCoord, par1Vec3D.yCoord, par1Vec3D.zCoord);
			l = (int)(vec3d.xCoord = MathHelper.floor_double(par1Vec3D.xCoord));

			if (byte0 == 5)
			{
				l--;
				vec3d.xCoord++;
			}

			i1 = (int)(vec3d.yCoord = MathHelper.floor_double(par1Vec3D.yCoord));

			if (byte0 == 1)
			{
				i1--;
				vec3d.yCoord++;
			}

			j1 = (int)(vec3d.zCoord = MathHelper.floor_double(par1Vec3D.zCoord));

			if (byte0 == 3)
			{
				j1--;
				vec3d.zCoord++;
			}

			int j2 = worldObj.getBlockId(l, i1, j1);
			int k2 = worldObj.getBlockMetadata(l, i1, j1);
			Block block1 = Block.blocksList[j2];

			if ((!par4 || block1 == null || block1.getCollisionBoundingBoxFromPool(worldObj, l, i1, j1) != null) && j2 > 0 && block1.canCollideCheck(k2, par3) && !isBlockTransparent(j2))
			{
				MovingObjectPosition movingobjectposition1 = block1.collisionRayTrace(worldObj, l, i1, j1, par1Vec3D, par2Vec3D);

				if (movingobjectposition1 != null)
				{
					return movingobjectposition1;
				}
			}
		}

		return null;
	}

	private boolean isBlockTransparent(int id)
	{
		for(int i = 0; i < transparentBlocks.length; i++)
		{
			if(id == transparentBlocks[i])
			{
				return true;
			}
		}
		return false;
	}

	private boolean checkForOtherAngels()
	{
		int a = 0;
		List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(20D, 20D, 20D));
		for(int j = 0; j < list.size(); j++)
		{
			Entity entity1 = (Entity)list.get(j);
			if(entity1 instanceof EntityWeepingAngel)
			{
				EntityWeepingAngel entityweepingangel = (EntityWeepingAngel)entity1;
				if(entityweepingangel.angelSeeAngel(this))
				{
					a++;
				}
			}
		}
		if(a > 0)
		{
			timeLocked = true;
		}else
		{
			timeLocked = false;
		}
		return a > 0;
	}

	private boolean angelDirectLook(EntityPlayer entityplayer)
	{
		if(worldObj.getFullBlockLightValue(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ)) < 1)
		{
			return false;
		}
		Vec3 vec3d = entityplayer.getLook(1.0F).normalize();
		Vec3 vec3d1 = Vec3.createVectorHelper(posX - entityplayer.posX, ((boundingBox.minY + (double)height) - entityplayer.posY) + (double)entityplayer.getEyeHeight(), posZ - entityplayer.posZ);
		double d = vec3d1.lengthVector();
		vec3d1 = vec3d1.normalize();
		double d1 = vec3d.dotProduct(vec3d1);
		if(d1 > 1.0D - 0.025000000000000001D / d)
		{
			if(aggressiveArmMovement || armMovement)
				slowPeriod = rand.nextInt(100);
			return entityplayer.canEntityBeSeen(this);
		} else
		{
			return false;
		}
	}

	public boolean angelSeeAngel(EntityWeepingAngel entity)
	{
		if(worldObj.getFullBlockLightValue(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ)) < 1)
		{
			return false;
		}
		if(!armMovement || !aggressiveArmMovement)
		{
			return false;
		}
		return isInFieldOfVision(entity, this, 40, 65);    
		//Vec3D vec3d = entity.getLook(1.0F).normalize();
		//Vec3D vec3d1 = Vec3D.createVector(posX - entity.posX, ((boundingBox.minY + (double)height) - entity.posY) + (double)entity.getEyeHeight(), posZ - entity.posZ);
		//double d = vec3d1.lengthVector();
		//vec3d1 = vec3d1.normalize();
		// double d1 = vec3d.dotProduct(vec3d1);
		//if(d1 > 1.0D - 0.025000000000000001D / d)
		// {
		// return entity.canEntityBeSeen(this);
		// } else
		//{
		//    return false;
		//}
	}

	public double getDistance(int i, int j, int k, int l, int i1, int j1)
	{
		int k1 = l - i;
		int l1 = i1 - j;
		int i2 = j1 - k;
		return Math.sqrt(k1 * k1 + l1 * l1 + i2 * i2);
	}

	public double getDistancetoEntityToAttack()
	{
		if(entityToAttack instanceof EntityPlayer)
		{
			double d = entityToAttack.posX - posX;
			double d2 = entityToAttack.posY - posY;
			double d4 = entityToAttack.posZ - posZ;
			return (double)MathHelper.sqrt_double(d * d + d2 * d2 + d4 * d4);
		}
		EntityPlayer entityplayer = worldObj.getClosestPlayerToEntity(this, 64D);
		if(entityplayer != null)
		{
			double d1 = entityplayer.posX - posX;
			double d3 = entityplayer.posY - posY;
			double d5 = entityplayer.posZ - posZ;
			return (double)MathHelper.sqrt_double(d1 * d1 + d3 * d3 + d5 * d5);
		} else
		{
			return 40000D;
		}
	}

	private void findNearestTorch()
	{
		int i = (int)posX;
		int j = (int)posY;
		int k = (int)posZ;
		int l = i + 10;
		int i1 = j + 10;
		int j1 = k + 10;
		int k1 = i - 10;
		int l1 = j - 10;
		int i2 = k - 10;
		int j2 = 100;
		for(int k2 = k1; k2 < l; k2++)
		{
			for(int l2 = l1; l2 < i1; l2++)
			{
				for(int i3 = i2; i3 < j1; i3++)
				{
					if(getDistance(i, j, k, k2, l2, i3) > (double)j2)
					{
						continue;
					}
					int j3 = worldObj.getBlockId(k2, l2, i3);
					Block block = j3 > 0 ? Block.blocksList[j3] : null;
					if(block == null || block != Block.torchWood && block != Block.torchRedstoneActive && block != Block.redstoneLampActive && block != Block.redstoneRepeaterActive && block != Block.glowStone || worldObj.rayTraceBlocks(Vec3.createVectorHelper(posX, posY + (double)getEyeHeight(), posZ), Vec3.createVectorHelper(k2, l2, i3)) != null || worldObj.rayTraceBlocks(Vec3.createVectorHelper(entityToAttack.posX, entityToAttack.posY + (double)entityToAttack.getEyeHeight(), entityToAttack.posZ), Vec3.createVectorHelper(k2, l2, i3)) != null)
					{
						continue;
					}
					if(!breakOnePerTick)
					{
						block.dropBlockAsItem(worldObj, k2, l2, i3, 1, 1);
						worldObj.setBlockWithNotify(k2, l2, i3, 0);
						worldObj.playSoundAtEntity(this, "resources.light", getSoundVolume(), ((rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F) * 1.8F);
						breakOnePerTick = true;
					}
					break;
				}

			}

		}

	}

	private boolean isInFieldOfVision(EntityWeepingAngel entityweepingangel, EntityLiving entityliving, float f4i, float f5i)
	{
		float f = entityliving.rotationYaw;
		float f1 = entityliving.rotationPitch;
		entityliving.faceEntity(entityweepingangel, 360F, 360F);
		float f2 = entityliving.rotationYaw;
		float f3 = entityliving.rotationPitch;
		entityliving.rotationYaw = f;
		entityliving.rotationPitch = f1;
		f = f2;
		f1 = f3;
		float f4 = f4i; // 70f
		float f5 = f5i; // 65f
		float f6 = entityliving.rotationYaw - f4;
		float f7 = entityliving.rotationYaw + f4;
		float f8 = entityliving.rotationPitch - f5;
		float f9 = entityliving.rotationPitch + f5;
		boolean flag = GetFlag(f6, f7, f, 0.0F, 360F);
		boolean flag1 = GetFlag(f8, f9, f1, -180F, 180F);
		return flag && flag1 && (entityliving.canEntityBeSeen(entityweepingangel) || LineOfSightCheck(entityliving));
	}

	public boolean GetFlag(float f, float f1, float f2, float f3, float f4)
	{
		if(f < f3)
		{
			if(f2 >= f + f4)
			{
				return true;
			}
			if(f2 <= f1)
			{
				return true;
			}
		}
		if(f1 >= f4)
		{
			if(f2 <= f1 - f4)
			{
				return true;
			}
			if(f2 >= f)
			{
				return true;
			}
		}
		if(f1 < f4 && f >= f3)
		{
			return f2 <= f1 && f2 > f;
		} else
		{
			return false;
		}
	}

	private void teleportPlayer(Entity entity)
	{
		if(entity instanceof EntityPlayer)
		{
			int rangeDifference = 2 * (WeepingAngelsMod.teleportRangeMax - WeepingAngelsMod.teleportRangeMin);
			int offsetX = rand.nextInt(rangeDifference) - rangeDifference/2 + WeepingAngelsMod.teleportRangeMin;
			int offsetZ = rand.nextInt(rangeDifference) - rangeDifference/2 + WeepingAngelsMod.teleportRangeMin;
			
			//Center the values on a block, to make the boundingbox calculations match less.
			double newX = MathHelper.floor_double(entity.posX) + offsetX + 0.5;
			double newY = rand.nextInt(128);
			double newZ = MathHelper.floor_double(entity.posZ) + offsetZ + 0.5;
			
			double bbMinX = newX - entity.width / 2.0;
			double bbMinY = newY - entity.yOffset + entity.ySize;
			double bbMinZ = newZ - entity.width / 2.0;
			double bbMaxX = newX + entity.width / 2.0;
			double bbMaxY = newY - entity.yOffset + entity.ySize + entity.height;
			double bbMaxZ = newZ + entity.width / 2.0;
			
			//FMLLog.info("Teleporting from: "+(int)entity.posX+" "+(int)entity.posY+" "+(int)entity.posZ);
			//FMLLog.info("Teleporting with offsets: "+offsetX+" "+newY+" "+offsetZ);
			//FMLLog.info("Starting BB Bounds: "+bbMinX+" "+bbMinY+" "+bbMinZ+" "+bbMaxX+" "+bbMaxY+" "+bbMaxZ);
			
			//Use a testing boundingBox, so we don't have to move the player around to test if it is a valid location
			AxisAlignedBB boundingBox = AxisAlignedBB.getBoundingBox(bbMinX, bbMinY, bbMinZ, bbMaxX, bbMaxY, bbMaxZ);
			
			// Make sure you are trying to teleport to a loaded chunk.
			Chunk teleportChunk = worldObj.getChunkFromBlockCoords((int)newX, (int)newZ);
			if (!teleportChunk.isChunkLoaded)
			{
				worldObj.getChunkProvider().loadChunk(teleportChunk.xPosition, teleportChunk.zPosition);
			}
			
			// Move up, until nothing intersects the entity anymore
			while (newY > 0 && newY < 128 && !this.worldObj.getAllCollidingBoundingBoxes(boundingBox).isEmpty())
			{
				++newY;
				
				bbMinY = newY - entity.yOffset + entity.ySize;
				bbMaxY = newY - entity.yOffset + entity.ySize + entity.height;
				
				boundingBox.setBounds(bbMinX, bbMinY, bbMinZ, bbMaxX, bbMaxY, bbMaxZ);
				
				//FMLLog.info("Failed to teleport, retrying at height: "+(int)newY);
			}
			
			//If we could place it, could we have placed it lower? To prevent teleports really high up.
			do 
			{
				--newY;
				
				bbMinY = newY - entity.yOffset + entity.ySize;
				bbMaxY = newY - entity.yOffset + entity.ySize + entity.height;
				
				boundingBox.setBounds(bbMinX, bbMinY, bbMinZ, bbMaxX, bbMaxY, bbMaxZ);
				
				//FMLLog.info("Trying a lower teleport at height: "+(int)newY);
			}
			while (newY > 0 && newY < 128 && this.worldObj.getAllCollidingBoundingBoxes(boundingBox).isEmpty());
			//Set Y one higher, as the last lower placing test failed.
			++newY;
					
			//Check for placement in lava
			//NOTE: This can potentially hang the game indefinitely, due to random recursion
			//However this situation is highly unlikelely
			//My advice: Dont encounter Weeping Angels in seas of lava
			//NOTE: This can theoretically still teleport you to a block of lava with air underneath, but gladly lava spreads ;)
			int blockId = worldObj.getBlockId(MathHelper.floor_double(newX), MathHelper.floor_double(newY), MathHelper.floor_double(newZ));
			if (blockId == 10 || blockId == 11)
			{
				teleportPlayer(entity);
				return;
			}
			
			//Set the location of the player, on the final position.
			entity.setLocationAndAngles(newX, newY, newZ, entity.rotationYaw, entity.rotationPitch);
			//FMLLog.info("Succesfully teleported to: "+(int)entity.posX+" "+(int)entity.posY+" "+(int)entity.posZ);
		}
	}

	protected boolean func_35178_q()
	{
		double d = posX + (rand.nextDouble() - 0.5D) * 64D;
		double d1 = posY + (double)(rand.nextInt(64) - 32);
		double d2 = posZ + (rand.nextDouble() - 0.5D) * 64D;
		return func_35179_a_(d, d1, d2);
	}

	protected boolean func_35182_c(Entity entity)
	{
		Vec3 vec3d = Vec3.createVectorHelper(posX - entity.posX, ((boundingBox.minY + (double)(height / 2.0F)) - entity.posY) + (double)entity.getEyeHeight(), posZ - entity.posZ);
		vec3d = vec3d.normalize();
		double d = 6D;
		double d1 = (posX + (rand.nextDouble() - 0.5D) * 8D) - vec3d.xCoord * d;
		double d2 = (posY + (double)(rand.nextInt(16) - 8)) - vec3d.yCoord * d;
		double d3 = (posZ + (rand.nextDouble() - 0.5D) * 8D) - vec3d.zCoord * d;
		return func_35179_a_(d1, d2, d3);
	}

	protected boolean func_35179_a_(double d, double d1, double d2)
	{
		double d3 = posX;
		double d4 = posY;
		double d5 = posZ;
		posX = d;
		posY = d1;
		posZ = d2;
		boolean flag = false;
		int i = MathHelper.floor_double(posX);
		int j = MathHelper.floor_double(posY);
		int k = MathHelper.floor_double(posZ);
		if(worldObj.blockExists(i, j, k))
		{
			boolean flag1;
			for(flag1 = false; !flag1 && j > 0;)
			{
				int i1 = worldObj.getBlockId(i, j - 1, k);
				if(i1 == 0 || !Block.blocksList[i1].blockMaterial.isSolid())
				{
					posY--;
					j--;
				} else
				{
					flag1 = true;
				}
			}

			if(flag1)
			{
				setPosition(posX, posY, posZ);
				if(worldObj.getCollidingBoundingBoxes(this, boundingBox).size() == 0 && !worldObj.isAnyLiquid(boundingBox))
				{
					flag = true;
				}
			}
		}
		if(!flag)
		{
			setPosition(d3, d4, d5);
			return false;
		}
		int l = 128;
		/* for(int j1 = 0; j1 < l; j1++)
        {
            double d6 = (double)j1 / ((double)l - 1.0D);
            float f = (rand.nextFloat() - 0.5F) * 0.2F;
            float f1 = (rand.nextFloat() - 0.5F) * 0.2F;
            float f2 = (rand.nextFloat() - 0.5F) * 0.2F;
            double d7 = d3 + (posX - d3) * d6 + (rand.nextDouble() - 0.5D) * (double)width * 2D;
            double d8 = d4 + (posY - d4) * d6 + rand.nextDouble() * (double)height;
            double d9 = d5 + (posZ - d5) * d6 + (rand.nextDouble() - 0.5D) * (double)width * 2D;
            worldObj.spawnParticle("portal", d7, d8, d9, f, f1, f2);
        }*/

		return true;
	}
	
	public String getMovementSound()
	{
		if(entityToAttack != null && (entityToAttack instanceof EntityPlayer) && !isInFieldOfVision(this, (EntityPlayer)entityToAttack, 70, 65))
		{
			/*
			String s = "step.stone";
			int i = rand.nextInt(4);
			switch(i)
			{
			case 0: // '\0'
				s = "mob.angel.stoneone";
				break;

			case 1: // '\001'
				s = "mob.angel.stonetwo";
				break;

			case 2: // '\002'
				s = "mob.angel.stonethree";
				break;

			case 3: // '\003'
				s = "mob.angel.stonefour";
				break;
			}
			return s;
			*/
			return "resources.stone";
		} else
		{
			return "";
		}
	}
	
	@Override
	protected String getLivingSound()
	{
		if(rand.nextInt(10) > 8)
		{
			return getMovementSound();
		}
		return "";
	}
	
	@Override
	protected String getHurtSound()
	{
		return "step.stone";
	}
	
	@Override
	protected String getDeathSound()
	{
		return "resources.crumble";
	}

	public void setYaw(float f)
	{
		rotationYaw = f;
	}

	@Override
	public int getMaxSpawnedInChunk()
	{
		return 10;
	}
	
	public boolean getAngry()
	{
		return this.dataWatcher.getWatchableObjectByte(16) == 1; 
	}
	
	public boolean getArmMovement()
	{
		return this.dataWatcher.getWatchableObjectByte(17) == 1; 
	}
	
	@Override
	public int getAttackStrength(Entity par1Entity)
	{
		return attackStrength;
	}

}