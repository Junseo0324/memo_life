package com.devhjs.memo.presentation.info

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devhjs.memo.presentation.component.AddInfoDialog
import com.devhjs.memo.presentation.designsystem.MemoTheme

data class InformationItem(
    val id: String = "1",
    val siteName: String = "네이버",
    val userId: String = "junso0324",
    val userPw: String = "dddd"
)

@Composable
fun InformationScreen(
    modifier: Modifier = Modifier,
    state: InformationState = InformationState(),
    onAction: (InformationAction) -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF9FAFB))
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Top Section: Header
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(
                        elevation = 10.dp,
                        spotColor = Color(0x1A000000),
                        ambientColor = Color(0x1A000000)
                    )
                    .background(Color(0xFF00A63E))
                    .padding(top = 48.dp, start = 24.dp, end = 24.dp, bottom = 24.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { onAction(InformationAction.OnBackClick) }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "홈으로",
                        color = Color.White,
                        fontSize = 18.sp
                    )
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = "회원가입 정보",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Search Bar
                TextField(
                    value = state.searchQuery,
                    onValueChange = { onAction(InformationAction.UpdateSearchQuery(it)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    placeholder = {
                        Text(
                            text = "사이트명 또는 아이디 검색",
                            color = Color(0x80101828), // rgba(16,24,40,0.5)
                            fontSize = 18.sp
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = Color(0x80101828),
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    shape = RoundedCornerShape(14.dp)
                )
            }
            
            // Bottom Section: Content Area
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp) // padding as per design
            ) {
                Button(
                    onClick = { onAction(InformationAction.ShowDialog) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .shadow(
                            elevation = 10.dp,
                            shape = RoundedCornerShape(14.dp),
                            spotColor = Color(0x1A000000),
                            ambientColor = Color(0x1A000000)
                        ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF00A63E)
                    ),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    // 버튼 텍스트의 볼드체와 크기 적용
                    Text(
                        text = "새 정보 추가",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                if (state.infoList.isEmpty()) {
                    // Empty state Icon
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(64.dp)
                                    .background(Color(0xFFF3F4F6), shape = CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Search, 
                                    contentDescription = "Empty state icon",
                                    tint = Color(0xFFD1D5DB),
                                    modifier = Modifier.size(40.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "회원정보를 추가해주세요",
                                color = Color(0xFF99A1AF),
                                fontSize = 20.sp
                            )
                        }
                    }
                } else {
                    // List of info cards
                    val filteredList = state.infoList.filter { 
                        it.siteName.contains(state.searchQuery, ignoreCase = true) || 
                        it.userId.contains(state.searchQuery, ignoreCase = true)
                    }
                    
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(bottom = 24.dp)
                    ) {
                        items(filteredList) { item ->
                            InformationItemCard(item = item)
                        }
                    }
                }
            }
        }

        if (state.isDialogVisible) {
            AddInfoDialog(
                siteName = state.siteName,
                userId = state.userId,
                userPw = state.userPw,
                memo = state.memo,
                onSiteNameChange = { onAction(InformationAction.UpdateSiteName(it)) },
                onUserIdChange = { onAction(InformationAction.UpdateUserId(it)) },
                onUserPwChange = { onAction(InformationAction.UpdateUserPw(it)) },
                onMemoChange = { onAction(InformationAction.UpdateMemo(it)) },
                onDismiss = { onAction(InformationAction.HideDialog) },
                onSave = { onAction(InformationAction.AddInformation) }
            )
        }
    }
}

@Composable
fun InformationItemCard(item: InformationItem) {
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(14.dp),
                spotColor = Color(0x0D000000),
                ambientColor = Color(0x0D000000)
            )
            .background(Color.White)
            .border(
                width = 1.dp,
                color = Color(0xFFF3F4F6),
                shape = RoundedCornerShape(14.dp)
            )
            .padding(24.dp)
    ) {
        // Top row: Site name + Action Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = item.siteName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF101828)
            )
            
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                // Edit Button
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(Color(0xFFDBEAFE), shape = RoundedCornerShape(10.dp))
                        .clickable { /* TODO: Edit */ },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit",
                        tint = Color(0xFF2563EB),
                        modifier = Modifier.size(18.dp)
                    )
                }
                
                // Delete Button
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(Color(0xFFFFE2E2), shape = RoundedCornerShape(10.dp))
                        .clickable { /* TODO: Delete */ },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = Color(0xFFDC2626),
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // ID Section
        Column {
            Text(
                text = "아이디",
                fontSize = 14.sp,
                color = Color(0xFF4A5565)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = item.userId,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF101828)
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Password Section
        Column {
            Text(
                text = "비밀번호",
                fontSize = 14.sp,
                color = Color(0xFF4A5565)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Masking password if not visible
                val displayedPassword = if (passwordVisible) {
                    item.userPw
                } else {
                    "•".repeat(item.userPw.length.coerceAtLeast(4).coerceAtMost(12))
                }
                
                Text(
                    text = displayedPassword,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF101828)
                )
                
                // Toggle Visibility Button
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(Color(0xFFF3F4F6), shape = RoundedCornerShape(10.dp))
                        .clickable { passwordVisible = !passwordVisible },
                    contentAlignment = Alignment.Center
                ) {
                    // Using Lock icon as placeholder for visibility toggle
                    Icon(
                        imageVector = Icons.Default.Lock, // Placeholder for visibility_off/on
                        contentDescription = "Toggle Visibility",
                        tint = Color(0xFF6B7280),
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InformationScreenPreview() {
    MemoTheme {
        InformationScreen()
    }
}