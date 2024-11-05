import io.izzel.taboolib.gradle.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    id("io.izzel.taboolib") version "2.0.19"
    id("org.jetbrains.kotlin.jvm") version "1.8.22"
}

subprojects {
    apply<JavaPlugin>()
    apply(plugin = "io.izzel.taboolib")
    apply(plugin = "org.jetbrains.kotlin.jvm")

    taboolib {
        env {
            install(Basic, Database, Kether, Metrics, XSeries, CommandHelper)
            install(Bukkit, BukkitUI, BukkitNavigation, BukkitUtil, BukkitHook)
            install(BukkitNMSUtil)
            install(MinecraftEffect, MinecraftChat)
        }
        version { taboolib = "6.2.0-beta33" }
    }
    repositories {
        maven { url = uri("https://repo.spongepowered.org/maven") }
        mavenCentral()
    }
    dependencies {
        compileOnly(kotlin("stdlib"))
        // server
        compileOnly("ink.ptms.core:v11604:11604")
        compileOnly("ink.ptms:nms-all:1.0.0")
        compileOnly("com.google.code.gson:gson:2.8.5")
        compileOnly("com.google.guava:guava:21.0")
        // include
        compileOnly("com.eatthepath:fast-uuid:0.2.0")
        compileOnly("org.spongepowered:math:2.0.1")
        // download
        compileOnly("com.github.ben-manes.caffeine:caffeine:2.9.3")
    }
    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
    }
    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs = listOf("-Xjvm-default=all", "-Xextended-compiler-checks")
        }
    }
    configure<JavaPluginConvention> {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    // 新增内容
    tasks.jar {
        archiveBaseName.set("adyeshach")
        destinationDirectory.set(file("$buildDir/output/libs"))
    }
}

gradle.buildFinished {
    buildDir.deleteRecursively()
}
