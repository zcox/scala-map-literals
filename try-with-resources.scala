package iascala

package object using {
  import scala.language.reflectiveCalls

  def using[Closeable <: {def close(): Unit}, A](closeable: Closeable)(f: Closeable => A): A = 
    try {
      f(closeable)
    } finally {
      closeable.close()
    }

  def using2[Closeable <: {def close(): Unit}, A](closeables: Closeable*)(f: Seq[Closeable] => A): A = 
    try {
      f(closeables)
    } finally {
      closeables.reverse.foreach(_.close())
    }

  /*
  TODO use HList to abstract over arity of Closeables
  Then f doesn't use a Seq[Closeable], it just takes same number of Closeables as passed into 1st param clause
  using(c1) { (c1) => ... }
  using(c1, c2) { (c1, c2) => ... }
  using(c1, c2, c3) { (c1, c2, c3) => ... }
  */
}

object UseStuff {
  import java.io._
  import iascala.using._

  def readFirstLineFromFile(path: String): String = 
    using(new BufferedReader(new FileReader(path)))(_.readLine())

  import java.nio.charset._
  import java.nio.file._
  import java.util._
  import java.util.zip._

  def writeToFileZipFileContents(zipFileName: String, outputFileName: String) {
    val charset = StandardCharsets.US_ASCII
    val outputFilePath = Paths.get(outputFileName)
    using2(new ZipFile(zipFileName), Files.newBufferedWriter(outputFilePath, charset)) { cs =>
      val zf = cs(0)
      val writer = cs(1)
      for (entry <- zf.entries()) {
        val newLine = System.getProperty("line.separator")
        val zipEntryName = entry.getName() + newLine
        writer.write(zipEntryName, 0, zipEntryName.length())
      }
    }
  }
}
