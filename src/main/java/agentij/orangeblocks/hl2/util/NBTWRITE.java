package agentij.orangeblocks.hl2.util;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import org.apache.commons.lang3.Validate;

import java.util.UUID;

public class NBTWRITE {
    private NBTTagCompound tagCompound;
    public NBTWRITE(NBTTagCompound nbtTagCompound)
    {
        Validate.notNull(nbtTagCompound);
        tagCompound=nbtTagCompound;
    }

    public NBTWRITE setInteger(String key, int i)
    {
        tagCompound.setInteger(key,i);
        return this;
    }

    public NBTWRITE setShort(String key, short s)
    {
        tagCompound.setShort(key, s);
        return this;
    }

    public NBTWRITE setByte(String key,byte b)
    {
        tagCompound.setByte(key, b);
        return this;
    }

    public NBTWRITE setLong(String key,long l)
    {
        tagCompound.setLong(key,l);
        return this;
    }

    public NBTWRITE setBoolean(String key, boolean b)
    {
        tagCompound.setBoolean(key, b);
        return this;
    }

    public NBTWRITE setFloat(String key, float f)
    {
        tagCompound.setFloat(key,f);
        return this;
    }

    public NBTWRITE setDouble(String key, double d)
    {
        tagCompound.setDouble(key, d);
        return this;
    }

    public NBTWRITE setString(String key, String s)
    {
        tagCompound.setString(key, s);
        return this;
    }

    public NBTWRITE setTagCompound(String key, NBTTagCompound nbtTagCompound)
    {
        tagCompound.setTag(key,nbtTagCompound);
        return this;
    }

    public NBTTagCompound getResult()
    {
        return tagCompound;
    }
}
