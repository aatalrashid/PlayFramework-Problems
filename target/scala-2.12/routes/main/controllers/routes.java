// @GENERATOR:play-routes-compiler
// @SOURCE:/home/trevor/Desktop/playProjects/my-form/conf/routes
// @DATE:Mon Feb 03 17:03:03 EAT 2020

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseHomeController HomeController = new controllers.ReverseHomeController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReversePlacesController PlacesController = new controllers.ReversePlacesController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseHomeController HomeController = new controllers.javascript.ReverseHomeController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReversePlacesController PlacesController = new controllers.javascript.ReversePlacesController(RoutesPrefix.byNamePrefix());
  }

}
