package com.github.neboskreb.instant.task.executor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import androidx.lifecycle.MutableLiveData;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


@ExtendWith(InstantExecutorExtension.class)
public class JavaInstantExecutorExtensionTest {

    @Test
    @DisplayName("Works also with Java tests")
    void test() {
        // GIVEN
        MutableLiveData<String> mutableLiveData = new MutableLiveData<>("foo");

        // WHEN
        mutableLiveData.postValue("bar");

        // THEN
        assertEquals("bar", mutableLiveData.getValue());
    }
}
