package sdir

import os.Path

object Run extends App with Measure {
  val mode = args(0)
  val dir = args(1)
  val res =
    measure {
      mode match {
        case "sync" => Sync.scan(dir)
        case _ => Vector.empty
      }
    }
}


object Sync {
  def scan(in: String): Seq[Path] = {
    scan(Path(in))
  }

  def scan(dir: Path): Seq[Path] = {
    val paths = os.list(dir, sort = false)
    val dirs = paths.filter(_.toIO.isDirectory)
    paths ++ dirs.flatMap(scan)
  }
}


trait Measure {
  def measure[A](f: => Seq[A]): Seq[A] = {
    val init = System.currentTimeMillis()
    val res = f
    val time = System.currentTimeMillis() - init
    println(s"Took $time ms. to process ${res.size} items. ${res.size/time} files/ms")
    res
  }
}