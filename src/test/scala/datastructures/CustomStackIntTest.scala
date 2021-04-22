package datastructures

import org.scalatest.FunSuite

class CustomStackIntTest extends FunSuite {
	
	test("test push int") {
		val stack = new CustomStack[Int]
		stack.push(1)
		stack.push(2)
		stack.push(3)
		assert(stack.size == 3)
	}
	
	test("test peek long") {
		val stack = new CustomStack[Int]
		stack.push(1)
		stack.push(2)
		stack.push(3)
		val expectedHead = 3
		assert(stack.peek == expectedHead)
		assert(stack.size == 3)
	}
	
	test("tes pop int") {
		val stack = new CustomStack[Int]
		stack.push(1)
		stack.push(2)
		stack.push(3)
		assert(stack.size == 3)
		val popped = stack.pop()
		assert(popped == 3)
		assert(stack.size == 2)
	}
}
