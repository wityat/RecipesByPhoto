package com.example.receiptsbyphoto

import android.annotation.SuppressLint
import android.content.ClipData.newIntent
import android.content.Context
import android.content.Intent
import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.spoonacular.client.model.InlineResponse2001
import com.squareup.picasso.Picasso


class RecipeActivity : AppCompatActivity(){
    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_recipe)
        listView = findViewById(R.id.listView)

        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        val app = applicationContext as MyApplication
        listView.setOnItemClickListener { _, _, position, _ ->
            app.selectedRecipe = app.recipes[position]
            startActivity(Intent(app, RecipeDetailsActivity::class.java))
        }
        val adapter = RecipeAdapter(app, app.recipes)
        listView.adapter = adapter
    }

    class RecipeAdapter(private val context: Context,
                        private val dataSource: Array<InlineResponse2001>
    ) : BaseAdapter() {
        //1
        override fun getCount(): Int {
            return dataSource.size
        }

        //2
        override fun getItem(position: Int): Any {
            return dataSource[position]
        }

        //3
        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        //4
        @SuppressLint("ViewHolder", "SetTextI18n")
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            // Get view for row item


            val rowView = inflater.inflate(R.layout.list_item_recipe, parent, false)
            // Get title element
            val titleTextView = rowView.findViewById(R.id.recipe_list_title) as TextView

            // Get subtitle element
            val subtitleTextView = rowView.findViewById(R.id.recipe_list_subtitle) as TextView

            // Get detail element
            val detailTextView = rowView.findViewById(R.id.recipe_list_detail) as TextView

            // Get thumbnail element
            val thumbnailImageView = rowView.findViewById(R.id.recipe_list_thumbnail) as ImageView

            // 1
            val recipe = getItem(position) as InlineResponse2001

            // 2
            titleTextView.text = recipe.title
            subtitleTextView.text = recipe.likes.toString() + "❤️"
            detailTextView.text = ""

            // 3
            Picasso.get().load(recipe.image).placeholder(R.mipmap.ic_launcher).into(thumbnailImageView)

            return rowView
        }

        private val inflater: LayoutInflater
                = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

//    override fun onBackPressed() {
//        finish()
//    }
}