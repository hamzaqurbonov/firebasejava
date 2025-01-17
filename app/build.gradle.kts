plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.firebasejava"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.firebasejava"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }


    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    packagingOptions {
        exclude ("META-INF/DEPENDENCIES")
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
//    implementation("androidx.activity:activity:1.8.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("com.google.firebase:firebase-database:20.2.2")
    // FirestoreRecyclerOptions
    implementation ("com.google.firebase:firebase-firestore:17.0.4")
    implementation ("com.google.firebase:firebase-core:21.1.1")
    implementation ("com.firebaseui:firebase-ui-firestore:7.1.1")
//
    implementation ("com.github.bumptech.glide:glide:4.16.0")

    implementation ("com.pierfrancescosoffritti.androidyoutubeplayer:core:12.1.1")
    implementation ("com.pierfrancescosoffritti.androidyoutubeplayer:chromecast-sender:0.30")

//    implementation ("com.pierfrancescosoffritti.android-youtube-player:core:10.0.5")

    implementation ("com.google.api-client:google-api-client:1.34.0")
    implementation ("com.google.http-client:google-http-client-gson:1.41.0")
    implementation ("com.google.apis:google-api-services-youtube:v3-rev222-1.25.0")
    implementation ("com.google.http-client:google-http-client-jackson2:1.42.3")
//    implementation(platform("com.google.firebase:firebase-bom:32.3.1"))
//    implementation("com.google.firebase:firebase-analytics")
//    implementation ("com.pierfrancescosoffritti.androidyoutubeplayer:core:12.1.0")
//    implementation("de.hdodenhof:circleimageview:3.1.0")
//    implementation ("com.etebarian:meow-bottom-navigation:1.2.0")

}