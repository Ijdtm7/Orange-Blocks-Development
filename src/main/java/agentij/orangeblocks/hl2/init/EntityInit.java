/*
 * Copyright (c) 6/21/2020 Agent Ij
 */

package agentij.orangeblocks.hl2.init;

import agentij.orangeblocks.hl2.Main;
import agentij.orangeblocks.hl2.util.QuickRef;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import javax.annotation.Nullable;
import java.awt.*;

public class EntityInit {
    @SuppressWarnings("unused")
    public static void RegisterEntities()
    {
        int entityIndex=-1;
    }



    private static void registerEntity(String name, Class<? extends Entity> entity, int id, @Nullable EntityLiving.SpawnPlacementType placementType, @Nullable Color shellColor, @Nullable Color spotColor)
    {

        ResourceLocation resourceLocation = new ResourceLocation(QuickRef.ID, name);
        EntityRegistry.registerModEntity(new ResourceLocation(QuickRef.ID + ":" + name), entity, name, id, Main.instance, 64, 3, true);
        if (shellColor!= null && spotColor != null)
        {
            EntityRegistry.registerEgg(resourceLocation, shellColor.getRGB(), spotColor.getRGB());
        }
        if(placementType!=null) EntitySpawnPlacementRegistry.setPlacementType(entity,placementType);



    }

}
