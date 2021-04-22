//package javagenerics;
//
//import org.junit.Test;
//
//import java.util.ArrayList;
//
//
//@SuppressWarnings("ALL")
//public class CustomStackJavaTest {
//
//    class LandAnimal {}
//    class Animal extends LandAnimal {}
//    class Dog extends Animal {}
//    class Cat extends Animal {}
//
//    class CovariantAnimalStructure<T extends Animal> {}
//    class InvariantAnimalStructure<T> {}
//
//    // błąd kompilacji
//    // class ContravariantAnimalStructure<T super Animal> {}
//
//
//    @Test
//    public void javaVariance() {
//        CovariantAnimalStructure<Animal> covariantAnimalStructure = new CovariantAnimalStructure<>();
//        CovariantAnimalStructure<Dog> covariantDogStructure = new CovariantAnimalStructure<>();
//
//        // błąd
//        // covariantAnimalStructure = covariantDogStructure;
//
//        CovariantAnimalStructure<? extends Animal> covariantAnimalStructureExtends = new CovariantAnimalStructure<>();
//
//        covariantAnimalStructureExtends = covariantDogStructure;
//
//
//
//        CovariantAnimalStructure<? super Dog> covariantDogStructureSuper = new CovariantAnimalStructure<>();
//
//        covariantDogStructureSuper = covariantAnimalStructure;
//
//        ArrayList<? super Dog> arrayListSup = new ArrayList<>();
//        ArrayList<? extends Animal> arrayListExt = new ArrayList<Dog>();
//        Object object = arrayListSup.get(0);
//
//        Animal animal = arrayListExt.get(0);
//        Animal aaa = new Dog();
//        arrayListExt.add(aaa)
//
//    }
//}
