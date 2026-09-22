# Fix KSP Build Error: unexpected jvm signature V

The project is currently failing to build with the error `[ksp] java.lang.IllegalStateException: unexpected jvm signature V`. This error is a known compatibility issue between **Room 2.6.1** and **Kotlin 2.1.0** when using **KSP**. The error occurs because the Kotlin 2.1.0 compiler generates metadata that Room 2.6.1's KSP processor cannot correctly parse for `suspend` functions returning `Unit` (represented by `V` in JVM signatures).

## Proposed Changes

### Build Configuration

#### [MODIFY] [libs.versions.toml](file:///C:/Users/monikab/Downloads/little-lemon/-little-lemon/LittleLemon/gradle/libs.versions.toml)
- Update Room version from `2.6.1` to `2.7.0-alpha11` (or a compatible version for Kotlin 2.1.0).

## Verification Plan

### Automated Tests
- Run `./gradlew :app:kspDebugKotlin` to verify the KSP error is resolved.
- Run `./gradlew assembleDebug` to ensure the full build succeeds.

### Manual Verification
- None required beyond build success.
