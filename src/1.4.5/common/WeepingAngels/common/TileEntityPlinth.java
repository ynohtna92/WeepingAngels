package WeepingAngels.common;

import net.minecraft.src.Entity;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.TileEntity;

public class TileEntityPlinth extends TileEntity
{
	public int statueType = WeepingAngelsMod.statue.shiftedIndex;
	public String signText[] = {
			"", ""
	};
	public int lineBeingEdited;
	private boolean isEditable;
	public Entity statueEntity;

	public TileEntityPlinth()
	{
		lineBeingEdited = -1;
		isEditable = true;
	}

	public void updateEntity()
	{
		super.updateEntity();
	}

	public void writeToNBT(NBTTagCompound nbttagcompound)
	{
		super.writeToNBT(nbttagcompound);
		nbttagcompound.setString("Text1", signText[0]);
		nbttagcompound.setString("Text2", signText[1]);
		nbttagcompound.setInteger("Type", statueType);
	}

	public void readFromNBT(NBTTagCompound nbttagcompound)
	{
		isEditable = false;
		super.readFromNBT(nbttagcompound);
		for(int i = 0; i < 2; i++)
		{
			signText[i] = nbttagcompound.getString((new StringBuilder()).append("Text").append(i + 1).toString());
			if(signText[i].length() > 15)
			{
				signText[i] = signText[i].substring(0, 15);
			}
		}

		statueType = nbttagcompound.getInteger("Type");
	}

}
