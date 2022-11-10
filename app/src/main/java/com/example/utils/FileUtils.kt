package com.example.utils

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Base64
import java.io.File
import java.io.FileInputStream
import java.math.BigDecimal
import java.math.BigInteger
import java.security.MessageDigest


/**
 *@package com.example.utils
 *@author https://github.com/asd3590058
 *@fileName FileUtils
 *@date 2022/3/8 15:04
 *@description 文件工具类
 */
object FileUtils {

    fun delete(file: File): Boolean {
        return file.deleteRecursively()
    }

    /**
     * 删除单个文件
     *
     * @param fileName
     * 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    private fun deleteFile(fileName: String): Boolean {
        val file = File(fileName)
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        return if (file.exists() && file.isFile) {
            if (file.delete()) {
                println("删除单个文件" + fileName + "成功！")
                true
            } else {
                println("删除单个文件" + fileName + "失败！")
                false
            }
        } else {
            println("删除单个文件失败：" + fileName + "不存在！")
            false
        }
    }

    /**
     * 删除目录及目录下的文件
     *
     * @param dir
     * 要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    private fun deleteDirectory(dir: File): Boolean {
        try {
            // 如果dir对应的文件不存在，或者不是一个目录，则退出
            if (dir.exists() || dir.isDirectory) {
                // 删除文件夹中的所有文件包括子目录
                val files = dir.listFiles()
                files?.let {
                    for (i in it.indices) {
                        // 删除子文件
                        if (it[i].isFile) if (!it[i].delete()) break
                        else if (it[i].isDirectory) if (!deleteDirectory(it[i])) break
                    }
                }
            }
            // 删除当前目录
            return dir.delete()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }

    fun getFileMd5(file: File): String {
        return try {
            val buffer = ByteArray(1024)
            var len: Int
            val digest = MessageDigest.getInstance("MD5")
            val input = FileInputStream(file)
            while (input.read(buffer).also { len = it } != -1) {
                digest.update(buffer, 0, len)
            }
            input.close()
            val bigInt = BigInteger(1, digest.digest())
            bigInt.toString(16)
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }

    private fun getSize(file: File): Long {
        var size: Long = 0
        try {
            file.listFiles()?.apply {
                for (f in this) {
                    size = if (f.isDirectory) {
                        size + getSize(f)
                    } else {
                        size + f.length()
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return size
    }

    private fun formatSize(size: Double): String {
        val kiloByte = size / 1024
        if (kiloByte < 1) {
            return "0KB"
        }
        val megaByte = kiloByte / 1024
        if (megaByte < 1) {
            val result = BigDecimal(kiloByte.toString())
            return result.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB"
        }
        val gigaByte = megaByte / 1024
        if (gigaByte < 1) {
            val result = BigDecimal(megaByte.toString())
            return result.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB"
        }
        val teraBytes = gigaByte / 1024
        if (teraBytes < 1) {
            val result = BigDecimal(gigaByte.toString())
            return result.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB"
        }
        val result = BigDecimal(teraBytes)
        return result.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB"
    }

    fun checkSDCardAvailable(): Boolean = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED

    /**获取app缓存路径*/
    fun getWebCachePath(context: Context, name: String = "WEB_CACHE"): String? {
        return if (checkSDCardAvailable() || !Environment.isExternalStorageRemovable()) {
            //外部存储可用
            File(context.externalCacheDir, name).apply { mkdirs() }.absolutePath
        } else {
            //外部存储不可用
            File(context.cacheDir, name).apply { mkdirs() }.absolutePath
        }
    }

    fun clearWebCache(context: Context, name: String = "WEB_CACHE") {
        if (checkSDCardAvailable() || !Environment.isExternalStorageRemovable()) {
            File(context.externalCacheDir, name).deleteRecursively()
        } else {
            File(context.cacheDir, name).deleteRecursively()
        }
    }

    fun getTotalCacheSize(context: Context): String {
        var cacheSize = getSize(context.cacheDir)
        if (checkSDCardAvailable()) {
            context.externalCacheDir?.apply {
                cacheSize += getSize(this)
            }
        }
        return formatSize(cacheSize.toDouble())
    }

    fun clearAllCache(context: Context): Boolean {
        val cacheDir = File(context.cacheDir.path).deleteRecursively()
        if (checkSDCardAvailable()) {
            val externalCacheDir = context.externalCacheDir?.path?.let { File(it).deleteRecursively() } == true
            return cacheDir && externalCacheDir
        }
        return cacheDir
    }

    fun fileToBase64(file: File?): String = Base64.encodeToString(file?.readBytes() ?: ByteArray(0), Base64.NO_WRAP)

    fun getBitmap(contentResolver: ContentResolver, fileUri: Uri?): Bitmap? {
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
            MediaStore.Images.Media.getBitmap(contentResolver, fileUri)
        } else {
            fileUri?.let {
                ImageDecoder.createSource(contentResolver, it)
            }?.let {
                ImageDecoder.decodeBitmap(it)
            }
        }
    }

}