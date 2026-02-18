package com.devhjs.memo.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            iconColor = Color(0xFF3B82F6), // 파란색
            iconBackgroundColor = Color(0xFFDBEAFE), // 연파란색 배경
            onClick = { onMenuClick("shopping_list") }
        )

        Spacer(modifier = Modifier.height(20.dp))

        // 회원가입 정보 카드
        HomeMenuCard(
            title = "회원가입 정보",
            description = "아이디와 비밀번호를 보관하세요",
            icon = Icons.Default.AccountCircle,
            iconColor = Color(0xFF22C55E), // 초록색
            iconBackgroundColor = Color(0xFFDCFCE7), // 연초록색 배경
            onClick = { onMenuClick("user_info") }
        )
    }
}

@Composable
fun HomeMenuCard(
    title: String,
    description: String,
    icon: ImageVector,
    iconColor: Color,
    iconBackgroundColor: Color,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(20.dp),
                ambientColor = Color.Black.copy(alpha = 0.1f),
                spotColor = Color.Black.copy(alpha = 0.1f)
            )
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 아이콘 박스
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(color = iconBackgroundColor, shape = RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconColor,
                    modifier = Modifier.size(40.dp)
                )
            }

            Spacer(modifier = Modifier.width(20.dp))

            // 텍스트 정보
            Column {
                Text(
                    text = title,
                    color = Color(0xFF0F172A),
                    style = AppTextStyles.Pretendard.Header2.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    color = Color(0xFF64748B),
                    style = AppTextStyles.Pretendard.Header5.copy(
                        fontSize = 15.sp
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}