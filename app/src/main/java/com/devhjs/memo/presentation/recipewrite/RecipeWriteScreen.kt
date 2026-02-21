package com.devhjs.memo.presentation.recipewrite

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.devhjs.memo.presentation.component.InputItem
import com.devhjs.memo.presentation.component.SectionHeader
import com.devhjs.memo.presentation.designsystem.AppTextStyles

@Composable
fun RecipeWriteScreen(
    state: RecipeWriteState,
    onAction: (RecipeWriteAction) -> Unit
) {
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            onAction(RecipeWriteAction.UpdateImage(uri?.toString()))
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9FAFB))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF54900))
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable { onAction(RecipeWriteAction.OnBackClick) }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "작성 취소",
                    style = AppTextStyles.Pretendard.Body.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
            Text(
                text = "레시피 작성",
                style = AppTextStyles.Pretendard.Header2.copy(
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            IconButton(onClick = { onAction(RecipeWriteAction.SaveRecipe) }) {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = "Save",
                    tint = Color.White
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clickable { imagePickerLauncher.launch("image/*") },
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE5E7EB))
                ) {
                    if (state.imageUrl != null) {
                        AsyncImage(
                            model = state.imageUrl,
                            contentDescription = "Recipe Image",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = null,
                                    modifier = Modifier.size(48.dp),
                                    tint = Color.Gray
                                )
                                Text(text = "대표 사진 추가", color = Color.Gray)
                            }
                        }
                    }
                }
            }

            item {
                OutlinedTextField(
                    value = state.title,
                    onValueChange = { onAction(RecipeWriteAction.UpdateTitle(it)) },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("레시피 제목을 입력하세요", color = Color.Gray) },
                    textStyle = AppTextStyles.Pretendard.Header1.copy(fontSize = 24.sp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFFF54900),
                        unfocusedBorderColor = Color.Transparent,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    ),
                    shape = RoundedCornerShape(12.dp)
                )
            }

            item {
                SectionHeader(title = "필요한 재료", onAddClick = { onAction(RecipeWriteAction.AddIngredient) })
            }
            itemsIndexed(state.ingredients) { index, ingredient ->
                InputItem(
                    text = ingredient,
                    placeholder = "예: 김치 200g, 고기 100g",
                    onValueChange = { onAction(RecipeWriteAction.UpdateIngredient(index, it)) },
                    onRemoveClick = { onAction(RecipeWriteAction.RemoveIngredient(index)) }
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                SectionHeader(title = "조리 순서", onAddClick = { onAction(RecipeWriteAction.AddInstruction) })
            }
            itemsIndexed(state.instructions) { index, instruction ->
                InputItem(
                    text = instruction,
                    placeholder = "${index + 1}. 조리 내용을 입력하세요",
                    onValueChange = { onAction(RecipeWriteAction.UpdateInstruction(index, it)) },
                    onRemoveClick = { onAction(RecipeWriteAction.RemoveInstruction(index)) },
                    isMultiLine = true
                )
            }
            
            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Preview
@Composable
private fun RecipeWriteScreenPreview() {
    RecipeWriteScreen(
        state = RecipeWriteState(),
        onAction = {}
    )
}