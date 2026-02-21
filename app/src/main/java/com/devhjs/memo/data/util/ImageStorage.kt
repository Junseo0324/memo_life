package com.devhjs.memo.data.util

import android.content.Context
import android.net.Uri
import java.io.File
import java.io.FileOutputStream
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageStorage @Inject constructor(
    private val context: Context
) {
    /**
     * 갤러리 URI의 이미지를 앱 내부 저장소로 복사하고 새 파일의 경로를 반환합니다.
     */
    fun saveImageToInternalStorage(uriString: String?): String? {
        if (uriString == null) return null
        
        return try {
            val uri = Uri.parse(uriString)
            val inputStream = context.contentResolver.openInputStream(uri) ?: return null
            
            // 앱 전용 내부 저장소 폴더 생성 (files/recipe_images)
            val directory = File(context.filesDir, "recipe_images")
            if (!directory.exists()) {
                directory.mkdirs()
            }
            
            // 고유한 파일명 생성
            val fileName = "recipe_${UUID.randomUUID()}.jpg"
            val file = File(directory, fileName)
            
            val outputStream = FileOutputStream(file)
            inputStream.use { input ->
                outputStream.use { output ->
                    input.copyTo(output)
                }
            }
            
            // 저장된 파일의 절대 경로 반환
            file.absolutePath
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
