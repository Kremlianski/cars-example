package ru.exxo.cars_example

import java.util.UUID

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}
import CarImplicits._

import scala.collection.mutable
import scala.scalajs.js
import scala.scalajs.js.UndefOr

case class CarState ( cars: Map[UUID, Car] = Map.empty)

@JSExportTopLevel("CarState")
object CarState {

  import scala.scalajs.js.JSConverters._

  // prefer immutable var over mutable val !
  var state = CarState()

  @JSExport
  def addCar(car: CarJ): String = {
    _addCar(car).toString
  }

  private def _addCar(car: Car): UUID = {
    state = state.copy(cars = state.cars + (car.id -> car))
    car.id
  }


  @JSExport
  def removeCar(id: String): Unit =
    _removeCar(UUID.fromString(id))

  private def _removeCar(id: UUID): Unit = state = state.copy(state.cars - id)

  @JSExport
  def findCar(id: String): UndefOr[CarJ] = _findCar(UUID.fromString(id)).map(_.convert()).orUndefined

  private def _findCar(id: UUID): Option[Car]  = state.cars.get(id)

  @JSExport
  def carsSize(): Int = state.cars.size

  @JSExport
  def allCars(): js.Array[CarJ] = {

    val list =state.cars.values.map(_.convert()).toList
    mutable.Seq(list: _*).toJSArray

  }

}
