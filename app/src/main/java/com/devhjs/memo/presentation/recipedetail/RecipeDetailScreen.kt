package com.devhjs.memo.presentation.recipedetail

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.devhjs.memo.R
import com.devhjs.memo.presentation.designsystem.AppTextStyles


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailScreen(
    state: RecipeDetailState,
    onAction: (RecipeDetailAction) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "레시피 상세",
                        style = AppTextStyles.Pretendard.Header2.copy(fontSize = 18.sp)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onAction(RecipeDetailAction.OnBackClick) }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        containerColor = Color.White
    ) { padding ->
        if (state.isLoading) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else if (state.recipe != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
            ) {
                AsyncImage(
                    model = state.recipe.imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .background(Color(0xFFF3F4F6)),
                    contentScale = ContentScale.Crop,
                    error = painterResource(id = R.drawable.ic_launcher_background),
                    placeholder = painterResource(id = R.drawable.ic_launcher_background)
                )

                Column(modifier = Modifier.padding(24.dp)) {
                    Text(
                        text = state.recipe.title,
                        style = AppTextStyles.Pretendard.Header1.copy(fontSize = 28.sp)
                    )
                    
                    Spacer(modifier = Modifier.height(32.dp))
                    
                    Text(
                        text = "재료",
                        style = AppTextStyles.Pretendard.Header2.copy(fontSize = 20.sp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    state.recipe.ingredients.forEach { ingredient ->
                        Text(
                            text = "• $ingredient",
                            style = AppTextStyles.Pretendard.Body.copy(fontSize = 16.sp, color = Color(0xFF4A5565)),
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    Text(
                        text = "조리 순서",
                        style = AppTextStyles.Pretendard.Header2.copy(fontSize = 20.sp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    state.recipe.instructions.forEachIndexed { index, instruction ->
                        Row(modifier = Modifier.padding(vertical = 8.dp)) {
                            Text(
                                text = "${index + 1}",
                                style = AppTextStyles.Pretendard.Header2.copy(
                                    fontSize = 14.sp,
                                    color = Color.White
                                ),
                                modifier = Modifier
                                    .size(24.dp)
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(Color(0xFF344054))
                                    .wrapContentSize(Alignment.Center)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = instruction,
                                style = AppTextStyles.Pretendard.Body.copy(fontSize = 16.sp, color = Color(0xFF4A5565))
                            )
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(40.dp))
                }
            }
        }
    }
}
