package ivorius.psychedelicraft.item;

import ivorius.psychedelicraft.screen.DrugGuideScreenHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class DrugGuideItem extends Item {
    public DrugGuideItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        if (!world.isClient && player instanceof ServerPlayerEntity serverPlayer) {
            serverPlayer.openHandledScreen(new NamedScreenHandlerFactory() {
                @Override
                public Text getDisplayName() {
                    return Text.translatable("gui.psychedelicraft.drug_guide.title");
                }

                @Override
                public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
                    return new DrugGuideScreenHandler(syncId, playerInventory);
                }
            });
        }
        return ActionResult.SUCCESS;
    }
}
