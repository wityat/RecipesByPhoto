package com.example.receiptsbyphoto

import android.Manifest.permission.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri

import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import android.util.Log
import android.graphics.drawable.Drawable
import java.io.InputStream


class MainActivity : AppCompatActivity() {
    lateinit var imageView: ImageView
    lateinit var captureButton: Button

    val REQUEST_IMAGE_CAPTURE = 1


    private val PERMISSION_REQUEST_CODE: Int = 101

    private var mCurrentPhotoPath: String? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        imageView = findViewById(R.id.image_view)
//        try {
//            // get input stream
//            val ims: InputStream = assets.open("main_page.jpg")
//            // load image as Drawable
//            val d = Drawable.createFromStream(ims, null)
//            // set image to ImageView
//            imageView.setImageDrawable(d)
//            ims.close()
//        } catch (ex: IOException) {
//            return
//        }
        captureButton = findViewById(R.id.btn_capture)
        captureButton.setOnClickListener {
            if (checkPersmission()) takePicture() else requestPermission()
        }
    }

    private fun checkPersmission(): Boolean {
        Log.d("TAG", "checkPersmission")
        return (ContextCompat.checkSelfPermission(this, CAMERA) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                &&  ContextCompat.checkSelfPermission(this, INTERNET) == PackageManager.PERMISSION_GRANTED)
    }

    private fun requestPermission() {
        Log.d("TAG", "requestPermission")
        ActivityCompat.requestPermissions(this, arrayOf(READ_EXTERNAL_STORAGE, CAMERA, INTERNET), PERMISSION_REQUEST_CODE)
    }

    private fun takePicture() {

        val intent: Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val file: File = createFile()

        val uri: Uri = FileProvider.getUriForFile(
            this,
            "com.example.android.fileprovider",
            file
        )
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {

            //To get the File for further usage
//            val auxFile = File(mCurrentPhotoPath)

            val intent1 = Intent(this, RecognizeActivity::class.java)
            // To pass any data to next activity
            intent1.putExtra("photoPath", mCurrentPhotoPath)
            // start your next activity
            startActivity(intent1)

        }
    }

    @Throws(IOException::class)
    private fun createFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            mCurrentPhotoPath = absolutePath
        }
    }

}



