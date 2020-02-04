package controllers

import play.api.libs.json.Json
import javax.inject.Inject
import play.api.Configuration
import play.api.mvc.{AbstractController, ControllerComponents}

import scala.concurrent.ExecutionContext


class PlacesController @Inject()(cc: ControllerComponents)(implicit assetsFinder: AssetsFinder, ec: ExecutionContext, configuration: Configuration)
  extends AbstractController(cc) {


  case class PlacesController(id: Int, name: String)

  val thePlaces: List[(Int, String)] = List(
    (1, "newyork"),
    (2, "chicago"),
    (3, "capetown")
  )

  implicit val thePlacesWrites = Json.writes[PlacesController]

  def listPlaces = Action {
    val json = Json.toJson(thePlaces)
    Ok(json)
  }


}