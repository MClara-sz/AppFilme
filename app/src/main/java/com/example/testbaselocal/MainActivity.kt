package com.example.testbaselocal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontSynthesis.Companion.Style
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testbaselocal.ui.theme.TestBaseLocalTheme
import java.util.ListResourceBundle

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val movies = listOf(
            Movie(
                imageResource = R.drawable.anatomiadumaqueda,
                titulo = "Anatomia de U...",
                grupo = "Filmes",
                sinopse = "verdadeira historia",
                tituloori = "game...",
                genero = "drama",
                episodios = 1,
                ano = 2024,
                pais = "tu",
                diretor = "nos",
                elenco = "eu",
                disponivelAte = "2024.2.4"

            ),

            Movie(

                imageResource = R.drawable.parasita,
                titulo = "Parasita",
                grupo = "Filmes",
                sinopse = "verdadeira historia",
                tituloori = "game...",
                genero = "drama",
                episodios = 1,
                ano = 2024,
                pais = "tu",
                diretor = "nos",
                elenco = "eu",
                disponivelAte = "2024.2.4",

                ),
                    Movie(

                    imageResource = R.drawable.duna1,
                    titulo = "Duna",
                    grupo = "Filmes",
                    sinopse = "verdadeira historia",
                    tituloori = "game...",
                    genero = "drama",
                    episodios = 1,
                    ano = 2024,
                    pais = "tu",
                    diretor = "nos",
                    elenco = "eu",
                    disponivelAte = "2024.2.4",

            ),
            Movie(

                imageResource = R.drawable.duna2,
                titulo = "Duna 2",
                grupo = "Filmes",
                sinopse = "verdadeira historia",
                tituloori = "game...",
                genero = "drama",
                episodios = 1,
                ano = 2024,
                pais = "tu",
                diretor = "nos",
                elenco = "eu",
                disponivelAte = "2024.2.4",

                ),
            Movie(

                imageResource = R.drawable.oscondenadosbest,
                titulo = "Os Condenados",
                grupo = "Filmes",
                sinopse = "verdadeira historia",
                tituloori = "game...",
                genero = "drama",
                episodios = 1,
                ano = 2024,
                pais = "tu",
                diretor = "nos",
                elenco = "eu",
                disponivelAte = "2024.2.4",

                ),
            Movie(

                imageResource = R.drawable.opoderosochef2best,
                titulo = "O Poderoso Chefão",
                grupo = "Filmes",
                sinopse = "verdadeira historia",
                tituloori = "game...",
                genero = "drama",
                episodios = 1,
                ano = 2024,
                pais = "tu",
                diretor = "nos",
                elenco = "eu",
                disponivelAte = "2024.2.4",

                ),
            Movie(

                imageResource = R.drawable.schinniderbest,
                titulo = "A Lista de Schinider",
                grupo = "Filmes",
                sinopse = "verdadeira historia",
                tituloori = "game...",
                genero = "drama",
                episodios = 1,
                ano = 2024,
                pais = "tu",
                diretor = "nos",
                elenco = "eu",
                disponivelAte = "2024.2.4",

                ),
            Movie(

                imageResource = R.drawable.viagemachimirobest,
                titulo = "A Viagem de Chimiro",
                grupo = "Filmes",
                sinopse = "verdadeira historia",
                tituloori = "game...",
                genero = "drama",
                episodios = 1,
                ano = 2024,
                pais = "tu",
                diretor = "nos",
                elenco = "eu",
                disponivelAte = "2024.2.4",

                ),
        )
        setContent {
            val navController = rememberNavController()
            NavHost(navController, startDestination = "MyScreen") {
                composable("MyScreen") { MyScreen(navController, movies) }
                composable("DetailScreen/{titulo}") { backStackEntry ->
                    val title = backStackEntry.arguments?.getString("titulo")
                    val movie = findMovieByTitle(title, movies)
                    if (movie != null) {
                        DetailScreen(navController, movie)
                    } else {
                        Text(text = "Titulo nao encontrado!")
                    }
                }
            }
        }
    }
}

@Composable
fun findMovieByTitle(title: String?, movies: List<Movie>):Movie? {
    if(title.isNullOrEmpty()) {
        return null
    }
    return movies.find { it.titulo == title   }
    }



@Composable
fun MyScreen(navController: NavController, movies: List<Movie>){
    val groupByGroup = movies.groupBy {it.grupo}

    LazyColumn{
        groupByGroup.forEach { (grupo, moviesByGroup) ->
            item {
                Text(
                    text = grupo,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    ),
                    modifier = Modifier.padding(8.dp)
                )
            }
            item {
                LazyRow{
                    items(moviesByGroup.size) {index ->
                        MoviePoster(movie = moviesByGroup[index], navController)
                        Spacer(modifier = Modifier.width(16.dp))
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.width(16.dp))
            }
    }

    }
}

@Composable
fun MoviePoster(movie: Movie, navController: NavController) {
    Column(
        modifier = Modifier.clickable {
            navController.navigate("DetailScreen/${movie.titulo}")
        }
    ){
        Image(
            painter = painterResource(id = movie.imageResource),
            contentDescription = null,
            modifier = Modifier
                .size(120.dp, 180.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Text(
            text = movie.titulo,
            style= TextStyle(fontSize=14.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top=4.dp)
        )

    }
}

@Composable
fun DetailScreen(navController: NavController, movie: Movie) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement =  Arrangement.spacedBy(16.dp)

    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Voltar",
                modifier = Modifier
                    .clickable { navController.popBackStack() }
                    .size(24.dp)

                )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "Detalhes", fontWeight = FontWeight.Bold)
        }
        Image(
            painter = painterResource(id = movie.imageResource),

            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Row(){
            Text(text = "Titulo: ", fontWeight = FontWeight.Bold)
            Text(text = "${movie.grupo}")
        }
        Row(){
            Text(text = "Titulo: ", fontWeight = FontWeight.Bold)
            Text(text = "${movie.genero}")
        }
        Row(){
            Text(text = "Titulo: ", fontWeight = FontWeight.Bold)
            Text(text = "${movie.episodios}")
        }
        Row(){
            Text(text = "Titulo: ", fontWeight = FontWeight.Bold)
            Text(text = "${movie.episodios}")
        }
        Row(){
            Text(text = "Titulo: ", fontWeight = FontWeight.Bold)
            Text(text = "${movie.ano}")
        }
        Row(){
            Text(text = "Titulo: ", fontWeight = FontWeight.Bold)
            Text(text = "${movie.pais}")
        }
        Row(){
            Text(text = "Titulo: ", fontWeight = FontWeight.Bold)
            Text(text = "${movie.elenco}")
        }
        Row(){
            Text(text = "Titulo: ", fontWeight = FontWeight.Bold)
            Text(text = "${movie.disponivelAte}")
        }

    }
}



data class Movie(
    val imageResource: Int,
    val titulo: String,
    val grupo: String,
    val sinopse: String,
    val tituloori: String,
    val genero: String,
    val episodios: Int,
    val ano: Int,
    val pais: String,
    val diretor: String,
    val elenco: String,
    val disponivelAte: String
)