package ru.exxo.cars_example

import java.util.UUID

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

// our Car case class
case class Car(
                id: UUID,
                brand: String,
                model: String
              )
// and it's front-end counterpart:
@ScalaJSDefined
class CarJ extends js.Object {
  var id: String = _
  var brand: String = _
  var model: String = _
}


// we need a constructors:
object CarJ {
  def apply(x: String, y: String, z: String): CarJ = {
    new CarJ{
      id = x
      brand = y
      model = z
    }
  }

  def apply(y: String, z: String): CarJ = {
    new CarJ {
      id = UUID.randomUUID().toString
      brand = y
      model = z
    }
  }
}


//our implicits:
object CarImplicits {

  // adds convert method to CarJ:
  implicit class CarJ2S(carJ: CarJ) {

    def convert(): Car= {

      if ( carJ.id == null )

        Car(
          UUID.randomUUID(),
          carJ.brand,
          carJ.model
        )
      else
        Car(
          UUID.fromString(carJ.id),
          carJ.brand,
          carJ.model
        )
    }

  }

  // adds convert methods to Car:
  implicit class CarS2J(car: Car) {

    def convert(): CarJ = {
      CarJ(
        car.id.toString,
        car.brand,
        car.model
      )
    }
  }


  // 2 methods for auto-converting:
  implicit def carJ2S(carJ: CarJ): Car =
    carJ.convert()


  implicit def carS2J(car: Car): CarJ =
    car.convert()

}



