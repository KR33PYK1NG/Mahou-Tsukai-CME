package rmc.mixins.mahou_tsukai_cme.inject;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import stepsword.mahoutsukai.capability.settingsmahou.ISettingsMahou;
import stepsword.mahoutsukai.capability.settingsmahou.SettingsMahou;
import stepsword.mahoutsukai.capability.settingsmahou.SettingsMahouStorage;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.List;

/**
 * Developed by RMC Team, 2021
 * @author KR33PY
 */
@Mixin(value = SettingsMahouStorage.class)
public abstract class SettingsMahouStorageMixin {

    @Overwrite(remap = false)
    public INBT writeNBT(Capability<ISettingsMahou> capability, ISettingsMahou instance, Direction side) {
        CompoundNBT nbt = new CompoundNBT();
        try {
            ListNBT keys = new ListNBT();
            HashMap<Integer, List<Integer>> intsettings = new HashMap<>();
            intsettings.putAll(((SettingsMahou) instance).intsettings);
            for (Integer i : intsettings.keySet()) {
                CompoundNBT nbttmp = new CompoundNBT();
                nbttmp.putInt("key", i.intValue());
                nbttmp.putIntArray("values", intsettings.get(i));
                keys.add(nbttmp);
            }
            nbt.put("intsettings", (INBT) keys);
        } catch (ConcurrentModificationException cme) {}
        return nbt;
    }

}
