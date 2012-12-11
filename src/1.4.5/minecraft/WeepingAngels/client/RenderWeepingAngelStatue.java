package WeepingAngels.client;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.src.RenderLiving;

@SideOnly(Side.CLIENT)
public class RenderWeepingAngelStatue extends RenderLiving{

	public RenderWeepingAngelStatue()
	{
		super(new ModelWeepingAngel(), 0.5f);
	}
}
