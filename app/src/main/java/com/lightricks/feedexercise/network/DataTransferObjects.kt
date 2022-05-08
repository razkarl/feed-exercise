package com.lightricks.feedexercise.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * This data class is a single template metadata.
 * See https://assets.swishvideoapp.com/Android/demo/feed.json for more details.
 */
@JsonClass(generateAdapter = true)
data class TemplateMetadata(
    @Json(name = "configuration") val configuration: String,
    @Json(name = "id") val id: String,
    @Json(name = "isNew") val isNew: Boolean,
    @Json(name = "isPremium") val isPremium: Boolean,
    @Json(name = "templateCategories") val templateCategories: List<String>,
    @Json(name = "templateName") val templateName: String,
    @Json(name = "templateThumbnailURI") val templateThumbnailURI: String
)

/**
 * This data class is a list of templates metadata.
 * See https://assets.swishvideoapp.com/Android/demo/feed.json for more details.
 */
@JsonClass(generateAdapter = true)
data class TemplatesMetadata(
    @Json(name = "templatesMetadata") val templates: List<TemplateMetadata>
)