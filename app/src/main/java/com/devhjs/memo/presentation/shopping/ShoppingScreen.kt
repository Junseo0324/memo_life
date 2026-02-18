package com.devhjs.memo.presentation.shopping

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.devhjs.memo.domain.model.ShoppingItem
import com.devhjs.memo.presentation.designsystem.AppTextStyles

@Composable
fun ShoppingScreen(
    onBackClick: () -> Unit,
    viewModel: ShoppingViewModel = hiltViewModel()
) {
    val shoppingList = viewModel.shoppingList.collectAsState(initial = emptyList()).value
    val isDialogVisible = viewModel.isDialogVisible.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Top Bar
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF2979FF))
                    .padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { onBackClick() }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "홈으로",
                        style = AppTextStyles.Pretendard.Body.copy(
                            color = Color.White,
                            fontWeight = FontWeight.Medium
                        )
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "장보기 리스트",
                    style = AppTextStyles.Pretendard.Header1.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = { viewModel.showDialog() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2979FF)),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = null, tint = Color.White)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("항목 추가", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }

                Button(
                    onClick = { viewModel.deleteAllItems() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD50000)), // Red Color
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .weight(0.5f)
                        .height(50.dp)
                ) {
                    Icon(Icons.Default.Delete, contentDescription = null, tint = Color.White)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("전체삭제", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                }
            }

            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(shoppingList) { item ->
                    ShoppingItemRow(
                        item = item,
                        onToggleCheck = { viewModel.toggleItemChecked(item) },
                        onDelete = { viewModel.deleteItem(item) }
                    )
                }
            }
        }

        if (isDialogVisible) {
            AddItemDialog(
                viewModel = viewModel,
                onDismiss = { viewModel.hideDialog() },
                onSave = { viewModel.addItem() }
            )
        }
    }
}

@Composable
fun ShoppingItemRow(
    item: ShoppingItem,
    onToggleCheck: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Checkbox(
                checked = item.isChecked,
                onCheckedChange = { onToggleCheck() },
                colors = CheckboxDefaults.colors(
                    checkedColor = Color(0xFF2979FF),
                    uncheckedColor = Color.Gray
                )
            )
            
            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.name,
                    style = AppTextStyles.Pretendard.Header3.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                )
                if (item.quantity.isNotBlank()) {
                    Text(
                        text = item.quantity,
                        style = AppTextStyles.Pretendard.Body.copy(
                            color = Color.Gray,
                            fontSize = 14.sp
                        )
                    )
                }
            }

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFFFEBEE))
                    .clickable { onDelete() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = Color(0xFFD50000),
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
fun AddItemDialog(
    viewModel: ShoppingViewModel,
    onDismiss: () -> Unit,
    onSave: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(24.dp)
            ) {
                Text(
                    text = "물건 추가",
                    style = AppTextStyles.Pretendard.Header2.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                )
                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "품목명 *",
                    style = AppTextStyles.Pretendard.Body.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF37474F)
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = viewModel.itemName.value,
                    onValueChange = { viewModel.onNameChange(it) },
                    placeholder = { Text("예: 사과", color = Color.Gray) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF2979FF),
                        unfocusedBorderColor = Color.LightGray
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Quantity Input
                Text(
                    text = "수량 (선택사항)",
                    style = AppTextStyles.Pretendard.Body.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF37474F)
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = viewModel.itemQuantity.value,
                    onValueChange = { viewModel.onQuantityChange(it) },
                    placeholder = { Text("예: 5개", color = Color.Gray) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF2979FF),
                        unfocusedBorderColor = Color.LightGray
                    )
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = onDismiss,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEEEEEE)), // Light Gray
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp)
                    ) {
                        Text("취소", color = Color.Black, fontWeight = FontWeight.Bold)
                    }

                    Button(
                        onClick = onSave,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFCFD8DC)), // Light Gray/Blueish - Enabled state handling needed?
                        // Design shows Gray button for 'Save' initially, maybe enabled when text entered?
                        // I will use Blue for save to be consistent with main theme or grey if disabled.
                        // The image shows "저장" in Gray, implying disabled state or just gray style.
                        // I'll stick to a neutral gray for now as per image, but maybe make it blue if inputs are valid?
                        // Let's use a gray distinct from cancel but maybe darker? Or just blue for "Action".
                        // In the image it looks like the "Save" button is disabled gray.
                        // I will use a different color for active state.
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp),
                        enabled = viewModel.itemName.value.isNotBlank()
                    ) {
                         // Interactive color logic
                        Text("저장", color = if (viewModel.itemName.value.isNotBlank()) Color.White else Color.Gray, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}
