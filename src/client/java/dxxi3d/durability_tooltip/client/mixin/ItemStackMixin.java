package dxxi3d.durability_tooltip.client.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Environment(EnvType.CLIENT)
@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @Shadow
    public abstract boolean isDamageableItem();

    @Shadow
    public abstract int getMaxDamage();

    @Shadow
    public abstract int getDamageValue();

    @Inject(method = "addDetailsToTooltip", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/TooltipFlag;isAdvanced()Z"))
    private void dxxi3d$durabilityTooltip$add(Item.TooltipContext context, TooltipDisplay display, @Nullable Player player, TooltipFlag tooltipFlag, Consumer<Component> builder, CallbackInfo ci) {
        if (!this.isDamageableItem() || !display.shows(DataComponents.DAMAGE)) {
            return;
        }

        int maxDamage = this.getMaxDamage();
        int currentDamage = maxDamage - this.getDamageValue();

        builder.accept(Component.empty());
        builder.accept(Component.translatable("item.durability", currentDamage, maxDamage));
    }

    @WrapOperation(method = "addDetailsToTooltip", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;isDamaged()Z"))
    private boolean dxxi3d$durabilityTooltip$hideVanilla(ItemStack instance, Operation<Boolean> original) {
        return false;
    }
}
