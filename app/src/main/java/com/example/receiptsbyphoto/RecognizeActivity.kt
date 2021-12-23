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
            val jodForGettingIngredients = async { getIngredients(bitmap) }
            ingredients = jodForGettingIngredients.await()
            Log.d("ingredients", ingredients.toString())
            textView.text = "Getting recipes list..."
            val jodForGettingRecipes = async { getRecipes(ingredients) }
            val app = applicationContext as MyApplication
            app.recipes = jodForGettingRecipes.await()
            progressBar.visibility = ProgressBar.INVISIBLE
            Log.d("recipes size", app.recipes.size.toString())
            if (app.recipes.isNotEmpty()){
                launch {startActivity(Intent(app, RecipeActivity::class.java))}
                finish()
            }
            else{
                val builder = AlertDialog.Builder(imageView.context)
                builder.setTitle("Unable to recognize")
                builder.setMessage("Cannot recognize food ingredients on your picture. Try take another picture, please.")
                Log.d("!recipes size", app.recipes.size.toString())
                val positiveButtonClick = { dialog: DialogInterface, which: Int ->
                    finish()
                }
                Log.d("!!!recipes size", app.recipes.size.toString())
                builder.setPositiveButton("OK", DialogInterface.OnClickListener(function =positiveButtonClick))
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

//    private fun getRecipes(ingredients: List<String>){
//        val (request, response, result) = Fuel.get("https://api.spoonacular.com/recipes/findByIngredients", listOf("ingredients" to ingredients)).response()
//        return result
//    }

    private fun getIngredients(image: Bitmap): List<String> {
        ImageAnnotatorClient.create(ImageAnnotatorSettings.newBuilder().setCredentialsProvider(
            FixedCredentialsProvider.create(ServiceAccountCredentials
                .fromStream(getAssets().open("food-recognize.json")))).build()).use { vision ->

            val imgBytes: ByteString = ByteString.copyFrom(encodeImage(image))

            // Builds the image annotation request
            val requests: MutableList<AnnotateImageRequest> =
                ArrayList<AnnotateImageRequest>()
            val img: Image = Image.newBuilder().setContent(imgBytes).build()
            val feat: Feature =
                Feature.newBuilder().setType(Feature.Type.OBJECT_LOCALIZATION).build()
            val request: AnnotateImageRequest =
                AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build()
            requests.add(request)

            // Performs label detection on the image file
            val response: BatchAnnotateImagesResponse = vision.batchAnnotateImages(requests)
            val responses: List<AnnotateImageResponse> = response.responsesList

            return if (responses.isNotEmpty())
                responses[0].localizedObjectAnnotationsList.map { it.name }
            else
                listOf("nothing")
        }
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
