package club.someoneice.LMSYSG;

import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = LMSYSG.MODID, version = LMSYSG.VERSION,  acceptableRemoteVersions = "*")
public class LMSYSG {
    public static final String MODID = "LMSYSG";
    public static final String VERSION = "0.1";

    @EventHandler
    public void init(FMLInitializationEvent event) {
        FMLInterModComms.sendMessage("Waila", "register", "club.someoneice.LMSYSG.WailaPlayerPlugin.callbackRegister");
    }

    @EventHandler
    public void perInit(FMLPreInitializationEvent event) {

    }
}
