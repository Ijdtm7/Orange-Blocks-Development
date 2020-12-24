package agentij.orangeblocks.hl2.common.item;

import agentij.orangeblocks.hl2.Main;
import agentij.orangeblocks.hl2.common.entity.EntityPortalProjectile;
import agentij.orangeblocks.hl2.util.NBTWRITE;
import agentij.orangeblocks.hl2.util.handlers.SoundRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class ItemPortalGun extends Item
{

    public ItemPortalGun()
    {
        setMaxStackSize(1);
        setHasSubtypes(true);
        setMaxDamage(0);
        setRegistryName("portalgun");
        setUnlocalizedName("portalgun");
        setCreativeTab(Main.portalBasicTab);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand handIn)
    {
        if(!world.isRemote)
        {
            ItemStack is = player.getHeldItem(handIn);
            world.playSound(null, player.posX, player.posY + player.getEyeHeight(), player.posZ, is.getItemDamage() == 0 ? SoundRegistry.fireblue : SoundRegistry.firered, SoundCategory.PLAYERS, 0.3F, 1.0F);
            world.spawnEntity(new EntityPortalProjectile(world, player, is.getItemDamage() == 1));
        }
        return new ActionResult(EnumActionResult.SUCCESS, player.getHeldItem(handIn));
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab))
        {
            for(int i =0; i < 2; ++i)
            {
                ItemStack stack = new ItemStack(this, 1, i);
                if(i == 0)
                {
                    stack.setTagCompound(new NBTWRITE(new NBTTagCompound()).setString("name", "Blue Portal Gun").getResult());
                } else {
                    stack.setTagCompound(new NBTWRITE(new NBTTagCompound()).setString("name", "Orange Portal Gun").getResult());
                }
                items.add(stack);
            }
        }

    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return super.getItemStackDisplayName(stack)+ (stack.getTagCompound()!=null ? ""+stack.getTagCompound().getString("name") : "");
    }


}
