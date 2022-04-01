package com.manriquetavi.hunterapp.data.paging_source

import androidx.paging.PagingSource.*
import com.manriquetavi.hunterapp.data.remote.FakeHxHApi
import com.manriquetavi.hunterapp.data.remote.HxHApi
import com.manriquetavi.hunterapp.domain.model.Hunter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class SearchHuntersSourceTest {
    private lateinit var hxHApi: HxHApi
    private lateinit var hunters: List<Hunter>

    @Before
    fun setup() {
        hxHApi = FakeHxHApi()
        hunters = listOf(
            Hunter(
                id = 1,
                name = "Gon Freecss",
                image = "/images/gonfreecss.png",
                about = "Gon Freecss (ゴン゠フリークス, Gon Furīkusu) is a Rookie Hunter and the son of Ging Freecss. Finding his father is Gon's motivation in becoming a Hunter.\n" +
                        "He has been the main protagonist for most of the series, having said role in the Hunter Exam, Zoldyck Family, Heavens Arena, Greed Island, and Chimera Ant arcs. He was also the deuteragonist of the Yorknew City arc.",
                rating = 5.0,
                power = 90,
                month = "May",
                day = "5",
                family = listOf(
                    "Ging Freecss",
                    "Mito Freecss",
                    "Abe",
                    "Don Freecss"
                ),
                abilities = listOf(
                    "Jajanken"
                ),
                nenTypes = listOf(
                    "Enhancement",
                    "Transmutation",
                    "Emission"
                )
            ),
            Hunter(
                id = 2,
                name = "Killua Zoldyck",
                image = "/images/killuazoldyck.png",
                about = "Killua Zoldyck (キルア゠ゾルディック, Kirua Zorudikku) is the third child of Silva and Kikyo Zoldyck and the heir of the Zoldyck Family, until he runs away from home and becomes a Rookie Hunter. He is the best friend of Gon Freecss and is currently traveling with Alluka Zoldyck.\n" +
                        "He has served as the deuteragonist for the series, having said role in the Heavens Arena, Greed Island, and Chimera Ant arcs. He was the main protagonist of the 13th Hunter Chairman Election arc. He also was the tritagonist of the Hunter Exam arc and the Yorknew City arc.",
                rating = 5.0,
                power = 90,
                month = "July",
                day = "7",
                family = listOf(
                    "Silva Zoldyck",
                    "Kikyo Zoldyck",
                    "Illumi Zoldyck",
                    "Milluki Zoldyck",
                    "Alluka Zoldyck",
                    "Kalluto Zoldyck",
                    "Zeno Zoldyck",
                    "Maha Zoldyck",
                    "Zigg Zoldyck"
                ),
                abilities = listOf(
                    "Lightning Palm",
                    "Thunderbolt",
                    "Godspeed"
                ),
                nenTypes = listOf(
                    "Transmutation",
                    "Emission"
                )
            ),
            Hunter(
                id = 3,
                name = "Kurapika",
                image = "/images/kurapika.jpg",
                about = "Kurapika (クラピカ, Kurapika) is the last survivor of the Kurta Clan. He is a Blacklist Hunter and the current leader of the organization founded by Light Nostrade. He is a member of the Zodiacs with the codename \"Rat\" (子ね, Ne). His goal is to avenge his clan and recover the remaining Scarlet Eyes.\n" +
                        "He takes the role of the main protagonist in the Yorknew City and Succession Contest arcs. He was also the deuteragonist of the Hunter Exam arc.",
                rating = 5.0,
                power = 90,
                month = "April",
                day = "4",
                family = listOf(
                    "Unnamed Father",
                    "Unnamed Mother"
                ),
                abilities = listOf(
                    "Holy Chain",
                    "Steal Chain",
                    "Chain Jail",
                    "Dowsing Chain",
                    "Judgment Chain",
                    "Emperor Time",
                    "Stealth Dolphin"
                ),
                nenTypes = listOf(
                    "Conjuration",
                    "Specialization"
                )
            ),
        )
    }

    @Test
    fun `Searched api with existing hunter name, expect single hunter result, assert LoadResult_Page`() =
        runBlockingTest {
            val hunterSource = SearchHuntersSource(hxHApi = hxHApi, query = "Gon")
            assertEquals<LoadResult<Int, Hunter>>(
                expected = LoadResult.Page(
                    data = listOf(hunters.first()),
                    prevKey = null,
                    nextKey = null
                ),
                actual = hunterSource.load(
                    LoadParams.Refresh(
                        key = null,
                        loadSize = 3,
                        placeholdersEnabled = false
                    )
                )
            )
        }

    @Test
    fun `Searched api with existing hunter name, expect multiple hunter result, assert LoadResult_Page`() =
        runBlockingTest {
            val hunterSource = SearchHuntersSource(hxHApi = hxHApi, query = "O")
            assertEquals<LoadResult<Int, Hunter>>(
                expected = LoadResult.Page(
                    data = listOf(hunters.first(), hunters[1]),
                    prevKey = null,
                    nextKey = null
                ),
                actual = hunterSource.load(
                    LoadParams.Refresh(
                        key = null,
                        loadSize = 3,
                        placeholdersEnabled = false
                    )
                )
            )
        }

    @Test
    fun `Searched api with empty hunter name, assert empty hunters list and LoadResult_Page`() =
        runBlockingTest {
            val hunterSource = SearchHuntersSource(hxHApi = hxHApi, query = "")
            val loadResult = hunterSource.load(
                LoadParams.Refresh(
                    key = null,
                    loadSize = 3,
                    placeholdersEnabled = false
                )
            )

            val result = hxHApi.searchHunters("").hunters

            assertTrue { result.isEmpty() }
            assertTrue { loadResult is LoadResult.Page }
        }

    @Test
    fun `Searched api with non_existing hunter name, assert empty hunters list and LoadResult_Page`() =
        runBlockingTest {
            val hunterSource = SearchHuntersSource(hxHApi = hxHApi, query = "Unknown")
            val loadResult = hunterSource.load(
                LoadParams.Refresh(
                    key = null,
                    loadSize = 3,
                    placeholdersEnabled = false
                )
            )

            val result = hxHApi.searchHunters("Unknown").hunters

            assertTrue { result.isEmpty() }
            assertTrue { loadResult is LoadResult.Page }
        }
}