import org.scalatest.FunSuite

class PatternMatching extends FunSuite {


  test("pattern one"){

    def matchNumberToString(x: Int): String = x match {
      case 1 => "one"
      case 2 => "two"
      case 3 => "three"
      case _ => "something else"
    }

    assert(matchNumberToString(1) === "one")
    assert(matchNumberToString(101) === "something else")
  }

  test("pattern twoA") {
    def isTrue(x: Any): Boolean = x match {
      case 0 | false | null  => false
      case _ => true
    }

    assert(isTrue(0) === false)
    assert(isTrue(null) === false)
    assert(isTrue(6) === true)
    assert(isTrue(-124) === true)
  }

  test("pattern twoB") {
    def isOdd(x: Int): Boolean = x match {
      case x if(x % 2 === 1)  => true
      case _ => false
    }

    assert(isOdd(0) === false)
    assert(isOdd(2112) === false)
    assert(isOdd(1) === true)
    assert(isOdd(127) === true)
  }



  test("pattern two"){

    def matchVehicleToString(x: Vehicle): String = x match {
      case Car("Fiat 126p", seats, color) => s"car named Fiat 126p with $seats seats has buty $color color and is super fast"
      case Bus(brand, 40, length) => s"Bus named $brand with 40 seats has $length meters length and drive you to home"
      case Tank(brand, seats, 45000) => s"Tank named $brand with $seats seats has 45000 kg weight and attack his enemies"
    }

    assert(matchVehicleToString(Car("Fiat 126p", 5, "red")) ===
      "car named Fiat 126p with 5 seats has buty red color and is super fast")

    assert(matchVehicleToString(Bus("Solaris", 40, 30)) ===
      "Bus named Solaris with 40 seats has 30 meters length and drive you to home")

    assert(matchVehicleToString(Tank("Panther", 4, 45000)) ===
      "Tank named Panther with 4 seats has 45000 kg weight and attack his enemies")
  }

  test("pattern three"){

    val car: Vehicle = Car("Fiat 126p", 5, "red")
    val bus: Vehicle = Bus("Solaris", 40, 30)
    val tank: Vehicle = Tank("Panther", 4, 45000)

    def vehDrive (veh: Vehicle): String = {
      veh.drive()
    }

    def matchVehicleToString(x: Vehicle): String = x match {
      case Car(_, _, _) => vehDrive(car)
      case Bus(_, _, _) => vehDrive(bus)
      case Tank(_, _, _) => vehDrive(tank)
    }

    assert(matchVehicleToString(car) ===
      "Car Fiat 126p is driving really fast")

    assert(matchVehicleToString(bus) ===
      "Bus Solaris is driving people to work and home")

    assert(matchVehicleToString(tank) ===
      "Tank Panther make Wrrrr !")
  }

  test("pattern four"){

    val car: Vehicle = Car("Fiat 126p", 5, "red")
    val bus: Vehicle = Bus("Solaris", 40, 30)
    val tank: Vehicle = Tank("Panther", 4, 45000)

    def vehDrive (veh: Vehicle): String = {
      veh.drive()
    }

    def matchVehicleTypeToString(x: Vehicle): String = x match {
      case c: Car => vehDrive(c)
      case b: Bus => vehDrive(b)
      case t: Tank => vehDrive(t)
    }

        assert(matchVehicleTypeToString(car) ===
          "Car Fiat 126p is driving really fast")
        assert(matchVehicleTypeToString(bus) ===
          "Bus Solaris is driving people to work and home")
        assert(matchVehicleTypeToString(tank) ===
          "Tank Panther make Wrrrr !")
    }



}
