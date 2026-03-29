# Installation and Compatibility

## Required environment

From `gradle.properties`, `build.gradle`, and `fabric.mod.json`:

- **Minecraft:** `1.21.5` (configured range `>=1.21.5`)
- **Loader:** Fabric (`fabric-loader >= 0.16.10`)
- **Fabric API:** `>=0.119.6+1.21.5`
- **Java:** runtime requires `>=17` (`fabric.mod.json`), build is configured with **toolchain 21** (`build.gradle`)

## Required/embedded dependencies

From `build.gradle`:

- `com.minelittlepony:kirin` (embedded)
- `com.sollace:fabwork` (embedded)
- `com.github.MattiDragon:TlaApi` (embedded)

## Optional integrations

- ModMenu
- Sodium (controlled by gradle property at build time)
- Iris (controlled by gradle property at build time)
- EMI or REI integration (selected by `tmi_type` gradle property)

## Known compatibility note

From `fabric.mod.json`:

- Declares conflict with `kubejs`.

## Build notes for contributors

- Repository README still mentions JDK 17 for local build commands.
- Build script is configured for Java toolchain 21, so using Java 21 is safest for source builds.
- In restricted environments, Gradle may fail to resolve `fabric-loom:1.10-SNAPSHOT` if the plugin is unavailable.
