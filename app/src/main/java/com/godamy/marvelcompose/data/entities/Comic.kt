package com.godamy.marvelcompose.data.entities

data class Comic(
    override val id: Int,
    override val title: String,
    override val description: String,
    override val thumbnail: String,
    val format: Format,
    override val references: List<ReferenceList>,
    override val urls: List<Url>
) : MarvelItem {
    enum class Format(val key: String) {
        COMIC("Comic"),
        MAGAZINE("Magazine"),
        TRADE_PAPERBACK("Trade Paperback"),
        HARDCOVER("Hardcover"),
        DIGEST("Digest"),
        GRAPHIC_NOVEL("Graphic Novel"),
        DIGITAL_COMIC("Digital Comic"),
        INFINITE_COMIC("Infinite Comic")
    }
}
