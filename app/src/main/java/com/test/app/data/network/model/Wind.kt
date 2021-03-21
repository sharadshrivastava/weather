package com.test.app.data.network.model

import com.squareup.moshi.Json

data class Wind(

	@Json(name="speed")
	val speed: Double? = null
)