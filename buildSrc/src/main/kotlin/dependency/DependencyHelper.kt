package dependency

import org.gradle.api.artifacts.dsl.DependencyHandler

/**
 * A helper class(a bridge) for default Gradle dependency management to make it easy for managing
 * the project dependencies.
 *
 * This helper class made to provide three main benefits:
 *  1- Increase dependency management readability and maintenance
 *  2- Remove the unwanted or typo mistakes
 *  3- Easy to change the injection scope of one or a group of dependencies
 *
 *  @param[dependencyHandler] Gradle dependency helper [org.gradle.api.artifacts.dsl.DependencyHandler]
 *  @param[injectScope] Injection Scope [InjectScope]
 *
 *  @author Mehdi Janbarari
 */
abstract class DependencyHelper(
    private var dependencyHandler: DependencyHandler,
    private val injectScope: InjectScope
) {

    enum class InjectScope {
        Local, Global
    }

    fun add(dependencyNotation: Any) {
        if (injectScope.ordinal == InjectScope.Local.ordinal) {
            dependencyHandler.add("api", dependencyNotation)
            return
        }
        dependencyHandler.add("implementation", dependencyNotation)
    }

    fun kapt(dependencyNotation: Any) {
        dependencyHandler.add("kapt", dependencyNotation)
    }

    fun test(dependencyNotation: Any) {
        dependencyHandler.add("testImplementation", dependencyNotation)
    }

    fun androidTest(dependencyNotation: Any) {
        dependencyHandler.add("androidTestImplementation", dependencyNotation)
    }

    fun debug(dependencyNotation: Any) {
        dependencyHandler.add("debugImplementation", dependencyNotation)
    }

    fun kaptAndroidTest(dependencyNotation: Any) {
        dependencyHandler.add("kaptAndroidTest", dependencyNotation)
    }

    fun inject(dependencyNotation: Any) {
        add(dependencyNotation)
    }

}

private fun getGlobalInstance(dh: DependencyHandler): Dependencies {
    return Dependencies(dh, DependencyHelper.InjectScope.Global)
}

private fun getLocalInstance(dh: DependencyHandler): Dependencies {
    return Dependencies(dh, DependencyHelper.InjectScope.Local)
}

val DependencyHandler.global: Dependencies
    get() = getGlobalInstance(this)

val DependencyHandler.local: Dependencies
    get() = getLocalInstance(this)

inline fun DependencyHandler.local(block: Dependencies.() -> Unit): Dependencies {
    return local.apply { block() }
}

inline fun DependencyHandler.global(block: Dependencies.() -> Unit): Dependencies {
    return global.apply { block() }
}