package se.clan.cl30.refs

import cats.effect.IO
import scodec.stream.decode

object TableLoader {

  def load(path: String): Table = decode
    .once[Entry]
    .many
    .decodeMmap[IO](new java.io.FileInputStream(path).getChannel)
    .runLog
    .unsafeRunSync()

}
