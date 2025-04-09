package com.doinglab.sangsik.utils

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class UtilsStringTest {
    @Test
    internal fun email_check_success() =
        assertTrue("doinglab@doinglab.com".checkEmail())

    @Test
    internal fun email_check_fail() =
        assertFalse("doinglab@doinglab".checkEmail())
}
