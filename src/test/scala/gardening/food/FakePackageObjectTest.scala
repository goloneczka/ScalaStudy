package gardening.food

import FakePackageObject._
import org.scalatest.FunSuite

class FakePackageObjectTest extends FunSuite {
	test("all orange foods have orange color assigned") {
		for (food <- fakeOrangeFood) {
			assert(food.color == "orange")
		}
	}
	
	test("shown food counter") {
		for(food <- fakeOrangeFood) {
			fakeShowFood(food)
		}
		assert(fakeShownFood == 3)
	}
}
