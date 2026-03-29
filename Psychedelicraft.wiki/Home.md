# Psychedelicraft Wiki

Psychedelicraft adds a full drug/alcohol production pipeline, custom fluids, custom processing blocks, hallucinatory visual/audio effects, and extensive configuration on Fabric.

This wiki is generated from the current source tree in `/src/main/java` and `/src/main/resources`.

## Quick Navigation

- [Installation and Compatibility](Installation-and-Compatibility)
- [Getting Started](Getting-Started)
- [Blocks and Items Reference](Blocks-and-Items-Reference)
- [Fluids and Drug Effects Reference](Fluids-and-Drug-Effects-Reference)
- [Commands, Gamerule, and Configuration](Commands-Gamerule-and-Configuration)
- [World Generation and Technical Systems](World-Generation-and-Technical-Systems)
- [Troubleshooting](Troubleshooting)

## What the mod includes

- **73 blocks** (`PSBlocks.java`)
- **108 items** (`PSItems.java`)
- **33 fluids** (`PSFluids.java`)
- **21 drug types** (`DrugType.java`)
- **4 entities** (`PSEntities.java`)
- **6 particles** (`PSParticles.java`)
- **15 named base sound events** + one dynamic `drug.<id>` sound event per drug type (`PSSounds.java`, `DrugType.java`)
- **1 custom status effect** (`teeth_grinding`)
- **3 admin commands** (`/drug`, `/hallucinate`, `/vomit`)
- **1 gamerule** (`doSleepDeprivation`)

## Canonical source files

- Mod metadata: `src/main/resources/fabric.mod.json`
- Runtime bootstrap: `src/main/java/ivorius/psychedelicraft/Psychedelicraft.java`
- Registries:
  - Blocks: `src/main/java/ivorius/psychedelicraft/block/PSBlocks.java`
  - Items: `src/main/java/ivorius/psychedelicraft/item/PSItems.java`
  - Fluids: `src/main/java/ivorius/psychedelicraft/fluid/PSFluids.java`
  - Drugs: `src/main/java/ivorius/psychedelicraft/entity/drug/DrugType.java`
  - Entities: `src/main/java/ivorius/psychedelicraft/entity/PSEntities.java`
  - Recipes: `src/main/java/ivorius/psychedelicraft/recipe/PSRecipes.java`
- Config:
  - Server/common: `src/main/java/ivorius/psychedelicraft/config/PSConfig.java`
  - Client: `src/main/java/ivorius/psychedelicraft/client/PSClientConfig.java`
