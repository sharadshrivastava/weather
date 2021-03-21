package com.test.app.data.network.model

import com.squareup.moshi.Json

data class PostItem(

	@Json(name="title")
	val title: String? = null,

	@Json(name="datetime")
	val datetime: Int? = null,

	@Json(name="images_count")
	val imagesCount: Int? = null,

	@Json(name="points")
	val points: Int? = null,

	@Json(name="score")
	val score: Int? = null,

	@Json(name="topic_id")
	val topicId: Int? = null

)