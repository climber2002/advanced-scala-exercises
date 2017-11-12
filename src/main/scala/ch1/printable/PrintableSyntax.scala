package ch1.printable

object PrintableSyntax {
  implicit class PrintOps[A](val a: A) {
    def format(implicit printable: Printable[A]): String =
      printable.format(a)

    def print(implicit printable: Printable[A]): Unit =
      println(format(printable))
  }
}
