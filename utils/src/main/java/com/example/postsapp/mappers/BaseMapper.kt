package com.example.postsapp.mappers

interface BaseMapper<FirstModel, SecondModel> {

  fun map(model: FirstModel): SecondModel
}