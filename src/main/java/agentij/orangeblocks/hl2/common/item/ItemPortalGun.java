package agentij.orangeblocks.hl2.common.item;

import agentij.orangeblocks.hl2.common.entity.EntityPortalProjectile;
import agentij.orangeblocks.hl2.util.handlers.SoundRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
        setCreativeTab(CreativeTabs.TOOLS);
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
}
