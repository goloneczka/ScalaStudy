case class Bus(brand: String, seats: Int, length: Int) extends Vehicle {

  val describe = "Bus is really ecofriendly vehicle"

  def drive(): String = {
    s"Bus $brand is driving people to work and home"
  }
}
