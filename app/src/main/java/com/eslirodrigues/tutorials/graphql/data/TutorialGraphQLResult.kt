package com.eslirodrigues.tutorials.graphql.data


sealed class TutorialGraphQLResult<out R> {
    data class Success<out T>(val data: T) : TutorialGraphQLResult<T>()
    data class Error(val exception: Throwable) : TutorialGraphQLResult<Nothing>()
    data object Loading : TutorialGraphQLResult<Nothing>()
}
