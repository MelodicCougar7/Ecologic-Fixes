package org.github.melodiccougar7.ecologic_fixes.mixin;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import samebutdifferent.ecologics.item.CoconutSliceItem;
import samebutdifferent.ecologics.registry.ModItems;

@MethodsReturnNonnullByDefault
@Mixin(CoconutSliceItem.class)
public class CoconutSliceNerf extends Item {

    public CoconutSliceNerf(Properties settings) {
        super(settings);
    }

    /**
     * @author MC7
     * @reason Terribly sorry, but some things just need to work before they are refactored later
     * Many thanks to ActiveRadar for proofing out the code and helping me to get it to compile!
     */

    @Overwrite

    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        if (!pLevel.isClientSide) {
            if (pLivingEntity instanceof Player player) {
                // player.removeAllEffects(); // behavior is the same, this is removed
                ItemStack mainHandStack = player.getMainHandItem();
                ItemStack coconutHuskStack = new ItemStack(ModItems.COCONUT_HUSK.get());
                if (!player.getAbilities().instabuild) {
                    if (!mainHandStack.isEmpty()) {
                        if (!player.getInventory().add(coconutHuskStack.copy())) {
                            player.drop(coconutHuskStack, false);
                        }
                    }
                }
            }
        }
        return super.finishUsingItem(pStack, pLevel, pLivingEntity);
    }
}