package com.example.receiptsbyphoto
import android.app.AlertDialog
import android.app.Application
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


import java.io.ByteArrayOutputStream

import com.spoonacular.RecipesApi
import com.spoonacular.client.model.InlineResponse2001
import kotlinx.coroutines.*
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import id.zelory.compressor.Compressor
import id.zelory.compressor.constraint.default
import id.zelory.compressor.constraint.destination
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.IOException


class MyApplication : Application() {
    lateinit var recipes: Array<InlineResponse2001>
    lateinit var selectedRecipe: InlineResponse2001
}

class RecognizeActivity : AppCompatActivity(){

    lateinit var imageView: ImageView
    lateinit var progressBar: ProgressBar
    lateinit var textView: TextView
    lateinit var ingredients: List<String>
    lateinit var recipes: Array<InlineResponse2001>


    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_recognize)
        imageView = findViewById(R.id.image_view2)
        progressBar = findViewById(R.id.progressBar)
        textView = findViewById(R.id.textView)
        super.onCreate(savedInstanceState)
    }



    override fun onResume(){
        super.onResume()
        val photoPath = intent.getStringExtra("photoPath")
        val bitmap: Bitmap = BitmapFactory.decodeFile(photoPath)
        imageView.setImageBitmap(bitmap.rotate(90f))

        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        val choosedIngredients = mutableListOf<String>()

        GlobalScope.launch(Dispatchers.Main) {
            progressBar.visibility = ProgressBar.VISIBLE
            val jodForGettingIngredients = async { getIngredients(photoPath) }

            ingredients =jodForGettingIngredients.await()

            if (ingredients.isNotEmpty() && ingredients[0] === "exception")
            {
                val builder = AlertDialog.Builder(imageView.context)
                builder.setCancelable(false)
                builder.setTitle("Server is temporary unavailable")
                builder.setMessage("Server is temporary unavailable. Try again later, please.")
                builder.setPositiveButton("TRY AGAIN") { dialog: DialogInterface, which: Int ->
                    finish()
                }
                builder.show()

            } else {
                val checkedIngredients = BooleanArray(ingredients.size)

                val builder = AlertDialog.Builder(imageView.context)
                builder.setTitle("Выберите ингредиенты")
                        .setCancelable(false)
                        .setMultiChoiceItems(ingredients.toTypedArray(), checkedIngredients) { dialog, which, isChecked ->
                            checkedIngredients[which] = isChecked
                        }
                        .setPositiveButton("Готово") { dialog, id ->

                            for (i in ingredients.indices) {
                                if (checkedIngredients[i]) {
                                    choosedIngredients.add(ingredients[i])
                                }
                            }

                            runBlocking {

                                textView.text = "Getting recipes list..."
                                val jodForGettingRecipes = async { getRecipes(choosedIngredients) }
                                val app = applicationContext as MyApplication
                                app.recipes = jodForGettingRecipes.await()
                                progressBar.visibility = ProgressBar.INVISIBLE
                                Log.d("recipes size", app.recipes.size.toString())
                                if (app.recipes.isNotEmpty()) {
                                    launch { startActivity(Intent(app, RecipeActivity::class.java)) }
                                    finish()
                                } else {
                                    val builder = AlertDialog.Builder(imageView.context)
                                    builder.setCancelable(false)
                                    builder.setTitle("Unable to recognize")
                                    builder.setMessage("Cannot recognize food ingredients on your picture. Try take another picture, please.")
                                    builder.setPositiveButton("OK") { dialog: DialogInterface, which: Int ->
                                        finish()
                                    }
                                    builder.show()
                                }

                            }


                        }
                        .setNegativeButton("Отмена") { dialog, _ ->
                            dialog.cancel()
                            finish()
                        }

                builder.show()
            }
        }
    }

    fun Bitmap.rotate(degrees: Float): Bitmap {
        val matrix = Matrix().apply { postRotate(degrees) }
        return Bitmap.createBitmap(this, 0, 0, width, height, matrix, true)
    }

    private fun getRecipes(ingredients: List<String>): Array<InlineResponse2001> {

        val api = RecipesApi("https://api.spoonacular.com?apiKey=c9cbdf49f66e41b7a533b63246b2f776")
        return api.searchRecipesByIngredients(
            ingredients=ingredients.joinToString(),
            number=10,
            limitLicense=true,
            ranking=2,
            ignorePantry=true
        )
    }

    private fun getIngredients(photoPath: String?): List<String> {
        data class Respons(
            val ingredient: Map<String, Float>,
        )

        var res: Respons
        val moshi: Moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<Respons> = moshi.adapter(Respons::class.java)


        val client = OkHttpClient()
        val context = this

        fun compres() = runBlocking {
            val compressedImageFile = Compressor.compress(context, File(photoPath.toString())) {
                default()
                destination(File(photoPath.toString()))
            }
            return@runBlocking compressedImageFile
        }

        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("image", "image.jpg",
                    compres().asRequestBody("image/jpeg".toMediaType()))
            .build()

        val request = Request.Builder()
            .header("token", "kreks")
            .url("https://ingredientsrecognize.pagekite.me/image/get_ingredients/")
            .post(requestBody)
            .build()

        try {
            client.newCall(request).execute().use { response ->
                if (response.isSuccessful) {
                    res = jsonAdapter.fromJson(response.body!!.source())!!
                } else {
                    throw IOException("Unexpected code $response")
                }
            }
        }catch (e: Exception){
            Log.d("Exception", e.message.toString())
            return listOf("exception")
        }

        return if (res.ingredient.isNotEmpty())
            res.ingredient.map{ it.key }
        else
            listOf()

    }

    private fun encodeImage(bm: Bitmap): ByteArray {
        val baos = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val b = baos.toByteArray()
        return b
    }
    private fun rotateImage(img: Bitmap, degree: Int): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(degree.toFloat())
        return Bitmap.createBitmap(img, 0, 0, img.width, img.height, matrix, true)
    }

}
