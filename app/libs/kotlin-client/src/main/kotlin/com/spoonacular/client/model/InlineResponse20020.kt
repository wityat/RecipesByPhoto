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

import com.spoonacular.client.model.RecipesParseIngredientsEstimatedCost
import com.spoonacular.client.model.RecipesParseIngredientsNutrition

import com.squareup.moshi.Json
/**
 * 
 * @param id 
 * @param original 
 * @param originalName 
 * @param name 
 * @param nameClean 
 * @param amount 
 * @param unit 
 * @param unitShort 
 * @param unitLong 
 * @param possibleUnits 
 * @param estimatedCost 
 * @param consistency 
 * @param aisle 
 * @param image 
 * @param meta 
 * @param nutrition 
 */
data class InlineResponse20020 (
    @Json(name = "id")
    val id: kotlin.Int,
    @Json(name = "original")
    val original: kotlin.String,
    @Json(name = "originalName")
    val originalName: kotlin.String,
    @Json(name = "name")
    val name: kotlin.String,
    @Json(name = "nameClean")
    val nameClean: kotlin.String,
    @Json(name = "amount")
    val amount: Int,
    @Json(name = "unit")
    val unit: kotlin.String,
    @Json(name = "unitShort")
    val unitShort: kotlin.String,
    @Json(name = "unitLong")
    val unitLong: kotlin.String,
    @Json(name = "possibleUnits")
    val possibleUnits: kotlin.Array<kotlin.String>,
    @Json(name = "estimatedCost")
    val estimatedCost: RecipesParseIngredientsEstimatedCost,
    @Json(name = "consistency")
    val consistency: kotlin.String,
    @Json(name = "aisle")
    val aisle: kotlin.String,
    @Json(name = "image")
    val image: kotlin.String,
    @Json(name = "meta")
    val meta: kotlin.Array<kotlin.String>,
    @Json(name = "nutrition")
    val nutrition: RecipesParseIngredientsNutrition
) {

}

