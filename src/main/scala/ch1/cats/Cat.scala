package ch1.cats

import cats.Show
import cats.Eq
import cats.instances.int._
import cats.instances.string._

final case class Cat(name: String,
                     age: Int,
                     color: String)

object Cat {
  implicit val showCat = new Show[Cat] {
    def show(cat: Cat): String =
      s"${cat.name} is a ${cat.age} year-old ${cat.color} cat."
  }

  implicit val eqCat = Eq.instance[Cat] { (cat1: Cat, cat2: Cat) =>
    Eq.eqv(cat1.name, cat2.name) &&
      Eq.eqv(cat1.age, cat2.age) &&
      Eq.eqv(cat1.color, cat2.color)
  }
}