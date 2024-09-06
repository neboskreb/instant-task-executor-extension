package com.github.neboskreb.instant.task.executor

import androidx.lifecycle.MutableLiveData
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows


class NoInstantExecutorExtensionTest {

    @Test
    fun `postValue throws exception when no extension applied`() {
        // GIVEN
        val mutableLiveData = MutableLiveData("foo")

        // WHEN
        val exception = assertThrows<Exception> {
            mutableLiveData.postValue("bar")
        }

        // THEN
        assertEquals("Method getMainLooper in android.os.Looper not mocked. See https://developer.android.com/r/studio-ui/build/not-mocked for details.", exception.message)
    }
}
