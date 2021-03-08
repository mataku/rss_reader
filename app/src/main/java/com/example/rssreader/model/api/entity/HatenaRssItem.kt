package com.example.rssreader.model.api.entity

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "item", strict = false)
data class HatenaRssItem(
    @set:Element(name = "title")
    @get:Element(name = "title")
    var title: String? = null,

    @set:Element(name = "link")
    @get:Element(name = "link")
    var link: String? = null,

    @set:Element(name = "imageurl", required = false)
    @get:Element(name = "imageurl", required = false)
    var imageUrl: String? = null
)