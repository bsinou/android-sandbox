# Android Sandbox

Simply test latest feature of the Android framework with target version 31.


## How To

In order to build and use one of the project in this repository, you must

- Have Android Studio
- Have a Java JDK > 1.8
- Copy `local.properties.sample` file to `local.properties` and adapt to your setup in the target sub project you want to launch

## Developer Hints

### Various Links

Below are some links to internet resources that have prove useful while doing first hops in the wonderful Android world:

#### Relevant training and sample

These links seem to be kept up-to-date with latest versions of the libraries and are good places to stop once you are more familiar with the framework, a.k.a after the various bootcamps, as a reference for real life use:

- [Kotlin coroutines in Android Apps](https://developer.android.com/codelabs/kotlin-coroutines)
- [Updated sample for LiveData with ViewModel](https://developer.android.com/codelabs/basic-android-kotlin-training-livedata)
- [About testing](https://developer.android.com/training/testing)

#### From android courses

While following Android's bootcamp, these links seemed worth:

##### Layouts

- [About re-usable layouts](https://developer.android.com/training/improving-layouts/reusing-layouts), sepcifically about `<include />` and `<merge />` tags.


##### Lifecycles

- [The Android Lifecycle cheat sheet - part I](https://medium.com/androiddevelopers/the-android-lifecycle-cheat-sheet-part-i-single-activities-e49fd3d202ab): Single Activity - This is a visual recap of much of the material here.
- [The Android Lifecycle cheat sheet - part II](https://medium.com/androiddevelopers/the-android-lifecycle-cheat-sheet-part-ii-multiple-activities-a411fd139f24): Multiple Activities - This shows the order of lifecycle calls when two activities interact.
- [The Android Lifecycle cheat sheet - part III](https://medium.com/androiddevelopers/the-android-lifecycle-cheat-sheet-part-iii-fragments-afc87d4f37fd): Fragments - This show the order of lifecycle calls when an activity and fragment interact.


##### Architecture

- [Basic samples based on a TODO app](https://github.com/android/architecture-samples)

##### Room

- A good post that [recap state of the art (as of dec. 2021) management of view model](https://proandroiddev.com/optimizing-viewmodel-with-lifecycle-2-2-0-a2895b5c01fd) using the `by viewModels()` pattern.
- [About migration with Room](https://developer.android.com/training/data-storage/room/migrating-db-versions)
- On Medium, [Understanding migration with Room](https://medium.com/androiddevelopers/understanding-migrations-with-room-f01e04b07929)
- Also on Medium, [Testing migrations with Room](https://medium.com/androiddevelopers/testing-room-migrations-be93cdb0d975)


#### Misceleneous 

Some Gotchas in kotlin:

- [Kotlin ‘By’ Property Delegation: Create Reusable Code](https://medium.com/rocket-fuel/kotlin-by-property-delegation-create-reusable-code-f2bc2253e227)

- We try to follow [some best practices](https://github.com/futurice/android-best-practices)
- [Browse SQLite database in Android Studio](https://medium.com/@mattyskala/browse-sqlite-database-in-android-studio-4fbba6cca105)
- [About submodules](https://www.vogella.com/tutorials/GitSubmodules/article.html) and [Android Logging](https://www.vogella.com/tutorials/AndroidLogging/article.html) by the famous Vogella.


