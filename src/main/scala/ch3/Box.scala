package ch3

final case class Box[A](value: A)

object Box {

  implicit val stringPrintable =
    new Printable[String] {
      def format(value: String): String =
        "\"" + value + "\""
    }
  implicit val booleanPrintable =
    new Printable[Boolean] {
      def format(value: Boolean): String =
        if(value) "yes" else "no"
    }

  def format[A](box: Box[A])(implicit printable: Printable[A]): String =
    printable.format(box.value)

}
