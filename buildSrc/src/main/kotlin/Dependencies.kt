

// Support Dependencies for kotlin and UI

val kotlinVersion = "1.12.0"
val lifecycleVersion = "2.7.5"
val espressoVersion = "3.5.1"
val lifecycleRuntimeVersion = "2.6.2"
val composeVersion = "1.8.1"
val appUpdateVersion = "2.1.0"





public val supportDependencies = hashMapOf(
    "coreKtx" to "androidx.core:core-ktx:$kotlinVersion",
    "lifecycle" to "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleRuntimeVersion",
    "compose" to "androidx.activity:activity-compose:$composeVersion",
    "compose-ui" to "androidx.compose.ui:ui",
    "compose-ui-graphics" to "androidx.compose.ui:ui-graphics",
    "compose-ui-tooling-preview" to "androidx.compose.ui:ui-tooling-preview",
    "compose-material3" to "androidx.compose.material3:material3",
    "navigationFragment" to "androidx.navigation:navigation-fragment-ktx:$lifecycleVersion",
    "navigationUi" to "androidx.navigation:navigation-ui-ktx:$lifecycleVersion",
    "espressoIdlingResource" to "androidx.test.espresso:espresso-idling-resource:$espressoVersion",
    "app-update" to "com.google.android.play:app-update:$appUpdateVersion",
    "app-update-ktx" to "com.google.android.play:app-update-ktx:$appUpdateVersion",

    )

// -----------------------------------------UI Dependencies-------------------------------------------------------


val glideVersion = "4.16.0"
val shimmerVersion = "0.5.0"
val splashScreenVersion = "1.0.1"
val nav_version = "2.7.5"
val systemuicontrollerVersion = "0.27.0"
val zxingVersion = "3.3.0"
val cameraxVersion = "1.3.0"
val snackbarVersion = "V2.0.0"
val iTextVersion = "8.0.2"
val coilVersion = "2.5.0"

val uiDependencies = hashMapOf(
    "shimmer" to "com.facebook.shimmer:shimmer:$shimmerVersion",
    "navigation-compose" to "androidx.navigation:navigation-compose:$nav_version",
    "splashscreen" to "androidx.core:core-splashscreen:$splashScreenVersion",
    "systemuicontroller" to "com.google.accompanist:accompanist-systemuicontroller:$systemuicontrollerVersion",
    "zxing" to "com.google.zxing:core:$zxingVersion",
    "camera2" to "androidx.camera:camera-camera2:$cameraxVersion",
    "cameraLifeCycle" to "androidx.camera:camera-lifecycle:$cameraxVersion",
    "cameraView" to "androidx.camera:camera-view:$cameraxVersion",
    "TSnackBar" to "com.github.Redman1037:TSnackBar:$snackbarVersion",
    "iText" to "com.itextpdf:itext7-core:$iTextVersion",
    "coil" to "io.coil-kt:coil-compose:$coilVersion"
)



// -----------------------------------------Dependencies for local unit tests-------------------------------------------------------


val junitVersion = "4.13.2"
val hamcrestVersion = "1.3"
val coroutinesTestVersion = "1.7.3"
val junit4AndroidVersion = "1.1.5"
val robolectricVersion = "4.8.1"
val jupiterVersion = "5.8.2"
val coreKtx = "1.5.0"
val androidXCoreKtxTestVersion = "1.5.0"
val androidXRulesTestVersion = "1.5.0"
val androidXArchCoreTest = "2.1.0"
val androidXJunit = "1.1.4"
val sharedPreferencesMockVersion = "1.2.4"

val supportDependenciesTest = hashMapOf(
    "junit4" to "junit:junit:$junitVersion",
    "hamcrest" to "org.hamcrest:hamcrest-all:$hamcrestVersion",
    "coroutinesTest" to "org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesTestVersion",
    "robolectric" to "org.robolectric:robolectric:$robolectricVersion",
    "coroutinesAndroidTest" to "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesTestVersion",
    "jupiterApi" to "org.junit.jupiter:junit-jupiter-api:$jupiterVersion",
    "jupiterParams" to "org.junit.jupiter:junit-jupiter-params:$jupiterVersion",
    "androiXCoreKTX" to "androidx.test:core-ktx:$androidXCoreKtxTestVersion",
    "androiXJunit" to "androidx.test.ext:junit:$androidXJunit",
    "androidXRules" to "androidx.test:rules:$androidXRulesTestVersion",
    "androidXArch" to "androidx.arch.core:core-testing:$androidXArchCoreTest",
    "" to "io.github.ivanshafran:shared-preferences-mock:$sharedPreferencesMockVersion"

)


val supportDependenciesTestRuntime = hashMapOf(
    "jupiterEngine" to "org.junit.jupiter:junit-jupiter-engine:$jupiterVersion"
)


// -----------------------------------------Dependencies for Android Test-------------------------------------------------------


val androidXTestCoreVersion = "1.5.0"
val androidXTestExtKotlinRunnerVersion = "1.1.5"
val coroutinesAndroidTestVersion = "1.7.3"
val androidXTestRulesVersion = "1.5.0"
val roomTestVersion = "2.6.0"
val archTestingVersion = "2.2.0"
val fragmentVersion = "1.6.2"
val mockitoVersion = "4.8.0"
val dexMakerVersion = "2.28.1"
val koinTestVerion = "3.4.0"
val uiAutomatorVersion = "2.2.0"
val mockwebserverVersion = "4.10.0"


val supportDependenciesAndroidTest = hashMapOf(
    "junit_4" to "junit:junit:$junitVersion",
    "androidXTestCore" to "androidx.test:core-ktx:$androidXTestCoreVersion",
    "androidXTestExtKotlinRunner" to "androidx.test.ext:junit-ktx:$androidXTestExtKotlinRunnerVersion",
    "coroutinesTest" to "org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesAndroidTestVersion",
    "androidXTestRules" to "androidx.test:rules:$androidXTestRulesVersion",
    "roomTest" to "androidx.room:room-testing:$roomTestVersion",
    "archTesting" to "androidx.arch.core:core-testing:$archTestingVersion",
    "robolectric" to "org.robolectric:annotations:$robolectricVersion",
    "espressoCore" to "androidx.test.espresso:espresso-core:$espressoVersion",
    "espressoContrib" to "androidx.test.espresso:espresso-contrib:$espressoVersion",
    "espressoIntents" to "androidx.test.espresso:espresso-intents:$espressoVersion",
    "espressoIdling" to "androidx.test.espresso.idling:idling-concurrent:$espressoVersion",
    "mockitoCore" to "org.mockito:mockito-core:$mockitoVersion",
    "dexmakerMockito" to "com.linkedin.dexmaker:dexmaker-mockito:$dexMakerVersion",
    "koinTest" to "io.insert-koin:koin-android-test:$koinTestVerion",
    "uiautomator" to "androidx.test.uiautomator:uiautomator:$uiAutomatorVersion",
    "mockwebserver" to "com.squareup.okhttp3:mockwebserver:$mockwebserverVersion",
    "compose-junit" to "androidx.compose.ui:ui-test-junit4"
)



val supportDependenciesDebugAndroidTest = hashMapOf(
    "fragmentTesting" to "androidx.fragment:fragment-testing:$fragmentVersion",
    "ui-tooling" to "androidx.compose.ui:ui-tooling",
    "ui-test-manifest" to "androidx.compose.ui:ui-test-manifest"
)


// -----------------------------------------Dependencies for Network Client-------------------------------------------------------


val retrofitVersion = "2.9.0"
val okhttpVersion = "4.10.0"
val loggingInterceptorVersion = "4.9.3"
val prdownloaderVersion = "1.0.4"
val moshiVersion = "2.4.0"


val networkClientDependencies = hashMapOf(
    "okkhttp" to "com.squareup.okhttp3:okhttp:$okhttpVersion",
    "logging_interceptor" to "com.squareup.okhttp3:logging-interceptor:$loggingInterceptorVersion",
    "gson" to "com.google.code.gson:gson:$retrofitVersion",
    "retrofit" to "com.squareup.retrofit2:retrofit:$retrofitVersion",
    "retrofit_gson" to "com.squareup.retrofit2:converter-gson:$retrofitVersion",
    "prdownloader" to "com.github.varungulatii:Kdownloader:$prdownloaderVersion",
    "moshi" to "com.squareup.retrofit2:converter-moshi:$moshiVersion"
)

// -----------------------------------------Dependencies for Dependency Injection-------------------------------------------------------

val koinVersion = "3.4.0"
val eventBusVersion = "3.3.1"


val dependencyInjectionDependencies = hashMapOf(

    "koinAndroid" to "io.insert-koin:koin-androidx-compose:$koinVersion",
    "event_bus" to "org.greenrobot:eventbus:$eventBusVersion"
)


// -----------------------------------------Dependencies for Database-------------------------------------------------------


// Database
val roomVersion = "2.6.0"

val databaseDependencies = hashMapOf(
    "room" to "androidx.room:room-runtime:$roomVersion",
    "room_ktx" to "androidx.room:room-ktx:$roomVersion"
)

val databaseAnnotationDependencies = hashMapOf(
    "room" to "androidx.room:room-compiler:$roomVersion"
)


// -----------------------------------------Dependencies for AppCenter-------------------------------------------------------



val appCenterSdkVersion = "4.4.5"

val appCenterDependencies = hashMapOf(
    "analytics" to "com.microsoft.appcenter:appcenter-analytics:${appCenterSdkVersion}",
    "crashes" to "com.microsoft.appcenter:appcenter-crashes:${appCenterSdkVersion}",
)



// -----------------------------------------Dependencies for Firebase-------------------------------------------------------


val crashlyticsVersion = "18.6.0"
val analyticsVersion = "21.5.0"
val messagingVersion = "23.3.1"

val firebaseDependencies = hashMapOf(
    "crashlytics" to "com.google.firebase:firebase-crashlytics-ktx:$crashlyticsVersion",
    "analytics" to "com.google.firebase:firebase-analytics-ktx:$analyticsVersion",
    "messaging" to "com.google.firebase:firebase-messaging:$messagingVersion",
)
