package com.andersenlab.poq.core.test.utils

import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert

/*
* Check that two objects are equal
* @param value - compared object
*/
infix fun <T> T?.isTheSame(value: T?) {
    MatcherAssert.assertThat(this, CoreMatchers.equalTo(value))
}

/*
* Check that two objects are not equal
* @param value - compared object
*/
infix fun <T, K : Any> T?.isNotTheSame(value: K?) {
    MatcherAssert.assertThat(this, CoreMatchers.not(CoreMatchers.equalTo(value)))
}