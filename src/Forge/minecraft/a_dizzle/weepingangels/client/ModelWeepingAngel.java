package a_dizzle.weepingangels.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import a_dizzle.weepingangels.common.EntityWeepingAngel;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelWeepingAngel extends ModelBase
{
    ModelRenderer leftfoot;
    ModelRenderer rightfoot;
    ModelRenderer leftwing1;
    ModelRenderer leftwing2;
    ModelRenderer leftwing3;
    ModelRenderer leftwing4;
    ModelRenderer rightwing1;
    ModelRenderer rightwing2;
    ModelRenderer rightwing3;
    ModelRenderer rightwing4;
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    private float angleX;
    private float angleY;
    private float angleZ;
    
    public ModelWeepingAngel()
    {
        this.textureHeight = 32;
    	this.textureWidth = 64;
        leftfoot = new ModelRenderer(this, 32, 0);
        leftfoot.addBox(-2F, 7F, -4F, 6, 5, 8);
        leftfoot.setRotationPoint(2.0F, 12F, 0.0F);
        leftfoot.rotateAngleX = 0.0F;
        leftfoot.rotateAngleY = 0.0F;
        leftfoot.rotateAngleZ = 0.0F;
        leftfoot.mirror = false;
        rightfoot = new ModelRenderer(this, 32, 0);
        rightfoot.addBox(-4F, 7F, -4F, 6, 5, 8);
        rightfoot.setRotationPoint(-2F, 12F, 0.0F);
        rightfoot.rotateAngleX = 0.0F;
        rightfoot.rotateAngleY = 0.0F;
        rightfoot.rotateAngleZ = 0.0F;
        rightfoot.mirror = false;
        leftwing1 = new ModelRenderer(this, 40, 25);
        leftwing1.addBox(-0.5F, -1F, 1.0F, 1, 5, 2);
        leftwing1.setRotationPoint(1.0F, 1.0F, 1.0F);
        leftwing1.rotateAngleX = 0.20944F;
        leftwing1.rotateAngleY = 0.61087F;
        leftwing1.rotateAngleZ = 0.0F;
        leftwing1.mirror = false;
        leftwing2 = new ModelRenderer(this, 46, 19);
        leftwing2.addBox(-0.5F, -2F, 3F, 1, 11, 2);
        leftwing2.setRotationPoint(1.0F, 1.0F, 1.0F);
        leftwing2.rotateAngleX = 0.20944F;
        leftwing2.rotateAngleY = 0.61087F;
        leftwing2.rotateAngleZ = 0.01745F;
        leftwing2.mirror = false;
        leftwing3 = new ModelRenderer(this, 58, 12);
        leftwing3.addBox(-0.5F, -2F, 5F, 1, 18, 2);
        leftwing3.setRotationPoint(1.0F, 1.0F, 1.0F);
        leftwing3.rotateAngleX = 0.20944F;
        leftwing3.rotateAngleY = 0.61087F;
        leftwing3.rotateAngleZ = 0.0F;
        leftwing3.mirror = false;
        leftwing4 = new ModelRenderer(this, 52, 16);
        leftwing4.addBox(-0.5F, 0.0F, 7F, 1, 14, 2);
        leftwing4.setRotationPoint(1.0F, 1.0F, 1.0F);
        leftwing4.rotateAngleX = 0.20944F;
        leftwing4.rotateAngleY = 0.61087F;
        leftwing4.rotateAngleZ = 0.0F;
        leftwing4.mirror = false;
        rightwing1 = new ModelRenderer(this, 40, 25);
        rightwing1.addBox(-0.5F, -1F, 1.0F, 1, 5, 2);
        rightwing1.setRotationPoint(-2F, 1.0F, 1.0F);
        rightwing1.rotateAngleX = 0.20944F;
        rightwing1.rotateAngleY = -0.61087F;
        rightwing1.rotateAngleZ = 0.0F;
        rightwing1.mirror = false;
        rightwing2 = new ModelRenderer(this, 46, 19);
        rightwing2.addBox(-0.5F, -2F, 3F, 1, 11, 2);
        rightwing2.setRotationPoint(-2F, 1.0F, 1.0F);
        rightwing2.rotateAngleX = 0.20944F;
        rightwing2.rotateAngleY = -0.61087F;
        rightwing2.rotateAngleZ = 0.0F;
        rightwing2.mirror = false;
        rightwing3 = new ModelRenderer(this, 58, 12);
        rightwing3.addBox(-0.5F, -2F, 5F, 1, 18, 2);
        rightwing3.setRotationPoint(-2F, 1.0F, 1.0F);
        rightwing3.rotateAngleX = 0.20944F;
        rightwing3.rotateAngleY = -0.61087F;
        rightwing3.rotateAngleZ = 0.0F;
        rightwing3.mirror = false;
        rightwing4 = new ModelRenderer(this, 52, 16);
        rightwing4.addBox(-0.5F, 0.0F, 7F, 1, 14, 2);
        rightwing4.setRotationPoint(-2F, 1.0F, 1.0F);
        rightwing4.rotateAngleX = 0.20944F;
        rightwing4.rotateAngleY = -0.61087F;
        rightwing4.rotateAngleZ = 0.0F;
        rightwing4.mirror = false;
        head = new ModelRenderer(this, 0, 0);
        head.addBox(-4F, -8F, -4F, 8, 8, 8);
        head.setRotationPoint(0.0F, 0.0F, 0.0F);
        head.rotateAngleX = 0.24435F;
        head.rotateAngleY = 0.0F;
        head.rotateAngleZ = 0.0F;
        head.mirror = false;
        body = new ModelRenderer(this, 0, 16);
        body.addBox(-4F, 0.0F, -2F, 8, 12, 4);
        body.setRotationPoint(0.0F, 0.0F, 0.0F);
        body.rotateAngleX = 0.0F;
        body.rotateAngleY = 0.0F;
        body.rotateAngleZ = 0.0F;
        body.mirror = false;
        rightarm = new ModelRenderer(this, 24, 19);
        rightarm.addBox(-3F, 0.0F, -2F, 4, 9, 4);
        rightarm.setRotationPoint(-5F, 0.0F, 0.0F);
        rightarm.rotateAngleX = -1.74533F;
        rightarm.rotateAngleY = -0.55851F;
        rightarm.rotateAngleZ = 0.0F;
        rightarm.mirror = false;
        leftarm = new ModelRenderer(this, 24, 19);
        leftarm.addBox(-1F, 0.0F, -2F, 4, 9, 4);
        leftarm.setRotationPoint(5F, 0.0F, 0.0F);
        leftarm.rotateAngleX = -1.74533F;
        leftarm.rotateAngleY = 0.55851F;
        leftarm.rotateAngleZ = 0.0F;
        leftarm.mirror = false;
        rightleg = new ModelRenderer(this, 24, 19);
        rightleg.addBox(-2F, 0.0F, -2F, 4, 9, 4);
        rightleg.setRotationPoint(-2F, 12F, 0.0F);
        rightleg.rotateAngleX = 0.0F;
        rightleg.rotateAngleY = 0.0F;
        rightleg.rotateAngleZ = 0.0F;
        rightleg.mirror = false;
        leftleg = new ModelRenderer(this, 24, 19);
        leftleg.addBox(-2F, 0.0F, -2F, 4, 9, 4);
        leftleg.setRotationPoint(2.0F, 12F, 0.0F);
        leftleg.rotateAngleX = 0.0F;
        leftleg.rotateAngleY = 0.0F;
        leftleg.rotateAngleZ = 0.0F;
        leftleg.mirror = false;
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        setRotationAngles(entity, f, f1, f2, f3, f4, f5);
        leftfoot.render(f5);
        rightfoot.render(f5);
        leftwing1.render(f5);
        leftwing2.render(f5);
        leftwing3.render(f5);
        leftwing4.render(f5);
        rightwing1.render(f5);
        rightwing2.render(f5);
        rightwing3.render(f5);
        rightwing4.render(f5);
        head.render(f5);
        body.render(f5);
        rightarm.render(f5);
        leftarm.render(f5);
        rightleg.render(f5);
        leftleg.render(f5);
    }

    public void setRotationAngles(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
    	if(entity instanceof EntityWeepingAngel){
        if(((EntityWeepingAngel)entity).getAngry())
        {
            angleX = toRadians(20F);
            angleY = toRadians(60F);
            angleZ = toRadians(5F);
        } else
        {
            angleX = toRadians(10F);
            angleY = toRadians(30F);
            angleZ = toRadians(5F);
        }
        if(((EntityWeepingAngel)entity).getAngry())
        {
            float f6 = MathHelper.sin(onGround * 3.141593F);
            float f7 = MathHelper.sin((1.0F - (1.0F - onGround) * (1.0F - onGround)) * 3.141593F);
            rightarm.rotateAngleZ = 0.0F;
            leftarm.rotateAngleZ = 0.0F;
            rightarm.rotateAngleY = -(0.1F - f6 * 0.6F);
            leftarm.rotateAngleY = 0.1F - f6 * 0.6F;
            rightarm.rotateAngleX = -1.570796F;
            leftarm.rotateAngleX = -1.570796F;
        } else
        if(((EntityWeepingAngel)entity).getArmMovement())
        {
            rightarm.rotateAngleX = -1.04533F;
            rightarm.rotateAngleY = -0.55851F;
            rightarm.rotateAngleZ = 0.0F;
            leftarm.rotateAngleX = -1.04533F;
            leftarm.rotateAngleY = 0.55851F;
            leftarm.rotateAngleZ = 0.0F;
        } else
        {
            rightarm.rotateAngleX = -1.74533F;
            rightarm.rotateAngleY = -0.55851F;
            rightarm.rotateAngleZ = 0.0F;
            leftarm.rotateAngleX = -1.74533F;
            leftarm.rotateAngleY = 0.55851F;
            leftarm.rotateAngleZ = 0.0F;
        }
        rightwing2.rotateAngleX = rightwing3.rotateAngleX = rightwing4.rotateAngleX = rightwing1.rotateAngleX = leftwing2.rotateAngleX = leftwing3.rotateAngleX = leftwing4.rotateAngleX = leftwing1.rotateAngleX = angleX;
        rightwing2.rotateAngleY = rightwing3.rotateAngleY = rightwing4.rotateAngleY = rightwing1.rotateAngleY = -angleY;
        leftwing2.rotateAngleY = leftwing3.rotateAngleY = leftwing4.rotateAngleY = leftwing1.rotateAngleY = angleY;
        rightwing2.rotateAngleZ = rightwing3.rotateAngleZ = rightwing4.rotateAngleZ = rightwing1.rotateAngleZ = angleZ;
        leftwing2.rotateAngleZ = leftwing3.rotateAngleZ = leftwing4.rotateAngleZ = leftwing1.rotateAngleZ = -angleZ;
    	}
    	else{
            rightarm.rotateAngleX = -1.74533F;
            rightarm.rotateAngleY = -0.55851F;
            rightarm.rotateAngleZ = 0.0F;
            leftarm.rotateAngleX = -1.74533F;
            leftarm.rotateAngleY = 0.55851F;
            leftarm.rotateAngleZ = 0.0F;
    	}
    }

    private float toRadians(float f)
    {
        return f / 57.29578F;
    }

    private float toDegrees(float f)
    {
        return f * 57.29578F;
    }
}package a_dizzle.weepingangels.client;

import a_dizzle.weepingangels.common.EntityWeepingAngel;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

@SideOnly(Side.CLIENT)
public class ModelWeepingAngel extends ModelBase
{
    ModelRenderer leftfoot;
    ModelRenderer rightfoot;
    ModelRenderer leftwing1;
    ModelRenderer leftwing2;
    ModelRenderer leftwing3;
    ModelRenderer leftwing4;
    ModelRenderer rightwing1;
    ModelRenderer rightwing2;
    ModelRenderer rightwing3;
    ModelRenderer rightwing4;
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    private float angleX;
    private float angleY;
    private float angleZ;
    
    public ModelWeepingAngel()
    {
    	this.textureHeight = 32;
    	this.textureWidth = 64;
        leftfoot = new ModelRenderer(this, 32, 0);
        leftfoot.addBox(-2F, 7F, -4F, 6, 5, 8);
        leftfoot.setRotationPoint(2.0F, 12F, 0.0F);
        leftfoot.rotateAngleX = 0.0F;
        leftfoot.rotateAngleY = 0.0F;
        leftfoot.rotateAngleZ = 0.0F;
        leftfoot.mirror = false;
        rightfoot = new ModelRenderer(this, 32, 0);
        rightfoot.addBox(-4F, 7F, -4F, 6, 5, 8);
        rightfoot.setRotationPoint(-2F, 12F, 0.0F);
        rightfoot.rotateAngleX = 0.0F;
        rightfoot.rotateAngleY = 0.0F;
        rightfoot.rotateAngleZ = 0.0F;
        rightfoot.mirror = false;
        leftwing1 = new ModelRenderer(this, 40, 25);
        leftwing1.addBox(-0.5F, -1F, 1.0F, 1, 5, 2);
        leftwing1.setRotationPoint(1.0F, 1.0F, 1.0F);
        leftwing1.rotateAngleX = 0.20944F;
        leftwing1.rotateAngleY = 0.61087F;
        leftwing1.rotateAngleZ = 0.0F;
        leftwing1.mirror = false;
        leftwing2 = new ModelRenderer(this, 46, 19);
        leftwing2.addBox(-0.5F, -2F, 3F, 1, 11, 2);
        leftwing2.setRotationPoint(1.0F, 1.0F, 1.0F);
        leftwing2.rotateAngleX = 0.20944F;
        leftwing2.rotateAngleY = 0.61087F;
        leftwing2.rotateAngleZ = 0.01745F;
        leftwing2.mirror = false;
        leftwing3 = new ModelRenderer(this, 58, 12);
        leftwing3.addBox(-0.5F, -2F, 5F, 1, 18, 2);
        leftwing3.setRotationPoint(1.0F, 1.0F, 1.0F);
        leftwing3.rotateAngleX = 0.20944F;
        leftwing3.rotateAngleY = 0.61087F;
        leftwing3.rotateAngleZ = 0.0F;
        leftwing3.mirror = false;
        leftwing4 = new ModelRenderer(this, 52, 16);
        leftwing4.addBox(-0.5F, 0.0F, 7F, 1, 14, 2);
        leftwing4.setRotationPoint(1.0F, 1.0F, 1.0F);
        leftwing4.rotateAngleX = 0.20944F;
        leftwing4.rotateAngleY = 0.61087F;
        leftwing4.rotateAngleZ = 0.0F;
        leftwing4.mirror = false;
        rightwing1 = new ModelRenderer(this, 40, 25);
        rightwing1.addBox(-0.5F, -1F, 1.0F, 1, 5, 2);
        rightwing1.setRotationPoint(-2F, 1.0F, 1.0F);
        rightwing1.rotateAngleX = 0.20944F;
        rightwing1.rotateAngleY = -0.61087F;
        rightwing1.rotateAngleZ = 0.0F;
        rightwing1.mirror = false;
        rightwing2 = new ModelRenderer(this, 46, 19);
        rightwing2.addBox(-0.5F, -2F, 3F, 1, 11, 2);
        rightwing2.setRotationPoint(-2F, 1.0F, 1.0F);
        rightwing2.rotateAngleX = 0.20944F;
        rightwing2.rotateAngleY = -0.61087F;
        rightwing2.rotateAngleZ = 0.0F;
        rightwing2.mirror = false;
        rightwing3 = new ModelRenderer(this, 58, 12);
        rightwing3.addBox(-0.5F, -2F, 5F, 1, 18, 2);
        rightwing3.setRotationPoint(-2F, 1.0F, 1.0F);
        rightwing3.rotateAngleX = 0.20944F;
        rightwing3.rotateAngleY = -0.61087F;
        rightwing3.rotateAngleZ = 0.0F;
        rightwing3.mirror = false;
        rightwing4 = new ModelRenderer(this, 52, 16);
        rightwing4.addBox(-0.5F, 0.0F, 7F, 1, 14, 2);
        rightwing4.setRotationPoint(-2F, 1.0F, 1.0F);
        rightwing4.rotateAngleX = 0.20944F;
        rightwing4.rotateAngleY = -0.61087F;
        rightwing4.rotateAngleZ = 0.0F;
        rightwing4.mirror = false;
        head = new ModelRenderer(this, 0, 0);
        head.addBox(-4F, -8F, -4F, 8, 8, 8);
        head.setRotationPoint(0.0F, 0.0F, 0.0F);
        head.rotateAngleX = 0.24435F;
        head.rotateAngleY = 0.0F;
        head.rotateAngleZ = 0.0F;
        head.mirror = false;
        body = new ModelRenderer(this, 0, 16);
        body.addBox(-4F, 0.0F, -2F, 8, 12, 4);
        body.setRotationPoint(0.0F, 0.0F, 0.0F);
        body.rotateAngleX = 0.0F;
        body.rotateAngleY = 0.0F;
        body.rotateAngleZ = 0.0F;
        body.mirror = false;
        rightarm = new ModelRenderer(this, 24, 19);
        rightarm.addBox(-3F, 0.0F, -2F, 4, 9, 4);
        rightarm.setRotationPoint(-5F, 0.0F, 0.0F);
        rightarm.rotateAngleX = -1.74533F;
        rightarm.rotateAngleY = -0.55851F;
        rightarm.rotateAngleZ = 0.0F;
        rightarm.mirror = false;
        leftarm = new ModelRenderer(this, 24, 19);
        leftarm.addBox(-1F, 0.0F, -2F, 4, 9, 4);
        leftarm.setRotationPoint(5F, 0.0F, 0.0F);
        leftarm.rotateAngleX = -1.74533F;
        leftarm.rotateAngleY = 0.55851F;
        leftarm.rotateAngleZ = 0.0F;
        leftarm.mirror = false;
        rightleg = new ModelRenderer(this, 24, 19);
        rightleg.addBox(-2F, 0.0F, -2F, 4, 9, 4);
        rightleg.setRotationPoint(-2F, 12F, 0.0F);
        rightleg.rotateAngleX = 0.0F;
        rightleg.rotateAngleY = 0.0F;
        rightleg.rotateAngleZ = 0.0F;
        rightleg.mirror = false;
        leftleg = new ModelRenderer(this, 24, 19);
        leftleg.addBox(-2F, 0.0F, -2F, 4, 9, 4);
        leftleg.setRotationPoint(2.0F, 12F, 0.0F);
        leftleg.rotateAngleX = 0.0F;
        leftleg.rotateAngleY = 0.0F;
        leftleg.rotateAngleZ = 0.0F;
        leftleg.mirror = false;
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        setRotationAngles(entity, f, f1, f2, f3, f4, f5);
        leftfoot.render(f5);
        rightfoot.render(f5);
        leftwing1.render(f5);
        leftwing2.render(f5);
        leftwing3.render(f5);
        leftwing4.render(f5);
        rightwing1.render(f5);
        rightwing2.render(f5);
        rightwing3.render(f5);
        rightwing4.render(f5);
        head.render(f5);
        body.render(f5);
        rightarm.render(f5);
        leftarm.render(f5);
        rightleg.render(f5);
        leftleg.render(f5);
    }

    public void setRotationAngles(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
    	if(entity instanceof EntityWeepingAngel){
        if(((EntityWeepingAngel)entity).getAngry())
        {
            angleX = toRadians(20F);
            angleY = toRadians(60F);
            angleZ = toRadians(5F);
        } else
        {
            angleX = toRadians(10F);
            angleY = toRadians(30F);
            angleZ = toRadians(5F);
        }
        if(((EntityWeepingAngel)entity).getAngry())
        {
            float f6 = MathHelper.sin(onGround * 3.141593F);
            float f7 = MathHelper.sin((1.0F - (1.0F - onGround) * (1.0F - onGround)) * 3.141593F);
            rightarm.rotateAngleZ = 0.0F;
            leftarm.rotateAngleZ = 0.0F;
            rightarm.rotateAngleY = -(0.1F - f6 * 0.6F);
            leftarm.rotateAngleY = 0.1F - f6 * 0.6F;
            rightarm.rotateAngleX = -1.570796F;
            leftarm.rotateAngleX = -1.570796F;
        } else
        if(((EntityWeepingAngel)entity).getArmMovement())
        {
            rightarm.rotateAngleX = -1.04533F;
            rightarm.rotateAngleY = -0.55851F;
            rightarm.rotateAngleZ = 0.0F;
            leftarm.rotateAngleX = -1.04533F;
            leftarm.rotateAngleY = 0.55851F;
            leftarm.rotateAngleZ = 0.0F;
        } else
        {
            rightarm.rotateAngleX = -1.74533F;
            rightarm.rotateAngleY = -0.55851F;
            rightarm.rotateAngleZ = 0.0F;
            leftarm.rotateAngleX = -1.74533F;
            leftarm.rotateAngleY = 0.55851F;
            leftarm.rotateAngleZ = 0.0F;
        }
        rightwing2.rotateAngleX = rightwing3.rotateAngleX = rightwing4.rotateAngleX = rightwing1.rotateAngleX = leftwing2.rotateAngleX = leftwing3.rotateAngleX = leftwing4.rotateAngleX = leftwing1.rotateAngleX = angleX;
        rightwing2.rotateAngleY = rightwing3.rotateAngleY = rightwing4.rotateAngleY = rightwing1.rotateAngleY = -angleY;
        leftwing2.rotateAngleY = leftwing3.rotateAngleY = leftwing4.rotateAngleY = leftwing1.rotateAngleY = angleY;
        rightwing2.rotateAngleZ = rightwing3.rotateAngleZ = rightwing4.rotateAngleZ = rightwing1.rotateAngleZ = angleZ;
        leftwing2.rotateAngleZ = leftwing3.rotateAngleZ = leftwing4.rotateAngleZ = leftwing1.rotateAngleZ = -angleZ;
    	}
    	else{
            rightarm.rotateAngleX = -1.74533F;
            rightarm.rotateAngleY = -0.55851F;
            rightarm.rotateAngleZ = 0.0F;
            leftarm.rotateAngleX = -1.74533F;
            leftarm.rotateAngleY = 0.55851F;
            leftarm.rotateAngleZ = 0.0F;
    	}
    }

    private float toRadians(float f)
    {
        return f / 57.29578F;
    }

    private float toDegrees(float f)
    {
        return f * 57.29578F;
    }
}
