package dependency

import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

class Dependencies(
    val dependencyHandler: DependencyHandler,
    injectScope: InjectScope
) : DependencyHelper(dependencyHandler, injectScope) {

    fun Coroutines(isTestEnabled: Boolean = true) {
        add("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
        add("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
        if (isTestEnabled) {
            test("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0")
        }
    }

    fun Room() {
        val v = "2.4.0"
        add("androidx.room:room-runtime:$v")
        add("androidx.room:room-ktx:$v")
        kapt("androidx.room:room-compiler:$v")
    }

    fun ConstraintLayout() {
        add("androidx.constraintlayout:constraintlayout:2.1.2")
    }

    fun Junit(isAndroidTestEnabled: Boolean = true) {
        test("junit:junit:4.13.2")
        if (isAndroidTestEnabled) {
            androidTest("androidx.test.ext:junit-ktx:1.1.3")
        }
    }

    fun Espresso() {
        androidTest("androidx.test.espresso:espresso-core:3.4.0")
        androidTest("androidx.test.espresso:espresso-contrib:3.4.0")
    }

    fun MaterialDesign() {
        add("com.google.android.material:material:1.4.0")
    }

    fun BaseAndroidx() {
        add("androidx.core:core-ktx:1.7.0")
        add("androidx.appcompat:appcompat:1.4.0")
        add("androidx.activity:activity-ktx:1.4.0")
        add("androidx.fragment:fragment-ktx:1.4.0")
        add("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")
    }

    fun NavigationComponent() {
        add("androidx.navigation:navigation-runtime-ktx:2.3.5")
        add("androidx.navigation:navigation-fragment-ktx:2.3.5")
        add("androidx.navigation:navigation-ui-ktx:2.3.5")
    }

    fun Hilt(isAndroidTestEnabled: Boolean = true) {
        add("com.google.dagger:hilt-android:2.40.3")
        kapt("com.google.dagger:hilt-android-compiler:2.40.3")
        if (isAndroidTestEnabled) {
            androidTest("com.google.dagger:hilt-android-testing:2.40.3")
            kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.40.3")
        }
    }

    fun RecyclerView() {
        add("androidx.recyclerview:recyclerview:1.2.1")
    }

    fun Moshi() {
        add("com.squareup.moshi:moshi-kotlin:1.13.0")
        kapt("com.squareup.moshi:moshi-kotlin-codegen:1.13.0")
    }

    fun Mockk() {
        test("io.mockk:mockk:1.11.0")
        androidTest("io.mockk:mockk-android:1.11.0")
    }

    fun Feature_List() {
        inject(dependencyHandler.project(":feature:list"))
    }

    fun Feature_Details() {
        inject(dependencyHandler.project(":feature:details"))
    }

    fun Core() {
        inject(dependencyHandler.project(":core"))
    }

    fun Data() {
        inject(dependencyHandler.project(":data"))
    }

    fun Domain() {
        inject(dependencyHandler.project(":domain"))
    }

}