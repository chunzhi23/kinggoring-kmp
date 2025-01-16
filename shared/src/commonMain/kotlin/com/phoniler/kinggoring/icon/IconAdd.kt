package com.phoniler.kinggoring.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val IconAdd =
    ImageVector
        .Builder(
            name = "Filled.Add",
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
                moveTo(440f, 520f)
                horizontalLineTo(240f)
                quadToRelative(-17f, 0f, -28.5f, -11.5f)
                reflectiveQuadTo(200f, 480f)
                quadToRelative(0f, -17f, 11.5f, -28.5f)
                reflectiveQuadTo(240f, 440f)
                horizontalLineToRelative(200f)
                verticalLineToRelative(-200f)
                quadToRelative(0f, -17f, 11.5f, -28.5f)
                reflectiveQuadTo(480f, 200f)
                quadToRelative(17f, 0f, 28.5f, 11.5f)
                reflectiveQuadTo(520f, 240f)
                verticalLineToRelative(200f)
                horizontalLineToRelative(200f)
                quadToRelative(17f, 0f, 28.5f, 11.5f)
                reflectiveQuadTo(760f, 480f)
                quadToRelative(0f, 17f, -11.5f, 28.5f)
                reflectiveQuadTo(720f, 520f)
                horizontalLineTo(520f)
                verticalLineToRelative(200f)
                quadToRelative(0f, 17f, -11.5f, 28.5f)
                reflectiveQuadTo(480f, 760f)
                quadToRelative(-17f, 0f, -28.5f, -11.5f)
                reflectiveQuadTo(440f, 720f)
                verticalLineToRelative(-200f)
                close()
            }
        }.build()
