# Troubleshooting

## Build fails before compiling

If Gradle fails resolving `fabric-loom:1.10-SNAPSHOT`, it is a plugin resolution issue (repo/network/environment), not a gameplay/data issue in this repository.

## No visual effects or incomplete hallucinations

Check:

- Client visual settings in `psychedelicraft_client.json` (shader/overlay toggles)
- Compatibility toggles (`irisSupport`, `sodiumSupport`)
- Whether your GPU/shader stack supports post effects

## Missing items in creative tabs

Some entries are intentionally config-gated:

- `enableHarmonium`
- `enableRiftJars`
- `disableMolotovs` (inverse behavior)

## World features not spawning

Check `balancing.worldGeneration` feature toggles/filters in `psychedelicraft.json`.

## No sleep deprivation effects

Enable gamerule:

```mcfunction
/gamerule doSleepDeprivation true
```

## Commands not available

All Psychedelicraft commands require permission level 2 (operator/admin).
