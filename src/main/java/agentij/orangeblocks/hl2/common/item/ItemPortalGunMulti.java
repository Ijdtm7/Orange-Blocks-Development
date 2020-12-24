package agentij.orangeblocks.hl2.common.item;

import agentij.orangeblocks.hl2.Main;
import agentij.orangeblocks.hl2.common.entity.EntityPortalProjectile;
import agentij.orangeblocks.hl2.util.handlers.SoundRegistry;
import agentij.orangeblocks.hl2.util.interfaces.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemPortalGunMulti extends Item {


    public ItemPortalGunMulti() {
        setMaxStackSize(1);
        setHasSubtypes(true);
        setMaxDamage(0);
        setRegistryName("portalgunmulti");
        setUnlocalizedName("portalgunmulti");
        setCreativeTab(Main.portalBasicTab);
    }


    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand handIn) {
        if(!world.isRemote)
        {
            ItemStack is = player.getHeldItem(handIn);
            world.playSound(null, player.posX, player.posY + player.getEyeHeight(), player.posZ, SoundRegistry.firered, SoundCategory.PLAYERS, 0.3F, 1.0F);
            world.spawnEntity(new EntityPortalProjectile(world, player, true));
            is.setItemDamage(1);
        }
        return new ActionResult(EnumActionResult.SUCCESS, player.getHeldItem(handIn));
    }

    @Override
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
        if (entityLiving instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entityLiving;
            World world = player.getEntityWorld();
            EnumHand handIn = player.getActiveHand();
            if(!world.isRemote)
            {
                ItemStack is = player.getHeldItem(handIn);
                world.playSound(null, player.posX, player.posY + player.getEyeHeight(), player.posZ, SoundRegistry.fireblue, SoundCategory.PLAYERS, 0.3F, 1.0F);
                world.spawnEntity(new EntityPortalProjectile(world, player, false));
                is.setItemDamage(0);
            }

        }
        return true;
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player) {
        return true;
    }
}
