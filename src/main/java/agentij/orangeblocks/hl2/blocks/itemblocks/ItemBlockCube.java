package agentij.orangeblocks.hl2.blocks.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
@SuppressWarnings("deprecation")
public class ItemBlockCube extends ItemBlock {
    public ItemBlockCube(Block block) {
        super(block);
        setMaxStackSize(1);
    }


    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return I18n.translateToLocal("tile.cube." + getDamage(stack)).trim();

    }
}
