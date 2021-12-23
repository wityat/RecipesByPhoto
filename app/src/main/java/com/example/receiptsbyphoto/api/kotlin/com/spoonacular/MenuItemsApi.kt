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
package com.spoonacular

import com.spoonacular.client.model.InlineResponse20032
import com.spoonacular.client.model.InlineResponse20035
import com.spoonacular.client.model.InlineResponse20036

import spoonacular.infrastructure.ApiClient
import spoonacular.infrastructure.ClientException
import spoonacular.infrastructure.ClientError
import spoonacular.infrastructure.ServerException
import spoonacular.infrastructure.ServerError
import spoonacular.infrastructure.MultiValueMap
import spoonacular.infrastructure.RequestConfig
import spoonacular.infrastructure.RequestMethod
import spoonacular.infrastructure.ResponseType
import spoonacular.infrastructure.Success
import spoonacular.infrastructure.toMultiValue

class MenuItemsApi(basePath: kotlin.String = "https://api.spoonacular.com") : ApiClient(basePath) {

    /**
    * Autocomplete Menu Item Search
    * Generate suggestions for menu items based on a (partial) query. The matches will be found by looking in the title only.
    * @param query The (partial) search query. 
    * @param number The number of results to return (between 1 and 25). (optional)
    * @return InlineResponse20032
    */
    @Suppress("UNCHECKED_CAST")
    fun autocompleteMenuItemSearch(query: kotlin.String, number: Float?) : InlineResponse20032 {
        val localVariableBody: kotlin.Any? = null
        val localVariableQuery: MultiValueMap = mapOf("query" to listOf("$query"), "number" to listOf("$number"))
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/food/menuItems/suggest",
            query = localVariableQuery,
            headers = localVariableHeaders
        )
        val response = request<InlineResponse20032>(
            localVariableConfig,
            localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as InlineResponse20032
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }

    /**
    * Get Menu Item Information
    * Use a menu item id to get all available information about a menu item, such as nutrition.
    * @param id The item&#39;s id. 
    * @return InlineResponse20036
    */
    @Suppress("UNCHECKED_CAST")
    fun getMenuItemInformation(id: kotlin.Int) : InlineResponse20036 {
        val localVariableBody: kotlin.Any? = null
        val localVariableQuery: MultiValueMap = mapOf()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/food/menuItems/{id}".replace("{"+"id"+"}", "$id"),
            query = localVariableQuery,
            headers = localVariableHeaders
        )
        val response = request<InlineResponse20036>(
            localVariableConfig,
            localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as InlineResponse20036
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }

    /**
    * Menu Item Nutrition by ID Image
    * Visualize a menu item&#39;s nutritional information as HTML including CSS.
    * @param id The menu item id. 
    * @return kotlin.Any
    */
    @Suppress("UNCHECKED_CAST")
    fun menuItemNutritionByIDImage(id: Float) : kotlin.Any {
        val localVariableBody: kotlin.Any? = null
        val localVariableQuery: MultiValueMap = mapOf()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/food/menuItems/{id}/nutritionWidget.png".replace("{"+"id"+"}", "$id"),
            query = localVariableQuery,
            headers = localVariableHeaders
        )
        val response = request<kotlin.Any>(
            localVariableConfig,
            localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as kotlin.Any
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }

    /**
    * Menu Item Nutrition Label Image
    * Visualize a menu item&#39;s nutritional label information as an image.
    * @param id The menu item id. 
    * @param showOptionalNutrients Whether to show optional nutrients. (optional)
    * @param showZeroValues Whether to show zero values. (optional)
    * @param showIngredients Whether to show a list of ingredients. (optional)
    * @return kotlin.Any
    */
    @Suppress("UNCHECKED_CAST")
    fun menuItemNutritionLabelImage(id: Float, showOptionalNutrients: kotlin.Boolean?, showZeroValues: kotlin.Boolean?, showIngredients: kotlin.Boolean?) : kotlin.Any {
        val localVariableBody: kotlin.Any? = null
        val localVariableQuery: MultiValueMap = mapOf("showOptionalNutrients" to listOf("$showOptionalNutrients"), "showZeroValues" to listOf("$showZeroValues"), "showIngredients" to listOf("$showIngredients"))
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/food/menuItems/{id}/nutritionLabel.png".replace("{"+"id"+"}", "$id"),
            query = localVariableQuery,
            headers = localVariableHeaders
        )
        val response = request<kotlin.Any>(
            localVariableConfig,
            localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as kotlin.Any
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }

    /**
    * Menu Item Nutrition Label Widget
    * Visualize a menu item&#39;s nutritional label information as HTML including CSS.
    * @param id The menu item id. 
    * @param defaultCss Whether the default CSS should be added to the response. (optional, default to true)
    * @param showOptionalNutrients Whether to show optional nutrients. (optional)
    * @param showZeroValues Whether to show zero values. (optional)
    * @param showIngredients Whether to show a list of ingredients. (optional)
    * @return kotlin.String
    */
    @Suppress("UNCHECKED_CAST")
    fun menuItemNutritionLabelWidget(id: Float, defaultCss: kotlin.Boolean?, showOptionalNutrients: kotlin.Boolean?, showZeroValues: kotlin.Boolean?, showIngredients: kotlin.Boolean?) : kotlin.String {
        val localVariableBody: kotlin.Any? = null
        val localVariableQuery: MultiValueMap = mapOf("defaultCss" to listOf("$defaultCss"), "showOptionalNutrients" to listOf("$showOptionalNutrients"), "showZeroValues" to listOf("$showZeroValues"), "showIngredients" to listOf("$showIngredients"))
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/food/menuItems/{id}/nutritionLabel".replace("{"+"id"+"}", "$id"),
            query = localVariableQuery,
            headers = localVariableHeaders
        )
        val response = request<kotlin.String>(
            localVariableConfig,
            localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as kotlin.String
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }

    /**
    * Search Menu Items
    * Search over 115,000 menu items from over 800 fast food and chain restaurants. For example, McDonald&#39;s Big Mac or Starbucks Mocha.
    * @param query The (natural language) search query. (optional)
    * @param minCalories The minimum amount of calories the menu item must have. (optional)
    * @param maxCalories The maximum amount of calories the menu item can have. (optional)
    * @param minCarbs The minimum amount of carbohydrates in grams the menu item must have. (optional)
    * @param maxCarbs The maximum amount of carbohydrates in grams the menu item can have. (optional)
    * @param minProtein The minimum amount of protein in grams the menu item must have. (optional)
    * @param maxProtein The maximum amount of protein in grams the menu item can have. (optional)
    * @param minFat The minimum amount of fat in grams the menu item must have. (optional)
    * @param maxFat The maximum amount of fat in grams the menu item can have. (optional)
    * @param addMenuItemInformation If set to true, you get more information about the menu items returned. (optional)
    * @param offset The number of results to skip (between 0 and 900). (optional)
    * @param number The maximum number of items to return (between 1 and 100). Defaults to 10. (optional, default to 10)
    * @return InlineResponse20035
    */
    @Suppress("UNCHECKED_CAST")
    fun searchMenuItems(query: kotlin.String?, minCalories: Float?, maxCalories: Float?, minCarbs: Float?, maxCarbs: Float?, minProtein: Float?, maxProtein: Float?, minFat: Float?, maxFat: Float?, addMenuItemInformation: kotlin.Boolean?, offset: kotlin.Int?, number: kotlin.Int?) : InlineResponse20035 {
        val localVariableBody: kotlin.Any? = null
        val localVariableQuery: MultiValueMap = mapOf("query" to listOf("$query"), "minCalories" to listOf("$minCalories"), "maxCalories" to listOf("$maxCalories"), "minCarbs" to listOf("$minCarbs"), "maxCarbs" to listOf("$maxCarbs"), "minProtein" to listOf("$minProtein"), "maxProtein" to listOf("$maxProtein"), "minFat" to listOf("$minFat"), "maxFat" to listOf("$maxFat"), "addMenuItemInformation" to listOf("$addMenuItemInformation"), "offset" to listOf("$offset"), "number" to listOf("$number"))
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/food/menuItems/search",
            query = localVariableQuery,
            headers = localVariableHeaders
        )
        val response = request<InlineResponse20035>(
            localVariableConfig,
            localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as InlineResponse20035
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }

    /**
    * Menu Item Nutrition by ID Widget
    * Visualize a menu item&#39;s nutritional information as HTML including CSS.
    * @param id The item&#39;s id. 
    * @param defaultCss Whether the default CSS should be added to the response. (optional, default to true)
    * @param accept Accept header. (optional)
    * @return kotlin.String
    */
    @Suppress("UNCHECKED_CAST")
    fun visualizeMenuItemNutritionByID(id: kotlin.Int, defaultCss: kotlin.Boolean?, accept: kotlin.String?) : kotlin.String {
        val localVariableBody: kotlin.Any? = null
        val localVariableQuery: MultiValueMap = mapOf("defaultCss" to listOf("$defaultCss"))
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf("Accept" to accept.toString())
        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/food/menuItems/{id}/nutritionWidget".replace("{"+"id"+"}", "$id"),
            query = localVariableQuery,
            headers = localVariableHeaders
        )
        val response = request<kotlin.String>(
            localVariableConfig,
            localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as kotlin.String
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }

}
