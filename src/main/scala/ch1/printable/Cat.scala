package ch1.printable

final case class Cat(name: String,
                     age: Int,
                     color: String)

object Cat {
  implicit val printable = new Printable[Cat] {
    def format(cat: Cat): String =
      s"${cat.name} is a ${cat.age} year-old ${cat.color} cat."
  }
}