package controllers

import javax.inject.Inject
import org.postgresql.jdbc.PgDatabaseMetaData
import play.api.mvc.{AbstractController, ControllerComponents}
import play.api.Configuration

import scala.concurrent.{ExecutionContext, Future}

class HomeController @Inject()(cc: ControllerComponents)(implicit assetsFinder: AssetsFinder, ec: ExecutionContext, configuration: Configuration)
  extends AbstractController(cc) {


  def login()= Action.async(parse.json){ implicit request =>

   Future(Ok(request.body))

  }

}
