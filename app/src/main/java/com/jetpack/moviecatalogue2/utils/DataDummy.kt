package com.jetpack.moviecatalogue2.utils

import com.jetpack.moviecatalogue2.data.source.local.entity.MovieEntity
import com.jetpack.moviecatalogue2.data.source.local.entity.TvEntity
import com.jetpack.moviecatalogue2.data.source.remote.response.MovieResponse

object DataDummy {

    fun generateRemoteDummyMovies(): List<MovieResponse> {
        val movies = ArrayList<MovieResponse>()

        movies.add(
            MovieResponse(
                "Mortal Kombat",
                "movie",
                460465,
                7.8,
                "/2xmx8oPlbDaxTjHsIOZvOt5L3aJ.jpg",
                "en",
                2126,
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe."
            )
        )

        movies.add(
            MovieResponse(
                "Godzilla vs. Kong",
                "movie",
                399566,
                8.2,
                "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                "en",
                4886,
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages."
            )
        )

        movies.add(
            MovieResponse(
                "Nobody",
                "movie",
                615457,
                8.5,
                "/oBgWY00bEFeZ9N25wWVyuQddbAo.jpg",
                "en",
                735,
                "Hutch Mansell, a suburban dad, overlooked husband, nothing neighbor — a \"nobody.\" When two thieves break into his home one night, Hutch's unknown long-simmering rage is ignited and propels him on a brutal path that will uncover dark secrets he fought to leave behind."
            )
        )

        movies.add(
            MovieResponse(
                "100% Wolf",
                "movie",
                520946,
                8.5,
                "/2VrvxK4yxNCU6KVgo5TADJeBEQu.jpg",
                "en",
                735,
                "Hutch Mansell, a suburban dad, overlooked husband, nothing neighbor — a \"nobody.\" When two thieves break into his home one night, Hutch's unknown long-simmering rage is ignited and propels him on a brutal path that will uncover dark secrets he fought to leave behind."
            )
        )

        movies.add(
            MovieResponse(
                "The Marksman",
                "movie",
                634528,
                7.1,
                "/6vcDalR50RWa309vBH1NLmG2rjQ.jpg",
                "en",
                157,
                "Jim Hanson’s quiet life is suddenly disturbed by two people crossing the US/Mexico border – a woman and her young son – desperate to flee a Mexican cartel. After a shootout leaves the mother dead, Jim becomes the boy’s reluctant defender. He embraces his role as Miguel’s protector and will stop at nothing to get him to safety, as they go on the run from the relentless assassins."
            )
        )

        movies.add(
            MovieResponse(
                "Chaos Walking",
                "movie",
                412656,
                7.4,
                "/9kg73Mg8WJKlB9Y2SAJzeDKAnuB.jpg",
                "en",
                439,
                "Two unlikely companions embark on a perilous adventure through the badlands of an unexplored planet as they try to escape a dangerous and disorienting reality, where all inner thoughts are seen and heard by everyone."
            )
        )

        movies.add(
            MovieResponse(
                "The Unholy",
                "movie",
                632357,
                8.5,
                "/b4gYVcl8pParX8AjkN90iQrWrWO.jpg",
                "en",
                735,
                "Alice, a young hearing-impaired girl who, after a supposed visitation from the Virgin Mary, is inexplicably able to hear, speak and heal the sick. As word spreads and people from near and far flock to witness her miracles, a disgraced journalist hoping to revive his career visits the small New England town to investigate. When terrifying events begin to happen all around, he starts to question if these phenomena are the works of the Virgin Mary or something much more sinister."
            )
        )

        movies.add(
            MovieResponse(
                "Raya and the Last Dragon",
                "movie",
                527774,
                8.5,
                "/lPsD10PP4rgUGiGR4CCXA6iY0QQ.jpg",
                "en",
                735,
                "Long ago, in the fantasy world of Kumandra, humans and dragons lived together in harmony. But when an evil force threatened the land, the dragons sacrificed themselves to save humanity. Now, 500 years later, that same evil has returned and it’s up to a lone warrior, Raya, to track down the legendary last dragon to restore the fractured land and its divided people."
            )
        )

        movies.add(
            MovieResponse(
                "Demon Slayer – Kimetsu no Yaiba – The Movie: Mugen Train",
                "movie",
                635302,
                8.5,
                "/h8Rb9gBr48ODIwYUttZNYeMWeUU.jpg",
                "en",
                735,
                "Tanjirō Kamado, joined with Inosuke Hashibira, a boy raised by boars who wears a boar's head, and Zenitsu Agatsuma, a scared boy who reveals his true power when he sleeps, boards the Infinity Train on a new mission with the Fire Hashira, Kyōjurō Rengoku, to defeat a demon who has been tormenting the people and killing the demon slayers who oppose it!"
            )
        )

        movies.add(
            MovieResponse(
                "Monster Hunter",
                "movie",
                458576,
                8.5,
                "/1UCOF11QCw8kcqvce8LKOO6pimh.jpg",
                "en",
                735,
                "A portal transports Cpt. Artemis and an elite unit of soldiers to a strange world where powerful monsters rule with deadly ferocity. Faced with relentless danger, the team encounters a mysterious hunter who may be their only hope to find a way home."
            )
        )

        return movies
    }

    fun generateRemoteDummyTvShows(): List<MovieResponse> {
        val tvShows = ArrayList<MovieResponse>()

        tvShows.add(
            MovieResponse(
                "Van Helsing",
                "tv",
                65820,
                6.9,
                "/r8ODGmfNbZQlNhiJl2xQENE2jsk.jpg",
                "en",
                535,
                "Vanessa Helsing, the daughter of famous vampire hunter and Dracula nemesis Abraham Van Helsing is resurrected five years in the future to find out that vampires have taken over the world and that she possesses unique power over them. She is humanity’s last hope to lead an offensive to take back what has been lost."
            )
        )

        tvShows.add(
            MovieResponse(
                "Luis Miguel: The Series",
                "tv",
                79008,
                8.5,
                "/34FaY8qpjBAVysSfrJ1l7nrAQaD.jpg",
                "en",
                735,
                "The series dramatizes the life story of Mexican superstar singer Luis Miguel, who has captivated audiences in Latin America and beyond for decades."
            )
        )

        tvShows.add(
            MovieResponse(
                "Fear the Walking Dead",
                "tv",
                62286,
                8.5,
                "/4UjiPdFKJGJYdxwRs2Rzg7EmWqr.jpg",
                "en",
                735,
                "What did the world look like as it was transforming into the horrifying apocalypse depicted in \"The Walking Dead\"? This spin-off set in Los Angeles, following new characters as they face the beginning of the end of the world, will answer that question."
            )
        )

        tvShows.add(
            MovieResponse(
                "Tagesschau",
                "tv",
                94722,
                8.5,
                "/7dFZJ2ZJJdcmkp05B9NWlqTJ5tq.jpg",
                "en",
                735,
                "German daily news program, the oldest still existing program on German television."
            )
        )

        tvShows.add(
            MovieResponse(
                "The Queen of Flow",
                "tv",
                80240,
                8.5,
                "/3T5mSuziI0TMa7z9R5w3vNl2qok.jpg",
                "en",
                735,
                "After spending seventeen years in prison unfairly, a talented songwriter seeks revenge on the men who sank her and killed her family."
            )
        )

        tvShows.add(
            MovieResponse(
                "9-1-1",
                "tv",
                75219,
                8.5,
                "/wP9Ho6PCmmrFHLK1VjSAwZKVKtX.jpg",
                "en",
                735,
                "Explore the high-pressure experiences of police officers, paramedics and firefighters who are thrust into the most frightening, shocking and heart-stopping situations. These emergency responders must try to balance saving those who are at their most vulnerable with solving the problems in their own lives."
            )
        )

        tvShows.add(
            MovieResponse(
                "",
                "Black Lightning",
                71663,
                8.5,
                "/h1xbvvO6oqchfLe6xh0yLNnQxeM.jpg",
                "en",
                735,
                "Jefferson Pierce is a man wrestling with a secret. As the father of two daughters and principal of a charter high school that also serves as a safe haven for young people in a New Orleans neighborhood overrun by gang violence, he is a hero to his community."
            )
        )

        tvShows.add(
            MovieResponse(
                "Edens Zero",
                "tv",
                104711,
                8.5,
                "/zwj3sFZksoFsKqYPMmqGB95H3XJ.jpg",
                "en",
                735,
                "At Granbell Kingdom, an abandoned amusement park, Shiki has lived his entire life among machines. But one day, Rebecca and her cat companion Happy appear at the park’s front gates. Little do these newcomers know that this is the first human contact Granbell has had in a hundred years!\n\nAs Shiki stumbles his way into making new friends, his former neighbors stir at an opportunity for a robo-rebellion… And when his old homeland becomes too dangerous, Shiki must join Rebecca and Happy on their spaceship and escape into the boundless cosmos."
            )
        )

        tvShows.add(
            MovieResponse(
                "The Good Doctor",
                "tv",
                71712,
                8.5,
                "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                "en",
                735,
                "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives"
            )
        )

        tvShows.add(
            MovieResponse(
                "The Nevers",
                "tv",
                80828,
                8.5,
                "/v6Xmj8Fy7ZruVTz3y2Po7O0TQh4.jpg",
                "en",
                735,
                "In the last years of Victoria's reign, London is beset by the \"Touched\": people — mostly women — who suddenly manifest abnormal abilities, some charming, some very disturbing. Among them are Amalia True, a mysterious, quick-fisted widow, and Penance Adair, a brilliant young inventor. They are the champions of this new underclass, making a home for the Touched, while fighting the forces of… well, pretty much all the forces — to make room for those whom history as we know it has no place."
            )
        )

        return tvShows
    }

    fun generateLocalDummyMovies(): List<MovieEntity> {
        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity(
                460465,
                "Mortal Kombat",
                "",
                7.8,
                "/2xmx8oPlbDaxTjHsIOZvOt5L3aJ.jpg",
                "en",
                2126,
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe."
            )
        )

        movies.add(
            MovieEntity(
                399566,
                "Godzilla vs. Kong",
                "",
                8.2,
                "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                "en",
                4886,
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages."
            )
        )
        return movies
    }

    fun generateLocalDummyTvShows(): List<TvEntity> {
        val tvShows = ArrayList<TvEntity>()

        tvShows.add(
            TvEntity(
                65820,
                "",
                "Van Helsing",
                6.9,
                "/r8ODGmfNbZQlNhiJl2xQENE2jsk.jpg",
                "en",
                535,
                "Vanessa Helsing, the daughter of famous vampire hunter and Dracula nemesis Abraham Van Helsing is resurrected five years in the future to find out that vampires have taken over the world and that she possesses unique power over them. She is humanity’s last hope to lead an offensive to take back what has been lost."
            )
        )

        tvShows.add(
            TvEntity(
                79008,
                "",
                "Luis Miguel: The Series",
                8.5,
                "/34FaY8qpjBAVysSfrJ1l7nrAQaD.jpg",
                "en",
                735,
                "The series dramatizes the life story of Mexican superstar singer Luis Miguel, who has captivated audiences in Latin America and beyond for decades."
            )
        )
        return tvShows
    }
}