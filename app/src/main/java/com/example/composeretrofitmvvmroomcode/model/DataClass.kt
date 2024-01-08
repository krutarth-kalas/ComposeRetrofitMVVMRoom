package com.example.composeretrofitmvvmroomcode.model

data class DataClass(
    val name : String,
    val email : String
)

fun dummyData() : List<DataClass>{
    return listOf(
        DataClass("jfdhdfhfdhffhdfdhfhfhfdfdhfdhfdfhdfdhfhfdfhdfhdhfdhfdfdhfdhfdhfdhfdh","fggfg"),
        DataClass("jfdhdfhfdhffhdfdhfhfhfdfdhfdhfdfhdfdhfhfdfhdfhdhfdhfdfdhfdhfdhfdhfdh","fggfg")
    )
}