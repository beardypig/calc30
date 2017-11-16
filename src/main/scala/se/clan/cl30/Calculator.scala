package se.clan.cl30

import se.clan.cl30.refs.{Entry, TableLoader}

object Calculator {

  def main(args: Array[String]): Unit = {

    val refTable = TableLoader.load("refstable.cpd")
    println(refTable.head)
    val result = refTable.search(Entry(0,0,0,10,0,28000,-40,108,111,127,2790))
    println(result.toString)

  }

}
