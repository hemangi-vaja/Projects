package com.aspl.technometricspracticaltask

import com.google.gson.annotations.SerializedName

class ApiResponse<T>(
//    @SerializedName("status") val status: Boolean,
    @SerializedName("results") val results: T,
//    @SerializedName("message") val message: String
)
