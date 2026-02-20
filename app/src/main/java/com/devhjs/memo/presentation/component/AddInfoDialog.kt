package com.devhjs.memo.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.devhjs.memo.domain.model.InformationItem
import com.devhjs.memo.presentation.designsystem.AppTextStyles

@Composable
fun AddInfoDialog(
    initialItem: InformationItem? = null,
    onDismiss: () -> Unit,
    onSave: (InformationItem) -> Unit
) {
    var siteName by remember { mutableStateOf(initialItem?.siteName ?: "") }
    var userId by remember { mutableStateOf(initialItem?.userId ?: "") }
    var userPw by remember { mutableStateOf(initialItem?.userPw ?: "") }
    var memo by remember { mutableStateOf(initialItem?.memo ?: "") }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(32.dp) // Figma: px-[31.99px], pt-[31.99px]
            ) {
                Text(
                    text = "새 정보 추가",
                    style = AppTextStyles.Pretendard.Header2.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF101828),
                        fontSize = 24.sp
                    )
                )
                Spacer(modifier = Modifier.height(24.dp))

                // 사이트명
                Text(
                    text = "사이트명 *",
                    style = AppTextStyles.Pretendard.Body.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF364153),
                        fontSize = 18.sp
                    )
                )
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedTextField(
                    value = siteName,
                    onValueChange = { siteName = it },
                    placeholder = { 
                        Text("예: 네이버", color = Color(0x800A0A0A), fontSize = 18.sp) 
                    },
                    modifier = Modifier.fillMaxWidth().height(62.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF2979FF),
                        unfocusedBorderColor = Color(0xFFD1D5DC)
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 아이디
                Text(
                    text = "아이디 *",
                    style = AppTextStyles.Pretendard.Body.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF364153),
                        fontSize = 18.sp
                    )
                )
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedTextField(
                    value = userId,
                    onValueChange = { userId = it },
                    placeholder = { 
                        Text("아이디 또는 이메일", color = Color(0x800A0A0A), fontSize = 18.sp) 
                    },
                    modifier = Modifier.fillMaxWidth().height(62.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF2979FF),
                        unfocusedBorderColor = Color(0xFFD1D5DC)
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 비밀번호
                Text(
                    text = "비밀번호 *",
                    style = AppTextStyles.Pretendard.Body.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF364153),
                        fontSize = 18.sp
                    )
                )
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedTextField(
                    value = userPw,
                    onValueChange = { userPw = it },
                    placeholder = { 
                        Text("비밀번호", color = Color(0x800A0A0A), fontSize = 18.sp) 
                    },
                    modifier = Modifier.fillMaxWidth().height(62.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF2979FF),
                        unfocusedBorderColor = Color(0xFFD1D5DC)
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 메모 (선택)
                Text(
                    text = "메모 (선택사항)",
                    style = AppTextStyles.Pretendard.Body.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF364153),
                        fontSize = 18.sp
                    )
                )
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedTextField(
                    value = memo,
                    onValueChange = { memo = it },
                    placeholder = { 
                        Text("기타 메모사항", color = Color(0x800A0A0A), fontSize = 18.sp) 
                    },
                    modifier = Modifier.fillMaxWidth().height(118.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF2979FF),
                        unfocusedBorderColor = Color(0xFFD1D5DC)
                    )
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Buttons
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = onDismiss,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE5E7EB)),
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(60.dp)
                    ) {
                        Text("취소", color = Color(0xFF364153), fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    }

                    val isEnabled = siteName.isNotBlank() && userId.isNotBlank() && userPw.isNotBlank()

                    Button(
                        onClick = {
                            val item = InformationItem(
                                id = initialItem?.id ?: 0,
                                siteName = siteName,
                                userId = userId,
                                userPw = userPw,
                                memo = memo
                            )
                            onSave(item)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = if (isEnabled) Color(0xFF00A63E) else Color(0xFFD1D5DC)),
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(60.dp),
                        enabled = isEnabled
                    ) {
                        Text("저장", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}
