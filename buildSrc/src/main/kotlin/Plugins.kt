import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

private typealias PDsS = PluginDependenciesSpec
private typealias PDS = PluginDependencySpec

inline val PDsS.androidApplication: PDS get() = id("com.android.application")
inline val PDsS.androidLib: PDS get() = id("com.android.library")
inline val PDsS.kotlinAndroid: PDS get() = id("kotlin-android")
inline val PDsS.kotlinExtension: PDS get() = id("kotlin-android-extensions")
inline val PDsS.kotlin: PDS get() = id("kotlin")
inline val PDsS.kotlinKapt: PDS get() = id("kotlin-kapt")
inline val PDsS.hilt: PDS get() = id("dagger.hilt.android.plugin")
inline val PDsS.navigationComponent: PDS get() = id("androidx.navigation.safeargs.kotlin")
inline val PDsS.kotlinParcelize: PDS get() = id("kotlin-parcelize")
inline val PDsS.gradleBuildTimeTracker: PDS get() = id("com.github.janbarari.gradlebuildtimetracker").version("1.0.1")