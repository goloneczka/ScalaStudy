case class Car(brand : String, seats : Int, color : String) extends Vehicle{

  val describe = "Car is really fast vehicle"

  def drive(): String = {
    s"Car $brand is driving really fast"
  }
}
