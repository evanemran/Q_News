package com.evanemran.qnewsbd.models

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "rss", strict = false)
class NewsApiResponse @JvmOverloads constructor(
    @field: Element(name = "channel")
    var channel: NewsChannel? = null
)

@Root(name = "channel", strict = false)
class NewsChannel @JvmOverloads constructor(
    @field: ElementList(inline = true)
    var itemList: List<NewsItem>? = null
)

@Root(name = "item", strict = false)
class NewsItem @JvmOverloads constructor(
    @field: Element(name = "title")
    var title: String = "",
    @field: Element(name = "link")
    var link: String = "",
    @field: Element(name = "description", required = false)
    var description: String = "",
    @field: Element(name = "pubDate", required = false)
    var pubDate: String = "",
    @field: Element(name = "media:content", required = false)
    var content: NewsMedia? = null,
//    @field: Element(name = "description", required = false)
//    var description: String = "",
)

//class NewsMedia @JvmOverloads constructor(
//    @field:Element(name = "medium")
//    var medium: String = "",
//    @field:Element(name = "width")
//    var width: String = "",
//    @field:Element(name = "height")
//    var height: String = "",
//    @field:Element(name = "url")
//    var url: String = "",
//)

@Root(name = "item", strict = false)
class NewsMedia @JvmOverloads constructor(
    @Attribute(name = "url")
    var url: String = ""
)
