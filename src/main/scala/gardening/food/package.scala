package gardening

package object food extends HelloWorldPrinter with GreeterPrinter {
	
	type Weighted[T] = (Double, T)
	
	val orangeFood = List(Orange, Mandarin, Carrot)
	
	var shownFood = 0
	
	def showFood(food: Food): Unit = {
		println(s"${food.name}s are ${food.color}")
		shownFood += 1
	}
	
	class Fruit(calories: Int, weighted: Weighted[Food]) {
		def showDensity(): Unit = println(s"Fruit's calories: $calories, weight: ${weighted._1}")
	}
	
	override def printHelloWorld(): Unit = println("Hello World!")
	
	override def greet(name: String): Unit = println(s"Hello, $name!")
}
