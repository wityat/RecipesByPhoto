package com.example.receiptsbyphoto

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.spoonacular.RecipesApi
import com.spoonacular.client.model.InlineResponse20015
import com.squareup.picasso.Picasso

class RecipeDetailsActivity  : AppCompatActivity(){
    lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_recipe_details)
        imageView = findViewById(R.id.imageView)

        super.onCreate(savedInstanceState)
        val app = applicationContext as MyApplication
        Picasso.get().load(getRecipeCard(app.selectedRecipe.id).url).placeholder(R.mipmap.ic_launcher).into(imageView)
    }

    private fun getRecipeCard(id: Int): InlineResponse20015 {
        val api = RecipesApi("https://api.spoonacular.com?apiKey=YOUR_API_KEY")
        return api.getRecipeCard(id)
    }
}