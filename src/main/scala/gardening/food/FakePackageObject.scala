package gardening.food

object FakePackageObject {
	type Weighted[T] = (Double, T)
	
	val fakeOrangeFood = List(Orange, Mandarin, Carrot)
	
	var fakeShownFood = 0
	
	def fakeShowFood(food: Food): Unit = {
		println(s"${food.name}s are ${food.color}")
		fakeShownFood += 1
	}
}
