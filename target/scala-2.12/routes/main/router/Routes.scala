// @GENERATOR:play-routes-compiler
// @SOURCE:/home/trevor/Desktop/playProjects/my-form/conf/routes
// @DATE:Mon Feb 03 17:03:03 EAT 2020

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.modules.reactivemongo.PathBindables._

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:2
  PlacesController_0: controllers.PlacesController,
  // @LINE:4
  HomeController_1: controllers.HomeController,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:2
    PlacesController_0: controllers.PlacesController,
    // @LINE:4
    HomeController_1: controllers.HomeController
  ) = this(errorHandler, PlacesController_0, HomeController_1, "/")

  def withPrefix(addPrefix: String): Routes = {
    val prefix = play.api.routing.Router.concatPrefix(addPrefix, this.prefix)
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, PlacesController_0, HomeController_1, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """places""", """controllers.PlacesController.listPlaces"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """login""", """controllers.HomeController.login"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:2
  private[this] lazy val controllers_PlacesController_listPlaces0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("places")))
  )
  private[this] lazy val controllers_PlacesController_listPlaces0_invoker = createInvoker(
    PlacesController_0.listPlaces,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.PlacesController",
      "listPlaces",
      Nil,
      "GET",
      this.prefix + """places""",
      """ Routes""",
      Seq()
    )
  )

  // @LINE:4
  private[this] lazy val controllers_HomeController_login1_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("login")))
  )
  private[this] lazy val controllers_HomeController_login1_invoker = createInvoker(
    HomeController_1.login,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "login",
      Nil,
      "POST",
      this.prefix + """login""",
      """""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:2
    case controllers_PlacesController_listPlaces0_route(params@_) =>
      call { 
        controllers_PlacesController_listPlaces0_invoker.call(PlacesController_0.listPlaces)
      }
  
    // @LINE:4
    case controllers_HomeController_login1_route(params@_) =>
      call { 
        controllers_HomeController_login1_invoker.call(HomeController_1.login)
      }
  }
}
