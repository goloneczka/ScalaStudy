import org.scalatest.FunSuite

class PatternMatching extends FunSuite {

  abstract class Vehicle {
    def drive() : String
  }

  case class Bus(brand: String, seats: Int, length: Int) extends Vehicle {

    def drive(): String = {
      s"Bus $brand is driving people to work and home"
    }
  }

  case class Tank(brand : String, seats : Int, height : Int) extends Vehicle{

    def drive(): String = {
      s"Tank $brand make Wrrrr !"
    }
  }

  case class Car(brand : String, seats : Int, color : String) extends Vehicle{

    def drive(): String = {
      s"Car $brand is driving really fast"
    }
  }


  test("basic view of pattern matching"){

    def matchNumberToString(x: Int): String = x match {
      case 1 => "one"
      case 2 => "two"
      case 3 => "three"
      case _ => "something else"
    }

    assert(matchNumberToString(1) === "one")
    assert(matchNumberToString(101) === "something else")

  }

  test("multiple choices") {
    def isTrue(x: Any): Boolean = x match {
      case 0 | false | null  => false
      case _ => true
    }

    assert(isTrue(0) === false)
    assert(isTrue(null) === false)
    assert(isTrue(6) === true)
    assert(isTrue("-124") === true)
  }

  test("if inside matching") {
    def isOdd(x: Int): Boolean = x match {
      case x if(x % 2 === 1)  => true
      case _ => false
    }

    assert(isOdd(0) === false)
    assert(isOdd(2112) === false)
    assert(isOdd(1) === true)
    assert(isOdd(127) === true)
  }



  test("pattern with objects"){

    def matchVehicleToString(x: Vehicle): String = x match {
      case Car("Fiat 126p", seats, color) => s"car named Fiat 126p with $seats seats has buty $color color and is super fast"
      case Bus(brand, 40, length) => s"Bus named $brand with 40 seats has $length meters length and drive you to home"
      case Tank(brand, seats, _) => s"Tank named $brand with $seats seats attack his enemies"
      case _ => "i don't know this vehicle"
    }

    assert(matchVehicleToString(Car("Fiat 126p", 5, "red")) ===
      "car named Fiat 126p with 5 seats has buty red color and is super fast")

    assert(matchVehicleToString(Bus("Solaris", 40, 30)) ===
      "Bus named Solaris with 40 seats has 30 meters length and drive you to home")

    assert(matchVehicleToString(Tank("Panther", 4, 45000)) ===
      "Tank named Panther with 4 seats attack his enemies")

    assert(matchVehicleToString(Car("Fiat 127p", 5, "red")) ===
      "i don't know this vehicle")
  }


  test("pattern with types"){

    val car: Vehicle = Car("Fiat 126p", 5, "red")
    val bus: Vehicle = Bus("Solaris", 40, 30)
    val tank: Vehicle = Tank("Panther", 4, 45000)

    def matchVehicleTypeToString(x: Vehicle): String = x match {
      case c: Car => c.drive()
      case b: Bus => b.drive()
      case t: Tank => t.drive()
    }

        assert(matchVehicleTypeToString(car) ===
          "Car Fiat 126p is driving really fast")
        assert(matchVehicleTypeToString(bus) ===
          "Bus Solaris is driving people to work and home")
        assert(matchVehicleTypeToString(tank) ===
          "Tank Panther make Wrrrr !")
    }


  test("pattern with comprehension"){

    case class Book(name : String, pages : Int, price: Int)

    val books = List(Book("Scala Testing", 200, 20),
      Book("Quo Vadis", 700, 15),
      Book("Clean Code", 450, 45),
      Book("Harry Poter", 500, 40))


    val matchedFromLoop = (for (b <- books) yield b.pages match {
      case 700 => "A lot of pages"
      case 500 | 450 => "not too much and not too less"
      case 200 => "A few pages"
    })

    assert(matchedFromLoop.contains("A lot of pages") ||
      matchedFromLoop.contains("not too much and not too less") ||
      matchedFromLoop.contains("A few pages"))
  }


}
