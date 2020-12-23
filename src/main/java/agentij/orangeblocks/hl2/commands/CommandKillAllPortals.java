package agentij.orangeblocks.hl2.commands;

import agentij.orangeblocks.hl2.common.tileentity.TileEntityPortal;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.swing.text.html.parser.Entity;
import java.util.List;

//TODO: Make this
public class CommandKillAllPortals extends CommandBase {
    @Override
    public String getName() {
        return "nearbyportalkill";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "nearbyportalkill <range>";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length < 1)
        {
            return;
        }
        String s = args[0];
        double range;
        try
        {
           range=Double.parseDouble(s);


        }
        catch (NumberFormatException n)
        {
            sender.sendMessage(new TextComponentString(TextFormatting.GOLD+ new TextComponentTranslation("hl2.error.portalkillcommand").getUnformattedComponentText()));
            return;
        }

        if (sender instanceof EntityPlayer)
        {

            World world = sender.getEntityWorld();
            if (!world.isRemote)
            {
                BlockPos pos = sender.getPosition();
                AxisAlignedBB scanarea = new AxisAlignedBB(pos).grow(range);
                BlockPos min = new BlockPos(scanarea.minX, scanarea.minY, scanarea.minZ);
                BlockPos max = new BlockPos(scanarea.maxX, scanarea.maxY, scanarea.maxZ);
                Iterable<BlockPos> blocks= BlockPos.getAllInBox(min, max);
                for (BlockPos blockPos : blocks)
                {
                    TileEntity te = world.getTileEntity(pos);
                    if (te instanceof TileEntityPortal)
                    {
                        TileEntityPortal portal = (TileEntityPortal) te;
                        world.setBlockToAir(blockPos);
                        if(portal.face.getAxis() != EnumFacing.Axis.Y)
                        {
                            BlockPos offset = portal.top ? pos.down() : pos.up();
                            if (world.getTileEntity(offset) instanceof TileEntityPortal)
                            {
                                world.setBlockToAir(offset);
                            }
                        }
                    }
                }
            }

        }
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }
}
