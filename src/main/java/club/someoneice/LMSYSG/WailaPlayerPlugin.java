package club.someoneice.LMSYSG;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaEntityAccessor;
import mcp.mobius.waila.api.IWailaEntityProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

import java.util.List;

public class WailaPlayerPlugin implements IWailaEntityProvider {
    @Override
    public Entity getWailaOverride(IWailaEntityAccessor accessor, IWailaConfigHandler iWailaConfigHandler) {
        return null;
    }

    @Override
    public List<String> getWailaHead(Entity entity, List<String> list, IWailaEntityAccessor accessor, IWailaConfigHandler iWailaConfigHandler) {
        return list;
    }

    @Override
    public List<String> getWailaBody(Entity entity, List<String> list, IWailaEntityAccessor accessor, IWailaConfigHandler iWailaConfigHandler) {
        return list;
    }

    @Override
    public List<String> getWailaTail(Entity entity, List<String> list, IWailaEntityAccessor accessor, IWailaConfigHandler iWailaConfigHandler) {
        if (accessor.getEntity() instanceof EntityPlayer) {
            ItemStack item = ((EntityPlayer) accessor.getEntity()).getHeldItem();
            if (item == null) return list;
            else item = item.copy();

            list.add(item.getDisplayName());
            if (item.getMaxDamage() > 0) list.add(item.getMaxDamage() - item.getItemDamage() + "/" + item.getMaxDamage());

            NBTTagList enchlist = item.getEnchantmentTagList();
            if (enchlist != null) for (int i = 0; i < enchlist.tagCount(); ++i) {
                short id = enchlist.getCompoundTagAt(i).getShort("id");
                short lv = enchlist.getCompoundTagAt(i).getShort("lvl");
                if (Enchantment.enchantmentsList[id] != null)
                    list.add(Enchantment.enchantmentsList[id].getTranslatedName(lv));
            }
        }

        return list;
    }

    @Override
    public NBTTagCompound getNBTData(EntityPlayerMP entityPlayerMP, Entity entity, NBTTagCompound nbtTagCompound, World world) {
        return null;
    }

    public static void callbackRegister(IWailaRegistrar registrar) {
        WailaPlayerPlugin instance = new WailaPlayerPlugin();
        registrar.registerTailProvider(instance, EntityPlayer.class);
    }
}
