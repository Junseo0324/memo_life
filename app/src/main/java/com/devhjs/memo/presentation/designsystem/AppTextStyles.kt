package com.devhjs.memo.presentation.designsystem
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object AppTextStyles {

    object Pretendard {
        val Header1 = TextStyle(
            fontFamily = AppFonts.pretendard,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            lineHeight = 36.sp
        )
        val Header2 = TextStyle(
            fontFamily = AppFonts.pretendard,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            lineHeight = 30.sp
        )
        val Header3 = TextStyle(
            fontFamily = AppFonts.pretendard,
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            lineHeight = 27.sp
        )
        val Header4 = TextStyle(
            fontFamily = AppFonts.pretendard,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp
        )
        val Header5 = TextStyle(
            fontFamily = AppFonts.pretendard,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 20.sp
        )
        val Body = TextStyle(
            fontFamily = AppFonts.pretendard,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp
        )
        val Label = TextStyle(
            fontFamily = AppFonts.pretendard,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp
        )
    }

}