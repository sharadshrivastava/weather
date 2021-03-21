package com.test.app.data.network.model

import com.squareup.moshi.Json

data class Main(

	@Json(name="temp")
	val temp: Double? = null,

	@field:Json(name="temp_min")
	val tempMin: Double? = null,

	@Json(name="humidity")
	val humidity: Int? = null,

	@Json(name="pressure")
	val pressure: Int? = null,

	@field:Json(name="feels_like")
	val feelsLike: Double? = null,

	@field:Json(name="temp_max")
	val tempMax: Double? = null
)