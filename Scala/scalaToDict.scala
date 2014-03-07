//Vimなどに使えるScala系のdictファイルを生成することができます
//先にdefのある行をgrepしてお使いください
//例: 
//grep -h def -r ~/scalaz-scalaz-seven > scalaz.txt 
//scala scalaToDict.scala scalaz.txt > scalaz.dict
import scala.io.Source
val regSym = """def\s([^0-9a-zA-Z_]+?)[\s\(\[]+""".r
val regAlf = """def\s(.+?)[^0-9a-zA-Z_]""".r
if(args.length > 0) {
  val lines = Source.fromFile(args(0)).getLines().toList
  val defdictSym = for{
    line <- lines
    if !(line contains "private def") && !(line contains "implicit def") && !(line contains "protected def")
    reg1 <- regSym.findFirstMatchIn(line)
  } yield reg1.group(1)

  val defdictAlf = for{
    line <- lines
    if !(line contains "private def") && !(line contains "implicit def") && !(line contains "protected def")
    reg2 <- regAlf.findFirstMatchIn(line)
  } yield reg2.group(1)

  val defdictsorted = (defdictSym ::: defdictAlf).sorted.distinct
	defdictsorted.foreach(println)
}
else Console.err.println("Please enter inputfile")
