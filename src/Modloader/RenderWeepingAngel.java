// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            RenderLiving, ModelWeepingAngel, EntityWeepingAngel, EntityLiving, 
//            Entity

public class RenderWeepingAngel extends RenderLiving
{

    public RenderWeepingAngel(float f)
    {
        super(new ModelWeepingAngel(), f);
    }

    public void renderWeepingAngel(EntityWeepingAngel entityweepingangel, double d, double d1, double d2, 
            float f, float f1)
    {
        super.doRenderLiving(entityweepingangel, d, d1, d2, f, f1);
    }

    public void doRenderLiving(EntityLiving entityliving, double d, double d1, double d2, 
            float f, float f1)
    {
        renderWeepingAngel((EntityWeepingAngel)entityliving, d, d1, d2, f, f1);
    }

    public void doRender(Entity entity, double d, double d1, double d2, 
            float f, float f1)
    {
        renderWeepingAngel((EntityWeepingAngel)entity, d, d1, d2, f, f1);
    }
}
