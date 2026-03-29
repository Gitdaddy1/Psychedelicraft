# World Generation and Technical Systems

## World generation

From `PSWorldGen.java` and `PSWorldGenFeatures.java`:

### Tree and wild patch features

- Juniper trees: dense + sparse variants
- Wild patches: morning glory, belladonna, jimsonweed, tomatoes, peyote, agave
- Tilled patch generation: cannabis, hop, tobacco, coffea, coca

### Config-gated feature enablement

Each worldgen feature checks `Psychedelicraft.getConfig().worldGeneration` and biome filters before injection.

## Recipe systems

From `PSRecipes.java`:

### Recipe types

- `mashing`
- `chemistry`
- `drying`
- `tray`

### Serializers

- `fill_receptical`
- `change_receptical`
- `crafting_pouring`
- `smelting_receptical`
- `crafting_shaped`
- `crafting_shapeless_fluid`
- `mashing`
- `reacting`
- `drying`
- `hardening`

## Entity registry

From `PSEntities.java`:

- `molotov_cocktail`
- `reality_rift`
- `juniper_boat`
- `juniper_chest_boat`

## Tag keys

From `PSTags.java`:

### Item tags

- `bottle_rack_insertable`
- `bunsen_burner_insertable`
- `barrels`
- `juniper_logs`
- `drying_tables`
- `receptical/all`
- `receptical/placeable`
- `receptical/drinks`
- `receptical/drugs`
- `receptical/suitable_for_hot_drinks`
- `receptical/suitable_for_alcoholic_drinks`
- `receptical/suitable_for_shots`
- `can_go_into_paper_bag`
- `drug_crop_seeds`
- `ingredients/morning_glory`

### Block tags

- `barrels`
- `lattices`
- `drying_tables`
- `juniper_logs`
- `nightshade`

### Entity tags

- `multiple_entity_hallucinations`
- `single_entity_hallucinations`

### Damage type tags

- `is_biological`
- `is_incediary` (spelling as in source)

### Biome tags

- `has_dense_juniper_trees`
- `has_sparce_juniper_trees`
- `has_morning_glory`
- `has_belladonna`
- `has_jimsonweed`
- `has_tomatoes`
- `has_peyote`

## Registries and bootstrap order

`Psychedelicraft.onInitialize()` initializes registries/systems in this order:

1. `PSBlockEntities`
2. `PSBlocks`
3. `PSItems`
4. `PSTags`
5. `PSItemGroups`
6. `PSFluids`
7. `PSRecipes`
8. `PSEntities`
9. `PSEffects`
10. `PSWorldGen`
11. `PSGameRules`
12. `PSCommands`
13. `PSSounds`
14. `PSScreenHandlers`
15. networking channel
16. criteria, particles, damage types, variant marshal
