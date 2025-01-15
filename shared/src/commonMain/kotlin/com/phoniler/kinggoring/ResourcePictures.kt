package com.phoniler.kinggoring

import com.phoniler.kinggoring.model.GpsPosition
import com.phoniler.kinggoring.model.PictureData

val resourcePictures =
    arrayOf(
        PictureData.Resource(
            resource = "files/1.jpg",
            thumbnailResource = "files/1-thumbnail.jpg",
            name = "2025/1/15-Breakfast",
            description = "Breakfast on 2025. 1. 15.".trimIndent(),
            dateString = "January 15, 2025",
            gps = GpsPosition(35.8825, 76.513333),
        ),
    )
