# Make sure to check everything always, can't hurt.

-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers
-forceprocessing

# TODO Adapt any changed strings

#-adaptclassstrings
#-adaptresourcefilenames
#-adaptresourcefilecontents
#-adaptresourcefilecontents **.MF

-keep public class com.github.NeRdTheNed.Punch2Prime.TntBlockMixin

-keepclassmembers,allowoptimization,allowobfuscation public class com.github.NeRdTheNed.Punch2Prime.TntBlockMixin {
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
