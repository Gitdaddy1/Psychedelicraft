package ivorius.psychedelicraft.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;

public class DrugGuideScreenHandler extends ScreenHandler {
    public DrugGuideScreenHandler(int syncId, PlayerInventory inventory) {
        super(PSScreenHandlers.DRUG_GUIDE, syncId);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }
}
