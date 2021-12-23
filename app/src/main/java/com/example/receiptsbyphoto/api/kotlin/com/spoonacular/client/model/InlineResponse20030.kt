/**
* spoonacular API
* The spoonacular Nutrition, Recipe, and Food API allows you to access over 380,000 recipes, thousands of ingredients, 800,000 food products, and 100,000 menu items. Our food ontology and semantic recipe search engine makes it possible to search for recipes using natural language queries, such as \"gluten free brownies without sugar\" or \"low fat vegan cupcakes.\" You can automatically calculate the nutritional information for any recipe, analyze recipe costs, visualize ingredient lists, find recipes for what's in your fridge, find recipes based on special diets, nutritional requirements, or favorite ingredients, classify recipes into types and cuisines, convert ingredient amounts, or even compute an entire meal plan. With our powerful API, you can create many kinds of food and especially nutrition apps.  Special diets/dietary requirements currently available include: vegan, vegetarian, pescetarian, gluten free, grain free, dairy free, high protein, whole 30, low sodium, low carb, Paleo, ketogenic, FODMAP, and Primal.
*
* The version of the OpenAPI document: 1.0
* Contact: mail@spoonacular.com
*
* NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
* https://openapi-generator.tech
* Do not edit the class manually.
*/
package com.spoonacular.client.model

import com.spoonacular.client.model.InlineResponse20028Nutrition
import com.spoonacular.client.model.InlineResponse20028Servings
import com.spoonacular.client.model.InlineResponse20030Ingredients

import com.squareup.moshi.Json
/**
 * 
 * @param id 
 * @param title 
 * @param breadcrumbs 
 * @param imageType 
 * @param badges 
 * @param importantBadges 
 * @param ingredientCount 
 * @param generatedText 
 * @param ingredientList 
 * @param ingredients 
 * @param likes 
 * @param aisle 
 * @param nutrition 
 * @param price 
 * @param servings 
 * @param spoonacularScore 
 */
data class InlineResponse20030 (
    @Json(name = "id")
    val id: kotlin.Int,
    @Json(name = "title")
    val title: kotlin.String,
    @Json(name = "breadcrumbs")
    val breadcrumbs: kotlin.Array<kotlin.String>,
    @Json(name = "imageType")
    val imageType: kotlin.String,
    @Json(name = "badges")
    val badges: kotlin.Array<kotlin.String>,
    @Json(name = "importantBadges")
    val importantBadges: kotlin.Array<kotlin.String>,
    @Json(name = "ingredientCount")
    val ingredientCount: kotlin.Int,
    @Json(name = "ingredientList")
    val ingredientList: kotlin.String,
    @Json(name = "ingredients")
    val ingredients: kotlin.Array<InlineResponse20030Ingredients>,
    @Json(name = "likes")
    val likes: Float,
    @Json(name = "aisle")
    val aisle: kotlin.String,
    @Json(name = "nutrition")
    val nutrition: InlineResponse20028Nutrition,
    @Json(name = "price")
    val price: Float,
    @Json(name = "servings")
    val servings: InlineResponse20028Servings,
    @Json(name = "spoonacularScore")
    val spoonacularScore: Float,
    @Json(name = "generatedText")
    val generatedText: kotlin.Any? = null
) {

}

