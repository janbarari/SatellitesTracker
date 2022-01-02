package dependency

import org.gradle.api.artifacts.dsl.DependencyHandler

class Dependencies(
    dependencyHandler: DependencyHandler,
    injectScope: InjectScope
) : DependencyHelper(dependencyHandler, injectScope) {

    fun Coroutines() {
        add("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
        add("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
    }

    fun Room() {
        val v = "2.4.0"
        add("androidx.room:room-runtime:$v")
        kapt("androidx.room:room-compiler:$v")
    }

    fun ConstraintLayout() {
        add("androidx.constraintlayout:constraintlayout:2.1.2")
    }

    fun Junit() {
        test("junit:junit:4.13.2")
        androidTest("androidx.test.ext:junit:1.1.3")
    }

    fun Espresso() {
        androidTest("androidx.test.espresso:espresso-core:3.4.0")
    }

    fun MaterialDesign() {
        add("com.google.android.material:material:1.4.0")
    }

    fun Appcompat() {
        add("androidx.core:core-ktx:1.7.0")
        add("androidx.appcompat:appcompat:1.4.0")
    }

    fun NavigationComponent() {
        add("androidx.navigation:navigation-fragment-ktx:2.3.5")
        add("androidx.navigation:navigation-ui-ktx:2.3.5")
    }

    fun Hilt() {
        add("com.google.dagger:hilt-android:2.40.3")
        kapt("com.google.dagger:hilt-android-compiler:2.40.3")
    }

    fun Moshi() {
        add("com.squareup.moshi:moshi-kotlin:1.13.0")
    }

}