package com.devhjs.memo.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.devhjs.memo.R
import com.devhjs.memo.presentation.designsystem.AppTextStyles
import com.devhjs.memo.presentation.recipe.RecipeItemUiModel

@Composable
fun RecipeCard(
    recipe: RecipeItemUiModel,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .shadow(elevation = 6.dp, shape = RoundedCornerShape(14.dp)),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = androidx.compose.foundation.BorderStroke(width = 0.7.dp, color = Color(0xFFF3F4F6))
    ) {
        Column {
            AsyncImage(
                model = recipe.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(256.dp)
                    .background(Color(0xFFF3F4F6)),
                contentScale = ContentScale.Crop,
                error = painterResource(id = R.drawable.ic_launcher_background),
                placeholder = painterResource(id = R.drawable.ic_launcher_background)
            )
            
            Column(
                modifier = Modifier.padding(24.dp)
            ) {
                Text(
                    text = recipe.title,
                    style = AppTextStyles.Pretendard.Header2.copy(
                        color = Color(0xFF101828),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = recipe.description,
                    style = AppTextStyles.Pretendard.Body.copy(
                        color = Color(0xFF4A5565),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal
                    ),
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = recipe.date,
                    style = AppTextStyles.Pretendard.Body.copy(
                        color = Color(0xFF99A1AF),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal
                    )
                )
            }
        }
    }
}