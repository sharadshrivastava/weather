package com.test.app.data.network.model

import com.squareup.moshi.Json

data class Response(

	@Json(name="timezone")
	val timezone: Int? = null,

	@Json(name="main")
	val main: Main? = null,

	@Json(name="wind")
	val wind: Wind? = null,

	@Json(name="weather")
	val weather: List<WeatherItem?>? = null,

	@Json(name="sys")
	val sys: Sys? = null,

	@Json(name="name")
	val name: String? = null
)