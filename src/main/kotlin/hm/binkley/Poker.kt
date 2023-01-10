package hm.binkley

enum class Suit(val symbol: String) {
    SPADES("S"),
    HEARTS("H"),
    DIAMONDS("D"),
    CLUBS("C");
}

enum class Rank(val symbol: String) {
    ACE("A"),
    KING("K"),
    QUEEN("Q"),
    JACK("J"),
    TEN("10"),
    NINE("9"),
    EIGHT("8"),
    SEVEN("7"),
    SIX("6"),
    FIVE("5"),
    FOUR("4"),
    THREE("3"),
    TWO("2"),
}

data class Card(val suit: Suit, val rank: Rank) {
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
        TODO("IMPLEMENT")
    }

    override fun toString() = "$first $second $third $fourth $fifth"
}
