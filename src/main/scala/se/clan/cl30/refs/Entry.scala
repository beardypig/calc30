package se.clan.cl30.refs

import scodec._
import scodec.Codec
import scodec.codecs._

case class Entry(
                  surface: Int,
                  bleed: Int,
                  aice: Int,
                  flaps: Int,
                  altitude: Long,
                  weight: Long,
                  temp: Int,
                  v1: Int = 0,
                  vr: Int = 0,
                  v2: Int = 0,
                  rwl: Long = 0) {
  def ::(that: Entry): Boolean =
    surface == that.surface && bleed == that.bleed &&
      aice == that.aice && flaps == that.flaps &&
      Entry.clampAltitude(altitude) == Entry.clampAltitude(that.altitude) &&
      Entry.clampWeight(weight) == Entry.clampWeight(that.weight)
}

object Entry {
  implicit val codec: Codec[Entry] = {
    ("surface" | uint8L) ::
      ("bleed" | uint8L) ::
      ("aice" | uint8L) ::
      ("flaps" | uint8L) ::
      ("altitude" | uint32L) ::
      ("weight" | uint32L) ::
      ("temp" | int8L) ::
      ("v1" | uint8L) ::
      ("vr" | uint8L) ::
      ("v2" | uint8L) ::
      ("rwl" | uint32L)
  }.as[Entry]

  def apply(surface: Int,
            bleed: Int,
            aice: Int,
            flaps: Int,
            altitude: Long,
            weight: Long,
            temp: Int):
  Entry = new Entry(surface, bleed, aice, flaps, altitude, weight, temp)

  def clampAltitude(alt: Long): Long = Math.min(
    Math.round(Math.max(alt, 0) / 1000.0) * 1000, 10000)

  def clampWeight(weight: Long): Long =
    Seq(28000L, 30000L, 32000L, 34000L, 36000L, 38000L, 38850L)
      .minBy(w => Math.abs(w - weight))
}