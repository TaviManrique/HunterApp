package com.manriquetavi.hunterapp.data.remote

import com.manriquetavi.hunterapp.domain.model.ApiResponse
import com.manriquetavi.hunterapp.domain.model.Hunter
import java.io.IOException

class FakeHxHApi2: HxHApi {

    private val hunters: Map<Int, List<Hunter>> by lazy {
        mapOf(
            1 to page1,
            2 to page2,
            3 to page3,
            4 to page4,
            5 to page5,
        )
    }
    private var page1 = listOf(
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
    private var page2 = listOf(
        Hunter(
            id = 4,
            name = "Leorio Paradinight",
            image = "/images/leorioparadinight.jpg",
            about = "Leorio Paradinight (レオリオ゠パラディナｲﾄ, Reorio Paradinaito) is a Rookie Hunter and a member of the Zodiacs with the codename \"Boar\" (亥い, I). He is currently a medical student, with a goal to become a doctor.",
            rating = 5.0,
            power = 90,
            month = "March",
            day = "3",
            family = listOf(
                ""
            ),
            abilities = listOf(
                "Remote Punch"
            ),
            nenTypes = listOf(
                "Emission"
            )
        ),
        Hunter(
            id = 5,
            name = "Hisoka Morrow",
            image = "/images/hisokamorrow.jpg",
            about = "Hisoka Morow (ヒソカ゠モロウ, Hisoka Morou) is a Hunter and former member #4 of the Phantom Troupe; his physical strength ranked third in the group. He is always in search for strong opponents, and would spare those who have great potential, such as Gon and Killua in order for them to get strong enough to actually challenge him. He originally served as the primary antagonist of the Hunter Exam arc and the Heavens Arena arc, before becoming a supporting character during the Yorknew City arc and Greed Island arc. During the 13th Hunter Chairman Election arc, he reprises his role as a secondary antagonist.",
            rating = 5.0,
            power = 98,
            month = "June",
            day = "6",
            family = listOf(
                ""
            ),
            abilities = listOf(
                "Bungee Gum",
                "Texture Surprise"
            ),
            nenTypes = listOf(
                "Transmutation",
                "Conjuration"
            )
        ),
        Hunter(
            id = 6,
            name = "Chrollo Lucilfer",
            image = "/images/chrollolucilfer.jpg",
            about = "Chrollo Lucilfer (クロロ゠ルシルフル, Kuroro Rushirufuru) is the founder, leader, and member #0 of the Phantom Troupe. His physical strength ranked seventh among the group.",
            rating = 5.0,
            power = 98,
            month = "Unknown",
            day = "Unknown",
            family = listOf(
                ""
            ),
            abilities = listOf(
                "Skill Hunter",
                "Double Face"
            ),
            nenTypes = listOf(
                "Specialization",
                "Conjuration",
                "Manipulation"
            )
        ),
    )
    private var page3 = listOf(
        Hunter(
            id = 7,
            name = "Kite",
            image = "/images/kite.png",
            about = "Kite (カイト, Kaito) was a Hunter and Ging Freecss' student. After their death, they were reborn as a Chimera Ant and Meruem's twin sibling. Colt named them Reina (レイナ, Reina), after his late sister, but they took to calling themself Kite once again.",
            rating = 5.0,
            power = 90,
            month = "Unknown",
            day = "Unknown",
            family = listOf(
                ""
            ),
            abilities = listOf(
                "Crazy Slots"
            ),
            nenTypes = listOf(
                "Conjuration"
            )
        ),
        Hunter(
            id = 8,
            name = "Kite (Chimera Ant)",
            image = "/images/kitechimeraant.jpg",
            about = "Kite (カイト, Kaito) was a Hunter and Ging Freecss' student. After their death, they were reborn as a Chimera Ant and Meruem's twin sibling. Colt named them Reina (レイナ, Reina), after his late sister, but they took to calling themself Kite once again.",
            rating = 5.0,
            power = 98,
            month = "Unknown",
            day = "Unknown",
            family = listOf(
                "The Queen",
                "Meruem"
            ),
            abilities = listOf(
                ""
            ),
            nenTypes = listOf(
                ""
            )
        ),
        Hunter(
            id = 9,
            name = "Meruem",
            image = "/images/meruem.jpg",
            about = "Meruem (メルエム, Meruemu) was the most powerful offspring of the Chimera Ant Queen. He was known as the \"King\" (王おう, Ō) of the Chimera Ants,[3] and served as the main antagonist of the Chimera Ant arc.",
            rating = 5.0,
            power = 100,
            month = "Unknown",
            day = "Unknown",
            family = listOf(
                "Chimera Ant Queen",
                "Kite"
            ),
            abilities = listOf(
                "Aura Synthesis",
                "Metamorphosis",
                "Rage Blast",
                "Photon"
            ),
            nenTypes = listOf(
                "Specialization",
                "Emission",
                "Transmutation"
            )
        ),
    )
    private var page4 = listOf(
        Hunter(
            id = 10,
            name = "Illumi Zoldyck",
            image = "/images/illumizoldyck.jpg",
            about = "Illumi Zoldyck (イルミ゠ゾルディック, Irumi Zorudikku) is the eldest child of Silva and Kikyo Zoldyck. During the 287th Hunter Exam, he appeared under the guise of Gittarackur (ギタラクル, Gitarakuru). At Hisoka's request, Illumi joins the Phantom Troupe as Uvogin's replacement, becoming Troupe member #11. He served as a secondary antagonist of the Hunter Exam arc and the primary one of the 13th Hunter Chairman Election arc.",
            rating = 5.0,
            power = 98,
            month = "Unknown",
            day = "Unknown",
            family = listOf(
                "Silva Zoldyck",
                "Kikyo Zoldyck",
                "Milluki Zoldyck",
                "Killua Zoldyck",
                "Alluka Zoldyck",
                "Kalluto Zoldyck",
                "Zeno Zoldyck",
                "Maha Zoldyck",
                "Zigg Zoldyck"
            ),
            abilities = listOf(
                "Pin and Needle Manipulation",
                "Needle People"
            ),
            nenTypes = listOf(
                "Manipulation"
            )
        ),
        Hunter(
            id = 11,
            name = "Isaac Netero",
            image = "/images/isaacnetero.jpg",
            about = "Isaac Netero (アイザック゠ネテロ, Aizakku Netero) was the 12th Chairman of the Hunter Association and the Head of the Exam Commission.[6] In his youth, he was extolled as the most powerful Nen user in the world, and retained dreadful strength even in his old age.",
            rating = 5.0,
            power = 100,
            month = "Unknown",
            day = "Unknown",
            family = listOf(
                "Beyond Netero"
            ),
            abilities = listOf(
                "100-Type Guanyin",
                "Bodhisattva"
            ),
            nenTypes = listOf(
                "Enhancement",
                "Manipulation",
                "Emission",
                "Transmutation"

            )
        ),
        Hunter(
            id = 12,
            name = "Hanzo",
            image = "/images/hanzo.png",
            about = "Hanzo (ハンゾー, Hanzō) is a Cloud-Hidden style jonin from Jappon. During the 287th Hunter Exam, he was applicant #294 and decided to become a Hunter in order to find the elusive Hermit's Scroll. He is currently one of the bodyguards of Prince Marayam Hui Guo Rou.",
            rating = 5.0,
            power = 100,
            month = "Unknown",
            day = "Unknown",
            family = listOf(
                ""
            ),
            abilities = listOf(
                "Hanzo Skill 4"
            ),
            nenTypes = listOf(
                "Unknown"
            )
        ),
    )
    private var page5 = listOf(
        Hunter(
            id = 13,
            name = "Knuckle Bine",
            image = "/images/knucklebine.jpg",
            about = "Knuckle Bine (ナックル゠バイン, Nakkuru Bain) is a Beast Hunter and Morel's apprentice.",
            rating = 5.0,
            power = 90,
            month = "Unknown",
            day = "Unknown",
            family = listOf(
                ""
            ),
            abilities = listOf(
                "Hakoware"
            ),
            nenTypes = listOf(
                "Emission",
                "Manipulation"
            )
        ),
        Hunter(
            id = 14,
            name = "Morel Mackernasey",
            image = "/images/morelmackernasey.jpg",
            about = "Morel Mackernasey (モラウ゠マッカーナーシ, Morau Makkānāshi) is a Single-Star Sea Hunter who uses a giant smoking pipe and smoke as weapons, and was one of the members of the Chimera Ant Extermination Team. He is also the master of both Knuckle Bine and Shoot McMahon.",
            rating = 5.0,
            power = 90,
            month = "Unknown",
            day = "Unknown",
            family = listOf(
                ""
            ),
            abilities = listOf(
                "Deep Purple",
                "Smoky Jail"
            ),
            nenTypes = listOf(
                "Manipulation",
                "Emission",
                "Transmutation"
            )
        ),
        Hunter(
            id = 15,
            name = "Neferpitou",
            image = "/images/neferpitou.jpg",
            about = "Neferpitou (ネフェルピトー, Neferupitō), nicknamed Pitou (ピトー, Pitō), was a cat-humanoid Chimera Ant and the firstborn of the Chimera Ant King's three Royal Guards.",
            rating = 5.0,
            power = 100,
            month = "Unknown",
            day = "Unknown",
            family = listOf(
                "Chimera Ant Queen"
            ),
            abilities = listOf(
                "Terpsichora",
                "Doctor Blythe",
                "Puppeteering"
            ),
            nenTypes = listOf(
                "Specialization",
                "Manipulation"
            )
        ),
    )

    fun clearData() {
        page1 = emptyList()
    }

    private var exception = false

    fun addException() {
        exception = true
    }

    override suspend fun getAllHunters(page: Int): ApiResponse {
        if(exception) {
            throw IOException()
        }
        require(page in 1..5)
        return ApiResponse(
            success = true,
            message = "Ok",
            prevPage = calculatePage(page = page)["prevPage"],
            nextPage = calculatePage(page = page)["nextPage"],
            hunters = hunters[page]!!
        )
    }

    override suspend fun searchHunters(name: String): ApiResponse {
        return ApiResponse(
            success = false
        )
    }

    private fun calculatePage(page: Int): Map<String, Int?> {
        if(page1.isEmpty()) {
            return mapOf("prevPage" to null, "nextPage" to null)
        }
        var prevPage: Int? = page
        var nextPage: Int? = page
        if (page in 1..4) {
            nextPage = nextPage?.plus(1)
        }
        if (page in 2..5) {
            prevPage = prevPage?.minus(1)
        }
        if (page == 1) {
            prevPage = null
        }
        if (page == 5) {
            nextPage = null
        }

        return mapOf("prevPage" to prevPage, "nextPage" to nextPage)
    }
}