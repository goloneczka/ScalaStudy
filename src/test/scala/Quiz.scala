import org.scalatest.FunSuite

class Quiz extends FunSuite {

  test("one"){

    case class Book(name : String, pages : Int, price: Int)

    val books = List(Book("Scala Testing", 200, 20),
      Book("Quo Vadis", 700, 15),
      Book("Clean Code", 450, 45),
      Book("Harry Poter", 500, 40))

    val matchedFromLoop = (for (b <- books) yield b.pages match {
      case b if(b > 200 && b < 700)  => "not too much and not too less"
      case 700 => "A lot of pages"
      case 200 => "A few pages"
    }).toArray

//    assert(!matchedFromLoop.contains("A few pages") && !matchedFromLoop.contains("A lot of pages") &&
//      matchedFromLoop.count(t => t ==="not too much and not too less") === 2)
//
//    assert(matchedFromLoop.contains("A few pages") && matchedFromLoop.contains("A lot of pages") &&
//      matchedFromLoop.count(t => t ==="not too much and not too less") === 1)
//
//    assert(matchedFromLoop.contains("A few pages") && matchedFromLoop.contains("A lot of pages") &&
//      !matchedFromLoop.contains("not too much and not too less"))
//
//    assert(matchedFromLoop.contains("A few pages") && matchedFromLoop.contains("A lot of pages") &&
//      matchedFromLoop.count(t => t ==="not too much and not too less") === 2)


  }

  test("two"){
    object Movies extends Enumeration {
      type Movie = Value

      val Musical = Value( "movies with a lot of music")
      val Comedy = Value( "entertainment audience")
      val Drama = Value(1, "emotional and unexpected series of events")
      val Horror = Value( "scare audience")
    }

//    assert(List(Movies.Drama, Movies.Musical, Movies.Comedy, Movies.Horror) === Movies.values.toList)
//    assert(List(Movies.Musical, Movies.Comedy, Movies.Drama, Movies.Horror) === Movies.values.toList)
//    assert(List(Movies.Musical, Movies.Comedy, Movies.Horror, Movies.Drama) === Movies.values.toList)
//    assertThrows[Error]{Movies.values}

  }

  test("three"){

    def fact(n: Int): Int = n match {
      case 0 => 1
      case n => n * fact(n-1)
    }
//
//    assertThrows[Error]{fact(3)}
//    assert(fact(3) === 6)
//    assert(fact(3) === 1)
//    assert(fact(3).isInfinite)

  }


}
