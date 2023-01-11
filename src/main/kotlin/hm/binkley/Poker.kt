package hm.binkley

enum class Suit(val symbol: String) {
    CLUBS("C"),
    DIAMONDS("D"),
    HEARTS("H"),
    SPADES("S"),
}

enum class Rank(val symbol: String) {
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JACK("J"),
    QUEEN("Q"),
    KING("K"),
    ACE("A"),
    ;

    fun of(suit: Suit) = Card(suit, this)
}

data class Card(val suit: Suit, val rank: Rank) : Comparable<Card> {
    override fun compareTo(other: Card): Int {
        TODO("Not yet implemented")
    }

    override fun toString() = suit.symbol + rank.symbol
}

data class Hand(
    val first: Card,
    val second: Card,
    val third: Card,
    val fourth: Card,
    val fifth: Card,
) {
    operator fun compareTo(other: Hand): Int {
        val cardsA = sortedSetOf(first, second, third, fourth, fifth)
        val cardsB = sortedSetOf(
            other.first,
            other.second,
            other.third,
            other.fourth,
            other.fifth
        )
        return cardsA.first().compareTo(cardsB.first())
    }

    override fun toString() = "$first $second $third $fourth $fifth"
}
