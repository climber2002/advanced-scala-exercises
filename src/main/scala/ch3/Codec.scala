package ch3

trait Codec[A] { self =>

  def encode(value: A): String

  def decode(value: String): Option[A]

  def imap[B](dec: A => B, enc: B => A): Codec[B] = new Codec[B] {

    override def encode(value: B): String = self.encode(enc(value))

    override def decode(value: String): Option[B] = self.decode(value).map(dec)

  }

}

object Codec {
  def encode[A](value: A)(implicit c: Codec[A]): String =
    c.encode(value)

  def decode[A](value: String)(implicit c: Codec[A]): Option[A] =
    c.decode(value)

  implicit val intCodec = new Codec[Int] {
    override def encode(value: Int): String = value.toString

    override def decode(value: String) = try {
      Some(value.toInt)
    } catch {
      case e: Throwable => None
    }
  }

  implicit def boxCodec[A](implicit c: Codec[A]): Codec[Box[A]] =
    c.imap[Box[A]](Box(_), _.value)
}
