import org.scalatest.FunSuite


class ForComprehension extends FunSuite {

  case class Book(name : String, pages : Int, price: Int)

  val books = List(Book("Scala Testing", 200, 20),
    Book("Quo Vadis", 700, 15),
    Book("Clean Code", 450, 45),
    Book("Harry Poter", 500, 40))


  test("basic view of for comprehension") {

    val allBooksOnDiscount = for (book <- books)
      yield book.price * 0.8

    assert(allBooksOnDiscount.size === 4)
    assert(allBooksOnDiscount.max == 36) // 45 * 0.8 = 36
    assert(allBooksOnDiscount.min == 12) // 15 * 0.8 = 12
  }

  test("additional filter") {

    val averageSizeBooks = for (book <- books if (book.pages >= 300 && book.pages <= 500))
      yield book.name

    assert(averageSizeBooks.size === 2)
    assert(averageSizeBooks.contains("Clean Code") && averageSizeBooks.contains("Harry Poter") )
  }

  test("multi generators, usage without yield") {
    for (
      n <- 1 to 3;
      c <- 'a' to 'c'
    ) println(s"($n, $c)")

    val looped = for (
      n <- 1 to 3;
      c <- 'a' to 'c'
    ) yield (n, c)

    assert(looped.isInstanceOf[IndexedSeq[(Int, Char)]])

  }

  test("multi generators and ifs"){

    val booksWithDuplicatedPrice = Book("added book with duplicated price", 333, 15) :: books

    val duplicated = for {
      b1 <- booksWithDuplicatedPrice
      b2 <- booksWithDuplicatedPrice
      if(b1 != b2)
      if (b1.price === b2.price )
    } yield b1.name

    assert(duplicated.size == 2 )
    assert(duplicated.contains("added book with duplicated price") && duplicated.contains("Quo Vadis"))

  }

}