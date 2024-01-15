// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.1" apply false
    id ("org.jetbrains.kotlin.android") version("1.9.0") apply false

    /****************** Dagger Hilt *************************************/
    id("com.google.dagger.hilt.android") version "2.47" apply false
}