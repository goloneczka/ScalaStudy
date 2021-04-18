import org.scalatest.FunSuite
import scala.language.implicitConversions

case class Enum() extends FunSuite{

  test("basic view of enum"){
    object Planets extends Enumeration {
      type SolarSystem = Value
      val Mercury, Venus, Earth, Mars, Jupiter, Saturn, Uranus, Neptune = Value
    }
    assert(Planets.values.size === 8)
    assert(Planets.Venus.toString === "Venus")
    assert(Planets.Mercury.id === 0)
    assert(Planets.Earth.id === 2)
  }

  test("IDS in enum"){
    object Movies extends Enumeration {
      type Movie = Value

      val Musical = Value(77, "movies with a lot of music")
      val Comedy = Value(2, "entertainment audience")
      val Drama = Value(3, "emotional and unexpected series of events")
      val Horror = Value(4, "scare audience")
    }
    assert("entertainment audience" === Movies.Comedy.toString)
    assert(Movies.Musical === Movies.withName("movies with a lot of music"))

    assert(List(Movies.Musical, Movies.Comedy, Movies.Drama, Movies.Horror) !== Movies.values.toList)
    assert(List(Movies.Comedy, Movies.Drama, Movies.Horror, Movies.Musical) === Movies.values.toList)

  }

  test("extends value in enum"){
    object Movies extends Enumeration {

      val Musical = Movie( "movies with a lot of music", 1010)
      val Comedy = Movie( "entertainment audience", 999)
      val Drama = Movie( "emotional and unexpected series of events", 1022)
      val Horror = Movie( "scare audience", 777)

      sealed case class Movie private[Movies](describe: String, count: Int) extends Val(describe)

      implicit def valueToMovie(v: Value): Movie = {
        v.asInstanceOf[Movie]
      }
    }

    def isLowestSet(movie: Movies.Movie): Boolean = {
      Movies.values.toList.sortBy(t => t.count).head === movie
    }

    assert(Movies.Comedy.id === 1)
    assert(isLowestSet(Movies.Horror))
    assert(!isLowestSet(Movies.Drama))
  }

  test("enum with pattern matching"){
    object Movies extends Enumeration {
      type Movie = Value

      val Musical = Value(1, "movies with a lot of music")
      val Comedy = Value(2, "entertainment audience")
      val Drama = Value(3, "emotional and unexpected series of events")
      val Horror = Value(4, "scare audience")
    }

    def countMovies(x: Movies.Movie): Int = x match {
      case Movies.Musical => 1010
      case Movies.Horror => 777
      case Movies.Drama => 1022
      case Movies.Comedy => 999
    }

    assert(countMovies(Movies.Musical) === 1010)
    assert(countMovies(Movies.withName("emotional and unexpected series of events")) === 1022)
  }
}
