package mcjty.lostcities.setup;


import mcjty.lostcities.LostCities;
import mcjty.lostcities.worldgen.LostCityFeature;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = LostCities.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Registration {

    @ObjectHolder("lostcities:lostcityfeature")
    public static LostCityFeature LOSTCITY_FEATURE;

    public static final RegistryKey<DimensionType> DIMENSION_TYPE = RegistryKey.create(Registry.DIMENSION_TYPE_REGISTRY, new ResourceLocation(LostCities.MODID, "lostcity"));
    public static final RegistryKey<World> DIMENSION = RegistryKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(LostCities.MODID, "lostcity"));

    @SubscribeEvent
    public static void registerFeatures(final RegistryEvent.Register<Feature<?>> event) {
        event.getRegistry().register(new LostCityFeature().setRegistryName("lostcityfeature"));
    }
}
