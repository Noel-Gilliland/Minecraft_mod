package net.noel.tutorialmod.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.noel.tutorialmod.TutorialMod;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.food.FoodProperties;


public class Moditems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TutorialMod.MOD_ID);

    public static final RegistryObject<Item> ALEXANDRITE = ITEMS.register("alexandrite", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DAEMON = ITEMS.register("daemon", () ->
            new DaemonFoodItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(4)
                            .saturationModifier(0.3f)
                            .alwaysEdible()
                            .build()

                    )));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
