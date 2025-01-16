package com.phoniler.kinggoring.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val IconMenu =
    ImageVector
        .Builder(
            name = "Filled.Menu",
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
                moveTo(160f, 720f)
                quadToRelative(-17f, 0f, -28.5f, -11.5f)
                reflectiveQuadTo(120f, 680f)
                quadToRelative(0f, -17f, 11.5f, -28.5f)
                reflectiveQuadTo(160f, 640f)
                horizontalLineToRelative(640f)
                quadToRelative(17f, 0f, 28.5f, 11.5f)
                reflectiveQuadTo(840f, 680f)
                quadToRelative(0f, 17f, -11.5f, 28.5f)
                reflectiveQuadTo(800f, 720f)
                horizontalLineTo(160f)
                close()
                moveToRelative(0f, -200f)
                quadToRelative(-17f, 0f, -28.5f, -11.5f)
                reflectiveQuadTo(120f, 480f)
                quadToRelative(0f, -17f, 11.5f, -28.5f)
                reflectiveQuadTo(160f, 440f)
                horizontalLineToRelative(640f)
                quadToRelative(17f, 0f, 28.5f, 11.5f)
                reflectiveQuadTo(840f, 480f)
                quadToRelative(0f, 17f, -11.5f, 28.5f)
                reflectiveQuadTo(800f, 520f)
                horizontalLineTo(160f)
                close()
                moveToRelative(0f, -200f)
                quadToRelative(-17f, 0f, -28.5f, -11.5f)
                reflectiveQuadTo(120f, 280f)
                quadToRelative(0f, -17f, 11.5f, -28.5f)
                reflectiveQuadTo(160f, 240f)
                horizontalLineToRelative(640f)
                quadToRelative(17f, 0f, 28.5f, 11.5f)
                reflectiveQuadTo(840f, 280f)
                quadToRelative(0f, 17f, -11.5f, 28.5f)
                reflectiveQuadTo(800f, 320f)
                horizontalLineTo(160f)
                close()
            }
        }.build()
