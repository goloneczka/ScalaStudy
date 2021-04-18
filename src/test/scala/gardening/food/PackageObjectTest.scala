package gardening.food // zaimportowana jest tylko paczka, a nie konkretny obiekt

import org.scalatest.FunSuite

class PackageObjectTest extends FunSuite {
	test("all orange foods have orange color assigned") {
		for(food <- orangeFood) {
			assert(food.color == "orange")
		}
	}
	
	test("shown orange food counter is 3") {
		for(food <- orangeFood) {
			showFood(food)
		}
		assert(shownFood == 3)
	}
}
