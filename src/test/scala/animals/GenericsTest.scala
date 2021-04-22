package animals

import datastructures.CustomStack
import org.scalatest.FunSuite

//noinspection ZeroIndexToHead,ScalaUnusedSymbol
class GenericsTest extends FunSuite {
	
	// #################################################################################################################
	// variance/covariance/invariance
	abstract class Animal {
		def name: String
		
		def color: (Int, Int, Int) // RGB color
	}
	
	class Cat(val name: String, val color: (Int, Int, Int) = (0, 0, 0)) extends Animal
	
	class Dog(val name: String, val color: (Int, Int, Int) = (0, 0, 0)) extends Animal
	
	class CovariantDogStructure[+T <: Dog]
	
	class InvariantDogStructure[T <: Dog]
	
	class ContravariantDogStructure[-T <: Dog]
	
	
	test("variance/covariance/invariance") {
		// covariant
		var covariantDogStructure = new CovariantDogStructure[Dog]
		val covariantHuskyStructure = new CovariantDogStructure[Husky]
		
		covariantDogStructure = covariantHuskyStructure
		
		
		// invariant
		var invariantDogStructure = new InvariantDogStructure[Dog]
		var invariantHuskyStructure = new InvariantDogStructure[Husky]
		
		assertTypeError("invariantDogStructure = invariantHuskyStructure")
		
		
		// contravariant
		var contravariantHuskyStructure = new ContravariantDogStructure[Husky]
		val contravariantDogStructure = new ContravariantDogStructure[Dog]
		
		contravariantHuskyStructure = contravariantDogStructure
	}
	
	
	// #################################################################################################################
	// Invariance
	
	test("animal invariance") {
		val cat = new Cat("Tom")
		val dog = new Dog("Fido")
		
		val catStack: CustomStack[Cat] = new CustomStack[Cat]
		catStack.push(cat)
		assertTypeError("val animalStack: CustomStack[Animal] = catStack")
		// animalStack.push(dog) -- ok, stack jest typu animal, więc możemy dodać psa
		// val acquiredCat = animalStack.pop() -- błąd! otrzymamy instancję Dog
	}
	
	
	// #################################################################################################################
	// Covariance
	def formatAnimalNames(animals: List[Animal]): List[String] = {
		animals.map(animal => s"The ${animal.getClass.getSimpleName}'s name is ${animal.name}")
	}
	
	test("animal covariance") {
		//Lista jest zdefiniowana jako "type List[+A] = List[A]"
		val cats: List[Cat] = List(new Cat("Whiskers"), new Cat("Tom"))
		val dogs: List[Dog] = List(new Dog("Fido"), new Dog("Rex"))
		
		val formattedCats = formatAnimalNames(cats)
		assert(formattedCats.size == 2)
		assert(formattedCats(0) == "The Cat's name is Whiskers")
		assert(formattedCats(1) == "The Cat's name is Tom")
		
		val formattedDogs = formatAnimalNames(dogs)
		assert(formattedDogs.size == 2)
		assert(formattedDogs(0) == "The Dog's name is Fido")
		assert(formattedDogs(1) == "The Dog's name is Rex")
	}
	
	
	// #################################################################################################################
	// Contravariance
	abstract class ColorHexConverter[-A] {
		def toHex(colorObject: A): String
	}
	
	class AnimalColorConverter extends ColorHexConverter[Animal] {
		override def toHex(colorObject: Animal): String =
			return f"Animal's hex color: #${colorObject.color._1}%02x${colorObject.color._2}%02x${colorObject.color._3}%02x"
	}
	
	class CatColorConverter extends ColorHexConverter[Cat] {
		override def toHex(colorObject: Cat): String =
			return f"Cat's hex color: #${colorObject.color._1}%02x${colorObject.color._2}%02x${colorObject.color._3}%02x"
	}
	
	def catHexColorToInt(catColorConverter: ColorHexConverter[Cat], cat: Cat): Int = {
		val fullHexString = catColorConverter.toHex(cat)
		val extractedHexString = fullHexString.takeRight(6)
		return Integer.parseInt(extractedHexString, 16)
	}
	
	test("animal contravariance") {
		val cat = new Cat("Tom")
		val animalColorConverter: ColorHexConverter[Animal] = new AnimalColorConverter
		val catColorConverter: ColorHexConverter[Cat] = new CatColorConverter
		
		assert(animalColorConverter.toHex(cat) == "Animal's hex color: #000000")
		assert(catColorConverter.toHex(cat) == "Cat's hex color: #000000")
		
		assert(catHexColorToInt(animalColorConverter, cat) == 0)
		assert(catHexColorToInt(catColorConverter, cat) == 0)
	}
	
	
	// #################################################################################################################
	// Upper type bounds
	class Bulldog(name: String, color: (Int, Int, Int) = (0, 0, 0)) extends Dog(name, color)
	
	class Husky(name: String, color: (Int, Int, Int) = (0, 0, 0)) extends Dog(name, color)
	
	class DogStackUpper[T <: Dog] extends CustomStack[T]
	
	test("upper type bounds") {
		val dogStack = new DogStackUpper[Dog]
		val bulldogStack = new DogStackUpper[Bulldog]
		val huskyStack = new DogStackUpper[Husky]
		
		// błąd kompilacji:
		// val catStack = new DogStack[Cat]
		// val animalStack = new DogStack[Animal]
	}
	
	// #################################################################################################################
	// Lower type bounds
	class DogStackLower[T >: Dog] extends CustomStack[T]
	
	test("lower type bounds") {
		val dogStack = new DogStackLower[Dog]
		val animalStack = new DogStackLower[Animal]
		
		// błąd kompilacji:
		// val catStack = new DogStackLower[Cat]
		//		val huskyStack = new DogStackLower[Husky]
		//		val bulldogStack = new DogStackLower[Bulldog]
	}
}
