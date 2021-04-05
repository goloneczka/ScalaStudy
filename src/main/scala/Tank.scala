case class Tank(brand : String, seats : Int, height : Int) extends Vehicle{

  val describe = "Tank is really aggressive vehicle"

  def drive(): String = {
    s"Tank $brand make Wrrrr !"
  }
}
