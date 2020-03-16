# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# keep everything in this package from being removed or renamed
-keep class com.squareup.moshi.** { *; }

# keep everything in this package from being renamed only
-keepnames class com.squareup.moshi.** { *; }

# keep everything in this package from being removed or renamed
-keep class com.github.kittinunf.** { *; }

# keep everything in this package from being renamed only
-keepnames class com.github.kittinunf.** { *; }

# keep everything in this package from being removed or renamed
-keep class org.koin.** { *; }

# keep everything in this package from being renamed only
-keepnames class org.koin.** { *; }

# keep everything in this package from being removed or renamed
-keep class androidx.lifecycle.** { *; }

# keep everything in this package from being renamed only
-keepnames class androidx.lifecycle.** { *; }

# keep everything in this package from being removed or renamed
-keep class android.arch.** { *; }

# keep everything in this package from being renamed only
-keepnames class android.arch.** { *; }

#-keep class com.bimasaktitest.main.models.** { *; }

-keep class androidx.** { *; }

-printusage D:/usage.txt
-printseeds D:/seeds.txt