package com.doinglab.sangsik.enums

enum class Cache(val cacheName: String, val duration: Long) {
    S3_IMAGE("s3Image", 55), CGM_BLOODSUGAR("cgmBloodsugar", 5)
}
