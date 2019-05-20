package ru.exxo.cars_example


import org.scalatest._
import CarImplicits._

import scala.scalajs.js

class CarSpec extends FreeSpec {

  "Let's produce some operations with CarState" in {


    val volvo = CarJ("Volvo","2018")
    val mercedes = CarJ("Mercedes", "2019")
    val bmw = CarJ("BMW", "2017")

    assert(CarState.carsSize() == 0)

    val volvoId = CarState.addCar(volvo)

    assert(CarState.carsSize() == 1)

    val mercedesId = CarState.addCar(mercedes)

    assert(CarState.carsSize() == 2)

    val bmwId = CarState.addCar(bmw)

    assert(CarState.carsSize() == 3)

    assert(CarState.findCar(bmwId).isInstanceOf[CarJ])

    assert(CarState.findCar(bmwId).get.convert() == bmw.convert())

    assert(CarState.findCar("5f0c93a4-e7fa-407e-abbc-8d405b41ac34") == js.undefined)

    CarState.removeCar(bmwId)

    assert(CarState.carsSize() == 2)

    assert(CarState.findCar(bmwId) == js.undefined)

    assert(CarState.allCars().isInstanceOf[js.Array[CarJ]])

    assert(CarState.allCars().length == 2)

    assert(CarState.allCars().apply(0).brand == "Volvo")

    assert(CarState.allCars().apply(0).id == volvoId)


  }

}


