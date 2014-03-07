//重複を取り除くだけ
//既存のdictファイルに生成したdictを追記する時などに使えます
import scala.io.Source
if(args.length > 0) {
  val dict = Source.fromFile(args(0)).getLines().toList
	val dictsorted = dict.sorted.distinct
	dictsorted.foreach(println)
}
else Console.err.println("Please enter inputfile")
