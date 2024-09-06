package com.github.neboskreb.instant.task.executor

import android.annotation.SuppressLint
import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext

/**
 * _This is replacement for the official [androidx.arch.core.executor.testing.InstantTaskExecutorRule] rule used in JUnit 4, as rules are not supported in JUnit 5._
 *
 * A JUnit Test extension that swaps the background executor used by the Architecture Components with a different one which executes each task synchronously.
 *
 * You can use this extension for your host side tests that use Architecture Components.
 *
 * Usage:
 * ```
 * @ExtendWith(InstantExecutorExtension::class)
 * class MyImplementationTest {
 *    ...
 *```
 *
 */
class InstantExecutorExtension : BeforeEachCallback, AfterEachCallback {

    @SuppressLint("RestrictedApi")
    override fun beforeEach(context: ExtensionContext?) {
        ArchTaskExecutor.getInstance()
            .setDelegate(object : TaskExecutor() {
                override fun executeOnDiskIO(runnable: Runnable) = runnable.run()

                override fun postToMainThread(runnable: Runnable) = runnable.run()

                override fun isMainThread(): Boolean = true
            })
    }

    @SuppressLint("RestrictedApi")
    override fun afterEach(context: ExtensionContext?) {
        ArchTaskExecutor.getInstance().setDelegate(null)
    }

}
