package hm.binkley

import hm.binkley.Rank.ACE
import hm.binkley.Rank.FIVE
import hm.binkley.Rank.FOUR
import hm.binkley.Rank.KING
import hm.binkley.Rank.TEN
import hm.binkley.Rank.THREE
import hm.binkley.Suit.CLUBS
import hm.binkley.Suit.DIAMONDS
import hm.binkley.Suit.HEARTS
import hm.binkley.Suit.SPADES
import io.kotest.matchers.comparables.shouldBeGreaterThan
import org.junit.jupiter.api.Test

class PokerTest {
    @Test
    fun `should rank one of a kind`() {
        val handA = Hand(
            ACE of SPADES,
            TEN of HEARTS,
            FIVE of DIAMONDS,
            FOUR of CLUBS,
            THREE of CLUBS
        )
        val handB = Hand(
            KING of SPADES,
            TEN of HEARTS,
            FIVE of DIAMONDS,
            FOUR of CLUBS,
            THREE of CLUBS
        )

        handA shouldBeGreaterThan handB
    }
}
