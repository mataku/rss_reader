package com.example.rssreader.model.api.entity

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.NamespaceList
import org.simpleframework.xml.Root

@NamespaceList(
    Namespace(reference = "http://www.w3.org/1999/02/22-rdf-syntax-ns#", prefix = "rdf"),
    Namespace(reference = "http://www.hatena.ne.jp/info/xmlns#", prefix = "hatena")
)
@Root(name = "RDF", strict = false)
data class HatenaRss(
    @set:ElementList(name = "item", inline = true)
    @get:ElementList(name = "item", inline = true)
    var items: MutableList<HatenaRssItem> = mutableListOf()
)