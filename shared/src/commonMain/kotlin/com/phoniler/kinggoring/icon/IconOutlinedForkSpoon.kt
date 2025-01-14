package com.phoniler.kinggoring.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val IconOutlinedForkSpoon =
    ImageVector
        .Builder(
            name = "Outlined.ForkSpoon",
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
                moveTo(240f, 880f)
                verticalLineToRelative(-366f)
                quadToRelative(-54f, -14f, -87f, -57f)
                reflectiveQuadToRelative(-33f, -97f)
                verticalLineToRelative(-280f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(240f)
                horizontalLineToRelative(40f)
                verticalLineToRelative(-240f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(240f)
                horizontalLineToRelative(40f)
                verticalLineToRelative(-240f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(280f)
                quadToRelative(0f, 54f, -33f, 97f)
                reflectiveQuadToRelative(-87f, 57f)
                verticalLineToRelative(366f)
                horizontalLineToRelative(-80f)
                close()
                moveToRelative(400f, 0f)
                verticalLineToRelative(-381f)
                quadToRelative(-54f, -18f, -87f, -75.5f)
                reflectiveQuadTo(520f, 293f)
                quadToRelative(0f, -89f, 47f, -151f)
                reflectiveQuadToRelative(113f, -62f)
                quadToRelative(66f, 0f, 113f, 62.5f)
                reflectiveQuadTo(840f, 294f)
                quadToRelative(0f, 73f, -33f, 130f)
                reflectiveQuadToRelative(-87f, 75f)
                verticalLineToRelative(381f)
                horizontalLineToRelative(-80f)
                close()
            }
        }.build()
