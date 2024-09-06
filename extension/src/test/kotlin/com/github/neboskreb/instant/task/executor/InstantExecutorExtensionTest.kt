package com.github.neboskreb.instant.task.executor

import androidx.lifecycle.MutableLiveData
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith


@ExtendWith(InstantExecutorExtension::class)
class InstantExecutorExtensionTest {

    @Test
    fun `Extension works`() {
        // GIVEN
        val mutableLiveData = MutableLiveData("foo")

        // WHEN
        mutableLiveData.postValue("bar")

        // THEN
        assertEquals("bar", mutableLiveData.value)
    }
}
