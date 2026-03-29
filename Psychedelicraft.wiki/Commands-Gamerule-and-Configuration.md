# Commands, Gamerule, and Configuration

## Commands

Registered in `PSCommands.java`.

### `/drug`

Implemented in `DrugCommand.java`.

- Permission level: `2`
- Targets: players
- Subcommands:
  - `get [drug]`
  - `set <drug|all> <value 0..1>`
  - `add <drug|all> <value 0..1>`
  - `lock <drug|all> <true|false>`

### `/hallucinate`

Implemented in `HallucinateCommand.java`.

- Permission level: `2`
- Usage pattern: `/hallucinate <type> [target|position]`
- Hallucination type suggestions are populated from `HallucinationTypeKeys`.

### `/vomit`

Implemented in `VomitCommand.java`.

- Permission level: `2`
- Usage: `/vomit [target]`

## Gamerule

From `PSGameRules.java`:

- `doSleepDeprivation` (boolean, default `false`, category `SPAWNING`)

## Common/server config (`psychedelicraft.json`)

Defined in `PSConfig.java`:

- `balancing.randomTicksUntilRiftSpawn`
- `balancing.dryingTableTickDuration`
- `balancing.ironDryingTableTickDuration`
- `balancing.slurryHardeningTime`
- `balancing.enableHarmonium`
- `balancing.enableRiftJars`
- `balancing.disableMolotovs`
- `balancing.worldGeneration` (per-feature enable + biome selectors)
- `balancing.fluidAttributes` (fluid tick rates)
- `balancing.messageDistortion` (`OUTGOING`, `INCOMING`, `BOTH`, `NONE`)

## Client config (`psychedelicraft_client.json`)

Defined in `PSClientConfig.java`:

- Depth-of-field controls:
  - `visual.dofFocalPointNear`
  - `visual.dofFocalBlurNear`
  - `visual.dofFocalPointFar`
  - `visual.dofFocalBlurFar`
- Shader/visual toggles:
  - `visual.shader2DEnabled`
  - `visual.shader3DEnabled`
  - `visual.doHeatDistortion`
  - `visual.doWaterDistortion`
  - `visual.doMotionBlur`
  - `visual.sunFlareIntensity`
  - `visual.waterOverlayEnabled`
  - `visual.hurtOverlayEnabled`
  - `visual.digitalEffectPixelRescale`
- Audio:
  - `audio.drugsBackgroundMusic`
  - `audio.drugsBackgroundMusicThreshold`
- Compatibility:
  - `compatibility.irisSupport`
  - `compatibility.sodiumSupport`
- Debug:
  - `debug.printsShaderSources`
  - `debug.forceShaderRecompiles`
