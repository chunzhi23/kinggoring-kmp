package com.phoniler.kinggoring.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val IconHome =
    ImageVector
        .Builder(
            name = "Filled.Home",
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
                moveTo(160f, 760f)
                verticalLineToRelative(-360f)
                quadToRelative(0f, -19f, 8.5f, -36f)
                reflectiveQuadToRelative(23.5f, -28f)
                lineToRelative(240f, -180f)
                quadToRelative(21f, -16f, 48f, -16f)
                reflectiveQuadToRelative(48f, 16f)
                lineToRelative(240f, 180f)
                quadToRelative(15f, 11f, 23.5f, 28f)
                reflectiveQuadToRelative(8.5f, 36f)
                verticalLineToRelative(360f)
                quadToRelative(0f, 33f, -23.5f, 56.5f)
                reflectiveQuadTo(720f, 840f)
                horizontalLineTo(600f)
                quadToRelative(-17f, 0f, -28.5f, -11.5f)
                reflectiveQuadTo(560f, 800f)
                verticalLineToRelative(-200f)
                quadToRelative(0f, -17f, -11.5f, -28.5f)
                reflectiveQuadTo(520f, 560f)
                horizontalLineToRelative(-80f)
                quadToRelative(-17f, 0f, -28.5f, 11.5f)
                reflectiveQuadTo(400f, 600f)
                verticalLineToRelative(200f)
                quadToRelative(0f, 17f, -11.5f, 28.5f)
                reflectiveQuadTo(360f, 840f)
                horizontalLineTo(240f)
                quadToRelative(-33f, 0f, -56.5f, -23.5f)
                reflectiveQuadTo(160f, 760f)
                close()
            }
        }.build()
