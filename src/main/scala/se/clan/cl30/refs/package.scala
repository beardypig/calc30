package se.clan.cl30

package object refs {
  type Table = Vector[Entry]

  implicit class EntryTable(entries: Table) {

    def search(entry: Entry): Option[Entry] =
      entries.filter(_ :: entry) match {
        case IndexedSeq() => None
        case v => Some(v.minBy(e => Math.abs(e.temp - entry.temp)))
      }
  }
}
