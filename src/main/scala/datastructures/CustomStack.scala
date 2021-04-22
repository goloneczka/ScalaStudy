package datastructures

class CustomStack[A] {
	private var elements: List[A] = Nil // Nil = list with 0 elements
	
	def push(x: A): Unit =
		elements = x :: elements
	
	def peek: A = elements.head
	
	def pop(): A = {
		val currentTop = peek
		elements = elements.tail
		return currentTop
	}
	
	def size: Int = elements.size
}
