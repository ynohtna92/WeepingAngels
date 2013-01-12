package a_dizzle.weepingangels.client;


import net.minecraft.client.renderer.entity.RenderLiving;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderWeepingAngelStatue extends RenderLiving{

	public RenderWeepingAngelStatue()
	{
		super(new ModelWeepingAngel(), 0.5f);
	}
}
