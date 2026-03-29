# Fluids and Drug Effects Reference

## Fluids (33)

From `PSFluids.java`:

`wheat_hop`, `wheat`, `potato`, `tomato`, `red_grapes`, `rice`, `juniper`, `honey`, `sugar_cane`, `corn`, `apple`, `pineapple`, `banana`, `milk`, `agave`, `coffee`, `coca_tea`, `cannabis_tea`, `peyote_juice`, `kava`, `ethanol`, `petrolium`, `gasoline`, `acid`, `atropine`, `cocaine`, `caffeine`, `bath_salts`, `morphine`, `morning_glory_extract`, `belladonna_extract`, `jimsonweed_extract`, `slurry`

### Fluid categories

- **Alcoholic fermentation chains:** wheat/hops, potato, tomato, grapes, rice, juniper, honey, sugar cane, corn, apple, pineapple, banana, milk, agave.
- **Drinkable drug fluids:** coffee, coca tea, cannabis tea, peyote juice, kava.
- **Injectables/solutions:** caffeine, bath salts, morphine, atropine, cocaine, acid, ethanol.
- **Extracts:** morning glory, belladonna, jimsonweed extracts.
- **Hazards/fuel:** petroleum (`petrolium` id), gasoline, slurry.

## Drug types (21)

From `DrugType.java`:

`alcohol`, `cannabis`, `brown_shrooms`, `red_shrooms`, `tobacco`, `coccaine`, `caffeine`, `sugar`, `bath_salts`, `sleep_deprivation`, `lsd`, `atropine`, `morphine`, `methamphetamine`, `kava`, `warmth`, `peyote`, `zero`, `power`, `harmonium`

Notes:

- `coccaine` is the registered internal ID spelling in source.
- Drug types dynamically register sound events under `drug.<id>`.

## Status effect

From `PSEffects.java`:

- `teeth_grinding` (harmful)

## Particles (6)

From `PSParticles.java`:

- `exhaled_smoke`
- `bubble`
- `dripping_fluid`
- `falling_fluid`
- `fluid_splash`
- `fluid_bubble`

## Base named sound events (15)

From `PSSounds.java`:

- `entity.player.heartbeat`
- `entity.player.breath`
- `entity.player.squeak`
- `entity.player.pacify`
- `block.rift_jar.toggle`
- `block.rift_jar.open`
- `block.rift_jar.close`
- `block.tray.harden`
- `block.valve.open`
- `block.valve.close`
- `item.syringe.inject`
- `item.broken_glass.eat`
- `block.bunsen_burner.work`
- `block.bunsen_burner.overheat`
- `block.bunsen_burner.fill`

## Post-processing effects

From `src/main/resources/assets/psychedelicraft/post_effect/`:

- `bloom.json`
- `blur.json`
- `blur_noise.json`
- `colored_bloom.json`
- `depth_of_field.json`
- `digital.json`
- `double_vision.json`
- `heat_distortion.json`
- `simple_effects.json`
- `underwater_overlay.json`
