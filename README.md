# instant-task-executor-extension
Support for `InstantTaskExecutorRule` for JUnit 5.

# Background

As Google does not have plans ([issue 127100532](https://issuetracker.google.com/issues/127100532), [issue 224](https://github.com/android/android-test/issues/224))
to support JUnit 5 in near future, a 3rd party solution was offered to fill the gap: [android-junit5](https://github.com/mannodermaus/android-junit5/tree/main?tab=readme-ov-file).

It works very well. The only thing missing is the `InstantTaskExecutorRule` to allow testing of LiveData and ViewModels 
in JUnit tests.     

# The problem
Android provides a rule for testing the LiveData mechanism in isolation:
```kotlin
class ExampleUnitTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun `my test`() {
        // GIVEN
        val mutableLiveData = MutableLiveData<String>("bar")

        // WHEN
        mutableLiveData.postValue("test")

        // THEN
        assertEquals("test", mutableLiveData.value)
    }
}
```
The problem is that `@Rule` is not supported by JUnit 5. 

Without the rule applied, your LiveData will try to actually post the update using the main thread's `Looper` - which 
obviously fails in the isolated environment of JUnit test.

# The solution
Use `InstantExecutorExtension` to get the same functionality in JUnit 5:

```kotlin
@ExtendWith(InstantExecutorExtension::class)
class ExampleUnitTest {

    @Test
    fun `my test`() {
        // GIVEN
        val mutableLiveData = MutableLiveData<String>("bar")

        // WHEN
        mutableLiveData.postValue("test")

        // THEN
        assertEquals("test", mutableLiveData.value)
    }
}
```

Many sources ([1](https://github.com/mannodermaus/android-junit5/issues/66), [2](https://jeroenmols.com/blog/2019/01/17/livedatajunit5/), 
[3](https://gist.github.com/william-reed/4b21a1cc4f85dde20806625b4c8fef84), [4](https://gist.github.com/alvindizon/227735c2e21763368cc248912476cf0f),
 etc.) suggested creating a small extension class, which works really well. This library is, essentially, that very same class, only
packaged so that you can include it in your Gradle script. 

# Setup
_For instructions how to enable JUnit 5 in your Android project in the first place, please refer to [android-junit5](https://github.com/mannodermaus/android-junit5) plugin._

In your `build.gradle` add this dependency:
```groovy
dependencies {
    testImplementation 'io.github.neboskreb:instant-task-executor-extension:1.0.0'
}
```


# Examples

See submodule [examples](https://github.com/neboskreb/instant-task-executor-extension/blob/master/examples)

# Contributions
Though it's hard to believe such a simple library can have any further development, pull requests are welcome! If you plan to open one, please first create an issue where you describe the problem/gap your contribution closes, and tag the keeper(s) of this repo so they could get back to you with help.

# License

```text
Copyright 2024 John Y. Pazekha

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
See also the [full License text](https://github.com/neboskreb/instant-task-executor-extension/blob/master/LICENSE).