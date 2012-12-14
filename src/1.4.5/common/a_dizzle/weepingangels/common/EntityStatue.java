package a_dizzle.weepingangels.common;

import net.minecraft.src.DamageSource;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.World;

public class EntityStatue extends EntityLiving
{
	public int dropId;
	public Class livingEntity;

	public EntityStatue(World world)
	{
		super(world);
		dropId = WeepingAngelsMod.statue.shiftedIndex;
		livingEntity = EntityWeepingAngel.class;
		texture = "/angels/weepingangel.png";
	}

	public int getMaxHealth()
	{
		return 15;
	}

	public boolean canBePushed()
	{
		return true;
	}

	public boolean attackEntityFrom(DamageSource damagesource, int i)
	{
		return false;
	}

	public void onLivingUpdate()
	{
	}

	public void onEntityUpdate()
	{
	}

	public void onUpdate()
	{
		prevRotationYaw = rotationYaw;
		prevRotationPitch = rotationPitch;
		if(newPosRotationIncrements > 0)
		{
			double d;
			for(d = newRotationYaw - (double)rotationYaw; d < -180D; d += 360D) { }
			for(; d >= 180D; d -= 360D) { }
			rotationYaw += d / (double)newPosRotationIncrements;
			rotationPitch += (newRotationPitch - (double)rotationPitch) / (double)newPosRotationIncrements;
			newPosRotationIncrements--;
			setRotation(rotationYaw, rotationPitch);
		}
		float f = renderYawOffset;
		float f1 = 0.0F;
		field_70768_au = field_70766_av;
		float f2 = 0.0F;
		field_70766_av = field_70766_av + (f2 - field_70766_av) * 0.3F;
		float f3;
		for(f3 = f - renderYawOffset; f3 < -180F; f3 += 360F) { }
		for(; f3 >= 180F; f3 -= 360F) { }
		renderYawOffset += f3 * 0.3F;
		float f4;
		for(f4 = rotationYaw - renderYawOffset; f4 < -180F; f4 += 360F) { }
		for(; f4 >= 180F; f4 -= 360F) { }
		boolean flag = f4 < -90F || f4 >= 90F;
		if(f4 < -75F)
		{
			f4 = -75F;
		}
		if(f4 >= 75F)
		{
			f4 = 75F;
		}
		renderYawOffset = rotationYaw;
		if(f4 * f4 > 2500F)
		{
			renderYawOffset += f4 * 0.2F;
		}
		if(flag)
		{
			f1 *= -1F;
		}
		for(; rotationYaw - prevRotationYaw < -180F; prevRotationYaw -= 360F) { }
		for(; rotationYaw - prevRotationYaw >= 180F; prevRotationYaw += 360F) { }
		for(; renderYawOffset - prevRenderYawOffset < -180F; prevRenderYawOffset -= 360F) { }
		for(; renderYawOffset - prevRenderYawOffset >= 180F; prevRenderYawOffset += 360F) { }
		for(; rotationPitch - prevRotationPitch < -180F; prevRotationPitch -= 360F) { }
		for(; rotationPitch - prevRotationPitch >= 180F; prevRotationPitch += 360F) { }
		field_70764_aw += f1;
		prevRenderYawOffset = renderYawOffset;
	}

	protected boolean canDespawn()
	{
		return false;
	}

}
