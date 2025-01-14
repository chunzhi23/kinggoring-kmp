package com.phoniler.kinggoring.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val IconOutlinedBarChart =
    ImageVector
        .Builder(
            name = "Outlined.BarChart",
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
                moveTo(640f, 780f)
                verticalLineToRelative(-236.15f)
                horizontalLineToRelative(140f)
                verticalLineTo(780f)
                horizontalLineTo(640f)
                close()
                moveToRelative(-230f, 0f)
                verticalLineToRelative(-600f)
                horizontalLineToRelative(140f)
                verticalLineToRelative(600f)
                horizontalLineTo(410f)
                close()
                moveToRelative(-230f, 0f)
                verticalLineToRelative(-403.84f)
                horizontalLineToRelative(140f)
                verticalLineTo(780f)
                horizontalLineTo(180f)
                close()
            }
        }.build()
