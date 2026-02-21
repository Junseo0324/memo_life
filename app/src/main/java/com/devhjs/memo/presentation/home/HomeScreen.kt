package com.devhjs.memo.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devhjs.memo.presentation.component.HomeMenuCard
import com.devhjs.memo.presentation.designsystem.AppTextStyles

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onMenuClick: (String) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)) // 밝은 배경색
            .padding(horizontal = 24.dp, vertical = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // 헤더 영역
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "생활 메모장",
            color = Color(0xFF0F172A), // 다크 블루 계열
            style = AppTextStyles.Pretendard.Header1.copy(
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "쉽고 편리한 생활 메모",
            color = Color(0xFF64748B), // 슬레이트 그레이 계열
            style = AppTextStyles.Pretendard.Header4
        )

        Spacer(modifier = Modifier.height(48.dp))

        // 장보기 리스트 카드
        HomeMenuCard(
            title = "장보기 리스트",
            description = "장볼 물건을 기록하세요",
            icon = Icons.Default.ShoppingCart,
            iconColor = Color(0xFF3B82F6),
            iconBackgroundColor = Color(0xFFDBEAFE),
            onClick = { onMenuClick("shopping_list") }
        )

        Spacer(modifier = Modifier.height(20.dp))

        // 회원가입 정보 카드
        HomeMenuCard(
            title = "회원가입 정보",
            description = "아이디와 비밀번호를 보관하세요",
            icon = Icons.Default.AccountCircle,
            iconColor = Color(0xFF22C55E),
            iconBackgroundColor = Color(0xFFDCFCE7),
            onClick = { onMenuClick("info") }
        )

        Spacer(modifier = Modifier.height(20.dp))

        // 레시피 정보 카드
        HomeMenuCard(
            title = "레시피 저장소",
            description = "맛있는 요리 레시피를 저장하세요",
            icon = Icons.Default.Create,
            iconColor = Color(0xFFF54900),
            iconBackgroundColor = Color(0xFFFFEDD4),
            onClick = { onMenuClick("recipe") }
        )
    }
}



@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}