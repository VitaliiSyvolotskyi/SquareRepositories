package com.andersenlab.poq.core.test

import androidx.annotation.CallSuper
import io.mockk.MockKAnnotations
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Before

open class BaseTest {
    @Before
    @CallSuper
    open fun setUp() {
        MockKAnnotations.init(this)
    }

    @After
    open fun finish() {
        unmockkAll()
    }
}