package ivorius.psychedelicraft.client.screen;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import ivorius.psychedelicraft.recipe.BottleRecipe;
import ivorius.psychedelicraft.recipe.BunsenBurnerRecipe;
import ivorius.psychedelicraft.recipe.DryingRecipe;
import ivorius.psychedelicraft.recipe.FluidAwareShapelessRecipe;
import ivorius.psychedelicraft.recipe.HardeningRecipe;
import ivorius.psychedelicraft.recipe.MashingRecipe;
import ivorius.psychedelicraft.recipe.MixingRecipe;
import ivorius.psychedelicraft.recipe.SmeltingFluidRecipe;
import ivorius.psychedelicraft.screen.DrugGuideScreenHandler;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;

public class DrugGuideScreen extends HandledScreen<DrugGuideScreenHandler> {
    private static final int ENTRY_HEIGHT = 12;
    private static final int ENTRY_COUNT = 17;
    private static final int LIST_WIDTH = 160;

    private final List<Item> allItems = new ArrayList<>();
    private int selection = 0;
    private int scroll = 0;

    public DrugGuideScreen(DrugGuideScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        backgroundWidth = 380;
        backgroundHeight = 240;
        playerInventoryTitle = Text.empty();
        titleX = 10;
        titleY = 8;
    }

    @Override
    protected void init() {
        super.init();
        rebuildItems();
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        if (isInsideList(mouseX, mouseY)) {
            if (verticalAmount < 0) {
                scroll = Math.min(maxScroll(), scroll + 1);
            } else if (verticalAmount > 0) {
                scroll = Math.max(0, scroll - 1);
            }
            return true;
        }
        return super.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0 && isInsideList(mouseX, mouseY)) {
            int localY = (int)mouseY - (y + 26);
            int idx = scroll + localY / ENTRY_HEIGHT;
            if (idx >= 0 && idx < allItems.size()) {
                selection = idx;
                return true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (allItems.isEmpty()) {
            return super.keyPressed(keyCode, scanCode, modifiers);
        }
        if (keyCode == 264) {
            selection = Math.min(selection + 1, allItems.size() - 1);
            ensureVisible();
            return true;
        }
        if (keyCode == 265) {
            selection = Math.max(selection - 1, 0);
            ensureVisible();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        context.fill(x, y, x + backgroundWidth, y + backgroundHeight, 0xCC111111);
        context.fill(x + 6, y + 20, x + 6 + LIST_WIDTH, y + 20 + ENTRY_COUNT * ENTRY_HEIGHT + 8, 0xAA000000);
        context.fill(x + 172, y + 20, x + backgroundWidth - 6, y + backgroundHeight - 6, 0xAA000000);

        context.drawText(textRenderer, Text.translatable("gui.psychedelicraft.drug_guide.substances"), x + 10, y + 10, Colors.WHITE, false);
        context.drawText(textRenderer, Text.translatable("gui.psychedelicraft.drug_guide.details"), x + 176, y + 10, Colors.WHITE, false);

        int top = y + 26;
        int bottom = y + 26 + ENTRY_COUNT * ENTRY_HEIGHT;
        for (int i = 0; i < ENTRY_COUNT; i++) {
            int idx = scroll + i;
            if (idx >= allItems.size()) {
                break;
            }
            Item item = allItems.get(idx);
            int rowY = top + i * ENTRY_HEIGHT;
            if (idx == selection) {
                context.fill(x + 8, rowY - 1, x + 8 + LIST_WIDTH - 4, rowY + ENTRY_HEIGHT - 1, 0x55FFFFFF);
            }
            context.drawItem(item.getDefaultStack(), x + 10, rowY);
            context.drawText(textRenderer, item.getName(), x + 30, rowY + 2, Colors.GRAY, false);
        }

        int detailsX = x + 178;
        int detailsY = y + 26;
        if (!allItems.isEmpty() && selection >= 0 && selection < allItems.size()) {
            Item selected = allItems.get(selection);
            context.drawItem(selected.getDefaultStack(), detailsX, detailsY);
            context.drawText(textRenderer, selected.getName(), detailsX + 20, detailsY + 4, Colors.WHITE, false);

            RecipeGuide guide = buildGuide(selected);
            int lineY = detailsY + 22;
            lineY = drawSection(context, Text.translatable("gui.psychedelicraft.drug_guide.how_to_make"), guide.howToMake(), detailsX, lineY);
            drawSection(context, Text.translatable("gui.psychedelicraft.drug_guide.used_for"), guide.usedFor(), detailsX, lineY + 4);
        }

        if (allItems.size() > ENTRY_COUNT) {
            int barHeight = Math.max(12, (ENTRY_COUNT * ENTRY_HEIGHT * ENTRY_COUNT) / allItems.size());
            int scrollRange = maxScroll();
            int barTop = top + (scrollRange <= 0 ? 0 : ((ENTRY_COUNT * ENTRY_HEIGHT - barHeight) * scroll) / scrollRange);
            context.fill(x + 6 + LIST_WIDTH - 4, top, x + 6 + LIST_WIDTH - 2, bottom, 0xFF333333);
            context.fill(x + 6 + LIST_WIDTH - 4, barTop, x + 6 + LIST_WIDTH - 2, barTop + barHeight, 0xFFBBBBBB);
        }
    }

    private int drawSection(DrawContext context, Text title, List<Text> lines, int px, int py) {
        context.drawText(textRenderer, title, px, py, Colors.WHITE, false);
        int line = py + 12;
        if (lines.isEmpty()) {
            context.drawText(textRenderer, Text.translatable("gui.psychedelicraft.drug_guide.none"), px, line, Colors.GRAY, false);
            return line + 10;
        }
        for (Text entry : lines) {
            context.drawText(textRenderer, Text.literal("• ").append(entry), px, line, Colors.GRAY, false);
            line += 10;
            if (line > y + backgroundHeight - 18) {
                break;
            }
        }
        return line;
    }

    private void rebuildItems() {
        RecipeManager manager = getRecipeManager();
        var lookup = getLookup();
        if (manager == null || lookup == null) {
            return;
        }
        Set<Item> items = new LinkedHashSet<>();
        manager.values().forEach(recipe -> addRecipeItems(items, recipe.value(), lookup));
        allItems.clear();
        allItems.addAll(items.stream().sorted(Comparator.comparing(i -> i.getName().getString())).toList());
        selection = Math.min(selection, Math.max(0, allItems.size() - 1));
        ensureVisible();
    }

    private void addRecipeItems(Set<Item> items, Recipe<?> recipe, net.minecraft.registry.RegistryWrapper.WrapperLookup lookup) {
        if (recipe instanceof CraftingRecipe crafting) {
            crafting.getIngredients().forEach(ingredient -> ingredient.getMatchingItems().forEach(entry -> items.add(entry.value())));
            ItemStack result = crafting.getResult(lookup);
            if (!result.isEmpty()) {
                items.add(result.getItem());
            }
        } else if (recipe instanceof DryingRecipe drying) {
            items.add(drying.output().getItem());
            drying.input().getMatchingItems().forEach(entry -> items.add(entry.value()));
        } else if (recipe instanceof MashingRecipe mashing) {
            mashing.ingredients().ingredients().forEach(ingredient -> ingredient.getMatchingItems().forEach(entry -> items.add(entry.value())));
        } else if (recipe instanceof ivorius.psychedelicraft.recipe.ReactingRecipe reacting) {
            reacting.ingredients().solids().forEach(ingredient -> ingredient.getMatchingItems().forEach(entry -> items.add(entry.value())));
            if (!reacting.result().byProduct().isEmpty()) {
                items.add(reacting.result().byProduct().getItem());
            }
        } else if (recipe instanceof HardeningRecipe hardening) {
            items.add(hardening.result().getItem());
        } else if (recipe instanceof SmeltingFluidRecipe smelting && !smelting.getResult().result().isEmpty()) {
            items.add(smelting.getResult().result().getItem());
        }
    }

    private RecipeGuide buildGuide(Item item) {
        RecipeManager manager = getRecipeManager();
        var lookup = getLookup();
        if (manager == null || lookup == null) {
            return RecipeGuide.EMPTY;
        }

        List<Text> craft = new ArrayList<>();
        List<Text> usage = new ArrayList<>();

        manager.values().forEach(recipeEntry -> {
            Recipe<?> recipe = recipeEntry.value();
            boolean produces = producesItem(recipe, item);
            boolean uses = usesItem(recipe, item);

            if (produces) {
                craft.add(describeRecipe(recipe, true));
            }
            if (uses) {
                usage.add(describeRecipe(recipe, false));
            }
        });

        return new RecipeGuide(deduplicate(craft), deduplicate(usage));
    }

    private boolean usesItem(Recipe<?> recipe, Item item) {
        if (recipe instanceof CraftingRecipe crafting) {
            return crafting.getIngredients().stream().anyMatch(ingredient -> ingredient.getMatchingItems().anyMatch(entry -> entry.value() == item));
        }
        if (recipe instanceof DryingRecipe drying) {
            return drying.input().getMatchingItems().anyMatch(entry -> entry.value() == item);
        }
        if (recipe instanceof MashingRecipe mashing) {
            return mashing.ingredients().ingredients().stream().anyMatch(ingredient -> ingredient.getMatchingItems().anyMatch(entry -> entry.value() == item));
        }
        if (recipe instanceof ivorius.psychedelicraft.recipe.ReactingRecipe reacting) {
            return reacting.ingredients().solids().stream().anyMatch(ingredient -> ingredient.getMatchingItems().anyMatch(entry -> entry.value() == item));
        }
        if (recipe instanceof FluidAwareShapelessRecipe fluidAware) {
            return fluidAware.getFluidAwareIngredients().stream()
                    .map(i -> i.receptical())
                    .flatMap(Optional::stream)
                    .anyMatch(ingredient -> ingredient.getMatchingItems().anyMatch(entry -> entry.value() == item));
        }
        if (recipe instanceof MixingRecipe mixing) {
            return mixing.getIngredients().stream().anyMatch(ingredient -> ingredient.getMatchingItems().anyMatch(entry -> entry.value() == item));
        }
        if (recipe instanceof BottleRecipe bottle) {
            return bottle.getIngredients().stream().anyMatch(ingredient -> ingredient.getMatchingItems().anyMatch(entry -> entry.value() == item));
        }
        return false;
    }

    private boolean producesItem(Recipe<?> recipe, Item item) {
        var lookup = getLookup();
        if (recipe instanceof CraftingRecipe crafting && lookup != null && crafting.getResult(lookup).isOf(item)) {
            return true;
        }
        if (recipe instanceof DryingRecipe drying) {
            return drying.output().isOf(item);
        }
        if (recipe instanceof HardeningRecipe hardening) {
            return hardening.result().isOf(item);
        }
        if (recipe instanceof ivorius.psychedelicraft.recipe.ReactingRecipe reacting) {
            return reacting.result().byProduct().isOf(item);
        }
        if (recipe instanceof SmeltingFluidRecipe smelting) {
            return smelting.getResult().result().isOf(item);
        }
        return false;
    }

    private Text describeRecipe(Recipe<?> recipe, boolean make) {
        Text label = Text.literal(recipe.getType().toString());
        if (recipe instanceof DryingRecipe) {
            label = Text.translatable("block.psychedelicraft.drying_table");
        } else if (recipe instanceof MashingRecipe) {
            label = Text.translatable("block.psychedelicraft.mash_tub");
        } else if (recipe instanceof BunsenBurnerRecipe) {
            label = Text.translatable("block.psychedelicraft.bunsen_burner");
        } else if (recipe instanceof HardeningRecipe) {
            label = Text.translatable("block.psychedelicraft.tray");
        } else if (recipe instanceof SmeltingFluidRecipe) {
            label = Text.translatable("gui.psychedelicraft.drug_guide.furnace");
        } else if (recipe instanceof CraftingRecipe) {
            label = Text.translatable("gui.psychedelicraft.drug_guide.crafting");
        }
        return make ? Text.translatable("gui.psychedelicraft.drug_guide.recipe_make", label) : Text.translatable("gui.psychedelicraft.drug_guide.recipe_use", label);
    }

    private List<Text> deduplicate(List<Text> values) {
        return values.stream()
                .collect(Collectors.toMap(Text::getString, t -> t, (a, b) -> a, java.util.LinkedHashMap::new))
                .values()
                .stream()
                .toList();
    }

    private boolean isInsideList(double mouseX, double mouseY) {
        return mouseX >= x + 8 && mouseX <= x + 8 + LIST_WIDTH - 6 && mouseY >= y + 26 && mouseY <= y + 26 + ENTRY_COUNT * ENTRY_HEIGHT;
    }

    private void ensureVisible() {
        if (selection < scroll) {
            scroll = selection;
        }
        if (selection >= scroll + ENTRY_COUNT) {
            scroll = selection - ENTRY_COUNT + 1;
        }
        scroll = Math.max(0, Math.min(scroll, maxScroll()));
    }

    private int maxScroll() {
        return Math.max(0, allItems.size() - ENTRY_COUNT);
    }

    private RecipeManager getRecipeManager() {
        return client == null || client.world == null ? null : client.world.getRecipeManager();
    }

    private net.minecraft.registry.RegistryWrapper.WrapperLookup getLookup() {
        return client != null && client.world != null ? client.world.getRegistryManager() : null;
    }

    private record RecipeGuide(List<Text> howToMake, List<Text> usedFor) {
        static final RecipeGuide EMPTY = new RecipeGuide(List.of(), List.of());
    }
}
