package se.clan.cl30

import org.scalatest._
import se.clan.cl30.refs.Entry

class TestEntry extends FunSuite {

  // valid altitudes = 1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000

  test("clampAltitude rounds to closest 1,000") {
    assert(Entry(0,0,0,0,1000,0,0).clampedAltitude == 1000)
    assert(Entry(0,0,0,0,1300,0,0).clampedAltitude == 1000)
    assert(Entry(0,0,0,0,1600,0,0).clampedAltitude == 2000)
  }

  test("clampAltitude round down to 10,000 for altitudes above 10,000") {
    assert(Entry(0,0,0,0,16000,0,0).clampedAltitude == 10000)
    assert(Entry(0,0,0,0,10001,0,0).clampedAltitude == 10000)
  }

  test("clampAltitude rounds up to 0 with negative altitude") {
    assert(Entry(0,0,0,0,-1000,0,0).clampedAltitude == 0)
    assert(Entry(0,0,0,0,-1,0,0).clampedAltitude == 0)
    assert(Entry(0,0,0,0,-10000,0,0).clampedAltitude == 0)
  }

  // valid weights = 28000, 30000, 32000, 34000, 36000, 38000, 38850

  test("clampWeight should round up to 28,000") {
    assert(Entry(0,0,0,0,0,20000,0).clampedWeight == 28000)
  }

  test("clampWeight should round down to 38,850") {
    assert(Entry(0,0,0,0,0,40000,0).clampedWeight == 38850)
  }

  test("clampWeight should round to closest weight") {
    assert(Entry(0,0,0,0,0,28100,0).clampedWeight == 28000)
    assert(Entry(0,0,0,0,0,28500,0).clampedWeight == 28000)
    assert(Entry(0,0,0,0,0,29001,0).clampedWeight == 30000)
    assert(Entry(0,0,0,0,0,29500,0).clampedWeight == 30000)
  }

  test("clampWeight should round up to 38,850") {
    assert(Entry(0,0,0,0,0,38426,0).clampedWeight == 38850)
  }
}
