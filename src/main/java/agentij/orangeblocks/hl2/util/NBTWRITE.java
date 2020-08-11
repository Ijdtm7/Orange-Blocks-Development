package agentij.orangeblocks.hl2.util;

import net.minecraft.nbt.NBTTagCompound;
import org.apache.commons.lang3.Validate;

public class NBTWRITE {
    private NBTTagCompound tagCompound;
    public NBTWRITE(NBTTagCompound nbtTagCompound)
    {
        Validate.notNull(nbtTagCompound);
        tagCompound=nbtTagCompound;
    }
    public NBTWRITE setString(String key, String s)
    {
        tagCompound.setString(key, s);
        return this;
    }



    public NBTTagCompound getResult()
    {
        return tagCompound;
    }
}
