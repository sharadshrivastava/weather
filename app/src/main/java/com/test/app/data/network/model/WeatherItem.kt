package com.test.app.data.network.model

import com.squareup.moshi.Json

data class WeatherItem(

	@Json(name="description")
	val description: String? = null
)