package hm.binkley

import hm.binkley.Rank.ACE
import hm.binkley.Rank.EIGHT
import hm.binkley.Rank.FIVE
import hm.binkley.Rank.FOUR
import hm.binkley.Rank.JACK
import hm.binkley.Rank.KING
import hm.binkley.Rank.NINE
import hm.binkley.Rank.QUEEN
import hm.binkley.Rank.SEVEN
import hm.binkley.Rank.SIX
import hm.binkley.Rank.TEN
import hm.binkley.Rank.THREE
import hm.binkley.Suit.CLUBS
import hm.binkley.Suit.DIAMONDS
import hm.binkley.Suit.HEARTS
import hm.binkley.Suit.SPADES
import io.kotest.matchers.comparables.shouldBeLessThan
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PokerTest {
    @Test
    fun `cards should display themselves`() {
        "${QUEEN of SPADES}" shouldBe "SQ"
    }

    @Test
    fun `hands should display themselves`() {
        val hand = Hand(
            JACK of SPADES,
            NINE of HEARTS,
            EIGHT of DIAMONDS,
            SEVEN of CLUBS,
            SIX of CLUBS
        )

        "$hand" shouldBe "SJ H9 D8 C7 C6"
    }

    @Test
    fun `hands should rank one of a kind`() {
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

        handA shouldBeLessThan handB
    }
}
