package ch1.printable

object PrintableInstances {

  implicit val stringPrintable = new Printable[String] {
    def format(s: String): String = s
  }

  implicit val intPrintable = new Printable[Int] {
    def format(i: Int): String = i.toString
  }
}
