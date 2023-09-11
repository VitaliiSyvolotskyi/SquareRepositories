package com.andersenlab.poq.utils

import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert

/*
* Check that two objects are equal
* @param value - compared object
*/
infix fun <T> T?.isTheSame(value: T?) {
    MatcherAssert.assertThat(this, CoreMatchers.equalTo(value))
}