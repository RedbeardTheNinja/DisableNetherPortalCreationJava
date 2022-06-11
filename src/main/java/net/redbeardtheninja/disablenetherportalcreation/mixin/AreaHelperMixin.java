package net.redbeardtheninja.disablenetherportalcreation.mixin;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.dimension.AreaHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.redbeardtheninja.disablenetherportalcreation.DisableNetherPortalCreation.DISABLE_NETHER_PORTAL_CREATION;

@Mixin(AreaHelper.class)
public abstract class AreaHelperMixin {
    @Accessor("world")
    abstract WorldAccess getServer();

    @Inject(
            method = "createPortal",
            at = @At(
                    value = "HEAD"
            ),
            cancellable = true
    )
    private void createPortal(CallbackInfo ci) {
        if (getServer() != null) {
            MinecraftServer server = getServer().getServer();
            if (server != null && server.getGameRules().getBoolean(DISABLE_NETHER_PORTAL_CREATION)) {
                ci.cancel();
            }
            if (server == null) {
                ci.cancel();
            }
        }
        if (getServer() == null) {
            ci.cancel();
        }
    }
}
