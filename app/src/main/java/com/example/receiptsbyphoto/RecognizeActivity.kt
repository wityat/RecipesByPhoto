package com.example.receiptsbyphoto
import android.app.AlertDialog
import android.app.Application
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


import com.google.api.gax.core.FixedCredentialsProvider
import com.google.cloud.vision.v1.*
import com.google.protobuf.ByteString
import java.io.ByteArrayOutputStream
import java.util.ArrayList
import com.google.auth.oauth2.ServiceAccountCredentials
import com.spoonacular.RecipesApi
import com.spoonacular.client.model.InlineResponse2001
import kotlinx.coroutines.*
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.widget.Toast
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.objects.ObjectDetection
import com.google.mlkit.vision.objects.defaults.ObjectDetectorOptions
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


        GlobalScope.launch(Dispatchers.Main) {
            progressBar.visibility = ProgressBar.VISIBLE
            val jodForGettingIngredients = async { getIngredients(photoPath) }
            ingredients = jodForGettingIngredients.await()
            val checkedIngredients = booleanArrayOf()
            ingredients.forEach{ _ -> checkedIngredients.toMutableList().add(false)}
            Log.d("ingredients", ingredients.toString())

            suspend fun contin(choosedIngredients: List<String>) {
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
                    builder.setTitle("Unable to recognize")
                    builder.setMessage("Cannot recognize food ingredients on your picture. Try take another picture, please.")
                    Log.d("!recipes size", app.recipes.size.toString())
                    val positiveButtonClick = { dialog: DialogInterface, which: Int ->
                        finish()
                    }
                    Log.d("!!!recipes size", app.recipes.size.toString())
                    builder.setPositiveButton(
                        "OK",
                        DialogInterface.OnClickListener(function = positiveButtonClick)
                    )
                    builder.show()
                }
            }

            val builder1 = AlertDialog.Builder(imageView.context)
            builder1.setTitle("Выберите ингредиенты")
                .setMultiChoiceItems(ingredients.toTypedArray(), checkedIngredients) {
                        dialog, which, isChecked ->
                    checkedIngredients[which] = isChecked
                    val name = ingredients[which] // Get the clicked item
                    Toast.makeText(imageView.context, name, Toast.LENGTH_LONG).show()
                }
                .setPositiveButton("Готово"
                ) {
                        dialog, id ->
                    val choosedIngredients = listOf<String>()
                        for (i in ingredients.indices) {
                        val checked = checkedIngredients[i]
                        if (checked) {
                            Log.i("Dialog", ingredients[i])
                            choosedIngredients.toMutableList().add(ingredients[i])
                        }

                    }
                    launch {contin(choosedIngredients)}
                }
                .setNegativeButton("Отмена") {
                        dialog, _ ->  dialog.cancel()
                    finish()
                }
            builder1.show()


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

//    private fun getRecipes(ingredients: List<String>){
//        val (request, response, result) = Fuel.get("https://api.spoonacular.com/recipes/findByIngredients", listOf("ingredients" to ingredients)).response()
//        return result
//    }

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
        // Use the imgur image upload API as documented at https://api.imgur.com/endpoints/image
        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
//                .addFormDataPart("title", "Square Logo")
            .addFormDataPart("image", "image.jpg",
                compres().asRequestBody("image/jpeg".toMediaType()))
            .build()

        val request = Request.Builder()
            .header("token", "kreks")
            .url("https://9141-109-184-53-6.ngrok.io/image/get_ingredients/")
            .post(requestBody)
            .build()

        try{
            client.newCall(request).execute().use { response ->
                if (response.isSuccessful) {
                    res = jsonAdapter.fromJson(response.body!!.source())!!
                } else {
                    throw IOException("Unexpected code $response")
//                    res = Respons(mapOf())
                }
            }
        }catch (e: Exception){
//                val builder = AlertDialog.Builder(imageView.context)
//                builder.setTitle("Server is temporary unavailable")
//                builder.setMessage("Server is temporary unavailable. Try again later, please.")
//                val positiveButtonClick = { dialog: DialogInterface, which: Int ->
//                    finish()
//                }
//                builder.setPositiveButton(
//                    "OK",
//                    DialogInterface.OnClickListener(function = positiveButtonClick)
//                )
//                builder.show()
            Log.d("ERROR", e.toString())
                res = Respons(mapOf())
            }


        if (res.ingredient.isNotEmpty())
            return res.ingredient.map{ it.key }
        else
            return listOf()

//
//        val options = ObjectDetectorOptions.Builder()
//            .setDetectorMode(ObjectDetectorOptions.SINGLE_IMAGE_MODE)
//            .enableMultipleObjects()
//            .enableClassification()  // Optional
//            .build()
//        val objectDetector = ObjectDetection.getClient(options)
//
//        val image = InputImage.fromBitmap(image, 0)
//        var ingredients = listOf<String>()
//
//        objectDetector.process(image)
//            .addOnSuccessListener { detectedObjects ->
//                // Task completed successfully
//                // ...
//                for (detectedObject in detectedObjects) {
//                    for (label in detectedObject.labels) {
//                        val text = label.text
//                        val confidence = label.confidence
//                        val index = label.index
//                        Log.d("DETECTED", "$text $confidence $index")
//                        ingredients.toMutableList().add(text)
//                    }
//
//                    ingredients = ingredients + detectedObject.labels.map { it.text }
//
//                }
//                if (detectedObjects.isNotEmpty())
//                    Log.d("SUCCESS", detectedObjects[0].labels[0].toString())
//                else
//                    Log.d("SUCCESS BUT NO OBJECTS", "NO OBJECTS DETECTED")
//
//            }
//            .addOnFailureListener { e ->
//                // Task failed with an exception
//                // ...
//                Log.d("ERROR!!!", e.toString())
//                ingredients = listOf("nothing")
//            }
//        return ingredients
//
//
//        ImageAnnotatorClient.create(ImageAnnotatorSettings.newBuilder().setCredentialsProvider(
//            FixedCredentialsProvider.create(ServiceAccountCredentials
//                .fromStream(getAssets().open("food-recognize.json")))).build()).use { vision ->
//
//            val imgBytes: ByteString = ByteString.copyFrom(encodeImage(image))
//
//            // Builds the image annotation request
//            val requests: MutableList<AnnotateImageRequest> =
//                ArrayList<AnnotateImageRequest>()
//            val img: Image = Image.newBuilder().setContent(imgBytes).build()
//            val feat: Feature =
//                Feature.newBuilder().setType(Feature.Type.LABEL_DETECTION).build()
//            val request: AnnotateImageRequest =
//                AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build()
//            requests.add(request)
//
//            // Performs label detection on the image file
//            val response: BatchAnnotateImagesResponse = vision.batchAnnotateImages(requests)
//            val responses: List<AnnotateImageResponse> = response.responsesList
//
//            return if (responses.isNotEmpty())
//                responses[0].labelAnnotationsList.map { it.description }
////                responses[0].localizedObjectAnnotationsList.map { it.name }
//            else
//                listOf("nothing")
//        }
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


//    fun requestPost() {
//
//        val httpAsync = url
//            .httpPost()
//            .jsonBody(visionR.toString())
//            .responseObject(VisionResponse.Deserializer()){
//
//            }
//
//        httpAsync.join()
//    }
}
