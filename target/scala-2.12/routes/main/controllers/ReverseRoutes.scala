// @GENERATOR:play-routes-compiler
// @SOURCE:/home/trevor/Desktop/playProjects/my-form/conf/routes
// @DATE:Mon Feb 03 17:03:03 EAT 2020

import play.api.mvc.Call


import _root_.controllers.Assets.Asset
import _root_.play.modules.reactivemongo.PathBindables._

// @LINE:2
package controllers {

  // @LINE:4
  class ReverseHomeController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:4
    def login(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "login")
    }
  
  }

  // @LINE:2
  class ReversePlacesController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:2
    def listPlaces(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "places")
    }
  
  }


}
