package com.phoniler.kinggoring.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val IconPerson =
    ImageVector
        .Builder(
            name = "Filled.Person",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f,
        ).apply {
            path(
                fill = SolidColor(Color(0xFFE8EAED)),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero,
            ) {
                moveTo(480f, 480f)
                quadToRelative(-66f, 0f, -113f, -47f)
                reflectiveQuadToRelative(-47f, -113f)
                quadToRelative(0f, -66f, 47f, -113f)
                reflectiveQuadToRelative(113f, -47f)
                quadToRelative(66f, 0f, 113f, 47f)
                reflectiveQuadToRelative(47f, 113f)
                quadToRelative(0f, 66f, -47f, 113f)
                reflectiveQuadToRelative(-113f, 47f)
                close()
                moveTo(160f, 720f)
                verticalLineToRelative(-32f)
                quadToRelative(0f, -34f, 17.5f, -62.5f)
                reflectiveQuadTo(224f, 582f)
                quadToRelative(62f, -31f, 126f, -46.5f)
                reflectiveQuadTo(480f, 520f)
                quadToRelative(66f, 0f, 130f, 15.5f)
                reflectiveQuadTo(736f, 582f)
                quadToRelative(29f, 15f, 46.5f, 43.5f)
                reflectiveQuadTo(800f, 688f)
                verticalLineToRelative(32f)
                quadToRelative(0f, 33f, -23.5f, 56.5f)
                reflectiveQuadTo(720f, 800f)
                horizontalLineTo(240f)
                quadToRelative(-33f, 0f, -56.5f, -23.5f)
                reflectiveQuadTo(160f, 720f)
                close()
            }
        }.build()
