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

import com.spoonacular.client.model.InlineResponse20044
import com.spoonacular.client.model.InlineResponse20045
import com.spoonacular.client.model.InlineResponse20046
import com.spoonacular.client.model.InlineResponse20047

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

class WineApi(basePath: kotlin.String = "https://api.spoonacular.com") : ApiClient(basePath) {

    /**
    * Dish Pairing for Wine
    * Find a dish that goes well with a given wine.
    * @param wine The type of wine that should be paired, e.g. \&quot;merlot\&quot;, \&quot;riesling\&quot;, or \&quot;malbec\&quot;. 
    * @return InlineResponse20044
    */
    @Suppress("UNCHECKED_CAST")
    fun getDishPairingForWine(wine: kotlin.String) : InlineResponse20044 {
        val localVariableBody: kotlin.Any? = null
        val localVariableQuery: MultiValueMap = mapOf("wine" to listOf("$wine"))
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/food/wine/dishes",
            query = localVariableQuery,
            headers = localVariableHeaders
        )
        val response = request<InlineResponse20044>(
            localVariableConfig,
            localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as InlineResponse20044
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }

    /**
    * Wine Description
    * Get a simple description of a certain wine, e.g. \&quot;malbec\&quot;, \&quot;riesling\&quot;, or \&quot;merlot\&quot;.
    * @param wine The name of the wine that should be paired, e.g. \&quot;merlot\&quot;, \&quot;riesling\&quot;, or \&quot;malbec\&quot;. 
    * @return InlineResponse20046
    */
    @Suppress("UNCHECKED_CAST")
    fun getWineDescription(wine: kotlin.String) : InlineResponse20046 {
        val localVariableBody: kotlin.Any? = null
        val localVariableQuery: MultiValueMap = mapOf("wine" to listOf("$wine"))
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/food/wine/description",
            query = localVariableQuery,
            headers = localVariableHeaders
        )
        val response = request<InlineResponse20046>(
            localVariableConfig,
            localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as InlineResponse20046
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }

    /**
    * Wine Pairing
    * Find a wine that goes well with a food. Food can be a dish name (\&quot;steak\&quot;), an ingredient name (\&quot;salmon\&quot;), or a cuisine (\&quot;italian\&quot;).
    * @param food The food to get a pairing for. This can be a dish (\&quot;steak\&quot;), an ingredient (\&quot;salmon\&quot;), or a cuisine (\&quot;italian\&quot;). 
    * @param maxPrice The maximum price for the specific wine recommendation in USD. (optional)
    * @return InlineResponse20045
    */
    @Suppress("UNCHECKED_CAST")
    fun getWinePairing(food: kotlin.String, maxPrice: Float?) : InlineResponse20045 {
        val localVariableBody: kotlin.Any? = null
        val localVariableQuery: MultiValueMap = mapOf("food" to listOf("$food"), "maxPrice" to listOf("$maxPrice"))
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/food/wine/pairing",
            query = localVariableQuery,
            headers = localVariableHeaders
        )
        val response = request<InlineResponse20045>(
            localVariableConfig,
            localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as InlineResponse20045
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }

    /**
    * Wine Recommendation
    * Get a specific wine recommendation (concrete product) for a given wine type, e.g. \&quot;merlot\&quot;.
    * @param wine The type of wine to get a specific product recommendation for. 
    * @param maxPrice The maximum price for the specific wine recommendation in USD. (optional)
    * @param minRating The minimum rating of the recommended wine between 0 and 1. For example, 0.8 equals 4 out of 5 stars. (optional)
    * @param number The number of wine recommendations expected (between 1 and 100). (optional, default to 10)
    * @return InlineResponse20047
    */
    @Suppress("UNCHECKED_CAST")
    fun getWineRecommendation(wine: kotlin.String, maxPrice: Float?, minRating: Float?, number: Float?) : InlineResponse20047 {
        val localVariableBody: kotlin.Any? = null
        val localVariableQuery: MultiValueMap = mapOf("wine" to listOf("$wine"), "maxPrice" to listOf("$maxPrice"), "minRating" to listOf("$minRating"), "number" to listOf("$number"))
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/food/wine/recommendation",
            query = localVariableQuery,
            headers = localVariableHeaders
        )
        val response = request<InlineResponse20047>(
            localVariableConfig,
            localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as InlineResponse20047
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }

}