import xyz.deftu.gradle.tools.minecraft.VersionType
import xyz.deftu.gradle.utils.GameSide

plugins {
    java
    val dgtVersion = "1.10.4"
    id("org.jetbrains.kotlin.jvm") version("1.6.10")
    id("xyz.deftu.gradle.tools") version(dgtVersion)
    id("xyz.deftu.gradle.tools.shadow") version(dgtVersion)
    id("xyz.deftu.gradle.tools.blossom") version(dgtVersion)
    id("xyz.deftu.gradle.tools.minecraft.loom") version(dgtVersion)
    id("xyz.deftu.gradle.tools.minecraft.releases") version(dgtVersion)
}

deftu.useDevAuth()

repositories {
    maven("https://repo.polyfrost.cc/releases")
}

loomHelper {
    useForgeMixin(modData.id)
    useMixinRefMap(modData.id)
    useProperty("mixin.debug.export", "true", GameSide.CLIENT)
    useTweaker("cc.polyfrost.oneconfig.loader.stage0.LaunchWrapperTweaker")
    disableRunConfigs(GameSide.SERVER)
}

dependencies {
    modCompileOnly("cc.polyfrost:oneconfig-${mcData.versionStr}-${mcData.loader.name}:0.2.0-alpha+")
    implementation(shade("cc.polyfrost:oneconfig-wrapper-launchwrapper:1.0.0-beta+")!!)

    compileOnly("org.spongepowered:mixin:0.8.5-SNAPSHOT")
}