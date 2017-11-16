package se.clan.cl30

import org.scalatest._
import se.clan.cl30.refs.Entry

class TestEntry extends FunSuite {

  // valid altitudes = 1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000

  test("clampAltitude rounds to closest 1,000") {
    assert(Entry.clampAltitude(1000) == 1000)
    assert(Entry.clampAltitude(1300) == 1000)
    assert(Entry.clampAltitude(1600) == 2000)
  }

  test("clampAltitude round down to 10,000 for altitudes above 10,000") {
    assert(Entry.clampAltitude(16000) == 10000)
    assert(Entry.clampAltitude(10001) == 10000)
  }

  test("clampAltitude rounds up to 0 with negative altitude") {
    assert(Entry.clampAltitude(-1000) == 0)
    assert(Entry.clampAltitude(-1) == 0)
    assert(Entry.clampAltitude(-10000) == 0)
  }

  // valid weights = 28000, 30000, 32000, 34000, 36000, 38000, 38850

  test("clampWeight should round up to 28,000") {
    assert(Entry.clampWeight(20000) == 28000)
  }

  test("clampWeight should round down to 38,850") {
    assert(Entry.clampWeight(40000) == 38850)
  }

  test("clampWeight should round to closest weight") {
    assert(Entry.clampWeight(28100) == 28000)
    assert(Entry.clampWeight(28500) == 28000)
    assert(Entry.clampWeight(29001) == 30000)
    assert(Entry.clampWeight(29500) == 30000)
  }

  test("clampWeight should round up to 38,850") {
    assert(Entry.clampWeight(38426) == 38850)
  }
}
