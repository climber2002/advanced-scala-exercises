package ch4

object MyId {

  type Id[A] = A

  def pure[A](value: A): Id[A] = value

  def map[A, B](initial: Id[A])(func: A => B): B =
    func(initial)

  def flatMap[A, B](initial: Id[A])(func: A => Id[B]): Id[B] =
    func(initial)

}
