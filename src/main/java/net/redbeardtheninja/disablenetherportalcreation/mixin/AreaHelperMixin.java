package net.redbeardtheninja.disablenetherportalcreation.mixin;

import net.minecraft.world.dimension.AreaHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AreaHelper.class)
public class AreaHelperMixin {
    @Inject(
            method = "createPortal",
            at = @At(
                    value = "HEAD"
            ),
            cancellable = true
    )
    private void createPortal(CallbackInfo ci) {
        ci.cancel();
    }
}
