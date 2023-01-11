package hm.binkley

enum class Suit(val symbol: String) {
    SPADES("S"),
    HEARTS("H"),
    DIAMONDS("D"),
    CLUBS("C"),
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
    TWO("2");

    infix fun of(suit: Suit) = Card(suit, this)
}

data class Card(val suit: Suit, val rank: Rank) : Comparable<Card> {
    override fun compareTo(other: Card) = rank.compareTo(other.rank)

    override fun toString() = suit.symbol + rank.symbol
}

class Hand(
    first: Card,
    second: Card,
    third: Card,
    fourth: Card,
    fifth: Card,
) : Comparable<Hand> {
    private val ordered = sortedSetOf(first, second, third, fourth, fifth)

    // TODO: Add more tests to flesh out comparing hands
    override operator fun compareTo(other: Hand) =
        ordered.first().compareTo(other.ordered.first())

    override fun toString() = ordered.joinToString(" ")
}
