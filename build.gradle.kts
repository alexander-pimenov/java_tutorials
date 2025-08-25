plugins {
    java
}

group = "ru.arhiser"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    sourceSets {
        main {
            java {
                setSrcDirs(listOf("src"))
            }
        }
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}


dependencies {

    // https://mvnrepository.com/artifact/io.reactivex.rxjava3/rxjava
    implementation("io.reactivex.rxjava3:rxjava:3.1.11")

    // Зависимости для тестов, если нужны
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
}

tasks.test {
    useJUnitPlatform()
}
