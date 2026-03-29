# Getting Started

## First login behavior

When a player joins or respawns, Psychedelicraft checks inventory and gives a **Drug Guide** if missing (`Psychedelicraft.java`, `giveGuideIfMissing`).

## Basic progression loop

1. **Collect seeds/plants** (cannabis, tobacco, coca, hop, coffea, morning glory, jimsonweed, belladonna, peyote, agave, tomatoes, grapes, juniper, etc.).
2. **Process raw materials**:
   - Drying (`drying_table`, `iron_drying_table`)
   - Mashing/fermenting (`mash_tub`, barrels)
   - Distillation (`flask`, `distillery`)
   - Chemistry/evaporation (`bunsen_burner`, `tray`, glass tube/valve, pump)
3. **Consume through delivery systems**:
   - Drink containers (mug/cup/chalice/shot glass/bottle)
   - Smokeables (cigarette/cigar/joint/blunt/pipe/bong)
   - Snortables/pills/edibles
   - Syringe injections
4. **Manage effects** via dosage, decay over time, and world/system interactions.

## Core workstation roles

- **Wooden Vat (`mash_tub`)**: large fluid processing + fermentation path start.
- **Barrels**: maturation/aging.
- **Flask / Distillery**: distillation.
- **Drying tables**: convert wet/raw leaves/plants to dried ingredients.
- **Bunsen burner + tray + glass network + pump**: chemistry and advanced processing.
- **Rift jar**: captures/contains rift fraction (if enabled in config).

## Consumption categories

- **Drinkable:** fluid containers and fluid-bearing vanilla proxy items.
- **Smokeable/Inhaled:** cigarette/cigar/joint/blunt/pipe/bong consumables.
- **Ingested:** tablets, mushrooms, muffins, berries, etc.
- **Immediate/snorted:** powders and some concentrated forms.
- **Injected:** syringe with injectable fluids.

## Admin/testing shortcuts

Use:

- `/drug ...` to query/add/set/lock drug values
- `/hallucinate ...` to trigger hallucination packets
- `/vomit [target]` to force vomit event
