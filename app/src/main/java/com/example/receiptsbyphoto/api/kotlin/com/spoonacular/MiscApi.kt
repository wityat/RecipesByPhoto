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

import com.spoonacular.client.model.InlineResponse20029
import com.spoonacular.client.model.InlineResponse20048
import com.spoonacular.client.model.InlineResponse20049
import com.spoonacular.client.model.InlineResponse20051
import com.spoonacular.client.model.InlineResponse20052
import com.spoonacular.client.model.InlineResponse20053
import com.spoonacular.client.model.InlineResponse20054
import com.spoonacular.client.model.InlineResponse20055
import com.spoonacular.client.model.InlineResponse20056
import com.spoonacular.client.model.InlineResponse20057

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

class MiscApi(basePath: kotlin.String = "https://api.spoonacular.com") : ApiClient(basePath) {

    /**
    * Detect Food in Text
    * Take any text and find all mentions of food contained within it. This task is also called Named Entity Recognition (NER). In this case, the entities are foods. Either dishes, such as pizza or cheeseburger, or ingredients, such as cucumber or almonds.
    * @param contentMinusType The content type. (optional)
    * @return InlineResponse20051
    */
    @Suppress("UNCHECKED_CAST")
    fun detectFoodInText(contentMinusType: kotlin.String?) : InlineResponse20051 {
        val localVariableBody: kotlin.Any? = null
        val localVariableQuery: MultiValueMap = mapOf()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf("Content-Type" to contentMinusType.toString())
        val localVariableConfig = RequestConfig(
            RequestMethod.POST,
            "/food/detect",
            query = localVariableQuery,
            headers = localVariableHeaders
        )
        val response = request<InlineResponse20051>(
            localVariableConfig,
            localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as InlineResponse20051
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }

    /**
    * Random Food Joke
    * Get a random joke that is related to food. Caution: this is an endpoint for adults!
    * @return InlineResponse20055
    */
    @Suppress("UNCHECKED_CAST")
    fun getARandomFoodJoke() : InlineResponse20055 {
        val localVariableBody: kotlin.Any? = null
        val localVariableQuery: MultiValueMap = mapOf()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/food/jokes/random",
            query = localVariableQuery,
            headers = localVariableHeaders
        )
        val response = request<InlineResponse20055>(
            localVariableConfig,
            localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as InlineResponse20055
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }

    /**
    * Conversation Suggests
    * This endpoint returns suggestions for things the user can say or ask the chatbot.
    * @param query A (partial) query from the user. The endpoint will return if it matches topics it can talk about. 
    * @param number The number of suggestions to return (between 1 and 25). (optional)
    * @return InlineResponse20057
    */
    @Suppress("UNCHECKED_CAST")
    fun getConversationSuggests(query: kotlin.String, number: Int?) : InlineResponse20057 {
        val localVariableBody: kotlin.Any? = null
        val localVariableQuery: MultiValueMap = mapOf("query" to listOf("$query"), "number" to listOf("$number"))
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/food/converse/suggest",
            query = localVariableQuery,
            headers = localVariableHeaders
        )
        val response = request<InlineResponse20057>(
            localVariableConfig,
            localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as InlineResponse20057
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }

    /**
    * Random Food Trivia
    * Returns random food trivia.
    * @return InlineResponse20055
    */
    @Suppress("UNCHECKED_CAST")
    fun getRandomFoodTrivia() : InlineResponse20055 {
        val localVariableBody: kotlin.Any? = null
        val localVariableQuery: MultiValueMap = mapOf()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/food/trivia/random",
            query = localVariableQuery,
            headers = localVariableHeaders
        )
        val response = request<InlineResponse20055>(
            localVariableConfig,
            localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as InlineResponse20055
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }

    /**
    * Image Analysis by URL
    * Analyze a food image. The API tries to classify the image, guess the nutrition, and find a matching recipes.
    * @param imageUrl The URL of the image to be analyzed. 
    * @return InlineResponse20049
    */
    @Suppress("UNCHECKED_CAST")
    fun imageAnalysisByURL(imageUrl: kotlin.String) : InlineResponse20049 {
        val localVariableBody: kotlin.Any? = null
        val localVariableQuery: MultiValueMap = mapOf("imageUrl" to listOf("$imageUrl"))
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/food/images/analyze",
            query = localVariableQuery,
            headers = localVariableHeaders
        )
        val response = request<InlineResponse20049>(
            localVariableConfig,
            localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as InlineResponse20049
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }

    /**
    * Image Classification by URL
    * Classify a food image.
    * @param imageUrl The URL of the image to be classified. 
    * @return InlineResponse20048
    */
    @Suppress("UNCHECKED_CAST")
    fun imageClassificationByURL(imageUrl: kotlin.String) : InlineResponse20048 {
        val localVariableBody: kotlin.Any? = null
        val localVariableQuery: MultiValueMap = mapOf("imageUrl" to listOf("$imageUrl"))
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/food/images/classify",
            query = localVariableQuery,
            headers = localVariableHeaders
        )
        val response = request<InlineResponse20048>(
            localVariableConfig,
            localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as InlineResponse20048
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }

    /**
    * Search All Food
    * Search all food content with one call. That includes recipes, grocery products, menu items, simple foods (ingredients), and food videos.
    * @param query The search query. 
    * @param offset The number of results to skip (between 0 and 900). (optional)
    * @param number The maximum number of items to return (between 1 and 100). Defaults to 10. (optional, default to 10)
    * @return InlineResponse20053
    */
    @Suppress("UNCHECKED_CAST")
    fun searchAllFood(query: kotlin.String, offset: kotlin.Int?, number: kotlin.Int?) : InlineResponse20053 {
        val localVariableBody: kotlin.Any? = null
        val localVariableQuery: MultiValueMap = mapOf("query" to listOf("$query"), "offset" to listOf("$offset"), "number" to listOf("$number"))
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/food/search",
            query = localVariableQuery,
            headers = localVariableHeaders
        )
        val response = request<InlineResponse20053>(
            localVariableConfig,
            localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as InlineResponse20053
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }

    /**
    * Search Custom Foods
    * Search custom foods in a user&#39;s account.
    * @param username The username. 
    * @param hash The private hash for the username. 
    * @param query The (natural language) search query. (optional)
    * @param offset The number of results to skip (between 0 and 900). (optional)
    * @param number The maximum number of items to return (between 1 and 100). Defaults to 10. (optional, default to 10)
    * @return InlineResponse20029
    */
    @Suppress("UNCHECKED_CAST")
    fun searchCustomFoods(username: kotlin.String, hash: kotlin.String, query: kotlin.String?, offset: kotlin.Int?, number: kotlin.Int?) : InlineResponse20029 {
        val localVariableBody: kotlin.Any? = null
        val localVariableQuery: MultiValueMap = mapOf("query" to listOf("$query"), "username" to listOf("$username"), "hash" to listOf("$hash"), "offset" to listOf("$offset"), "number" to listOf("$number"))
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/food/customFoods/search",
            query = localVariableQuery,
            headers = localVariableHeaders
        )
        val response = request<InlineResponse20029>(
            localVariableConfig,
            localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as InlineResponse20029
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }

    /**
    * Search Food Videos
    * Find recipe and other food related videos.
    * @param query The (natural language) search query. (optional)
    * @param type The type of the recipes. See a full list of supported meal types. (optional)
    * @param cuisine The cuisine(s) of the recipes. One or more, comma separated. See a full list of supported cuisines. (optional)
    * @param diet The diet for which the recipes must be suitable. See a full list of supported diets. (optional)
    * @param includeIngredients A comma-separated list of ingredients that the recipes should contain. (optional)
    * @param excludeIngredients A comma-separated list of ingredients or ingredient types that the recipes must not contain. (optional)
    * @param minLength Minimum video length in seconds. (optional)
    * @param maxLength Maximum video length in seconds. (optional)
    * @param offset The number of results to skip (between 0 and 900). (optional)
    * @param number The maximum number of items to return (between 1 and 100). Defaults to 10. (optional, default to 10)
    * @return InlineResponse20054
    */
    @Suppress("UNCHECKED_CAST")
    fun searchFoodVideos(query: kotlin.String?, type: kotlin.String?, cuisine: kotlin.String?, diet: kotlin.String?, includeIngredients: kotlin.String?, excludeIngredients: kotlin.String?, minLength: Float?, maxLength: Float?, offset: kotlin.Int?, number: kotlin.Int?) : InlineResponse20054 {
        val localVariableBody: kotlin.Any? = null
        val localVariableQuery: MultiValueMap = mapOf("query" to listOf("$query"), "type" to listOf("$type"), "cuisine" to listOf("$cuisine"), "diet" to listOf("$diet"), "includeIngredients" to listOf("$includeIngredients"), "excludeIngredients" to listOf("$excludeIngredients"), "minLength" to listOf("$minLength"), "maxLength" to listOf("$maxLength"), "offset" to listOf("$offset"), "number" to listOf("$number"))
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/food/videos/search",
            query = localVariableQuery,
            headers = localVariableHeaders
        )
        val response = request<InlineResponse20054>(
            localVariableConfig,
            localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as InlineResponse20054
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }

    /**
    * Search Site Content
    * Search spoonacular&#39;s site content. You&#39;ll be able to find everything that you could also find using the search suggestions on spoonacular.com. This is a suggest API so you can send partial strings as queries.
    * @param query The query to search for. You can also use partial queries such as \&quot;spagh\&quot; to already find spaghetti recipes, articles, grocery products, and other content. 
    * @return InlineResponse20052
    */
    @Suppress("UNCHECKED_CAST")
    fun searchSiteContent(query: kotlin.String) : InlineResponse20052 {
        val localVariableBody: kotlin.Any? = null
        val localVariableQuery: MultiValueMap = mapOf("query" to listOf("$query"))
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/food/site/search",
            query = localVariableQuery,
            headers = localVariableHeaders
        )
        val response = request<InlineResponse20052>(
            localVariableConfig,
            localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as InlineResponse20052
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }

    /**
    * Talk to Chatbot
    * This endpoint can be used to have a conversation about food with the spoonacular chatbot. Use the \&quot;Get Conversation Suggests\&quot; endpoint to show your user what he or she can say.
    * @param text The request / question / answer from the user to the chatbot. 
    * @param contextId An arbitrary globally unique id for your conversation. The conversation can contain states so you should pass your context id if you want the bot to be able to remember the conversation. (optional)
    * @return InlineResponse20056
    */
    @Suppress("UNCHECKED_CAST")
    fun talkToChatbot(text: kotlin.String, contextId: kotlin.String?) : InlineResponse20056 {
        val localVariableBody: kotlin.Any? = null
        val localVariableQuery: MultiValueMap = mapOf("text" to listOf("$text"), "contextId" to listOf("$contextId"))
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/food/converse",
            query = localVariableQuery,
            headers = localVariableHeaders
        )
        val response = request<InlineResponse20056>(
            localVariableConfig,
            localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as InlineResponse20056
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }

}
