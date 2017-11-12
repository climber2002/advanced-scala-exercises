package ch3

trait Printable[A] { self =>
  def format(value: A): String

  def contramap[B](func: B => A): Printable[B] = new Printable[B] {
    override def format(b: B): String = self.format(func(b))
  }
}

object Printable {

  def format[A](value: A)(implicit p: Printable[A]): String = p.format(value)
}
