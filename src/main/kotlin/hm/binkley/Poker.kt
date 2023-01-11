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
    ACE("A");

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

    override fun toString() = ordered.toString()
}
