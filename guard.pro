# Make sure to check everything always, can't hurt.

-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers
-forceprocessing

# TODO Adapt any changed strings

#-adaptclassstrings
#-adaptresourcefilenames
#-adaptresourcefilecontents
#-adaptresourcefilecontents **.MF

# Keep all methods that overrides a super method, or methods that are an event handler.

-keepclassmembers,allowobfuscation class * {
	@java.lang.Override,net.minecraftforge.fml.common.Mod$EventHandler <methods>;
}

-keep public class com.github.NeRdTheNed.Punch2Prime.Mixin.TntBlockMixin
-keep public class com.github.NeRdTheNed.Punch2Prime.Punch2Prime
-keep public class com.github.NeRdTheNed.Punch2Prime.Connector

-keepclassmembers,allowoptimization,allowobfuscation public class com.github.NeRdTheNed.Punch2Prime.Mixin.TntBlockMixin {
	private void init(org.spongepowered.asm.mixin.injection.callback.CallbackInfo);
}

-keepattributes *Annotation*

# Needed to prevent ProGuard from changing a method signature
-optimizations !method/removal/parameter

# Debug ProGuard

-printusage
-whyareyoukeeping class mcTextureGen.**

# Bonus optimisations

-optimizationpasses 64
-allowaccessmodification
-mergeinterfacesaggressively
-overloadaggressively
